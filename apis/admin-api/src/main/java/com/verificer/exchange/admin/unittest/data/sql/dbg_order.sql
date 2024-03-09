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

 Date: 03/09/2024 23:01:02 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE=InnoDB AUTO_INCREMENT=522 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `dbg_order`
-- ----------------------------
BEGIN;
INSERT INTO `dbg_order` VALUES ('490', '1', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709612885000', 'DX625760585257120182', null, null, null, '0', null, '046525', null, '1709619287000'), ('491', '1', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709521119000', 'DX170960383086714269', null, null, null, '0', null, null, null, '1709530545000'), ('492', '1', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709355165000', 'DX364428808842389326', null, null, null, '0', null, null, null, '1709362542000'), ('493', '1', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709611373000', 'DX988009732069697271', null, null, null, '0', null, '730328', null, '1709619773000'), ('494', '2', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709696814000', 'DX750057657430239758', null, null, null, '0', null, null, null, '1709706308000'), ('495', '2', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709522736000', 'DX723483232625384094', null, null, null, '0', null, null, null, '1709535810000'), ('496', '2', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709524623000', 'DX589842612532049999', null, null, null, '0', null, null, null, '1709533445000'), ('497', '2', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709351189000', 'DX103493114051195671', null, null, null, '0', null, null, null, '1709368866000'), ('498', '3', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709613765000', 'DX698325581913464217', null, null, null, '0', null, null, null, '1709622213000'), ('499', '3', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709350848000', 'DX851639560544701302', null, null, null, '0', null, null, null, '1709365568000'), ('500', '3', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709436958000', 'DX950635073319851512', null, null, null, '0', null, null, null, '1709456100000'), ('501', '3', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709436155000', 'DX127451386300559605', null, null, null, '0', null, null, null, '1709445292000'), ('502', '4', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709613340000', 'DX830331900033332779', null, '1709619749000', null, '0', null, null, null, null), ('503', '4', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709527745000', 'DX007876245543295659', null, '1709532359000', null, '0', null, null, null, null), ('504', '4', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709695970000', 'DX660259371056854683', null, '1709701728000', null, '0', null, null, null, null), ('505', '4', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709698222000', 'DX327403817929062370', null, '1709699700000', null, '0', null, null, null, null), ('506', '5', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709521997000', 'DX925074698097663972', null, null, null, '0', null, '761104', null, '1709523771000'), ('507', '5', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709520925000', 'DX029671702173876947', null, null, null, '0', null, '601450', null, '1709524185000'), ('508', '5', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709525070000', 'DX958780376305817912', null, null, null, '0', null, '716725', null, '1709534792000'), ('509', '5', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709351328000', 'DX585305576743205899', null, '1709356911000', null, '0', null, null, null, '1709366353000'), ('510', '6', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709612510000', 'DX346155628097983516', null, '1709614183000', null, '0', null, null, null, '1709620067000'), ('511', '6', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709694886000', 'DX082971486720975070', null, null, null, '0', null, '476748', null, '1709696758000'), ('512', '6', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709695062000', 'DX061618044105653929', null, '1709696263000', null, '0', null, null, null, '1709696971000'), ('513', '6', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709352003000', 'DX100881416477729983', null, null, null, '0', null, '255962', null, '1709362581000'), ('514', '22', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709351978000', 'DX969666356861040717', null, null, null, '0', null, '707733', null, null), ('515', '22', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709521156000', 'DX582388418835291780', null, null, null, '0', null, '197988', null, null), ('516', '22', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709353549000', 'DX202704590774677645', null, null, null, '0', null, '972226', null, null), ('517', '22', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709440474000', 'DX767861214529622580', null, null, null, '0', null, '629640', null, null), ('518', '7', '1', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '2', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709695901000', 'DX768508354013482457', null, null, null, '0', null, null, null, null), ('519', '7', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', '1709700281000', 'DX597858217130554986', null, '1709700763000', null, '0', null, null, null, '1709701385000'), ('520', '7', '1', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '2', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709350176000', 'DX157256643577963674', null, null, null, '0', null, null, null, null), ('521', '7', '1', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '2', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', '1709354344000', 'DX415890944522079234', null, null, null, '0', null, null, null, null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
