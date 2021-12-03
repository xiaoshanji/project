package com.shanji.over.crawler.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * @version: V1.0
 * @className: JsonUtil
 * @packageName: com.shanji.json
 * @data: 2020/7/18 19:36
 * @description:  json工具类，可以将json字符串与各种对象转换，在调用 jsonStrToEntity时，由于泛型的限制，所以在调用以后需要进行强制转换
 *      依赖：
 *      <dependency>
 *       <groupId>com.fasterxml.jackson.core</groupId>
 *       <artifactId>jackson-databind</artifactId>
 *       <version>2.9.8</version>
 *     </dependency>
 */
public class JsonUtil<T>
{
    private static ObjectMapper mapper = new ObjectMapper();

    public static Map<String,String> jsonStrToMap(String jsonStr) throws Exception
    {
        return mapper.readValue(jsonStr,new TypeReference<Map<String,String>>(){});
    }

    public static String objStrToJsonStr(Object object) throws Exception
    {
        return mapper.writeValueAsString(object);
    }

    public static Object jsonStrToEntity(String jsonStr,Class cla) throws Exception
    {
        return mapper.readValue(jsonStr,cla);
    }

    public static JsonNode jsonStrToJsonNode(String jsonStr) throws Exception
    {
        return mapper.readTree(jsonStr);
    }



}
