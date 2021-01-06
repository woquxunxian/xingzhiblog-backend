package com.xingzhi.xingzhiblog.controller.article;

import com.xingzhi.xingzhiblog.common.result.ResponseObject;
import com.xingzhi.xingzhiblog.common.result.ResponseUtil;
import com.xingzhi.xingzhiblog.domain.vo.ArticleCommentVO;
import com.xingzhi.xingzhiblog.service.ArticleCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 文章评论
 * @author: 行之
 * @create: 2021-01-02 19:26
 **/
@RestController
@RequestMapping("api/article")
@Api(tags="文章相关操作")
public class ArticleCommentController {

    @Autowired
    private ArticleCommentService articleCommentService;

    @PostMapping("comment")
    @ApiOperation("根据文章id获取相关评论")
    public ResponseObject getArticleCommentByBlogId(int blogId) {
        ResponseUtil responseUtil = new ResponseUtil();
        List<ArticleCommentVO> articleCommentVOList = articleCommentService.getArticleCommentByBlogId(blogId);
        return responseUtil.success(articleCommentVOList);
    };

}
