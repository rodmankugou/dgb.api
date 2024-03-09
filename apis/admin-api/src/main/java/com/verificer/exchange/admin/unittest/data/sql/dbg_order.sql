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

 Date: 03/09/2024 21:15:34 PM
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
) ENGINE=InnoDB AUTO_INCREMENT=330 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `dbg_order`
-- ----------------------------
BEGIN;
INSERT INTO `dbg_order` VALUES ('298', '1', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', null, 'DX013623105424717460', null, null, null, '0', null, '123193', null, '272474000'), ('299', '1', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', null, 'DX283367114215181023', null, null, null, '0', null, null, null, '267326000'), ('300', '1', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', null, 'DX709851840901123693', null, null, null, '0', null, null, null, '356793000'), ('301', '1', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', null, 'DX485966478176149340', null, null, null, '0', null, null, null, '97446000'), ('302', '2', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', null, 'DX892007981047940702', null, null, null, '0', null, null, null, '185081000'), ('303', '2', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', null, 'DX288763101972739155', null, null, null, '0', null, null, null, '178790000'), ('304', '2', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', null, 'DX391740792057560598', null, null, null, '0', null, null, null, '275754000'), ('305', '2', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', null, 'DX690089015946972421', null, null, null, '0', null, null, null, '186331000'), ('306', '3', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', null, 'DX620180347042222708', null, null, null, '0', null, null, null, '14125000'), ('307', '3', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', null, 'DX626402864565873543', null, null, null, '0', null, null, null, '351855000'), ('308', '3', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', null, 'DX312098704740383221', null, null, null, '0', null, null, null, '275776000'), ('309', '3', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', null, 'DX459213793633224738', null, null, null, '0', null, null, null, '266935000'), ('310', '4', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', null, 'DX809067210857609688', null, '269071000', null, '0', null, null, null, null), ('311', '4', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', null, 'DX897093292151505845', null, '349779000', null, '0', null, null, null, null), ('312', '4', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', null, 'DX583833859512947557', null, '176771000', null, '0', null, null, null, null), ('313', '4', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', null, 'DX910430363440237379', null, '271539000', null, '0', null, null, null, null), ('314', '5', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', null, 'DX250336839503065128', null, '6672000', null, '0', null, null, null, '20406000'), ('315', '5', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', null, 'DX467398244413538184', null, null, null, '0', null, '470629', null, '354552000'), ('316', '5', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', null, 'DX017575031684340597', null, null, null, '0', null, '960860', null, '92850000'), ('317', '5', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', null, 'DX903263570709194152', null, '179416000', null, '0', null, null, null, '183094000'), ('318', '6', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', null, 'DX218141991219324111', null, null, null, '0', null, '167180', null, '2750000'), ('319', '6', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', null, 'DX078322203324902921', null, '90534000', null, '0', null, null, null, '90994000'), ('320', '6', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', null, 'DX699522288870597627', null, '90723000', null, '0', null, null, null, '93121000'), ('321', '6', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', null, 'DX553665178172328826', null, '355183000', null, '0', null, null, null, '361309000'), ('322', '22', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', null, 'DX311702054197493565', null, null, null, '0', null, '258858', null, null), ('323', '22', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', null, 'DX365963451323321727', null, null, null, '0', null, '387622', null, null), ('324', '22', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', null, 'DX396281674724833418', null, null, null, '0', null, '962873', null, null), ('325', '22', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', null, 'DX148258147280432211', null, null, null, '0', null, '647245', null, null), ('326', '7', '3', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', null, null, '1', null, '1', null, '1', null, '李东云', '18169408966', '湖南省长沙市开福区通泰街万达A座', '3908', '180.200000000000000000', '22.330000000000000000', null, null, '', null, 'DX684778038012551334', null, '3953000', null, '0', null, null, null, '9511000'), ('327', '7', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', null, 'DX039367762911429927', null, null, null, '0', null, '668995', null, '363395000'), ('328', '7', '2', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '1', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', null, 'DX071058152591580810', null, null, null, '0', null, '579629', null, '3990000'), ('329', '7', '1', '2', 'd000e3c443794213a92d61a9c6f6f6fe', '后海科兴总店', null, null, '2', null, '1', null, null, null, null, null, null, null, null, null, null, null, '', null, 'DX576132928684600688', null, null, null, '0', null, null, null, null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
