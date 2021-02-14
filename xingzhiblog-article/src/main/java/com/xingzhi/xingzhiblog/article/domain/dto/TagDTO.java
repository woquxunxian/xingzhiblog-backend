package com.xingzhi.xingzhiblog.article.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @program: xingzhiblog
 * @description: 标签传输实体
 * @author: 行之
 * @create: 2020-12-28 23:18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("TagDTO")
public class TagDTO {
    String tagName;
    String color;
}