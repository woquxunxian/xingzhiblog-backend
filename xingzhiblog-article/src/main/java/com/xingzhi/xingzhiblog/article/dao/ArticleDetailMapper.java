package com.xingzhi.xingzhiblog.article.dao;

import com.xingzhi.xingzhiblog.article.domain.vo.ArticleDetailVO;
import com.xingzhi.xingzhiblog.article.domain.vo.ArticleListVO;
import org.apache.ibatis.annotations.Param;
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
    */
    List<ArticleListVO> getALlArticle();

    /**
    * @Description: 通过文章ID获取文章内容
    * @Param: blogId 博客id
    * @return: ArticleDetailVO 博客详情响应对象
    * @Author: 行之
    */
    ArticleDetailVO getArticleContentByBlogId(@Param("blogId") int blogId);

    /**
    * @Description: 通过文章标题关键字模糊查询文章
    * @Param: articleTitle 文章标题
    * @return: List<ArticleListVO> 文章信息流响应对象列表
    * @Author: 行之
    */
    List<ArticleListVO> getArticleBySearchWithTitle(String articleTitle);

    /**
    * @Description: 通过标签名查询文章
    * @Param: articleTagName 标签名
    * @return: List<ArticleListVO> 文章信息流响应对象列表
    * @Author: 行之
    */
    List<ArticleListVO> getArticleByTagName(String articleTagName);

    /**
    * @Description: 通过博客id增加阅读数
    * @Param: BlogId 博客id
    * @return: 成功：1；失败：0
    * @Author: 行之
    */
    int updateViewCountByBlogId(Integer BlogId);

}
