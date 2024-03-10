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

 Date: 03/10/2024 15:31:58 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `addr`
-- ----------------------------
DROP TABLE IF EXISTS `addr`;
CREATE TABLE `addr` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addr` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户ID',
  `detail_addr` varchar(255) DEFAULT NULL,
  `longitude` decimal(38,18) DEFAULT NULL,
  `latitude` decimal(38,18) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `rc_name` varchar(255) DEFAULT NULL COMMENT '收货人姓名',
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `addr`
-- ----------------------------
BEGIN;
INSERT INTO `addr` VALUES ('1', '湖南省长沙市开福区通泰街万达A座', '1', '3908', '180.200000000000000000', '22.330000000000000000', '公司', '18169408966', '李东云', null);
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=utf8 COMMENT='调货表';

-- ----------------------------
--  Table structure for `brand`
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '品牌名',
  `logo_img` varchar(255) DEFAULT NULL COMMENT 'logo图，图片URL',
  `first_char` varchar(255) DEFAULT NULL COMMENT '首字母',
  `priority` int DEFAULT NULL COMMENT '排序优先级，值越小，优先级越高',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `del_flag` tinyint DEFAULT NULL COMMENT '是否已删除，true-是，false-否',
  `del_time` bigint DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `brand`
-- ----------------------------
BEGIN;
INSERT INTO `brand` VALUES ('4', '彭亨州', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/5.png', 'P', '1', '1709953575233', '0', null);
COMMIT;

-- ----------------------------
--  Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'category',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `short_name` varchar(255) DEFAULT NULL COMMENT '间称',
  `logo_img` varchar(255) DEFAULT NULL COMMENT 'logo图，图片URL',
  `search_key` varchar(255) DEFAULT NULL COMMENT '搜索关键字',
  `create_time` bigint DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `del_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `category`
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES ('3', '马来猫山王', '猫山王', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/4.png', '马来猫山王', '1709954253726', '0', null), ('4', '马来金枕头', '金枕头', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/4.png', '金枕头', '1709954253726', '0', null);
COMMIT;

-- ----------------------------
--  Table structure for `dbg_order`
-- ----------------------------
DROP TABLE IF EXISTS `dbg_order`;
CREATE TABLE `dbg_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status` tinyint DEFAULT NULL COMMENT '1-待支付 2-备货中 3-待发货 4-运输中 5-已收货（完成）6-已评价 7-已关闭 21-线下订单 22-线下自提 101-超时被取消 102-用户取消',
  `order_type` tinyint DEFAULT NULL COMMENT '1-线下订单 2-自提订单 3-平台配送订单',
  `ref_type` tinyint DEFAULT NULL COMMENT '售方类型，1-仓库 2-店铺',
  `ref_id` varchar(255) DEFAULT NULL COMMENT '仓库/店铺ID',
  `ref_name` varchar(2000) DEFAULT NULL COMMENT '店铺/仓库名称',
  `amount` decimal(38,18) DEFAULT NULL,
  `real_amount` decimal(38,18) DEFAULT NULL,
  `pay_type` int NOT NULL COMMENT '支付方式 1-微信支付 2-线下pos支付 3-现金支付',
  `pay_id` bigint DEFAULT NULL COMMENT '支付记录id',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `transit_type` tinyint DEFAULT NULL COMMENT '快的类型 快递类型 1-同城急送 2-普通快递',
  `transit_way_code` varchar(100) DEFAULT NULL COMMENT '运送方式。关联transit_way表',
  `rc_name` varchar(255) DEFAULT NULL COMMENT '收货人姓名',
  `rc_mobile` varchar(255) DEFAULT NULL COMMENT '收货人手机号码',
  `rc_addr` varchar(255) DEFAULT NULL COMMENT '收获地址',
  `rc_addr_detail` varchar(255) DEFAULT NULL COMMENT '详细收货地址',
  `rc_longitude` decimal(38,18) DEFAULT NULL,
  `rc_latitude` decimal(38,18) DEFAULT NULL,
  `activity_id` bigint DEFAULT NULL COMMENT '活动ID',
  `activity_title` varchar(255) DEFAULT NULL COMMENT '活动标题',
  `buyer_remark` varchar(255) DEFAULT NULL COMMENT '买家备注',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `order_num` varchar(255) NOT NULL COMMENT '订单号',
  `transit_order_id` varchar(255) DEFAULT NULL COMMENT '运输单ID',
  `transit_time` bigint DEFAULT NULL COMMENT '发货时间',
  `transit_order_num` varchar(255) DEFAULT NULL COMMENT '物流单号',
  `del_flag` tinyint DEFAULT NULL COMMENT '是否已删除',
  `del_time` bigint DEFAULT NULL COMMENT '是否已提货。true-是 false-否',
  `take_code` varchar(255) DEFAULT NULL,
  `take_flag` tinyint(1) DEFAULT NULL COMMENT '是否已提取。true-是；false-否',
  `take_time` bigint DEFAULT NULL COMMENT '提货时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=554 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `dbg_order`
