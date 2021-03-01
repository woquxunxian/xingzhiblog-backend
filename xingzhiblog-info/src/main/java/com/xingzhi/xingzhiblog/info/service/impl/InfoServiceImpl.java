package com.xingzhi.xingzhiblog.info.service.impl;

import com.xingzhi.xingzhiblog.common.exception.SystemException;
import com.xingzhi.xingzhiblog.info.constant.RedisConstant;
import com.xingzhi.xingzhiblog.info.dao.InfoMapper;
import com.xingzhi.xingzhiblog.info.domain.vo.InfoVO;
import com.xingzhi.xingzhiblog.info.service.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: xingzhiblog
 * @description:
 * @author: 行之
 * @create: 2020-12-23 17:23
 **/
@Slf4j
@Service
@Transactional
public class InfoServiceImpl implements InfoService {

    @Autowired
    private InfoMapper infoMapper;

    /**
    * @Description: 获取博客、作者全部信息，数据几乎不怎么改变，放redis中，key为blogInfo
    * @Param:  * @param null
    * @return: InfoVO 信息数据响应封装
    * @Author: 行之
    */
    @Override
    @Cacheable(value = RedisConstant.INFO, key = "#root.methodName", sync = true)
    public InfoVO getInfo() {
        log.info("[getAllInfoById] 获取作者的信息 - 缓存未命中");
        InfoVO infoVO = infoMapper.getInfo();
        if (infoVO != null) {
            return infoVO;
        } else {
            throw new SystemException("infoMapper.getAllInfoById返回值为空");
        }
    }
}
