package com.xingzhi.xingzhiblog.result;

import java.util.Arrays;

/**
 * @program: xingzhiblog
 * @description: 请求参数
 * @author: 行之
 * @create: 2020-12-27 15:36
 */
public class RequestLog {
    private String url;
    private String ip;
    private String classMethod;
    private Object[] args;

    public RequestLog(String url, String ip, String classMethod, Object[] args) {
        this.url = url;
        this.ip = ip;
        this.classMethod = classMethod;
        this.args = args;
    }

    @Override
    public String toString() {
        return "{" +
                "url='" + url + '\'' +
                ", ip='" + ip + '\'' +
                ", classMethod='" + classMethod + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
