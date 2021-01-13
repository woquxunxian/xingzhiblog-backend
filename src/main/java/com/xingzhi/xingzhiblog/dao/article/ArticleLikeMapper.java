package com.xingzhi.xingzhiblog.dao.article;

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
     * @Description: 通过博客id增加点赞数，目前只是模拟增加，后期维护用户系统后才能统计真正的点赞数量
     * @Param:  * @param null
     * @return:
     * @Author: 行之
     * @Date: 2021/1/6
     */
    int updateLikeCountByBlogId(Integer BlogId);

    /**
     * @Description: 查看用户在相关文章是否存在点赞的记录
     * @Param:  * @param null
     * @return:
     * @Author: 行之
     * @Date: 2021/1/13
     */
    Integer getUserArticleLikeRecord(Integer blogId, Integer userId);

    /**
     * @Description:
     * @Param:  * @param null
     * @return:
     * @Author: 行之
     * @Date: 2021/1/9
     */
    int updateMinusLikeCountByBlogId(Integer blogId);

    /**
     * @Description: 添加文章的点赞记录，like_status为1
     * @Param:  * @param null
     * @return:
     * @Author: 行之
     * @Date: 2021/1/11
     */
    int addArticleLikeRecord(Integer blogId, Integer userId);

    /**
     * @Description: 当用文章点赞表点赞状态进行更新
     * @Param: blogId 文章id
     * @Param: userId 微信用户id
     * @Param: likeStatus 点赞状态：1为点赞，0为取消赞
     * @return:
     * @Author: 行之
     * @Date: 2021/1/11
     */
    int updateArticleLikeStatus(Integer blogId, Integer userId, Integer likeStatus);

    /**
     * @Description: 获取用户是否点赞，点赞了则返回1，未点赞则返回0
     * @Param:  * @param null
     * @return:
     * @Author: 行之
     * @Date: 2021/1/11
     */
    Integer getArticleLikeStatusByBlogIdAndUserId(Integer blogId, Integer userId);

}
