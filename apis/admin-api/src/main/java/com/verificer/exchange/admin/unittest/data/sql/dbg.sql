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

 Date: 03/28/2024 12:24:07 PM
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
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `account`
-- ----------------------------
BEGIN;
INSERT INTO `account` VALUES ('5', '1', 'USR_INTEGRAL', '0.000000000000000000', '0.000000000000000000', '0', '1710930932823', '1710930932823'), ('7', '1', 'USR_REF', '38.000000000000000000', '0.000000000000000000', '0', '1710930932823', '1711080219430'), ('51', 'U697987100650', 'USR_INTEGRAL', '0.000000000000000000', '0.000000000000000000', '0', '1711080216298', '1711080216298'), ('52', 'U423871034851', 'USR_INTEGRAL', '0.000000000000000000', '0.000000000000000000', '0', '1711080219655', '1711080219655'), ('53', 'U227493379652', 'USR_INTEGRAL', '0.000000000000000000', '0.000000000000000000', '0', '1711136310622', '1711136310622'), ('54', 'U402351239653', 'USR_INTEGRAL', '0.000000000000000000', '0.000000000000000000', '0', '1711202188715', '1711202188715'), ('55', 'U286357567554', 'USR_INTEGRAL', '0.000000000000000000', '0.000000000000000000', '0', '1711203040467', '1711203040467'), ('56', 'U667948071055', 'USR_INTEGRAL', '0.000000000000000000', '0.000000000000000000', '0', '1711357898882', '1711357898882'), ('57', 'U869662309056', 'USR_INTEGRAL', '0.000000000000000000', '0.000000000000000000', '0', '1711421553453', '1711421553453');
COMMIT;

