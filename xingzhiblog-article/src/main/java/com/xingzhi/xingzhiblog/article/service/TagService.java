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

    List<TagVO> getAllTag();

    List<TagVO> getTagByFuzzyQuery(String tagName);

    List<ArticleListVO> getArticleByTagName(String articleTagName);
}
