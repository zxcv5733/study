package com.hit.edu.controller;

import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author: Li dong
 * @date: 2023/2/11 19:34
 * @description:
 */
@RestController
public class ThreadController {

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


    @SneakyThrows
    @GetMapping("/threadDemo")
    public String threadPool(){

        Map<String, Object> map = new HashMap<>(2);


        CompletableFuture<Void> addressFuture = CompletableFuture.runAsync(() -> {
            map.put("address", 1);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行任务一");
        }, threadPoolTaskExecutor);


        CompletableFuture<Void> goodsFuture = CompletableFuture.runAsync(() -> {
            map.put("goods", 1);
            System.out.println("执行任务二");
        }, threadPoolTaskExecutor);


        CompletableFuture.allOf(addressFuture, goodsFuture).get();

        System.out.println(map);

        return "success";
    }

    @SneakyThrows
    @GetMapping("/asyncTask")
    @Async("asyncTaskExecutor")
    public String asyncTask(){
        System.out.println(Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(6);
        return "success";
    }
}
