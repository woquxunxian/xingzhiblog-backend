package com.xingzhi.xingzhiblog.common.result;

/**
 * @program: xingzhiblog
 * @description: 统一结果返回方法封装
 * @author: 行之
 * @create: 2020-12-23 17:29
 **/
public class ResponseUtil<T> {

    public ResponseObject success() {
        ResponseObject res = new ResponseObject();
        res.setCode(ResultCode.BUSINESS_SUCESS.getErrorCode());
        res.setMsg("获取成功");
        return res;
    }

    public ResponseObject success(T data) {
        ResponseObject res = success();
        res.setData(data);
        return res;
    }

    public ResponseObject success(T data, String msg) {
        ResponseObject res = success(data);
        res.setMsg(msg);
        return res;
    }

    public ResponseObject failed() {
        ResponseObject res = new ResponseObject();
        res.setCode(ResultCode.BUSINESS_FAILURE.getErrorCode());
        res.setMsg("获取失败");
        res.setData(null);
        return res;
    }

    public ResponseObject failed(String msg) {
        ResponseObject res = failed();
        res.setMsg(msg);
        res.setData(null);
        return res;
    }

    public ResponseObject failed(T data, String msg) {
        ResponseObject res = failed(msg);
        res.setData(data);
        return res;
    }


    public ResponseObject custom(int code, T data, String msg) {
        return new ResponseObject(code, data, msg);
    }

}
