package com.hit.edu.config;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author: Li dong
 * @date: 2023/9/1 15:59
 * @description:
 */
@Component
public class ApplicationContextHolder {

    private static ApplicationContext applicationContext = null;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextHolder.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(String name) {
        return (T)applicationContext.getBean(name);
    }

    public static <T> T getBean(Class clz) {
        return (T)applicationContext.getBean(clz);
    }

}