package com.xingzhi.xingzhiblog.controller.wx;

import com.xingzhi.xingzhiblog.common.result.ResponseObject;
import com.xingzhi.xingzhiblog.common.result.ResponseUtil;
import com.xingzhi.xingzhiblog.domain.dto.LoginDTO;
import com.xingzhi.xingzhiblog.service.wx.WxAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: xingzhiblog
 * @description: 微信登录
 * @author: 行之
 * @create: 2021-01-10 00:06
 **/
@RestController
@RequestMapping("api/wx")
@Api(tags="微信登录相关操作")
@Slf4j
public class WxAccountController {

    @Autowired
    private WxAccountService wxAccountService;

    @ApiOperation("微信登录")
    @PostMapping("/login")
    public ResponseObject wxLogin(@RequestBody LoginDTO loginDTO){
        ResponseUtil res = new ResponseUtil();
        return res.success(wxAccountService.wxLogin(loginDTO));
    }

    @ApiOperation("通过id获取用户数据")
    @PostMapping("/{id}")
    public ResponseObject wxLogin(@PathVariable int id){
        ResponseUtil res = new ResponseUtil();
        return res.success(wxAccountService.getUserDataById(id));
    }

}
