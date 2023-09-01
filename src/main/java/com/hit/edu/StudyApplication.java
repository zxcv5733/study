package com.hit.edu;

import com.hit.edu.config.ApplicationContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author Ling dong.
 * @Date 2020/9/20 - 20:01
 */
@EnableAsync
@SpringBootApplication
@MapperScan("com.hit.edu.dao")
@EnableCaching
public class StudyApplication extends SpringBootServletInitializer implements ApplicationContextAware {
    public static void main(String[] args) {
        SpringApplication.run(StudyApplication.class, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //重点在此，通过这个赋值context
        ApplicationContextHolder.setApplicationContext(applicationContext);
    }
}
