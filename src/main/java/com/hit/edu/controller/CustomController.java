package com.hit.edu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Ling dong.
 * @Date 2020/9/20 - 20:46
 */
@RestController
public class CustomController {

    @GetMapping("/testFilter")
    public Object testFiler(){
        return "testFilter";
    }
}
