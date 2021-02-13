package com.xingzhi.xingzhiblog.info;

import com.xingzhi.xingzhiblog.info.dao.InfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XingzhiblogInfoApplicationTests {

    @Autowired
    private InfoMapper infoMapper;

    @Test
    void InfoMapperTest() {
        System.out.println(infoMapper.getInfo().toString());
    }

    @Test
    void contextLoads() {
    }

}
