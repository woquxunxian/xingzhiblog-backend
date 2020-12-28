package com.xingzhi.xingzhiblog.domain.vo;

import com.xingzhi.xingzhiblog.domain.entity.ArticleComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class ArticleDetailVO {
    String articleContent;
    List<ArticleComment> articleCommentList;
}
