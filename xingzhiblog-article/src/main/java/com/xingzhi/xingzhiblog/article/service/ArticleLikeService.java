package com.xingzhi.xingzhiblog.article.service;

/**
 * @program: xingzhiblog
 * @description: 文章点赞相关
 * @author: 行之
 * @create: 2021-01-13 21:20
 **/
public interface ArticleLikeService {

    /**
     * @Description: 获取用户相关文章点赞状态
     * @Param: blogId 博客id
     * @param: userId 用户id
     * @return: int 已点赞：1；未点赞：0
     * @Author: 行之
     */
    int getArticleLikeStatusByBlogIdAndUserId(Integer blogId, Integer userId);

    /**
     * @Description: 文章点赞数加一
     * @Param: blogId 博客id
     * @param: userId 用户id
     * @return: Integer 更新成功：1；失败：0
     * @Author: 行之
     */
    Integer updateLikeCountByBlogId(Integer blogId, Integer userId);

    /**
     * @Description: 文章点赞数减一
     * @Param: blogId 博客id
     * @param: userId 用户id
     * @return: Integer 更新成功：1；失败：0
     * @Author: 行之
     */
    Integer updateMinusLikeCountByBlogId(Integer blogId, Integer userId);

}
