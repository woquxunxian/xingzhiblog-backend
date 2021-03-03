package com.xingzhi.xingzhiblog.article.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xingzhi.xingzhiblog.article.constant.RedisConstant;
import com.xingzhi.xingzhiblog.article.constant.RedissonConstant;
import com.xingzhi.xingzhiblog.article.dao.ArticleDetailMapper;
import com.xingzhi.xingzhiblog.article.domain.vo.ArticleCommentVO;
import com.xingzhi.xingzhiblog.article.domain.vo.ArticleDetailVO;
import com.xingzhi.xingzhiblog.article.domain.vo.ArticleListVO;
import com.xingzhi.xingzhiblog.article.feign.WxAccountFeignService;
import com.xingzhi.xingzhiblog.article.service.ArticleCommentService;
import com.xingzhi.xingzhiblog.article.service.ArticleDetailService;
import com.xingzhi.xingzhiblog.common.result.R;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

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

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @Description: 获取所有有效文章
     * @Param:  null
     * @return: List<ArticleListVO> 全部文章
     * @Author: 行之
     */
    @Override
    @Cacheable(value = RedisConstant.ARTICLE_LIST , key = "#root.methodName")
    public List<ArticleListVO> getAllArticle() {
        // TODO 分布式锁，锁上没缓存时去数据库拿然后缓存的过程 --完成
        // 获取列表信息流的分布式锁
        RLock rLock = redissonClient.getLock(RedissonConstant.ARTICLELIST_REDISSONLOCK);
        rLock.lock();
        try {
            String allArticleListString = stringRedisTemplate.opsForValue().get("articleList::getAllArticle");
            if (StrUtil.isEmpty(allArticleListString)) { // 缓存中确实没有，去数据库拿
                log.info("缓存未命中---getAllArticle()进行了数据库查询");
                List<ArticleListVO> articleListVOList = articleDetailMapper.getALlArticle();
                return articleListVOList;
            } else { // 缓存中有了，转成list后返回
                List<ArticleListVO> articleListVOList = JSON.parseObject(allArticleListString, new TypeReference<List<ArticleListVO>>(){});
                return articleListVOList;
            }
        }  finally {
            rLock.unlock();
            log.info("分布式锁"+ RedissonConstant.ARTICLELIST_REDISSONLOCK +"解锁");
        }
    }

    /**
     * @Description: 通过id获取文章内容
     * @Param: blogId 文章id
     * @return: ArticleDetailVO 文章详情响应数据
     * @Author: 行之
     */
    @Override
    @Transactional
//    @GlobalTransactional
    public ArticleDetailVO getArticleContentByBlogId(int blogId) throws ExecutionException, InterruptedException {
        // TODO 异步编排 --完成
        ArticleDetailVO articleDetailVO = new ArticleDetailVO();

        //获取文章内容
        CompletableFuture<Void> articleContentCompletableFuture = CompletableFuture.runAsync(() -> {
            articleDetailVO.setArticleContent(articleDetailMapper.getArticleContentByBlogId(blogId));
        }, threadPoolExecutor);
        //获取文章评论
        CompletableFuture<Void> articleCommentVOListCompletableFuture = CompletableFuture.runAsync(() -> {
            articleDetailVO.setArticleCommentVOList(articleCommentService.getArticleCommentByBlogId(blogId));
        }, threadPoolExecutor);
        // 等待异步结果
        CompletableFuture.allOf(articleContentCompletableFuture, articleCommentVOListCompletableFuture).get();

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
        // TODO 阅读量先放入redis，定时任务读取写入数据库 --完成
        RLock rLock = redissonClient.getLock(RedisConstant.VIEW_NUMBER_KEY + "-lock");
        rLock.lock();
        try {
            stringRedisTemplate.opsForHash().increment(RedisConstant.VIEW_NUMBER_KEY, String.valueOf(blogId), 1);
            return 1;
        }finally {
            rLock.unlock();
        }
    }

}
