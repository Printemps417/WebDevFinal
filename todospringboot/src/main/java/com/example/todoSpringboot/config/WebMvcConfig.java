package com.example.todoSpringboot.config;

//import com.example.todoSpringboot.utils.AuthTokenInterceptor;
import com.example.todoSpringboot.interceptor.AuthTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: WebMvcConfig
 * Package: com.example.todoSpringboot
 * Description:
 * User: 1953287730
 *
 * @Author 栗飞龙
 * @Create 2023/10/17 9:16
 * @Version 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private AuthTokenInterceptor authTokenInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // 使用allowedOriginPatterns替代allowedOrigins
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("Authorization", "Cache-Control", "Content-Type")
                .allowCredentials(true)
                .maxAge(3600);

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authTokenInterceptor)
                .addPathPatterns("/**") // 应用到所有API
                .excludePathPatterns("/accounts/login","/accounts/reloadtoken"); // 排除登录和token刷新接口
    }

}

