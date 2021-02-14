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
    ArticleDetailVO getArticleContentByBlogId(@Param("blogId") int blogId);

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
    * @Description: 通过博客id增加阅读数，目前只是模拟增加，后期维护用户系统后才能统计真正的阅读数量
    * @Param:  * @param null
    * @return:
    * @Author: 行之
    * @Date: 2021/1/6
    */
    int updateViewCountByBlogId(Integer BlogId);

}
