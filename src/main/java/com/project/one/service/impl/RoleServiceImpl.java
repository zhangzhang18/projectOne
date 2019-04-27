package com.project.one.service.impl;

import com.project.one.mapper.RoleMapper;
import com.project.one.mapper.UserRoleMapper;
import com.project.one.pojo.Role;
import com.project.one.pojo.RoleExample;
import com.project.one.pojo.UserRole;
import com.project.one.pojo.UserRoleExample;
import com.project.one.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: zhangchunmeng
 * @Date: 2019-04-27
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> getRoleByUid(Long uid) {
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(uid);
        List<UserRole> userRoleList = userRoleMapper.selectByExample(userRoleExample);
        if (userRoleList != null && !userRoleList.isEmpty()) {
            for (UserRole userRole : userRoleList) {
                RoleExample roleExample = new RoleExample();
                roleExample.createCriteria().andIdEqualTo(userRole.getRoleId());
                return roleMapper.selectByExample(roleExample);
            }
        }
        return null;
    }
}
