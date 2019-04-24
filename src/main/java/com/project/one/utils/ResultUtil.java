package com.project.one.utils;


import com.project.one.enums.ResponseCodeEnum;

/**
 * Created by zhanghenan on 18/5/17.
 */
public class ResultUtil {

    private ResultUtil() {
    }

    public static <T> ActionResult<T> getResult(ResponseCodeEnum errCodelEnum, T data) {
        return new ActionResult<T>(errCodelEnum.getCode(), errCodelEnum.getDesc(), data);
    }

    public static <T> ActionResult<T> getSuccessResult() {
        return getResult(ResponseCodeEnum.SUCCESS, null);
    }

    public static <T> ActionResult<T> getSuccessResult(T data) {
        return getResult(ResponseCodeEnum.SUCCESS, data);
    }

    public static <T> ActionResult<T> getErrorResult(ResponseCodeEnum errCodelEnum) {
        return getResult(errCodelEnum, null);
    }


}
