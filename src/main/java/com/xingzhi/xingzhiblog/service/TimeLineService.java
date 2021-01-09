package com.xingzhi.xingzhiblog.service;

import com.xingzhi.xingzhiblog.domain.vo.TimeLineVO;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 归档相关
 * @author: 行之
 * @create: 2021-01-09 17:30
 **/
public interface TimeLineService {
    List<TimeLineVO> getAllArticleDate();
}
