package com.xingzhi.xingzhiblog.article.dao;

import com.xingzhi.xingzhiblog.article.domain.vo.TagVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 标签相关Mapper
 * @author: 行之
 * @create: 2020-12-27 23:25
 **/
@Repository
public interface TagMapper {

    /**
    * @Description: 获取所有标签
    * @Param: null
    * @return: List<TagVO> 标签响应数据列表
    * @Author: 行之
    */
    List<TagVO> getAllTag();

    /**
    * @Description: 通过标签名模糊查询标签
    * @Param: null
    * @return: List<TagVO> 标签响应数据列表
    * @Author: 行之
    */
    List<TagVO> getTagByFuzzyQuery(String tagName);

}
