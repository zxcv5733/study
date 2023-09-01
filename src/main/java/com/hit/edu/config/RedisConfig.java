package com.hit.edu.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author: Li dong
 * @date: 2023/9/1 15:44
 * @description:
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * retemplate相关配置
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(factory);

        GenericJackson2JsonRedisSerializer jacksonRedis = new GenericJackson2JsonRedisSerializer();
        // 值采用json序列化
        template.setValueSerializer(jacksonRedis);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(jacksonRedis);

        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jacksonRedis);
        template.afterPropertiesSet();

        return template;
    }

}
