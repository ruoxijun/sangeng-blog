package com.ruoxijun.config;


import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring6.data.redis.FastJsonRedisSerializer;
import com.alibaba.fastjson2.support.spring6.data.redis.GenericFastJsonRedisSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // 使用官方 GenericFastJsonRedisSerializer 作为默认序列化器
        GenericFastJsonRedisSerializer fastJsonSerializer = new GenericFastJsonRedisSerializer();
        template.setDefaultSerializer(fastJsonSerializer);

        // key 用 String
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        // 对需要 JSON 的部分可使用 defaultSerializer 或单独指定 value/hashValue
        template.setValueSerializer(fastJsonSerializer);
        template.setHashValueSerializer(fastJsonSerializer);

        template.afterPropertiesSet();
        return template;
    }
}