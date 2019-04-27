package com.project.one.controller;

import com.alibaba.fastjson.JSON;
import com.project.one.enums.ResponseCodeEnum;
import com.project.one.pojo.User;
import com.project.one.service.UserService;
import com.project.one.utils.ActionResult;
import com.project.one.utils.ResultUtil;
import com.project.one.utils.UniqueIdUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description:
 * @Author: zhangchunmeng
 * @Date: 2019-04-24
 */
@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @ApiOperation(value = "getAllUser", notes = "获取所有用户信息")
    @RequestMapping(value = {"getAllUser"}, method = RequestMethod.GET)
    public ActionResult<List<User>> getAllUser() {
        logger.info("getAllUser start");
        return ResultUtil.getSuccessResult(userService.getAllUser());
    }

    @PostMapping("/register")
    public ActionResult register(User user) {
        ActionResult result = new ActionResult();
        logger.info("getAllUser start", JSON.toJSONString(user));
        if (user == null) {
            return ResultUtil.getErrorResult(ResponseCodeEnum.ERROR);
        }
        if (StringUtils.isBlank(user.getName())) {
            return ResultUtil.getErrorResult(ResponseCodeEnum.USER_NAME_NULL);
        }
        if (StringUtils.isBlank(user.getPassword())) {
            return ResultUtil.getErrorResult(ResponseCodeEnum.PASSWORD_NULL);
        }
        try {
            if (userService.getUserByName(user.getName()) == null) {
                if (userService.insert(user) > 0) {
                    result = ResultUtil.getSuccessResult();
                }
            } else {
                result = ResultUtil.getErrorResult(ResponseCodeEnum.USER_NAME_EXIST);
            }
        } catch (Exception e) {
            result = ResultUtil.getErrorResult(ResponseCodeEnum.ERROR);
            logger.error("register err e:{}", e);
        }
        return result;
    }
}
