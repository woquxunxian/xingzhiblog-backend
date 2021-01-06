package com.xingzhi.xingzhiblog.exception.handler;

import com.xingzhi.xingzhiblog.common.result.ResponseObject;
import com.xingzhi.xingzhiblog.common.result.ResponseUtil;
import com.xingzhi.xingzhiblog.domain.entity.UserLogin;
import com.xingzhi.xingzhiblog.exception.SystemException;
import com.xingzhi.xingzhiblog.exception.UserLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public ResponseObject systemExceptionHander(HttpServletRequest request, SystemException e) throws Exception {
        ResponseUtil res = new ResponseUtil();
        System.out.println("捕获UserLoginException异常");
        e.printStackTrace();
        log.error("Requst URL : {}，SystemException : {}", request.getRequestURL(), e);
        return res.failed(e.getMessage());
    }

}
