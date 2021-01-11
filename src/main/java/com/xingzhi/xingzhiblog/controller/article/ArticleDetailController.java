package com.xingzhi.xingzhiblog.controller.article;

import com.xingzhi.xingzhiblog.common.result.ResponseObject;
import com.xingzhi.xingzhiblog.common.result.ResponseUtil;
import com.xingzhi.xingzhiblog.domain.vo.ArticleDetailVO;
import com.xingzhi.xingzhiblog.domain.vo.ArticleListVO;
import com.xingzhi.xingzhiblog.service.ArticleDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 文章相关请求控制器
 * @author: 行之
 * @create: 2020-12-30 16:46
 **/
@Slf4j
@RestController
@RequestMapping("api/article")
@Api(tags="文章相关操作")
public class ArticleDetailController {

    @Autowired
    private ArticleDetailService articleDetailService;

    @GetMapping("all")
    @ApiOperation("获取所有文章列表")
    public ResponseObject getAllArticle() {
//        @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize
//        PageHelper.startPage(pageNo, pageSize);
        List<ArticleListVO> articleListVOList = articleDetailService.getAllArticle();
        ResponseUtil responseUtil = new ResponseUtil();
        return responseUtil.success(articleListVOList);
    }

    @GetMapping("content")
    @ApiOperation("通过文章id获取文章内容（包括评论）")
    public ResponseObject getArticleContent(int blogId) {
        ArticleDetailVO articleDetailVO = articleDetailService.getArticleContentByBlogId(blogId);
        ResponseUtil responseUtil = new ResponseUtil();
        return responseUtil.success(articleDetailVO);
    }

    @GetMapping("search")
    @ApiOperation("通过文章标题关键字搜索相关文章")
    public ResponseObject getArticleContent(String articleTitle) {
        List<ArticleListVO> articleListVOList = articleDetailService.getArticleBySearchWithTitle(articleTitle);
        ResponseUtil responseUtil = new ResponseUtil();
        return responseUtil.success(articleListVOList);
    }

    @PutMapping("like/number")
    @ApiOperation("通过文章id更新点赞数+1")
    public ResponseObject updateLikeCountByBlogId(@RequestParam("blogId") Integer blogId, @RequestParam("userId") Integer userId) {
        log.info("like---blogId:{}",blogId + "userId:{}",userId);
        Integer updateStatus = articleDetailService.updateLikeCountByBlogId(blogId, userId);
        ResponseUtil responseUtil = new ResponseUtil();
        return responseUtil.success(updateStatus);
    }

    @GetMapping("like/status")
    @ApiOperation("获取用户的点赞状态")
    public ResponseObject getArticleLikeStatusByBlogIdAndUserId(Integer blogId, Integer userId) {
        Integer updateStatus = articleDetailService.getArticleLikeStatusByBlogIdAndUserId(blogId, userId);
        ResponseUtil responseUtil = new ResponseUtil();
        return responseUtil.success(updateStatus);
    }

    @PutMapping("unlike/number")
    @ApiOperation("通过文章id更新点赞数-1")
    public ResponseObject updateMinusLikeCountByBlogId(@RequestParam("blogId") Integer blogId, @RequestParam("userId") Integer userId) {
        log.info("unlike---blogId:{}",blogId + "userId:{}",userId);
        Integer updateStatus = articleDetailService.updateMinusLikeCountByBlogId(blogId, userId);
        ResponseUtil responseUtil = new ResponseUtil();
        return responseUtil.success(updateStatus);
    }

    @PutMapping("view/number")
    @ApiOperation("通过文章id更新点赞数")
    public ResponseObject updateViewCountByBlogId(Integer blogId) {
        Integer updateStatus = articleDetailService.updateViewCountByBlogId(blogId);
        ResponseUtil responseUtil = new ResponseUtil();
        return responseUtil.success(updateStatus);
    }

}
