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

 Date: 03/09/2024 19:49:44 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `spec`
-- ----------------------------
DROP TABLE IF EXISTS `spec`;
CREATE TABLE `spec` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `goods_id` bigint DEFAULT NULL,
  `w_price` decimal(38,18) DEFAULT NULL COMMENT '门店计重单价',
  `price` decimal(38,18) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `img` varchar(255) DEFAULT NULL COMMENT '规格图',
  `create_time` bigint DEFAULT NULL,
  `del_flag` tinyint DEFAULT NULL,
  `del_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `spec`
-- ----------------------------
BEGIN;
INSERT INTO `spec` VALUES ('26', '20', null, '11.000000000000000000', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '1709984953773', '0', null), ('27', '21', null, '478.000000000000000000', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '1709984953821', '0', null), ('28', '21', null, '388.000000000000000000', '预选佳品 1.7-2.1KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '1709984953825', '0', null), ('29', '21', null, '298.000000000000000000', '预选佳品 1.3-1.7KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '1709984953829', '0', null), ('30', '21', null, '158.000000000000000000', '预选佳品 1.0-1.3KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '1709984953835', '0', null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
