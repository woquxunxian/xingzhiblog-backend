package com.xingzhi.xingzhiblog.article.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @program: xingzhiblog
 * @description: 文章点赞相关
 * @author: 行之
 * @create: 2021-01-13 21:04
 **/
@Repository
public interface ArticleLikeMapper {

    /**
     * @Description: 通过博客id增加点赞数
     * @Param: blogId
     * @return: int 成功：1；失败：0
     * @Author: 行之
     */
    int updateLikeCountByBlogId(@Param("blogId") Integer blogId);

    /**
     * @Description: 查看用户在相关文章是否存在点赞的记录
     * @Param: blogId 博客id
     * @param: userId 用户id
     * @return: Integer 存在：1；不存在：0
     * @Author: 行之
     */
    Integer getUserArticleLikeRecord(@Param("blogId") Integer blogId, @Param("userId") Integer userId);

    /**
     * @Description: 更新点赞数量-1
     * @Param: blogId 博客id
     * @return: int 成功：1；失败：0
     * @Author: 行之
     */
    int updateMinusLikeCountByBlogId(@Param("blogId") Integer blogId);

    /**
     * @Description: 添加文章的点赞记录，like_status为1
     * @Param: blogId 博客id
     * @param: userId 用户id
     * @return: int 成功：1；失败：0
     * @Author: 行之
     */
    int addArticleLikeRecord(@Param("blogId") Integer blogId, @Param("userId") Integer userId);

    /**
     * @Description: 当用文章点赞表点赞状态进行更新
     * @Param: blogId 文章id
     * @Param: userId 微信用户id
     * @Param: likeStatus 点赞状态：1为点赞，0为取消赞
     * @return: int 成功：1；失败：0
     * @Author: 行之
     */
    int updateArticleLikeStatus(@Param("blogId") Integer blogId, @Param("userId") Integer userId, @Param("likeStatus") Integer likeStatus);

    /**
     * @Description: 获取用户是否点赞，点赞了则返回1，未点赞则返回0
     * @Param: blogId 博客id
     * @param: userId 用户id
     * @return: int 已点赞：1；未点赞：0
     * @Author: 行之
     */
    Integer getArticleLikeStatusByBlogIdAndUserId(@Param("blogId") Integer blogId, @Param("userId") Integer userId);

}