-- ----------------------------
BEGIN;
INSERT INTO `dbg_order` VALUES ('522', '1', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709523015000', 'DX598045322781078371', null, null, null, '0', null, '829323', null, '1709529889000'), ('523', '1', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709349461000', 'DX686580113487682857', null, null, null, '0', null, '517473', null, '1709357931000'), ('524', '1', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709611252000', 'DX128043483652888661', null, null, null, '0', null, null, null, '1709624401000'), ('525', '1', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709521866000', 'DX083767934399252558', null, null, null, '0', null, null, null, '1709530121000'), ('526', '2', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709614119000', 'DX876828174314393749', null, null, null, '0', null, null, null, '1709624026000'), ('527', '2', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709438745000', 'DX839194178262404353', null, null, null, '0', null, null, null, '1709447213000'), ('528', '2', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709351899000', 'DX487955613524955586', null, null, null, '0', null, null, null, '1709368237000'), ('529', '2', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709440365000', 'DX613001720544216961', null, null, null, '0', null, null, null, '1709447658000'), ('530', '3', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709437124000', 'DX404778774571735911', null, null, null, '0', null, null, null, '1709439966000'), ('531', '3', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709435421000', 'DX719860123786393932', null, null, null, '0', null, null, null, '1709436606000'), ('532', '3', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709526789000', 'DX520911891855467186', null, null, null, '0', null, null, null, '1709539592000'), ('533', '3', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709698356000', 'DX476958886756959589', null, null, null, '0', null, null, null, '1709706418000'), ('534', '4', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709353937000', 'DX598853238547660738', null, '1709357843000', null, '0', null, null, null, null), ('535', '4', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709697900000', 'DX671252624246336691', null, '1709698543000', null, '0', null, null, null, null), ('536', '4', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709439582000', 'DX973999530968150288', null, '1709441439000', null, '0', null, null, null, null), ('537', '4', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709697346000', 'DX599501872186843553', null, '1709703293000', null, '0', null, null, null, null), ('538', '5', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709439505000', 'DX689763000207850763', null, null, null, '0', null, '160236', null, '1709442535000'), ('539', '5', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709523846000', 'DX647634135990119790', null, null, null, '0', null, '771705', null, '1709533610000'), ('540', '5', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709522256000', 'DX295093931010230040', null, null, null, '0', null, '038014', null, '1709523952000'), ('541', '5', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709698868000', 'DX754110827687948955', null, null, null, '0', null, '033406', null, '1709706942000'), ('542', '6', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709698552000', 'DX837701252724437608', null, null, null, '0', null, '236467', null, '1709700846000'), ('543', '6', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709520876000', 'DX584131372671974436', null, '1709521844000', null, '0', null, null, null, '1709535584000'), ('544', '6', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709352163000', 'DX960358747128164533', null, '1709352672000', null, '0', null, null, null, '1709354496000'), ('545', '6', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709522478000', 'DX731594130975319662', null, null, null, '0', null, '317119', null, '1709530210000'), ('546', '22', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709435854000', 'DX680251196815039574', null, null, null, '0', null, '210719', null, null), ('547', '22', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709698335000', 'DX020367824208852711', null, null, null, '0', null, '795073', null, null), ('548', '22', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709607933000', 'DX373383241782656406', null, null, null, '0', null, '967376', null, null), ('549', '22', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709438850000', 'DX955799420939264286', null, null, null, '0', null, '179359', null, null), ('550', '7', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709696314000', 'DX422299714774210272', null, '1709698544000', null, '0', null, null, null, '1709698612000'), ('551', '7', '1', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '2', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709694887000', 'DX675545951807186098', null, null, null, '0', null, null, null, null), ('552', '7', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709434644000', 'DX061095203914080691', null, '1709436291000', null, '0', null, null, null, '1709448745000'), ('553', '7', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709438102000', 'DX899359649077798332', null, '1709441404000', null, '0', null, null, null, '1709444552000');
COMMIT;

