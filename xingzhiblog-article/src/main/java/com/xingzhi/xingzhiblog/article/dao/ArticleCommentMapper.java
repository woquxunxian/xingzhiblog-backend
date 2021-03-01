package com.xingzhi.xingzhiblog.article.dao;

import com.xingzhi.xingzhiblog.article.domain.vo.ArticleCommentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 文章评论
 * @author: 行之
 * @create: 2021-01-02 19:03
 **/
@Repository
public interface ArticleCommentMapper {

    /**
     * @Description: 获取文章父评论
     * @Param: blogId 博客id
     * @Param: parentId 父评论id
     * @return: List<ArticleCommentVO> 评论数据
     * @Author: 行之
     */
    List<ArticleCommentVO> getArticleParentCommentByBlogId(@Param("blogId") int blogId, @Param("parentId") int parentId);

    /**
     * @Description: 获取文章子评论
     * @Param: parentId 父评论id
     * @return: List<ArticleCommentVO> 评论数据
     * @Author: 行之
     */
    List<ArticleCommentVO> getArticleChildrenCommentByCommentId(@Param("parentId") int parentId);

    /**
     * @Description: 添加文章父评论
     * @Param: content 评论内容
     * @Param: userId 用户id
     * @param: blogId 博客id
     * @return: Integer 添加是否成功，成功：1、失败：0
     * @Author: 行之
     */
    Integer addArticleParentComment(@Param("content") String content, @Param("userId") int userId,
                                    @Param("blogId") int blogId);
    /**
     * @Description: 添加文章子评论
     * @Param: content 评论内容
     * @Param: userId 用户id
     * @param: blogId 博客id
     * @param: parentCommentId 父评论id
     * @return: Integer 添加是否成功，成功：1、失败：0
     * @Author: 行之
     */
    Integer addArticleSonComment(@Param("content") String content, @Param("userId") int userId,
                                 @Param("blogId") int blogId, @Param("parentCommentId") int parentCommentId);

    /**
     * @Description: 更新文章评论数
     * @param: blogId 文章id
     * @return: Integer 更新是否成功，成功：1、失败：0
     * @Author: 行之
     */
    Integer updateArticleCommentCountByBlogId(@Param("blogId") int blogId);

}
