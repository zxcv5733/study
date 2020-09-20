package com.hit.edu.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Ling dong.
 * @Date 2020/9/20 - 20:38
 */
@Configuration
public class FilterRegistration {
    
    @Bean
    public FilterRegistrationBean filterregistration(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // Filter可以new，也可以使用依赖注入Bean
        registration.setFilter(new CustomFilter());
        // 过滤器名称
        registration.setName("customFilter");
        // 拦截路径
        registration.addUrlPatterns("/*");
        // 设置顺序数字越小越先执行
        registration.setOrder(10);
        return registration;
    }
}
