package com.xingzhi.xingzhiblog.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: xingzhiblog
 * @description: 用户登录信息实体类
 * @author: 行之
 * @create: 2020-12-28 23:20
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {
    Integer id;
    String userName;
    String password;
    Integer isValid;
    Date createTime;
    Date updateTime;
}
