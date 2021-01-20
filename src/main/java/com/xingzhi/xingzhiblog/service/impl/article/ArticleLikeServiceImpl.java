package com.xingzhi.xingzhiblog.service.impl.article;

import com.xingzhi.xingzhiblog.dao.article.ArticleLikeMapper;
import com.xingzhi.xingzhiblog.service.ArticleLikeService;
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
     * @Description: 通过博客id增加点赞数，赞+1
     * @Param:  * @param null
     * @return:
     * @Author: 行之
     * @Date: 2021/1/6
     */
    @CacheEvict(value = "articleList", allEntries=true)
    @Override
    public Integer updateLikeCountByBlogId(Integer blogId, Integer userId) {
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
     * @Description: 用户取消赞操作,赞-1
     * @Param:  * @param null
     * @return:
     * @Author: 行之
     * @Date: 2021/1/11
     */
    @CacheEvict(value = "articleList", allEntries=true)
    @Override
    public Integer updateMinusLikeCountByBlogId(Integer blogId, Integer userId) {
        //对点赞表进行状态更新
        int minUpdateStatus = articleLikeMapper.updateArticleLikeStatus(blogId, userId, 0);
        if (minUpdateStatus != 1) return minUpdateStatus;
        //对文章表的点赞数量进行更新
        Integer updateStatus  = articleLikeMapper.updateMinusLikeCountByBlogId(blogId);
        return updateStatus;
    }

}
