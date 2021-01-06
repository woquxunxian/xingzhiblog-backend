package com.xingzhi.xingzhiblog.service;

import com.xingzhi.xingzhiblog.domain.entity.ArticleComment;
import com.xingzhi.xingzhiblog.domain.vo.ArticleCommentVO;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 文章评论
 * @author: 行之
 * @create: 2021-01-02 19:24
 **/
public interface ArticleCommentService {

    List<ArticleCommentVO> getArticleCommentByBlogId(int blogId);

}
