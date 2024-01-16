package com.example.todoSpringboot.config;

import com.example.todoSpringboot.utils.LockUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: LockConfig
 * Package: com.example.todoSpringboot.config
 * Description:
 * User: 1953287730
 *
 * @Author 栗飞龙
 * @Create 2023/12/24 16:34
 * @Version 1.0
 */
@Configuration
public class LockConfig {

    @Bean
    public LockUtils lockUtils(){
        return new LockUtils();
    }
}
