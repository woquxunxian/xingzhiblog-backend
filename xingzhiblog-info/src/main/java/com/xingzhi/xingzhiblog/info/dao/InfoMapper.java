package com.xingzhi.xingzhiblog.info.dao;

import com.xingzhi.xingzhiblog.info.domain.vo.InfoVO;
import org.springframework.stereotype.Repository;

/**
 * @program: xingzhiblog
 * @description: 博客信息mapper
 * @author: 行之
 * @create: 2020-12-23 16:26
 **/
@Repository
public interface InfoMapper {
    /**
     * 获取博客及博主个人信息
     * @param null
     * @return InfoVO
     */
    InfoVO getInfo();
}
