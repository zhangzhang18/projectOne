package com.project.one.controller;

import com.project.one.pojo.User;
import com.project.one.service.UserService;
import com.project.one.utils.ActionResult;
import com.project.one.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
