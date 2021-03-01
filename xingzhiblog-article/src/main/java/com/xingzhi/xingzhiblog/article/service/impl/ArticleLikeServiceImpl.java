package com.xingzhi.xingzhiblog.article.service.impl;

import com.xingzhi.xingzhiblog.article.constant.RedisConstant;
import com.xingzhi.xingzhiblog.article.dao.ArticleLikeMapper;
import com.xingzhi.xingzhiblog.article.service.ArticleLikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: xingzhiblog
 * @description: 文章点赞相关
 * @author: 行之
 * @create: 2021-01-13 21:16
 **/
@Slf4j
@Service
@Transactional
public class ArticleLikeServiceImpl implements ArticleLikeService {

    @Autowired
    private ArticleLikeMapper articleLikeMapper;

    /**
     * @Description: 获取用户相关文章点赞状态
     * @Param: blogId 博客id
     * @param: userId 用户id
     * @return: int 已点赞：1；未点赞：0
     * @Author: 行之
     */
    @Override
    public int getArticleLikeStatusByBlogIdAndUserId(Integer blogId, Integer userId) {
        Integer likeStatus = articleLikeMapper.getArticleLikeStatusByBlogIdAndUserId(blogId, userId);
        if (likeStatus == null) {
            return 0;
        } else {
            return likeStatus;
        }
    }

    /**
     * @Description: 文章点赞数加一
     * @Param: blogId 博客id
     * @param: userId 用户id
     * @return: Integer 更新成功：1；失败：0
     * @Author: 行之
     */
    @Override
    @Transactional
    @CacheEvict(value = RedisConstant.ARTICLE_LIST, allEntries=true)
    public Integer updateLikeCountByBlogId(Integer blogId, Integer userId) {
        //TODO 操作先写入redis，然后直接返回结果；然后再定时放入队列，由消费者来监听topic并写入数据库
        // TODO 分布式事务
        Integer isExist = articleLikeMapper.getUserArticleLikeRecord(blogId, userId);
        if (isExist != null) {
            int plusUpdateStatus = articleLikeMapper.updateArticleLikeStatus(blogId, userId, 1);
            if (plusUpdateStatus != 1) return plusUpdateStatus;
            Integer updateStatus  = articleLikeMapper.updateLikeCountByBlogId(blogId);
            return updateStatus;
        }
        int insertStatus = articleLikeMapper.addArticleLikeRecord(blogId, userId);
        if (insertStatus != 1) return insertStatus;
        log.info("articleLikeMapper.updateLikeCountByBlogId(blogId): blogId-{}",blogId);
        Integer updateStatus  = articleLikeMapper.updateLikeCountByBlogId(blogId);
        return updateStatus;
    }

    /**
     * @Description: 文章点赞数减一
     * @Param: blogId 博客id
     * @param: userId 用户id
     * @return: Integer 更新成功：1；失败：0
     * @Author: 行之
     */
    @Override
    @Transactional
    @CacheEvict(value = RedisConstant.ARTICLE_LIST, allEntries=true)
    public Integer updateMinusLikeCountByBlogId(Integer blogId, Integer userId) {
        //TODO 操作先写入redis，然后直接返回结果；然后再定时放入队列，由消费者来监听topic并写入数据库
        // TODO 分布式事务
        //对点赞表进行状态更新
        int minUpdateStatus = articleLikeMapper.updateArticleLikeStatus(blogId, userId, 0);
        if (minUpdateStatus != 1) return minUpdateStatus;
        //对文章表的点赞数量进行更新
        Integer updateStatus  = articleLikeMapper.updateMinusLikeCountByBlogId(blogId);
        return updateStatus;
    }

}
