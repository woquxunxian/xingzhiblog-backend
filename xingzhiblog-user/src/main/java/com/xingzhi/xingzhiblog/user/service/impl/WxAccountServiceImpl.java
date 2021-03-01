package com.xingzhi.xingzhiblog.user.service.impl;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xingzhi.xingzhiblog.common.base.domain.dto.LoginDTO;
import com.xingzhi.xingzhiblog.common.base.service.WxAccountService;
import com.xingzhi.xingzhiblog.common.exception.SystemException;
import com.xingzhi.xingzhiblog.user.config.util.AesCbcUtil;
import com.xingzhi.xingzhiblog.user.config.util.QiNiuCloudImgUtil;
import com.xingzhi.xingzhiblog.user.config.wx.WxAdapter;
import com.xingzhi.xingzhiblog.user.dao.WxAccountMapper;
import com.xingzhi.xingzhiblog.common.base.domain.vo.WxAccountVO;
import com.xingzhi.xingzhiblog.common.base.domain.dto.SessionDTO;
import lombok.extern.slf4j.Slf4j;
//import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: xingzhiblog
 * @description: 微信登录相关
 * @author: 行之
 * @create: 2021-01-09 23:18
 **/
@Slf4j
//@Service
@Component
@Transactional
public class WxAccountServiceImpl implements WxAccountService {
    
    @Autowired
    private WxAdapter wxAdapter;

    @Autowired
    private WxAccountMapper wxAccountMapper;
    
    /**
    * @Description: 微信登录
    *  步骤说明：
     *   1、首先获取用户的名字和openid以及图片的路径，我原本以为小程序端获取到头像图片路径
     *      是临时路径，所以就蒙着头把图片上传到七牛云的代码给写了，写完之后突然想起应该先
     *      查查其是不是临时路径，结果一看，还真不是...裂开了，算了，先用微信自己的吧
     *      不过貌似用户换了头像后之前的地址就不行了
     *   2、用openID去查用户是否已经存在数据库中了，如果存在的话就直接查出来返回数据，未存
     *      在的话就插入数据并返回数据
    * @Param: LoginDTO 登陆数据传输对象
    * @return: WxAccountVO 微信账户响应对象
    * @Author: 行之
    */
    @Override
    @Transactional
    public WxAccountVO wxLogin(LoginDTO loginDTO){
        //获取用户昵称和openid和临时头像路径
        Map<String, String> userInfo = getUserWxLoginData(loginDTO);
        String nickName = userInfo.get("nickName");
//        byte[] avatarBase64 = loginDTO.getAvatarBase64();
//        String avatarUrl = upLoadImgToQiNiuCloud(avatarBase64);
        String avatarUrl = userInfo.get("avatarUrl");
        String openId = userInfo.get("openId");

        WxAccountVO wxAccountVO = getWxAccountByOpenId(openId);
        log.info("wxLoginVO:{}", wxAccountVO);
        if (wxAccountVO == null) {
            wxAccountVO = new WxAccountVO();
            wxAccountVO.setAvatar(avatarUrl);
            wxAccountVO.setNickName(nickName);
            wxAccountVO.setOpenId(openId);
            wxAccountVO.setUnionId("null");
//            Integer id = wxAccountMapper.addWxLoginAccount(avatarUrl, nickName, openId, "null");
            Integer id = wxAccountMapper.addWxLoginAccount(wxAccountVO);
            log.info("id:{}",id);
            if (id == 1) return wxAccountVO;
//            if (id == null) throw new SystemException("系统出错");
//            wxAccountVO = new WxAccountVO();
//            wxAccountVO.setAvatar(nickName);
//            wxAccountVO.setNickName(avatarUrl);
//            wxAccountVO.setOpenId(openId);
//            wxAccountVO.setId(id);
        }
        return wxAccountVO;
    }


    /**
     * @Description: 通过主键id获取微信账户数据
     * @Param: id 记录主键id
     * @return: WxAccountVO 微信账户响应对象
     * @Author: 行之
     */
    @Override
    public WxAccountVO getUserDataById(int id) {
        return wxAccountMapper.getWxAccountById(id);
    }

    /**
    * @Description: 获取用户数据，但是UnionID没获取到...不知道为啥，后续再看看
    * @Param: LoginDTO null
    * @return: Map<String, String> 用户数据map
    * @Author: 行之
    */
    private Map<String, String> getUserWxLoginData(LoginDTO loginDTO) {
        Map<String, String> userInfo = new HashMap<>();
        try{
            SessionDTO sessionDTO = wxAdapter.jscode2session(loginDTO.getCode());
            String result = AesCbcUtil.decrypt(loginDTO.getEncryptedData(), sessionDTO.getSessionKey() ,loginDTO.getIv(), "UTF-8");
            if (null != result && result.length() > 0) {
                JSONObject userInfoJSON = JSON.parseObject(result);
                userInfo.put("openId", String.valueOf(userInfoJSON.get("openId")));
                userInfo.put("nickName", String.valueOf(userInfoJSON.get("nickName")));
                userInfo.put("gender", String.valueOf(userInfoJSON.get("gender")));
                userInfo.put("city", String.valueOf(userInfoJSON.get("city")));
                userInfo.put("province", String.valueOf(userInfoJSON.get("province")));
                userInfo.put("country", String.valueOf(userInfoJSON.get("country")));
                userInfo.put("avatarUrl", String.valueOf(userInfoJSON.get("avatarUrl")));
                userInfo.put("unionId", String.valueOf(userInfoJSON.get("unionId")));
            } else {
                throw new SystemException("系统错误");
            }
            return userInfo;
        }catch (SystemException e){
            throw new SystemException("系统错误");
        }catch (Exception e){
            throw new SystemException("系统错误");
        }
    }

    /**
     * @Description: 通过OpenId获取微信账户数据
     * @Param: openId 账户在该微信小程序的openid
     * @return: WxAccountVO 微信账户响应对象
     * @Author: 行之
     */
    private WxAccountVO getWxAccountByOpenId(String OpenId) {
        WxAccountVO wxAccountVO = wxAccountMapper.getWxAccountByOpenId(OpenId);
        try {
            Integer id = wxAccountVO.getId();
            return wxAccountVO;
        } catch (NullPointerException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
    * @Description: 上传图片到七牛云OSS
    * @Param: imgBase64 base64编码的图片
    * @return: String 上传后的图片url
    * @Author: 行之
    */
    public String upLoadImgToQiNiuCloud(byte[] imgBase64) {
        String imageName = UUID.randomUUID().toString();
        QiNiuCloudImgUtil qiniuUtil = new QiNiuCloudImgUtil();
        try {
            //使用base64方式上传到七牛云
            String qiNiuCloudImgUrl = qiniuUtil.put64image(imgBase64, imageName);
            log.info("qiNiuCloudImgUrl:{}",qiNiuCloudImgUrl);
            //使用url方式上传到七牛云
//            String qiNiuCloudImgUrl = qiniuUtil.upload(temImgUrl, imageName);
            return qiNiuCloudImgUrl;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误");
        }
    }

}
