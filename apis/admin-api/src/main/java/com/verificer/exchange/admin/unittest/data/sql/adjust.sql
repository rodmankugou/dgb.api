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

 Date: 03/10/2024 21:41:32 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `adjust`
-- ----------------------------
DROP TABLE IF EXISTS `adjust`;
CREATE TABLE `adjust` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` tinyint DEFAULT NULL COMMENT '状态。1-待确认 11-已确认收货',
  `type` tinyint DEFAULT NULL COMMENT '1-仓库配货到仓库 2-仓库接收门店退货  3-仓库进货 11-门店从仓库入货 2-门店退货给仓库',
  `mer_id` varchar(255) NOT NULL COMMENT '商家货/门店id',
  `from_id` varchar(255) DEFAULT NULL,
  `from_name` varchar(255) DEFAULT NULL,
  `to_id` varchar(255) DEFAULT NULL,
  `to_name` varchar(255) DEFAULT NULL,
  `mer_name` varchar(255) DEFAULT NULL COMMENT '门店ID',
  `goods_id` bigint DEFAULT NULL COMMENT '商家货门店ID',
  `goods_name` varchar(255) DEFAULT NULL,
  `spec_id` bigint DEFAULT NULL COMMENT '规格ID',
  `spec_name` varchar(255) DEFAULT NULL,
  `count` int DEFAULT NULL COMMENT '数量',
  `real_count` int DEFAULT NULL COMMENT '实际进货数量',
  `remark` varchar(2000) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  `finiish_time` bigint DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`id`),
  KEY `idx_mer_id` (`mer_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8 COMMENT='调货表';

-- ----------------------------
--  Records of `adjust`
-- ----------------------------
BEGIN;
INSERT INTO `adjust` VALUES ('161', '2', '3', '6f22c403ffa94c9da21cce5b715c3cfe', null, null, '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', '深圳盐田仓库', '20', '新的测试榴莲', '26', '新的测试榴莲', '100', '100', null, null, '1710078043821', null), ('162', '2', '3', '6f22c403ffa94c9da21cce5b715c3cfe', null, null, '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', '深圳盐田仓库', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', '100', '100', null, null, '1710078044181', null), ('163', '2', '3', '6f22c403ffa94c9da21cce5b715c3cfe', null, null, '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', '深圳盐田仓库', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '28', '预选佳品 1.7-2.1KG 一人尝鲜', '100', '100', null, null, '1710078044530', null), ('164', '2', '3', '6f22c403ffa94c9da21cce5b715c3cfe', null, null, '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', '深圳盐田仓库', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '29', '预选佳品 1.3-1.7KG 一人尝鲜', '100', '100', null, null, '1710078044874', null), ('165', '2', '3', '6f22c403ffa94c9da21cce5b715c3cfe', null, null, '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', '深圳盐田仓库', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '30', '预选佳品 1.0-1.3KG 一人尝鲜', '100', '100', null, null, '1710078045220', null), ('166', '2', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', '深圳盐田仓库', '20', '新的测试榴莲', '26', '新的测试榴莲', '13', '13', null, null, '1710078045656', null), ('167', '2', '11', 'd000e3c443794213a92d61a9c6f6f6fe', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', '后海科兴总店', '20', '新的测试榴莲', '26', '新的测试榴莲', '13', '13', null, null, '1710078045658', null), ('168', '2', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', '深圳盐田仓库', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', '13', '13', null, null, '1710078045669', null), ('169', '2', '11', 'd000e3c443794213a92d61a9c6f6f6fe', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', '后海科兴总店', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', '13', '13', null, null, '1710078045671', null), ('170', '2', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', '深圳盐田仓库', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '28', '预选佳品 1.7-2.1KG 一人尝鲜', '13', '13', null, null, '1710078045683', null), ('171', '2', '11', 'd000e3c443794213a92d61a9c6f6f6fe', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', '后海科兴总店', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '28', '预选佳品 1.7-2.1KG 一人尝鲜', '13', '13', null, null, '1710078045685', null), ('172', '2', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', '深圳盐田仓库', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '29', '预选佳品 1.3-1.7KG 一人尝鲜', '13', '13', null, null, '1710078045695', null), ('173', '2', '11', 'd000e3c443794213a92d61a9c6f6f6fe', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', '后海科兴总店', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '29', '预选佳品 1.3-1.7KG 一人尝鲜', '13', '13', null, null, '1710078045701', null), ('174', '2', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', '深圳盐田仓库', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '30', '预选佳品 1.0-1.3KG 一人尝鲜', '13', '13', null, null, '1710078045711', null), ('175', '2', '11', 'd000e3c443794213a92d61a9c6f6f6fe', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', '后海科兴总店', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '30', '预选佳品 1.0-1.3KG 一人尝鲜', '13', '13', null, null, '1710078045713', null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