-- ----------------------------
--  Table structure for `dbg_order_log`
-- ----------------------------
DROP TABLE IF EXISTS `dbg_order_log`;
CREATE TABLE `dbg_order_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint DEFAULT NULL,
  `op_name` varchar(255) DEFAULT NULL COMMENT '操作者名称',
  `op_id` varchar(255) DEFAULT NULL COMMENT '操作者id',
  `op_entry` varchar(255) DEFAULT NULL COMMENT '操作端口',
  `order_status` tinyint DEFAULT NULL COMMENT '订单状态，参考订单表',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

-- ----------------------------
--  Table structure for `member_order`
-- ----------------------------
DROP TABLE IF EXISTS `member_order`;
CREATE TABLE `member_order` (
  `id` bigint NOT NULL,
  `member_type_id` bigint DEFAULT NULL COMMENT '会员套餐类型',
  `s_time` bigint DEFAULT NULL COMMENT '开始时间，取购买当天的开始时间',
  `e_time` bigint DEFAULT NULL COMMENT '结束时间，取一天的结束时间',
  `referrer_type` tinyint DEFAULT NULL COMMENT '引荐人类型。1-店铺 2-用户',
  `referrer_id` varchar(255) DEFAULT NULL COMMENT '引荐人ID',
  `price` decimal(38,18) DEFAULT NULL COMMENT '价格',
  `commission` decimal(38,18) DEFAULT NULL COMMENT '佣金',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `member_type`
-- ----------------------------
DROP TABLE IF EXISTS `member_type`;
CREATE TABLE `member_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `time_unit` tinyint DEFAULT NULL COMMENT '时间单位，1-年 2-月',
  `time_count` int DEFAULT NULL COMMENT '时间数量',
  `price` decimal(38,18) DEFAULT NULL COMMENT '价格',
  `del_flag` tinyint DEFAULT NULL COMMENT '是否删除。true-是，false-否',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员套餐表';

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

-- ----------------------------
--  Table structure for `order_detail`
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint DEFAULT NULL COMMENT '订单ID',
  `goods_id` bigint DEFAULT NULL COMMENT '商品ID',
  `goods_name` varchar(255) DEFAULT NULL,
  `spec_id` bigint DEFAULT NULL COMMENT '规格ID',
  `spec_name` varchar(255) DEFAULT NULL,
  `spec_img` varchar(255) DEFAULT NULL,
  `transit_fee` decimal(38,18) DEFAULT NULL COMMENT '运费',
  `price` decimal(38,18) DEFAULT NULL COMMENT '价格',
  `real_price` decimal(38,18) DEFAULT NULL COMMENT '收费时的实际价格，考虑到pos收银台可以改价格',
  `count` int DEFAULT NULL COMMENT '数量',
  `amount` decimal(38,18) DEFAULT NULL COMMENT '总价',
  `status` tinyint DEFAULT NULL COMMENT '1-未发货 2-运输中 3-已签收',
  `refund_status` tinyint DEFAULT NULL COMMENT '1-退款中 2-退款失败 3-退款成功',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=783 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `order_detail`
