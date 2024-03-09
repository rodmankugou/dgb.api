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

 Date: 03/09/2024 19:50:28 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `goods_sta`
-- ----------------------------
DROP TABLE IF EXISTS `goods_sta`;
CREATE TABLE `goods_sta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `goods_id` bigint DEFAULT NULL,
  `spec_id` bigint DEFAULT NULL,
  `sum_sta_flag` tinyint(1) DEFAULT NULL COMMENT '是否商品总计，true-是，false-否',
  `pla_sale_count` int DEFAULT NULL COMMENT '平台销售量',
  `pla_stage_count` int DEFAULT NULL COMMENT '平台库存量',
  `shop_sale_count` int DEFAULT NULL COMMENT '店铺销售量',
  `shop_stage_count` int DEFAULT NULL COMMENT '店铺库存量',
  `evaluate_count` int DEFAULT NULL COMMENT '评论数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `goods_sta`
-- ----------------------------
BEGIN;
INSERT INTO `goods_sta` VALUES ('45', '20', null, '1', '0', '0', '0', '0', '0'), ('46', '20', '26', '0', '0', '0', '0', '0', '0'), ('47', '21', null, '1', '0', '0', '0', '0', '0'), ('48', '21', '27', '0', '0', '0', '0', '0', '0'), ('49', '21', '28', '0', '0', '0', '0', '0', '0'), ('50', '21', '29', '0', '0', '0', '0', '0', '0'), ('51', '21', '30', '0', '0', '0', '0', '0', '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
