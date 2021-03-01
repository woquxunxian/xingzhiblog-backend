package com.xingzhi.xingzhiblog.common.base.service;

import com.xingzhi.xingzhiblog.common.base.domain.dto.LoginDTO;
import com.xingzhi.xingzhiblog.common.base.domain.vo.WxAccountVO;

/**
 * @program: xingzhiblog
 * @description: 微信账户服务
 * @author: 行之
 * @create: 2021-02-24 17:00
 **/
public interface WxAccountService {

    /**
    * @Description: 微信登录
    * @Param: LoginDTO 登录数据传输对象
    * @return: WxAccountVO 微信账户响应对象
    * @Author: 行之
    */
    WxAccountVO wxLogin(LoginDTO loginDTO);

    /**
     * @Description: 通过主键id获取微信账户数据
     * @Param: id 记录主键id
     * @return: WxAccountVO 微信账户响应对象
     * @Author: 行之
     */
    WxAccountVO getUserDataById(int id);
}
