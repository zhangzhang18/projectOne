package com.project.one.web.controller;

import com.project.one.enums.ResponseCodeEnum;
import com.project.one.utils.ActionResult;
import com.project.one.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author: zhangchunmeng
 * @Date: 2019-04-26
 */
@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @ApiOperation(value = "/index", notes = "首页")
    @RequestMapping(value = {"index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @ApiOperation(value = "/loginPage", notes = "登录")
    @RequestMapping(value = {"loginPage"}, method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @ApiOperation(value = "/hello", notes = "hello")
    @RequestMapping(value = {"hello"}, method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @ApiOperation(value = "/error", notes = "error")
    @RequestMapping(value = {"error"}, method = RequestMethod.GET)
    public String error() {
        return "error/404";
    }

    @ApiOperation(value = "/unauth", notes = "unauth")
    @RequestMapping(value = {"unauth"}, method = RequestMethod.GET)
    @ResponseBody
    public ActionResult unauth() {
        return ResultUtil.getErrorResult(ResponseCodeEnum.NO_PERMISSION);
    }
}
