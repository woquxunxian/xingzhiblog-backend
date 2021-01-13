package com.xingzhi.xingzhiblog.dao.article;

import com.xingzhi.xingzhiblog.domain.vo.ArticleDetailVO;
import com.xingzhi.xingzhiblog.domain.vo.ArticleListVO;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 文章管理Mapper
 * @author: 行之
 * @create: 2020-12-28 21:38
 **/
@Repository
public interface ArticleDetailMapper {

    /**
    * @Description: 获取所有文章列表
    * @Param:  * @param null
    * @return:
    * @Author: 行之
    * @Date: 2020/12/31
    */
    List<ArticleListVO> getALlArticle();

    /**
    * @Description: 通过文章ID获取文章内容
    * @Param:  * @param null
    * @return:
    * @Author: 行之
    * @Date: 2021/1/6
    */
    ArticleDetailVO getArticleContentByBlogId(int blogId);

    /**
    * @Description: 通过文章标题关键字模糊查询文章
    * @Param:  * @param null
    * @return:
    * @Author: 行之
    * @Date: 2021/1/6
    */
    List<ArticleListVO> getArticleBySearchWithTitle(String articleTitle);

    /**
    * @Description: 通过标签名查询文章
    * @Param:  * @param null
    * @return:
    * @Author: 行之
    * @Date: 2021/1/9
    */
    List<ArticleListVO> getArticleByTagName(String articleTagName);

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
    * @Description: 通过博客id增加阅读数，目前只是模拟增加，后期维护用户系统后才能统计真正的阅读数量
    * @Param:  * @param null
    * @return:
    * @Author: 行之
    * @Date: 2021/1/6
    */
    int updateViewCountByBlogId(Integer BlogId);

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
