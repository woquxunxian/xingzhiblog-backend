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

    @GetMapping("comment/{blogId}")
    @ApiOperation("根据文章id获取相关评论")
    public ResponseObject getArticleCommentByBlogId(@PathVariable int blogId) {
        ResponseUtil responseUtil = new ResponseUtil();
        List<ArticleCommentVO> articleCommentVOList = articleCommentService.getArticleCommentByBlogId(blogId);
        return responseUtil.success(articleCommentVOList);
    };

    @GetMapping("comment/parent")
    @ApiOperation("添加文章父评论")
    public ResponseObject getArticleCommentByBlogId(String content, int userId, int blogId) {
        ResponseUtil responseUtil = new ResponseUtil();
        Integer status = articleCommentService.addArticleParentComment(content, userId, blogId);
        return responseUtil.success(status);
    };

    @GetMapping("comment/son")
    @ApiOperation("添加文章子评论")
    public ResponseObject getArticleCommentByBlogId(String content, int userId, int blogId, int parentCommentId) {
        ResponseUtil responseUtil = new ResponseUtil();
        Integer status = articleCommentService.addArticleSonComment(content, userId, blogId, parentCommentId);
        return responseUtil.success(status);
    };

}
