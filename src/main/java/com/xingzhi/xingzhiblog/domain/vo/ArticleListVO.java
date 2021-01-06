package com.xingzhi.xingzhiblog.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xingzhi.xingzhiblog.domain.dto.TagDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

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

    Integer likeCount;

    Integer viewCount;

    Integer commentCount;

    List<TagDTO> tagDtoList;

    UserListVO userListVo;

    Date createTime;
}
