package com.example.todoSpringboot.component;

import com.example.todoSpringboot.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class DestroyRunner {
    @PreDestroy
    public void onDestroy() throws Exception {
        // 清理代码
//        redisService.deleteKeysWithPrefixes("student_lessons");
        System.out.println("……………………系统关闭……………………");
    }
}
