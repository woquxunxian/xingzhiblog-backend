package com.xingzhi.xingzhiblog.article.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 文章详细页视图类
 * @author: 行之
 * @create: 2020-12-28 23:36
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("ArticleDetailVO")
public class ArticleDetailVO implements Serializable {
    String articleContent;
    List<ArticleCommentVO> articleCommentVOList;
}
