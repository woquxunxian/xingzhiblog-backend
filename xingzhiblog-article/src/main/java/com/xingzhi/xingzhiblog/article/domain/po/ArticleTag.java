package com.xingzhi.xingzhiblog.article.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

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
@Alias("Tag")
public class ArticleTag {
    Integer id;
    String tagName;
    String description;
    String color;
    String validStatus;
    Date createTime;
    Date updateTime;
}
