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

 Date: 03/22/2024 02:02:35 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', '1', null, '李东云', '1', '1711043940459', '1742659199999', null, '44', '广东省', null, null, null, 'https://dbg.obs.cn-south-1.myhuaweicloud.com/avatar.png', '15920278701', null, '1', '1', '464130953646563552', '20.000000000000000000', '2', '40.000000000000000000', '0', '0.000000000000000000'), ('23', 'U802928622223', '9c8e98ed9e344a4ca697094ca2880422', 'U802928622223', '1', '1711043940459', '1742659199999', '127.0.0.1', '44', '广东省', '2', '1', null, 'https://dbg.obs.cn-south-1.myhuaweicloud.com/avatar.png', '15998019364', '1711043940411', '0', '0', null, null, '0', '0.000000000000000000', '0', '0.000000000000000000');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
