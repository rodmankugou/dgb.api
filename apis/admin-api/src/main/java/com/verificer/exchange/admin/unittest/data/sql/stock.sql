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

 Date: 03/11/2024 05:16:12 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `stock`
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `goods_id` bigint DEFAULT NULL COMMENT '商品ID',
  `spec_id` bigint DEFAULT NULL COMMENT '规格ID',
  `stage_flag` tinyint(1) DEFAULT NULL COMMENT '是否仓库库存，true-是 ，false-否',
  `rel_id` varchar(255) DEFAULT NULL COMMENT '仓库或店铺ID，视type而定',
  `count` int DEFAULT NULL COMMENT '库存量',
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_uuid_spec_id` (`rel_id`,`spec_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `stock`
-- ----------------------------
BEGIN;
INSERT INTO `stock` VALUES ('20', '20', '26', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '174', '1709984953783'), ('21', '21', '27', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '174', '1709984953824'), ('22', '21', '28', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '174', '1709984953828'), ('23', '21', '29', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '174', '1709984953834'), ('24', '21', '30', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '174', '1709984953838');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
