package com.xingzhi.xingzhiblog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;


/**
 * @program: xingzhiblog
 * @description: 用户登录信息传输类
 * @author: 行之
 * @create: 2020-12-28 23:32
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("UserLoginDTO")
public class UserLoginDTO {
    String userName;
}
