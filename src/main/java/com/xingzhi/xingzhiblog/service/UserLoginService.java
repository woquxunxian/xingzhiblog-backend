package com.xingzhi.xingzhiblog.service;

import com.xingzhi.xingzhiblog.domain.vo.UserListVO;
import com.xingzhi.xingzhiblog.domain.vo.UserLoginVO;

/**
 * @program: xingzhiblog
 * @description: 用户操作
 * @author: 行之
 * @create: 2021-01-01 20:29
 **/
public interface UserLoginService {
    UserListVO userLogin(UserLoginVO userLoginVO);
}
