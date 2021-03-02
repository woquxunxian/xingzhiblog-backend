package com.xingzhi.xingzhiblog.article.service;

import com.xingzhi.xingzhiblog.article.domain.vo.ArticleDetailVO;
import com.xingzhi.xingzhiblog.article.domain.vo.ArticleListVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
    * @Param:  null
    * @return: List<ArticleListVO> 全部文章
    * @Author: 行之
    */
    List<ArticleListVO> getAllArticle();

    /**
    * @Description: 通过id获取文章内容
    * @Param: blogId 文章id
    * @return: ArticleDetailVO 文章详情响应数据
    * @Author: 行之
    */
    ArticleDetailVO getArticleContentByBlogId(int blogId) throws ExecutionException, InterruptedException;

    /**
    * @Description: 通过文章标题关键字搜索文章
    * @Param: articleTitle 文章标题
    * @return: List<ArticleListVO> 相关文章响应对象列表
    * @Author: 行之
    */
    List<ArticleListVO> getArticleBySearchWithTitle(String articleTitle);

    /**
     * @Description: 更新文章阅读量
     * @Param: blogId 博客id
     * @return: Integer 更新成功：1；失败：0
     * @Author: 行之
     */
    Integer updateViewCountByBlogId(Integer blogId);

}
