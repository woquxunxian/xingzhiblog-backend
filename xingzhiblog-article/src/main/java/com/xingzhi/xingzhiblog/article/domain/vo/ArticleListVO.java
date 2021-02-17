package com.xingzhi.xingzhiblog.article.domain.vo;

import com.xingzhi.xingzhiblog.article.domain.dto.TagDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.util.List;

/**
 * @program: xingzhiblog
 * @description: 文章视图实体
 * @author: 行之
 * @create: 2020-12-28 21:37
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("ArticleListVO")
public class ArticleListVO {
    Integer id;

    String title;

    String image;

    String brief;

    String authorName;

    String authorAvatar;

    Integer likeCount;

    Integer viewCount;

    Integer commentCount;

    List<TagDTO> tagDtoList;

    Date createTime;
}
