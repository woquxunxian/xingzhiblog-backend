package com.xingzhi.xingzhiblog.service.impl.User;

import com.xingzhi.xingzhiblog.common.security.AuthConstant;
import com.xingzhi.xingzhiblog.common.security.AuthTokenVO;
import com.xingzhi.xingzhiblog.common.security.JwtAuthenticator;
import com.xingzhi.xingzhiblog.dao.user.UserLoginMapper;
import com.xingzhi.xingzhiblog.domain.entity.Permission;
import com.xingzhi.xingzhiblog.domain.entity.Role;
import com.xingzhi.xingzhiblog.domain.vo.*;
import com.xingzhi.xingzhiblog.exception.SystemException;
import com.xingzhi.xingzhiblog.exception.UserLoginException;
import com.xingzhi.xingzhiblog.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 用户相关
 * @author: 行之
 * @create: 2021-01-01 20:30
 **/
@Slf4j
@Service
@Transactional
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    UserLoginMapper userLoginMapper;

    @Override
    public UserListVO userLogin(UserLoginVO userLoginVO) {
        Date setTime = new Date();
        Date expireTime = new Date();
        expireTime.setTime(setTime.getTime() + AuthConstant.EXPIRE_TIME);
        String token = JwtAuthenticator.sign(userLoginVO, expireTime);
        AuthTokenVO authTokenVO = new AuthTokenVO(token);
        Subject subject = SecurityUtils.getSubject();
        subject.login(authTokenVO);
        Integer userId = userLoginMapper.userLoginForm(userLoginVO);
        if (userId != null) {
            UserListVO userListVO = userLoginMapper.getUserInfoByUserId(userId);
            if (userListVO.getUserName() == null) throw new SystemException("系统出错");
            userListVO.setToken(token);
            return userListVO;
        }
        throw new UserLoginException("用户名或密码错误");
    }

    @Override
    public UserLoginVO getUserLoginVOByUserName(String userName) {
        return userLoginMapper.getUserLoginVOByUserName(userName);
    }

    @Override
    public UserInfoVO getUserInfoVOByUserName(String userName) {
        UserInfoVO userInfoVO = userLoginMapper.getUserInfoVOByUserName(userName);
        if (userInfoVO == null) throw new SystemException("用户不存在");
        List<Role> roleList = userLoginMapper.getUseRoleByUserId(userInfoVO.getUserId()); // 获取用户所拥有的角色
        List<Permission> permissionList;
        List<RoleVO> roleVoList = new ArrayList<>();
        RoleVO roleVO = new RoleVO();
        for (int i = 0; i < roleList.size(); ++i) {
            permissionList = userLoginMapper.getRolePermissionByRoleId(roleList.get(i).getId());
            roleVO.setRoleName(roleList.get(i).getRoleName());
            roleVO.setPermissionList(permissionList);
            roleVoList.add(roleVO);
        }
        userInfoVO.setRoleVOList(roleVoList);
        return userInfoVO;
    }

}
