package com.xingzhi.xingzhiblog.user.service;


import com.xingzhi.xingzhiblog.user.domain.dto.LoginDTO;
import com.xingzhi.xingzhiblog.user.domain.vo.WxAccountVO;

/**
 * @program: xingzhiblog
 * @description: 微信登录相关
 *
 *                  登录逻辑如下：（也许只适用于本项目）
 *  服务端：
 *      接收到登录请求时:
 *      1、接收小程序端传过来的用户基本信息和加密信息，解密加密信息
 *      2、得到UnionID，判断UnionID是否存在
 *          - 如果存在，则直接返回用户id
 *          - 如果不存在，则把用户微信头像、昵称、UnionID存入数据库，得到mysql自增主键
 *      3、返回id和UnionID给小程序端
 *      接收到需要登陆的业务请求时：
 *      1、接收到用户id，用id去进行业务操作
 *      2、返回响应数据
 *
 *  微信小程序端：
 *      1、获取用户基本信息和加密信息
 *      2、把得到的数据发送给后端解密
 *      3、接收后端传回来的数据库自增id和微信生态的UnionID，存到缓存
 *      4、之后的每次使用到要登陆的业务会有两种情况：
 *          - 缓存的id还在，用缓存的id去请求业务
 *          - 缓存的id不在了，重新请求获取id
 *
 * @author: 行之
 * @create: 2021-01-09 23:17
 **/
public interface WxAccountService {

    WxAccountVO wxLogin(LoginDTO loginDTO);

    WxAccountVO getUserDataById(int id);
}
