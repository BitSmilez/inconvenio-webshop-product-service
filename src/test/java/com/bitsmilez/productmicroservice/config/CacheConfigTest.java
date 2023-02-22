package com.bitsmilez.productmicroservice.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

class CacheConfigTest {
    private CacheConfig cacheConfig;

    @BeforeEach
    public void setUp() {
        cacheConfig = new CacheConfig();
    }

    @Test
    void cacheConfiguration() {

        RedisCacheConfiguration config = cacheConfig.cacheConfiguration();
        Assertions.assertNotNull(config);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<Object> valueSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        RedisSerializationContext.SerializationPair<Object> serializationPair = RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer);

        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer))
                .serializeValuesWith(serializationPair);

        Assertions.assertNotNull(defaultConfig);

        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(config.getTtl())
                .serializeKeysWith(config.getKeySerializationPair())
                .serializeValuesWith(config.getValueSerializationPair())
                .disableCachingNullValues();

        Assertions.assertNotNull(cacheConfig);
    }
}
