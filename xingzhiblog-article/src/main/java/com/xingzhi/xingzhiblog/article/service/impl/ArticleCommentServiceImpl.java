package com.xingzhi.xingzhiblog.article.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fasterxml.jackson.databind.BeanProperty;
import com.xingzhi.xingzhiblog.article.constant.RedisConstant;
import com.xingzhi.xingzhiblog.article.dao.ArticleCommentMapper;
import com.xingzhi.xingzhiblog.article.domain.vo.ArticleCommentVO;
import com.xingzhi.xingzhiblog.article.domain.vo.WxAccountVO;
import com.xingzhi.xingzhiblog.article.feign.WxAccountFeignService;
import com.xingzhi.xingzhiblog.article.service.ArticleCommentService;
import com.xingzhi.xingzhiblog.common.base.service.WxAccountService;
import com.xingzhi.xingzhiblog.common.result.R;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
    private WxAccountFeignService wxAccountFeignService;

//    @Reference
//    private WxAccountService wxAccountService;

    /**
     * @Description: 获取文章评论
     * @Param: blogId 博客id
     * @return: List<ArticleCommentVO> 评论数据
     * @Author: 行之
     */
    @Override
    @Transactional
    @GlobalTransactional
    public List<ArticleCommentVO> getArticleCommentByBlogId(int blogId) {
        // TODO 优化下数据处理过程、
        //  分布式事务 √
        //获取父评论
        List<ArticleCommentVO> articleCommentVOParentList = articleCommentMapper.getArticleParentCommentByBlogId(blogId, -1);
        if (articleCommentVOParentList.size() == 0) return null;
        // 循环把子评论和父评论对上，并放入返回结果
        for (ArticleCommentVO articleParentCommentVO : articleCommentVOParentList) {
            int userId = articleParentCommentVO.getUserId();
            int commentId = articleParentCommentVO.getId();
            //获取用户微信账户信息
            articleParentCommentVO.setWxAccountVO(this.getWxAccountById(userId));
            log.info(articleParentCommentVO.getWxAccountVO().toString());
            //获取子评论
            List<ArticleCommentVO> articleCommentVOChildrenList = articleCommentMapper.getArticleChildrenCommentByCommentId(commentId);
            for (ArticleCommentVO articleChildrenCommentVO : articleCommentVOChildrenList) {
                int childrenUserId = articleChildrenCommentVO.getUserId();
                WxAccountVO childWxAccountVO = this.getWxAccountById(childrenUserId);
                articleChildrenCommentVO.setWxAccountVO(childWxAccountVO);
            }
            articleParentCommentVO.setArticleCommentVOList(articleCommentVOChildrenList);
        }
        return articleCommentVOParentList;
    }

    /**
     * @Description: 添加文章父评论，删除缓存articleList
     * @Param: content 评论内容
     * @Param: userId 用户id
     * @param: blogId 博客id
     * @return: Integer 添加是否成功，成功：1、失败：0
     * @Author: 行之
     */
    @Override
    @CacheEvict(value = RedisConstant.ARTICLE_COMMENT, allEntries=true)
    public Integer addArticleParentComment(String content, int userId, int blogId) {
        // TODO 评论先写入redis，
        //  然后放到消息队列中，
        //  定时读取队列写入数据库，但我感觉评论这个东西并发不高，先不处理
        Integer insertCommentStatus = articleCommentMapper.addArticleParentComment(content, userId, blogId);
        if (insertCommentStatus == 0) return insertCommentStatus;
        Integer updateCommentCountStatus = articleCommentMapper.updateArticleCommentCountByBlogId(blogId);
        return updateCommentCountStatus;
    }

    /**
     * @Description: 添加文章子评论，删除缓存articleList
     * @Param: content 评论内容
     * @Param: userId 用户id
     * @param: blogId 博客id
     * @param: parentCommentId 父评论id
     * @return: Integer 添加是否成功，成功：1、失败：0
     * @Author: 行之
     */
    @Override
    @CacheEvict(value = RedisConstant.ARTICLE_COMMENT, allEntries=true)
    public Integer addArticleSonComment(String content, int userId, int blogId, int parentCommentId) {
        // TODO 评论先写入redis，
        //  然后放到消息队列中，
        //  定时读取队列写入数据库，但我感觉评论这个东西并发不高，先不处理
        Integer insertCommentStatus = articleCommentMapper.addArticleSonComment(content, userId, blogId, parentCommentId);
        if (insertCommentStatus == 0) return insertCommentStatus;
        Integer updateCommentCountStatus = articleCommentMapper.updateArticleCommentCountByBlogId(blogId);
        return updateCommentCountStatus;
    }

    /**
     * @Description: 获取微信账户
     * @Param: userId 用户id
     * @return: WxAccountVO 微信账户响应对象
     * @Author: 行之
     */
    public WxAccountVO getWxAccountById(int userId) {
        R wxAccountResult = wxAccountFeignService.getWxUserDataById(userId);
//        com.xingzhi.xingzhiblog.common.base.domain.vo.WxAccountVO wxAccountResult = wxAccountService.getUserDataById(userId);
        log.info("wxAccountResult:{}",wxAccountResult.toString());
        Map map = (Map) wxAccountResult.get("data");
        WxAccountVO wxAccountVO = new WxAccountVO();
        BeanUtil.copyProperties(wxAccountResult, wxAccountVO);
        wxAccountVO.setId((Integer) map.get("id"));
        wxAccountVO.setNickName((String) map.get("nickName"));
        wxAccountVO.setOpenId((String) map.get("openId"));
        wxAccountVO.setAvatar((String) map.get("avatar"));
        return wxAccountVO;
    }

}
