package com.hit.edu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Author Ling dong.
 * @Date 2020/9/20 - 20:55
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    CustomHandlerInterceptor customHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器 拦截规则
        // 多个拦截器时 以此添加 执行顺序按添加顺序
        registry.addInterceptor(customHandlerInterceptor).addPathPatterns("/*");
    }
}
