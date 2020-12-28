package com.xingzhi.xingzhiblog.dao;

import com.xingzhi.xingzhiblog.domain.vo.TagVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 标签相关Mapper
 * @author: 行之
 * @create: 2020-12-27 23:25
 **/
@Repository
public interface BlogTagMapper {

    List<TagVO> getAllTag();

}
