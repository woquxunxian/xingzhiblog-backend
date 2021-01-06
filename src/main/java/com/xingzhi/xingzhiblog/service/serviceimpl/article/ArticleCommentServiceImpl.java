package com.xingzhi.xingzhiblog.service.serviceimpl.article;

import com.xingzhi.xingzhiblog.dao.article.ArticleCommentMapper;
import com.xingzhi.xingzhiblog.dao.user.UserLoginMapper;
import com.xingzhi.xingzhiblog.domain.entity.ArticleComment;
import com.xingzhi.xingzhiblog.domain.vo.ArticleCommentVO;
import com.xingzhi.xingzhiblog.domain.vo.UserListVO;
import com.xingzhi.xingzhiblog.exception.SystemException;
import com.xingzhi.xingzhiblog.service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 文章评论
 * @author: 行之
 * @create: 2021-01-02 19:25
 **/
@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Override
    public List<ArticleCommentVO> getArticleCommentByBlogId(int blogId) {
        List<ArticleCommentVO> articleCommentVOParentList = articleCommentMapper.getArticleParentCommentByBlogId(blogId, -1);
        if (articleCommentVOParentList.size() == 0) return null;
        // 循环把子评论和父评论对上，并放入返回结果
        for (ArticleCommentVO articleParentCommentVO : articleCommentVOParentList) {
            int userId = articleParentCommentVO.getUserId();
            int commentId = articleParentCommentVO.getId();
            UserListVO userListVO = userLoginMapper.getUserInfoByUserId(userId);
            articleParentCommentVO.setUserListVo(userListVO);
            List<ArticleCommentVO> articleCommentVOChildrenList = articleCommentMapper.getArticleChildrenCommentByCommentId(commentId);
            for (ArticleCommentVO articleChildrenCommentVO : articleCommentVOChildrenList) {
                int childrenUserId = articleChildrenCommentVO.getUserId();
                UserListVO userChildrenListVO = userLoginMapper.getUserInfoByUserId(childrenUserId);
                articleChildrenCommentVO.setUserListVo(userChildrenListVO);
            }
            articleParentCommentVO.setArticleCommentVOList(articleCommentVOChildrenList);
        }
        return articleCommentVOParentList;
    }
}
