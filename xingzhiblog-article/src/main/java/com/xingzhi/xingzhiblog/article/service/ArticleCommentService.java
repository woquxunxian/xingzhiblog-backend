package com.xingzhi.xingzhiblog.article.service;

import com.xingzhi.xingzhiblog.article.domain.vo.ArticleCommentVO;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 文章评论
 * @author: 行之
 * @create: 2021-01-02 19:24
 **/
public interface ArticleCommentService {

    List<ArticleCommentVO> getArticleCommentByBlogId(int blogId);

    Integer addArticleParentComment(String content, int userId, int blogId);

    @CacheEvict(value = "articleList", allEntries=true)
    Integer addArticleSonComment(String content, int userId, int blogId, int parentCommentId);
}
