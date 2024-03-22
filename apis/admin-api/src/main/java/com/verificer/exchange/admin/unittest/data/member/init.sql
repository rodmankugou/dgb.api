
delete from user ;
delete from account

delete from account_log;

delete from member_order;

delete from referrer_withdraw;

delete from settle;
delete from settle_order;
delete from settle_item;
delete from pla_income_log;

INSERT INTO `account` VALUES ('5', '1', 'USR_INTEGRAL', '0.000000000000000000', '0.000000000000000000', '0', '1710930932823', '1710930932823'), ('7', '1', 'USR_REF', '20.000000000000000000', '0.000000000000000000', '0', '1710930932823', '1710932631736');
INSERT INTO `user` VALUES ('1', '1', null, '李东云', '1', '1711043940459', '1742659199999', null, '44', '广东省', null, null, null, 'https://dbg.obs.cn-south-1.myhuaweicloud.com/avatar.png', '15920278701', null, '1', '1', '464130953646563552', '20.000000000000000000', '2', '40.000000000000000000', '0', '0.000000000000000000');
update user set reg_time = '1711043940459';