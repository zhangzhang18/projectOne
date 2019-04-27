package com.project.one.controller;

import com.project.one.enums.ResponseCodeEnum;
import com.project.one.pojo.User;
import com.project.one.service.UserService;
import com.project.one.utils.ActionResult;
import com.project.one.utils.ResultUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    public ActionResult login(String username, String passport, Boolean rememberMe) {
        if (username == null || passport == null) {
            return ResultUtil.getErrorResult(ResponseCodeEnum.PARAM_ERROR);
        }
        //添加用户认证信息
        ActionResult result = new ActionResult();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, passport, rememberMe);
        //进行验证，这里可以捕获异常，然后返回对应信息
        try {
            subject.login(usernamePasswordToken);
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
        ActionResult result = new ActionResult();
        try {
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            result = ResultUtil.getErrorResult(ResponseCodeEnum.ERROR);
            logger.error("logOut err e:{}", e);
        }
        return result;
    }
}
