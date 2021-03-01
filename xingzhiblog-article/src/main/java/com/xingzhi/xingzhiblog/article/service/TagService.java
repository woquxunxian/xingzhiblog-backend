package com.xingzhi.xingzhiblog.article.service;

import com.xingzhi.xingzhiblog.article.domain.vo.ArticleListVO;
import com.xingzhi.xingzhiblog.article.domain.vo.TagVO;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 博客标签Service
 * @author: 行之
 * @create: 2020-12-27 23:46
 **/
public interface TagService {

    /**
     * @Description: 获取所有标签数据
     * @Param: null
     * @return: List<TagVO> 标签响应对象列表
     * @Author: 行之
     */
    List<TagVO> getAllTag();

    /**
     * @Description: 通过标签名查询标签
     * @Param: tagName 标签名
     * @return: List<TagVO> 标签响应对象列表
     * @Author: 行之
     */
    List<TagVO> getTagByFuzzyQuery(String tagName);

    /**
     * @Description: 通过标签名获取相关文章
     * @Param: articleTagName 标签名
     * @return: List<TagVO> 标签响应对象列表
     * @Author: 行之
     */
    List<ArticleListVO> getArticleByTagName(String articleTagName);
}
