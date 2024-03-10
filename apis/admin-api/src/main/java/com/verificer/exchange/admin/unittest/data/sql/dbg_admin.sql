/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost
 Source Database       : dbg_admin

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : utf-8

 Date: 03/10/2024 18:08:14 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `staff`
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `real_name` varchar(255) NOT NULL,
  `mobile` varchar(32) NOT NULL DEFAULT '' COMMENT '手机号码',
  `password` varchar(255) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `lock_status` tinyint(1) NOT NULL,
  `google_secret` varchar(255) DEFAULT NULL COMMENT '谷歌身份认证唯一标识',
  `google_secret_auth` tinyint(1) DEFAULT NULL COMMENT '是否启用谷歌认证',
  `create_time` bigint NOT NULL COMMENT '创建时间',
  `creator_id` bigint NOT NULL COMMENT '创建者id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='管理系统用户表';

-- ----------------------------
--  Records of `staff`
-- ----------------------------
BEGIN;
INSERT INTO `staff` VALUES ('1', 'rodman', '罗德曼', '+8615920278701', '32a968dfb27d225d703e6be1e14b1226b591e0a909c4c12ae5ea12bc5623c108b30492f74fa4a250e153272f765701bd33b868d9fab044523237823049fd4317', '1', '0', '0', null, '1612180481000', '1'), ('2', 'jordan', '乔丹', '+8615920278702', '282d9a13dcb0f33663da6fe47c44fe9295fb67a2b82ed47d8487a4a4b9fb6f0d50d7c15c421e57284e4d3f22e4bcc78bec9ea571085887f486fe49770654732e', '1', '0', null, null, '1612185831418', '1'), ('3', 'admin', '管理员', '+861592027777', '282d9a13dcb0f33663da6fe47c44fe9295fb67a2b82ed47d8487a4a4b9fb6f0d50d7c15c421e57284e4d3f22e4bcc78bec9ea571085887f486fe49770654732e', '1', '0', '0', null, '1612500874338', '1'), ('4', 'admin2', '管理员', '+861592027777', '282d9a13dcb0f33663da6fe47c44fe9295fb67a2b82ed47d8487a4a4b9fb6f0d50d7c15c421e57284e4d3f22e4bcc78bec9ea571085887f486fe49770654732e', '1', '0', '0', null, '1612500874338', '1'), ('5', 'admin3', 'AA', '+861592027777', '219ecef12f4e0f69e1e636ec328e992765f75a58458749a2af2fb69f87f75bba28e8402aba7af7ff46ae91cb537d99b4f34b389e750e5633ff401d489bcb9e3f', '1', '0', '0', null, '1612500874338', '1'), ('6', 'admin4', '管理员', '+861592027777', '282d9a13dcb0f33663da6fe47c44fe9295fb67a2b82ed47d8487a4a4b9fb6f0d50d7c15c421e57284e4d3f22e4bcc78bec9ea571085887f486fe49770654732e', '1', '0', '0', null, '1612500874338', '1'), ('7', 'terew', '财务专员2', '+86123456789', '8e82439573aade0b6e1e4eeb2970ad6fcfde60847fccbfe8a3e8693700902e1fbd152b623dd11cc3ade417b20ceff099ea868362956494b3fe48a2aa03e6ecc7', '0', '0', null, null, '1626836549983', '3'), ('8', 'testw', 'ces', '+861562', '282d9a13dcb0f33663da6fe47c44fe9295fb67a2b82ed47d8487a4a4b9fb6f0d50d7c15c421e57284e4d3f22e4bcc78bec9ea571085887f486fe49770654732e', '1', '0', null, null, '1629186160667', '2'), ('9', '哦啊哥特人\\', 'ryt', '+86234', '219ecef12f4e0f69e1e636ec328e992765f75a58458749a2af2fb69f87f75bba28e8402aba7af7ff46ae91cb537d99b4f34b389e750e5633ff401d489bcb9e3f', '0', '0', null, null, '1629186221884', '2'), ('10', 'EWR', 'ETR', '+86EWTRE', '668bf541903dd3001db00394aac7e071c27832c41bbd755204aece4117b68bd6828951f50f178548a7ca18961dee89663dd259c2e3ca72a2462685962849de09', '1', '0', null, null, '1629186666493', '2'), ('11', 'testw', '2', '+862', '56750cc72992eb7a6e9993827d10d0716ed7c80b7be9e8a3ee2959bf2cd129f9aa57463cc75c9406b195d527a3cb8f109b1abbdaf31e7cd60e2cdee69854683a', '0', '0', null, null, '1629188365218', '3'), ('12', 'wu', 'w', '+8618215583385', '282d9a13dcb0f33663da6fe47c44fe9295fb67a2b82ed47d8487a4a4b9fb6f0d50d7c15c421e57284e4d3f22e4bcc78bec9ea571085887f486fe49770654732e', '1', '0', null, null, '1629254454213', '3'), ('13', 'pippen', '皮蓬', '+8615920278701', '8e82439573aade0b6e1e4eeb2970ad6fcfde60847fccbfe8a3e8693700902e1fbd152b623dd11cc3ade417b20ceff099ea868362956494b3fe48a2aa03e6ecc7', '1', '0', '0', null, '1612180481000', '1'), ('14', 'wuu', 'www', '+8618215583385', '282d9a13dcb0f33663da6fe47c44fe9295fb67a2b82ed47d8487a4a4b9fb6f0d50d7c15c421e57284e4d3f22e4bcc78bec9ea571085887f486fe49770654732e', '1', '0', null, null, '1631952783147', '6'), ('15', 'www', 'www', '8618215583385', '282d9a13dcb0f33663da6fe47c44fe9295fb67a2b82ed47d8487a4a4b9fb6f0d50d7c15c421e57284e4d3f22e4bcc78bec9ea571085887f486fe49770654732e', '1', '0', null, null, '1631952956370', '6'), ('16', 'IvesTest', 'Ives', '+60126663967', '282d9a13dcb0f33663da6fe47c44fe9295fb67a2b82ed47d8487a4a4b9fb6f0d50d7c15c421e57284e4d3f22e4bcc78bec9ea571085887f486fe49770654732e', '1', '0', null, null, '1631953376348', '6'), ('17', 'QMTest', 'QM Testing Account', '61403641865', '437faeb1b0b088a848d6951da6283f031c1ad0599f8108ec2b610d12f6e6097ab689e12c5b5e044f964258e4b933ca12e32d43fdfd8ffe5fd34e52cfc927c1f1', '1', '0', null, null, '1632297449806', '16'), ('18', 'Chay', 'Chay Winstanly', '61403641865', 'cbe2446f119812a28653f9cd8a412388ad5d7da1f8c8d25f1730ffc1292366c1bbd4c525aece6417a04f9bb137efe03772a79b3831300d52f27d2f058800adfd', '1', '0', null, null, '1635407631643', '16'), ('19', 'Hadden', 'James Hadden', '15920278703', '8e82439573aade0b6e1e4eeb2970ad6fcfde60847fccbfe8a3e8693700902e1fbd152b623dd11cc3ade417b20ceff099ea868362956494b3fe48a2aa03e6ecc7', '0', '0', null, null, '1651887936406', '1'), ('20', 'KKK', '2', '150', '219ecef12f4e0f69e1e636ec328e992765f75a58458749a2af2fb69f87f75bba28e8402aba7af7ff46ae91cb537d99b4f34b389e750e5633ff401d489bcb9e3f', '1', '0', null, null, '1651890934914', '1');
COMMIT;

-- ----------------------------
--  Table structure for `staff_operation_log`
-- ----------------------------
DROP TABLE IF EXISTS `staff_operation_log`;
CREATE TABLE `staff_operation_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(2000) DEFAULT NULL COMMENT '标题',
  `staff_id` bigint DEFAULT NULL COMMENT '操作人Id',
  `staff_real_name` varchar(255) DEFAULT NULL COMMENT '操作人真实姓名',
  `content` varchar(255) DEFAULT NULL COMMENT '操作内容',
  `create_time` bigint DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
