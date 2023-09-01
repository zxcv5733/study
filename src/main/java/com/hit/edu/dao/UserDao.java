package com.hit.edu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hit.edu.po.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Li dong
 * @date: 2023/9/1 15:02
 * @description:
 */
@Repository
public interface UserDao extends BaseMapper<User> {

    /**
     * 使用二级缓存查询
     * @return
     */
    List<User> cacheList();
}
