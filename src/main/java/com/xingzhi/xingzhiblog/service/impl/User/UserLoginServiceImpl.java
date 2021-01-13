package com.xingzhi.xingzhiblog.service.impl.User;

import com.xingzhi.xingzhiblog.dao.user.UserLoginMapper;
import com.xingzhi.xingzhiblog.domain.vo.UserListVO;
import com.xingzhi.xingzhiblog.domain.vo.UserLoginVO;
import com.xingzhi.xingzhiblog.exception.SystemException;
import com.xingzhi.xingzhiblog.exception.UserLoginException;
import com.xingzhi.xingzhiblog.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: xingzhiblog
 * @description: 用户相关
 * @author: 行之
 * @create: 2021-01-01 20:30
 **/
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    UserLoginMapper userLoginMapper;

    @Override
    public UserListVO userLogin(UserLoginVO userLoginVO) {
        Integer userId = userLoginMapper.userLoginForm(userLoginVO);
        if (userId != null) {
            UserListVO userListVO = userLoginMapper.getUserInfoByUserId(userId);
            if (userListVO.getUserName() == null) throw new SystemException("系统出错");
            return userListVO;
        }
        throw new UserLoginException("用户名或密码错误");
    }
}
