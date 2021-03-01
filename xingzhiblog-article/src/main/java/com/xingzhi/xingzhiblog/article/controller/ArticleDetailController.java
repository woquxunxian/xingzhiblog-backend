package com.xingzhi.xingzhiblog.article.controller;

import com.xingzhi.xingzhiblog.article.domain.vo.ArticleDetailVO;
import com.xingzhi.xingzhiblog.article.domain.vo.ArticleListVO;
import com.xingzhi.xingzhiblog.article.service.ArticleDetailService;
import com.xingzhi.xingzhiblog.common.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 文章相关请求控制器
 * @author: 行之
 * @create: 2020-12-30 16:46
 **/
@Slf4j
@RestController
@RequestMapping("article")
@Api(tags="文章相关操作")
public class ArticleDetailController {

    @Autowired
    private ArticleDetailService articleDetailService;

    /**
     * @Description: 获取文章信息流列表
     * @Param: null
     * @return: R
     * @Author: 行之
     */
    @GetMapping("all")
    @ApiOperation("获取所有文章列表")
    public R getAllArticle() {
//        @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize
//        PageHelper.startPage(pageNo, pageSize);
        List<ArticleListVO> articleListVOList = articleDetailService.getAllArticle();
        return R.ok().put("data", articleListVOList);
    }

    /**
     * @Description: 获取文章详情内容
     * @Param: blogId 博客id
     * @return: R
     * @Author: 行之
     */
    @GetMapping("content")
    @ApiOperation("通过文章id获取文章内容（包括评论）")
    public R getArticleContent(int blogId) {
        ArticleDetailVO articleDetailVO = articleDetailService.getArticleContentByBlogId(blogId);
        return R.ok().put("data", articleDetailVO);
    }

    /**
     * @Description: 搜索相关文章
     * @Param: articleTitle 文章标题
     * @return: R
     * @Author: 行之
     */
    @GetMapping("search")
    @ApiOperation("通过文章标题关键字搜索相关文章")
    public R getArticleContent(String articleTitle) {
        List<ArticleListVO> articleListVOList = articleDetailService.getArticleBySearchWithTitle(articleTitle);
        return R.ok().put("data", articleListVOList);
    }

    /**
     * @Description: 更新文章阅读量
     * @Param: blogId 博客id
     * @return: R
     * @Author: 行之
     */
    @PutMapping("view/number")
    @ApiOperation("通过文章id更新阅读量")
    public R updateViewCountByBlogId(Integer blogId) {
        Integer updateStatus = articleDetailService.updateViewCountByBlogId(blogId);
        return R.ok().put("data", updateStatus);
    }

}
