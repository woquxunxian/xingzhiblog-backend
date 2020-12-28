package com.xingzhi.xingzhiblog.controller;


import com.xingzhi.xingzhiblog.domain.vo.InfoVO;
import com.xingzhi.xingzhiblog.result.ResponseObject;
import com.xingzhi.xingzhiblog.result.ResponseUtil;
import com.xingzhi.xingzhiblog.service.InfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @program: xingzhiblog
 * @description:
 * @author: 行之
 * @create: 2020-12-23 17:24
 **/
@RestController
@RequestMapping("info")
@Api(tags = "用户、博客信息管理")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @GetMapping("blog")
    @ApiOperation("通过id获取所有博客、个人信息")
    public ResponseObject getAllInfoById(int id) {
        InfoVO infoVO = infoService.getAllInfoById(id);
        ResponseUtil res = new ResponseUtil();
        return res.success(infoVO);
    }
}
