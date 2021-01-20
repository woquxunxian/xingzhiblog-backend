package com.xingzhi.xingzhiblog.common.security;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.xingzhi.xingzhiblog.common.result.ResponseObject;
import com.xingzhi.xingzhiblog.common.result.ResponseUtil;
import com.xingzhi.xingzhiblog.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: xingzhiblog
 * @description: 实现自定义的认证拦截器,接收传过来的token,实现前后端分离的权限认证
 * @author: 行之
 * @create: 2021-01-19 10:14
 **/
@Slf4j
public class AuthFilter extends AuthenticatingFilter {

    private ResponseObject responseObject = new ResponseUtil<>()
                    .custom(ResultCode.USER_ACCESS_FORBIDDEN.getErrorCode(), null, AuthConstant.AUTHENTICATE_FAIL);

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        return null;
    }

    /**
     * 在这里拦截所有请求
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        String token = JwtAuthenticator.getRequestToken((HttpServletRequest)request);
        String token =  ((HttpServletRequest) request).getHeader("mini-token");
        if (!StrUtil.isBlank(token)){
            try {
                this.executeLogin(request, response);
            } catch (Exception e) {
                log.info(e.getMessage());
                responseObject = new ResponseUtil<>().failed(e.getMessage());
                return false;
            }
        } else {
            // cookie中未检查到token或token为空
            HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
            String httpMethod = httpServletRequest.getMethod();
            String requestURI = httpServletRequest.getRequestURI();
            responseObject = new ResponseUtil<>().failed(AuthConstant.TOKEN_BLANK);
            log.info("请求 {} 的Token为空 请求类型 {}", requestURI, httpMethod);
            return false;
        }
        return true;
    }

    /**
     * 请求失败拦截，请求终止，不进行转发直接返回客户端拦截结果
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception{
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        String result = JSONObject.toJSONString(responseObject);
        httpServletResponse.getWriter().print(result);
        return false;
    }

    /**
     * 用户存在，执行登录认证
     * @param request
     * @param response
     * @return boolean
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
//        String token = JwtAuthenticator.getRequestToken((HttpServletRequest)request);
        String token = ((HttpServletRequest) request).getHeader("mini-token");
        AuthTokenVO jwtToken = new AuthTokenVO(token);
        getSubject(request, response).login(jwtToken);
        return true;
    }
}
