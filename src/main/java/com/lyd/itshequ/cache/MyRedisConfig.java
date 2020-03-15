package com.lyd.itshequ.cache;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.lyd.itshequ.bean.LikeDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.net.UnknownHostException;

import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;

@Configuration
public class MyRedisConfig {
    @Bean
    public RedisTemplate<Object, LikeDTO> myRedisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, LikeDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<LikeDTO> redisSerializer = new Jackson2JsonRedisSerializer<LikeDTO>(LikeDTO.class);
        template.setDefaultSerializer(redisSerializer);
        return template;
    }
    @Primary//将某个缓存管理器设置为默认的
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //  解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                //设置缓存过期时间
                //.entryTtl(Duration.ofMinutes(150))
                //禁用缓存NULL值，不缓存NULL校验
                .disableCachingNullValues()
                //设置拼接前缀
                .computePrefixWith(cacheName -> cacheName)
                //设置自定义前缀
                //.prefixKeysWith("我的员工")
                // 设置CacheManager的值序列化方式为json序列化，可加入@Class属性
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer));
        // 使用RedisCacheConfiguration创建RedisCacheManager
        RedisCacheManager redisCacheManager = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(redisCacheConfiguration).build();
        return redisCacheManager;
    }
}
