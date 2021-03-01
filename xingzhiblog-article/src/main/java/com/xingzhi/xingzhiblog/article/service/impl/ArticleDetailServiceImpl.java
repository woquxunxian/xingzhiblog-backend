package com.xingzhi.xingzhiblog.article.service.impl;

import com.xingzhi.xingzhiblog.article.constant.RedisConstant;
import com.xingzhi.xingzhiblog.article.dao.ArticleDetailMapper;
import com.xingzhi.xingzhiblog.article.domain.vo.ArticleDetailVO;
import com.xingzhi.xingzhiblog.article.domain.vo.ArticleListVO;
import com.xingzhi.xingzhiblog.article.feign.WxAccountFeignService;
import com.xingzhi.xingzhiblog.article.service.ArticleCommentService;
import com.xingzhi.xingzhiblog.article.service.ArticleDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 文章内容相关
 * @author: 行之
 * @create: 2020-12-30 16:58
 **/
@Slf4j
@Service
@Transactional
public class ArticleDetailServiceImpl implements ArticleDetailService {

    @Autowired
    private ArticleDetailMapper articleDetailMapper;

    @Autowired
    private ArticleCommentService articleCommentService;

    /**
     * @Description: 获取所有有效文章
     * @Param:  null
     * @return: List<ArticleListVO> 全部文章
     * @Author: 行之
     */
    @Override
    @Cacheable(value = RedisConstant.ARTICLE_LIST, key = "#root.methodName")
    public List<ArticleListVO> getAllArticle() {
        // TODO 分布式锁，锁上没缓存时去数据库拿然后缓存的过程，防止缓存击穿
        List<ArticleListVO> articleListVOList = articleDetailMapper.getALlArticle();
        return articleListVOList;
    }

    /**
     * @Description: 通过id获取文章内容
     * @Param: blogId 文章id
     * @return: ArticleDetailVO 文章详情响应数据
     * @Author: 行之
     */
    @Override
    public ArticleDetailVO getArticleContentByBlogId(int blogId) {
        // TODO 异步编排、分布式事务
        //获取文章内容
        ArticleDetailVO articleDetailVO = articleDetailMapper.getArticleContentByBlogId(blogId);
        //获取文章评论
        articleDetailVO.setArticleCommentVOList(articleCommentService.getArticleCommentByBlogId(blogId));
        return articleDetailVO;
    }

    /**
     * @Description: 通过文章标题关键字搜索文章
     * @Param: articleTitle 文章标题
     * @return: List<ArticleListVO> 相关文章响应对象列表
     * @Author: 行之
     */
    @Override
    public List<ArticleListVO> getArticleBySearchWithTitle(String articleTitle) {
        // TODO 用 elastic search 来模糊查询标题
        List<ArticleListVO> articleListVOList = articleDetailMapper.getArticleBySearchWithTitle(articleTitle);
        return articleListVOList;
    }

    /**
     * @Description: 更新文章阅读量，删除信息流缓存
     * @Param: blogId 博客id
     * @return: Integer 更新成功：1；失败：0
     * @Author: 行之
     */
    @Override
    @CacheEvict(value = RedisConstant.ARTICLE_LIST, allEntries=true)
    public Integer updateViewCountByBlogId(Integer blogId) {
        // TODO 阅读量先放入redis，然后定时任务读取写入数据库
        Integer updateStatus  = articleDetailMapper.updateViewCountByBlogId(blogId);
        return updateStatus;
    }

}
