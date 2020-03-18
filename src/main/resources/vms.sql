/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : mentor-stu

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2020-03-18 12:04:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) NOT NULL COMMENT '请求路径规则',
  `path` varchar(64) NOT NULL COMMENT '路由path',
  `component` varchar(64) NOT NULL COMMENT '组件名称',
  `name` varchar(64) NOT NULL COMMENT '组件名',
  `menu_roleId` int(11) DEFAULT NULL COMMENT '角色id,外键',
  `iconCls` varchar(64) DEFAULT NULL COMMENT '菜单图标',
  `keepAlive` tinyint(1) DEFAULT NULL COMMENT '菜单切换时是否保活',
  `parentId` int(11) DEFAULT NULL COMMENT '父菜单id,普通索引',
  PRIMARY KEY (`id`),
  KEY `menu_roleId` (`menu_roleId`),
  CONSTRAINT `menu_roleId` FOREIGN KEY (`menu_roleId`) REFERENCES `role` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '角色名称',
  `nameZh` varchar(32) NOT NULL COMMENT '角色中文名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL COMMENT '学号',
  `name` varchar(10) NOT NULL COMMENT '姓名',
  `gender` varchar(4) NOT NULL COMMENT '性别',
  `major` varchar(32) NOT NULL COMMENT '专业',
  `politic` varchar(32) NOT NULL COMMENT '政治面貌',
  `email` varchar(64) NOT NULL COMMENT '邮箱',
  `phone` varchar(11) NOT NULL COMMENT '电话',
  `describe` varchar(255) NOT NULL COMMENT '自我描述',
  `teacherId` int(11) DEFAULT NULL COMMENT '所选导师',
  `status` int(3) NOT NULL DEFAULT '1' COMMENT '状态，默认为1  ,未选择导师:1     已选择导师:2     已互选成功:3',
  PRIMARY KEY (`id`),
  KEY `teacherIdIndex` (`teacherId`) USING BTREE,
  CONSTRAINT `teacherId` FOREIGN KEY (`teacherId`) REFERENCES `teacher` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL COMMENT '教职工号',
  `name` varchar(10) NOT NULL COMMENT '姓名',
  `gender` varchar(4) NOT NULL COMMENT '性别',
  `email` varchar(64) NOT NULL COMMENT '邮箱',
  `phone` varchar(11) NOT NULL COMMENT '电话',
  `direction` varchar(255) NOT NULL COMMENT '研究方向',
  `describe` varchar(255) NOT NULL COMMENT '自我描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '学生/老师/管理员的id',
  `password` varchar(255) NOT NULL COMMENT '登录密码',
  `roleId` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  KEY `roleId_Index` (`roleId`) USING BTREE,
  CONSTRAINT `user_roleId` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
