package com.xingzhi.xingzhiblog.article.config.handler;

import com.xingzhi.xingzhiblog.common.exception.SystemException;
import com.xingzhi.xingzhiblog.common.exception.UserLoginException;
import com.xingzhi.xingzhiblog.common.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;

/**
 * @program: xingzhiblog
 * @description: 线程异常处理
 * @author: 行之
 * @create: 2021-03-02 11:46
 **/
@Slf4j
@RestControllerAdvice
public class ThreadExceptionHandler {

    @ExceptionHandler(ExecutionException.class)
    public R systemExceptionHander(HttpServletRequest request, ExecutionException e) throws Exception {
        System.out.println("捕获ExecutionException异常");
        e.printStackTrace();
        log.error("Requst URL : {}，SystemException : {}", request.getRequestURL(), e);
        return R.error(e.getMessage());
    }

    @ExceptionHandler(InterruptedException.class)
    public R systemExceptionHander(HttpServletRequest request, InterruptedException e) throws Exception {
        System.out.println("捕获InterruptedException异常");
        e.printStackTrace();
        log.error("Requst URL : {}，SystemException : {}", request.getRequestURL(), e);
        return R.error(e.getMessage());
    }
}
