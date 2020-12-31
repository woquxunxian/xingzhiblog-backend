package com.xingzhi.xingzhiblog.controller.article;

import com.xingzhi.xingzhiblog.common.result.ResponseObject;
import com.xingzhi.xingzhiblog.common.result.ResponseUtil;
import com.xingzhi.xingzhiblog.domain.vo.ArticleDetailVO;
import com.xingzhi.xingzhiblog.domain.vo.ArticleListVO;
import com.xingzhi.xingzhiblog.service.article.ArticleDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 文章相关请求控制器
 * @author: 行之
 * @create: 2020-12-30 16:46
 **/
@RestController
@RequestMapping("article")
@Api(tags="文章相关操作")
public class ArticleDetailController {

    @Autowired
    private ArticleDetailService articleDetailService;

    @GetMapping("all")
    @ApiOperation("获取所有文章")
    public ResponseObject getAllArticle() {
        List<ArticleListVO> articleListVOList = articleDetailService.getAllArticle();
        ResponseUtil responseUtil = new ResponseUtil();
        return responseUtil.success(articleListVOList);
    }

    @PostMapping("detail")
    @ApiOperation("通过id获取文章内容")
    public ResponseObject getArticleContentById(int id) {
        ArticleDetailVO articleDetailVO = articleDetailService.getArticleContentById(id);
        ResponseUtil responseUtil = new ResponseUtil();
        return responseUtil.success(articleDetailVO);
    }

}
