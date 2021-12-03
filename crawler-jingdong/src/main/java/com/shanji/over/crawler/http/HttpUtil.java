package com.shanji.over.crawler.http;

import cn.hutool.http.HttpRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @version: V1.0
 * @className: HttpUtil
 * @packageName: com.shanji.http
 * @data: 2020/7/31 16:21
 * @description:
 */
public class HttpUtil
{
    public static String sendGet(String url, Map<String, String> headers) throws Exception
    {
        HttpRequest request = HttpRequest.get(url);
        if(headers == null)
        {
            headers = new HashMap<>();
        }
        for(Map.Entry<String,String> entry : headers.entrySet())
        {
            request.header(entry.getKey(),entry.getValue());
        }
        return request.execute().body();
    }

    public static String sendPost(String url, Map<String, String> headers,Map<String,String> params) throws Exception
    {
        HttpRequest request = HttpRequest.post(url);
        if(headers == null)
        {
            headers = new HashMap<>();
        }
        if(params == null)
        {
            params = new HashMap<>();
        }
        for(Map.Entry<String,String> entry : headers.entrySet())
        {
            request.header(entry.getKey(),entry.getValue());
        }

        for(Map.Entry<String,String> entry : params.entrySet())
        {
            request.body(entry.getKey(),entry.getValue());
        }
        return request.execute().body();
    }
}
