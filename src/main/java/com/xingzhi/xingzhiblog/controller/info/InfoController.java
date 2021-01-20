package com.xingzhi.xingzhiblog.controller.info;


import com.xingzhi.xingzhiblog.domain.vo.InfoVO;
import com.xingzhi.xingzhiblog.common.result.ResponseObject;
import com.xingzhi.xingzhiblog.common.result.ResponseUtil;
import com.xingzhi.xingzhiblog.service.InfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@RequestMapping("api/info")
@Api(tags = "作者信息管理")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @GetMapping("all")
    @ApiOperation("通过作者信息")
    @RequiresPermissions("/info")
    public ResponseObject getAllInfoById() {
        InfoVO infoVO = infoService.getInfo();
        ResponseUtil res = new ResponseUtil();
        return res.success(infoVO);
    }
}
