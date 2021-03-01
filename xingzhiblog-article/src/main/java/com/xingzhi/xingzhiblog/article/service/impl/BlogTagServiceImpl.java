package com.xingzhi.xingzhiblog.article.service.impl;

import com.xingzhi.xingzhiblog.article.constant.RedisConstant;
import com.xingzhi.xingzhiblog.article.dao.ArticleDetailMapper;
import com.xingzhi.xingzhiblog.article.dao.TagMapper;
import com.xingzhi.xingzhiblog.article.domain.vo.ArticleListVO;
import com.xingzhi.xingzhiblog.article.domain.vo.TagVO;
import com.xingzhi.xingzhiblog.article.service.TagService;
import com.xingzhi.xingzhiblog.common.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 博客标签service实现类
 * @author: 行之
 * @create: 2020-12-27 23:47
 **/
@Slf4j
@Service
@Transactional
public class BlogTagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleDetailMapper articleDetailMapper;

    /**
     * @Description: 获取所有标签数据
     * @Param: null
     * @return: List<TagVO> 标签响应对象列表
     * @Author: 行之
     */
    @Override
    @Cacheable(value = RedisConstant.ARTICLE_TAG, key = "#root.methodName")
    public List<TagVO> getAllTag() {
        List<TagVO> tagVOList = tagMapper.getAllTag();
        if (tagVOList == null) throw new SystemException("系统出错");
        return tagVOList;
    }

    /**
     * @Description: 通过标签名查询标签
     * @Param: tagName 标签名
     * @return: List<TagVO> 标签响应对象列表
     * @Author: 行之
     */
    @Override
    public List<TagVO> getTagByFuzzyQuery(String tagName) {
        // TODO elastic search 增强搜索速度
        List<TagVO> tagVOList = tagMapper.getTagByFuzzyQuery(tagName);
        if (tagVOList == null) throw new SystemException("系统出错");
        return tagVOList;
    }

    /**
     * @Description: 通过标签名获取相关文章
     * @Param: articleTagName 标签名
     * @return: List<TagVO> 标签响应对象列表
     * @Author: 行之
     */
    @Override
    public List<ArticleListVO> getArticleByTagName(String articleTagName) {
        // TODO elastic search 增强搜索速度
        List<ArticleListVO> articleListVOList = articleDetailMapper.getArticleByTagName(articleTagName);
        return articleListVOList;
    }
}
