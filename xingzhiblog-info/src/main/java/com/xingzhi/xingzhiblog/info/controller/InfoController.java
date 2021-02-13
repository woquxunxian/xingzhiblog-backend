package com.xingzhi.xingzhiblog.info.controller;

import com.xingzhi.xingzhiblog.common.result.R;
import com.xingzhi.xingzhiblog.info.domain.vo.InfoVO;
import com.xingzhi.xingzhiblog.info.service.InfoService;
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
@Api(tags = "作者信息管理")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @GetMapping("all")
    @ApiOperation("通过作者信息")
    public R getAllInfoById() {
        InfoVO infoVO = infoService.getInfo();
        return R.ok().put("data", infoVO);
    }
}
