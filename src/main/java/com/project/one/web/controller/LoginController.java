package com.project.one.web.controller;

import com.project.one.enums.ResponseCodeEnum;
import com.project.one.service.UserService;
import com.project.one.utils.ActionResult;
import com.project.one.utils.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Description:
 * @Author: zhangchunmeng
 * @Date: 2019-04-26
 */
@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ActionResult login(String username, String passport, Boolean rememberme) {
        if (username == null || passport == null||rememberme==null) {
            return ResultUtil.getErrorResult(ResponseCodeEnum.PARAM_ERROR);
        }
        //添加用户认证信息
        ActionResult result;
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, passport, rememberme);
        try {
            subject.login(usernamePasswordToken);
            result = ResultUtil.getSuccessResult();
        } catch (IncorrectCredentialsException e) {
            result = ResultUtil.getErrorResult(ResponseCodeEnum.PASSWORD_ERROR);
        } catch (AuthenticationException e) {
            result = ResultUtil.getErrorResult(ResponseCodeEnum.ERROR);
        } catch (Exception e) {
            result = ResultUtil.getErrorResult(ResponseCodeEnum.USER_NAME_NOT_EXIST);
        }
        return result;
    }

    @RequestMapping(value = "/logOut", method = RequestMethod.POST)
    public ActionResult logOut() {
        ActionResult result;
        Subject subject = SecurityUtils.getSubject();
        try {
            if (subject.isAuthenticated()) {
                subject.logout();
            }
            result = ResultUtil.getSuccessResult();
        } catch (Exception e) {
            result = ResultUtil.getErrorResult(ResponseCodeEnum.ERROR);
            logger.error("logOut err e:{}", e);
        }
        return result;
    }
}
