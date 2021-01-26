package com.xingzhi.xingzhiblog.service.impl.article;

import com.xingzhi.xingzhiblog.dao.article.ArticleCommentMapper;
import com.xingzhi.xingzhiblog.dao.wx.WxAccountMapper;
import com.xingzhi.xingzhiblog.domain.vo.ArticleCommentVO;
import com.xingzhi.xingzhiblog.domain.vo.WxAccountVO;
import com.xingzhi.xingzhiblog.service.ArticleCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 文章评论
 * @author: 行之
 * @create: 2021-01-02 19:25
 **/
@Slf4j
@Service
@Transactional
public class ArticleCommentServiceImpl implements ArticleCommentService {

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Autowired
    private WxAccountMapper wxAccountMapper;

    @Override
    public List<ArticleCommentVO> getArticleCommentByBlogId(int blogId) {
        //获取父评论
        List<ArticleCommentVO> articleCommentVOParentList = articleCommentMapper.getArticleParentCommentByBlogId(blogId, -1);
        if (articleCommentVOParentList.size() == 0) return null;
        // 循环把子评论和父评论对上，并放入返回结果
        for (ArticleCommentVO articleParentCommentVO : articleCommentVOParentList) {
            int userId = articleParentCommentVO.getUserId();
            int commentId = articleParentCommentVO.getId();
            //获取用户微信账户信息
            WxAccountVO wxAccountVO = wxAccountMapper.getWxAccountById(userId);
            articleParentCommentVO.setWxAccountVO(wxAccountVO);
            //获取子评论
            List<ArticleCommentVO> articleCommentVOChildrenList = articleCommentMapper.getArticleChildrenCommentByCommentId(commentId);
            for (ArticleCommentVO articleChildrenCommentVO : articleCommentVOChildrenList) {
                int childrenUserId = articleChildrenCommentVO.getUserId();
                WxAccountVO childWxAccountVO = wxAccountMapper.getWxAccountById(childrenUserId);
                articleChildrenCommentVO.setWxAccountVO(childWxAccountVO);
            }
            articleParentCommentVO.setArticleCommentVOList(articleCommentVOChildrenList);
        }
        return articleCommentVOParentList;
    }

    @Override
    @CacheEvict(value = "articleList", allEntries=true)
    public Integer addArticleParentComment(String content, int userId, int blogId) {
        Integer insertCommentStatus = articleCommentMapper.addArticleParentComment(content, userId, blogId);
        if (insertCommentStatus == 0) return insertCommentStatus;
        Integer updateCommentCountStatus = articleCommentMapper.updateArticleCommentCountByBlogId(blogId);
        return updateCommentCountStatus;
    }

    @Override
    @CacheEvict(value = "articleList", allEntries=true)
    public Integer addArticleSonComment(String content, int userId, int blogId, int parentCommentId) {
        Integer insertCommentStatus = articleCommentMapper.addArticleSonComment(content, userId, blogId, parentCommentId);
        if (insertCommentStatus == 0) return insertCommentStatus;
        Integer updateCommentCountStatus = articleCommentMapper.updateArticleCommentCountByBlogId(blogId);
        return updateCommentCountStatus;
    }

}
