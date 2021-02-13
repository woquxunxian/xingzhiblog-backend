package com.xingzhi.xingzhiblog.common.exception;

/**
 * @program: xingzhiblog
 * @description: 登录异常
 * @author: 行之
 * @create: 2021-01-02 12:42
 **/
public class UserLoginException extends RuntimeException{
    public UserLoginException() {
    }

    public UserLoginException(String message) {
        super(message);
    }

    public UserLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
