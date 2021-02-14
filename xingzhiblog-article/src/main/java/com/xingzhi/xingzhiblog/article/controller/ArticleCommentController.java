package com.xingzhi.xingzhiblog.article.controller;

import com.xingzhi.xingzhiblog.article.domain.vo.ArticleCommentVO;
import com.xingzhi.xingzhiblog.article.service.ArticleCommentService;
import com.xingzhi.xingzhiblog.common.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 文章评论
 * @author: 行之
 * @create: 2021-01-02 19:26
 **/
@RestController
@RequestMapping("article")
@Api(tags="文章相关操作")
public class ArticleCommentController {

    @Autowired
    private ArticleCommentService articleCommentService;

    @GetMapping("comment/{blogId}")
    @ApiOperation("根据文章id获取相关评论")
    public R getArticleCommentByBlogId(@PathVariable int blogId) {
        List<ArticleCommentVO> articleCommentVOList = articleCommentService.getArticleCommentByBlogId(blogId);
        return R.ok().put("data", articleCommentVOList);
    };

    @GetMapping("comment/parent")
    @ApiOperation("添加文章父评论")
    public R getArticleCommentByBlogId(String content, int userId, int blogId) {
        Integer status = articleCommentService.addArticleParentComment(content, userId, blogId);
        return R.ok().put("data", status);
    };

    @GetMapping("comment/son")
    @ApiOperation("添加文章子评论")
    public R getArticleCommentByBlogId(String content, int userId, int blogId, int parentCommentId) {
        Integer status = articleCommentService.addArticleSonComment(content, userId, blogId, parentCommentId);
        return R.ok().put("data", status);
    };

}
