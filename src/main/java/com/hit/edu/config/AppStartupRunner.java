package com.hit.edu.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author Ling dong.
 * @Date 2020/9/20 - 21:12
 */
@Slf4j
@Component
public class AppStartupRunner implements ApplicationRunner {

    // 应用启动加载参数配置
    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("ApplicationRunner参数名称：{}", args.getOptionNames());
        log.info("ApplicationRunner参数值：{}", args.getOptionValues("age"));
        log.info("ApplicationRunner参数：{}", Arrays.toString(args.getSourceArgs()));
    }
}
