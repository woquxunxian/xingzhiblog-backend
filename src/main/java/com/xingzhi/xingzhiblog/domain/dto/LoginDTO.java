package com.xingzhi.xingzhiblog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @program: xingzhiblog
 * @description:
 * @author: 行之
 * @create: 2021-01-09 23:50
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("LoginDTO")
public class LoginDTO {
    private String rawData;
    private String signature;
    private String code;
    private String encryptedData;
    private String iv;
//    private byte[] avatarBase64;
    private String avatarUrl;
    private String nickName;
}
