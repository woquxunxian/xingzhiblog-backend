package com.xingzhi.xingzhiblog.dao.user;

import com.xingzhi.xingzhiblog.domain.entity.UserInfo;
import com.xingzhi.xingzhiblog.domain.vo.UserListVO;
import com.xingzhi.xingzhiblog.domain.vo.UserLoginVO;
import org.springframework.stereotype.Repository;

/**
 * @program: xingzhiblog
 * @description: 用户数据操作
 * @author: 行之
 * @create: 2021-01-01 20:08
 **/
@Repository
public interface UserLoginMapper {

    /**
    * @Description: 用户登录
    * @Param:  userLoginVO 用户登录表单
    * @return: UserListVO 用户信息-用户名和密码
    * @Author: 行之
    * @Date: 2021/1/1
    */
    UserListVO getUserInfoByUserId(int userId);

    Integer userLoginForm(UserLoginVO userLoginVO);

}
