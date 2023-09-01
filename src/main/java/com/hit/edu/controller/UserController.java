package com.hit.edu.controller;

import com.hit.edu.po.User;
import com.hit.edu.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Li dong
 * @date: 2023/9/1 14:54
 * @description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 添加
     * @return
     */
    @RequestMapping("/add")
    public String add(){
        userService.add();
        return "success";
    }


    @RequestMapping("/list")
    public List<User> list(){
        return userService.list();
    }
}
