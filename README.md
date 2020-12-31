# 行之Blog

## 项目简介

一款博客微信小程序，包括服务后端，微信小程序端，管理后台前端。主要功能有博客内容维护、博主信息维护、分类标签维护、归档时间轴维护等几个模块。后端主要使用Spring Boot + Mybatis进行开发；使用 Jwt + Shiro做登录验证和权限校验；使用ElasticSearch作为检索服务；使用Mysql作为持久化数据库以及Redis缓存中间件。小程序端主要使用原生框架进行开发，使用ColorUI作为UI框架。管理后台前端主要使用......。此项目是我的第一个动手的项目，目前处于开发前期阶段，许多技术也很不熟练，大都是一边写一边学习的，还希望大家多多指教，感谢！

## 项目代码仓库

| github                                                       | gitee                                                    |
| ------------------------------------------------------------ | -------------------------------------------------------- |
| 微信小程序端：https://github.com/woquxunxian/xingzhiblog-miniprogram | 微信小程序端：https://gitee.com/cyyqz/xingzhi-blog-wx    |
| Java后端：https://github.com/woquxunxian/xingzhiblog-backend | Java后端：https://gitee.com/cyyqz/xingzhi-blog-backend   |
| 后台前端：https://github.com/woquxunxian/xingzhiblog-admin   | 微信小程序端：https://gitee.com/cyyqz/xingzhi-blog-admin |

##技术栈

### java后端

- jdk 1.8
- spring boot 2.3.2
- mysql 5.7.26
- redis
- elastic search
- shiro
- jwt

### 微信小程序端

- mina
- color-ui 2.0

### 后台前端

- vue
- element-ui

## 项目意义

## 项目开发全程记录

### 需求分析

<img src="http://qiniupublic.qingpingyue.top/行之Blog.png"/>

目前画的思维导图...应该不是很规范...

### 数据库设计

目前是这样的....仍在完善

<img src="http://qiniupublic.qingpingyue.top/20201231163049.png">

## 代码实现

正在进行....

## 项目展示

### 微信小程序展示

微信小程序端用了大量colorui的样式、组件

| 主页                                                         | 博客简介                                                     | 作者简介                                                     |
| :----------------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| <img src="http://qiniupublic.qingpingyue.top/20201231161800.png"> | <img src="http://qiniupublic.qingpingyue.top/20201231161905.png"/> | <img src="http://qiniupublic.qingpingyue.top/20201231161837.png"/> |
| **分类标签页**                                               | **归档页**                                                   | **作者简介--关于作者**                                       |
| <img src="http://qiniupublic.qingpingyue.top/20201231162109.png"/> | <img src="http://qiniupublic.qingpingyue.top/20201231162130.png"/> | <img src="http://qiniupublic.qingpingyue.top/20201231162030.png"/> |

### 管理后台展示

### 