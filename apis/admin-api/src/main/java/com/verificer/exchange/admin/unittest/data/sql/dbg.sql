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

 Date: 03/22/2024 11:58:46 AM
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
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `account`
-- ----------------------------
BEGIN;
INSERT INTO `account` VALUES ('5', '1', 'USR_INTEGRAL', '0.000000000000000000', '0.000000000000000000', '0', '1710930932823', '1710930932823'), ('7', '1', 'USR_REF', '38.000000000000000000', '0.000000000000000000', '0', '1710930932823', '1711076873119'), ('49', 'U286948476048', 'USR_INTEGRAL', '0.000000000000000000', '0.000000000000000000', '0', '1711076872699', '1711076872699'), ('50', 'U966747612249', 'USR_INTEGRAL', '0.000000000000000000', '0.000000000000000000', '0', '1711076873368', '1711076873368');
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
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `account_log`
-- ----------------------------
BEGIN;
INSERT INTO `account_log` VALUES ('49', '1', '7', 'USR_REF', '1', '20.000000000000000000', '35', '20.000000000000000000', '0.000000000000000000', '40.000000000000000000', '0.000000000000000000', '引荐人分润', '1711076873066'), ('50', '1', '7', 'USR_REF', '2', '2.000000000000000000', '16', '40.000000000000000000', '0.000000000000000000', '38.000000000000000000', '2.000000000000000000', '引荐人申请提现-冻结', '1711076873103'), ('51', '1', '7', 'USR_REF', '3', '2.000000000000000000', '16', '38.000000000000000000', '2.000000000000000000', '38.000000000000000000', '0.000000000000000000', '引荐人提现', '1711076873119');
COMMIT;

