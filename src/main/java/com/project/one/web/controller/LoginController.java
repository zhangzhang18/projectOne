package com.project.one.web.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.project.one.enums.ResponseCodeEnum;
import com.project.one.service.UserService;
import com.project.one.utils.ActionResult;
import com.project.one.utils.CodeUtil;
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

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;


/**
 * @Description:
 * @Author: zhangchunmeng
 * @Date: 2019-04-26
 */
@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private DefaultKaptcha captchaProducer;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ActionResult login(String username, String passport, Boolean rememberme,HttpServletRequest request) {
        if (username == null || passport == null || rememberme == null) {
            return ResultUtil.getErrorResult(ResponseCodeEnum.PARAM_ERROR);
        }
        if (!CodeUtil.checkVerifyCode(request)) {
            return ResultUtil.getErrorResult(ResponseCodeEnum.CODE_ERROR);
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
    @RequestMapping(value = "/kaptcha", method = RequestMethod.GET)
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成验证码
        String capText = captchaProducer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        //向客户端写出
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}
