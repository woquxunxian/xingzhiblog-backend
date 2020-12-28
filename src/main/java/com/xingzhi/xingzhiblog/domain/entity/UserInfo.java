package com.xingzhi.xingzhiblog.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: xingzhiblog
 * @description: 用户个人信息实体类
 * @author: 行之
 * @create: 2020-12-28 23:22
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    Integer id;
    String avatar;
    String email;
    String phone;
    String personalSignature;
    String lastIp;
    Date registerTime;
    Integer isValid;
    Date createTime;
    Date updateTime;
    Integer userId;
}