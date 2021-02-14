package com.xingzhi.xingzhiblog.user.dao;

import com.xingzhi.xingzhiblog.user.domain.vo.WxAccountVO;
import org.springframework.stereotype.Repository;

/**
 * @program: xingzhiblog
 * @description: 微信用户登录
 * @author: 行之
 * @create: 2021-01-09 23:19
 **/
@Repository
public interface WxAccountMapper {

    Integer addWxLoginAccount(WxAccountVO wxAccountVO);

    WxAccountVO getWxAccountByOpenId(String openId);

    WxAccountVO getWxAccountById(int id);


}
