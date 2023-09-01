package com.hit.edu.service;

import com.hit.edu.po.User;

import java.util.List;

/**
 * @author: Li dong
 * @date: 2023/9/1 14:56
 * @description:
 */
public interface UserService {
    /**
     * 添加用户
     */
    void add();

    /**
     * 列表
     * @return
     */
    List<User> list();
}
