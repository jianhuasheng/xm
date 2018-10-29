package com.xm.xmscconfig.onecache;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;


@Slf4j
public class RedisConfiguration {


    private String host;


    private Integer port;


    private String password;

    @Bean
    public RedisCacheConfig redisCacheConfig() {
        log.info("redis初始化");
        if (log.isDebugEnabled()) {
            log.debug("redis config: host:{}, port:{}, password:{}", host, port, password);
        } else {
            log.info("redis config: host:{}", host);
        }

        RedisCacheConfig redisCacheConfig = new RedisCacheConfig();
        redisCacheConfig.setHost(host);
        redisCacheConfig.setPort(port);
        redisCacheConfig.setPassword(password);
        redisCacheConfig.setTimeout(5000);
        redisCacheConfig.setMaxIdle(20);
        redisCacheConfig.setMaxTotal(100);
        redisCacheConfig.setMaxWait(6000);
        return redisCacheConfig;
    }


    @Bean(name = "redisClient")
    public CacheClientIF redisClient(RedisCacheConfig redisCacheConfig) {
        return OneCacheFactory.getRedisClient(redisCacheConfig);
    }

}
