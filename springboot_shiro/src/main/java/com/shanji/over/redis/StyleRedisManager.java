package com.shanji.over.redis;

import com.shanji.over.cache.StyleCache;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @version: V1.0
 * @className: StyleRedisManager
 * @packageName: com.shanji.over.redis
 * @data: 2020/11/23 21:47
 * @description:
 */
public class StyleRedisManager implements CacheManager
{
    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException
    {
        String[] arr = s.split(".");
        System.out.println(arr.length);
        StyleCache<K,V> cache = new StyleCache<>(s);
        return cache;
    }
}