-- ----------------------------
BEGIN;
INSERT INTO `order_detail` VALUES ('734', '522', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '1', '11.000000000000000000', '1', null), ('735', '522', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '1', '478.000000000000000000', '1', null), ('736', '523', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '4', '44.000000000000000000', '1', null), ('737', '523', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '4', '1912.000000000000000000', '1', null), ('738', '524', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '1', '11.000000000000000000', '1', null), ('739', '524', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '1', '478.000000000000000000', '1', null), ('740', '525', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '2', '22.000000000000000000', '1', null), ('741', '526', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '2', '22.000000000000000000', '1', null), ('742', '526', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '2', '956.000000000000000000', '1', null), ('743', '527', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '4', '1912.000000000000000000', '1', null), ('744', '528', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '4', '44.000000000000000000', '1', null), ('745', '528', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '4', '1912.000000000000000000', '1', null), ('746', '529', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '4', '44.000000000000000000', '1', null), ('747', '530', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '3', '33.000000000000000000', '1', null), ('748', '530', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '3', '1434.000000000000000000', '1', null), ('749', '531', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '1', '11.000000000000000000', '1', null), ('750', '531', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '1', '478.000000000000000000', '1', null), ('751', '532', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '3', '33.000000000000000000', '1', null), ('752', '532', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '3', '1434.000000000000000000', '1', null), ('753', '533', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '4', '44.000000000000000000', '1', null), ('754', '534', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '2', '22.000000000000000000', '1', null), ('755', '535', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '2', '22.000000000000000000', '1', null), ('756', '536', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '4', '44.000000000000000000', '1', null), ('757', '536', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '4', '1912.000000000000000000', '1', null), ('758', '537', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '2', '22.000000000000000000', '1', null), ('759', '537', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '2', '956.000000000000000000', '1', null), ('760', '538', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '1', '11.000000000000000000', '3', null), ('761', '538', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '1', '478.000000000000000000', '3', null), ('762', '539', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '3', '33.000000000000000000', '3', null), ('763', '539', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '3', '1434.000000000000000000', '3', null), ('764', '540', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '4', '44.000000000000000000', '3', null), ('765', '541', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '2', '956.000000000000000000', '3', null), ('766', '542', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '4', '44.000000000000000000', '3', null), ('767', '542', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '4', '1912.000000000000000000', '3', null), ('768', '543', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '3', '1434.000000000000000000', '3', null), ('769', '544', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '2', '956.000000000000000000', '3', null), ('770', '545', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '2', '22.000000000000000000', '3', null), ('771', '545', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '2', '956.000000000000000000', '3', null), ('772', '546', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '1', '11.000000000000000000', '3', null), ('773', '546', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '1', '478.000000000000000000', '3', null), ('774', '547', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '1', '11.000000000000000000', '3', null), ('775', '548', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '2', '956.000000000000000000', '3', null), ('776', '549', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '2', '22.000000000000000000', '3', null), ('777', '549', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '2', '956.000000000000000000', '3', null), ('778', '550', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '4', '44.000000000000000000', '3', null), ('779', '551', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '4', '44.000000000000000000', '3', null), ('780', '551', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '4', '1912.000000000000000000', '3', null), ('781', '552', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '3', '33.000000000000000000', '3', null), ('782', '553', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '1', '11.000000000000000000', '3', null);
COMMIT;

