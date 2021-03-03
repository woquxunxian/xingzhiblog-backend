package com.xingzhi.xingzhiblog.article.listener;

import com.rabbitmq.client.Channel;
import com.xingzhi.xingzhiblog.article.constant.RedisConstant;
import com.xingzhi.xingzhiblog.article.dao.ArticleLikeMapper;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * @program: xingzhiblog
 * @description: 点赞主题订阅者
 * @author: 行之
 * @create: 2021-03-03 12:39
 **/
@Slf4j
@Component
@RabbitListener(queues = "like-status-queue")
public class likeListener {

    @Autowired
    private ArticleLikeMapper articleLikeMapper;

    @Autowired
    private RedissonClient redissonClient;

    @Transactional
    @RabbitHandler
    public void listener(String blogIdUserIdLikeStatus, Channel channel, Message message) throws IOException {
        try{
            RLock likeListenerLock = redissonClient.getLock(RedisConstant.LIKE_STATUS_KEY + "listenerLock");
            likeListenerLock.lock();
            try {
                log.info("点赞订阅者获得消息：{}", blogIdUserIdLikeStatus);
                int[] data = dataProcess(blogIdUserIdLikeStatus);
                int blogId = data[0]; int userId = data[1]; int likeStatus = data[2];
                Integer isExist = articleLikeMapper.getUserArticleLikeRecord(blogId, userId);
                if (isExist != null) {
                    articleLikeMapper.updateArticleLikeStatus(blogId, userId, likeStatus);
                    articleLikeMapper.updateLikeCountByBlogId(blogId);
                    log.info("articleLikeMapper.updateLikeCountByBlogId(blogId): blogId-{}",blogId);
                } else {
                    articleLikeMapper.addArticleLikeRecord(blogId, userId);
                    articleLikeMapper.updateMinusLikeCountByBlogId(blogId);
                    log.info("articleLikeMapper.addArticleLikeRecord(blogId): blogId-{}",blogId);
                }
            } finally {
                likeListenerLock.unlock();
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch (Exception e){
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        }
    }

    private int[] dataProcess(String data) {
        String[] strs = data.split("_");
        int[] res = new int[strs.length];
        for (int i = 0; i < strs.length; ++i) {
            res[i] = Integer.valueOf(strs[i]);
        }
        return res;
    }

}
