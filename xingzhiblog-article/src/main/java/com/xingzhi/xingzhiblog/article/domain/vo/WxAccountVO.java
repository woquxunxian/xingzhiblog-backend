package com.xingzhi.xingzhiblog.article.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @program: xingzhiblog
 * @description: 微信登录返回类
 * @author: 行之
 * @create: 2021-01-10 16:06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("WxAccountVO")
public class WxAccountVO implements Serializable {
    private Integer id;
    private String openId;
    private String unionId;
    private String avatar;
    private String nickName;
}
