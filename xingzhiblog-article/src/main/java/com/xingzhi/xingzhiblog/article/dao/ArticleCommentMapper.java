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

    List<ArticleCommentVO> getArticleParentCommentByBlogId(@Param("blogId") int blogId, @Param("parentId") int parentId);

    List<ArticleCommentVO> getArticleChildrenCommentByCommentId(@Param("parentId") int parentId);

    Integer addArticleParentComment(@Param("content") String content, @Param("userId") int userId,
                                    @Param("blogId") int blogId);

    Integer updateArticleCommentCountByBlogId(@Param("blogId") int blogId);

    Integer addArticleSonComment(@Param("content") String content, @Param("userId") int userId,
                                 @Param("blogId") int blogId, @Param("parentCommentId") int parentCommentId);

}