-- ----------------------------
--  Table structure for `addr`
-- ----------------------------
DROP TABLE IF EXISTS `addr`;
CREATE TABLE `addr` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addr` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
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
  `root_order_id` bigint DEFAULT NULL COMMENT '补货单的最原始订单ID',
  `orig_order_id` bigint DEFAULT NULL COMMENT '补货单的原订单ID',
  `order_type` tinyint DEFAULT NULL COMMENT '1-线下订单 2-自提订单 3-平台配送订单 4-补货单',
  `rel_type` tinyint DEFAULT NULL COMMENT '售方类型，1-仓库 2-店铺',
  `rel_id` varchar(255) DEFAULT NULL COMMENT '仓库/店铺ID',
  `ref_name` varchar(2000) DEFAULT NULL COMMENT '店铺/仓库名称',
  `amount` decimal(38,18) DEFAULT NULL COMMENT '应收金额',
  `real_amount` decimal(38,18) DEFAULT NULL,
  `pay_type` int DEFAULT NULL COMMENT '支付方式 1-微信支付 2-线下pos支付 3-现金支付',
  `pay_id` bigint DEFAULT NULL COMMENT '支付记录id',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
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
  `write_to_db_time` bigint DEFAULT NULL COMMENT '订单写入数据库的时间',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `order_num` varchar(255) DEFAULT NULL COMMENT '订单号',
  `transit_order_id` varchar(255) DEFAULT NULL COMMENT '运输单ID',
  `transit_time` bigint DEFAULT NULL COMMENT '发货时间',
  `transit_rc_time` bigint DEFAULT NULL COMMENT '送达时间',
  `transit_user_confirm_time` datetime DEFAULT NULL COMMENT '用户确认到货时间',
  `transit_order_num` varchar(255) DEFAULT NULL COMMENT '物流单号',
  `transit_staff_id` bigint DEFAULT NULL COMMENT '发货员id',
  `transit_staff_name` varchar(255) DEFAULT NULL COMMENT '发货员姓名',
  `del_flag` tinyint DEFAULT NULL COMMENT '是否已删除',
  `del_time` bigint DEFAULT NULL COMMENT '是否已提货。true-是 false-否',
  `take_code` varchar(255) DEFAULT NULL,
  `take_flag` tinyint(1) DEFAULT NULL COMMENT '是否已提取。true-是；false-否',
  `take_time` bigint DEFAULT NULL COMMENT '提货时间',
  `pos_ord_id` bigint DEFAULT NULL COMMENT 'pos机系统中的订单id',
  `pos_cashier_uid` bigint DEFAULT NULL COMMENT 'pos机系统中的收银员ID',
  `pos_member_uid` bigint DEFAULT NULL COMMENT 'pos机系统中的会员ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_relId_takeCode_takeFlag` (`rel_id`,`take_code`,`take_flag`) USING BTREE,
  UNIQUE KEY `idx_order_num` (`order_num`) USING BTREE,
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `goods`
-- ----------------------------
BEGIN;
INSERT INTO `goods` VALUES ('20', '3', '4', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '新的测试榴莲', '新的测试榴莲@新的测试榴莲', '1', '1.500', '0.010000', '100.000000000000000000', '1.000000000000000000', '个', '1', '0', '0', '<H1><H1>', null, '0', null, '0', null, null, '0'), ('21', '3', '4', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果@马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '1', '1.500', '0.010000', '100.000000000000000000', '1.000000000000000000', '个', '1', '0', '0', '<H1><H1>', null, '0', null, '0', null, null, '0');
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
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `goods_sta`
-- ----------------------------
BEGIN;
INSERT INTO `goods_sta` VALUES ('45', '20', null, '1', '0.000000000000000000', '0.000000000000000000', '0.000000000000000000', '0.000000000000000000', '0'), ('46', '20', '26', '0', '0.000000000000000000', '0.000000000000000000', '0.000000000000000000', '0.000000000000000000', '0'), ('47', '21', null, '1', '0.000000000000000000', '0.000000000000000000', '0.000000000000000000', '0.000000000000000000', '0'), ('48', '21', '27', '0', '0.000000000000000000', '0.000000000000000000', '0.000000000000000000', '0.000000000000000000', '0'), ('49', '21', '28', '0', '0.000000000000000000', '0.000000000000000000', '0.000000000000000000', '0.000000000000000000', '0'), ('50', '21', '29', '0', '0.000000000000000000', '0.000000000000000000', '0.000000000000000000', '0.000000000000000000', '0'), ('51', '21', '30', '0', '0.000000000000000000', '0.000000000000000000', '0.000000000000000000', '0.000000000000000000', '0');
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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `member_order`
-- ----------------------------
BEGIN;
INSERT INTO `member_order` VALUES ('35', 'DM3379693493624935', '48', '1', '2', '1', '1711076872725', '1742659199999', '2', '1', '388.000000000000000000', '20.000000000000000000', '1', '1711076872725', '1711076872744', '127.0.0.1', '0', null), ('36', 'DM8336529649657336', '49', '1', '2', '1', '1711076873377', '1742659199999', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '388.000000000000000000', null, '1', '1711076873377', '1711076873383', '127.0.0.1', '0', null);
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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `pla_income_log`
-- ----------------------------
BEGIN;
INSERT INTO `pla_income_log` VALUES ('28', '1', null, '35', 'DM3379693493624935', '388.000000000000000000', '1', null, '会员注册，U286948476048, 15989708172, U286948476048'), ('29', '1', 'd000e3c443794213a92d61a9c6f6f6fe', '36', 'DM8336529649657336', '388.000000000000000000', '1', null, '会员注册，U966747612249, 15989209579, U966747612249'), ('30', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '31', 'DS8548912761813831', '1.620000000000000000', '0', null, '会员佣金, 后海科兴总店, 账单编号DS8548912761813831'), ('31', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '32', 'DS5845447693813732', '1.620000000000000000', '0', null, '会员佣金, 后海科兴总店, 账单编号DS5845447693813732'), ('32', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '33', 'DS4439588399221533', '1.620000000000000000', '0', null, '会员佣金, 后海科兴总店, 账单编号DS4439588399221533'), ('33', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '34', 'DS1785295868204034', '1.620000000000000000', '0', null, '会员佣金, 后海科兴总店, 账单编号DS1785295868204034'), ('34', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '35', 'DS8129605543942235', '1.620000000000000000', '0', null, '会员佣金, 后海科兴总店, 账单编号DS8129605543942235'), ('35', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '36', 'DS2145739397316036', '1.620000000000000000', '0', null, '会员佣金, 后海科兴总店, 账单编号DS2145739397316036'), ('36', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '37', 'DS2950910275493337', '1.620000000000000000', '0', null, '会员佣金, 后海科兴总店, 账单编号DS2950910275493337');
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
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `pos_sync_task`
-- ----------------------------
BEGIN;
INSERT INTO `pos_sync_task` VALUES ('160', 'V1.0', '7', '21', null, '{\"expireTime\":1742486399999,\"name\":\"U504180769820\",\"number\":\"U504180769820\",\"phone\":\"15916808579\"}', '1', '0', null, '1710932631739', null, null, null), ('161', 'V1.0', '8', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U225632271221\",\"number\":\"U225632271221\",\"phone\":\"15930583325\"}', '1', '0', null, '1711043811622', null, null, null), ('162', 'V1.0', '9', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U191168857722\",\"number\":\"U191168857722\",\"phone\":\"15952284004\"}', '1', '0', null, '1711043852309', null, null, null), ('163', 'V1.0', '10', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U802928622223\",\"number\":\"U802928622223\",\"phone\":\"15998019364\"}', '1', '0', null, '1711043940806', null, null, null), ('164', 'V1.0', '11', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U749938879424\",\"number\":\"U749938879424\",\"phone\":\"15960466453\"}', '1', '0', null, '1711045364915', null, null, null), ('165', 'V1.0', '12', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U086918615625\",\"number\":\"U086918615625\",\"phone\":\"15916800590\"}', '1', '0', null, '1711069391522', null, null, null), ('166', 'V1.0', '13', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U371895420326\",\"number\":\"U371895420326\",\"phone\":\"15969271457\"}', '1', '0', null, '1711069391947', null, null, null), ('167', 'V1.0', '14', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U724831468827\",\"number\":\"U724831468827\",\"phone\":\"15914562447\"}', '1', '0', null, '1711070418369', null, null, null), ('168', 'V1.0', '15', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U383032966028\",\"number\":\"U383032966028\",\"phone\":\"15901713252\"}', '1', '0', null, '1711070452085', null, null, null), ('169', 'V1.0', '16', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U015335620429\",\"number\":\"U015335620429\",\"phone\":\"15998849060\"}', '1', '0', null, '1711070515384', null, null, null), ('170', 'V1.0', '17', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U962365687430\",\"number\":\"U962365687430\",\"phone\":\"15990250831\"}', '1', '0', null, '1711070538991', null, null, null), ('171', 'V1.0', '18', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U880317553831\",\"number\":\"U880317553831\",\"phone\":\"15912233707\"}', '1', '0', null, '1711070539414', null, null, null), ('172', 'V1.0', '19', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U786365776132\",\"number\":\"U786365776132\",\"phone\":\"15947918571\"}', '1', '0', null, '1711070568449', null, null, null), ('173', 'V1.0', '20', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U468109703733\",\"number\":\"U468109703733\",\"phone\":\"15960398366\"}', '1', '0', null, '1711070568733', null, null, null), ('174', 'V1.0', '21', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U217582220134\",\"number\":\"U217582220134\",\"phone\":\"15999471489\"}', '1', '0', null, '1711071593243', null, null, null), ('175', 'V1.0', '22', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U324214388635\",\"number\":\"U324214388635\",\"phone\":\"15993541634\"}', '1', '0', null, '1711071593563', null, null, null), ('176', 'V1.0', '23', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U050654501036\",\"number\":\"U050654501036\",\"phone\":\"15972806165\"}', '1', '0', null, '1711071649730', null, null, null), ('177', 'V1.0', '24', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U459811236437\",\"number\":\"U459811236437\",\"phone\":\"15909234909\"}', '1', '0', null, '1711071650151', null, null, null), ('178', 'V1.0', '25', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U941316231138\",\"number\":\"U941316231138\",\"phone\":\"15921805601\"}', '1', '0', null, '1711072289066', null, null, null), ('179', 'V1.0', '26', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U481634757639\",\"number\":\"U481634757639\",\"phone\":\"15968387966\"}', '1', '0', null, '1711072289732', null, null, null), ('182', 'V1.0', '29', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U568586417542\",\"number\":\"U568586417542\",\"phone\":\"15905763365\"}', '1', '0', null, '1711075030544', null, null, null), ('183', 'V1.0', '30', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U710261831243\",\"number\":\"U710261831243\",\"phone\":\"15917273718\"}', '1', '0', null, '1711075030858', null, null, null), ('184', 'V1.0', '31', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U896025535744\",\"number\":\"U896025535744\",\"phone\":\"15966351838\"}', '1', '0', null, '1711075416885', null, null, null), ('185', 'V1.0', '32', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U928738260945\",\"number\":\"U928738260945\",\"phone\":\"15950831124\"}', '1', '0', null, '1711075417194', null, null, null), ('186', 'V1.0', '33', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U846502448246\",\"number\":\"U846502448246\",\"phone\":\"15987005771\"}', '1', '0', null, '1711076745490', null, null, null), ('187', 'V1.0', '34', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U923239970847\",\"number\":\"U923239970847\",\"phone\":\"15919686892\"}', '1', '0', null, '1711076745825', null, null, null), ('188', 'V1.0', '35', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U286948476048\",\"number\":\"U286948476048\",\"phone\":\"15989708172\"}', '1', '0', null, '1711076873086', null, null, null), ('189', 'V1.0', '36', '21', null, '{\"expireTime\":1742659199999,\"name\":\"U966747612249\",\"number\":\"U966747612249\",\"phone\":\"15989209579\"}', '1', '0', null, '1711076873483', null, null, null);
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `referrer_withdraw`
-- ----------------------------
BEGIN;
INSERT INTO `referrer_withdraw` VALUES ('16', 'DW8851881920244016', '1', '2.000000000000000000', '4', null, '1711076873099', '1711076873110', '1', 'Rodman', null, '1', 'Rodman', '1711076873116');
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `settle`
-- ----------------------------
BEGIN;
INSERT INTO `settle` VALUES ('11', '1', 'd000e3c443794213a92d61a9c6f6f6fe', null, '36', '0.050000000000000000', '12', '7', '1', '19.400000000000000000', '1.620000000000000000', 'U966747612249, 15989209579, U966747612249,', '1711900800000', '0', '1711076873485');
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
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `settle_item`
-- ----------------------------
BEGIN;
INSERT INTO `settle_item` VALUES ('35', '31', '11', '1', 'd000e3c443794213a92d61a9c6f6f6fe', null, '会员分期', 'U966747612249, 15989209579, U966747612249,1/12期', '2023', '9', '0', '1.620000000000000000', '0.050000000000000000', '1711076893185'), ('36', '32', '11', '2', 'd000e3c443794213a92d61a9c6f6f6fe', null, '会员分期', 'U966747612249, 15989209579, U966747612249,2/12期', '2023', '10', '0', '1.620000000000000000', '0.050000000000000000', '1711076893206'), ('37', '33', '11', '3', 'd000e3c443794213a92d61a9c6f6f6fe', null, '会员分期', 'U966747612249, 15989209579, U966747612249,3/12期', '2023', '11', '0', '1.620000000000000000', '0.050000000000000000', '1711076893226'), ('38', '34', '11', '4', 'd000e3c443794213a92d61a9c6f6f6fe', null, '会员分期', 'U966747612249, 15989209579, U966747612249,4/12期', '2023', '12', '0', '1.620000000000000000', '0.050000000000000000', '1711076893245'), ('39', '35', '11', '5', 'd000e3c443794213a92d61a9c6f6f6fe', null, '会员分期', 'U966747612249, 15989209579, U966747612249,5/12期', '2024', '1', '0', '1.620000000000000000', '0.050000000000000000', '1711076893263'), ('40', '36', '11', '6', 'd000e3c443794213a92d61a9c6f6f6fe', null, '会员分期', 'U966747612249, 15989209579, U966747612249,6/12期', '2024', '2', '0', '1.620000000000000000', '0.050000000000000000', '1711076893284'), ('41', '37', '11', '7', 'd000e3c443794213a92d61a9c6f6f6fe', null, '会员分期', 'U966747612249, 15989209579, U966747612249,7/12期', '2024', '3', '1', '1.620000000000000000', '0.050000000000000000', '1711076893308');
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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `settle_order`
-- ----------------------------
BEGIN;
INSERT INTO `settle_order` VALUES ('31', 'DS8548912761813831', 'd000e3c443794213a92d61a9c6f6f6fe', '0.050000000000000000', '1.620000000000000000', '1', '2023', '9', null, '0', '1', null, null, null, '1711076893167'), ('32', 'DS5845447693813732', 'd000e3c443794213a92d61a9c6f6f6fe', '0.050000000000000000', '1.620000000000000000', '1', '2023', '10', null, '0', '1', null, null, null, '1711076893200'), ('33', 'DS4439588399221533', 'd000e3c443794213a92d61a9c6f6f6fe', '0.050000000000000000', '1.620000000000000000', '1', '2023', '11', null, '0', '1', null, null, null, '1711076893220'), ('34', 'DS1785295868204034', 'd000e3c443794213a92d61a9c6f6f6fe', '0.050000000000000000', '1.620000000000000000', '1', '2023', '12', null, '0', '1', null, null, null, '1711076893238'), ('35', 'DS8129605543942235', 'd000e3c443794213a92d61a9c6f6f6fe', '0.050000000000000000', '1.620000000000000000', '1', '2024', '1', null, '0', '1', null, null, null, '1711076893257'), ('36', 'DS2145739397316036', 'd000e3c443794213a92d61a9c6f6f6fe', '0.050000000000000000', '1.620000000000000000', '1', '2024', '2', null, '0', '1', null, null, null, '1711076893277'), ('37', 'DS2950910275493337', 'd000e3c443794213a92d61a9c6f6f6fe', '0.050000000000000000', '1.620000000000000000', '1', '2024', '3', '1711076905190', '1', '1', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/7.png', '1', 'Rodman', '1711076893299');
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
INSERT INTO `shop` VALUES ('d000e3c443794213a92d61a9c6f6f6fe', null, '后海科兴总店', '4403a13a5380da7b4e76a3a09885e6c4cd3d', '068f2a7b83f0e9e5879fe022f3572dd96adbe741e1e398dac6527d008d6de8dafc6eaff8716645e126e62021e21344fed562300d45851bee8b88a628ff104082', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/7.png', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/7.png', '新媒体零售体', '0.050000000000000000', '2', '44', '广东省', '4403', '深圳市', '440305', '南山区', '后海科技大道', '广东省深圳市南山区后海科技大道', '莫文业', '0755-5433138', '13800384234', '22.310000000000000000', '180.320000000000000000', '0', null, '1709922752310', '0', null, '10000', '0', '后海科兴总店', 'https://area53-win.pospal.cn:443/', 'wemK5+DZQLy9P+KSrRPyHjMqBiYiBvPNPzwzdHwN0FRvSefz3qN6mCezCURQVRlQ', '747A09332D58D135805F190A93C84FA7');
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
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `shop_goods`
-- ----------------------------
BEGIN;
INSERT INTO `shop_goods` VALUES ('87', '20', '26', 'd000e3c443794213a92d61a9c6f6f6fe', '149652681311220415', '1710108360673', '0'), ('88', '21', '27', 'd000e3c443794213a92d61a9c6f6f6fe', '1018132258893959927', '1710108360687', '0'), ('89', '21', '28', 'd000e3c443794213a92d61a9c6f6f6fe', '181078658522870480', '1710108360698', '0'), ('90', '21', '29', 'd000e3c443794213a92d61a9c6f6f6fe', '984322170749340247', '1710108360710', '0'), ('91', '21', '30', 'd000e3c443794213a92d61a9c6f6f6fe', '188567848956050059', '1710108360721', '0');
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
  `sku_flag` bigint DEFAULT NULL COMMENT '是否sku库存',
  `stage_flag` tinyint(1) DEFAULT NULL COMMENT '是否仓库库存，true-是 ，false-否',
  `rel_id` varchar(255) DEFAULT NULL COMMENT '仓库或店铺ID，视type而定',
  `count` decimal(38,18) DEFAULT NULL COMMENT '库存量',
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_uuid_spec_id` (`rel_id`,`spec_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `stock`
-- ----------------------------
BEGIN;
INSERT INTO `stock` VALUES ('20', '20', '26', null, '1', '6f22c403ffa94c9da21cce5b715c3cfe', '174.000000000000000000', '1709984953783'), ('21', '21', '27', null, '1', '6f22c403ffa94c9da21cce5b715c3cfe', '174.000000000000000000', '1709984953824'), ('22', '21', '28', null, '1', '6f22c403ffa94c9da21cce5b715c3cfe', '174.000000000000000000', '1709984953828'), ('23', '21', '29', null, '1', '6f22c403ffa94c9da21cce5b715c3cfe', '174.000000000000000000', '1709984953834'), ('24', '21', '30', null, '1', '6f22c403ffa94c9da21cce5b715c3cfe', '174.000000000000000000', '1709984953838');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', '1', null, '李东云', '1', '1711043940459', '1742659199999', null, '44', '广东省', null, null, null, 'https://dbg.obs.cn-south-1.myhuaweicloud.com/avatar.png', '15920278701', '1711043940459', '1', '1', '464130953646563552', '20.000000000000000000', '3', '60.000000000000000000', '0', '0.000000000000000000'), ('48', 'U286948476048', '5aa98ffb22b24e868f319af011773192', 'U286948476048', '1', '1711076872725', '1742659199999', '127.0.0.1', '44', '广东省', '2', '1', null, 'https://dbg.obs.cn-south-1.myhuaweicloud.com/avatar.png', '15989708172', '1711076872670', '0', '0', null, null, '0', '0.000000000000000000', '0', '0.000000000000000000'), ('49', 'U966747612249', 'bec4187305f843c1989ab2f3de2f40a5', 'U966747612249', '1', '1711076873377', '1742659199999', '127.0.0.1', '44', '广东省', '1', 'd000e3c443794213a92d61a9c6f6f6fe', null, 'https://dbg.obs.cn-south-1.myhuaweicloud.com/avatar.png', '15989209579', '1711076873363', '0', '0', null, null, '0', '0.000000000000000000', '0', '0.000000000000000000');
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
