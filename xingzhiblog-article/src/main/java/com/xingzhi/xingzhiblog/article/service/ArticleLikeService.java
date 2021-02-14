package com.xingzhi.xingzhiblog.article.service;

/**
 * @program: xingzhiblog
 * @description: 文章点赞相关
 * @author: 行之
 * @create: 2021-01-13 21:20
 **/
public interface ArticleLikeService {

    int getArticleLikeStatusByBlogIdAndUserId(Integer blogId, Integer userId);

    Integer updateLikeCountByBlogId(Integer blogId, Integer userId);

    Integer updateMinusLikeCountByBlogId(Integer blogId, Integer userId);

}
