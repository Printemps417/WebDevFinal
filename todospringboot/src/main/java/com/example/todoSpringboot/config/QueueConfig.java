package com.example.todoSpringboot.config;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: QueueConfig
 * Package: com.example.todoSpringboot.config
 * Description:
 * User: 1953287730
 *
 * @Author 栗飞龙
 * @Create 2023/12/28 11:50
 * @Version 1.0
 */
@Configuration
public class QueueConfig {
    @Bean
    public Queue queue1(){
        return new Queue("select.queue");
    }
    @Bean
    public Queue queue2(){
        return new Queue("remove.queue");
    }
}
