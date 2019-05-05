package com.project.one.shiro.realm;

import com.project.one.pojo.Role;
import com.project.one.pojo.User;
import com.project.one.service.RoleService;
import com.project.one.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @Description:
 * @Author: zhangchunmeng
 * @Date: 2019-04-27
 */
public class MyShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Value("${md5.salt}")
    String salt;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    /**
     * 角色权限和对应权限添加
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        User user = (User) principalCollection.getPrimaryPrincipal();
        List<Role> roleList = null;
        if (user != null && user.getId() != null) {
            roleList = roleService.getRoleByUid(user.getId());
        }
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (roleList != null && !roleList.isEmpty()) {
            for (Role role : roleList) {
                //添加角色
                simpleAuthorizationInfo.addRole(role.getRoleName());
                simpleAuthorizationInfo.addStringPermission(role.getRoleName());
            }
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
        if (name == null) {
            logger.info("参数错误");
            throw new AccountException("参数错误");
        }
        User user = userService.getUserByName(name);
        if (user == null) {
            throw new AuthenticationException("用户名不存在");
        } else {
            SecurityUtils.getSubject().getSession().setAttribute("user", user);
            return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(salt), getName());
        }
    }
}
