package com.xingzhi.xingzhiblog.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: xingzhiblog
 * @description: 文章内容实体类
 * @author: 行之
 * @create: 2020-12-28 23:24
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetail {
    Integer id;
    String articleContent;
    Integer isValid;
    Date createTime;
    Date updateTime;
}
