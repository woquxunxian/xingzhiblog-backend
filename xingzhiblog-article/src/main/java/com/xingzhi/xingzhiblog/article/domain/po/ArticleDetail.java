package com.xingzhi.xingzhiblog.article.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
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
@Alias("ArticleDetail")
public class ArticleDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    Integer id;
    String articleContent;
    Integer isValid;
    Date createTime;
    Date updateTime;
}
