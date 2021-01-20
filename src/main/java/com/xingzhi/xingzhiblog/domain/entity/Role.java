package com.xingzhi.xingzhiblog.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @program: xingzhiblog
 * @description:
 * @author: 行之
 * @create: 2021-01-19 10:51
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("Role")
public class Role {
    private Integer id;
    private String roleName;
}
