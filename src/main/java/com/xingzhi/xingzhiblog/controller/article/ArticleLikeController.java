package com.xingzhi.xingzhiblog.controller.article;

import com.xingzhi.xingzhiblog.common.result.ResponseObject;
import com.xingzhi.xingzhiblog.common.result.ResponseUtil;
import com.xingzhi.xingzhiblog.domain.vo.ArticleDetailVO;
import com.xingzhi.xingzhiblog.domain.vo.ArticleListVO;
import com.xingzhi.xingzhiblog.service.ArticleDetailService;
import com.xingzhi.xingzhiblog.service.ArticleLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 文章点赞相关
 * @author: 行之
 * @create: 2021-01-13 21:27
 **/
@Slf4j
@RestController
@RequestMapping("api/article")
@Api(tags="文章相关操作")
public class ArticleLikeController {

    @Autowired
    private ArticleLikeService articleLikeService;

    @PutMapping("like/number")
    @ApiOperation("通过文章id更新点赞数+1")
    public ResponseObject updateLikeCountByBlogId(@RequestParam("blogId") Integer blogId, @RequestParam("userId") Integer userId) {
        log.info("like---blogId:{}",blogId + "userId:{}",userId);
        Integer updateStatus = articleLikeService.updateLikeCountByBlogId(blogId, userId);
        ResponseUtil responseUtil = new ResponseUtil();
        return responseUtil.success(updateStatus);
    }

    @GetMapping("like/status")
    @ApiOperation("获取用户的点赞状态")
    public ResponseObject getArticleLikeStatusByBlogIdAndUserId(Integer blogId, Integer userId) {
        Integer updateStatus = articleLikeService.getArticleLikeStatusByBlogIdAndUserId(blogId, userId);
        ResponseUtil responseUtil = new ResponseUtil();
        return responseUtil.success(updateStatus);
    }

    @PutMapping("unlike/number")
    @ApiOperation("通过文章id更新点赞数-1")
    public ResponseObject updateMinusLikeCountByBlogId(@RequestParam("blogId") Integer blogId, @RequestParam("userId") Integer userId) {
        log.info("unlike---blogId:{}",blogId + "userId:{}",userId);
        Integer updateStatus = articleLikeService.updateMinusLikeCountByBlogId(blogId, userId);
        ResponseUtil responseUtil = new ResponseUtil();
        return responseUtil.success(updateStatus);
    }

}
