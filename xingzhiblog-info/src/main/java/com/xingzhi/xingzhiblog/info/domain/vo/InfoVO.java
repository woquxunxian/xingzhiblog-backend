package com.xingzhi.xingzhiblog.info.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @program: xingzhiblog
 * @description: 博客信息响应对象
 * @author: 行之
 * @create: 2020-12-23 16:34
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("InfoVO")
public class InfoVO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String authorName; /** 作者名 */
    private String authorAvatar; /** 作者头像 */
    private Integer authorAge; /** 作者年龄 */
    private String authorIntroduction; /** 作者简绍 */
    private String authorSchool; /** 作者学校 */
    private String authorAdress; /** 作者居住地 */
    private String authorQrcode; /** 作者微信二维码 */
    private String authorWechatNumber; /** 作者微信号 */
    private String authorGiteeLink; /** 作者码云地址 */
    private String authorGithubLink; /** 作者github地址 */
    private String blogName; /** 博客名 */
    private String blogLogo; /** 博客logo */
    private String blogIntroduction; /** 博客简介 */
    private String blogGiteeLink; /** 博客开源代码码云地址 */
    private String blogGithubLink; /** 博客开源代码github地址 */
}
