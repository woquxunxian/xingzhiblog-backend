package com.xingzhi.xingzhiblog.info.service.impl;

import com.xingzhi.xingzhiblog.common.exception.SystemException;
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

    @Cacheable(key = "'blogInfo'", value = "blogInfo")
    @Override
    public InfoVO getInfo() {
        log.info("[getAllInfoById] 获取作者的信息");
        InfoVO infoVO = infoMapper.getInfo();
        if (infoVO != null) {
            return infoVO;
        } else {
            throw new SystemException("infoMapper.getAllInfoById返回值为空");
        }
    }
}
