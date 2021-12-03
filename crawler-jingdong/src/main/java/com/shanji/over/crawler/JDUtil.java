package com.shanji.over.crawler;

import cn.hutool.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.shanji.over.crawler.http.HttpUtil;
import com.shanji.over.crawler.json.JsonUtil;
import com.shanji.over.goods.Goods;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.HttpCookie;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version: V1.0
 * @className: JDUtil
 * @packageName: com.shanji
 * @data: 2020/7/31 16:01
 * @description:  此程序用来爬取京东的商品信息
 *
 *  依赖：
 *     <dependency>
 *       <groupId>org.apache.httpcomponents</groupId>
 *       <artifactId>httpclient</artifactId>
 *       <version>4.5.2</version>
 *     </dependency>
 *
 *     <dependency>
 *       <groupId>org.jsoup</groupId>
 *       <artifactId>jsoup</artifactId>
 *       <version>1.11.3</version>
 *     </dependency>
 *
 *     <dependency>
 *       <groupId>com.fasterxml.jackson.core</groupId>
 *       <artifactId>jackson-databind</artifactId>
 *       <version>2.9.8</version>
 *     </dependency>
 *
 *   以及其他包中的工具类
 */
@Component
public class JDUtil
{




    private List<String> failUrls = new ArrayList<>();

    private Map<String,String> header = new HashMap<String, String>(){{
        put("cookie","");
        put("user-agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.20 Safari/537.36");
    }};

