package com.xingzhi.xingzhiblog.domain.vo;

import com.xingzhi.xingzhiblog.domain.entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description:
 * @author: 行之
 * @create: 2021-01-19 10:52
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("RoleVO")
public class RoleVO {
    private String roleName;
    private List<Permission> permissionList;
}
