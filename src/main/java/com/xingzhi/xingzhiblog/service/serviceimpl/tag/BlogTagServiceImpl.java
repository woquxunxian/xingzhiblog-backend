package com.xingzhi.xingzhiblog.service.serviceimpl.tag;

import com.xingzhi.xingzhiblog.dao.tag.TagMapper;
import com.xingzhi.xingzhiblog.domain.vo.TagVO;
import com.xingzhi.xingzhiblog.exception.SystemException;
import com.xingzhi.xingzhiblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(key="'tagList'", value = "tagList")
    @Override
    public List<TagVO> getAllTag() {
        List<TagVO> tagVOList = tagMapper.getAllTag();
        if (tagVOList == null) throw new SystemException("系统出错");
        return tagVOList;
    }

    @Override
    public List<TagVO> getTagByFuzzyQuery(String tagName) {
        List<TagVO> tagVOList = tagMapper.getTagByFuzzyQuery(tagName);
        if (tagVOList == null) throw new SystemException("系统出错");
        return tagVOList;
    }
}
