package com.xingzhi.xingzhiblog.article.constant;

/**
 * @program: xingzhiblog
 * @description: Redis缓存常类
 * @author: 行之
 * @create: 2021-03-01 20:49
 **/
public class RedisConstant {
    /**
     * 数据自增步长为1
     */
    public static final Integer INCREMENT = 1;
    /**
     * 数据自减步长为1
     */
    public static final Integer REDUCTION = -1;
    /**
     * 存储文章评论前缀
     */
    public static final String ARTICLE_COMMENT = "articleComment";
    /**
     * 存储文章详情前缀
     */
    public static final String ARTICLE_LIST = "articleList";
    /**
     * 存储标签前缀
     */
    public static final String ARTICLE_TAG = "articleTag";
    /**
     * 存储归档轴前缀
     */
    public static final String ARTICLE_TIMELINE = "articleTimeLine";

    /** 阅读量key */
    public static final String VIEW_NUMBER_KEY = "view::number";

    /** 点赞key */
    public static final String LIKE_STATUS_KEY = "like::status";
}
