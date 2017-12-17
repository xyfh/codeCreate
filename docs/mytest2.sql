/*
Navicat MySQL Data Transfer

Source Server         : hehe
Source Server Version : 50155
Source Host           : localhost:3306
Source Database       : mytest2

Target Server Type    : MYSQL
Target Server Version : 50155
File Encoding         : 65001

Date: 2017-12-13 16:32:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `address`
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `address` varchar(20) DEFAULT NULL COMMENT '家庭地址',
  `personId` varchar(32) DEFAULT NULL COMMENT '所属人',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`),
  KEY `personId` (`personId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('eee', '新疆', 'a', null);
INSERT INTO `address` VALUES ('qqq', '北京', 'a', null);
INSERT INTO `address` VALUES ('www', '上海', 'b', null);

-- ----------------------------
-- Table structure for `card`
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `number` int(20) DEFAULT NULL COMMENT '编号',
  `pid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of card
-- ----------------------------
INSERT INTO `card` VALUES ('eee', '321', 'a');
INSERT INTO `card` VALUES ('qqq', '333', 'b');

-- ----------------------------
-- Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` varchar(32) NOT NULL,
  `uname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('a', '小明');
INSERT INTO `person` VALUES ('b', '小孩');

-- ----------------------------
-- Table structure for `t_course`
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_course
-- ----------------------------
INSERT INTO `t_course` VALUES ('1', '高数');
INSERT INTO `t_course` VALUES ('2', '计算机原理');

-- ----------------------------
-- Table structure for `t_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------
INSERT INTO `t_teacher` VALUES ('teacher1', '刘老师');
INSERT INTO `t_teacher` VALUES ('teacher2', '何老师');

-- ----------------------------
-- Table structure for `t_teacher_course`
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher_course`;
CREATE TABLE `t_teacher_course` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `cid` varchar(32) DEFAULT NULL,
  `tid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_teacher_course
-- ----------------------------
INSERT INTO `t_teacher_course` VALUES ('one', '1', 'teacher1');
INSERT INTO `t_teacher_course` VALUES ('three', '2', 'teacher2');
INSERT INTO `t_teacher_course` VALUES ('two', '2', 'teacher1');
