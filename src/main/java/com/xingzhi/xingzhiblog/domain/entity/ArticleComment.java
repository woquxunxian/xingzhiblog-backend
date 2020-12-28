package com.xingzhi.xingzhiblog.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: xingzhiblog
 * @description: 文章评论实体类
 * @author: 行之
 * @create: 2020-12-28 23:27
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleComment {
    Integer id;
    String commentContent;
    Integer isValid;
    Date createTime;
    Date updateTime;
    Integer userId;
    Integer parentId;
    Integer articleId;
}
