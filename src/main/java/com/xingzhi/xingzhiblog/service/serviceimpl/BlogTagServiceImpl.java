package com.xingzhi.xingzhiblog.service.serviceimpl;

import com.xingzhi.xingzhiblog.dao.tag.TagMapper;
import com.xingzhi.xingzhiblog.domain.vo.TagVO;
import com.xingzhi.xingzhiblog.service.TagService;
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
public class BlogTagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<TagVO> getAllTag() {
        List<TagVO> tagVOList = tagMapper.getAllTag();
        return tagVOList;
    }
}
