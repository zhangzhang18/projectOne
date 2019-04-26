package com.project.one.service;

import com.project.one.pojo.User;

import java.util.List;

/**
 * @Description:
 * @Author: zhangchunmeng
 * @Date: 2019-04-24
 */
public interface UserService {
    List<User> getAllUser();

    User getUserById(Long id);

    User getUserByName(String name);

    Integer insert(User user);
}
