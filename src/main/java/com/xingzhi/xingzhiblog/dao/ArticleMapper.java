package com.xingzhi.xingzhiblog.dao;

import com.xingzhi.xingzhiblog.domain.vo.ArticleListVO;
import org.springframework.stereotype.Repository;

/**
 * @program: xingzhiblog
 * @description: 文章管理Mapper
 * @author: 行之
 * @create: 2020-12-28 21:38
 **/
@Repository
public interface ArticleMapper {

    ArticleListVO getALlArticle();

}
