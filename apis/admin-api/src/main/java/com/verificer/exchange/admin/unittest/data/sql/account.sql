/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost
 Source Database       : dbg

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : utf-8

 Date: 03/22/2024 01:23:20 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `customer_id` varchar(255) NOT NULL,
  `sub_name` varchar(30) NOT NULL,
  `available_amount` decimal(38,18) NOT NULL,
  `frozen_amount` decimal(38,18) NOT NULL,
  `negative_flag` tinyint(1) DEFAULT NULL COMMENT '可否为负数',
  `create_time` bigint NOT NULL,
  `update_time` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `customer_id_sub_name` (`customer_id`,`sub_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `account`
-- ----------------------------
BEGIN;
INSERT INTO `account` VALUES ('5', '1', 'USR_INTEGRAL', '0.000000000000000000', '0.000000000000000000', '0', '1710930932823', '1710930932823'), ('7', '1', 'USR_REF', '20.000000000000000000', '0.000000000000000000', '0', '1710930932823', '1710932631736');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
