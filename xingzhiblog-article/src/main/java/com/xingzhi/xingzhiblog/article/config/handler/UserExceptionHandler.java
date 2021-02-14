package com.xingzhi.xingzhiblog.article.config.handler;


import com.xingzhi.xingzhiblog.common.exception.SystemException;
import com.xingzhi.xingzhiblog.common.exception.UserLoginException;
import com.xingzhi.xingzhiblog.common.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: xingzhiblog
 * @description: 用户相关异常处理
 * @author: 行之
 * @create: 2021-01-02 12:43
 **/
@Slf4j
@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserLoginException.class)
    public R systemExceptionHander(HttpServletRequest request, SystemException e) throws Exception {
        System.out.println("捕获UserLoginException异常");
        e.printStackTrace();
        log.error("Requst URL : {}，SystemException : {}", request.getRequestURL(), e);
        return R.error(e.getMessage());
    }

}
