package com.xingzhi.xingzhiblog.user.dao;

import com.xingzhi.xingzhiblog.common.base.domain.vo.WxAccountVO;
import org.springframework.stereotype.Repository;

/**
 * @program: xingzhiblog
 * @description: 微信用户登录
 * @author: 行之
 * @create: 2021-01-09 23:19
 **/
@Repository
public interface WxAccountMapper {

    /**
    * @Description: 新增一个微信账户
    * @Param: WxAccountVO 微信账户响应对象
    * @return: Integer 是否新增成功，1：成功；0：失败。
    * @Author: 行之
    */
    Integer addWxLoginAccount(WxAccountVO wxAccountVO);

    /**
    * @Description: 通过OpenId获取微信账户数据
    * @Param: openId 账户在该微信小程序的openid
    * @return: WxAccountVO 微信账户响应对象
    * @Author: 行之
    */
    WxAccountVO getWxAccountByOpenId(String openId);

    /**
    * @Description: 通过主键id获取微信账户数据
    * @Param: id 记录主键id
    * @return: WxAccountVO 微信账户响应对象
    * @Author: 行之
    */
    WxAccountVO getWxAccountById(int id);


}
