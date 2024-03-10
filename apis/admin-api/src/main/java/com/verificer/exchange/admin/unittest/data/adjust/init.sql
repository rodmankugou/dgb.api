delete from addr;
delete from user;
delete from adjust;
delete from brand;
delete from category;
delete from dbg_order;
delete from dbg_order_log;
delete from goods;
delete from goods_sta;
delete from member_order;
delete from member_type;
delete from order_detail;
delete from pointer;
delete from refund;
delete from refund_log;
delete from refund_reason;
delete from s_cart;
delete from shop;
delete from shop_info;
delete from spec;
delete from stage;
delete from stock;
delete from transit_way;
delete from user;
delete from pos_sync_task;
delete from mer_category;
delete from shop_goods;

INSERT INTO `shop` VALUES ('d000e3c443794213a92d61a9c6f6f6fe', null, '后海科兴总店', '4403a13a5380da7b4e76a3a09885e6c4cd3d', '068f2a7b83f0e9e5879fe022f3572dd96adbe741e1e398dac6527d008d6de8dafc6eaff8716645e126e62021e21344fed562300d45851bee8b88a628ff104082', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/7.png', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/7.png', '新媒体零售体', '0.050000000000000000', '2', '44', '广东省', '4403', '深圳市', '440305', '南山区', '后海科技大道', '广东省深圳市南山区后海科技大道', '莫文业', '0755-5433138', '13800384234', '22.310000000000000000', '180.320000000000000000', '0', null, '1709922752310', '0', null, '10000', '0', '后海科兴总店', 'https://area53-win.pospal.cn:443/', '874859095189735996', '747A09332D58D135805F190A93C84FA7');
INSERT INTO `shop_info` VALUES ('4', 'd000e3c443794213a92d61a9c6f6f6fe', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/3.png', '文兴果业', '3423432429', '后台中科大厦', '零售、餐饮', '440437199102311', '440437199102311', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/3.png', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/3.png', '莫文业', '13800384234', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/3.png', '823423484289293', '水果、零食', '莫文业', '平安银行后海支行', '62033018234248', '深圳', '莫文业', '中国银行福田支行', '62391083489728942', '深圳');
INSERT INTO `stage` VALUES ('6f22c403ffa94c9da21cce5b715c3cfe', '深圳盐田仓库', '133993583', '李有田', '44', '广东省', '4403', '深圳市', '440305', '南山区', '南油', '广东省深圳市南山区南油', '23.200000000000000000', '173.200000000000000000', '1709925876053');

INSERT INTO `brand` VALUES ('4', '彭亨州', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/5.png', 'P', '1', '1709953575233', '0', null);

INSERT INTO `category` VALUES ('3', '马来猫山王', '猫山王', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/4.png', '马来猫山王', '1709954253726', '0', null), ('4', '马来金枕头', '金枕头', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/4.png', '金枕头', '1709954253726', '0', null);

INSERT INTO `goods` VALUES ('20', '3', '4', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '新的测试榴莲', '新的测试榴莲@新的测试榴莲', '1', '1.500', '0.010000', '100', '1', '个', '1', '0', '0', '<H1><H1>', '1709984953750', '0', null, '0', null, '0'), ('21', '3', '4', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果@马来西亚猫山王进口D197液氮冷冻新鲜带壳当季水果', '1', '1.500', '0.010000', '100', '1', '个', '1', '0', '0', '<H1><H1>', '1709984953817', '0', null, '0', null, '0');

INSERT INTO `goods_sta` VALUES ('45', '20', null, '1', '0', '0', '0', '0', '0'), ('46', '20', '26', '0', '0', '0', '0', '0', '0'), ('47', '21', null, '1', '0', '0', '0', '0', '0'), ('48', '21', '27', '0', '0', '0', '0', '0', '0'), ('49', '21', '28', '0', '0', '0', '0', '0', '0'), ('50', '21', '29', '0', '0', '0', '0', '0', '0'), ('51', '21', '30', '0', '0', '0', '0', '0', '0');

INSERT INTO `spec` VALUES ('26', '20', null, '11.000000000000000000', '新的测试榴莲', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '1709984953773', '0', null), ('27', '21', null, '478.000000000000000000', '预选佳品 2.1-2.5KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '1709984953821', '0', null), ('28', '21', null, '388.000000000000000000', '预选佳品 1.7-2.1KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '1709984953825', '0', null), ('29', '21', null, '298.000000000000000000', '预选佳品 1.3-1.7KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '1709984953829', '0', null), ('30', '21', null, '158.000000000000000000', '预选佳品 1.0-1.3KG 一人尝鲜', 'https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg', '1709984953835', '0', null);

INSERT INTO `stock` VALUES ('20', '20', '26', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '0', '1709984953783'), ('21', '21', '27', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '0', '1709984953824'), ('22', '21', '28', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '0', '1709984953828'), ('23', '21', '29', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '0', '1709984953834'), ('24', '21', '30', '1', '6f22c403ffa94c9da21cce5b715c3cfe', '0', '1709984953838');

INSERT INTO `user` VALUES ('1', '李东云', '1', null, null, null, null, null, null, '464130953646563552', null, null, null);

insert into `dbg`.`addr` ( `mobile`, `detail_addr`, `longitude`, `id`, `rc_name`, `addr`, `tag`, `user_id`, `latitude`, `create_time`) values ( '18169408966', '3908', '180.200000000000000000', '1', '李东云', '湖南省长沙市开福区通泰街万达A座', '公司', '1', '22.330000000000000000', null);

INSERT INTO `mer_category` VALUES ('1', 'd000e3c443794213a92d61a9c6f6f6fe', '0', '3', '1709867459998183336', '1'), ('2', 'd000e3c443794213a92d61a9c6f6f6fe', '0', '4', '1710050416910217910', '1');
