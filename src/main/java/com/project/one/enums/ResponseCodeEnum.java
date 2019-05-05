package com.project.one.enums;

public enum ResponseCodeEnum {
    SUCCESS(0, "操作成功", "操作成功"),
    ERROR(1, "出了点问题，请重新试试", "系统异常"),
    PARAM_ERROR(2, "参数错误", "请检查传入的参数"),
    USER_NAME_NULL(101, "用户名为空", "用户名为空"),
    USER_NAME_EXIST(102, "用户名已存在", "用户名已存在"),
    USER_NAME_NOT_EXIST(102, "用户名不存在", "用户名不存在"),
    PASSWORD_NULL(103, "密码为空", "密码为空"),
    PASSWORD_ERROR(104, "密码错误", "密码错误"),
    NO_LOGIN(501, "未登录", "未登录"),
    NO_PERMISSION(502, "权限不足", "权限不足"),
    ;

    private int code;
    private String desc;
    private String detail;

    ResponseCodeEnum(int type, String desc, String detail) {
        this.code = type;
        this.desc = desc;
        this.detail = detail;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getDetail() {
        return detail;
    }

    public static ResponseCodeEnum getEnum(int type) {
        for (ResponseCodeEnum rsp : ResponseCodeEnum.values()) {
            if (rsp.getCode() == type) {
                return rsp;
            }
        }
        return null;
    }

}