-- ----------------------------
--  Table structure for `account_log`
-- ----------------------------
DROP TABLE IF EXISTS `account_log`;
CREATE TABLE `account_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `customer_id` varchar(255) NOT NULL,
  `account_id` bigint NOT NULL,
  `sub_name` varchar(30) NOT NULL,
  `op_type` int NOT NULL,
  `op_amount` decimal(38,18) NOT NULL,
  `attach_id` bigint NOT NULL,
  `before_available` decimal(38,18) NOT NULL,
  `before_frozen` decimal(38,18) NOT NULL,
  `after_available` decimal(38,18) NOT NULL,
  `after_frozen` decimal(38,18) NOT NULL,
  `remark` varchar(100) NOT NULL,
  `create_time` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`) USING BTREE,
  KEY `create_time` (`create_time`) USING BTREE,
  KEY `accountId_createtime` (`account_id`,`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `account_log`
-- ----------------------------
BEGIN;
INSERT INTO `account_log` VALUES ('52', '1', '7', 'USR_REF', '1', '20.000000000000000000', '37', '20.000000000000000000', '0.000000000000000000', '40.000000000000000000', '0.000000000000000000', '引荐人分润', '1711080219354'), ('53', '1', '7', 'USR_REF', '2', '2.000000000000000000', '17', '40.000000000000000000', '0.000000000000000000', '38.000000000000000000', '2.000000000000000000', '引荐人申请提现-冻结', '1711080219407'), ('54', '1', '7', 'USR_REF', '3', '2.000000000000000000', '17', '38.000000000000000000', '2.000000000000000000', '38.000000000000000000', '0.000000000000000000', '引荐人提现', '1711080219431'), ('55', '1', '54', 'USR_INTEGRAL', '11', '2.000000000000000000', '17', '100.000000000000000000', '0.000000000000000000', '251.000000000000000000', '2.000000000000000000', 'v1@积分获取@订单编号767562602200262330', '1711080219407');
COMMIT;

-- ----------------------------
--  Table structure for `addr`
-- ----------------------------
DROP TABLE IF EXISTS `addr`;
CREATE TABLE `addr` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `adr_area1` varchar(255) DEFAULT NULL COMMENT '省编码',
  `adr_area1_name` varchar(255) DEFAULT NULL COMMENT '省名',
  `adr_area2` varchar(255) DEFAULT NULL COMMENT '市编码',
  `adr_area2_name` varchar(255) DEFAULT NULL COMMENT '市名',
  `adr_area3` varchar(255) DEFAULT NULL COMMENT '区编码',
  `adr_area3_name` varchar(255) DEFAULT NULL COMMENT '区名',
  `adr` varchar(255) DEFAULT NULL,
  `adr_detail` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `full_addr` varchar(255) DEFAULT NULL COMMENT '全地址',
  `longitude` decimal(38,18) DEFAULT NULL,
  `latitude` decimal(38,18) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `rc_name` varchar(255) DEFAULT NULL COMMENT '收货人姓名',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '是否已删除',
  `del_type` tinyint DEFAULT NULL COMMENT '1-修改 2-删除',
  `del_time` bigint DEFAULT NULL COMMENT '删除时间',
  `recent_use_time` bigint DEFAULT NULL COMMENT '最近使用时间',
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_userId_delFlag_lastUserTime` (`user_id`,`del_flag`,`recent_use_time`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户收货地址表';

-- ----------------------------
--  Records of `addr`
-- ----------------------------
BEGIN;
INSERT INTO `addr` VALUES ('1', '44', '广东省', '4403', '深圳市', '440305', '南山区', '广东省深圳市南山区通泰街万达A座', '1号', '1', '3908', '180.200000000000000000', '22.330000000000000000', '公司', '18169408966', '李东云', '0', null, null, '1', null), ('5', '11', '北京市', '1101', '市辖区', '110101', '东城区', '软件园一期栋1', '3楼303', '55', '软件园一期栋1 3楼303', '44.000000000000000000', '33.000000000000000000', '家', '15920278703', '黄志梦', '1', '1', '1711359473850', '1711359473676', '1711359473676'), ('6', '44', '广东省', '4403', '深圳市', '440305', '南山区', '软件园一期栋', '2楼202', '55', '软件园一期栋 2楼202', '33.000000000000000000', '22.000000000000000000', '公司', '15920278702', '黄志强', '1', '2', '1711359473958', '1711359473886', '1711359473886'), ('7', '44', '广东省', '4403', '深圳市', '440305', '南山区', '软件园一期栋', '2楼202', '55', '软件园一期栋 2楼202', '33.000000000000000000', '22.000000000000000000', '公司', '15920278702', '黄志强', '0', null, null, '1711359473998', '1711359473998');
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
  `count` decimal(38,18) DEFAULT NULL COMMENT '数量',
  `real_count` decimal(38,18) DEFAULT NULL COMMENT '实际进货数量',
  `remark` varchar(2000) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  `finiish_time` bigint DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`id`),
  KEY `idx_mer_id` (`mer_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=341 DEFAULT CHARSET=utf8 COMMENT='调货表';

-- ----------------------------
--  Table structure for `adjust_item`
-- ----------------------------
DROP TABLE IF EXISTS `adjust_item`;
CREATE TABLE `adjust_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL COMMENT '调货单id',
  `status` tinyint NOT NULL COMMENT '状态。1-待确认 2-已确认收货',
  `goods_id` bigint DEFAULT NULL COMMENT '商家货门店ID',
  `goods_name` varchar(255) DEFAULT NULL,
  `spec_id` bigint DEFAULT NULL COMMENT '规格ID',
  `spec_name` varchar(255) DEFAULT NULL,
  `count` decimal(38,18) DEFAULT NULL COMMENT '数量',
  `real_count` decimal(38,18) DEFAULT NULL COMMENT '实际进货数量',
  `remark` varchar(2000) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  `finish_time` bigint DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`id`),
  KEY `idx_ord_id` (`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=486 DEFAULT CHARSET=utf8 COMMENT='调货单明细表';

-- ----------------------------
--  Records of `adjust_item`
-- ----------------------------
BEGIN;
INSERT INTO `adjust_item` VALUES ('476', '44', '2', '64', '新的测试榴莲', '133', '新的测试榴莲', '1645.000000000000000000', '1645.000000000000000000', null, null, '1711567290201', '1711567290284'), ('477', '44', '2', '65', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '134', '预选佳品 2.1-2.5KG 一人尝鲜', '1098.000000000000000000', '1098.000000000000000000', null, null, '1711567290207', '1711567290284'), ('478', '44', '2', '65', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '135', '预选佳品 1.7-2.1KG 一人尝鲜', '1207.000000000000000000', '1207.000000000000000000', null, null, '1711567290212', '1711567290284'), ('479', '44', '2', '65', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '136', '预选佳品 1.3-1.7KG 一人尝鲜', '1604.000000000000000000', '1604.000000000000000000', null, null, '1711567290215', '1711567290284'), ('480', '44', '2', '65', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '137', '预选佳品 1.0-1.3KG 一人尝鲜', '2741.000000000000000000', '2741.000000000000000000', null, null, '1711567290219', '1711567290284'), ('481', '45', '2', '64', '新的测试榴莲', '133', '新的测试榴莲', '385.000000000000000000', '385.000000000000000000', null, null, '1711567290702', '1711567291184'), ('482', '45', '2', '65', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '134', '预选佳品 2.1-2.5KG 一人尝鲜', '280.000000000000000000', '280.000000000000000000', null, null, '1711567290704', '1711567291184'), ('483', '45', '2', '65', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '135', '预选佳品 1.7-2.1KG 一人尝鲜', '480.000000000000000000', '480.000000000000000000', null, null, '1711567290706', '1711567291184'), ('484', '45', '2', '65', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '136', '预选佳品 1.3-1.7KG 一人尝鲜', '500.000000000000000000', '500.000000000000000000', null, null, '1711567290708', '1711567291184'), ('485', '45', '2', '65', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '137', '预选佳品 1.0-1.3KG 一人尝鲜', '154.000000000000000000', '154.000000000000000000', null, null, '1711567290709', '1711567291184');
COMMIT;

-- ----------------------------
--  Table structure for `adjust_order`
-- ----------------------------
DROP TABLE IF EXISTS `adjust_order`;
CREATE TABLE `adjust_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ord_num` varchar(255) NOT NULL,
  `spec_count` int DEFAULT NULL COMMENT 'sku总数',
  `status` tinyint NOT NULL COMMENT '状态。1-待确认 2-部分确认 3-已确认',
  `type` tinyint DEFAULT NULL COMMENT '91-其他配至仓库 92-其他配至门店 11-仓库配置仓库 12-仓库配至门店 21-门店配至仓库  22-门店配至门店',
  `from_id` varchar(255) DEFAULT NULL,
  `from_name` varchar(255) DEFAULT NULL,
  `to_id` varchar(255) DEFAULT NULL,
  `to_name` varchar(255) DEFAULT NULL,
  `count` decimal(38,18) DEFAULT NULL COMMENT '配货数量',
  `real_count` decimal(38,18) DEFAULT NULL COMMENT '实际到货数量',
  `create_time` bigint DEFAULT NULL,
  `finish_time` bigint DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_ordNum` (`ord_num`) USING BTREE,
  KEY `idx_merId_createTime` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `adjust_order`
-- ----------------------------
BEGIN;
INSERT INTO `adjust_order` VALUES ('44', 'DA1866959933734344', '5', '3', '91', 'ADJ_OTHER', '其它', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', '8295.000000000000000000', '8295.000000000000000000', '1711567290191', '1711567290224'), ('45', 'DA6566345149100045', '5', '3', '12', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', '1799.000000000000000000', '1799.000000000000000000', '1711567290680', '1711567291152');
COMMIT;

-- ----------------------------
--  Table structure for `after_sale`
-- ----------------------------
DROP TABLE IF EXISTS `after_sale`;
CREATE TABLE `after_sale` (
  `id` bigint NOT NULL,
  `root_order_id` bigint DEFAULT NULL COMMENT '原始订单ID',
  `status` tinyint DEFAULT NULL COMMENT '状态。1-未提交 2-待审核 3-审核通过 4-审核不通过 ',
  `order_id` bigint DEFAULT NULL COMMENT '订单ID',
  `goods_id` bigint DEFAULT NULL,
  `goods_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `spec_id` bigint DEFAULT NULL,
  `spec_name` varchar(255) DEFAULT NULL COMMENT '商品规格',
  `count` decimal(38,18) DEFAULT NULL COMMENT '订单原数量',
  `apply_count` decimal(38,18) DEFAULT NULL COMMENT '申请售后数量',
  `reason` text COMMENT '原因',
  `img_list` text COMMENT '售后申请补充图片',
  `review_staff_id` bigint DEFAULT NULL,
  `review_staff_name` varchar(255) DEFAULT NULL COMMENT '审核员姓名',
  `reject_reason` text COMMENT '拒绝原因',
  `create_time` bigint DEFAULT NULL,
  `expire_time` bigint DEFAULT NULL COMMENT '售后到期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
--  Table structure for `cart`
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `goods_id` bigint NOT NULL COMMENT '商品ID',
  `spec_id` bigint DEFAULT NULL COMMENT '规格id',
  `shop_flag` tinyint(1) DEFAULT NULL COMMENT '是否店铺商品',
  `shop_id` varchar(255) DEFAULT NULL COMMENT '店铺ID',
  `count` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_userId_specId_shopId` (`user_id`,`spec_id`,`shop_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='购物车';

-- ----------------------------
--  Records of `cart`
-- ----------------------------
BEGIN;
INSERT INTO `cart` VALUES ('17', '1', '65', '137', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '3');
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
  `status` tinyint NOT NULL COMMENT '1-待支付 2-备货中 3-待发货 4-运输中 5-已收货（完成）6-已评价 7-已关闭 21-线下订单 22-线下自提 101-超时被取消 102-用户取消',
  `root_order_id` bigint NOT NULL COMMENT '补货单的最原始订单ID',
  `orig_order_id` bigint NOT NULL COMMENT '补货单的原订单ID',
  `order_type` tinyint NOT NULL COMMENT '1-线下订单 2-自提订单 3-平台配送订单 4-补货单 5-免单',
  `rel_type` tinyint NOT NULL COMMENT '售方类型，1-仓库 2-店铺',
  `rel_id` varchar(255) NOT NULL COMMENT '仓库/店铺ID',
  `ref_name` varchar(2000) NOT NULL COMMENT '店铺/仓库名称',
  `total_amount` decimal(38,18) NOT NULL COMMENT '订单总金额',
  `member_discount_amount` decimal(38,18) NOT NULL COMMENT '会员优惠金额',
  `transit_fee` decimal(38,18) NOT NULL COMMENT '运费',
  `amount` decimal(38,18) NOT NULL COMMENT '应收金额',
  `real_amount` decimal(38,18) NOT NULL COMMENT '实收金额，考虑到pose机',
  `pay_type` int NOT NULL COMMENT '支付方式 1-微信支付 2-线下pos支付 3-现金支付',
  `pay_id` bigint NOT NULL COMMENT '支付记录id',
  `member_flag` tinyint(1) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL COMMENT '用户ID，pose机的非会员订单，没有用户信息',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `transit_type` tinyint NOT NULL COMMENT '快的类型 快递类型 1-同城急送 2-普通快递 10-自提 ',
  `transit_compnay` varchar(100) DEFAULT NULL COMMENT '快递公司',
  `rc_addr_id` bigint DEFAULT NULL COMMENT '收货人地址ID',
  `rc_name` varchar(255) DEFAULT NULL COMMENT '收货人姓名',
  `rc_mobile` varchar(255) DEFAULT NULL COMMENT '收货人手机号码',
  `rc_full_addr` varchar(255) DEFAULT NULL COMMENT '全地址',
  `activity_id` bigint DEFAULT NULL COMMENT '活动ID',
  `activity_title` varchar(255) DEFAULT NULL COMMENT '活动标题',
  `buyer_remark` varchar(255) DEFAULT NULL COMMENT '买家备注',
  `create_time` bigint NOT NULL COMMENT '创建时间',
  `evaluate_time` bigint NOT NULL,
  `order_num` varchar(255) NOT NULL COMMENT '订单号',
  `transit_order_id` varchar(255) DEFAULT NULL COMMENT '运输单ID',
  `transit_time` bigint DEFAULT NULL COMMENT '发货时间',
  `transit_rc_time` bigint DEFAULT NULL COMMENT '送达时间',
  `transit_user_confirm_time` bigint DEFAULT NULL COMMENT '用户确认到货时间',
  `transit_order_num` varchar(255) DEFAULT NULL COMMENT '物流单号',
  `del_flag` tinyint NOT NULL COMMENT '是否已删除',
  `del_time` bigint DEFAULT NULL COMMENT '是否已提货。true-是 false-否',
  `take_code` varchar(255) DEFAULT NULL,
  `take_time` bigint DEFAULT NULL COMMENT '提货时间',
  `write_to_db_time` bigint DEFAULT NULL COMMENT '订单录入系统的时间，例如pose机的订单的create_time和write_to_db_time不一样',
  `pos_ord_id` bigint DEFAULT NULL COMMENT 'pos机系统中的订单id',
  `pos_cashier_uid` bigint DEFAULT NULL COMMENT 'pos机系统中的收银员ID',
  `pos_member_uid` bigint DEFAULT NULL COMMENT 'pos机系统中的会员ID',
  `version` varchar(255) NOT NULL COMMENT '订单版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_order_num` (`order_num`) USING BTREE,
  UNIQUE KEY `idx_relId_takeCode` (`rel_id`,`take_code`) USING BTREE,
  KEY `idx_userId_createTime` (`user_id`,`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=654 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dbg_order_log`
-- ----------------------------
DROP TABLE IF EXISTS `dbg_order_log`;
CREATE TABLE `dbg_order_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint DEFAULT NULL,
  `op_type` tinyint DEFAULT NULL COMMENT '操作类型。 1-创建订单 2-支付 3-提货 4-退款 5-备货 6-发货 7-派送员确认收货 8-用户确认收货 9-用户评价 10-系统默认评价 11-补发',
  `opr_name` varchar(255) DEFAULT NULL COMMENT '操作者名称',
  `opr_id` varchar(255) DEFAULT NULL COMMENT '操作者id',
  `op_entry` tinyint DEFAULT NULL COMMENT '操作者类型 1-系统 2-小程序 3-BO 4-商家 5-Pos机',
  `order_status` tinyint DEFAULT NULL COMMENT '订单状态，参考订单表',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `evaluate`
-- ----------------------------
DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE `evaluate` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status` tinyint(1) DEFAULT NULL COMMENT '1-待审核 2-已通过 3-未通过',
  `user_id` bigint DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `order_detail_id` bigint DEFAULT NULL,
  `score` int DEFAULT NULL,
  `goods_id` bigint DEFAULT NULL,
  `goods_name` varchar(255) DEFAULT NULL,
  `spec_id` bigint DEFAULT NULL,
  `spec_name` varchar(255) DEFAULT NULL,
  `spec_img` varchar(255) DEFAULT NULL,
  `comment` text,
  `img_list` text,
  `create_time` bigint DEFAULT NULL,
  `review_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_goodsId` (`goods_id`) USING BTREE,
  KEY `idx_userId_createTime` (`user_id`,`create_time`) USING BTREE,
  KEY `idx_status_create_time` (`status`,`create_time`),
  KEY `idx_orderDetailId` (`order_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `evaluate`
-- ----------------------------
BEGIN;
INSERT INTO `evaluate` VALUES ('1', '1', '1', 'Jordan', '3', null, '1', '4', '新的测试榴莲', '5', '一人享用', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '不行，给1分', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/avatar.png', '1711225688573', null), ('2', '1', '1', 'Jordan', '2', '3', '5', '4', '新的测试榴莲', '5', '一人享用', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '不错的，给5分', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/avatar.png', '1711225757239', null), ('3', '1', '1', 'Jordan', '2', '3', '3', '4', '新的测试榴莲', '5', '一人享用', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '不行，给3分', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/avatar.png', '1711225757469', null), ('4', '1', '1', 'Jordan', '2', '3', '1', '4', '新的测试榴莲', '5', '一人享用', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '不行，给1分', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/avatar.png', '1711225757528', null), ('5', '1', '1', 'Jordan', '2', '3', '5', '4', '新的测试榴莲', '5', '一人享用', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '不错的，给5分', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/avatar.png', '1711225757577', null), ('6', '1', '1', 'Jordan', '2', '3', '4', '4', '新的测试榴莲', '5', '一人享用', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '不错的，给4分', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/avatar.png', '1711225757629', null), ('7', '2', '1', 'Jordan', '2', '3', '1', '4', '新的测试榴莲', '5', '一人享用', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '不行，给1分', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/avatar.png', '1711225757684', null);
COMMIT;

-- ----------------------------
--  Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category_id` bigint DEFAULT NULL,
  `brand_id` bigint DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sub_title` varchar(255) DEFAULT NULL COMMENT '推荐词',
  `img_list` varchar(2000) DEFAULT NULL COMMENT '图片url列表，多个url以符号”@“隔开',
  `key_word` varchar(500) DEFAULT NULL COMMENT '搜索关键字',
  `search_key` varchar(255) DEFAULT NULL COMMENT '搜索关键字。key_word|name',
  `free_shipping_flag` tinyint(1) DEFAULT NULL COMMENT '是否免邮。0-是 1-否',
  `weight` decimal(38,3) DEFAULT NULL COMMENT '重量',
  `volume` decimal(38,6) DEFAULT NULL COMMENT '体积',
  `max_limit_count` decimal(38,18) DEFAULT NULL COMMENT '限购份数',
  `min_limit_count` decimal(38,18) DEFAULT NULL COMMENT '起售份数',
  `unit` varchar(255) DEFAULT NULL COMMENT '单位',
  `sale_flag` tinyint DEFAULT NULL COMMENT '是否上架。true-是；false-否',
  `sale_time_out_flag` tinyint DEFAULT NULL COMMENT '是否定时下架。true-是；false-否',
  `stop_sale_time` bigint DEFAULT NULL COMMENT '销售截止时间',
  `detail` text COMMENT '描述',
  `create_time` bigint DEFAULT NULL COMMENT '订单实际的创建时间',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '是否已删除',
  `del_time` bigint DEFAULT NULL,
  `rubbish_flag` tinyint(1) DEFAULT NULL COMMENT '是否已被放入回收站',
  `rubbish_time` bigint DEFAULT NULL,
  `sku_flag` tinyint(1) DEFAULT NULL COMMENT '是否sku库存方式',
  `pos_by_weight_flag` tinyint(1) DEFAULT NULL COMMENT '门店是否按重量计价',
  `app_sale_flag` tinyint(1) DEFAULT NULL COMMENT '是否在小程序销售',
  `shop_sale_flag` tinyint DEFAULT NULL COMMENT '是在在门店销售',
  `non_member_buy_flag` bigint DEFAULT NULL COMMENT '非会员能否购买 true-是 false-否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `goods`
-- ----------------------------
BEGIN;
INSERT INTO `goods` VALUES ('64', '3', '4', '新的测试榴莲', '经济实惠', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '新的测试榴莲', '新的测试榴莲@新的测试榴莲', '1', '1.500', '0.010000', '100.000000000000000000', '1.000000000000000000', '个', '1', '0', '0', '<H1><H1>', '1711567289346', '0', null, '0', null, '1', '0', '1', '1', '1'), ('65', '3', '4', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '口感杠杆的', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果@马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '1', '1.500', '0.010000', '100.000000000000000000', '1.000000000000000000', '个', '1', '0', '0', '<H1><H1>', '1711567289651', '0', null, '0', null, '1', '0', '1', '1', '1');
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
  `pla_sale_count` decimal(38,18) DEFAULT NULL COMMENT '平台销售量',
  `pla_stage_count` decimal(38,18) DEFAULT NULL COMMENT '平台库存量',
  `shop_sale_count` decimal(38,18) DEFAULT NULL COMMENT '店铺销售量',
  `shop_stage_count` decimal(38,18) DEFAULT NULL COMMENT '店铺库存量',
  `evaluate_count` int DEFAULT NULL COMMENT '评论数',
  PRIMARY KEY (`id`),
  KEY `idx_goods_id` (`goods_id`,`sum_sta_flag`) USING BTREE,
  KEY `idx_spec_id` (`spec_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `goods_sta`
-- ----------------------------
BEGIN;
INSERT INTO `goods_sta` VALUES ('196', '64', null, '1', '0.000000000000000000', '1260.000000000000000000', '0.000000000000000000', '385.000000000000000000', '0'), ('197', '64', '133', '0', '0.000000000000000000', '1260.000000000000000000', '0.000000000000000000', '385.000000000000000000', '0'), ('198', '65', null, '1', '0.000000000000000000', '5236.000000000000000000', '0.000000000000000000', '1414.000000000000000000', '0'), ('199', '65', '134', '0', '0.000000000000000000', '818.000000000000000000', '0.000000000000000000', '280.000000000000000000', '0'), ('200', '65', '135', '0', '0.000000000000000000', '727.000000000000000000', '0.000000000000000000', '480.000000000000000000', '0'), ('201', '65', '136', '0', '0.000000000000000000', '1104.000000000000000000', '0.000000000000000000', '500.000000000000000000', '0'), ('202', '65', '137', '0', '0.000000000000000000', '2587.000000000000000000', '0.000000000000000000', '154.000000000000000000', '0');
COMMIT;

-- ----------------------------
--  Table structure for `member_order`
-- ----------------------------
DROP TABLE IF EXISTS `member_order`;
CREATE TABLE `member_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ord_num` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `member_type_id` bigint DEFAULT NULL COMMENT '会员套餐类型',
  `status` tinyint DEFAULT NULL COMMENT '1-待支付 2-已支付 3-超时自动关闭',
  `pay_id` bigint DEFAULT NULL COMMENT '支付单id',
  `s_time` bigint DEFAULT NULL COMMENT '开始时间，取购买当天的开始时间',
  `e_time` bigint DEFAULT NULL COMMENT '结束时间，取一天的结束时间',
  `referrer_type` tinyint DEFAULT NULL COMMENT '引荐人类型。1-店铺 2-用户',
  `referrer_id` varchar(255) DEFAULT NULL COMMENT '用户uid/店铺ID',
  `price` decimal(38,18) DEFAULT NULL COMMENT '价格',
  `commission` decimal(38,18) DEFAULT NULL COMMENT '佣金，当referrer_type=2时，有值',
  `first_charge_flag` tinyint(1) DEFAULT NULL COMMENT '是否首次充值',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `pay_time` bigint DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL COMMENT '注册时用户的ip地址',
  `del_flag` tinyint DEFAULT NULL,
  `del_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_ord_num` (`ord_num`) USING BTREE,
  KEY `idx_refId_status` (`referrer_id`,`status`) USING BTREE,
  KEY `idx_userId_status` (`user_id`,`status`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `member_order`
-- ----------------------------
BEGIN;
INSERT INTO `member_order` VALUES ('37', 'DM7723646831931537', '50', '1', '2', '1', '1711080217950', '1742659199999', '2', '1', '388.000000000000000000', '20.000000000000000000', '1', '1711080217950', '1711080217980', '127.0.0.1', '0', null), ('38', 'DM8620983402429438', '51', '1', '2', '1', '1711080219664', '1742659199999', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '388.000000000000000000', null, '1', '1711080219664', '1711080219670', '127.0.0.1', '0', null), ('39', 'DM5890197494816539', '53', '1', '1', null, '1711202554748', '1742745599999', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '388.000000000000000000', null, null, '1711202554748', null, '0:0:0:0:0:0:0:1', '0', null), ('40', 'DM7544284117760640', '53', '1', '1', null, '1711202631873', '1742745599999', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '388.000000000000000000', null, null, '1711202631873', null, '0:0:0:0:0:0:0:1', '0', null), ('41', 'DM3655721205973341', '53', '1', '2', '1', '1711202694471', '1742745599999', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '388.000000000000000000', null, '1', '1711202694471', '1711202694903', '0:0:0:0:0:0:0:1', '0', null);
COMMIT;

-- ----------------------------
--  Table structure for `member_type`
-- ----------------------------
DROP TABLE IF EXISTS `member_type`;
CREATE TABLE `member_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `sub_title` varchar(255) DEFAULT NULL COMMENT '子标题',
  `time_unit` tinyint DEFAULT NULL COMMENT '时间单位，1-年 2-月 3-日',
  `time_count` int DEFAULT NULL COMMENT '时间数量',
  `price` decimal(38,18) DEFAULT NULL COMMENT '价格',
  `del_flag` tinyint DEFAULT NULL COMMENT '是否删除。true-是，false-否',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='会员套餐表';

-- ----------------------------
--  Records of `member_type`
-- ----------------------------
BEGIN;
INSERT INTO `member_type` VALUES ('1', '包年', '低至¥32.3元/月', '1', '1', '388.000000000000000000', '0', '1');
COMMIT;

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
  `count` decimal(38,18) DEFAULT NULL COMMENT '数量',
  `amount` decimal(38,18) DEFAULT NULL COMMENT '总价',
  `real_amount` decimal(38,18) DEFAULT NULL,
  `status` tinyint DEFAULT NULL COMMENT '1-未发货 2-运输中 3-已签收',
  `refund_status` tinyint DEFAULT NULL COMMENT '1-退款中 2-退款失败 3-退款成功',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=926 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `order_detail`
-- ----------------------------
BEGIN;
INSERT INTO `order_detail` VALUES ('873', '619', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '4.000000000000000000', '44.000000000000000000', null, '1', null), ('874', '619', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '4.000000000000000000', '1912.000000000000000000', null, '1', null), ('875', '620', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '4.000000000000000000', '44.000000000000000000', null, '1', null), ('876', '620', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '4.000000000000000000', '1912.000000000000000000', null, '1', null), ('877', '621', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '4.000000000000000000', '44.000000000000000000', null, '1', null), ('878', '621', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '4.000000000000000000', '1912.000000000000000000', null, '1', null), ('879', '622', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '4.000000000000000000', '44.000000000000000000', null, '1', null), ('880', '623', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '1.000000000000000000', '478.000000000000000000', null, '1', null), ('881', '624', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '1.000000000000000000', '11.000000000000000000', null, '1', null), ('882', '625', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '3.000000000000000000', '1434.000000000000000000', null, '1', null), ('883', '626', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '4.000000000000000000', '44.000000000000000000', null, '1', null), ('884', '627', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '1.000000000000000000', '11.000000000000000000', null, '1', null), ('885', '627', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '1.000000000000000000', '478.000000000000000000', null, '1', null), ('886', '628', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '3.000000000000000000', '33.000000000000000000', null, '1', null), ('887', '628', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '3.000000000000000000', '1434.000000000000000000', null, '1', null), ('888', '629', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '1.000000000000000000', '11.000000000000000000', null, '1', null), ('889', '629', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '1.000000000000000000', '478.000000000000000000', null, '1', null), ('890', '630', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '4.000000000000000000', '44.000000000000000000', null, '1', null), ('891', '631', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '4.000000000000000000', '44.000000000000000000', null, '1', null), ('892', '631', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '4.000000000000000000', '1912.000000000000000000', null, '1', null), ('893', '632', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '4.000000000000000000', '44.000000000000000000', null, '1', null), ('894', '633', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '4.000000000000000000', '44.000000000000000000', null, '1', null), ('895', '633', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '4.000000000000000000', '1912.000000000000000000', null, '1', null), ('896', '634', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '1.000000000000000000', '478.000000000000000000', null, '1', null), ('897', '635', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '2.000000000000000000', '22.000000000000000000', null, '3', null), ('898', '635', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '2.000000000000000000', '956.000000000000000000', null, '3', null), ('899', '636', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '2.000000000000000000', '22.000000000000000000', null, '3', null), ('900', '636', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '2.000000000000000000', '956.000000000000000000', null, '3', null), ('901', '637', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '2.000000000000000000', '956.000000000000000000', null, '3', null), ('902', '638', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '4.000000000000000000', '44.000000000000000000', null, '3', null), ('903', '638', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '4.000000000000000000', '1912.000000000000000000', null, '3', null), ('904', '639', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '3.000000000000000000', '33.000000000000000000', null, '3', null), ('905', '639', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '3.000000000000000000', '1434.000000000000000000', null, '3', null), ('906', '640', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '3.000000000000000000', '33.000000000000000000', null, '3', null), ('907', '641', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '3.000000000000000000', '1434.000000000000000000', null, '3', null), ('908', '642', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '1.000000000000000000', '11.000000000000000000', null, '3', null), ('909', '642', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '1.000000000000000000', '478.000000000000000000', null, '3', null), ('910', '643', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '4.000000000000000000', '44.000000000000000000', null, '3', null), ('911', '643', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '4.000000000000000000', '1912.000000000000000000', null, '3', null), ('912', '644', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '3.000000000000000000', '33.000000000000000000', null, '3', null), ('913', '645', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '2.000000000000000000', '22.000000000000000000', null, '3', null), ('914', '645', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '2.000000000000000000', '956.000000000000000000', null, '3', null), ('915', '646', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '4.000000000000000000', '44.000000000000000000', null, '3', null), ('916', '646', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '4.000000000000000000', '1912.000000000000000000', null, '3', null), ('917', '647', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '1.000000000000000000', '478.000000000000000000', null, '3', null), ('918', '648', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '3.000000000000000000', '33.000000000000000000', null, '3', null), ('919', '649', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '1.000000000000000000', '11.000000000000000000', null, '3', null), ('920', '649', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '1.000000000000000000', '478.000000000000000000', null, '3', null), ('921', '650', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '3.000000000000000000', '33.000000000000000000', null, '3', null), ('922', '650', '21', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '27', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '478.000000000000000000', null, '3.000000000000000000', '1434.000000000000000000', null, '3', null), ('923', '651', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '1.000000000000000000', '11.000000000000000000', null, '3', null), ('924', '652', '20', '新的测试榴莲', '26', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '0.000000000000000000', '11.000000000000000000', null, '1.000000000000000000', '11.000000000000000000', null, '3', null);
COMMIT;

-- ----------------------------
--  Table structure for `pay`
-- ----------------------------
DROP TABLE IF EXISTS `pay`;
CREATE TABLE `pay` (
  `id` bigint NOT NULL,
  `type` tinyint DEFAULT NULL COMMENT '支付类型，1-微信支付 ',
  `amount` decimal(38,18) DEFAULT NULL,
  `out_ord_num` varchar(255) DEFAULT NULL COMMENT '支付平台的支付订单ID',
  `ord_num` int DEFAULT NULL COMMENT '平台内部使用的订单号',
  `pay_time` bigint DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_pay_ord_num` (`ord_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `pla_income_log`
-- ----------------------------
DROP TABLE IF EXISTS `pla_income_log`;
CREATE TABLE `pla_income_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` tinyint DEFAULT NULL COMMENT '类型 1-会员注册 2-门店结算',
  `shop_id` varchar(255) DEFAULT NULL COMMENT '店铺ID',
  `ord_id` bigint DEFAULT NULL COMMENT '订单ID',
  `ord_num` varchar(255) DEFAULT NULL COMMENT '订单号',
  `amount` decimal(38,18) DEFAULT NULL COMMENT '金额，可能为负数',
  `income_flag` tinyint(1) DEFAULT NULL COMMENT '是否为收入 true-是 false-否',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `pla_income_log`
-- ----------------------------
BEGIN;
INSERT INTO `pla_income_log` VALUES ('37', '1', null, '37', 'DM7723646831931537', '388.000000000000000000', '1', null, '会员注册，U697987100650, 15980521260, U697987100650'), ('38', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '38', 'DM8620983402429438', '388.000000000000000000', '1', null, '会员注册，U423871034851, 15965547729, U423871034851'), ('39', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '38', 'DS2257294411843538', '1.620000000000000000', '0', null, '会员佣金, 后海科兴总店, 账单编号DS2257294411843538'), ('40', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '39', 'DS7716308380941939', '1.620000000000000000', '0', null, '会员佣金, 后海科兴总店, 账单编号DS7716308380941939'), ('41', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '40', 'DS7848994338720040', '1.620000000000000000', '0', null, '会员佣金, 后海科兴总店, 账单编号DS7848994338720040'), ('42', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '41', 'DS1561777321802741', '1.620000000000000000', '0', null, '会员佣金, 后海科兴总店, 账单编号DS1561777321802741'), ('43', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '42', 'DS2229275163553342', '1.620000000000000000', '0', null, '会员佣金, 后海科兴总店, 账单编号DS2229275163553342'), ('44', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '43', 'DS3489184891046043', '1.620000000000000000', '0', null, '会员佣金, 后海科兴总店, 账单编号DS3489184891046043'), ('45', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '44', 'DS5992081198640944', '1.620000000000000000', '0', null, '会员佣金, 后海科兴总店, 账单编号DS5992081198640944'), ('46', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '45', 'DS6222384706855445', '1.620000000000000000', '0', null, '会员佣金, 后海科兴总店, 账单编号DS6222384706855445'), ('47', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '46', 'DS9311227322960346', '1.620000000000000000', '0', null, '会员佣金, 后海科兴总店, 账单编号DS9311227322960346'), ('48', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '47', 'DS1469648355091347', '1.620000000000000000', '0', null, '会员佣金, 后海科兴总店, 账单编号DS1469648355091347'), ('49', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '48', 'DS9242513003740348', '1.620000000000000000', '0', null, '会员佣金, 后海科兴总店, 账单编号DS9242513003740348'), ('50', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '49', 'DS7306782997335149', '1.620000000000000000', '0', null, '会员佣金, 后海科兴总店, 账单编号DS7306782997335149'), ('51', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '41', 'DM3655721205973341', '388.000000000000000000', '1', null, '会员注册，U402351239653, 15920233301, U402351239653');
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
--  Records of `pointer`
-- ----------------------------
BEGIN;
INSERT INTO `pointer` VALUES ('POS_SHOP_ORDER_SYNC_d000e3c443794213a92d61a9c6f6f6fe', '1710108918999', '1');
COMMIT;

-- ----------------------------
--  Table structure for `pos_pay_item`
-- ----------------------------
DROP TABLE IF EXISTS `pos_pay_item`;
CREATE TABLE `pos_pay_item` (
  `id` bigint NOT NULL,
  `order_id` bigint DEFAULT NULL,
  `pay_type_code` varchar(255) DEFAULT NULL,
  `amount` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银豹收银明细表。Pos机中的一个订单可能有组合收款方式，例如一个订单面额100，现金收50，微信支付收50';

-- ----------------------------
--  Table structure for `pos_sync_task`
-- ----------------------------
DROP TABLE IF EXISTS `pos_sync_task`;
CREATE TABLE `pos_sync_task` (
  `id` int NOT NULL AUTO_INCREMENT,
  `version` varchar(255) DEFAULT NULL,
  `rel_id` bigint DEFAULT NULL COMMENT '关联ID，如商品/分类ID',
  `type` int NOT NULL COMMENT '1-新增商品 2-修改/删除商品 11-新增分类 12-修改分类 21-新增会员 22-修改会员',
  `shop_id` varchar(255) DEFAULT NULL,
  `req_data` text NOT NULL COMMENT 'json 请求数据',
  `status` tinyint NOT NULL COMMENT '1-未开始 2-失败待充实 3-成功 4-失败',
  `retry_count` int DEFAULT NULL COMMENT '已重试次数',
  `next_retry_time` bigint DEFAULT NULL COMMENT '下一次的重试时间',
  `create_time` bigint DEFAULT NULL,
  `last_call_time` bigint DEFAULT NULL,
  `finish_time` bigint DEFAULT NULL COMMENT '完成时间',
  `err_msg` text COMMENT '错误信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=198 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `pos_sync_task`
-- ----------------------------
BEGIN;
INSERT INTO `pos_sync_task` VALUES ('193', 'V1.0', '133', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '{\"goodsId\":64,\"mainSpec\":false,\"name\":\"新的测试榴莲\",\"posCatId\":1709867459998183336,\"price\":11.000000000000000000,\"specName\":\"新的测试榴莲\",\"weighing\":false}', '1', '0', null, '1711567291268', null, null, null), ('194', 'V1.0', '134', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '{\"goodsId\":65,\"mainSpec\":false,\"name\":\"马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果\",\"posCatId\":1709867459998183336,\"price\":478.000000000000000000,\"specName\":\"预选佳品 2.1-2.5KG 一人尝鲜\",\"weighing\":false}', '1', '0', null, '1711567291273', null, null, null), ('195', 'V1.0', '135', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '{\"goodsId\":65,\"mainSpec\":false,\"name\":\"马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果\",\"posCatId\":1709867459998183336,\"price\":388.000000000000000000,\"specName\":\"预选佳品 1.7-2.1KG 一人尝鲜\",\"weighing\":false}', '1', '0', null, '1711567291276', null, null, null), ('196', 'V1.0', '136', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '{\"goodsId\":65,\"mainSpec\":false,\"name\":\"马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果\",\"posCatId\":1709867459998183336,\"price\":298.000000000000000000,\"specName\":\"预选佳品 1.3-1.7KG 一人尝鲜\",\"weighing\":false}', '1', '0', null, '1711567291279', null, null, null), ('197', 'V1.0', '137', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '{\"goodsId\":65,\"mainSpec\":false,\"name\":\"马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果\",\"posCatId\":1709867459998183336,\"price\":158.000000000000000000,\"specName\":\"预选佳品 1.0-1.3KG 一人尝鲜\",\"weighing\":false}', '1', '0', null, '1711567291282', null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `referrer_withdraw`
-- ----------------------------
DROP TABLE IF EXISTS `referrer_withdraw`;
CREATE TABLE `referrer_withdraw` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ord_num` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `amount` decimal(38,18) DEFAULT NULL,
  `status` tinyint DEFAULT NULL COMMENT '1-待审批 2-审批不通过 3-审批通过 4-已打款',
  `remark` text,
  `create_time` bigint DEFAULT NULL,
  `review_time` bigint DEFAULT NULL,
  `review_staff_id` bigint DEFAULT NULL,
  `review_staff_name` varchar(255) DEFAULT NULL,
  `certificate_img` varchar(255) DEFAULT NULL COMMENT '转账凭证图',
  `transfer_staff_id` bigint DEFAULT NULL COMMENT '转账人ID',
  `transfer_staff_name` varchar(255) DEFAULT NULL COMMENT '转账人昵称',
  `transfer_time` bigint DEFAULT NULL COMMENT '转账时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_ord_num` (`ord_num`) USING BTREE,
  KEY `idx_userId_createTime` (`user_id`,`create_time`),
  KEY `create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `referrer_withdraw`
-- ----------------------------
BEGIN;
INSERT INTO `referrer_withdraw` VALUES ('17', 'DW7238089542088417', '1', '2.000000000000000000', '4', null, '1711080219402', '1711080219418', '1', 'Rodman', null, '1', 'Rodman', '1711080219426');
COMMIT;

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
--  Table structure for `revise`
-- ----------------------------
DROP TABLE IF EXISTS `revise`;
CREATE TABLE `revise` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `stage_flag` tinyint(1) DEFAULT NULL COMMENT '是否仓库',
  `rel_id` varchar(255) DEFAULT NULL COMMENT '仓库/门店ID',
  `rel_name` varchar(255) DEFAULT NULL COMMENT '商铺/仓库ID',
  `goods_id` bigint DEFAULT NULL,
  `goods_name` varchar(255) DEFAULT NULL,
  `spec_id` bigint DEFAULT NULL,
  `spec_name` varchar(255) DEFAULT NULL,
  `spec_img` varchar(255) DEFAULT NULL,
  `add_flag` tinyint(1) DEFAULT NULL COMMENT '是否增加',
  `count` decimal(38,18) DEFAULT NULL COMMENT '数量',
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='库存修正表（对应BO的补货/减货）';

-- ----------------------------
--  Records of `revise`
-- ----------------------------
BEGIN;
INSERT INTO `revise` VALUES ('1', '0', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', '31', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '55', '预选佳品 1.0-1.3KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '1', '2.000000000000000000', '1711221703143');
COMMIT;

-- ----------------------------
--  Table structure for `settle`
-- ----------------------------
DROP TABLE IF EXISTS `settle`;
CREATE TABLE `settle` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` tinyint DEFAULT NULL COMMENT '关联类型 1-店铺会员',
  `shop_id` varchar(255) DEFAULT NULL,
  `child_shop_id` varchar(255) DEFAULT NULL COMMENT '下属门店',
  `rel_id` bigint DEFAULT NULL COMMENT '管理id，例如member_oerder.id',
  `commission_rate` decimal(38,18) DEFAULT NULL,
  `total_phase` int DEFAULT NULL COMMENT '总期数',
  `cal_pahse` int DEFAULT NULL COMMENT '已生成结算的期数',
  `settle_phase` int DEFAULT NULL COMMENT '已结算的期数',
  `total_amount` decimal(38,18) DEFAULT NULL COMMENT '总结算金额',
  `phase_amount` decimal(38,18) DEFAULT NULL COMMENT '每期结算金额',
  `title` varchar(255) DEFAULT NULL,
  `next_cal_time` bigint DEFAULT NULL COMMENT '下一期结算时间',
  `finish_cal_flag` tinyint(1) DEFAULT NULL COMMENT '是否已完成所有计算',
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_nextCalTIme_CalFinishFlag` (`next_cal_time`,`finish_cal_flag`) USING BTREE,
  KEY `idx_shopId_nextCalTime_CalFinishFlag` (`shop_id`,`next_cal_time`,`finish_cal_flag`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `settle`
-- ----------------------------
BEGIN;
INSERT INTO `settle` VALUES ('12', '1', 'd000e3c443794213a92d61a9c6f6f6fe', null, '38', '0.050000000000000000', '12', '12', '0', '19.400000000000000000', '1.620000000000000000', 'U423871034851, 15965547729, U423871034851,', '1693497600000', '1', '1711080220216'), ('13', '1', 'd000e3c443794213a92d61a9c6f6f6fe', null, '41', '0.050000000000000000', '12', '0', '0', '19.400000000000000000', '1.620000000000000000', 'U402351239653, 15920233301, U402351239653,', '1711900800000', '0', '1711202697319');
COMMIT;

-- ----------------------------
--  Table structure for `settle_item`
-- ----------------------------
DROP TABLE IF EXISTS `settle_item`;
CREATE TABLE `settle_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint DEFAULT NULL,
  `settle_id` bigint DEFAULT NULL,
  `settle_phase` int DEFAULT NULL,
  `shop_id` varchar(255) DEFAULT NULL,
  `child_shop_id` varchar(255) DEFAULT NULL COMMENT '下属门店',
  `title` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `year` int DEFAULT NULL,
  `month` int DEFAULT NULL,
  `settle_flag` tinyint DEFAULT NULL COMMENT '是否已结算 true-是 false-否',
  `amount` decimal(38,18) DEFAULT NULL,
  `commission_rate` decimal(38,18) DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `settle_item`
-- ----------------------------
BEGIN;
INSERT INTO `settle_item` VALUES ('42', '38', '12', '1', 'd000e3c443794213a92d61a9c6f6f6fe', null, '会员分期', 'U423871034851, 15965547729, U423871034851,1/12期', '2022', '9', '0', '1.620000000000000000', '0.050000000000000000', '1711080225536'), ('43', '39', '12', '2', 'd000e3c443794213a92d61a9c6f6f6fe', null, '会员分期', 'U423871034851, 15965547729, U423871034851,2/12期', '2022', '10', '0', '1.620000000000000000', '0.050000000000000000', '1711080225565'), ('44', '40', '12', '3', 'd000e3c443794213a92d61a9c6f6f6fe', null, '会员分期', 'U423871034851, 15965547729, U423871034851,3/12期', '2022', '11', '0', '1.620000000000000000', '0.050000000000000000', '1711080225585'), ('45', '41', '12', '4', 'd000e3c443794213a92d61a9c6f6f6fe', null, '会员分期', 'U423871034851, 15965547729, U423871034851,4/12期', '2022', '12', '0', '1.620000000000000000', '0.050000000000000000', '1711080225604'), ('46', '42', '12', '5', 'd000e3c443794213a92d61a9c6f6f6fe', null, '会员分期', 'U423871034851, 15965547729, U423871034851,5/12期', '2023', '1', '0', '1.620000000000000000', '0.050000000000000000', '1711080225622'), ('47', '43', '12', '6', 'd000e3c443794213a92d61a9c6f6f6fe', null, '会员分期', 'U423871034851, 15965547729, U423871034851,6/12期', '2023', '2', '0', '1.620000000000000000', '0.050000000000000000', '1711080225640'), ('48', '44', '12', '7', 'd000e3c443794213a92d61a9c6f6f6fe', null, '会员分期', 'U423871034851, 15965547729, U423871034851,7/12期', '2023', '3', '0', '1.620000000000000000', '0.050000000000000000', '1711080225660'), ('49', '45', '12', '8', 'd000e3c443794213a92d61a9c6f6f6fe', null, '会员分期', 'U423871034851, 15965547729, U423871034851,8/12期', '2023', '4', '0', '1.620000000000000000', '0.050000000000000000', '1711080225681'), ('50', '46', '12', '9', 'd000e3c443794213a92d61a9c6f6f6fe', null, '会员分期', 'U423871034851, 15965547729, U423871034851,9/12期', '2023', '5', '0', '1.620000000000000000', '0.050000000000000000', '1711080225701'), ('51', '47', '12', '10', 'd000e3c443794213a92d61a9c6f6f6fe', null, '会员分期', 'U423871034851, 15965547729, U423871034851,10/12期', '2023', '6', '0', '1.620000000000000000', '0.050000000000000000', '1711080225718'), ('52', '48', '12', '11', 'd000e3c443794213a92d61a9c6f6f6fe', null, '会员分期', 'U423871034851, 15965547729, U423871034851,11/12期', '2023', '7', '0', '1.620000000000000000', '0.050000000000000000', '1711080225734'), ('53', '49', '12', '12', 'd000e3c443794213a92d61a9c6f6f6fe', null, '会员分期', 'U423871034851, 15965547729, U423871034851,12/12期', '2023', '8', '0', '1.620000000000000000', '0.050000000000000000', '1711080225751');
COMMIT;

-- ----------------------------
--  Table structure for `settle_order`
-- ----------------------------
DROP TABLE IF EXISTS `settle_order`;
CREATE TABLE `settle_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ord_num` varchar(255) DEFAULT NULL,
  `shop_id` varchar(255) DEFAULT NULL,
  `commission_rate` decimal(38,18) DEFAULT NULL COMMENT '门店佣金比例',
  `amount` decimal(38,18) DEFAULT NULL COMMENT '结算金额',
  `count` int DEFAULT NULL COMMENT '份数',
  `year` int DEFAULT NULL,
  `month` int DEFAULT NULL,
  `settle_time` bigint DEFAULT NULL,
  `settle_flag` tinyint DEFAULT NULL COMMENT '是否已结算，0-否 1-是',
  `cal_finish_flag` tinyint DEFAULT NULL COMMENT '是否计算完毕',
  `certificate_img` varchar(255) DEFAULT NULL,
  `transfer_staff_id` bigint DEFAULT NULL,
  `transfer_staff_name` varchar(255) DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_shopId_year_month` (`shop_id`,`year`,`month`) USING BTREE,
  UNIQUE KEY `idx_ord_num` (`ord_num`) USING BTREE,
  KEY `idx_year_month` (`year`,`month`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `settle_order`
-- ----------------------------
BEGIN;
INSERT INTO `settle_order` VALUES ('38', 'DS2257294411843538', 'd000e3c443794213a92d61a9c6f6f6fe', '0.050000000000000000', '1.620000000000000000', '1', '2022', '9', null, '0', '1', null, null, null, '1711080225516'), ('39', 'DS7716308380941939', 'd000e3c443794213a92d61a9c6f6f6fe', '0.050000000000000000', '1.620000000000000000', '1', '2022', '10', null, '0', '1', null, null, null, '1711080225557'), ('40', 'DS7848994338720040', 'd000e3c443794213a92d61a9c6f6f6fe', '0.050000000000000000', '1.620000000000000000', '1', '2022', '11', null, '0', '1', null, null, null, '1711080225578'), ('41', 'DS1561777321802741', 'd000e3c443794213a92d61a9c6f6f6fe', '0.050000000000000000', '1.620000000000000000', '1', '2022', '12', null, '0', '1', null, null, null, '1711080225597'), ('42', 'DS2229275163553342', 'd000e3c443794213a92d61a9c6f6f6fe', '0.050000000000000000', '1.620000000000000000', '1', '2023', '1', null, '0', '1', null, null, null, '1711080225616'), ('43', 'DS3489184891046043', 'd000e3c443794213a92d61a9c6f6f6fe', '0.050000000000000000', '1.620000000000000000', '1', '2023', '2', null, '0', '1', null, null, null, '1711080225634'), ('44', 'DS5992081198640944', 'd000e3c443794213a92d61a9c6f6f6fe', '0.050000000000000000', '1.620000000000000000', '1', '2023', '3', null, '0', '1', null, null, null, '1711080225653'), ('45', 'DS6222384706855445', 'd000e3c443794213a92d61a9c6f6f6fe', '0.050000000000000000', '1.620000000000000000', '1', '2023', '4', null, '0', '1', null, null, null, '1711080225675'), ('46', 'DS9311227322960346', 'd000e3c443794213a92d61a9c6f6f6fe', '0.050000000000000000', '1.620000000000000000', '1', '2023', '5', null, '0', '1', null, null, null, '1711080225694'), ('47', 'DS1469648355091347', 'd000e3c443794213a92d61a9c6f6f6fe', '0.050000000000000000', '1.620000000000000000', '1', '2023', '6', null, '0', '1', null, null, null, '1711080225712'), ('48', 'DS9242513003740348', 'd000e3c443794213a92d61a9c6f6f6fe', '0.050000000000000000', '1.620000000000000000', '1', '2023', '7', null, '0', '1', null, null, null, '1711080225728'), ('49', 'DS7306782997335149', 'd000e3c443794213a92d61a9c6f6f6fe', '0.050000000000000000', '1.620000000000000000', '1', '2023', '8', null, '0', '1', null, null, null, '1711080225746');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `shop_goods`
-- ----------------------------
BEGIN;
INSERT INTO `shop_goods` VALUES ('92', '64', '133', 'd000e3c443794213a92d61a9c6f6f6fe', null, '1711567291189', '0'), ('93', '65', '134', 'd000e3c443794213a92d61a9c6f6f6fe', null, '1711567291272', '0'), ('94', '65', '135', 'd000e3c443794213a92d61a9c6f6f6fe', null, '1711567291275', '0'), ('95', '65', '136', 'd000e3c443794213a92d61a9c6f6f6fe', null, '1711567291278', '0'), ('96', '65', '137', 'd000e3c443794213a92d61a9c6f6f6fe', null, '1711567291281', '0');
COMMIT;

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
  `w_ori_price` decimal(38,18) DEFAULT NULL,
  `price` decimal(38,18) DEFAULT NULL,
  `ori_price` decimal(38,18) DEFAULT NULL COMMENT '原价',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `img` varchar(255) DEFAULT NULL COMMENT '规格图',
  `create_time` bigint DEFAULT NULL,
  `del_flag` tinyint DEFAULT NULL,
  `del_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `spec`
-- ----------------------------
BEGIN;
INSERT INTO `spec` VALUES ('133', '64', null, null, '11.000000000000000000', '13.000000000000000000', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '1711567289377', '0', null), ('134', '65', null, null, '478.000000000000000000', '578.000000000000000000', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '1711567289657', '0', null), ('135', '65', null, null, '388.000000000000000000', '478.000000000000000000', '预选佳品 1.7-2.1KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '1711567289660', '0', null), ('136', '65', null, null, '298.000000000000000000', '358.000000000000000000', '预选佳品 1.3-1.7KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '1711567289664', '0', null), ('137', '65', null, null, '158.000000000000000000', '238.000000000000000000', '预选佳品 1.0-1.3KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '1711567289670', '0', null);
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
  `sku_flag` bigint DEFAULT NULL COMMENT '是否sku库存',
  `stage_flag` tinyint(1) DEFAULT NULL COMMENT '是否仓库库存，true-是 ，false-否',
  `rel_id` varchar(255) DEFAULT NULL COMMENT '仓库或店铺ID，视type而定',
  `count` decimal(38,18) DEFAULT NULL COMMENT '库存量',
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_uuid_spec_id` (`rel_id`,`spec_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `stock`
-- ----------------------------
BEGIN;
INSERT INTO `stock` VALUES ('180', '64', '133', '1', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1260.000000000000000000', '1711567290230'), ('181', '65', '134', '1', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '818.000000000000000000', '1711567290236'), ('182', '65', '135', '1', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '727.000000000000000000', '1711567290239'), ('183', '65', '136', '1', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1104.000000000000000000', '1711567290241'), ('184', '65', '137', '1', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2587.000000000000000000', '1711567290244'), ('185', '64', '133', '1', '0', 'd000e3c443794213a92d61a9c6f6f6fe', '385.000000000000000000', '1711567291155'), ('186', '65', '134', '1', '0', 'd000e3c443794213a92d61a9c6f6f6fe', '280.000000000000000000', '1711567291159'), ('187', '65', '135', '1', '0', 'd000e3c443794213a92d61a9c6f6f6fe', '480.000000000000000000', '1711567291161'), ('188', '65', '136', '1', '0', 'd000e3c443794213a92d61a9c6f6f6fe', '500.000000000000000000', '1711567291163'), ('189', '65', '137', '1', '0', 'd000e3c443794213a92d61a9c6f6f6fe', '154.000000000000000000', '1711567291165');
COMMIT;

-- ----------------------------
--  Table structure for `stock_log`
-- ----------------------------
DROP TABLE IF EXISTS `stock_log`;
CREATE TABLE `stock_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `stock_id` bigint DEFAULT NULL,
  `goods_id` bigint DEFAULT NULL,
  `spec_id` bigint DEFAULT NULL,
  `op_type` tinyint DEFAULT NULL COMMENT '1-配货入库 2-配货出库 3-补货 4-报损 5-下单减库存 6-取消订单加库存 7-退款减库存',
  `rel_id` varchar(255) DEFAULT NULL COMMENT '关联ID，例如订单ID',
  `count` decimal(38,18) DEFAULT NULL COMMENT '增加/减少量',
  `before_stock` decimal(38,18) DEFAULT NULL COMMENT '操作后库存',
  `after_stock` decimal(38,18) DEFAULT NULL COMMENT '操作前库存',
  `create_time` bigint DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `stock_log`
-- ----------------------------
BEGIN;
INSERT INTO `stock_log` VALUES ('1', '120', '28', '46', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1835.000000000000000000', '0.000000000000000000', '1835.000000000000000000', '1711128348842', '配货入货, 其它 -> 深圳盐田仓库'), ('2', '121', '29', '47', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2459.000000000000000000', '0.000000000000000000', '2459.000000000000000000', '1711128348850', '配货入货, 其它 -> 深圳盐田仓库'), ('3', '122', '29', '48', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2418.000000000000000000', '0.000000000000000000', '2418.000000000000000000', '1711128348854', '配货入货, 其它 -> 深圳盐田仓库'), ('4', '123', '29', '49', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2162.000000000000000000', '0.000000000000000000', '2162.000000000000000000', '1711128348858', '配货入货, 其它 -> 深圳盐田仓库'), ('5', '124', '29', '50', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2213.000000000000000000', '0.000000000000000000', '2213.000000000000000000', '1711128348861', '配货入货, 其它 -> 深圳盐田仓库'), ('6', '125', '30', '51', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1383.000000000000000000', '0.000000000000000000', '1383.000000000000000000', '1711128545353', '配货入货, 其它 -> 深圳盐田仓库'), ('7', '126', '31', '52', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1841.000000000000000000', '0.000000000000000000', '1841.000000000000000000', '1711128545356', '配货入货, 其它 -> 深圳盐田仓库'), ('8', '127', '31', '53', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2780.000000000000000000', '0.000000000000000000', '2780.000000000000000000', '1711128545359', '配货入货, 其它 -> 深圳盐田仓库'), ('9', '128', '31', '54', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2902.000000000000000000', '0.000000000000000000', '2902.000000000000000000', '1711128545363', '配货入货, 其它 -> 深圳盐田仓库'), ('10', '129', '31', '55', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2904.000000000000000000', '0.000000000000000000', '2904.000000000000000000', '1711128545366', '配货入货, 其它 -> 深圳盐田仓库'), ('11', '125', '30', '51', '2', '6f22c403ffa94c9da21cce5b715c3cfe', '275.000000000000000000', '1383.000000000000000000', '1108.000000000000000000', '1711128545395', '配货出货, 深圳盐田仓库 -> 后海科兴总店'), ('12', '126', '31', '52', '2', '6f22c403ffa94c9da21cce5b715c3cfe', '422.000000000000000000', '1841.000000000000000000', '1419.000000000000000000', '1711128545398', '配货出货, 深圳盐田仓库 -> 后海科兴总店'), ('13', '127', '31', '53', '2', '6f22c403ffa94c9da21cce5b715c3cfe', '142.000000000000000000', '2780.000000000000000000', '2638.000000000000000000', '1711128545401', '配货出货, 深圳盐田仓库 -> 后海科兴总店'), ('14', '128', '31', '54', '2', '6f22c403ffa94c9da21cce5b715c3cfe', '218.000000000000000000', '2902.000000000000000000', '2684.000000000000000000', '1711128545403', '配货出货, 深圳盐田仓库 -> 后海科兴总店'), ('15', '129', '31', '55', '2', '6f22c403ffa94c9da21cce5b715c3cfe', '110.000000000000000000', '2904.000000000000000000', '2794.000000000000000000', '1711128545406', '配货出货, 深圳盐田仓库 -> 后海科兴总店'), ('16', '130', '30', '51', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '275.000000000000000000', '0.000000000000000000', '275.000000000000000000', '1711128545542', '配货入货, 深圳盐田仓库 -> 后海科兴总店'), ('17', '131', '31', '52', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '422.000000000000000000', '0.000000000000000000', '422.000000000000000000', '1711128545547', '配货入货, 深圳盐田仓库 -> 后海科兴总店'), ('18', '132', '31', '53', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '142.000000000000000000', '0.000000000000000000', '142.000000000000000000', '1711128545549', '配货入货, 深圳盐田仓库 -> 后海科兴总店'), ('19', '133', '31', '54', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '218.000000000000000000', '0.000000000000000000', '218.000000000000000000', '1711128545552', '配货入货, 深圳盐田仓库 -> 后海科兴总店'), ('20', '134', '31', '55', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '110.000000000000000000', '0.000000000000000000', '110.000000000000000000', '1711128545554', '配货入货, 深圳盐田仓库 -> 后海科兴总店'), ('21', '135', '34', '58', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2303.000000000000000000', '0.000000000000000000', '2303.000000000000000000', '1711566205800', '配货入货, 其它 -> 深圳盐田仓库'), ('22', '136', '35', '59', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1467.000000000000000000', '0.000000000000000000', '1467.000000000000000000', '1711566205827', '配货入货, 其它 -> 深圳盐田仓库'), ('23', '137', '35', '60', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1620.000000000000000000', '0.000000000000000000', '1620.000000000000000000', '1711566205832', '配货入货, 其它 -> 深圳盐田仓库'), ('24', '138', '35', '61', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2688.000000000000000000', '0.000000000000000000', '2688.000000000000000000', '1711566205841', '配货入货, 其它 -> 深圳盐田仓库'), ('25', '139', '35', '62', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1236.000000000000000000', '0.000000000000000000', '1236.000000000000000000', '1711566205844', '配货入货, 其它 -> 深圳盐田仓库'), ('26', '140', '36', '63', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2333.000000000000000000', '0.000000000000000000', '2333.000000000000000000', '1711566316047', '配货入货, 其它 -> 深圳盐田仓库'), ('27', '141', '37', '64', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2703.000000000000000000', '0.000000000000000000', '2703.000000000000000000', '1711566316049', '配货入货, 其它 -> 深圳盐田仓库'), ('28', '142', '37', '65', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1776.000000000000000000', '0.000000000000000000', '1776.000000000000000000', '1711566316051', '配货入货, 其它 -> 深圳盐田仓库'), ('29', '143', '37', '66', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2419.000000000000000000', '0.000000000000000000', '2419.000000000000000000', '1711566316053', '配货入货, 其它 -> 深圳盐田仓库'), ('30', '144', '37', '67', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2845.000000000000000000', '0.000000000000000000', '2845.000000000000000000', '1711566316055', '配货入货, 其它 -> 深圳盐田仓库'), ('31', '145', '38', '68', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2893.000000000000000000', '0.000000000000000000', '2893.000000000000000000', '1711566409331', '配货入货, 其它 -> 深圳盐田仓库'), ('32', '146', '39', '69', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2828.000000000000000000', '0.000000000000000000', '2828.000000000000000000', '1711566409333', '配货入货, 其它 -> 深圳盐田仓库'), ('33', '147', '39', '70', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2713.000000000000000000', '0.000000000000000000', '2713.000000000000000000', '1711566409336', '配货入货, 其它 -> 深圳盐田仓库'), ('34', '148', '39', '71', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2339.000000000000000000', '0.000000000000000000', '2339.000000000000000000', '1711566409339', '配货入货, 其它 -> 深圳盐田仓库'), ('35', '149', '39', '72', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2988.000000000000000000', '0.000000000000000000', '2988.000000000000000000', '1711566409341', '配货入货, 其它 -> 深圳盐田仓库'), ('36', '150', '40', '73', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2188.000000000000000000', '0.000000000000000000', '2188.000000000000000000', '1711566435134', '配货入货, 其它 -> 深圳盐田仓库'), ('37', '151', '41', '74', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1754.000000000000000000', '0.000000000000000000', '1754.000000000000000000', '1711566435139', '配货入货, 其它 -> 深圳盐田仓库'), ('38', '152', '41', '75', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1866.000000000000000000', '0.000000000000000000', '1866.000000000000000000', '1711566435142', '配货入货, 其它 -> 深圳盐田仓库'), ('39', '153', '41', '76', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2328.000000000000000000', '0.000000000000000000', '2328.000000000000000000', '1711566435145', '配货入货, 其它 -> 深圳盐田仓库'), ('40', '154', '41', '77', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2133.000000000000000000', '0.000000000000000000', '2133.000000000000000000', '1711566435148', '配货入货, 其它 -> 深圳盐田仓库'), ('41', '155', '42', '78', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1165.000000000000000000', '0.000000000000000000', '1165.000000000000000000', '1711566469299', '配货入货, 其它 -> 深圳盐田仓库'), ('42', '156', '43', '79', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2294.000000000000000000', '0.000000000000000000', '2294.000000000000000000', '1711566469302', '配货入货, 其它 -> 深圳盐田仓库'), ('43', '157', '43', '80', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2786.000000000000000000', '0.000000000000000000', '2786.000000000000000000', '1711566469304', '配货入货, 其它 -> 深圳盐田仓库'), ('44', '158', '43', '81', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1682.000000000000000000', '0.000000000000000000', '1682.000000000000000000', '1711566469306', '配货入货, 其它 -> 深圳盐田仓库'), ('45', '159', '43', '82', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1563.000000000000000000', '0.000000000000000000', '1563.000000000000000000', '1711566469308', '配货入货, 其它 -> 深圳盐田仓库'), ('46', '160', '44', '83', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1548.000000000000000000', '0.000000000000000000', '1548.000000000000000000', '1711566508391', '配货入货, 其它 -> 深圳盐田仓库'), ('47', '161', '45', '84', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2577.000000000000000000', '0.000000000000000000', '2577.000000000000000000', '1711566508393', '配货入货, 其它 -> 深圳盐田仓库'), ('48', '162', '45', '85', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2521.000000000000000000', '0.000000000000000000', '2521.000000000000000000', '1711566508396', '配货入货, 其它 -> 深圳盐田仓库'), ('49', '163', '45', '86', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2915.000000000000000000', '0.000000000000000000', '2915.000000000000000000', '1711566508399', '配货入货, 其它 -> 深圳盐田仓库'), ('50', '164', '45', '87', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2390.000000000000000000', '0.000000000000000000', '2390.000000000000000000', '1711566508401', '配货入货, 其它 -> 深圳盐田仓库'), ('51', '165', '48', '93', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2618.000000000000000000', '0.000000000000000000', '2618.000000000000000000', '1711566716238', '配货入货, 其它 -> 深圳盐田仓库'), ('52', '166', '49', '94', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1985.000000000000000000', '0.000000000000000000', '1985.000000000000000000', '1711566716247', '配货入货, 其它 -> 深圳盐田仓库'), ('53', '167', '49', '95', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2336.000000000000000000', '0.000000000000000000', '2336.000000000000000000', '1711566716253', '配货入货, 其它 -> 深圳盐田仓库'), ('54', '168', '49', '96', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2132.000000000000000000', '0.000000000000000000', '2132.000000000000000000', '1711566716259', '配货入货, 其它 -> 深圳盐田仓库'), ('55', '169', '49', '97', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1323.000000000000000000', '0.000000000000000000', '1323.000000000000000000', '1711566716264', '配货入货, 其它 -> 深圳盐田仓库'), ('56', '170', '50', '98', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2813.000000000000000000', '0.000000000000000000', '2813.000000000000000000', '1711566890458', '配货入货, 其它 -> 深圳盐田仓库'), ('57', '171', '51', '99', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2368.000000000000000000', '0.000000000000000000', '2368.000000000000000000', '1711566890461', '配货入货, 其它 -> 深圳盐田仓库'), ('58', '172', '51', '100', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1510.000000000000000000', '0.000000000000000000', '1510.000000000000000000', '1711566890463', '配货入货, 其它 -> 深圳盐田仓库'), ('59', '173', '51', '101', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2095.000000000000000000', '0.000000000000000000', '2095.000000000000000000', '1711566890465', '配货入货, 其它 -> 深圳盐田仓库'), ('60', '174', '51', '102', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1254.000000000000000000', '0.000000000000000000', '1254.000000000000000000', '1711566890467', '配货入货, 其它 -> 深圳盐田仓库'), ('61', '175', '60', '123', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2322.000000000000000000', '0.000000000000000000', '2322.000000000000000000', '1711567136105', '配货入货, 其它 -> 深圳盐田仓库'), ('62', '176', '61', '124', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1445.000000000000000000', '0.000000000000000000', '1445.000000000000000000', '1711567136107', '配货入货, 其它 -> 深圳盐田仓库'), ('63', '177', '61', '125', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1830.000000000000000000', '0.000000000000000000', '1830.000000000000000000', '1711567136110', '配货入货, 其它 -> 深圳盐田仓库'), ('64', '178', '61', '126', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1003.000000000000000000', '0.000000000000000000', '1003.000000000000000000', '1711567136113', '配货入货, 其它 -> 深圳盐田仓库'), ('65', '179', '61', '127', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1966.000000000000000000', '0.000000000000000000', '1966.000000000000000000', '1711567136115', '配货入货, 其它 -> 深圳盐田仓库'), ('66', '180', '64', '133', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1645.000000000000000000', '0.000000000000000000', '1645.000000000000000000', '1711567290256', '配货入货, 其它 -> 深圳盐田仓库'), ('67', '181', '65', '134', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1098.000000000000000000', '0.000000000000000000', '1098.000000000000000000', '1711567290265', '配货入货, 其它 -> 深圳盐田仓库'), ('68', '182', '65', '135', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1207.000000000000000000', '0.000000000000000000', '1207.000000000000000000', '1711567290270', '配货入货, 其它 -> 深圳盐田仓库'), ('69', '183', '65', '136', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '1604.000000000000000000', '0.000000000000000000', '1604.000000000000000000', '1711567290275', '配货入货, 其它 -> 深圳盐田仓库'), ('70', '184', '65', '137', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '2741.000000000000000000', '0.000000000000000000', '2741.000000000000000000', '1711567290280', '配货入货, 其它 -> 深圳盐田仓库'), ('71', '180', '64', '133', '2', '6f22c403ffa94c9da21cce5b715c3cfe', '385.000000000000000000', '1645.000000000000000000', '1260.000000000000000000', '1711567290688', '配货出货, 深圳盐田仓库 -> 后海科兴总店'), ('72', '181', '65', '134', '2', '6f22c403ffa94c9da21cce5b715c3cfe', '280.000000000000000000', '1098.000000000000000000', '818.000000000000000000', '1711567290690', '配货出货, 深圳盐田仓库 -> 后海科兴总店'), ('73', '182', '65', '135', '2', '6f22c403ffa94c9da21cce5b715c3cfe', '480.000000000000000000', '1207.000000000000000000', '727.000000000000000000', '1711567290693', '配货出货, 深圳盐田仓库 -> 后海科兴总店'), ('74', '183', '65', '136', '2', '6f22c403ffa94c9da21cce5b715c3cfe', '500.000000000000000000', '1604.000000000000000000', '1104.000000000000000000', '1711567290696', '配货出货, 深圳盐田仓库 -> 后海科兴总店'), ('75', '184', '65', '137', '2', '6f22c403ffa94c9da21cce5b715c3cfe', '154.000000000000000000', '2741.000000000000000000', '2587.000000000000000000', '1711567290699', '配货出货, 深圳盐田仓库 -> 后海科兴总店'), ('76', '185', '64', '133', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '385.000000000000000000', '0.000000000000000000', '385.000000000000000000', '1711567291169', '配货入货, 深圳盐田仓库 -> 后海科兴总店'), ('77', '186', '65', '134', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '280.000000000000000000', '0.000000000000000000', '280.000000000000000000', '1711567291171', '配货入货, 深圳盐田仓库 -> 后海科兴总店'), ('78', '187', '65', '135', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '480.000000000000000000', '0.000000000000000000', '480.000000000000000000', '1711567291174', '配货入货, 深圳盐田仓库 -> 后海科兴总店'), ('79', '188', '65', '136', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '500.000000000000000000', '0.000000000000000000', '500.000000000000000000', '1711567291176', '配货入货, 深圳盐田仓库 -> 后海科兴总店'), ('80', '189', '65', '137', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '154.000000000000000000', '0.000000000000000000', '154.000000000000000000', '1711567291180', '配货入货, 深圳盐田仓库 -> 后海科兴总店');
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
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `uid` varchar(255) DEFAULT NULL COMMENT '唯一id',
  `wx_open_id` varchar(255) DEFAULT NULL COMMENT '微信openId',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `member_flag` tinyint DEFAULT NULL COMMENT '是否会员',
  `member_s_time` bigint DEFAULT NULL COMMENT '会员有效期开始时间',
  `member_e_time` bigint DEFAULT NULL COMMENT '会员有效期结束时间',
  `member_nickname` varchar(255) DEFAULT NULL COMMENT '会员昵称，注册会员时所使用的昵称',
  `member_ip` varchar(255) DEFAULT NULL,
  `member_province_code` varchar(255) DEFAULT NULL,
  `member_province_name` varchar(255) DEFAULT NULL,
  `member_ref_type` tinyint DEFAULT NULL COMMENT '引荐人类型',
  `member_ref_id` varchar(255) DEFAULT NULL COMMENT '引荐人ID',
  `member_shop_name` varchar(255) DEFAULT NULL COMMENT '会员绑定的店铺',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像，图片URL',
  `mobile` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `reg_time` bigint DEFAULT NULL COMMENT '注册时间',
  `referrer_flag` tinyint(1) DEFAULT NULL COMMENT '是否引荐人。false-否 ，true-是',
  `referrer_enable_flag` tinyint(1) DEFAULT NULL COMMENT '启用/停用引荐功能，如果停用，那么用户无法进行引荐。但是可以提现和查看历史引荐数据',
  `pos_member_id` bigint DEFAULT NULL COMMENT '收银系统Pos会员ID',
  `invite_commission` decimal(38,18) DEFAULT NULL COMMENT '邀请总佣金',
  `total_invite_count` int DEFAULT NULL COMMENT '总邀请人数',
  `total_invite_commission` decimal(38,18) DEFAULT NULL COMMENT '总佣金',
  `total_order_count` int DEFAULT NULL COMMENT '总订单数，退款的不含在内',
  `total_order_amount` decimal(38,18) DEFAULT NULL COMMENT '总订单金额，退款的不含在内',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_uid` (`uid`) USING BTREE,
  UNIQUE KEY `idx_wxOpenId` (`wx_open_id`) USING BTREE,
  KEY `ind_pos_member_id` (`pos_member_id`),
  KEY `idx_memberFlag_eTime` (`member_flag`,`member_e_time`) USING BTREE,
  KEY `idx_memberRefId` (`member_ref_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', '1', '1', '李东云', '1', '1711043940459', '1743135547827', null, null, '44', '广东省', null, null, null, 'https://dbg.obs.cn-south-1.myhuaweicloud.com/avatar.png', '15920278701', '1711043940459', '1', '1', '464130953646563552', '20.000000000000000000', '3', '60.000000000000000000', '0', '0.000000000000000000'), ('50', 'U697987100650', '553de69babf44f21bf06f6ae2b52bdcd', 'U697987100650', '1', '1711080217950', '1742659199999', null, '127.0.0.1', '44', '广东省', '2', '1', null, 'https://dbg.obs.cn-south-1.myhuaweicloud.com/avatar.png', '15980521260', '1711080216236', '0', '0', null, null, '0', '0.000000000000000000', '0', '0.000000000000000000'), ('51', 'U423871034851', '16d4a763bacf4eadb23b30decdde1cc8', 'U423871034851', '1', '1711080219664', '1742659199999', null, '127.0.0.1', '44', '广东省', '1', 'd000e3c443794213a92d61a9c6f6f6fe', null, 'https://dbg.obs.cn-south-1.myhuaweicloud.com/avatar.png', '15965547729', '1711080219650', '0', '0', null, null, '0', '0.000000000000000000', '0', '0.000000000000000000'), ('52', 'U227493379652', '4b6e903fa6dd46bfba625e0a669e54c4', 'U227493379652', '0', null, null, null, null, null, null, null, null, null, 'https://dbg.obs.cn-south-1.myhuaweicloud.com/avatar.png', null, '1711136310542', '0', '0', null, null, '0', '0.000000000000000000', '0', '0.000000000000000000'), ('53', 'U402351239653', 'a39f8ca4fbc84edeb182807ea89aa074', 'U402351239653', '1', '1711202694471', '1742745599999', 'U402351239653', '0:0:0:0:0:0:0:1', '44', '广东省', '1', 'd000e3c443794213a92d61a9c6f6f6fe', null, 'https://dbg.obs.cn-south-1.myhuaweicloud.com/avatar.png', '15920233301', '1711202188662', '0', '0', null, null, '0', '0.000000000000000000', '0', '0.000000000000000000'), ('54', 'U286357567554', '8bb60f42d8ef4743b5a0927f8923b5e8', 'U286357567554', '0', null, null, null, null, null, null, null, null, null, 'https://dbg.obs.cn-south-1.myhuaweicloud.com/avatar.png', null, '1711203040460', '0', '0', null, null, '0', '0.000000000000000000', '0', '0.000000000000000000'), ('55', 'U667948071055', '111', 'U667948071055', '0', null, null, null, null, null, null, null, null, null, 'https://dbg.obs.cn-south-1.myhuaweicloud.com/avatar.png', null, '1711357898839', '0', '0', null, null, '0', '0.000000000000000000', '0', '0.000000000000000000'), ('56', 'U869662309056', '2ef9035dad624958969550478fdf4de9', 'U869662309056', '0', null, null, null, null, null, null, null, null, null, 'https://dbg.obs.cn-south-1.myhuaweicloud.com/avatar.png', null, '1711421553401', '0', '0', null, null, '0', '0.000000000000000000', '0', '0.000000000000000000');
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

-- ----------------------------
--  Table structure for `user_copy2`
-- ----------------------------
DROP TABLE IF EXISTS `user_copy2`;
CREATE TABLE `user_copy2` (
  `id` varchar(255) NOT NULL COMMENT 'ID,使用uuid方案',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `member_flag` tinyint DEFAULT NULL COMMENT '是否会员',
  `member_s_time` bigint DEFAULT NULL COMMENT '会员有效期开始时间',
  `member_e_time` bigint DEFAULT NULL COMMENT '会员有效期结束时间',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像，图片URL',
  `reg_time` bigint DEFAULT NULL COMMENT '注册时间',
  `referrer_flag` tinyint DEFAULT NULL COMMENT '是否引荐人。false-否 ，true-是',
  `invite_count` int DEFAULT NULL COMMENT '邀请会员总数',
  `pos_member_id` bigint DEFAULT NULL COMMENT '收银系统Pos会员ID',
  `invite_commission` decimal(38,18) DEFAULT NULL COMMENT '邀请总佣金',
  `sum_order_amount` decimal(38,18) DEFAULT NULL COMMENT '总购买金额',
  `sum_order_count` int DEFAULT NULL COMMENT '总订单数量',
  PRIMARY KEY (`id`),
  KEY `ind_pos_member_id` (`pos_member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user_copy2`
-- ----------------------------
BEGIN;
INSERT INTO `user_copy2` VALUES ('1', '李东云', '1', null, null, null, null, null, null, '464130953646563552', null, null, null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
