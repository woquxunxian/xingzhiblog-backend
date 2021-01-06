package com.xingzhi.xingzhiblog.controller.user;

import com.xingzhi.xingzhiblog.common.result.ResponseObject;
import com.xingzhi.xingzhiblog.common.result.ResponseUtil;
import com.xingzhi.xingzhiblog.domain.vo.UserLoginVO;
import com.xingzhi.xingzhiblog.service.UserLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: xingzhiblog
 * @description: 用户相关
 * @author: 行之
 * @create: 2021-01-01 20:32
 **/
@RestController
@RequestMapping("api/user")
@Api(tags="用户相关操作")
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResponseObject UserLogin(UserLoginVO userLoginVO) {
        ResponseUtil responseUtil = new ResponseUtil();
        return responseUtil.success(userLoginService.userLogin(userLoginVO));
    }

}
