package com.xingzhi.xingzhiblog.article.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @program: xingzhiblog
 * @description: 标签视图类
 * @author: 行之
 * @create: 2020-12-27 23:40
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("TagVO")
public class TagVO {
    String tagName;
    String tagDescription;
    String color;
}