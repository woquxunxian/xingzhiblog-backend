package com.xingzhi.xingzhiblog.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: xingzhiblog
 * @description: 错误码枚举类
 * @author: 行之
 * @create: 2020-12-20 20:37
 **/
public enum ResultCode {
    SUCESS(200, "请求成功"),
    NO_CONTENT(204, "请求无内容"),
    USER_NOT_LOGIN(401, "用户未登录"),
    USER_LOGIN_FAIL(402, "用户登录失败"),
    USER_ACCESS_FORBIDDEN(403, "用户授权失败"),
    NOT_FOUND(404, "资源不存在"),
    INTERNAL_ERROR(500, "服务器错误"),
    SYSTEM_ERROR(501, "系统繁忙"),
    BUSINESS_SUCESS(1, "数据正常返回"),
    UNKNOWN(-1, "系统异常"),
    BUSINESS_FAILURE(-2, "业务异常"),
    INVALID_ARGUMENT(11001, "非法参数");

    private static final Map<Integer, ResultCode> values = new HashMap();
    private int errorCode;
    private String error;

    private ResultCode(int errorCode, String error) {
        this.errorCode = errorCode;
        this.error = error;
    }

    public static ResultCode valueOf(int code) {
        ResultCode ec = (ResultCode)values.get(Integer.valueOf(code));
        return ec != null ? ec : UNKNOWN;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getError() {
        return this.error;
    }

    static {
        ResultCode[] var0 = values();
        int var1 = var0.length;
        for(int var2 = 0; var2 < var1; ++var2) {
            ResultCode ec = var0[var2];
            values.put(Integer.valueOf(ec.errorCode), ec);
        }
    }
}
