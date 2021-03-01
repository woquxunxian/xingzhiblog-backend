package com.xingzhi.xingzhiblog.info.service;

import com.xingzhi.xingzhiblog.info.domain.vo.InfoVO;

/**
 * @program: xingzhiblog
 * @description: 博客、博主信息Service接口
 * @author: 行之
 * @create: 2020-12-23 17:22
 **/
public interface InfoService {
    /**
     * @Description: 获取博客、作者全部信息
     * @Param:  * @param null
     * @return: InfoVO 信息数据响应封装
     * @Author: 行之
     */
    InfoVO getInfo();
}
