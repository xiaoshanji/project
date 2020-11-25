package com.shanji.over.cache;

import com.shanji.over.util.ApplicationUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collection;
import java.util.Set;

/**
 * @version: V1.0
 * @className: StyleCache
 * @packageName: com.shanji.over.cache
 * @data: 2020/11/23 21:47
 * @description:
 */
public class StyleCache<K,V> implements Cache<K,V>
{
    private String cacheName;

    public StyleCache()
    {

    }
    public StyleCache(String cacheName)
    {
        this.cacheName = cacheName;
    }

    @Override
    public V get(K k) throws CacheException
    {
        return (V)getRedisTemplate().opsForHash().get(this.cacheName,k.toString());
    }

    @Override
    public V put(K k, V v) throws CacheException
    {
        getRedisTemplate().opsForHash().put(this.cacheName,k.toString(),v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException
    {
        return (V) getRedisTemplate().opsForHash().delete(this.cacheName,k.toString());
    }

    @Override
    public void clear() throws CacheException
    {
        getRedisTemplate().delete(this.cacheName);
    }

    @Override
    public int size()
    {
        return getRedisTemplate().opsForHash().size(this.cacheName).intValue();
    }

    @Override
    public Set<K> keys()
    {
        return getRedisTemplate().opsForHash().keys(this.cacheName);
    }

    @Override
    public Collection<V> values()
    {
        return getRedisTemplate().opsForHash().values(this.cacheName);
    }


    public RedisTemplate getRedisTemplate()
    {
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationUtil.getBeanByName("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