-- ----------------------------
--  Table structure for `pointer`
-- ----------------------------
DROP TABLE IF EXISTS `pointer`;
CREATE TABLE `pointer` (
  `code` varchar(255) NOT NULL COMMENT '类型编码',
  `pointer` varchar(255) DEFAULT NULL COMMENT '指针',
  `upd_time` bigint DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='指针表';

-- ----------------------------
--  Table structure for `pos_sync_task`
-- ----------------------------
DROP TABLE IF EXISTS `pos_sync_task`;
CREATE TABLE `pos_sync_task` (
  `id` int NOT NULL AUTO_INCREMENT,
  `version` varchar(255) DEFAULT NULL,
  `type` int NOT NULL COMMENT '1-新增商品 2-修改/删除商品 11-新增分类 12-修改分类 21-新增会员 22-修改会员',
  `shop_id` varchar(255) NOT NULL,
  `req_data` text NOT NULL COMMENT 'json 请求数据',
  `status` tinyint NOT NULL COMMENT '1-未开始 2-失败待充实 3-成功 4-失败',
  `retry_count` int DEFAULT NULL COMMENT '已重试次数',
  `next_retry_time` bigint DEFAULT NULL COMMENT '下一次的重试时间',
  `create_time` bigint DEFAULT NULL,
  `last_call_time` bigint DEFAULT NULL,
  `finish_time` bigint DEFAULT NULL COMMENT '完成时间',
  `err_msg` text COMMENT '错误信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `refund`
-- ----------------------------
DROP TABLE IF EXISTS `refund`;
CREATE TABLE `refund` (
  `id` int NOT NULL,
  `order_id` bigint DEFAULT NULL COMMENT '订单ID',
  `user_id` varchar(255) DEFAULT NULL,
  `status` tinyint DEFAULT NULL COMMENT '状态',
  `type` tinyint DEFAULT NULL COMMENT '1-仅退款 2-退货退款',
  `ord_num` varchar(255) DEFAULT NULL COMMENT '退款单编号',
  `reason_type` tinyint DEFAULT NULL COMMENT '退款原因。参考公共接口-退款原因',
  `remark` varchar(255) DEFAULT NULL COMMENT '退款说明',
  `count` bigint DEFAULT NULL COMMENT '退款数量',
  `amount` decimal(38,18) DEFAULT NULL,
  `img_list` varchar(2000) DEFAULT NULL COMMENT '退款图片列表，多个图片以符号@隔开',
  `audit_op_id` bigint DEFAULT NULL COMMENT '退款退货初审者ID',
  `audit_time` bigint DEFAULT NULL COMMENT '退款退货初审时间',
  `audit_op_name` varchar(255) DEFAULT NULL COMMENT '退款退货初审者名称',
  `refund_op_id` bigint DEFAULT NULL COMMENT '退款审核者ID',
  `refund_op_name` varchar(255) DEFAULT NULL COMMENT '退款审核者姓名',
  `refund_time` bigint DEFAULT NULL COMMENT '退款审核时间',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `refund_log`
-- ----------------------------
DROP TABLE IF EXISTS `refund_log`;
CREATE TABLE `refund_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint DEFAULT NULL,
  `op_name` varchar(255) DEFAULT NULL COMMENT '操作者名称',
  `op_id` varchar(255) DEFAULT NULL COMMENT '操作者id',
  `order_status` tinyint DEFAULT NULL COMMENT '订单状态，参考订单表',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `refund_reason`
-- ----------------------------
DROP TABLE IF EXISTS `refund_reason`;
CREATE TABLE `refund_reason` (
  `id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `s_cart`
-- ----------------------------
DROP TABLE IF EXISTS `s_cart`;
CREATE TABLE `s_cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `goods_id` bigint DEFAULT NULL COMMENT '商品ID',
  `spec_id` bigint DEFAULT NULL COMMENT '规格id',
  `ref_type` tinyint DEFAULT NULL COMMENT '卖家类型。1-平台仓库 2-店铺',
  `ref_id` varchar(255) DEFAULT NULL COMMENT '仓库/店铺ID',
  `count` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车';

-- ----------------------------
--  Table structure for `shop`
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` varchar(255) NOT NULL COMMENT 'id，使用uuid方案',
  `parent_id` varchar(255) DEFAULT NULL COMMENT '上级店铺ID',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `login_name` varchar(100) DEFAULT NULL COMMENT '登录名',
  `login_pwd` varchar(200) DEFAULT NULL COMMENT '密码',
  `avatar_img` varchar(255) DEFAULT NULL COMMENT '店铺logo（头像）',
  `big_img` varchar(255) DEFAULT NULL COMMENT '店铺大图',
  `description` varchar(2000) DEFAULT NULL COMMENT '店铺描述',
  `commission_rate` decimal(38,18) DEFAULT NULL COMMENT '佣金率',
  `shop_level` tinyint DEFAULT NULL COMMENT '1-一级店铺 2-普通店铺',
  `adr_area1` varchar(255) DEFAULT NULL COMMENT '省编码',
  `adr_area1_name` varchar(255) DEFAULT NULL COMMENT '省名',
  `adr_area2` varchar(255) NOT NULL COMMENT '市编码',
  `adr_area2_name` varchar(255) DEFAULT NULL COMMENT '市名',
  `adr_area3` varchar(255) DEFAULT NULL COMMENT '区编码',
  `adr_area3_name` varchar(255) DEFAULT NULL COMMENT '区名',
  `adr_detail` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `full_addr` varchar(2000) DEFAULT NULL COMMENT '全地址',
  `cp_name` varchar(255) DEFAULT NULL COMMENT '联系人姓名',
  `cp_tel` varchar(255) DEFAULT NULL COMMENT '联系人电话',
  `cp_mobile` varchar(255) DEFAULT NULL COMMENT '联系人手机号码',
  `longitude` decimal(38,18) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(38,18) DEFAULT NULL COMMENT '纬度',
  `frozen_flag` tinyint(1) DEFAULT NULL COMMENT '是否冻结，0否 1-是',
  `frozen_time` bigint DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '是否已删除',
  `del_time` bigint DEFAULT NULL,
  `op_s_time` bigint DEFAULT NULL COMMENT '运营时间开始时间。毫秒时间戳，如早上8时，则为（8*60*60*1000）',
  `op_e_time` bigint DEFAULT NULL COMMENT '运营时间开始时间，毫秒时间戳。如早上8时，则为（8*60*60*1000）',
  `search_key` varchar(2000) DEFAULT NULL,
  `pos_base_url` varchar(255) DEFAULT NULL COMMENT '接口的访问域名端口',
  `pos_app_secret` varchar(255) DEFAULT NULL COMMENT '200',
  `pos_app_id` varchar(255) DEFAULT NULL COMMENT '200',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_login_name` (`login_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `shop`
-- ----------------------------
BEGIN;
INSERT INTO `shop` VALUES ('d000e3c443794213a92d61a9c6f6f6fe', null, '后海科兴总店', '4403a13a5380da7b4e76a3a09885e6c4cd3d', '068f2a7b83f0e9e5879fe022f3572dd96adbe741e1e398dac6527d008d6de8dafc6eaff8716645e126e62021e21344fed562300d45851bee8b88a628ff104082', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/7.png', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/7.png', '新媒体零售体', '0.050000000000000000', '2', '44', '广东省', '4403', '深圳市', '440305', '南山区', '后海科技大道', '广东省深圳市南山区后海科技大道', '莫文业', '0755-5433138', '13800384234', '22.310000000000000000', '180.320000000000000000', '0', null, '1709922752310', '0', null, '10000', '0', '后海科兴总店', 'https://area53-win.pospal.cn:443/', '874859095189735996', '747A09332D58D135805F190A93C84FA7');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `shop_info`
-- ----------------------------
DROP TABLE IF EXISTS `shop_info`;
CREATE TABLE `shop_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `shop_id` varchar(255) DEFAULT NULL,
  `com_license` varchar(255) DEFAULT NULL COMMENT '营业执照',
  `com_name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `com_code` varchar(255) DEFAULT NULL COMMENT '统一社会信用码',
  `com_addr` varchar(255) DEFAULT NULL COMMENT '公司所在地',
  `com_op_range` varchar(255) DEFAULT NULL COMMENT '经营范围',
  `com_lp_id_card_num` varchar(255) DEFAULT NULL COMMENT '公司法人身份证号码',
  `lp_id_card_num` varchar(255) DEFAULT NULL COMMENT '负责人/法人身份证号码',
  `lp_id_card_front` varchar(255) DEFAULT NULL COMMENT '负责人/法人身份证照姓名面。图片url。',
  `lp_id_card_back` varchar(255) DEFAULT NULL COMMENT '负责人/法人身份证照姓名面。图片url。',
  `lp_name` varchar(255) DEFAULT NULL COMMENT '负责人/法人姓名',
  `lp_mobile` varchar(255) DEFAULT NULL COMMENT '负责人/法人手机号码',
  `shop_license` varchar(255) DEFAULT NULL COMMENT '营业执照因袭-营业执照，图片URL。',
  `shop_code` varchar(255) DEFAULT NULL COMMENT '营业执照信息-统一社会信息码',
  `shop_op_range` varchar(255) DEFAULT NULL COMMENT '营业执照-经营范围',
  `bk_user_name` varchar(255) DEFAULT NULL COMMENT '银行信息-开户名',
  `bk_bank_name` varchar(255) DEFAULT NULL COMMENT '银行信息-开户支行名称',
  `bk_num` varchar(255) DEFAULT NULL COMMENT '银行信息-卡号',
  `bk_city` varchar(255) DEFAULT NULL COMMENT '银行信息-开户行所在地',
  `sbk_user_name` varchar(255) DEFAULT NULL COMMENT '结算账号信息-开户名',
  `sbk_bank_name` varchar(255) DEFAULT NULL COMMENT '结算账号信息-开户支行名称',
  `sbk_num` varchar(255) DEFAULT NULL COMMENT '结算账号信息-卡号',
  `sbk_city` varchar(255) DEFAULT NULL COMMENT '结算账号信息-开户支行所在地',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_shop_id` (`shop_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `shop_info`
-- ----------------------------
BEGIN;
INSERT INTO `shop_info` VALUES ('4', 'd000e3c443794213a92d61a9c6f6f6fe', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/3.png', '文兴果业', '3423432429', '后台中科大厦', '零售、餐饮', '440437199102311', '440437199102311', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/3.png', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/3.png', '莫文业', '13800384234', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/3.png', '823423484289293', '水果、零食', '莫文业', '平安银行后海支行', '62033018234248', '深圳', '莫文业', '中国银行福田支行', '62391083489728942', '深圳');
COMMIT;

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

-- ----------------------------
--  Table structure for `stage`
-- ----------------------------
DROP TABLE IF EXISTS `stage`;
CREATE TABLE `stage` (
  `id` varchar(255) NOT NULL COMMENT 'id，使用uuid方案',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `cp_name` varchar(100) DEFAULT NULL COMMENT '登录名',
  `cp_mobile` varchar(50) DEFAULT NULL COMMENT '密码',
  `adr_area1` varchar(255) DEFAULT NULL COMMENT '省编码',
  `adr_area1_name` varchar(255) DEFAULT NULL COMMENT '省名',
  `adr_area2` varchar(255) DEFAULT NULL COMMENT '市编码',
  `adr_area2_name` varchar(255) DEFAULT NULL COMMENT '市名',
  `adr_area3` varchar(255) DEFAULT NULL COMMENT '区编码',
  `adr_area3_name` varchar(255) DEFAULT NULL COMMENT '区名',
  `adr_detail` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `full_addr` varchar(255) DEFAULT NULL COMMENT '全地址',
  `longitude` decimal(38,18) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(38,18) DEFAULT NULL COMMENT '纬度',
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `stage`
-- ----------------------------
BEGIN;
INSERT INTO `stage` VALUES ('6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', '133993583', '李有田', '44', '广东省', '4403', '深圳市', '440305', '南山区', '南油', '广东省深圳市南山区南油', '23.200000000000000000', '173.200000000000000000', '1709925876053');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `stock`
-- ----------------------------
BEGIN;
INSERT INTO `stock` VALUES ('20', '20', '26', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '0', '1709984953783'), ('21', '21', '27', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '0', '1709984953824'), ('22', '21', '28', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '0', '1709984953828'), ('23', '21', '29', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '0', '1709984953834'), ('24', '21', '30', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '0', '1709984953838');
COMMIT;

-- ----------------------------
--  Table structure for `transit_way`
-- ----------------------------
DROP TABLE IF EXISTS `transit_way`;
CREATE TABLE `transit_way` (
  `code` varchar(255) NOT NULL COMMENT '唯一标识',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL COMMENT 'ID,使用uuid方案',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `member_flag` tinyint DEFAULT NULL COMMENT '是否会员',
  `member_s_time` bigint DEFAULT NULL COMMENT '会员有效期开始时间',
  `member_e_time` bigint DEFAULT NULL COMMENT '会员有效期结束时间',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像，图片URL',
  `reg_time` bigint DEFAULT NULL COMMENT '注册时间',
  `referrer_flag` tinyint DEFAULT NULL COMMENT '是否引荐人。false-否 ，true-是',
  `invite_count` int DEFAULT NULL COMMENT '邀请会员总数',
  `invite_commission` decimal(38,18) DEFAULT NULL COMMENT '邀请总佣金',
  `sum_order_amount` decimal(38,18) DEFAULT NULL COMMENT '总购买金额',
  `sum_order_count` int DEFAULT NULL COMMENT '总订单数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', '李东云', '1', null, null, null, null, null, null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `user_copy`
-- ----------------------------
DROP TABLE IF EXISTS `user_copy`;
CREATE TABLE `user_copy` (
  `id` varchar(255) NOT NULL COMMENT 'ID,使用uuid方案',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `member_flag` tinyint DEFAULT NULL COMMENT '是否会员',
  `member_s_time` bigint DEFAULT NULL COMMENT '会员有效期开始时间',
  `member_e_time` bigint DEFAULT NULL COMMENT '会员有效期结束时间',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像，图片URL',
  `reg_time` bigint DEFAULT NULL COMMENT '注册时间',
  `referrer_flag` tinyint DEFAULT NULL COMMENT '是否引荐人。false-否 ，true-是',
  `invite_count` int DEFAULT NULL COMMENT '邀请会员总数',
  `invite_commission` decimal(38,18) DEFAULT NULL COMMENT '邀请总佣金',
  `sum_order_amount` decimal(38,18) DEFAULT NULL COMMENT '总购买金额',
  `sum_order_count` int DEFAULT NULL COMMENT '总订单数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user_copy`
-- ----------------------------
BEGIN;
INSERT INTO `user_copy` VALUES ('1', '李东云', '1', null, null, null, null, null, null, null, null, null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
