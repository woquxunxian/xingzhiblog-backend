package com.xingzhi.xingzhiblog.dao.info;

import com.xingzhi.xingzhiblog.domain.vo.InfoVO;
import org.apache.ibatis.annotations.Mapper;
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
     * 通过id获取博客及博主个人信息
     * @param
     * @return InfoVO
     */
    public InfoVO getInfo();
}
