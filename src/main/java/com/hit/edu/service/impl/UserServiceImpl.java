package com.hit.edu.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.hit.edu.dao.UserDao;
import com.hit.edu.po.User;
import com.hit.edu.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Li dong
 * @date: 2023/9/1 14:57
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    /**
     * 添加用户
     */
    @Override
    public void add() {
        User user = User.builder().name(String.valueOf(RandomUtil.randomChinese()))
                .age(RandomUtil.randomInt()).build();
        userDao.insert(user);
    }

    /**
     * 列表
     *
     * @return
     */
    @Override
    public List<User> list() {
        return userDao.cacheList();
    }
}
