package com.example.todoSpringboot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: LockUtils
 * Package: com.example.todoSpringboot.utils
 * Description:
 * User: 1953287730
 *
 * @Author 栗飞龙
 * @Create 2023/12/24 16:32
 * @Version 1.0
 */

public class LockUtils {
    @Autowired
    private RedisTemplate redisTemplate;

    public  boolean lock(String key,long expiretime){
        boolean acquired = redisTemplate.opsForValue().setIfAbsent(key,"lock",expiretime, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(acquired);
    }

    public void unlock(String key){
        redisTemplate.delete(key);
    }
}
