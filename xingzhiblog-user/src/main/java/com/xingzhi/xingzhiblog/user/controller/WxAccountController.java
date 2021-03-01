package com.xingzhi.xingzhiblog.user.controller;

import com.xingzhi.xingzhiblog.common.base.domain.dto.LoginDTO;
import com.xingzhi.xingzhiblog.common.base.service.WxAccountService;
import com.xingzhi.xingzhiblog.common.result.R;
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
@RequestMapping("user/wx")
@Api(tags="微信登录相关操作")
@Slf4j
public class WxAccountController {

    @Autowired
    private WxAccountService wxAccountService; /** 微信账号操作相关Service */

    /**
    * @Description: 微信登录
    * @Param:  LoginDTO 登录数据传输对象
    * @return: R
    * @Author: 行之
    */
    @ApiOperation("微信登录")
    @PostMapping("/login")
    public R wxLogin(@RequestBody LoginDTO loginDTO){
        return R.ok().put("data", wxAccountService.wxLogin(loginDTO));
    }

    /**
    * @Description: 通过用户id获取相应用户数据
    * @Param:  id 用户id
    * @return:  R
    * @Author: 行之
    */
    @ApiOperation("通过id获取用户数据")
    @PostMapping("/{id}")
    public R getWxUserDataById(@PathVariable int id){
        return R.ok().put("data", wxAccountService.getUserDataById(id));
    }

}
