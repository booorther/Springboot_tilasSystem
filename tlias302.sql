/*
Navicat MySQL Data Transfer

Source Server         : HHG
Source Server Version : 80031
Source Host           : localhost:3306
Source Database       : tlias302

Target Server Type    : MYSQL
Target Server Version : 80031
File Encoding         : 65001

Date: 2024-08-02 23:58:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(10) NOT NULL COMMENT '部门名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3 COMMENT='部门表';

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('1', '编辑部', '2024-06-22 15:13:37', '2024-06-22 15:13:37');
INSERT INTO `dept` VALUES ('2', '教研部', '2024-06-22 08:42:08', '2024-06-22 08:42:08');
INSERT INTO `dept` VALUES ('3', '咨询部', '2024-06-22 08:42:08', '2024-06-22 08:42:08');
INSERT INTO `dept` VALUES ('7', '坤歌部', '2024-06-22 10:08:49', '2024-06-22 16:13:01');
INSERT INTO `dept` VALUES ('8', '部幂部', '2024-06-22 10:11:16', '2024-06-26 17:49:43');
INSERT INTO `dept` VALUES ('10', '班主任部', '2024-06-22 10:16:18', '2024-06-22 15:40:59');
INSERT INTO `dept` VALUES ('11', '哥们部', '2024-06-22 10:27:30', '2024-06-22 15:49:36');
INSERT INTO `dept` VALUES ('12', '学习部', '2024-06-22 11:31:09', '2024-06-22 15:46:20');

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '123456' COMMENT '密码',
  `name` varchar(10) NOT NULL COMMENT '姓名',
  `gender` tinyint unsigned NOT NULL COMMENT '性别, 说明: 1 男, 2 女',
  `image` varchar(300) DEFAULT NULL COMMENT '图像',
  `job` tinyint unsigned DEFAULT NULL COMMENT '职位, 说明: 1 班主任,2 讲师, 3 学工主管, 4 教研主管, 5 咨询师',
  `entrydate` date DEFAULT NULL COMMENT '入职时间',
  `dept_id` int unsigned DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3 COMMENT='员工表';

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES ('9', 'dingminjun', '123456', '丁敏君', '2', '9.jpg', '1', '2011-03-11', '1', '2024-06-21 16:43:27', '2024-06-21 16:43:27');
INSERT INTO `emp` VALUES ('10', 'zhaomin', '123456', '赵敏', '2', '10.jpg', '1', '2013-09-05', '1', '2024-06-21 16:43:27', '2024-06-21 16:43:27');
INSERT INTO `emp` VALUES ('11', 'luzhangke', '123456', '鹿杖客', '1', '11.jpg', '5', '2007-02-01', '3', '2024-06-21 16:43:27', '2024-06-21 16:43:27');
INSERT INTO `emp` VALUES ('12', 'hebiweng', '123456', '鹤笔翁', '1', '12.jpg', '5', '2008-08-18', '3', '2024-06-21 16:43:27', '2024-06-21 16:43:27');
INSERT INTO `emp` VALUES ('13', 'fangdongbai', '123456', '方东白', '1', '13.jpg', '5', '2012-11-01', '3', '2024-06-21 16:43:27', '2024-06-21 16:43:27');
INSERT INTO `emp` VALUES ('14', 'zhangsanfeng', '123456', '张三丰', '1', '14.jpg', '2', '2002-08-01', '2', '2024-06-21 16:43:27', '2024-06-21 16:43:27');
INSERT INTO `emp` VALUES ('15', 'yulianzhou', '123456', '俞莲舟', '1', '15.jpg', '2', '2011-05-01', '2', '2024-06-21 16:43:27', '2024-06-21 16:43:27');
INSERT INTO `emp` VALUES ('16', 'songyuanqiao', '123456', '宋远桥', '1', '16.jpg', '2', '2007-01-01', '2', '2024-06-21 16:43:27', '2024-06-21 16:43:27');
INSERT INTO `emp` VALUES ('17', 'chenyouliang', '123456', '陈友谅', '1', '17.jpg', null, '2015-03-21', null, '2024-06-21 16:43:27', '2024-06-21 16:43:27');
INSERT INTO `emp` VALUES ('18', '广泛的衮', '123456', '球', '1', '86.jpg', '2', null, null, '2024-06-24 10:37:22', '2024-06-24 10:37:22');
INSERT INTO `emp` VALUES ('19', 'liming', '123456', '黎明', '1', '', '1', '2024-06-23', '11', '2024-06-24 10:46:58', '2024-06-24 10:46:58');
INSERT INTO `emp` VALUES ('20', 'nimeide', '123456', '名名', '1', '', '1', '2024-06-24', '7', '2024-06-24 10:48:10', '2024-06-24 10:48:10');
INSERT INTO `emp` VALUES ('21', 'wangmang', '123456', '王莽', '1', 'https://tlias302.oss-cn-guangzhou.aliyuncs.com/2024/06/26/950be7f1d2734c88b62ccc7bbfd85e43.jpg', '2', '2024-06-24', '3', '2024-06-26 11:17:26', '2024-06-26 11:17:26');
INSERT INTO `emp` VALUES ('22', 'hanmeimei', '56daf5ef767790f5ee24133c2da7d6e3b48d198c303d4527940a62629811e42b', '韩梅梅', '2', 'https://tlias302.oss-cn-guangzhou.aliyuncs.com/2024/06/25/799457e3513543ba8b607ed30ba84b37.jpg', '3', '2024-06-16', '10', '2024-06-25 10:02:52', '2024-06-25 10:02:52');
INSERT INTO `emp` VALUES ('23', 'liuyifei', '188f994cd08b7732af93af8e533e2091d35eafd1526f4a10835c3830565908d1', '刘亦菲', '2', 'https://tlias302.oss-cn-guangzhou.aliyuncs.com/2024/06/25/257809f409024b1ebf7cb0e9bef78120.jpg', '1', '2024-06-27', '10', '2024-06-25 09:32:26', '2024-06-25 09:32:26');
INSERT INTO `emp` VALUES ('24', 'limei', 'f18c418b611b5dbb844690717aa4dbde88c5d564d5aa47a984e6ac9c28997c6c', '李梅', '2', 'https://tlias302.oss-cn-guangzhou.aliyuncs.com/2024/06/26/5337118fb841487bbfac16e6d7e6e3f2.jpg', '2', '2024-06-25', '11', '2024-06-26 10:40:48', '2024-06-26 10:40:48');
SET FOREIGN_KEY_CHECKS=1;
