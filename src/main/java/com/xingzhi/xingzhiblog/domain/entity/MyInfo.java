package com.xingzhi.xingzhiblog.domain.entity;

import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @program: xingzhiblog
 * @description: 博客信息实体类
 * @author: 行之
 * @create: 2020-12-23 16:29
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("Info")
public class MyInfo {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String authorName;
    private String authorAvatar;
    private Integer authorAge;
    private String authorIntroduction;
    private String authorSchool;
    private String authorAdress;
    private String authorQrcode;
    private String authorWechatNumber;
    private String authorGiteeLink;
    private String authorGithubLink;
    private String blogName;
    private String blogLogo;
    private String blogIntroduction;
    private String blogGiteeLink;
    private Integer validStatus;
    private DateTime createTime;
    private DateTime updateTime;
}
