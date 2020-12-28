package com.xingzhi.xingzhiblog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: xingzhiblog
 * @description: 博客信息VO
 * @author: 行之
 * @create: 2020-12-23 16:34
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoVO {
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
}
