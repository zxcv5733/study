package com.hit.edu.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;

/**
 * @Author Ling dong.
 * @Date 2020/9/20 - 21:09
 */
@Slf4j
@Component
public class CommandLineStartupRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("CommandLineRunner传入参数：{}", Arrays.toString(args));
    }
}
