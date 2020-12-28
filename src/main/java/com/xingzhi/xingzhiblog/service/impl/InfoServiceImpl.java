package com.xingzhi.xingzhiblog.service.impl;


import com.xingzhi.xingzhiblog.dao.InfoMapper;
import com.xingzhi.xingzhiblog.domain.vo.InfoVO;
import com.xingzhi.xingzhiblog.exception.SystemException;
import com.xingzhi.xingzhiblog.service.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: xingzhiblog
 * @description:
 * @author: 行之
 * @create: 2020-12-23 17:23
 **/
@Slf4j
@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private InfoMapper infoMapper;

    @Override
    public InfoVO getAllInfoById(int id) {
        log.info("[getAllInfoById] 获取" + id + "的信息");
        InfoVO infoVO = infoMapper.getAllInfoById(id);
        if (infoVO != null) {
            return infoVO;
        } else {
            throw new SystemException("infoMapper.getAllInfoById返回值为空");
        }
    }
}
