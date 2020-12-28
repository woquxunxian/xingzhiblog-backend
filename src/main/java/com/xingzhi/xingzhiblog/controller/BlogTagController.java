package com.xingzhi.xingzhiblog.controller;

import com.xingzhi.xingzhiblog.domain.vo.TagVO;
import com.xingzhi.xingzhiblog.result.ResponseObject;
import com.xingzhi.xingzhiblog.result.ResponseUtil;
import com.xingzhi.xingzhiblog.service.BlogTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 博客标签控制类
 * @author: 行之
 * @create: 2020-12-27 23:50
 **/
@RestController
@RequestMapping("blog/tag")
@Api(tags="博客标签相关操作")
public class BlogTagController {

    @Autowired
    private BlogTagService blogTagService;

    @GetMapping("all")
    @ApiOperation("获取所有标签信息")
    public ResponseObject getAllTag() {
        ResponseUtil responseUtil = new ResponseUtil();
        List<TagVO> tagVOList = blogTagService.getAllTag();
        return responseUtil.success(tagVOList);
    }


}
