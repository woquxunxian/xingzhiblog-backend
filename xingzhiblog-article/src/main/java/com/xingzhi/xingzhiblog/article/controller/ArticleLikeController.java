package com.xingzhi.xingzhiblog.article.controller;

import com.xingzhi.xingzhiblog.article.service.ArticleLikeService;
import com.xingzhi.xingzhiblog.common.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: xingzhiblog
 * @description: 文章点赞相关
 * @author: 行之
 * @create: 2021-01-13 21:27
 **/
@Slf4j
@RestController
@RequestMapping("article")
@Api(tags="文章相关操作")
public class ArticleLikeController {

    @Autowired
    private ArticleLikeService articleLikeService;

    @PutMapping("like/number")
    @ApiOperation("通过文章id更新点赞数+1")
    public R updateLikeCountByBlogId(@RequestParam("blogId") Integer blogId, @RequestParam("userId") Integer userId) {
        log.info("like---blogId:{}",blogId + "userId:{}",userId);
        Integer updateStatus = articleLikeService.updateLikeCountByBlogId(blogId, userId);
        return R.ok().put("data", updateStatus);
    }

    @GetMapping("like/status")
    @ApiOperation("获取用户的点赞状态")
    public R getArticleLikeStatusByBlogIdAndUserId(Integer blogId, Integer userId) {
        Integer updateStatus = articleLikeService.getArticleLikeStatusByBlogIdAndUserId(blogId, userId);
        return R.ok().put("data", updateStatus);
    }

    @PutMapping("unlike/number")
    @ApiOperation("通过文章id更新点赞数-1")
    public R updateMinusLikeCountByBlogId(@RequestParam("blogId") Integer blogId, @RequestParam("userId") Integer userId) {
        log.info("unlike---blogId:{}",blogId + "userId:{}",userId);
        Integer updateStatus = articleLikeService.updateMinusLikeCountByBlogId(blogId, userId);
        return R.ok().put("data", updateStatus);
    }

}
