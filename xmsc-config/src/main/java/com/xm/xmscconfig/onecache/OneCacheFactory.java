package com.xm.xmscconfig.onecache;


import com.xm.xmscbean.utils.serial.Serializer;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存工厂，生产具体的缓存产品实例,同一配置属性，采用单利模式。
 *
 */
public class OneCacheFactory
{
    // memcache map
    private static ConcurrentHashMap<MemcacheConfig, CacheClientIF> memcacheClientMap = new ConcurrentHashMap<>();

    // redis map
    private static ConcurrentHashMap<RedisCacheConfig, CacheClientIF> redisClientMap = new ConcurrentHashMap<>();

    // localCache map
    private volatile static CacheClientIF localCacheClient;


    // 获取memcached 客户端实例
    public static synchronized CacheClientIF getMemcacheClient(MemcacheConfig memcacheConfig)
    {
        return getMemcacheClient(memcacheConfig, null);
    }

    public static synchronized CacheClientIF getMemcacheClient(MemcacheConfig memcacheConfig, Serializer valueSerializer)
    {
        CacheClientIF cacheClient = memcacheClientMap.get(memcacheConfig);
        if (null == cacheClient)
        {
//            cacheClient = new MemcacheClientImpl(memcacheConfig, valueSerializer);
            memcacheClientMap.put(memcacheConfig, cacheClient);
        }
        return cacheClient;
    }

    //redis 缓存客户端
    public static synchronized CacheClientIF getRedisClient(RedisCacheConfig redisCacheConfig)
    {
        return getRedisClient(redisCacheConfig, null, null);
    }

    public static synchronized CacheClientIF getRedisClient(RedisCacheConfig redisCacheConfig, Serializer keySerializer, Serializer valueSerializer)
    {
        CacheClientIF cacheClient = redisClientMap.get(redisCacheConfig);
        if (null == cacheClient)
        {
//            cacheClient = new RedisCacheClientImpl(redisCacheConfig, keySerializer, valueSerializer);
            redisClientMap.put(redisCacheConfig, cacheClient);
        }
        return cacheClient;
    }

    /***
     * 本地缓存客户端
     *
     * @return CacheClientIF
     */
    public static synchronized CacheClientIF getLocalCacheClient()
    {
        if (Objects.isNull(localCacheClient))
        {
//            localCacheClient = new LocalCacheClientImpl();
            return localCacheClient;
        }
        return localCacheClient;
    }
}