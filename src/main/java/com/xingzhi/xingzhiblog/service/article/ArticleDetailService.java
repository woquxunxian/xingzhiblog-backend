package com.xingzhi.xingzhiblog.service.article;

import com.xingzhi.xingzhiblog.domain.vo.ArticleDetailVO;
import com.xingzhi.xingzhiblog.domain.vo.ArticleListVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 文章内容相关
 * @author: 行之
 * @create: 2020-12-30 16:54
 **/
@Service
public interface ArticleDetailService {

    /**
    * @Description: 获取所有有效文章
    * @Param:  * @param null
    * @return: List<ArticleListVO>
    * @Author: 行之
    * @Date: 2020/12/31
    */
    List<ArticleListVO> getAllArticle();

    /**
    * @Description: 通过id获取文章内容
    * @Param:  * @param null
    * @return: 
    * @Author: 行之
    * @Date: 2020/12/31
    */
    ArticleDetailVO getArticleContentById(int id);


}
