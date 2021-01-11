package com.xingzhi.xingzhiblog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @program: xingzhiblog
 * @description:
 * @author: 行之
 * @create: 2021-01-09 23:48
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("SessionDTO")
public class SessionDTO {
    private String openid;
    private String sessionKey;
}
