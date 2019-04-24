package com.project.one.enums;

public enum ResponseCodeEnum {
    SUCCESS(0, "操作成功", "操作成功"),
    ERROR(1, "出了点问题，请重新试试", "系统异常"),
    PARAM_ERROR(2, "参数错误", "请检查传入的参数"),
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