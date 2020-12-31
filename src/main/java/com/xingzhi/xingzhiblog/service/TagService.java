package com.xingzhi.xingzhiblog.service;

import com.xingzhi.xingzhiblog.domain.vo.TagVO;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 博客标签Service
 * @author: 行之
 * @create: 2020-12-27 23:46
 **/
public interface TagService {

    List<TagVO> getAllTag();

}
