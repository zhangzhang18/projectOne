package com.project.one.service.impl;

import com.alibaba.fastjson.JSON;
import com.project.one.mapper.UserMapper;
import com.project.one.pojo.User;
import com.project.one.pojo.UserExample;
import com.project.one.service.RoleService;
import com.project.one.service.UserService;
import com.project.one.utils.UniqueIdUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: zhangchunmeng
 * @Date: 2019-04-24
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${md5.salt}")
    String salt;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleService roleService;

    @Override
    public List<User> getAllUser() {
        logger.info("getAllUser");
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    public User getUserById(Long id) {
        logger.info("getUserById id:{}", id);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(id);
        List<User> users = userMapper.selectByExample(userExample);
        if (users != null) {
            users.get(0).setRoles(roleService.getRoleByUid(users.get(0).getId()));
            return users.get(0);
        }
        return null;
    }

    @Override
    public User getUserByName(String name) {
        logger.info("getUserByName name:{}", name);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(name);
        List<User> users = userMapper.selectByExample(userExample);
        if (users != null) {
            users.get(0).setRoles(roleService.getRoleByUid(users.get(0).getId()));
            return users.get(0);
        }
        return null;
    }

    @Override
    public Integer insert(User user) {
        logger.info("insert user:{}", JSON.toJSONString(user));
        user.setId(UniqueIdUtil.getUniqueID());
        Date now = new Date();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        user.setPassword(new Md5Hash(user.getPassword(), salt, 2).toString());
        return userMapper.insertSelective(user);
    }

}
