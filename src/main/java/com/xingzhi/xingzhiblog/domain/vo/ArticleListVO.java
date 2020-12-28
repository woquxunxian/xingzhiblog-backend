package com.xingzhi.xingzhiblog.domain.vo;

import com.xingzhi.xingzhiblog.domain.dto.TagDTO;
import com.xingzhi.xingzhiblog.domain.entity.UserLogin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class ArticleListVO {
    Integer id;
    String title;
    String image;
    String brief;
    String userName;
    Integer likeCount;
    Integer viewCount;
    Integer commentCount;
    List<TagDTO> tagDtoList;
    UserLoginVO userLoginVo;
}
