package com.project.one.shiro.realm;

import com.project.one.pojo.Role;
import com.project.one.pojo.User;
import com.project.one.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Author: zhangchunmeng
 * @Date: 2019-04-27
 */
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    /**
     * 角色权限和对应权限添加
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        User user = userService.getUserByName(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     *
     * @param authenticationToken
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        String passport = new String((char[]) authenticationToken.getCredentials());
        if (name == null) {
            throw new AccountException("参数错误");
        }
        User user = userService.getUserByName(name);
        if (user == null) {
            throw new AuthenticationException ("用户名不存在");
        } else {
            SecurityUtils.getSubject().getSession().setAttribute("user", user);
            return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes("mameng"), getName());

        }
    }
}
