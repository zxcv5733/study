package com.hit.edu.config;

import com.hit.edu.controller.CustomController;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author Ling dong.
 * @Date 2020/9/27 - 22:02
 */
@Component
@Aspect
@Slf4j
public class ComtomProxy {

    @Pointcut("execution(* com.hit.edu.controller.CustomController.testFiler(..))")
    public void pointCut(){

    }

    // 前置通知
    @Before("pointCut()")
    public void before(JoinPoint joinPoint){
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        CustomAnnotation annotation = method.getAnnotation(CustomAnnotation.class);
        String value = annotation.value();
        System.out.println(value);
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("参数="+arg+" ");
        }
        log.info("before...");
    }

    // 后置通知
    @AfterReturning(value = "pointCut()", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue){
        System.out.println(returnValue);
        log.info("afterReturning...");
    }

    // 最终通知
    @After("pointCut()")
    public void after(JoinPoint joinPoint){
        Object target = joinPoint.getTarget();
        System.out.println(target.toString());
        log.info("after....");
    }

    // 异常通知
    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        log.info("afterThrowing");
    }

}
