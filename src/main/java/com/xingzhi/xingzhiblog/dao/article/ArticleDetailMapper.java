package com.xingzhi.xingzhiblog.dao.article;

import com.xingzhi.xingzhiblog.domain.vo.ArticleDetailVO;
import com.xingzhi.xingzhiblog.domain.vo.ArticleListVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 文章管理Mapper
 * @author: 行之
 * @create: 2020-12-28 21:38
 **/
@Repository
public interface ArticleDetailMapper {

    /**
    * @Description:
    * @Param:  * @param null
    * @return:
    * @Author: 行之
    * @Date: 2020/12/31
    */
    List<ArticleListVO> getALlArticle();

    ArticleDetailVO getArticleContentById(int id);

}
