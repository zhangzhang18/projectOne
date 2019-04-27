package com.project.one.service;

import com.project.one.pojo.Role;

import java.util.List;

/**
 * @Description:
 * @Author: zhangchunmeng
 * @Date: 2019-04-26
 */
public interface RoleService {
    List<Role> getRoleByUid(Long uid);
}
