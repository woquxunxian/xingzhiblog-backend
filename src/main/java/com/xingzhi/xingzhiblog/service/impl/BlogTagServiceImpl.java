package com.xingzhi.xingzhiblog.service.impl;

import com.xingzhi.xingzhiblog.dao.BlogTagMapper;
import com.xingzhi.xingzhiblog.domain.vo.TagVO;
import com.xingzhi.xingzhiblog.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 博客标签service实现类
 * @author: 行之
 * @create: 2020-12-27 23:47
 **/
@Service
public class BlogTagServiceImpl implements BlogTagService {

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Override
    public List<TagVO> getAllTag() {
        List<TagVO> tagVOList = blogTagMapper.getAllTag();
        return tagVOList;
    }
}
