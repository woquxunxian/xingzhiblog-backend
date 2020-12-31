package com.xingzhi.xingzhiblog.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @program: xingzhiblog
 * @description: 文章实体类
 * @author: 行之
 * @create: 2020-12-28 21:34
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("Article")
public class ArticleBlog {

    private static final long serialVersionUID = 1L;

    Integer id;
    String title;
    String image;
    String brief;
    Integer likeCount;
    Integer viewCount;
    Integer commentCount;
    Integer isValid;
    Date createTime;
    Date updateTime;
    Integer userId;
}
