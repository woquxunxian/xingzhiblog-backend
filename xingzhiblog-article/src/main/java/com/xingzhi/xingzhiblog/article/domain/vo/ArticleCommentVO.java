package com.xingzhi.xingzhiblog.article.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 文章评论视图类
 * @author: 行之
 * @create: 2020-12-31 12:28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("ArticleCommentVO")
public class ArticleCommentVO implements Serializable {
    Integer id;
    Integer userId;
    @ApiModelProperty(value = "评论内容")
    String commentContent;
    Date createTime;
    WxAccountVO wxAccountVO;
    List<ArticleCommentVO> articleCommentVOList;
}