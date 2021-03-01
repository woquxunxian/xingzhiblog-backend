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

    /**
    * @Description: 获取文章评论
    * @Param: blogId 博客id
    * @return: List<ArticleCommentVO> 评论数据
    * @Author: 行之
    */
    List<ArticleCommentVO> getArticleCommentByBlogId(int blogId);

    /**
     * @Description: 添加文章父评论
     * @Param: content 评论内容
     * @Param: userId 用户id
     * @param: blogId 博客id
     * @return: Integer 添加是否成功，成功：1、失败：0
     * @Author: 行之
     */
    Integer addArticleParentComment(String content, int userId, int blogId);

    /**
     * @Description: 添加文章子评论
     * @Param: content 评论内容
     * @Param: userId 用户id
     * @param: blogId 博客id
     * @param: parentCommentId 父评论id
     * @return: Integer 添加是否成功，成功：1、失败：0
     * @Author: 行之
     */
    Integer addArticleSonComment(String content, int userId, int blogId, int parentCommentId);
}
