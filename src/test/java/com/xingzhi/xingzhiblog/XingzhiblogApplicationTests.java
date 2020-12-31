package com.xingzhi.xingzhiblog;

import com.xingzhi.xingzhiblog.dao.article.ArticleDetailMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XingzhiblogApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private ArticleDetailMapper articleDetailMapper;

    @Test
    public void getAllArticleListTest() {
        System.out.println(articleDetailMapper.getALlArticle().toString());
    }

}
