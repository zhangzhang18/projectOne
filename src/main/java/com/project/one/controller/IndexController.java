package com.project.one.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: zhangchunmeng
 * @Date: 2019-04-26
 */
@RestController
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @ApiOperation(value = "index", notes = "首页")
    @RequestMapping(value = {"index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
