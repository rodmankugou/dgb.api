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

 Date: 03/10/2024 14:17:12 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `mer_category`
-- ----------------------------
DROP TABLE IF EXISTS `mer_category`;
CREATE TABLE `mer_category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `mer_id` varchar(255) DEFAULT NULL,
  `stage_flag` tinyint DEFAULT NULL COMMENT '是否仓库 true-是 false-否',
  `category_id` bigint DEFAULT NULL,
  `pos_cat_id` bigint DEFAULT NULL COMMENT '门店pos机的分类ID',
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_shop_id_cat_id` (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `mer_category`
-- ----------------------------
BEGIN;
INSERT INTO `mer_category` VALUES ('1', 'd000e3c443794213a92d61a9c6f6f6fe', '0', '3', '1709867459998183336', '1'), ('2', 'd000e3c443794213a92d61a9c6f6f6fe', '0', '4', '1710050416910217910', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
