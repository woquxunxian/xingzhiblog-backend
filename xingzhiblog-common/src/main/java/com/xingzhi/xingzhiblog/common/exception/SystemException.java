package com.xingzhi.xingzhiblog.common.exception;

/**
 * @program: xingzhiblog
 * @description: 自定义系统异常
 * @author: 行之
 * @create: 2020-12-23 14:37
 **/
public class SystemException extends RuntimeException{
    public SystemException() {
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
