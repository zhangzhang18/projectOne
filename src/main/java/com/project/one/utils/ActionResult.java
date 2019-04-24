package com.project.one.utils;

import java.io.Serializable;

public class ActionResult<T> implements Serializable {
    private static final long serialVersionUID = -1491499610244557029L;
    private static final int CODE_SUCCESS = 0;
    private static final int CODE_FAILURED = -1;
    /**
     * 处理状态：0: 成功
     */
    private int code;
    private String codeMsg;
    /**
     * 返回数据
     */
    private T data;

    public ActionResult() {
    }

    public ActionResult(int code, String codeMsg, T data) {
        this.code = code;
        this.codeMsg = codeMsg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCodeMsg() {
        return codeMsg;
    }

    public void setCodeMsg(String codeMsg) {
        this.codeMsg = codeMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ActionResult [code=" + code + ", codeMsg=" + codeMsg + ", data="
                + data + "]";
    }


}
