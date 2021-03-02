package com.xingzhi.xingzhiblog.article;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xingzhi.xingzhiblog.article.constant.RedisConstant;
import com.xingzhi.xingzhiblog.article.domain.vo.ArticleDetailVO;
import com.xingzhi.xingzhiblog.article.domain.vo.ArticleListVO;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class XingzhiblogArticleApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    RedissonClient redissonClient;

    @Test
    protected void testRedis() {
        String allArticleListString = stringRedisTemplate.opsForValue().get("articleList::getAllArticle");
        System.out.println(allArticleListString);
        List<ArticleListVO> articleListVOList = JSON.parseObject(allArticleListString, new TypeReference<List<ArticleListVO>>() {});
        articleListVOList.forEach(item -> {
            System.out.println(item.toString());
        });
    }

//    @Test
    public void test() {
        RLock rLock = redissonClient.getLock(RedisConstant.VIEW_NUMBER_KEY + "-lock");
        rLock.lock();
        try {
            Thread.sleep(2000);
            System.out.println(stringRedisTemplate.opsForValue().increment(RedisConstant.VIEW_NUMBER_KEY));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            rLock.unlock();
        }
//        System.out.println(stringRedisTemplate.opsForValue().increment(RedisConstant.VIEW_NUMBER_KEY));
////        System.out.println(null == redisTemplate.opsForValue().get(RedisConstant.VIEW_NUMBER_KEY));
    }

    @Test
    public void Main() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                this.test();
            }
            private void test() {
                RLock rLock = redissonClient.getLock(RedisConstant.VIEW_NUMBER_KEY + "-lock");
                rLock.lock();
                try {
                    Thread.sleep(2000);
                    System.out.println(stringRedisTemplate.opsForValue().increment(RedisConstant.VIEW_NUMBER_KEY));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    rLock.unlock();
                }
            }
        });
        this.test();
        thread1.start();
    }

    @Test
    public void viewNumber() {
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("view::number");
        entries.forEach((key, value) -> {
            System.out.println("blogId: " + key + ",value: " + value);
        });
    }

}
