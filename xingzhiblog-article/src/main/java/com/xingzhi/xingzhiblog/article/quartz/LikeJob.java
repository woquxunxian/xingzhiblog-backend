package com.xingzhi.xingzhiblog.article.quartz;

import com.xingzhi.xingzhiblog.article.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Map;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: xingzhiblog
 * @description: 点赞状态任务
 * @author: 行之
 * @create: 2021-03-02 20:14
 **/
@Slf4j
public class LikeJob extends QuartzJobBean {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(this.getClass() + "--进入点赞定时任务--" + System.currentTimeMillis());
        RLock likeStatusLock = redissonClient.getLock(RedisConstant.LIKE_STATUS_KEY + "-lock");
        likeStatusLock.lock();
        Map<Object, Object> likeStatusEntries;
        try {
            likeStatusEntries = stringRedisTemplate.opsForHash().entries(RedisConstant.LIKE_STATUS_KEY);
            stringRedisTemplate.delete(RedisConstant.LIKE_STATUS_KEY);
        } finally {
            likeStatusLock.unlock();
        }
        RLock likeStatusSendLock = redissonClient.getLock(RedisConstant.LIKE_STATUS_KEY + "-sendLock");
        likeStatusSendLock.lock();
        try {
            log.info("likeStatusEntries:{}", likeStatusEntries.toString());
            likeStatusEntries.forEach((blogIdUserId, status) -> {
                String message = String.valueOf(blogIdUserId) + "_" + String.valueOf(status);
                log.info(message);
                rabbitTemplate.convertAndSend("like-event-exchange","like.status", message);
            });
            log.info(this.getClass() + "--点赞定时任务完成--" + System.currentTimeMillis());
        } finally {
            likeStatusSendLock.unlock();
        }
    }

//    private int[] dataProcess(Object blogIdUserId, Object status) {
//        int[] data = dataProcess(blogIdUserId, status);
//        int blogId = data[0]; int userId = data[1]; int likeStatus = data[2];
//    }

}
