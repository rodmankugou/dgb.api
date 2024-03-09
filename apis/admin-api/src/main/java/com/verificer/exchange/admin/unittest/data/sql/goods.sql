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

 Date: 03/09/2024 19:54:13 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category_id` bigint DEFAULT NULL,
  `brand_id` bigint DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `img_list` varchar(2000) DEFAULT NULL COMMENT '图片url列表，多个url以符号”@“隔开',
  `key_word` varchar(500) DEFAULT NULL COMMENT '搜索关键字',
  `search_key` varchar(255) DEFAULT NULL COMMENT '搜索关键字。key_word|name',
  `free_shipping_flag` tinyint(1) DEFAULT NULL COMMENT '是否免邮。0-是 1-否',
  `weight` decimal(38,3) DEFAULT NULL COMMENT '重量',
  `volume` decimal(38,6) DEFAULT NULL COMMENT '体积',
  `max_limit_count` int DEFAULT NULL COMMENT '限购份数',
  `min_limit_count` int DEFAULT NULL COMMENT '起售份数',
  `unit` varchar(255) DEFAULT NULL COMMENT '单位',
  `sale_flag` tinyint DEFAULT NULL COMMENT '是否上架。true-是；false-否',
  `sale_time_out_flag` tinyint DEFAULT NULL COMMENT '是否定时下架。true-是；false-否',
  `stop_sale_time` bigint DEFAULT NULL COMMENT '销售截止时间',
  `detail` text COMMENT '描述',
  `create_time` bigint DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '是否已删除',
  `del_time` bigint DEFAULT NULL,
  `rubbish_flag` tinyint(1) DEFAULT NULL COMMENT '是否已被放入回收站',
  `rubbish_time` bigint DEFAULT NULL,
  `pos_by_weight_flag` tinyint(1) DEFAULT NULL COMMENT '门店是否按重量计价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `goods`
-- ----------------------------
BEGIN;
INSERT INTO `goods` VALUES ('20', '3', '4', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '新的测试榴莲', '新的测试榴莲@新的测试榴莲', '1', '1.500', '0.010000', '100', '1', '个', '1', '0', '0', '<H1><H1>', '1709984953750', '0', null, '0', null, '0'), ('21', '3', '4', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果@马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '1', '1.500', '0.010000', '100', '1', '个', '1', '0', '0', '<H1><H1>', '1709984953817', '0', null, '0', null, '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
