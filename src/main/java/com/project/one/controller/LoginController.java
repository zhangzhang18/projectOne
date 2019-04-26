package com.project.one.controller;

import com.project.one.enums.ResponseCodeEnum;
import com.project.one.pojo.User;
import com.project.one.service.UserService;
import com.project.one.utils.ActionResult;
import com.project.one.utils.ResultUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/login")
    public ActionResult login(@RequestParam String userName, @RequestParam String password, HttpServletRequest request, HttpSession session) {
        logger.info("login userName:{},password:{}", userName, password);
        if (StringUtils.isBlank(userName)) {
            return ResultUtil.getErrorResult(ResponseCodeEnum.USER_NAME_NULL);
        }
        if (StringUtils.isBlank(password)) {
            return ResultUtil.getErrorResult(ResponseCodeEnum.PASSWORD_NULL);
        }
        ActionResult result = new ActionResult();
        User user = userService.getUserByName(userName);
        if (user != null) {
            if (DigestUtils.md5Hex(password).equals(user.getPassword())) {
                logger.info("密码验证通过");
                user.setPassword(null);
                session.setAttribute("user", user);
            } else {
                return ResultUtil.getErrorResult(ResponseCodeEnum.PASSWORD_ERROR);
            }
        }
        return result;
    }

    @GetMapping("/logout")
    public ActionResult logout(HttpSession session) {
        session.removeAttribute("user");
        return ResultUtil.getSuccessResult();
    }
}
