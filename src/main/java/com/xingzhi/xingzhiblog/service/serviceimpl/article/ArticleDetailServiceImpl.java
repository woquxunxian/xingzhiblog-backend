package com.xingzhi.xingzhiblog.service.serviceimpl.article;

import com.xingzhi.xingzhiblog.dao.article.ArticleDetailMapper;
import com.xingzhi.xingzhiblog.domain.vo.ArticleDetailVO;
import com.xingzhi.xingzhiblog.domain.vo.ArticleListVO;
import com.xingzhi.xingzhiblog.service.article.ArticleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 文章内容相关
 * @author: 行之
 * @create: 2020-12-30 16:58
 **/
@Service
public class ArticleDetailServiceImpl implements ArticleDetailService {

    @Autowired
    private ArticleDetailMapper articleDetailMapper;

    /**
    * @Description: 获取所有有效文章
    * @Param:  * @param null
    * @return: 
    * @Author: 行之
    * @Date: 2020/12/31
    */
    @Override
    public List<ArticleListVO> getAllArticle() {
        List<ArticleListVO> articleListVOList = articleDetailMapper.getALlArticle();
        return articleListVOList;
    }

    /**
    * @Description: 通过id获取文章内容
    * @Param:  * @param null
    * @return: 
    * @Author: 行之
    * @Date: 2020/12/31
    */
    @Override
    public ArticleDetailVO getArticleContentById(int id) {
        ArticleDetailVO articleDetailVO = articleDetailMapper.getArticleContentById(id);
        return articleDetailVO;
    }


}
