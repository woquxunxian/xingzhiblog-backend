# 行之Blog

## 项目简介

一款博客微信小程序，包括java后端，微信小程序端，管理后台前端。主要功能有博客内容（文章列表、文章内容、评论、点赞）、博主信息、分类标签、归档时间轴等几个模块。后端主要使用Spring Boot + Mybatis进行开发；使用 Jwt + Shiro做登录验证和权限校验；使用ElasticSearch作为检索服务；使用Mysql作为持久化数据库以及Redis缓存中间件。小程序端主要使用原生框架进行开发，使用ColorUI作为UI框架，wemark组件进行markdown渲染。管理后台前端主要使用Vue、element-ui、axios。此项目是我的第一个动手的项目，目前处于开发前期阶段，许多技术也很不熟练，大多是一边写一边学习的，还希望大家多多指教，感谢！

## 特别说明

目前微信小程序已上线，但是有部分功能未开发完全，上线目的主要是给自己一点激励...

体验方式：微信搜索小程序“行之Blog”或者微信扫码以下小程序码

![小程序码](http://xingzhi-blog-images.qingpingyue.top/gh_48be86939d75_258.jpg)

**特别说明：**

目前尚未完成的功能：

-  Jwt + Shiro目前还未用上...
- ElasticSearch目前也没用上，后续迭代的时候会用上...
- Redis也只是一些简单的应用，后续迭代会加深使用

- 小程序端目前尚未完成评论和点赞、收藏、用户系统的真正实现（主要是我不知道有评论后，个人小程序还能不能上线了...）
- 后台还未开发（最近要考试啦，要复习了(╯‵□′)╯︵┻━┻）

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
- redis 3.2.100
- elastic search 
- shiro
- jwt

### 微信小程序端

- [MINA](https://developers.weixin.qq.com/miniprogram/dev/framework/)
- [color-ui 2.0](https://github.com/weilanwl/ColorUI)
- [wemark](https://github.com/TooBug/wemark)

### 后台前端

- vue
- element-ui

## 项目意义

此项目算是我真正意义上的第一个实战项目把，对我个人而言，可能技术意义大于项目应用意义。项目一开始时也踩了很多坑，到现在，也是跌跌撞撞把一些基础功能实现了，目前的技术栈可能还是比较基础的，后续我也会迭代，把一些陆续学习到的新技术应用到这个项目中，用这个项目来记录自己的成长。

## 开发过程

### 需求分析

<img src="http://qiniupublic.qingpingyue.top/行之Blog.png"/>

目前画的思维导图...应该不是很规范...

### 数据库设计

仍在完善...具体见后端仓库的sql文件夹，完善后会放ER图

### 代码实现

正在进行....

## 项目展示

### 微信小程序展示

微信小程序端用了大量colorui的样式、组件以及wemark的markdown渲染

| 主页（1）                                                    | 主页（2）                                                    | 作者简介（抽屉式）                                           | 博客简介（抽屉式）                                           |
| :----------------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| ![](http://xingzhi-blog-images.qingpingyue.top/b4b6baedf4530c3a345ec7368e3c62c.jpg) | ![](http://xingzhi-blog-images.qingpingyue.top/ff7cb27d3bbdcf1963b08233b479068.jpg) | ![](http://xingzhi-blog-images.qingpingyue.top/57b70cb65bb95e0f40e7214c201ae48.jpg) | ![](http://xingzhi-blog-images.qingpingyue.top/983be3c649fe857560f7c3a6e34af48.jpg) |
| **分类标签页**                                               | **归档页**                                                   | **文章详情（1）**                                            | **文章详情（2）**                                            |
| ![](http://xingzhi-blog-images.qingpingyue.top/9eb3a254821a305b42a5efae87fe009.jpg) | ![](http://xingzhi-blog-images.qingpingyue.top/7797566ec43526ff34baf032c3deef3.jpg) | ![](http://xingzhi-blog-images.qingpingyue.top/826f628cf4fcf3ade0cf15efcee8894.jpg) | ![](http://xingzhi-blog-images.qingpingyue.top/fc418fb2e2fdb1817967ae48fe88d7b.jpg) |

### 管理后台展示

**正在努力开发中....**