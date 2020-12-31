package com.xingzhi.xingzhiblog.common.result;

import com.xingzhi.xingzhiblog.common.enums.ResultCode;

import java.io.Serializable;

/**
 * @program: xingzhiblog
 * @description: 统一结果封装
 * @author: 行之
 * @create: 2020-12-20 20:38
 **/
public class ResponseObject<T> implements Serializable {
    private static final long serialVersionUID = 3240173080634963941L;
    private int code;
    private T data;
    private String msg;

    public ResponseObject() {
        this.code = ResultCode.BUSINESS_SUCESS.getErrorCode();
    }

    public ResponseObject(T data) {
        this.code = ResultCode.BUSINESS_SUCESS.getErrorCode();
        this.data = data;
    }

    public ResponseObject(int status, T data, String msg) {
        this.code = status;
        this.data = data;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
