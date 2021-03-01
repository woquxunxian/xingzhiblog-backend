package com.xingzhi.xingzhiblog.article.service;

import com.xingzhi.xingzhiblog.article.domain.vo.TimeLineVO;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 归档相关
 * @author: 行之
 * @create: 2021-01-09 17:30
 **/
public interface TimeLineService {
    /**
     * @Description: 获取归档数据
     * @Param: null
     * @return: List<TimeLineVO> 归档轴响应对象列表
     * @Author: 行之
     */
    List<TimeLineVO> getAllArticleDate();
}
