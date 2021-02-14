package com.xingzhi.xingzhiblog.article.service.impl;

import com.xingzhi.xingzhiblog.article.dao.TimeLineMapper;
import com.xingzhi.xingzhiblog.article.domain.vo.TimeLineVO;
import com.xingzhi.xingzhiblog.article.service.TimeLineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.Map.Entry;

/**
 * @program: xingzhiblog
 * @description: 归档相关
 * @author: 行之
 * @create: 2021-01-09 17:30
 **/
@Slf4j
@Service
@Transactional
public class TimeLineServiceImpl implements TimeLineService {

    @Autowired
    private TimeLineMapper timeLineMapper;

    /**
    * @Description: 获取归档数据
    *               1、获取全部归档数据，用HashMap的特性进行归档统计
     *              2、为了方便小程序端的展示，把Map中的数据转换到List中
    * @Param:  * @param null
    * @return:
    * @Author: 行之
    * @Date: 2021/1/9
    */
    @Override
    public List<TimeLineVO> getAllArticleDate() {
        List<String> timeLineList = timeLineMapper.getAllArticleDate();
        Map<String, Integer> timeLineMap = new HashMap<>();
        for (String date : timeLineList) {
            if (timeLineMap.get(date) == null) {
                timeLineMap.put(date, 1);
            } else {
                timeLineMap.put(date, timeLineMap.get(date) + 1);
            }
        }
        List<TimeLineVO> timeLineVOList = new LinkedList<>();
//        TimeLineVO tempTimeLineVO = new TimeLineVO();
        Iterator timeLineMapIterator = timeLineMap.entrySet().iterator();
        while (timeLineMapIterator.hasNext()) {
            TimeLineVO tempTimeLineVO = new TimeLineVO();
            Entry entry = (Entry) timeLineMapIterator.next();
            tempTimeLineVO.setDate((String) entry.getKey());
            tempTimeLineVO.setCount((Integer) entry.getValue());
            timeLineVOList.add(tempTimeLineVO);
        }
        return timeLineVOList;
    }

}
