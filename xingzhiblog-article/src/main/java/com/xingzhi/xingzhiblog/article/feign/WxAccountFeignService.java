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

    @PostMapping("/user/wx/{id}")
    public R getWxUserDataById(@PathVariable(value="id") int id);

    @PostMapping("/user/wx/login")
    public R wxLogin(@RequestBody LoginDTO loginDTO);

}
