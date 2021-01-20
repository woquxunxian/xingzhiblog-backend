package com.xingzhi.xingzhiblog.common.security;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @program: xingzhiblog
 * @description:
 * @author: 行之
 * @create: 2021-01-19 10:40
 **/
public class AuthTokenVO implements AuthenticationToken {
    private String token;

    public AuthTokenVO(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal(){
        return token;
    }

    @Override
    public Object getCredentials(){
        return token;
    }
}

