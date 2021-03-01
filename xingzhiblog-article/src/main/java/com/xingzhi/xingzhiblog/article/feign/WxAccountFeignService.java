package com.xingzhi.xingzhiblog.article.feign;

import com.xingzhi.xingzhiblog.article.domain.dto.LoginDTO;
import com.xingzhi.xingzhiblog.common.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @program: xingzhiblog
 * @description: 远程调用微信账户Service
 * @author: 行之
 * @create: 2021-02-14 15:50
 **/
@FeignClient("xingzhiblog-user")
public interface WxAccountFeignService {

    /**
     * @Description: 获取微信账户
     * @Param: userId 用户id
     * @return: WxAccountVO 微信账户响应对象
     * @Author: 行之
     */
    @PostMapping("/user/wx/{id}")
    public R getWxUserDataById(@PathVariable(value="id") int id);

    /**
     * @Description: 微信登录
     * @Param:  LoginDTO 登录数据传输对象
     * @return: R
     * @Author: 行之
     */
    @PostMapping("/user/wx/login")
    public R wxLogin(@RequestBody LoginDTO loginDTO);

}
