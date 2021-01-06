package com.xingzhi.xingzhiblog.service;

import com.xingzhi.xingzhiblog.domain.vo.ArticleDetailVO;
import com.xingzhi.xingzhiblog.domain.vo.ArticleListVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 文章内容相关
 * @author: 行之
 * @create: 2020-12-30 16:54
 **/
@Service
public interface ArticleDetailService {

    /**
    * @Description: 获取所有有效文章
    * @Param:  * @param null
    * @return: List<ArticleListVO>
    * @Author: 行之
    * @Date: 2020/12/31
    */
    List<ArticleListVO> getAllArticle();

    /**
    * @Description: 通过id获取文章内容
    * @Param:  * @param null
    * @return: 
    * @Author: 行之
    * @Date: 2020/12/31
    */
    ArticleDetailVO getArticleContentByBlogId(int blogId);

    /**
    * @Description: 通过文章标题关键字搜索文章
    * @Param:  * @param null
    * @return:
    * @Author: 行之
    * @Date: 2021/1/6
    */
    List<ArticleListVO> getArticleBySearchWithTitle(String articleTitle);

    Integer updateLikeCountByBlogId(Integer blogId);

    Integer updateViewCountByBlogId(Integer blogId);
}
