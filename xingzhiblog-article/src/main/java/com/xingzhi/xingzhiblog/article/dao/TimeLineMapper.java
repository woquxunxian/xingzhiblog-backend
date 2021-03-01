package com.xingzhi.xingzhiblog.article.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 归档相关
 * @author: 行之
 * @create: 2021-01-09 16:10
 **/
@Repository
public interface TimeLineMapper {

    /**
    * @Description: 不重复地获取所有文章的日期
    * @Param: null
    * @return: List<String> 文章日期列表
    * @Author: 行之
    */
    List<String> getAllArticleDate();



}
