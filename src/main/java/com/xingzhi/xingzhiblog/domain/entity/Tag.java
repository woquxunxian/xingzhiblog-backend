package com.xingzhi.xingzhiblog.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: xingzhiblog
 * @description: 标签实体类
 * @author: 行之
 * @create: 2020-12-27 23:27
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    Integer id;
    String name;
    String description;
    String color;
    String validStatus;
    Date createTime;
    Date updateTime;
}
