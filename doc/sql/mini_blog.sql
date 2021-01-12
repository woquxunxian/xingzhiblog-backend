/*
 Navicat Premium Data Transfer

 Source Server         : 47.111.95.54
 Source Server Type    : MySQL
 Source Server Version : 50649
 Source Host           : 47.111.95.54:3306
 Source Schema         : mini_blog

 Target Server Type    : MySQL
 Target Server Version : 50649
 File Encoding         : 65001

 Date: 12/01/2021 23:09:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info`  (
  `id` int(7) NOT NULL,
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `personal_signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `last_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `register_time` datetime(0) NOT NULL,
  `is_valid` int(11) NOT NULL DEFAULT 1,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  `user_id` int(7) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uk_user_info`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for admin_login
-- ----------------------------
DROP TABLE IF EXISTS `admin_login`;
CREATE TABLE `admin_login`  (
  `id` int(7) NOT NULL AUTO_INCREMENT COMMENT '用户主键id',
  `user_name` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `is_valid` int(1) NOT NULL DEFAULT 1,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for article_blog
-- ----------------------------
DROP TABLE IF EXISTS `article_blog`;
CREATE TABLE `article_blog`  (
  `id` int(6) NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文章标题',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文章封面图',
  `brief` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文章摘要',
  `like_count` int(11) NOT NULL DEFAULT 0 COMMENT '文章点赞总数',
  `view_count` int(11) NOT NULL COMMENT '文章阅读总数',
  `comment_count` int(11) DEFAULT NULL COMMENT '文章评论总数',
  `is_valid` int(1) NOT NULL DEFAULT 1 COMMENT '文章是否发布',
  `create_time` datetime(0) NOT NULL COMMENT '文章创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '文章修改时间',
  `admin_info_id` int(7) NOT NULL COMMENT '文章作者id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_blog_id`(`id`) USING BTREE COMMENT '主键id索引',
  INDEX `idk_article_user_info`(`admin_info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for article_blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `article_blog_tag`;
CREATE TABLE `article_blog_tag`  (
  `id` int(9) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `tag_id` int(3) NOT NULL COMMENT '标签id',
  `blog_id` int(6) NOT NULL COMMENT '博客id',
  `is_valid` int(1) NOT NULL DEFAULT 1 COMMENT '是否生效',
  `create_time` datetime(0) NOT NULL COMMENT '创建日期',
  `update_time` datetime(0) NOT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for article_comment
-- ----------------------------
DROP TABLE IF EXISTS `article_comment`;
CREATE TABLE `article_comment`  (
  `id` int(15) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `comment_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `is_valid` int(1) NOT NULL DEFAULT 1,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  `user_id` int(7) NOT NULL COMMENT '用户的微信id',
  `parent_id` int(15) NOT NULL COMMENT '父评论的id，-1为无父评论',
  `article_id` int(6) NOT NULL COMMENT '文章的id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_comment_user`(`user_id`) USING BTREE,
  INDEX `idx_comment_comment`(`parent_id`) USING BTREE,
  INDEX `idx_comment_blog`(`article_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for article_detail
-- ----------------------------
DROP TABLE IF EXISTS `article_detail`;
CREATE TABLE `article_detail`  (
  `id` int(5) NOT NULL COMMENT '文章内容id',
  `article_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章内容',
  `is_valid` int(1) NOT NULL DEFAULT 1 COMMENT '是否有效',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `blog_id` int(6) NOT NULL COMMENT '文章id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_blog_content`(`blog_id`) USING BTREE COMMENT '外键文章id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for article_like
-- ----------------------------
DROP TABLE IF EXISTS `article_like`;
CREATE TABLE `article_like`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '点赞表主键',
  `type_id` int(10) NOT NULL COMMENT '对应的文章id或者评论id',
  `user_id` int(10) NOT NULL COMMENT '对应的点赞用户的id',
  `like_status` int(1) NOT NULL COMMENT '点赞的状态：1为点赞，0为取消赞',
  `like_type` int(1) NOT NULL COMMENT '点赞类型：1为文章点赞，2为评论点赞',
  `is_valid` int(1) NOT NULL DEFAULT 1 COMMENT '记录是否有效',
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for article_tag
-- ----------------------------
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '分类标签主键id',
  `tag_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '标签名',
  `tag_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签描述',
  `color` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签颜色',
  `is_valid` int(1) NOT NULL DEFAULT 1 COMMENT '是否生效',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for my_info
-- ----------------------------
DROP TABLE IF EXISTS `my_info`;
CREATE TABLE `my_info`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `author_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `author_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `author_age` int(3) DEFAULT NULL,
  `author_introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `author_school` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `author_adress` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `author_qrcode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `author_wechat_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `author_gitee_link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `author_github_link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `blog_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `blog_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `blog_introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `blog_gitee_link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `blog_github_link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `is_valid` int(1) NOT NULL DEFAULT 1,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(7) NOT NULL,
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `personal_signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `last_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `register_time` datetime(0) NOT NULL,
  `is_valid` int(11) NOT NULL DEFAULT 1,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  `user_id` int(7) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uk_user_info`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_login
-- ----------------------------
DROP TABLE IF EXISTS `user_login`;
CREATE TABLE `user_login`  (
  `id` int(7) NOT NULL AUTO_INCREMENT COMMENT '用户主键id',
  `user_name` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `is_valid` int(1) NOT NULL DEFAULT 1,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for wx_user
-- ----------------------------
DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '微信用户主键',
  `union_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '微信用户的UnionID',
  `open_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '微信用户的openID',
  `avatar` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '微信头像',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '微信名',
  `is_valid` int(1) NOT NULL DEFAULT 1 COMMENT '记录是否有效',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
