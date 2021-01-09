package com.xingzhi.xingzhiblog.controller.tag;

import com.xingzhi.xingzhiblog.domain.vo.ArticleListVO;
import com.xingzhi.xingzhiblog.domain.vo.TagVO;
import com.xingzhi.xingzhiblog.common.result.ResponseObject;
import com.xingzhi.xingzhiblog.common.result.ResponseUtil;
import com.xingzhi.xingzhiblog.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("api/tag")
@Api(tags="标签相关操作")
public class TagController {

    @Autowired
    private TagService blogTagService;

    @GetMapping("all")
    @ApiOperation("获取所有标签信息")
    public ResponseObject getAllTag() {
        ResponseUtil responseUtil = new ResponseUtil();
        List<TagVO> tagVOList = blogTagService.getAllTag();
        return responseUtil.success(tagVOList);
    }

    @GetMapping("search")
    @ApiOperation("通过标签名查询标签")
    public ResponseObject getTagByFuzzyQuery(String tagName) {
        ResponseUtil responseUtil = new ResponseUtil();
        List<TagVO> tagVOList = blogTagService.getTagByFuzzyQuery(tagName);
        return responseUtil.success(tagVOList);
    }

    @GetMapping("article")
    @ApiOperation("通过标签名获取相关文章")
    public ResponseObject getArticleContent(String articleTagName) {
        List<ArticleListVO> articleListVOList = blogTagService.getArticleByTagName(articleTagName);
        ResponseUtil responseUtil = new ResponseUtil();
        return responseUtil.success(articleListVOList);
    }


}
