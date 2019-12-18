/*
 Navicat Premium Data Transfer

 Source Server         : 本地连接
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : myblog-2

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 09/12/2019 19:55:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_announcement
-- ----------------------------
DROP TABLE IF EXISTS `t_announcement`;
CREATE TABLE `t_announcement`
(
  `annc_id`      int(11)                                                NOT NULL AUTO_INCREMENT COMMENT '公告id，主键',
  `annc_title`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '公告标题',
  `annc_desc`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '公告描述',
  `annc_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin         NOT NULL COMMENT '公告内容',
  `top_flag`     bit(1)                                                 NOT NULL COMMENT '是否置顶',
  `create_time`  datetime(0)                                            NOT NULL COMMENT '创建时间',
  `update_time`  datetime(0)                                            NOT NULL COMMENT '更新时间',
  `del_flag`     bit(1)                                                 NOT NULL COMMENT '删除标志：1-删除 0-未删除',
  `del_time`     datetime(0) DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`annc_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT = '公告表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog`
(
  `blog_id`            int(11)                                                NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id`            int(11)                                                DEFAULT NULL COMMENT '博主id，对应用户id',
  `blog_flag`          varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   NOT NULL COMMENT '博客标志：1-原创，2-转载，3-翻译',
  `blog_desc`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '博客描述',
  `recommend`          bit(1)                                                 NOT NULL COMMENT '是否推荐',
  `share_flag`         bit(1)                                                 NOT NULL COMMENT '转载声明是否开启',
  `type_id`            int(11)                                                DEFAULT NULL COMMENT '博客类型',
  `tag_id_str`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT
    '博客标签字符串，格式：,1,2,3,4,5,',
  `first_picture_addr` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '首图地址',
  `published`          bit(1)                                                 NOT NULL COMMENT '是否发布：0-草稿 1-发布',
  `blog_title`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '博客标题',
  `blog_content`       longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin     NOT NULL COMMENT '博客内容',
  `blog_views`         int(11)                                                NOT NULL COMMENT '博客浏览次数',
  `appreciation_flag`  bit(1)                                                 NOT NULL COMMENT '是否开启赞赏',
  `commentabled`       bit(1)                                                 NOT NULL COMMENT '是否开启评论',
  `blog_comments`      int(11)                                                NOT NULL COMMENT '博客评论次数',
  `create_time`        datetime(0)                                            NOT NULL COMMENT '创建时间',
  `update_time`        datetime(0)                                            NOT NULL COMMENT '更新时间',
  `del_flag`           bit(1)                                                 NOT NULL COMMENT '删除标识',
  `del_time`           datetime(0)                                            DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`blog_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog`
VALUES (1, 1, '1', 'Nginx是一款轻量级的Web服务器、反向代理服务器，由于它的内存占用少，启动极快，高并发能力强，在互联网项目中广泛应用。本文就带大家来全面了解Nginx的使用', b'1', b'1', 1,
        '-1-2-3-4-', 'https://picsum.photos/800/450', b'1', 'title1', '111', 1, b'1', b'1', 1, '2019-12-09 19:36:19',
        '2019-12-01 10:15:25', b'0', NULL);
INSERT INTO `t_blog`
VALUES (2, 1, '1', '1', b'1', b'1', 2, '-1-3-4-5-', 'https://picsum.photos/800/450', b'1', 'title2', '222', 1, b'1',
        b'1', 1, '2019-12-09 19:36:19', '2019-12-01 10:15:25', b'0', NULL);
INSERT INTO `t_blog`
VALUES (3, 1, '2', '1', b'1', b'1', 3, '-4-5-', 'https://picsum.photos/800/450', b'1', 'title3', '333', 1, b'1', b'1',
        1, '2019-12-09 19:36:19', '2019-12-01 10:15:25', b'0', NULL);
INSERT INTO `t_blog`
VALUES (4, 1, '2', '1', b'1', b'1', 4, '-2-4-', 'https://picsum.photos/800/450', b'1', 'title4', '444', 1, b'1', b'1',
        1, '2019-12-09 19:36:19', '2019-12-01 10:15:25', b'0', NULL);
INSERT INTO `t_blog`
VALUES (5, 1, '1', '1', b'1', b'1', 4, '-1-', 'https://picsum.photos/800/450', b'1', 'title5', '555', 1, b'1', b'1', 1,
        '2019-12-09 19:36:19', '2019-12-01 10:15:25', b'0', NULL);
INSERT INTO `t_blog`
VALUES (6, 1, '1', '1', b'1', b'1', 4, '-1-', 'https://picsum.photos/800/450', b'1', 'title6', '666', 1, b'1', b'1', 1,
        '2019-12-09 19:36:19', '2019-12-01 10:15:25', b'0', NULL);
INSERT INTO `t_blog`
VALUES (7, 1, '2', '1', b'1', b'1', 1, '-1-', 'https://picsum.photos/800/450', b'1', 'title7', '777', 1, b'1', b'1', 1,
        '2019-12-09 19:36:19', '2019-12-01 10:15:25', b'0', NULL);
INSERT INTO `t_blog`
VALUES (8, 1, '2', '1', b'1', b'1', 2, '-1-', 'https://picsum.photos/800/450', b'1', 'title8', '888', 1, b'1', b'1', 1,
        '2019-12-09 19:36:19', '2019-12-01 10:15:25', b'0', NULL);
INSERT INTO `t_blog`
VALUES (9, 1, '1', '1', b'1', b'1', 2, '-1-', 'https://picsum.photos/800/450', b'1', 'title9', '999', 1, b'1', b'1', 1,
        '2019-12-09 19:36:19', '2019-12-01 10:15:25', b'0', NULL);
INSERT INTO `t_blog`
VALUES (10, 1, '1', '1', b'1', b'1', 2, '-1-', 'https://picsum.photos/800/450', b'1', 'titlea', 'aaa', 1, b'1', b'1', 1,
        '2019-12-09 19:36:19', '2019-12-01 10:15:25', b'0', NULL);
INSERT INTO `t_blog`
VALUES (11, 1, '3', '1', b'1', b'1', 2, '-1-', 'https://picsum.photos/800/450', b'1', 'titleb', 'bbb', 1, b'1', b'1', 1,
        '2019-12-09 19:36:19', '2019-12-01 10:15:25', b'0', NULL);
INSERT INTO `t_blog`
VALUES (12, 1, '3', '1', b'1', b'1', 2, '-1-', 'https://picsum.photos/800/450', b'1', 'titlec', 'ccc', 1, b'1', b'1', 1,
        '2019-12-09 19:36:19', '2019-12-01 10:15:25', b'0', NULL);
INSERT INTO `t_blog`
VALUES (13, 1, '1', '1', b'1', b'1', 2, '-1-', 'https://picsum.photos/800/450', b'1', 'titled', 'ddd', 1, b'1', b'1', 1,
        '2019-12-09 19:36:19', '2019-12-01 10:15:25', b'0', NULL);
INSERT INTO `t_blog`
VALUES (14, 1, '2', '1', b'1', b'1', 1, '-1-', 'https://picsum.photos/800/450', b'1', 'titlee', 'eee', 1, b'1', b'1', 1,
        '2019-12-09 19:36:19', '2019-12-01 10:15:25', b'0', NULL);
INSERT INTO `t_blog`
VALUES (15, 1, '3', '1', b'1', b'1', 3, '-1-', 'https://picsum.photos/800/450', b'1', 'titlef', 'fff', 1, b'1', b'1', 1,
        '2019-12-09 19:36:19', '2019-12-01 10:15:25', b'0', NULL);
INSERT INTO `t_blog`
VALUES (16, 1, '3', '1', b'1', b'1', 3, '-1-', 'https://picsum.photos/800/450', b'1', 'titleg', 'ggg', 1, b'1', b'1', 1,
        '2019-12-09 19:36:19', '2019-12-01 10:15:25', b'0', NULL);

-- ----------------------------
-- Table structure for t_blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_tag`;
CREATE TABLE `t_blog_tag`
(
  `id`      int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `blog_id` int(11) NOT NULL COMMENT '博客id',
  `tag_id`  int(11) NOT NULL COMMENT '标签id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blog_tag
-- ----------------------------
INSERT INTO `t_blog_tag`
VALUES (1, 1, 1);
INSERT INTO `t_blog_tag`
VALUES (2, 1, 2);
INSERT INTO `t_blog_tag`
VALUES (3, 2, 1);
INSERT INTO `t_blog_tag`
VALUES (4, 2, 3);
INSERT INTO `t_blog_tag`
VALUES (5, 2, 4);
INSERT INTO `t_blog_tag`
VALUES (6, 2, 5);
INSERT INTO `t_blog_tag`
VALUES (8, 3, 5);
INSERT INTO `t_blog_tag`
VALUES (9, 3, 4);
INSERT INTO `t_blog_tag`
VALUES (10, 4, 2);
INSERT INTO `t_blog_tag`
VALUES (11, 1, 3);
INSERT INTO `t_blog_tag`
VALUES (12, 1, 4);
INSERT INTO `t_blog_tag`
VALUES (14, 4, 4);
INSERT INTO `t_blog_tag`
VALUES (15, 5, 1);
INSERT INTO `t_blog_tag`
VALUES (16, 6, 1);
INSERT INTO `t_blog_tag`
VALUES (17, 7, 1);
INSERT INTO `t_blog_tag`
VALUES (18, 8, 1);
INSERT INTO `t_blog_tag`
VALUES (19, 9, 1);
INSERT INTO `t_blog_tag`
VALUES (20, 10, 1);
INSERT INTO `t_blog_tag`
VALUES (21, 11, 1);
INSERT INTO `t_blog_tag`
VALUES (22, 12, 1);
INSERT INTO `t_blog_tag`
VALUES (23, 13, 1);
INSERT INTO `t_blog_tag`
VALUES (24, 14, 1);
INSERT INTO `t_blog_tag`
VALUES (25, 15, 1);
INSERT INTO `t_blog_tag`
VALUES (26, 16, 1);

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`
(
  `comment_id`      int(11)                                        NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `comment_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '评论内容',
  `user_id`         int(11)                                        NOT NULL COMMENT '评论用户id',
  `blog_id`         int(11)                                        NOT NULL COMMENT '评论的博客id',
  `comment_time`    datetime(0)                                    NOT NULL COMMENT '评论时间',
  `create_time`     datetime(0)                                    NOT NULL COMMENT '创建时间',
  `update_time`     datetime(0)                                    NOT NULL COMMENT '更新时间',
  `del_flag`        bit(1)                                         NOT NULL COMMENT '删除标志：1-删除 0-未删除',
  `del_time`        datetime(0) DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment`
VALUES (1, '哈哈哈哈1', 1, 1, '2019-12-09 15:03:57', '2019-12-09 19:51:32', '2019-12-09 19:51:32', b'0', NULL);
INSERT INTO `t_comment`
VALUES (2, '哈哈哈哈1', 2, 1, '2019-12-09 15:04:26', '2019-12-09 19:51:32', '2019-12-09 19:51:32', b'0', NULL);

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message`
(
  `message_id`            int(11)                          NOT NULL AUTO_INCREMENT COMMENT '主键',
  `message_user_id`       int(11)                          NOT NULL COMMENT '存储已经登录的用户id，否则为0',
  `message_nickname`      varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '留言者昵称',
  `replied_user_id`       int(11)                          DEFAULT NULL COMMENT '被回复id，一级留言为空，二级留言游客为0',
  `replied_user_nickname` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '被回复昵称，一级留言为空',
  `message_email`         varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '留言者邮箱',
  `message_content`       text COLLATE utf8mb4_bin         NOT NULL COMMENT '留言内容',
  `message_level`         varchar(2) COLLATE utf8mb4_bin   NOT NULL COMMENT '留言等级：1-留言，2-对留言的回复',
  `parent_message_id`     int(11)                          NOT NULL COMMENT '父留言id，没有上层为0',
  `create_time`           datetime                         NOT NULL COMMENT '创建时间',
  `update_time`           datetime                         NOT NULL COMMENT '更新时间',
  `del_flag`              bit(1)                           NOT NULL COMMENT '删除标志：1-删除 0-未删除',
  `del_time`              datetime                         DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`message_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_reply
-- ----------------------------
DROP TABLE IF EXISTS `t_reply`;
CREATE TABLE `t_reply`
(
  `reply_id`        int(11)                                        NOT NULL AUTO_INCREMENT COMMENT '主键',
  `comment_id`      int(11)                                        NOT NULL COMMENT '评论id',
  `reply_content`   text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '回复内容',
  `user_id`         int(11)                                        NOT NULL COMMENT '回复的用户id',
  `reply_time`      datetime(0)                                    NOT NULL COMMENT '回复时间',
  `replied_user_id` int(11)                                        NOT NULL COMMENT '被回复的用户id',
  `create_time`     datetime(0)                                    NOT NULL COMMENT '创建时间',
  `update_time`     datetime(0)                                    NOT NULL COMMENT '更新时间',
  `del_flag`        bit(1)                                         NOT NULL COMMENT '删除标志：1-删除 0-未删除',
  `del_time`        datetime(0) DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`reply_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_reply
-- ----------------------------
INSERT INTO `t_reply`
VALUES (1, 1, '回复1', 1, '2019-12-09 15:11:06', 1, '2019-12-09 19:51:32', '2019-12-09 19:51:32', b'0', NULL);
INSERT INTO `t_reply`
VALUES (2, 1, '回复2', 1, '2019-12-09 15:11:24', 2, '2019-12-09 19:51:32', '2019-12-09 19:51:32', b'0', NULL);

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`
(
  `tag_id`      int(11)                                               NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tag_name`    varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '标签名',
  `create_time` datetime(0)                                           NOT NULL COMMENT '创建时间',
  `update_time` datetime(0)                                           NOT NULL COMMENT '更新时间',
  `del_flag`    bit(1)                                                NOT NULL COMMENT '删除标志：1-删除 0-未删除',
  `del_time`    datetime(0) DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag`
VALUES (1, '1111', '2019-12-09 19:50:56', '2019-12-09 19:51:32', b'0', NULL);
INSERT INTO `t_tag`
VALUES (2, '2222', '2019-12-09 19:51:00', '2019-12-09 19:51:35', b'0', NULL);
INSERT INTO `t_tag`
VALUES (3, '3333', '2019-12-09 19:51:03', '2019-12-09 19:51:38', b'0', NULL);
INSERT INTO `t_tag`
VALUES (4, '4444', '2019-12-09 19:51:05', '2019-12-09 19:51:40', b'0', NULL);
INSERT INTO `t_tag`
VALUES (5, '5555', '2019-12-09 19:51:07', '2019-12-09 19:51:43', b'0', NULL);
INSERT INTO `t_tag`
VALUES (6, '6666', '2019-12-09 19:51:10', '2019-12-09 19:51:46', b'0', NULL);

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`
(
  `type_id`     int(11)                                               NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_name`   varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '类型名',
  `create_time` datetime(0)                                           NOT NULL COMMENT '创建时间',
  `update_time` datetime(0)                                           NOT NULL COMMENT '更新时间',
  `del_flag`    bit(1)                                                NOT NULL COMMENT '删除标志：1-删除 0-未删除',
  `del_time`    datetime(0) DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type`
VALUES (1, '1111', '2019-12-09 19:37:49', '2019-12-09 19:38:02', b'0', NULL);
INSERT INTO `t_type`
VALUES (2, '2222', '2019-12-09 19:37:52', '2019-12-09 19:38:07', b'0', NULL);
INSERT INTO `t_type`
VALUES (3, '3333', '2019-12-09 19:37:54', '2019-12-09 19:38:09', b'0', NULL);
INSERT INTO `t_type`
VALUES (4, '4444', '2019-12-09 19:37:57', '2019-12-09 19:38:11', b'0', NULL);
INSERT INTO `t_type`
VALUES (5, '5555', '2019-12-09 19:37:59', '2019-12-09 19:38:14', b'0', NULL);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
  `user_id`         int(11)                                                NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL COMMENT '用户登录名',
  `nickname`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL COMMENT '昵称',
  `password`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL COMMENT '登录密码',
  `user_role`       varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   NOT NULL COMMENT '角色类型：1-超级管理员；2-普通管理员；3-VIP用户；4-普通用户',
  `user_status`     varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   NOT NULL COMMENT '用户状态：0-封禁；1-正常',
  `telephone`       varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系方式',
  `email`           varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `head_portrait`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户头像',
  `register_time`   datetime(0)                                            NOT NULL COMMENT '注册时间',
  `last_login_time` datetime(0)                                            NOT NULL COMMENT '最后登录时间',
  `last_login_ip`   varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL COMMENT '最后登录ip',
  `create_time`     datetime(0)                                            NOT NULL COMMENT '创建时间',
  `update_time`     datetime(0)                                            NOT NULL COMMENT '更新时间',
  `del_flag`        bit(1)                                                 NOT NULL COMMENT '删除标识',
  `del_time`        datetime(0)                                           DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user`
VALUES (1, 'xianzilei22', '贤子磊22', '9dd1cbdaec89487b1ba9583f8aa26198', '4', '1', '17854212902', '282991723@qq.com',
        '/images/avatar.png', '2019-12-01 18:41:37', '2019-12-01 18:41:37', '0:0:0:0:0:0:0:1', '2019-12-01 18:41:37',
        '2019-12-01 18:41:37', b'0', NULL);
INSERT INTO `t_user`
VALUES (2, 'xianzilei', '贤子磊', 'e10adc3949ba59abbe56e057f20f883e', '4', '1', '17854212902', '282991723@qq.com',
        '/images/avatar.png', '2019-12-01 18:35:40', '2019-12-07 14:52:44', '0:0:0:0:0:0:0:1', '2019-12-01 18:35:40',
        '2019-12-01 18:35:40', b'0', NULL);
INSERT INTO `t_user`
VALUES (3, 'xianzilei', '贤子磊', 'e10adc3949ba59abbe56e057f20f883e', '4', '1', '17854212902', '282991723@qq.com',
        '/images/avatar.png', '2019-12-01 18:36:46', '2019-12-01 18:36:46', '0:0:0:0:0:0:0:1', '2019-12-01 18:36:46',
        '2019-12-01 18:36:46', b'0', NULL);
INSERT INTO `t_user`
VALUES (4, 'xianzilei11', '贤子磊11', '5aacb803e626ef128c0dc23c52e829d8', '4', '1', '17854212902', '282991723@qq.com',
        '/images/avatar.png', '2019-12-01 18:38:23', '2019-12-01 18:38:23', '0:0:0:0:0:0:0:1', '2019-12-01 18:38:23',
        '2019-12-01 18:38:23', b'0', NULL);
INSERT INTO `t_user`
VALUES (6, 'jichunlei', '贤子磊23132', '052a1a3c0142ad636571f88ea2506eac', '4', '1', '17854212902', '282991723@qq.com',
        '/images/avatar.png', '2019-12-01 18:45:27', '2019-12-01 18:45:27', '0:0:0:0:0:0:0:1', '2019-12-01 18:45:27',
        '2019-12-01 18:45:27', b'0', NULL);

SET FOREIGN_KEY_CHECKS = 1;
