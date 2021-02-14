package com.xingzhi.xingzhiblog.article.config.handler;


import com.xingzhi.xingzhiblog.common.exception.SystemException;
import com.xingzhi.xingzhiblog.common.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: xingzhiblog
 * @description: 全局控制器异常处理
 * @author: 行之
 * @create: 2020-12-23 14:37
 **/
@Slf4j
@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public R systemExceptionHander(HttpServletRequest request, SystemException e) throws Exception {
        e.printStackTrace();
        log.error("Requst URL : {}，SystemException : {}", request.getRequestURL(), e);
        return R.error().put("error", e.toString());
    }

}
