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

 Date: 03/11/2024 06:06:53 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `shop_goods`
-- ----------------------------
DROP TABLE IF EXISTS `shop_goods`;
CREATE TABLE `shop_goods` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `goods_id` bigint DEFAULT NULL,
  `spec_id` bigint DEFAULT NULL,
  `shop_id` varchar(255) DEFAULT NULL,
  `pos_goods_id` bigint DEFAULT NULL COMMENT '收银机的商品id',
  `create_time` bigint DEFAULT NULL,
  `del_flag` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_shop_goods_spec` (`shop_id`,`goods_id`,`spec_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `shop_goods`
-- ----------------------------
BEGIN;
INSERT INTO `shop_goods` VALUES ('87', '20', '26', 'd000e3c443794213a92d61a9c6f6f6fe', '149652681311220415', '1710108360673', '0'), ('88', '21', '27', 'd000e3c443794213a92d61a9c6f6f6fe', '1018132258893959927', '1710108360687', '0'), ('89', '21', '28', 'd000e3c443794213a92d61a9c6f6f6fe', '181078658522870480', '1710108360698', '0'), ('90', '21', '29', 'd000e3c443794213a92d61a9c6f6f6fe', '984322170749340247', '1710108360710', '0'), ('91', '21', '30', 'd000e3c443794213a92d61a9c6f6f6fe', '188567848956050059', '1710108360721', '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
