package com.xingzhi.xingzhiblog.security;

import cn.hutool.core.util.StrUtil;
import com.xingzhi.xingzhiblog.domain.entity.Permission;
import com.xingzhi.xingzhiblog.domain.vo.RoleVO;
import com.xingzhi.xingzhiblog.domain.vo.UserInfoVO;
import com.xingzhi.xingzhiblog.domain.vo.UserLoginVO;
import com.xingzhi.xingzhiblog.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: xingzhiblog
 * @description: 自定义安全数据Realm
 * @author: 行之
 * @create: 2021-01-19 10:11
 **/
@Slf4j
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserLoginService userLoginService;

    /**
     * 重写，绕过身份令牌异常导致的shiro报错
     * @param authenticationToken
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken authenticationToken){
        return authenticationToken instanceof AuthTokenVO;
    }

    /**
     * 执行授权逻辑
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
        log.info(principals.getPrimaryPrincipal().toString());
        String userName = JwtAuthenticator.getUsername((String) principals.getPrimaryPrincipal());
        UserInfoVO userInfoVo = userLoginService.getUserInfoVOByUserName(userName);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        for (RoleVO roleVo : userInfoVo.getRoleVOList()){
            authorizationInfo.addRole(roleVo.getRoleName());
            for(Permission permission : roleVo.getPermissionList()){
                authorizationInfo.addStringPermission(permission.getPermissionUrl());
            }
        }
        return authorizationInfo;
    }

    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{
        String token = (String)authenticationToken.getCredentials();
        String userName = JwtAuthenticator.getUsername(token);
        if (StrUtil.isBlank(userName)){
            throw new AuthenticationException(AuthConstant.TOKEN_BLANK);
        }
        UserLoginVO userLoginVo = userLoginService.getUserLoginVOByUserName(userName);
        if (userLoginVo == null) {
            throw new AuthenticationException(AuthConstant.TOKEN_INVALID);
        } else if (!(JwtAuthenticator.verifyToken(token, userName, userLoginVo.getPassword()))) {
            throw new AuthenticationException(AuthConstant.TOKEN_EXPIRE);
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                token, token, "auth_realm");
        return authenticationInfo;
    }
}

