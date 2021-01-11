package com.xingzhi.xingzhiblog.dao.wx;

import com.xingzhi.xingzhiblog.domain.vo.WxAccountVO;
import org.springframework.stereotype.Repository;

/**
 * @program: xingzhiblog
 * @description: 微信用户登录
 * @author: 行之
 * @create: 2021-01-09 23:19
 **/
@Repository
public interface WxAccountMapper {

    Integer addWxLoginAcount(String avatarUrl, String nickName, String openId, String unionId);

    WxAccountVO getWxAccountByOpenId(String openId);

    WxAccountVO getWxAccountById(int id);


}
