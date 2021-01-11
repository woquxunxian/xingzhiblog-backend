package com.xingzhi.xingzhiblog.common.wx;

import com.alibaba.fastjson.JSON;
import com.xingzhi.xingzhiblog.domain.dto.SessionDTO;
import com.xingzhi.xingzhiblog.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @program: xingzhiblog
 * @description: 获取微信session_key
 * @author: 行之
 * @create: 2021-01-09 23:43
 **/
@Slf4j
@Component
public class WxAdapter {

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.secret}")
    private String secret;


    public SessionDTO jscode2session(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
        OkHttpClient okHttpClient = new OkHttpClient();
        log.info("appid:{}",appid + "secret:{}",secret);
        Request request = new Request.Builder()
                .addHeader("content-type", "application/json")
                .url(String.format(url, appid, secret, code))
                .build();
        try {
            Response execute = okHttpClient.newCall(request).execute();
            if (execute.isSuccessful()) {
                SessionDTO sessionDTO = JSON.parseObject(execute.body().string(), SessionDTO.class);
                return sessionDTO;
            } else {
                throw new SystemException("系统错误！");
            }

        } catch (IOException e) {
            throw new SystemException("系统错误！");
        }
    }
}
