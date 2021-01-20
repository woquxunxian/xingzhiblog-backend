package com.xingzhi.xingzhiblog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 用户信息视图
 * @author: 行之
 * @create: 2021-01-01 20:11
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("UserInfoVO")
public class UserInfoVO {
    private Integer userId;
    private String userName;
    private String email;
    private String phone;
    private String avatar;
    private List<RoleVO> roleVOList;
}
