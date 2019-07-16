/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 16/07/2019 12:01:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bz_book
-- ----------------------------
DROP TABLE IF EXISTS `bz_book`;
CREATE TABLE `bz_book`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(0) DEFAULT NULL,
  `last_modified_date` datetime(0) DEFAULT NULL,
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bz_book
-- ----------------------------
INSERT INTO `bz_book` VALUES (2, '2019-07-13 12:04:58', '2019-07-13 12:04:58', '课程一', '课程一', 11);
INSERT INTO `bz_book` VALUES (3, '2019-07-13 12:11:39', '2019-07-13 12:11:39', '课程2', '课程2', 10);
INSERT INTO `bz_book` VALUES (4, '2019-07-14 03:36:04', '2019-07-14 03:36:04', '课程3', '课程3', 12);

-- ----------------------------
-- Table structure for bz_chapter
-- ----------------------------
DROP TABLE IF EXISTS `bz_chapter`;
CREATE TABLE `bz_chapter`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(0) DEFAULT NULL,
  `last_modified_date` datetime(0) DEFAULT NULL,
  `book_id` bigint(20) NOT NULL,
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sort` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bz_chapter
-- ----------------------------
INSERT INTO `bz_chapter` VALUES (1, '2019-07-15 16:29:07', '2019-07-15 16:29:13', 2, '第一章、飞机概述、应急设备、门、窗', '第一章、飞机概述、应急设备、门、窗', 0);

-- ----------------------------
-- Table structure for bz_problem
-- ----------------------------
DROP TABLE IF EXISTS `bz_problem`;
CREATE TABLE `bz_problem`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(0) DEFAULT NULL,
  `last_modified_date` datetime(0) DEFAULT NULL,
  `chapter_id` bigint(20) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sort` int(11) NOT NULL,
  `book_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bz_problem
-- ----------------------------
INSERT INTO `bz_problem` VALUES (1, '2019-07-15 16:29:55', '2019-07-15 16:30:01', 1, '关于紧急出口灯开关的位置，下列哪一种说法是正确的？', 0, 1);
INSERT INTO `bz_problem` VALUES (2, '2019-07-15 16:29:55', '2019-07-15 16:30:01', 1, '在捏紧和拉下时，氧气面罩释放手杆：', 1, 1);
INSERT INTO `bz_problem` VALUES (3, '2019-07-15 16:29:55', '2019-07-15 16:30:01', 1, '供水系统的控制:', 2, 1);
INSERT INTO `bz_problem` VALUES (4, '2019-07-15 16:29:55', '2019-07-15 16:30:01', 1, '在驾驶舱的什么地方显示机组人员的氧气压力？', 3, 1);
INSERT INTO `bz_problem` VALUES (5, '2019-07-15 16:29:55', '2019-07-15 16:30:01', 1, '位于多少座舱高度时，乘客氧气系统会自动启动？', 4, 1);
INSERT INTO `bz_problem` VALUES (6, '2019-07-15 16:29:55', '2019-07-15 16:30:01', 1, '琥珀色的PASS OXY ON（乘客氧气开灯）指示灯亮起，这说明什么？', 5, 1);

-- ----------------------------
-- Table structure for bz_selection
-- ----------------------------
DROP TABLE IF EXISTS `bz_selection`;
CREATE TABLE `bz_selection`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(0) DEFAULT NULL,
  `last_modified_date` datetime(0) DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `correct` bit(1) NOT NULL,
  `problem_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bz_selection
-- ----------------------------
INSERT INTO `bz_selection` VALUES (1, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '当开关置于“ARMED”位时，如果1号交流转换汇流条失效，所有应急灯将亮。', b'0', 1);
INSERT INTO `bz_selection` VALUES (2, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '如果飞机电力供应出现故障或者切断电力供应，开关处于OFF位置时将防止应急灯系统工作。', b'1', 1);
INSERT INTO `bz_selection` VALUES (3, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '将开关置于ON位置将只亮起驾驶舱内的应急灯。', b'0', 1);
INSERT INTO `bz_selection` VALUES (4, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '将开关置于ON位置将只亮起厨房的应急灯。', b'0', 1);
INSERT INTO `bz_selection` VALUES (5, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '立刻启动氧气流红十字。', b'0', 2);
INSERT INTO `bz_selection` VALUES (6, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '将面罩锁定在其储藏盒中。', b'0', 2);
INSERT INTO `bz_selection` VALUES (7, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '启动氧气和麦克风。', b'1', 2);
INSERT INTO `bz_selection` VALUES (8, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '使面罩放气。', b'0', 2);
INSERT INTO `bz_selection` VALUES (9, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '需要来自飞机转换汇流条的直流电源。', b'0', 3);
INSERT INTO `bz_selection` VALUES (10, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '是通过机身左上方的面板进行的。', b'0', 3);
INSERT INTO `bz_selection` VALUES (11, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '是通过位于机身尾部右下方的面板进行的。', b'1', 3);
INSERT INTO `bz_selection` VALUES (12, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '是通过两个贮水箱的重力灌注来完成的。', b'0', 3);
INSERT INTO `bz_selection` VALUES (13, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '位于机组人员氧气关断活门上。', b'0', 4);
INSERT INTO `bz_selection` VALUES (14, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '在副驾驶座位后面的P-6面板上。', b'0', 4);
INSERT INTO `bz_selection` VALUES (15, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '位于后顶板的机组人员氧气压力指示器上。', b'1', 4);
INSERT INTO `bz_selection` VALUES (16, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '在机长座位后面的P-18面板上。', b'0', 4);
INSERT INTO `bz_selection` VALUES (17, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '17，000英尺', b'0', 5);
INSERT INTO `bz_selection` VALUES (18, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '10，000英尺', b'0', 5);
INSERT INTO `bz_selection` VALUES (19, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '14，000英尺', b'1', 5);
INSERT INTO `bz_selection` VALUES (20, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '13，000英尺', b'0', 5);
INSERT INTO `bz_selection` VALUES (21, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '乘客氧气量过低。', b'0', 6);
INSERT INTO `bz_selection` VALUES (22, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '乘客氧气系统压力过低。', b'0', 6);
INSERT INTO `bz_selection` VALUES (23, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '乘客氧气系统已经启动，并且氧气面罩已经掉下。', b'1', 6);
INSERT INTO `bz_selection` VALUES (24, '2019-07-15 16:30:30', '2019-07-15 16:30:32', '主警告系统中的顶板指示灯已经熄灭。', b'0', 6);

-- ----------------------------
-- Table structure for wx_error_record
-- ----------------------------
DROP TABLE IF EXISTS `wx_error_record`;
CREATE TABLE `wx_error_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(0) DEFAULT NULL,
  `last_modified_date` datetime(0) DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `problem_id` bigint(20) NOT NULL,
  `selection_id` bigint(20) NOT NULL,
  `we_chat_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wx_user
-- ----------------------------
DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(0) DEFAULT NULL,
  `last_modified_date` datetime(0) DEFAULT NULL,
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `country` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gender` tinyint(4) DEFAULT NULL,
  `language` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
