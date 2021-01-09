package com.xingzhi.xingzhiblog.service.serviceimpl.article;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xingzhi.xingzhiblog.dao.article.ArticleDetailMapper;
import com.xingzhi.xingzhiblog.domain.vo.ArticleDetailVO;
import com.xingzhi.xingzhiblog.domain.vo.ArticleListVO;
import com.xingzhi.xingzhiblog.service.ArticleCommentService;
import com.xingzhi.xingzhiblog.service.ArticleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 文章内容相关
 * @author: 行之
 * @create: 2020-12-30 16:58
 **/
@Service
public class ArticleDetailServiceImpl implements ArticleDetailService {

    @Autowired
    private ArticleDetailMapper articleDetailMapper;

    @Autowired
    private ArticleCommentService articleCommentService;

    /**
    * @Description: 获取所有有效文章
    *               放入redis缓存
    * @Param:  * @param null
    * @return: 文章列表
    * @Author: 行之
    * @Date: 2020/12/31
    */
    @Cacheable(key = "'articleList'", value = "articleList")
    @Override
    public List<ArticleListVO> getAllArticle() {
        List<ArticleListVO> articleListVOList = articleDetailMapper.getALlArticle();
        return articleListVOList;
    }

    /**
    * @Description: 通过文章id获取文章内容
    * @Param:  * @param null
    * @return:
    * @Author: 行之
    * @Date: 2021/1/6
    */
    @Override
    public ArticleDetailVO getArticleContentByBlogId(int blogId) {
        ArticleDetailVO articleDetailVO = articleDetailMapper.getArticleContentByBlogId(blogId);
        articleDetailVO.setArticleCommentVOList(articleCommentService.getArticleCommentByBlogId(blogId));
        return articleDetailVO;
    }

    /**
    * @Description: 通过标题关键词查询博客
    * @Param:  * @param null
    * @return:
    * @Author: 行之
    * @Date: 2021/1/6
    */
    @Override
    public List<ArticleListVO> getArticleBySearchWithTitle(String articleTitle) {
        List<ArticleListVO> articleListVOList = articleDetailMapper.getArticleBySearchWithTitle(articleTitle);
        return articleListVOList;
    }

    /**
    * @Description: 通过博客id增加点赞数，目前只是模拟增加，后期维护用户系统后才能统计真正的点赞数量
    * @Param:  * @param null
    * @return:
    * @Author: 行之
    * @Date: 2021/1/6
    */
    @CacheEvict(value = "articleList", allEntries=true)
    @Override
    public Integer updateLikeCountByBlogId(Integer blogId) {
        Integer updateStatus  = articleDetailMapper.updateLikeCountByBlogId(blogId);
        return updateStatus;
    }

    /**
    * @Description: 通过博客id增加阅读数，目前只是模拟增加，后期维护用户系统后才能统计真正的阅读数量
    * @Param:  * @param null
    * @return:
    * @Author: 行之
    * @Date: 2021/1/6
    */
    @CacheEvict(value = "articleList", allEntries=true)
    @Override
    public Integer updateViewCountByBlogId(Integer blogId) {
        Integer updateStatus  = articleDetailMapper.updateViewCountByBlogId(blogId);
        return updateStatus;
    }

    @CacheEvict(value = "articleList", allEntries=true)
    @Override
    public Integer updateMinusLikeCountByBlogId(Integer blogId) {
        Integer updateStatus  = articleDetailMapper.updateMinusLikeCountByBlogId(blogId);
        return updateStatus;
    }


}
