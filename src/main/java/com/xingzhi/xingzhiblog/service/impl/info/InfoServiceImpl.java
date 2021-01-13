package com.xingzhi.xingzhiblog.service.impl.info;


import com.xingzhi.xingzhiblog.dao.info.InfoMapper;
import com.xingzhi.xingzhiblog.domain.vo.InfoVO;
import com.xingzhi.xingzhiblog.exception.SystemException;
import com.xingzhi.xingzhiblog.service.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
