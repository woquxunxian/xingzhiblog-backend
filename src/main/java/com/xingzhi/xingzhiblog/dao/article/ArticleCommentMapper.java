package com.xingzhi.xingzhiblog.dao.article;

import com.xingzhi.xingzhiblog.domain.entity.ArticleComment;
import com.xingzhi.xingzhiblog.domain.vo.ArticleCommentVO;
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

    List<ArticleCommentVO> getArticleParentCommentByBlogId(int blogId, int parentId);

    List<ArticleCommentVO> getArticleChildrenCommentByCommentId(int parentId);

    Integer addArticleParentComment(String content, int userId, int blogId);

    Integer updateArticleCommentCountByBlogId(int blogId);

}