    /*public static void main(String[] args) throws Exception
    {

        Scanner input = new Scanner(System.in);
        System.out.println("商品名:");
        String shopName = input.nextLine();
        System.out.println("页数（每页30条）：");
        int pageCount = input.nextInt();
        run(shopName,pageCount);

//        getInfo("https://item.jd.com/64132594488.html");

    }*/
    public List<Goods> run(String word,int count) throws Exception
    {
        List<Goods> goods = new LinkedList<>();
        Set<String> urls = getUrls(word, count);
        for(String temp : urls)
        {
            Goods g = null;
            try{
                g = getInfo(temp);
            }
            catch (Exception e){
                e.printStackTrace();
                failUrls.add(temp);
            }
            if(g != null){
                goods.add(g);
            }
            TimeUnit.MILLISECONDS.sleep(500);
        }

        if(!failUrls.isEmpty()){
            for(String temp : failUrls){
                Goods g = null;
                try{
                    g = getInfo(temp);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                if(g != null){
                    goods.add(g);
                }
            }
            failUrls.clear();
        }
        return goods;
    }


    // 获取商品的详细信息
    public Goods getInfo(String url) throws Exception
    {


        Goods goods = new Goods();

        String html = HttpUtil.sendGet(url, header);


        Document document = Jsoup.parse(html);


        System.out.println(url);


        // 提取商品标题

        goods.setTitle(document.select("div[class='sku-name']").text());


        // 获取商品的图片列表
        StringBuilder img = new StringBuilder();
        for(String temp : getPics(document))
        {
            img.append(temp);
            img.append(";");
        }
        goods.setImg(img.toString());



        // 通过调用 api 获取商品的价格
        String PicUrl = "https://item-soa.jd.com/getWareBusiness?skuId=";
        int start = url.lastIndexOf("/");
        int end = url.lastIndexOf(".h");


        // url.substring(start + 1, end) 提取 url 中的商品的编码

        JsonNode priceNode = JsonUtil.jsonStrToJsonNode(HttpUtil.sendGet(PicUrl + url.substring(start + 1, end), header));
        // 从返回的 json 数据中得到价格

        goods.setPrice(new BigDecimal(priceNode.get("price").get("p").asText()));



        // 定位参数所在的 ul
        // 两种情况，有时 html 中的 ul 标签中 class 属性不会有 p-parameter-list 要进行判断
        Elements uls = document.select("ul[class='parameter2 p-parameter-list']");
        Element ul = null;

        if(uls.size() == 0)
        {
            ul = document.select("ul[class='parameter2']").get(0);
        }
        else
        {
            ul = uls.get(0);
        }

        // 获取参数列表 li ，打印其中的值

        StringBuilder param = new StringBuilder();

        Elements lis = ul.getElementsByTag("li");
        for(Element li : lis)
        {
            param.append(li.text());
            param.append(";");
        }

        // 上面获取的是商品介绍中的参数，这里获取规格与包装中的参数，但此位置不一定会有数据，所以要进行判断
        Elements div = document.select("div[class='Ptable']");
        if(div.size() > 0)
        {
            Element divOfFirst = div.get(0);
            Elements dls = divOfFirst.select("dl[class='clearfix']");
            for(Element dl : dls)
            {
                param.append(dl.getElementsByTag("dt").get(0).text() + "：" + dl.getElementsByTag("dd").get(0).text());
                param.append(";");
            }
        }

        goods.setParams(param.toString());


        StringBuilder detail = new StringBuilder();

        // 获取商品的详细，详细信息均为图片
        for(String temp : getDetail(document))
        {
            detail.append(temp);
            detail.append(";");
        }

        goods.setDetails(detail.toString());

        return goods;
    }


    public Set<String> getPics(Document document)
    {
        Set<String> result = new HashSet<>();

        // 定位图片所在的 div
        Element picDiv = document.getElementById("spec-list");
        // 获取 div 中的 img 标签
        Elements imgs = picDiv.getElementsByTag("img");
        for(Element img : imgs)
        {
            // 提取 img 标签中的图片链接
            String path = img.attr("src");

            // 去掉某些图片后面的多余的后缀
            int index = path.lastIndexOf("!");
            if(index > -1)
            {
                path = path.substring(0,index);
            }

            // 对图片的 url 进行更改，以获取 800x800 的图片
            int start = path.indexOf("n5");
            path = path.substring(0,start) + "n1" + path.substring(start + 2);

            int end = path.indexOf("_jfs",start);

            if(end > -1)
            {
                path = path.substring(0,start + 3) + "s800x800_jfs" + path.substring(end + 4);
            }
            else
            {
                path = path.replace("jfs","s800x800_jfs");
            }

            path = "https:" + path;
            result.add(path);
        }

        return result;
    }

    //通过京东的搜索API，获取到所需要的所有商品的详情页链接
    public Set<String> getUrls(String word,int count) throws Exception
    {
        Set<String> result = new HashSet<>();

        // 对 url 中的中文字符进行编码
        word = URLEncoder.encode(word);

        String url = "https://search.jd.com/Search?keyword=" + word + "&enc=utf-8";
        for(int i = 1 ; i <= count ; i++)
        {
            // 通过调用 api 获取页面 html 代码
            Document doc = Jsoup.connect(url + "&page=" + i).maxBodySize(0).get();




            // 提取出每次返回的 html 代码中的所有商品详情页链接
            result.addAll(getUrls(doc));
        }
        return result;
    }

    // 对每次调用 api 返回的 html 代码进行链接提取
    public Set<String> getUrls(Document document)
    {

        Set<String> result = new HashSet<>();

        // 商品信息都位于 id 为 J_goodsList 的 div 下
        Element div = document.getElementById("J_goodsList");

        // 获取所有的商品列表标签
        Elements ulList = div.select("li[class='gl-item']");
        for (Element item : ulList)
        {
            // 提取每个商品的详情页链接
            result.add("https://" + item.select("a[target='_blank']").attr("href").substring(2));
        }
        return result;
    }



    public Set<String> getDetail(Document document) throws Exception
    {
        Set<String> result = new HashSet<>();

        // 通过正则表达式，获取到商品详情图片所对应的 url
        String pattern = "colorSize:.*?desc:(.*?),";
        // 这里需要忽略回车，所以要传入参数 Pattern.DOTALL
        Pattern compile = Pattern.compile(pattern,Pattern.DOTALL);
        Matcher matcher = compile.matcher(document.toString());
        String url = "";
        while (matcher.find())
        {
            url = matcher.group(1).replace("'","").substring(3);
        }
        // 获取对应的 json 字符串

        String detailHtml = HttpUtil.sendGet("https://" + url,header);


        if (detailHtml.startsWith("showdesc"))
        {
            pattern = "showdesc[(](.*?)[)]";
            compile = Pattern.compile(pattern);
            matcher = compile.matcher(detailHtml);
            while (matcher.find())
            {
                detailHtml = matcher.group(1);
            }
        }


        JsonNode jsonNode = JsonUtil.jsonStrToJsonNode(detailHtml);
        String contentText = jsonNode.get("content").asText();


        // 两种情况，一种直接返回 img 标签，详情图片为对应的 src 的值，另一种，返回一系列的 div 标签，图片路径在其对应的 style 属性的 background-image 的值。
        if(contentText.startsWith("<style>"))
        {
            // 获取 div 的背景图片路径
            pattern = "background-image:url(.*?)[};]";
            compile = Pattern.compile(pattern);
            matcher = compile.matcher(contentText);
            while (matcher.find())
            {
                url = "https:" + matcher.group(1).replace("(","").replace(")","");
                result.add(url);
            }
        }
        else
        {
            Document parse = Jsoup.parse(contentText);
            // 获取所有的 img 标签
            Elements imgs = parse.getElementsByTag("img");
            for(Element img : imgs)
            {
                String attr = img.attr("data-lazyload");
                if(!attr.startsWith("http"))
                {
                    attr = "https:" + attr;
                }
                result.add(attr);
            }
        }
        return result;

    }
}
