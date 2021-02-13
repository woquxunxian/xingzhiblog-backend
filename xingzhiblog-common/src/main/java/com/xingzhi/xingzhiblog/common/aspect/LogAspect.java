package com.xingzhi.xingzhiblog.common.aspect;

import com.xingzhi.xingzhiblog.common.result.RequestLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: xingzhiblog
 * @description: 日志切面
 * @author: 行之
 * @create: 2020-12-27 15:35
 */
@Slf4j
@Aspect
@Component
public class LogAspect {
    //定义切面，申明log()是一个切面
    @Pointcut("execution(* com.xingzhi.xingzhiblog.*.*(..))")
    public void logAspect() {}

    //在切面之前执行
    @Before("logAspect()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("进入切面");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取URL、IP
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        //获取请求方法
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        //获取请求参数
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        log.info("Request : {}", requestLog);
    }

    //在切面之后执行
    @After("logAspect()")
    public void doAfter() {

    }

    //返回之后拦截
    @AfterReturning(returning = "result",pointcut = "logAspect()")
    public void doAfterRuturn(Object result) {
        log.info("Result : {}", result);
    }

}
