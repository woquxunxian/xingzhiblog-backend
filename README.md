# 行之Blog

## 项目简介

一款博客微信小程序，包括 Java 服务端，微信小程序端，管理后台。主要功能有博客主体（首页卡片流、文章详细内容阅读、文章评论、文章点赞）、博主信息、分类标签、归档时间轴等几个模块。后端主要使用 Spring Boot + Spring Cloud & Alibaba + Mybatis 进行web开发；使用 Mysql 作为持久化数据库以及 Redis作为 缓存中间件、RabbitMQ作为消息中间件；使用 ElasticSearch 作为检索服务（es刚学会，还未集成到项目）；使用七牛云 OSS 作为图片存储服务；使用 [renren-fast](https://gitee.com/renrenio/renren-fast) 作为后台服务脚手架。微信小程序端主要使用原生框架进行开发，使用 ColorUI 作为 UI 框架，Wemark 组件进行 Markdown 渲染。管理后台前端主要使用Vue、Element-UI、axios、[renren-fast-vue](https://gitee.com/renrenio/renren-fast-vue)。

## 项目体验

目前微信小程序已上线，欢迎围观 ~

体验方式：微信搜索小程序“行之Blog”或者微信扫码以下小程序码

![小程序码](http://xingzhi-blog-images.qingpingyue.top/gh_48be86939d75_258.jpg)

**特别说明：**

- 文章详情内容渲染速度有点看手机性能的，如果点击文章进入后内容没有马上显示请稍微等一下

- ElasticSearch 目前还未用到项目上，后续迭代的时候会用上（用于文章检索、标签检索等）
- 管理后台目前还处于待开发状态

## 项目代码仓库

| github                                                       | gitee                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------ |
| 微信小程序端：https://github.com/woquxunxian/xingzhiblog-miniprogram | 微信小程序端：https://gitee.com/cyyqz/xingzhi-blog-wx  |
| Java后端：https://github.com/woquxunxian/xingzhiblog-backend | Java后端：https://gitee.com/cyyqz/xingzhi-blog-backend |
| 后台前端：https://github.com/woquxunxian/xingzhiblog-admin   | 后台前端：https://gitee.com/cyyqz/xingzhi-blog-admin   |

## 技术介绍

### 技术特色

- 文章详情页采用 CompletableFuture 异步编排的方式获取详情页的内容数据，提升接口响应速度
- 点赞和阅读量采用异步写入的方式
- 采用了 Redisson 作为分布式锁的解决方案，以及 Seata 作为分布式事务的解决方案
- 目前是用的 develop 分支部署的，master 分支目前还没完全优化完
- 下阶段计划：集成 ElasticSearch 构建搜索服务，分布式部署 master 分支，Nginx 负载均衡

### 技术栈
#### Java服务端

- JDK 1.8
- Spring Boot 2.2.1.RELEASE
- Spring Cloud Greenwich.SR3 & Alibaba
- MySQL 5.7.26
- Redis 3.2.100
- RabbitMQ 3.8.14
- Elastic Search (待开发)
- oss：七牛云

#### 微信小程序端

- [MINA](https://developers.weixin.qq.com/miniprogram/dev/framework/)
- [ColorUI 2.0](https://github.com/weilanwl/ColorUI)
- [wemark](https://github.com/TooBug/wemark)

## 功能介绍

### 服务端

服务端使用 Java 作为主要编程语言，向微信小程序端（前台）和 Vue后台端提供数据交互服务，使用的技术包括：Spring Boot 进行 Web 搭建和项目主要支持，Spring Cloud & Alibaba 进行微服务搭建，Mybatis 作为数据库ORM框架，Mysql 数据库进行数据持久化，Redis 作为缓存中间件进行读操作的缓存以支持高频的读操作，使用RabbitMQ作为消息中间件进行了一些异步写入的操作，Elastic Search 进行检索服务的搭建，七牛云 OSS 进行图片存储，Maven 进行项目管理，Tomcat 作为 web 服务器，GIt / GitHub / GItee 进行代码管理。

### 微信小程序

微信小程序作为面向用户的前台，具备文章流显示，文章详细内容显示，文章点赞/取消点赞，文章阅读量显示，文章评论/回复，用户微信登录，博主信息展示，博客归档信息等功能。目前来说功能比较少，因为我的初衷只是做一个非常简单的个人博客，所以后续可能会只进行功能的优化和修改，而不会增加功能。

## 项目展示

### 微信小程序展示

微信小程序端用了 ColorUI 的样式、组件以及 wemark 的 markdown 渲染

| 主页（1）                                                    | 主页（2）                                                    | **文章详情（1）**                                            | **文章详情（2）**                                            |
| :----------------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| ![](http://xingzhi-blog-images.qingpingyue.top/b4b6baedf4530c3a345ec7368e3c62c.jpg) | ![](http://xingzhi-blog-images.qingpingyue.top/ff7cb27d3bbdcf1963b08233b479068.jpg) | ![](http://xingzhi-blog-images.qingpingyue.top/826f628cf4fcf3ade0cf15efcee8894.jpg) | ![](http://xingzhi-blog-images.qingpingyue.top/fc418fb2e2fdb1817967ae48fe88d7b.jpg) |
| **分类标签页**                                               | **归档页**                                                   | **作者简介（抽屉式）**                                       | **评论区**                                                   |
| ![](http://xingzhi-blog-images.qingpingyue.top/9eb3a254821a305b42a5efae87fe009.jpg) | ![](http://xingzhi-blog-images.qingpingyue.top/7797566ec43526ff34baf032c3deef3.jpg) | ![](http://xingzhi-blog-images.qingpingyue.top/57b70cb65bb95e0f40e7214c201ae48.jpg) | ![](http://xingzhi-blog-images.qingpingyue.top/a5c3e2c12b90da3f48e4e9924aac6e3.jpg) |
