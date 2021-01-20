package com.xingzhi.xingzhiblog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @program: xingzhiblog
 * @description:
 * @author: 行之
 * @create: 2021-01-19 11:34
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("PermissionVO")
public class PermissionVO {
    String permissionName;
}
