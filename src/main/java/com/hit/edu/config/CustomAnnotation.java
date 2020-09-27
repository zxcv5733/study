package com.hit.edu.config;

import java.lang.annotation.*;

/**
 * @Author Ling dong.
 * @Date 2020/9/27 - 21:34
 */
@Target(ElementType.METHOD)           // 注解用于什么地方
@Documented                         // 注解是否将包含在JavaDoc中
@Inherited                          // 是否允许子类继承该注解
@Retention(RetentionPolicy.RUNTIME) // 什么时候使用该注解
public @interface CustomAnnotation {
    String value() default "";
}
