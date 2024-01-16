//package com.example.todoSpringboot.config;
//
//import com.example.todoSpringboot.entity.Lessons;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//public class RedisConfig {
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(factory);
//
//        Jackson2JsonRedisSerializer<Lessons> serializer = new Jackson2JsonRedisSerializer<>(Lessons.class);
//
//        template.setValueSerializer(serializer);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.afterPropertiesSet();
//
//        return template;
//    }
//
//}