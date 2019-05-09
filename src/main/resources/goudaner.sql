/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : goudaner

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 09/05/2019 16:56:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gd_account
-- ----------------------------
DROP TABLE IF EXISTS `gd_account`;
CREATE TABLE `gd_account` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '32位自增id',
  `account_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户id',
  `system_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '系统用户id',
  `account_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `gmt_modify` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`,`account_no`,`system_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for gd_goods
-- ----------------------------
DROP TABLE IF EXISTS `gd_goods`;
CREATE TABLE `gd_goods` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `gds_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品id',
  `gds_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品名称',
  `gds_price` decimal(32,2) NOT NULL COMMENT '商品价格',
  `gds_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '商品类型',
  `gds_stock` int(32) DEFAULT NULL COMMENT '商品库存',
  `gds_live_stock` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '商品活跃库存',
  `gds_total_stock` int(32) DEFAULT NULL COMMENT '商品总库存',
  `gmt_modify` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`,`gds_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gd_goods
-- ----------------------------
BEGIN;
INSERT INTO `gd_goods` VALUES (1, '123123123', '123123123', 0.00, '1', NULL, '11111', NULL, NULL, NULL);
INSERT INTO `gd_goods` VALUES (2, '123123123', '123123123', 0.00, '1', NULL, '11111', NULL, NULL, NULL);
INSERT INTO `gd_goods` VALUES (3, '123123123', '123123123', 0.00, '1', NULL, '11111', NULL, NULL, NULL);
INSERT INTO `gd_goods` VALUES (4, '123123123', '123123123', 0.00, '1', NULL, '11111', NULL, NULL, NULL);
INSERT INTO `gd_goods` VALUES (5, '123123123', '123123123', 0.00, '1', NULL, '11111', NULL, NULL, NULL);
INSERT INTO `gd_goods` VALUES (6, '123123123', '123123123', 0.00, '1', NULL, '11111', NULL, NULL, NULL);
INSERT INTO `gd_goods` VALUES (7, '123123123', '123123123', 0.00, '1', NULL, '11111', NULL, NULL, NULL);
INSERT INTO `gd_goods` VALUES (8, '123123123', '123123123', 0.00, '1', NULL, '11111', NULL, NULL, NULL);
INSERT INTO `gd_goods` VALUES (9, '123123123', '123123123', 0.00, '1', NULL, '11111', NULL, NULL, NULL);
INSERT INTO `gd_goods` VALUES (10, '123123123', '123123123', 0.00, '1', NULL, '11111', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for gd_order
-- ----------------------------
DROP TABLE IF EXISTS `gd_order`;
CREATE TABLE `gd_order` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '订单id',
  `system_no` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '用户id',
  `order_state` int(2) DEFAULT NULL COMMENT '订单状态：0:待支付，1:待收货，2:结束',
  `order_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '订单名称',
  `order_amt` decimal(32,0) DEFAULT NULL COMMENT '订单总金额',
  `gmt_modify` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`,`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gd_order
-- ----------------------------
BEGIN;
INSERT INTO `gd_order` VALUES (22, 'O0509143813041205261', '111111', 1, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for gd_order_merch
-- ----------------------------
DROP TABLE IF EXISTS `gd_order_merch`;
CREATE TABLE `gd_order_merch` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '订单id',
  `gds_name` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `gds_price` decimal(32,2) DEFAULT NULL,
  `gds_state` int(2) DEFAULT NULL COMMENT '订单状态：0:待支付，1:待收货，2:结束',
  `gmt_modify` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gd_order_merch
-- ----------------------------
BEGIN;
INSERT INTO `gd_order_merch` VALUES (3, 'O0509143813041205261', NULL, NULL, 1, NULL, NULL);
INSERT INTO `gd_order_merch` VALUES (4, 'O0509143813041205261', NULL, NULL, 1, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for gd_wallet
-- ----------------------------
DROP TABLE IF EXISTS `gd_wallet`;
CREATE TABLE `gd_wallet` (
  `id` int(32) NOT NULL COMMENT '主键id',
  `system_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户id',
  `order_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '订单名称',
  `total_amt` decimal(32,0) DEFAULT NULL COMMENT '累计总金额',
  `surplus` decimal(32,0) DEFAULT NULL COMMENT '剩余金额',
  `gmt_modify` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`,`system_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for pagroup
-- ----------------------------
DROP TABLE IF EXISTS `pagroup`;
CREATE TABLE `pagroup` (
  `groupId` int(11) DEFAULT NULL,
  `groupName` varchar(256) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `isAdvance` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pagroup
-- ----------------------------
BEGIN;
INSERT INTO `pagroup` VALUES (1, '1', 1, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
