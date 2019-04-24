package com.project.one.service.impl;

import com.project.one.mapper.UserMapper;
import com.project.one.pojo.User;
import com.project.one.pojo.UserExample;
import com.project.one.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: zhangchunmeng
 * @Date: 2019-04-24
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> getAllUser() {
        return userMapper.selectByExample(new UserExample());
    }
}
