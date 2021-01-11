package com.xingzhi.xingzhiblog.common.util;

import cn.hutool.core.util.StrUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.Base64;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;

/**
 * @program: xingzhiblog
 * @description: 七牛云上传图片配置
 * @author: 行之
 * @create: 2021-01-10 19:45
 **/
@Slf4j
public class QiNiuCloudImgUtil {

    private static final String ACCESS_KEY = "***-7jHs9gXA4HvgC7jBH6Pbuhs5XM_9yTzj";
    private static final String SECRET_KEY = "*****";

 
    private static final String bucketName = "xingzhi-blog-images";

    // 密钥
    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    private static final String DOMAIN = "xingzhi-blog-images.qingpingyue.top";

//    private static final String style = "自定义的图片样式";

    public String getUpToken() {
        return auth.uploadToken(bucketName, null, 3600, new StringMap().put("insertOnly", 1));
    }

    // 普通上传
    public String upload(String filePath, String fileName) throws IOException {
        UploadManager uploadManager = new UploadManager();
        try {
            String token = auth.uploadToken(bucketName);
            if(StrUtil.isEmpty(token)) {
                System.out.println("未获取到token");
                return null;
            }
            Response res = uploadManager.put(filePath, fileName, token);
            System.out.println(res.bodyString());
            if (res.isOK()) {
                Ret ret = res.jsonToObject(Ret.class);
                return DOMAIN + ret.key;
//                return DOMAIN + ret.key + "?" + style;
            }
        } catch (QiniuException e) {
            Response r = e.response;
            System.out.println(r.toString());
            try {
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
            }
        }
        return null;
    }


    //base64方式上传
    public String put64image(byte[] base64, String key) throws Exception{
        String file64 = Base64.encodeToString(base64, 0);
        Integer l = base64.length;
        String url = "http://upload.qiniu.com/putb64/" + l + "/key/"+ UrlSafeBase64.encodeToString(key);
        RequestBody rb = RequestBody.create(null, file64);
        Request request = new Request.Builder().
                url(url).
                addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + getUpToken())
                .post(rb).build();
        OkHttpClient client = new OkHttpClient();
        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response);
        return "http://" + DOMAIN + "/" + key;
//        return DOMAIN + key + "?" + style;
    }


    // 普通删除
    public void delete(String key) throws IOException {
        // 实例化一个BucketManager对象
        BucketManager bucketManager = new BucketManager(auth);
        // 此处的33是去掉：http://ongsua0j7.bkt.clouddn.com/,剩下的key就是图片在七牛云的名称
        key = key.substring(33);
        try {
            // 调用delete方法移动文件
            bucketManager.delete(bucketName, key);
        } catch (QiniuException e) {
            // 捕获异常信息
            Response r = e.response;
            System.out.println(r.toString());
        }
    }

    class Ret {
        public long fsize;
        public String key;
        public String hash;
        public int width;
        public int height;
    }
}
