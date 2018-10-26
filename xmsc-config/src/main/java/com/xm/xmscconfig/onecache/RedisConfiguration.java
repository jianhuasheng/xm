package com.ggj.hqbs.life.config.onecache;

import com.ggj.hqbs.life.config.xconf.XconfConfiguration;
import com.ggj.platform.onecache.config.RedisCacheConfig;
import com.ggj.platform.onecache.factory.CacheClientIF;
import com.ggj.platform.onecache.factory.OneCacheFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@AutoConfigureAfter({XconfConfiguration.class})
public class RedisConfiguration {

    @Value("${redis.local_life.host}")
    private String host;

    @Value("${redis.local_life.port}")
    private Integer port;

    @Value("${redis.local_life.password}")
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
