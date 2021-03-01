package com.xingzhi.xingzhiblog.common.base.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @program: xingzhiblog
 * @description: 用户登录信息视图类
 * @author: 行之
 * @create: 2020-12-28 23:31
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("UserListVO")
public class UserListVO implements Serializable {
    String userName;
    String avatar;
    String token;
}
