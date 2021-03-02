package com.xingzhi.xingzhiblog.article.quartz;

import com.xingzhi.xingzhiblog.article.constant.RedisConstant;
import com.xingzhi.xingzhiblog.article.dao.ArticleDetailMapper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Map;

/**
 * @program: xingzhiblog
 * @description: 阅读量任务
 * @author: 行之
 * @create: 2021-03-02 20:14
 **/
@Slf4j
public class ViewJob extends QuartzJobBean {

    @Autowired
    private ArticleDetailMapper articleDetailMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(this.getClass() + "--进入阅读量定时任务--" + System.currentTimeMillis());
        RLock getViewNumberLock = redissonClient.getLock(RedisConstant.VIEW_NUMBER_KEY + "-lock");
        getViewNumberLock.lock();
        Map<Object, Object> viewEntries;
        try {
            viewEntries = stringRedisTemplate.opsForHash().entries(RedisConstant.VIEW_NUMBER_KEY);
            stringRedisTemplate.delete(RedisConstant.VIEW_NUMBER_KEY);
        } finally {
            getViewNumberLock.unlock();
        }
        log.info("viewEntries:{}", viewEntries.toString());
        viewEntries.forEach((blogId, viewCount) -> {
            articleDetailMapper.updateViewCountByBlogIdAndViewCount(Integer.valueOf(String.valueOf(blogId)),
                                                                Integer.valueOf(String.valueOf(viewCount)));
        });
        log.info(this.getClass() + "--阅读量定时任务完成--" + System.currentTimeMillis());
    }
}
