package com.xingzhi.xingzhiblog.article.controller;

import com.xingzhi.xingzhiblog.article.domain.vo.ArticleListVO;
import com.xingzhi.xingzhiblog.article.domain.vo.TagVO;
import com.xingzhi.xingzhiblog.article.service.TagService;
import com.xingzhi.xingzhiblog.common.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 博客标签控制类
 * @author: 行之
 * @create: 2020-12-27 23:50
 **/
@RestController
@RequestMapping("tag")
@Api(tags="标签相关操作")
public class TagController {

    @Autowired
    private TagService blogTagService;

    @GetMapping("all")
    @ApiOperation("获取所有标签信息")
    public R getAllTag() {
        List<TagVO> tagVOList = blogTagService.getAllTag();
        return R.ok().put("data", tagVOList);
    }

    @GetMapping("search")
    @ApiOperation("通过标签名查询标签")
    public R getTagByFuzzyQuery(String tagName) {
        List<TagVO> tagVOList = blogTagService.getTagByFuzzyQuery(tagName);
        return R.ok().put("data", tagVOList);
    }

    @GetMapping("article")
    @ApiOperation("通过标签名获取相关文章")
    public R getArticleContent(String articleTagName) {
        List<ArticleListVO> articleListVOList = blogTagService.getArticleByTagName(articleTagName);
        return R.ok().put("data", articleListVOList);
    }


}
