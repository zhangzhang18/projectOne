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
 * @Date: 2019-04-24
 */
@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value = "hello", notes = "")
    @RequestMapping(value = {"hello"}, method = RequestMethod.GET)
    public String index() {
        logger.info("go hello");
        return "Hello World";
    }

}
