package com.xingzhi.xingzhiblog.dao.timeline;

import com.xingzhi.xingzhiblog.domain.vo.TimeLineVO;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    * @Param:  * @param null
    * @return:
    * @Author: 行之
    * @Date: 2021/1/9
    */
    List<String> getAllArticleDate();



}
