package com.xingzhi.xingzhiblog.exception.handler;

import com.xingzhi.xingzhiblog.exception.SystemException;
import com.xingzhi.xingzhiblog.result.ResponseObject;
import com.xingzhi.xingzhiblog.result.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: xingzhiblog
 * @description: 全局控制器异常处理
 * @author: 行之
 * @create: 2020-12-23 14:37
 **/
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseObject systemExceptionHander(HttpServletRequest request, SystemException e) throws Exception {
        ResponseUtil res = new ResponseUtil();
        log.error("Requst URL : {}，SystemException : {}", request.getRequestURL(), e);
        return res.failed(e.toString());
    }

}
