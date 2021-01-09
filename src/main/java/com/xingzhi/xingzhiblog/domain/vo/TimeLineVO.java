package com.xingzhi.xingzhiblog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @program: xingzhiblog
 * @description: 小程序-归档查询请求返回对象
 * @author: 行之
 * @create: 2021-01-06 23:49
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("TimeLineVO")
public class TimeLineVO {
    String date;
    Integer count;
}
