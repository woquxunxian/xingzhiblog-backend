package com.xingzhi.xingzhiblog.info.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @program: xingzhiblog
 * @description: 博客信息VO
 * @author: 行之
 * @create: 2020-12-23 16:34
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("InfoVO")
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
    private String blogGithubLink;
}
