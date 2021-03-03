package com.xingzhi.xingzhiblog.article.service.impl;

import cn.hutool.core.util.StrUtil;
import com.xingzhi.xingzhiblog.article.constant.RedisConstant;
import com.xingzhi.xingzhiblog.article.dao.ArticleLikeMapper;
import com.xingzhi.xingzhiblog.article.service.ArticleLikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Struct;

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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
        //TODO 操作先写入redis，然后直接返回结果；然后再定时放入队列，由消费者来监听topic并写入数据库 --完成
        String key = blogId + "_" + userId;
        stringRedisTemplate.opsForHash().put(RedisConstant.LIKE_STATUS_KEY, key, "1");
//        Integer isExist = articleLikeMapper.getUserArticleLikeRecord(blogId, userId);
//        if (isExist != null) {
//            int plusUpdateStatus = articleLikeMapper.updateArticleLikeStatus(blogId, userId, 1);
//            if (plusUpdateStatus != 1) return plusUpdateStatus;
//            Integer updateStatus  = articleLikeMapper.updateLikeCountByBlogId(blogId);
//            return updateStatus;
//        }
//        int insertStatus = articleLikeMapper.addArticleLikeRecord(blogId, userId);
//        if (insertStatus != 1) return insertStatus;
//        log.info("articleLikeMapper.updateLikeCountByBlogId(blogId): blogId-{}",blogId);
//        Integer updateStatus  = articleLikeMapper.updateLikeCountByBlogId(blogId);
        return 1;
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
        //TODO 操作先写入redis，然后直接返回结果；然后再定时放入队列，由消费者来监听topic并写入数据库 --完成
        String key = blogId + "_" + userId;
        stringRedisTemplate.opsForHash().put(RedisConstant.LIKE_STATUS_KEY, key, "0");
        //对点赞表进行状态更新
//        int minUpdateStatus = articleLikeMapper.updateArticleLikeStatus(blogId, userId, 0);
//        if (minUpdateStatus != 1) return minUpdateStatus;
        //对文章表的点赞数量进行更新
//        Integer updateStatus  = articleLikeMapper.updateMinusLikeCountByBlogId(blogId);
        return 1;
    }

}
