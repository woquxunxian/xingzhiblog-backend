package com.xingzhi.xingzhiblog.user.controller;

import com.xingzhi.xingzhiblog.common.result.R;
import com.xingzhi.xingzhiblog.user.domain.dto.LoginDTO;
import com.xingzhi.xingzhiblog.user.service.WxAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: xingzhiblog
 * @description: 微信登录
 * @author: 行之
 * @create: 2021-01-10 00:06
 **/
@RestController
@RequestMapping("user/wx")
@Api(tags="微信登录相关操作")
@Slf4j
public class WxAccountController {

    @Autowired
    private WxAccountService wxAccountService;

    @ApiOperation("微信登录")
    @PostMapping("/login")
    public R wxLogin(@RequestBody LoginDTO loginDTO){
        return R.ok().put("data", wxAccountService.wxLogin(loginDTO));
    }

    @ApiOperation("通过id获取用户数据")
    @PostMapping("/{id}")
    public R getWxUserDataById(@PathVariable int id){
        return R.ok().put("data", wxAccountService.getUserDataById(id));
    }

}
