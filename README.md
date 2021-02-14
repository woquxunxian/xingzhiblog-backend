# 行之Blog

## 项目简介

一款博客微信小程序，包括 Java 服务端，微信小程序端，vue 管理后台前端。主要功能有博客主体（文章流、文章详细内容、评论、点赞）、博主信息、分类标签、归档时间轴等几个模块。后端主要使用 Spring Boot + Spring Cloud & Alibaba + Mybatis 进行开发；使用 Jwt + Shiro做登录验证和权限校验；使用 ElasticSearch 作为检索服务；使用 Mysql 作为持久化数据库以及 Redis 缓存中间件；使用七牛云 OSS 作为图片存储服务；使用 [renren-fast](https://gitee.com/renrenio/renren-fast) 作为后台服务脚手架。微信小程序端主要使用原生框架进行开发，使用 ColorUI 作为 UI 框架，Wemark 组件进行 Markdown 渲染。管理后台前端主要使用Vue、Element-UI、axios、[renren-fast-vue](https://gitee.com/renrenio/renren-fast-vue)。此项目是我的第一个项目，目前处于开发前期阶段，许多技术也很不熟练，大多是一边写一边学习的，还希望各位多多指教/批评，感谢！o(*////▽////*)q

## 项目体验

目前微信小程序已上线，但是后台还没开发完全，上线目的主要是给自己一点激励...

体验方式：微信搜索小程序“行之Blog”或者微信扫码以下小程序码

![小程序码](http://xingzhi-blog-images.qingpingyue.top/gh_48be86939d75_258.jpg)

**特别说明：**

- 文章详情内容渲染速度有点看手机性能的，如果点击文章进入后内容没有马上显示请稍微等一下

- ElasticSearch目前也没用上，后续迭代的时候会用上...
- Redis也只是一些简单的应用，后续迭代会加深使用

- 小程序端主要功能基本实现，正在思考有没有要完善的地方
- 后台尚未开发完全，注意：master 分支使用的是人人开源的 [renren-fast-vue](https://gitee.com/renrenio/renren-fast-vue) 作为脚手架，而 develop 分支则是自己搭建的 Shiro + Jwt

## 项目代码仓库

| github                                                       | gitee                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------ |
| 微信小程序端：https://github.com/woquxunxian/xingzhiblog-miniprogram | 微信小程序端：https://gitee.com/cyyqz/xingzhi-blog-wx  |
| Java后端：https://github.com/woquxunxian/xingzhiblog-backend | Java后端：https://gitee.com/cyyqz/xingzhi-blog-backend |
| 后台前端：https://github.com/woquxunxian/xingzhiblog-admin   | 后台前端：https://gitee.com/cyyqz/xingzhi-blog-admin   |

## 技术栈

### java后端

- JDK 1.8
- Spring Boot 2.2.1.RELEASE
- Spring Cloud Greenwich.SR3 & Alibaba
- MySQL 5.7.26
- Redis 3.2.100
- Elastic Search 
- Shiro
- Jwt
- 第三方：七牛云

### 微信小程序端

- [MINA](https://developers.weixin.qq.com/miniprogram/dev/framework/)
- [ColorUI 2.0](https://github.com/weilanwl/ColorUI)
- [wemark](https://github.com/TooBug/wemark)

### 后台前端

- Vue
- Element-UI
- [renren-fast-vue](https://gitee.com/renrenio/renren-fast-vue)

## 功能介绍

### 服务端

服务端使用 Java 作为主要编程语言，向微信小程序端（前台）和 Vue后台端提供数据交互服务，使用的技术包括：Spring Boot 进行 Web 搭建和项目主要支持，Spring Cloud & Alibaba 进行微服务搭建，Mybatis 作为数据库ORM框架，Mysql 数据库进行数据持久化，Redis 作为缓存中间件进行读操作的缓存以支持高频的读操作，Elastic Search 作为关键字检索开发框架，Shiro + Jwt 进行权限控制，七牛云 OSS 进行图片存储，Maven 进行项目管理，Tomcat 作为 web 服务器，GIt / GitHub / GItee 进行代码管理。

### 微信小程序

微信小程序作为面向用户的前台，具备文章流显示，文章详细内容显示，文章点赞/取消点赞，文章阅读量显示，文章评论/回复，用户微信登录，博主信息展示，博客归档信息等功能。目前来说功能比较少，因为我的初衷是做一个个人博客，所以后续可能会只进行功能的优化和修改，而不会增加功能（如前台用户系统，收藏等等功能）。由于后续我也会不断地学习新的技术，因此我将把后续学到的技术应用到新的项目中。

### 后台

后台作为面向系统管理员的应用，具备以下功能：

- 主题定制，通过scss变量统一一站式定制
- 动态菜单，通过菜单管理统一管理访问路由
- 数据切换，通过mock配置对接口数据／mock模拟数据进行切换

当然了，以上功能是 renren-fast-vue 自带的 o(*////▽////*)q 

而我基于以上基础，进行二次开发，最后具备以下功能

- 内容管理
  - 文章管理
    - 文章内容管理
    - 评论管理
  - 标签管理
  - 归档管理
- 用户管理
  - 系统管理员管理
  - 前台微信用户管理

## 项目意义

此项目算是我真正意义上的第一个实战项目吧，对我个人而言，可能技术意义大于项目应用意义。项目一开始时也踩了很多坑，到现在，也是跌跌撞撞把一些基础功能实现了，目前的技术栈可能还是比较基础的，后续我也会迭代，把一些陆续学习到的新技术应用到这个项目中，用这个项目来记录自己的成长。

## 开发过程

### 需求分析

<img src="http://qiniupublic.qingpingyue.top/行之Blog.png"/>

目前画的思维导图...应该不是很规范...

## 项目展示

### 微信小程序展示

微信小程序端用了 ColorUI 的样式、组件以及 wemark 的 markdown 渲染

| 主页（1）                                                    | 主页（2）                                                    | **文章详情（1）**                                            | **文章详情（2）**                                            |
| :----------------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| ![](http://xingzhi-blog-images.qingpingyue.top/b4b6baedf4530c3a345ec7368e3c62c.jpg) | ![](http://xingzhi-blog-images.qingpingyue.top/ff7cb27d3bbdcf1963b08233b479068.jpg) | ![](http://xingzhi-blog-images.qingpingyue.top/826f628cf4fcf3ade0cf15efcee8894.jpg) | ![](http://xingzhi-blog-images.qingpingyue.top/fc418fb2e2fdb1817967ae48fe88d7b.jpg) |
| **分类标签页**                                               | **归档页**                                                   | **作者简介（抽屉式）**                                       | **评论区**                                                   |
| ![](http://xingzhi-blog-images.qingpingyue.top/9eb3a254821a305b42a5efae87fe009.jpg) | ![](http://xingzhi-blog-images.qingpingyue.top/7797566ec43526ff34baf032c3deef3.jpg) | ![](http://xingzhi-blog-images.qingpingyue.top/57b70cb65bb95e0f40e7214c201ae48.jpg) | ![](http://xingzhi-blog-images.qingpingyue.top/a5c3e2c12b90da3f48e4e9924aac6e3.jpg) |

### 管理后台展示

**正在努力开发中....**