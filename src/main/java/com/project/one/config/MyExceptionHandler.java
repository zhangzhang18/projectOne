package com.project.one.config;

import com.project.one.enums.ResponseCodeEnum;
import com.project.one.utils.ActionResult;
import com.project.one.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @Description:
 * @Author: zhangchunmeng
 * @Date: 2019-04-30
 */
@RestControllerAdvice
public class MyExceptionHandler implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ActionResult handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        if (bindingResult != null && bindingResult.hasErrors()) {
            logger.error(bindingResult.getFieldError().getField() + " " + bindingResult.getFieldError().getDefaultMessage());
            return ResultUtil.getErrorResult(ResponseCodeEnum.ERROR);
        }
        return ResultUtil.getErrorResult(ResponseCodeEnum.ERROR);
    }

    /**
     * 处理所有异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ActionResult handleException(Exception e) {
        logger.error("异常捕获", e);
        return ResultUtil.getErrorResult(ResponseCodeEnum.ERROR);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("异常捕获类初始化成功");
    }
}
