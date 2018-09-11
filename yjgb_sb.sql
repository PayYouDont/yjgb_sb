/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50705
 Source Host           : localhost:3306
 Source Schema         : yjgb_sb

 Target Server Type    : MySQL
 Target Server Version : 50705
 File Encoding         : 65001

 Date: 07/09/2018 15:23:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for accidentlevel
-- ----------------------------
DROP TABLE IF EXISTS `accidentlevel`;
CREATE TABLE `accidentlevel`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `level` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应急消息严重程度文字说明',
  `levelCode` int(20) NULL DEFAULT NULL COMMENT '应急消息严重程度等级',
  `number` int(20) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of accidentlevel
-- ----------------------------
INSERT INTO `accidentlevel` VALUES (2, '1级（特别重大）', 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `accidentlevel` VALUES (3, '2级（重大）', 2, 2, NULL, NULL, '2018-07-30 16:25:36', 'admin');
INSERT INTO `accidentlevel` VALUES (4, '3级（较大）', 3, 3, NULL, NULL, '2018-07-30 16:30:35', 'admin');
INSERT INTO `accidentlevel` VALUES (5, '4级(一般)', 4, 4, NULL, NULL, '2018-07-30 14:44:37', 'admin');

-- ----------------------------
-- Table structure for accidenttype
-- ----------------------------
DROP TABLE IF EXISTS `accidenttype`;
CREATE TABLE `accidenttype`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名称',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编号',
  `number` int(20) NULL DEFAULT NULL COMMENT '序号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改日期',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `updata_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 979 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of accidenttype
-- ----------------------------
INSERT INTO `accidenttype` VALUES (1, '测试专用', '00000', 0, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (2, '突发事件', '10000', 1, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (3, '自然灾害', '11000', 2, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (4, '水旱灾害', '11A00', 3, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (5, '洪水', '11A01', 4, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (6, '内涝', '11A02', 5, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (7, '水库重大险情', '11A03', 6, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (8, '堤防重大险情', '11A04', 7, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (9, '凌汛灾害', '11A05', 8, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (10, '山洪灾害事件', '11A51', 9, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (11, '农业干旱', '11A52', 10, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (12, '城镇缺水', '11A53', 11, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (13, '生态干旱', '11A54', 12, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (14, '农村人畜饮水困难', '11A10', 13, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (15, '其它水旱灾害', '11A99', 14, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (16, '气象灾害', '11B00', 15, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (17, '台风事件', '11B01', 16, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (18, '龙卷风事件', '11B02', 17, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (19, '暴雨\'事件', '11B03', 18, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (20, '暴雪事件', '11B04', 19, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (21, '寒潮事件', '11B05', 20, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (22, '大风事件', '11B06', 21, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (23, '沙尘暴事件', '11B07', 22, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (24, '低温冻害事件', '11B08', 23, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (25, '巾高温事件', '11B09', 24, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (26, '热浪事件', '11B10', 25, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (27, '干热风', '11B11', 26, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (28, '下击暴流事件', '11B12', 27, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (29, '雪崩事件', '11B13', 28, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (30, '雷电事件', '11B14', 29, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (31, '冰雹事件', '11B15', 30, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (32, '霜冻事件', '11B16', 31, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (33, '大雾事件', '11B17', 32, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (34, '低空风切变事件', '11B18', 33, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (35, '其它气象灾害事件', '11B99', 34, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (36, '地震灾害', '11C00', 35, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (37, '人工地震事件', '11C01', 36, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (38, '天然地震事件', '11C02', 37, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (39, '其它地震灾害', '11C99', 38, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (40, '地质灾害', '11D00', 39, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (41, '滑坡事件', '11D01', 40, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (42, '泥石流事件', '11D02', 41, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (43, '山体崩塌事件', '11D03', 42, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (44, '地面塌陷事件', '11D04', 43, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (45, '地裂缝事件', '11D05', 44, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (46, '地面沉降事件', '11D06', 45, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (47, '火山喷发事件', '11D07', 46, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (48, '其它地质灾害事件', '11D99', 47, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (49, '海洋灾害事件', '11E00', 48, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (50, '海啸事件', '11E01', 49, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (51, '风暴潮事件', '11E02', 50, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (52, '海冰事件', '11E03', 51, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (53, '巨浪事件', '11E04', 52, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (54, '赤潮事件', '11E05', 53, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (55, '其它海洋灾害事件', '11E99', 54, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (56, '生物灾害事件', '11F00', 55, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (57, '农业病害事件', '11F01', 56, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (58, '农业虫害事件', '11F02', 57, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (59, '农业草害事件', '11F03', 58, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (60, '农业鼠害事件', '11F04', 59, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (61, '森林病害事件', '11F05', 60, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (62, '森林虫害事件', '11F06', 61, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (63, '森林鼠害事件', '11F07', 62, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (64, '农业转基因生物安全突发事件', '11F08', 63, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (65, '林业转基因生物安全突发事件', '11F09', 64, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (66, '林业有害植物事件', '11F10', 65, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (67, '外来有害动植物威胁农业生产事件', '11F11', 66, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (68, '外来有害动植物威胁林业生产事件', '11F12', 67, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (69, '其它生物灾害', '11F99', 68, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (70, '森林草原火灾', '11G00', 69, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (71, '境内森林火灾事件', '11001', 70, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (72, '跨境森林火灾事件', '11002', 71, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (73, '境外威胁我国境内的森林火灾', '11003', 72, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (74, '其他森林火灾事件', '11004', 73, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (75, '境内草原火灾事件', '11005', 74, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (76, '跨境草原火灾事件', '11006', 75, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (77, '境外威胁我国境内的草原火灾', '11007', 76, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (78, '其他草原火灾事件', '11008', 77, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (79, '其他自然灾害事件', '11Y00', 78, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (80, '事故灾难', '12000', 79, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (81, '煤矿事故', '12A00', 80, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (82, '煤矿瓦斯事故', '12A01', 81, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (83, '煤矿顶板事故', '12A02', 82, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (84, '煤矿运输事故', '12A03', 83, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (85, '煤矿水害事故', '12A04', 84, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (86, '煤矿机电事故', '12A05', 85, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (87, '煤矿放炮事故', '12A06', 86, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (88, '煤矿火灾事故', '12A07', 87, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (89, '煤矿其他事故', '12A99', 88, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (90, '金属与非金属矿山事故', '12B00', 89, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (91, '金属与非金属矿顶板事故', '12B01', 90, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (92, '金属与非金属矿水害事故', '12B02', 91, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (93, '金属与非金属矿中毒和窒息事故', '12B03', 92, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (94, '金属与非金属矿尾矿库垮坝事故', '12B04', 93, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (95, '金属与非金属矿火灾事故', '12B05', 94, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (96, '金属与非金属矿机电事故', '12B06', 95, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (97, '金属与非金属矿运输事故', '12B07', 96, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (98, '金属与非金属矿放炮事故', '12B08', 97, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (99, '金属与非金属矿火药爆炸事故', '12B09', 98, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (100, '金属与非金属矿山其他事故', '12B99', 99, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (101, '建筑业事故', '12C00', 100, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (102, '房屋建筑与市政工程施工安全事故', '12C01', 101, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (103, '其他建筑施工安全事故', '12C02', 102, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (104, '危险化学品事故', '12D00', 103, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (105, '危险化学品爆炸事故', '12D01', 104, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (106, '危险化学品泄漏事故', '12D02', 105, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (107, '危险化学品火灾事故', '12D03', 106, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (108, '危险化学品中毒和窒息事故', '12D04', 107, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (109, '危险化学品灼烫事故', '12D05', 108, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (110, '危险化学品其他事故', '12D99', 109, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (111, '烟花爆竹和民用爆炸物事故', '12E00', 110, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (112, '烟花爆竹生产企业爆炸事故', '12E01', 111, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (113, '烟花爆竹运输爆炸事故', '12E02', 112, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (114, '民用爆炸物爆炸事故', '12E03', 113, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (115, '其他烟花爆竹事故', '12E99', 114, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (116, '其他工矿商贸事故', '12F00', 115, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (117, '火灾事故', '12G00', 116, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (118, '一般工业建筑火灾', '12G01', 117, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (119, '特种工业建筑火灾', '12G02', 118, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (120, '一般民用建筑火灾', '12G03', 119, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (121, '高层民用建筑火灾', '12G04', 120, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (122, '地下建筑火灾', '12G05', 121, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (123, '公用建筑火灾', '12G06', 122, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (124, '隧道火灾', '12G07', 123, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (125, '其它火灾事故', '12099', 124, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (126, '道路交通事故', '12H00', 125, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (127, '翻车帮件', '12H01', 126, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (128, '撞车事件', '12H02', 127, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (129, '车辆坠水、坠沟事件', '12H03', 128, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (130, '车辆起火事件', '12H04', 129, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (131, '校车交通事故', '12H05', 130, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (132, '其他道路交通事故', '12H99', 131, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (133, '水上交通事故', '12J00', 132, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (134, '船舶碰撞事故', '12J01', 133, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (135, '船舶触礁事故', '12J02', 134, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (136, '船舶触损事故', '12J03', 135, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (137, '船舶搁浅事故', '12J04', 136, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (138, '船舶遭受风灾事故', '12J05', 137, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (139, '船舶火灾事故', '12J06', 138, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (140, '船舶失踪事故', '12J07', 139, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (141, '船舶海上遇险事故', '12J08', 140, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (142, '水上保安事件', '12J09', 141, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (143, '沿海渔业设施事故', '12J10', 142, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (144, '其他水上交通事故', '12J99', 143, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (145, '铁路交通事故', '12K00', 144, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (146, '列车脱轨事故', '12K01', 145, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (147, '列车追尾事故', '12K02', 146, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (148, '列车撞车事故', '12K03', 147, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (149, '列车撞人事故', '12K04', 148, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (150, '列车火灾、爆炸事故', '12K05', 149, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (151, '其他铁路交通事故', '12K99', 150, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (152, '城市轨道交通事故', '12L00', 151, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (153, '地铁、轻轨、单轨列车脱轨事故', '12L01', 152, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (154, '地铁、轻轨、单轨列车追尾事故', '12L02', 153, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (155, '地铁、轻轨、单轨列车撞车事故', '12L03', 154, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (156, '地铁、轻轨、单轨列车撞人事故', '12L04', 155, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (157, '地铁、轻轨、单轨列车火灾、爆炸事故', '12L05', 156, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (158, '其他城市轨道交通事故', '12L99', 157, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (159, '民用航空器事故', '12M00', 158, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (160, '坠机事件', '12M01', 159, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (161, '撞机事件', '12M02', 160, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (162, '其他民用航空器飞行事故', '12M99', 161, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (163, '特种设备事故', '12N00', 162, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (164, '锅炉事故', '12N01', 163, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (165, '压力容器事故', '12N02', 164, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (166, '压力管道事故', '12N03', 165, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (167, '电梯事故', '12N04', 166, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (168, '起重机械事故', '12N05', 167, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (169, '客运索道事故', '12N06', 168, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (170, '大型游乐设施事故', '12N07', 169, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (171, '其他特种设备事故', '12N99', 170, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (172, '基础设施和公用设施事故', '12P00', 171, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (173, '公路交通设施事故', '12P01', 172, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (174, '铁路交通设施事故', '12P02', 173, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (175, '城市轨道交通设施事故', '12P03', 174, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (176, '城市桥梁隧道设施事故', '12P04', 175, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (177, '水运交通设施事故', '12P05', 176, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (178, '民航交通设施事故', '12P06', 177, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (179, '水利设施事故', '12P07', 178, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (180, '电力基础设施事故', '12P08', 179, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (181, '石油天然气基础设施事故', '12P09', 180, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (182, '通讯基础设施事故', '12P10', 181, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (183, '金融基础设施事故', '12P11', 182, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (184, '城市生命线基础设施事故', '12P12', 183, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (185, '建筑垮塌事故', '12P13', 184, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (186, '其他公用设施和设备事故', '12P99', 185, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (187, '环境污染和生态破坏事件', '12Q00', 186, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (188, '水域污染事件', '12Q01', 187, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (189, '空气污染事件', '12Q02', 188, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (190, '土壤污染事件', '12Q03', 189, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (191, '海上溢油事件', '12Q04', 190, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (192, '污染导致城市水源供水中断事故', '12Q05', 191, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (193, '转基因生物生态破坏事件', '12Q06', 192, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (194, '盗伐、滥伐、哄抢森林事件', '12Q07', 193, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (195, '毁林、乱占林地、非法改变林地用途事件', '12Q10', 194, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (196, '濒危物种生存环境遭受环境污染事件', '12Q11', 195, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (197, '野生动（植）物种群大批死亡事件', '12Q12', 196, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (198, '自然保护区、风景名胜区生态破坏', '12Q13', 197, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (199, '进口再生原料污染事件', '12Q14', 198, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (200, '非法倾倒、埋藏剧毒危险废物事件', '12Q15', 199, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (201, '其它环境污染和生态破坏事件', '12Q99', 200, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (202, '农业机械事故', '12R00', 201, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (203, '农业机械行驶事故', '12R01', 202, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (204, '农业机械作业事故', '12R02', 203, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (205, '农业机械碾压事件', '12R03', 204, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (206, '农业机械碰撞事件', '12R04', 205, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (207, '农业机械翻车事件', '12R05', 206, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (208, '农业机械落车事件', '12R06', 207, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (209, '农业机械火灾事件', '12R07', 208, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (210, '其他农业机械事故', '12R99', 209, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (211, '踩踏事件', '12800', 210, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (212, '公园组织大型群众性活动或聚会踩踏事件', '12801', 211, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (213, '校园踩踏事件', '12802', 212, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (214, '其他踩踏事件', '12899', 213, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (215, '核与辐射事故', '12T00', 214, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (216, '核设施事故', '12T01', 215, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (217, '放射性物质运输事故', '12T02', 216, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (218, '放射源事故', '12T03', 217, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (219, '射线装置事故', '12T99', 218, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (220, '能源供应中断事故', '12U00', 219, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (221, '其他事故灾难', '12Y00', 220, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (222, '公共卫生事件', '13000', 221, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (223, '传染病事件', '13A00', 222, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (224, '鼠疫流行事件', '13A01', 223, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (225, '霍乱流行事件', '13A02', 224, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (226, '肺炭疽流行事件', '13A03', 225, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (227, '传染性非典型肺炎流行事件', '13A04', 226, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (228, '大感染高致病性禽流感流行事件', '13A05', 227, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (229, '其他甲类或甲类管理传染病流行事件', '13A06', 228, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (230, '其他乙类传染病流行事件', '13A07', 229, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (231, '新传染病或我国尚未发现的传染病传入事件', '13A08', 230, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (232, '我国己消灭传染病重新流行事件', '13A09', 231, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (233, '其它传染病事件流行事件', '13A99', 232, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (234, '食品药品安全事件', '13B00', 233, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (235, '药品安全事件', '13B01', 234, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (236, '群体性预防接种反应', '13B02', 235, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (237, '食品安全事件', '13B03', 236, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (238, '农产品质量安全事件', '13B04', 237, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (239, '其它食品药品安全事件', '13B99', 238, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (240, '群体性中毒、感染事件', '13C00', 239, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (241, '急性职业中毒事件', '13C01', 240, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (242, '重金属中毒事件', '13C02', 241, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (243, '非职业性一氧化碳中毒事件', '13C03', 242, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (244, '其他群体性中毒感染事件', '13C99', 243, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (245, '病原微生物、菌毒株事件', '13D00', 244, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (246, '菌株、毒株、致病因子丢失事件', '13D01', 245, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (247, '隐匿运输、邮寄病原体、生物毒素', '13D02', 246, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (248, '医源性感染事件', '13D03', 247, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (249, '其他病原微生物、菌毒株事件', '13D99', 248, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (250, '动物疫情事件', '13E00', 249, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (251, '高致病性禽流感', '13E01', 250, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (252, '口蹄疫', '13E02', 251, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (253, '疯牛病', '13E03', 252, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (254, '猪瘟', '13E04', 253, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (255, '新城疫', '13E05', 254, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (256, '蓝舌病', '13E06', 255, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (257, '动物布鲁氏菌病', '13E07', 256, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (258, '动物结核病', '13E08', 257, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (259, '狂犬病', '13E09', 258, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (260, '动物炭瘟病', '13E10', 259, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (261, '反鱼兽疫', '13E11', 260, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (262, '我国未发的动物疫病传入事件', '13E12', 261, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (263, '我国己消灭动物疫病重新流行事件', '13E13', 262, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (264, '其他动物疫情事件', '13E99', 263, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (265, '群体性不明原因疾病', '13F00', 264, '2018-06-28 16:44:40', '2018-06-28 16:44:40', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (266, '其它严重影响公共健康和卫生安全事件', '13600', 265, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (267, '其社会他公共卫生事件', '13Y00', 266, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (268, '安全事件', '14000', 267, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (269, '群体性事件', '14A00', 268, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (270, '非法集会游行示威', '14A01', 269, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (271, '集体上访请愿', '14A02', 270, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (272, '冲击、围攻党政军机关和要害部门事件', '14A03', 271, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (273, '大规模打、砸、抢、烧犯罪事件', '14A04', 272, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (274, '群体性械斗、冲突事件', '14A05', 273, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (275, '静坐事件', '14A06', 274, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (276, '罢市、罢工', '14A07', 275, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (277, '罢课', '14AI0', 276, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (278, '高校内聚集事件失控', '14A11', 277, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (279, '高校校园网大范围串联、煽动和蛊惑信息事件', '14A12', 278, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (280, '阻断交通事件', '14A13', 279, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (281, '阻挠、妨碍国家重点建设工程施工事件', '14A14', 280, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (282, '暴狱事件', '14A15', 281, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (283, '聚众闹事', '14A16', 282, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (284, '其它群体事件', '14A99', 283, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (285, '刑事案件', '14B00', 284, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (286, '杀人案件', '14B01', 285, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (287, '爆炸案件', '14B02', 286, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (288, '放火案件', '14B03', 287, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (289, '投放危险物质案件', '14B04', 288, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (290, '以危害方法危害公共安全案件', '14B05', 289, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (291, '绑架案件', '14B06', 290, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (292, '抢劫、盗窃金融机构或运钞车案件', '14B07', 291, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (293, '抢劫、走私、盗窃军(警)用枪械案件', '14B08', 292, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (294, '放射性材料被盗、丢失案件', '14B09', 293, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (295, '炸药、雷管被盗、丢失案件', '14B10', 294, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (296, '走私放射性材料案件', '14B11', 295, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (297, '走私固体废物案件', '14B12', 296, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (298, '制贩毒品案件', '14B13', 297, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (299, '盗窃、出卖、泄露及丢失国家秘密案件', '14B14', 298, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (300, '攻击破坏计算机网络案件', '14BJ5', 299, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (301, '攻击破坏卫星通信、广播电视传输系统案件', '14B16', 300, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (302, '制售假劣药品、医疗器械案件', '14B17', 301, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (303, '制售不符合卫生标准、有毒有害食品', '14B18', 302, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (304, '走私、骗汇、逃汇、洗钱案件', '14B19', 303, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (305, '金融诈骗案', '14B20', 304, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (306, '增值税发票及其他票证案', '14B21', 305, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (307, '假劣种子、化肥、农药坑农案件', '14B22', 306, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (308, '非法狗猎、采集保护野生动植物案件', '14B23', 307, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (309, '破坏物种资源案件', '14B24', 308, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (310, '偷渡案件', '14B25', 309, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (311, '其它刑事案件', '14B99', 310, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (312, '金融突发事件', '14C00', 311, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (313, '银行业金融突发事件', '14C01', 312, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (314, '证券业金融突发事件', '14C02', 313, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (315, '保险业金融突发事件', '14C03', 314, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (316, '外汇类突发事件', '14C04', 315, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (317, '货币发行类突发事件', '14C05', 316, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (318, '支付结算类突发事件', '14C06', 317, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (319, '其它金融突发事件', '14C99', 318, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (320, '影响市场稳定的突发事件', '14D00', 319, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (321, '粮食市场异常波动', '14D01', 320, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (322, '生活必需品市场异常波动', '14D02', 321, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (323, '其他影响市场稳定的突发事件', '14D99', 322, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (324, '民族和宗教事件', '14E00', 323, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (325, '民族分裂活动', '14E01', 324, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (326, '宗教大规模非法聚会', '14E02', 325, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (327, '民族冲突事件', '14E03', 326, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (328, '宗教冲突事件', '14E04', 327, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (329, '其它民族宗教事件', '14E99', 328, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (330, '恐怖袭击事件', '14F00', 329, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (331, '袭击公共聚集场所事件', '14F01', 330, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (332, '袭击党政军首脑机关事件', '14F02', 331, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (333, '袭击城市标志性建筑物事件', '14F03', 332, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (334, '袭击国防设施事件', '14F04', 333, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (335, '袭击宗教场所事件', '14F05', 334, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (336, '袭击外交机构或国际组织事件', '14F06', 335, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (337, '袭击重要经济目标事件', '14F07', 336, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (338, '袭击重要基础设施事件', '14F08', 337, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (339, '袭击城市基础设施事件', '14F09', 338, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (340, '袭击交通工具事件', '14F10', 339, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (341, '袭击重要计算机信息网络系统事件', '14F11', 340, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (342, '袭击通信或新闻中枢事件', '14F12', 341, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (343, '袭击重要核生化设施事件', '14F13', 342, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (344, '袭击党政军要员事件', '14F14', 343, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (345, '袭击外交人员事件', '14F15', 344, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (346, '袭击平民事件', '14F16', 345, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (347, '袭击宗教人士事件', '14F17', 346, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (348, '袭击知名人士事件', '14F18', 347, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (349, '袭击外国公民事件', '14F19', 348, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (350, '核生化战剂袭击事件', '14F20', 349, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (351, '劫持航空器事件', '14F21', 350, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (352, '劫持船舶事件', '14F22', 351, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (353, '劫持火车事件', '14F23', 352, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (354, '袭击警卫对象、警卫现场事件', '14F24', 353, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (355, '其它恐怖袭击事件', '14F99', 354, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (356, '涉外事件', '14G00', 355, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (357, '政治类涉外事件', '14G01', 356, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (358, '经济类涉外事件', '14G02', 357, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (359, '灾害事故卫生类涉外事件', '14G03', 358, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (360, '恐怖暴力类涉外事件', '14G04', 359, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (361, '境外敌对势力类涉外事件', '14G05', 360, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (362, '社会安全类涉外事件', '14G06', 361, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (363, '其它涉外事件', '14G99', 362, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (364, '信息安全事件', '14H00', 363, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (365, '影响公共互联网骨干网的大规模网络病毒传播事件', '14H01', 364, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (366, '针对公共互联网核心设备的网络攻击事件', '14H02', 365, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (367, '针对国家重要信息系统的网络攻击入侵事件', '14H03', 366, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (368, '其他信息安全事件', '14H99', 367, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (369, '其他社会安全事件', '14Y00', 368, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (370, '行政区划界限纠纷事件', '14Y01', 369, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (371, '行政区划调整和地名变更引发事件', '14Y02', 370, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (372, '其它突发事件', '19000', 371, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (373, '危险源和风险隐患区', '20000', 372, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (374, '自然灾害风险隐患区', '21000', 373, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (375, '水旱灾害风险区', '21A00', 374, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (376, '洪水灾害风险区', '21A01', 375, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (377, '内涝灾害风险区', '21A02', 376, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (378, '冰凌灾害风险区', '21A03', 377, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (379, '山洪灾害风险区', '21A04', 378, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (380, '农业干旱风险区', '21A51', 379, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (381, '城镇缺水风险区', '21A52', 380, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (382, '生态干旱风险区', '21A53', 381, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (383, '农村饮水困难风险区', '21A54', 382, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (384, '其它水旱灾害风险区', '21A99', 383, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (385, '气象灾害风险区', '21B00', 384, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (386, '台风灾害风险区', '21B01', 385, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (387, '龙卷风灾害风险区', '21B02', 386, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (388, '暴雨灾害风险区', '21B03', 387, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (389, '暴雪灾害风险区', '21B04', 388, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (390, '寒潮灾害风险区', '21B05', 389, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (391, '大风灾害风险区', '21B06', 390, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (392, '沙尘暴灾害风险区', '21B07', 391, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (393, '低温冻害风险区', '21B08', 392, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (394, '雷电灾区', '21B09', 393, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (395, '冰雹灾害风险区', '21B10', 394, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (396, '霜冻灾害风险区', '21B11', 395, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (397, '大雾灾害风险区', '21B12', 396, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (398, '其它气象灾害风险区', '21B99', 397, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (399, '海洋灾害风险区', '21C00', 398, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (400, '海啸风险区', '21C01', 399, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (401, '风暴湖风险区', '21C02', 400, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (402, '海冰风险区', '21C03', 401, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (403, '巨浪风险区', '21C04', 402, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (404, '赤潮风险区', '21C0S', 403, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (405, '其它海洋灾害风险区', '21C99', 404, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (406, '地质灾害风险区', '21D00', 405, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (407, '滑坡区', '21D01', 406, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (408, '泥石流区', '21D02', 407, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (409, '崩塌区', '21D03', 408, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (410, '地陷区', '21D04', 409, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (411, '地裂区', '21D05', 410, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (412, '火山喷发区', '21D06', 411, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (413, '其它地质灾害风险区', '21D99', 412, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (414, '地震灾害风险区', '21E00', 413, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (415, '地震带', '21E01', 414, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (416, '其它地震灾害风险区', '21E99', 415, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (417, '生物灾害风险区', '21F00', 416, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (418, '农业生物灾害风险区', '21F01', 417, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (419, '林业生物灾害风险区', '21F02', 418, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (420, '畜牧业生物灾害风险区', '21F03', 419, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (421, '兽医生物灾害风险区', '21F04', 420, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (422, '渔业生物灾害风险区', '21F05', 421, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (423, '其它生物灾害风险区', '21F99', 422, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (424, '森林草原火灾风险区', '21G00', 423, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (425, '森林火灾风险区', '21001', 424, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (426, '草原火灾风险区', '21002', 425, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (427, '其它森林草原火灾风险区', '21099', 426, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (428, '其它自然灾害风险区', '21Y00', 427, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (429, '事故灾难危险源', '22000', 428, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (430, '生产安全危险源', '22A00', 429, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (431, '贮罐区(贮罐)', '22A01', 430, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (432, '库区(库)', '22A02', 431, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (433, '生产场所', '22A03', 432, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (434, '压力管道', '22A04', 433, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (435, '锅炉', '22A05', 434, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (436, '压力容器', '22A06', 435, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (437, '煤矿', '22A07', 436, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (438, '金属非金属地下矿山', '22A08', 437, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (439, '尾矿库', '22A09', 438, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (440, '气瓶充装站', '22A10', 439, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (441, '加油站', '22A11', 440, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (442, '油气田', '22A12', 441, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (443, '其他生产安全危险源', '22A99', 442, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (444, '运输安全危险源', '22B00', 443, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (445, '危险化学品运输车辆', '22B01', 444, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (446, '危险化学品运输船舶', '22B02', 445, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (447, '油轮', '22B03', 446, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (448, '其他运输安全危险源', '22B99', 447, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (449, '其它事故灾难安全危险源', '22Y00', 448, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (450, '公共卫生危险源', '23000', 449, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (451, '自然疫源地类危险源', '23A00', 450, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (452, '医疗机构类危险源', '23B00', 451, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (453, '病原微生物实验室类危险源', '23C00', 452, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (454, '菌毒株保藏类危险源', '23D00', 453, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (455, '其它公共卫生危险源', '23Y00', 454, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (456, '社会安全隐患', '24000', 455, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (457, '国际恐怖组织', '24A00', 456, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (458, '国内恐怖组织', '24B00', 457, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (459, '贩毒组织', '24C00', 458, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (460, '涉黑团体', '24D00', 459, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (461, '非法民间组织', '24E00', 460, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (462, '其他非法组织', '24F00', 461, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (463, '其他社会安全隐患', '24Y00', 462, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (464, '其他危险源和风险隐患区', '29000', 463, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (465, '防护目标', '30000', 464, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (466, '重要部位', '31000', 465, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (467, '政府部门', '31A00', 466, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (468, '各级党委', '31A01', 467, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (469, '各级人大', '31A02', 468, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (470, '各级政府', '31A03', 469, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (471, '各级政协', '31A04', 470, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (472, '各级公安机关', '31A05', 471, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (473, '各级检察机关', '31A06', 472, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (474, '各级法院', '31A07', 473, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (475, '其他政府部门', '31A99', 474, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (476, '学校', '31B00', 475, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (477, '大学', '31B01', 476, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (478, '普通高中', '31B02', 477, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (479, '职业高中', '31B03', 478, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (480, '中专', '31B04', 479, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (481, '初级中学', '31B05', 480, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (482, '小学', '31B06', 481, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (483, '幼儿园', '31B07', 482, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (484, '其他学校', '31B99', 483, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (485, '科研机构', '31C00', 484, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (486, '国家实验室', '31C01', 485, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (487, '国家重点实验室', '31C02', 486, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (488, '国家工程技术中心', '31C03', 487, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (489, '国家工程中心', '31C04', 488, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (490, '部门重点实验室', '31C05', 489, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (491, '省级研究院所', '31C06', 490, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (492, '其他科研机构', '31C99', 491, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (493, '新闻广播机构', '31D00', 492, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (494, '电视台', '31D01', 493, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (495, '电台', '31D02', 494, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (496, '报社', '31D03', 495, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (497, '网站', '31D04', 496, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (498, '其他新闻广播机构', '31D99', 497, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (499, '国防目标', '31E00', 498, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (500, '驻地', '31E01', 499, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (501, '军事基地', '31E02', 500, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (502, '军事机场', '31E03', 501, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (503, '军事机场', '31E04', 502, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (504, '军事仓库', '31E05', 503, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (505, '军事工程', '31E06', 504, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (506, '其它军事目标', '31E99', 505, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (507, '公众聚集场所', '31F00', 506, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (508, '体育馆', '31F01', 507, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (509, '大型商场', '31F02', 508, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (510, '影剧院', '31F03', 509, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (511, '其它公众聚集场所', '31F99', 510, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (512, '金融机构', '31G00', 511, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (513, '银行', '31G01', 512, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (514, '金库', '31G02', 513, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (515, '造币厂', '31G03', 514, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (516, '证券交易场所', '31G04', 515, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (517, '金融机构数据中心', '31G05', 516, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (518, '金融机构数据备份中心', '31G06', 517, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (519, '交易清算中心', '31G07', 518, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (520, '其他金融机构', '31G99', 519, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (521, '重要场所', '31H00', 520, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (522, '重点居住场所', '31H01', 521, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (523, '使领馆', '31H02', 522, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (524, '古建筑', '31H03', 523, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (525, '储备物资库', '31H04', 524, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (526, '其他重要场所', '31H99', 525, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (527, '监测台站', '31J00', 526, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (528, '地震监测台站', '31J01', 527, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (529, '地质监测台站', '31J02', 528, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (530, '水文监测台站', '31J03', 529, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (531, '气象监测台站', '31J04', 530, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (532, '环保监测台站', '31J05', 531, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (533, '野生动物疫源病疫病监测站', '31J06', 532, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (534, '其它监测台站', '31J99', 533, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (535, '野生动物保护管理场所', '31K00', 534, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (536, '自然保护区', '31K01', 535, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (537, '狞猎区', '31K02', 536, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (538, '动物园', '31K03', 537, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (539, '野生动物园', '31K04', 538, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (540, '濒危野生动物拯救(救护)繁育基地', '31K05', 539, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (541, '野生动物种源繁育基地', '31K06', 540, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (542, '其他野生动物保护管理场所', '31K99', 541, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (543, '重要生态安全区', '31L00', 542, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (544, '重点风景名胜区', '31L01', 543, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (545, '重要生态区', '31L02', 544, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (546, '自然遗产区', '31L03', 545, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (547, '其他重要生态区', '31L99', 546, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (548, '在建工程施工现场', '31M00', 547, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (549, '其他重要部位', '31Y00', 548, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (550, '关键基础设施', '32000', 549, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (551, '通信系统', '32A00', 550, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (552, '长途枢纽楼', '32A0I', 551, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (553, '国际出入口局', '32A02', 552, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (554, '光缆干线', '32A03', 553, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (555, '通信中心机房', '32A04', 554, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (556, '其他通信基础设施', '32A99', 555, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (557, '公路基础设施', '32B00', 556, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (558, '公路', '32B0I', 557, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (559, '公路桥梁', '32B02', 558, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (560, '公路隧道', '32B03', 559, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (561, '其他公路基础设施', '32B99', 560, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (562, '铁路基础设施', '32C00', 561, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (563, '铁路', '32C0I', 562, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (564, '铁路站房', '32C02', 563, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (565, '铁路桥梁', '32C03', 564, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (566, '铁路隧道', '32C04', 565, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (567, '其他通铁路基础设施', '32C99', 566, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (568, '水运交基础设施', '32D00', 567, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (569, '河道', '32D01', 568, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (570, '内河港口码头', '32D02', 569, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (571, '沿海港口码头', '32D03', 570, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (572, '船→刊', '32D04', 571, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (573, '航道枢纽工程', '32D05', 572, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (574, '其他水运交通基础设施', '32D99', 573, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (575, '民航交通设施', '32E00', 574, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (576, '机场民航专业工程', '32E0I', 575, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (577, '机场附属工程', '32E02', 576, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (578, '其他民航交通基础设施', '32E99', 577, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (579, '水利设施', '32F00', 578, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (580, '水库', '32F01', 579, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (581, '拦河水闸工程', '32F02', 580, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (582, '排灌泵站工程', '32F03', 581, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (583, '堤防工程', '32F04', 582, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (584, '引调水工程', '32F05', 583, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (585, '其他水利设施', '32F99', 584, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (586, '电力基础设施', '32G00', 585, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (587, '火力发电厂', '32001', 586, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (588, '水电站', '32002', 587, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (589, '核电厂', '32003', 588, '2018-06-28 16:44:41', '2018-06-28 16:44:41', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (590, '工矿企业自备电厂', '32004', 589, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (591, '变电所', '32005', 590, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (592, '输电线路大跨越塔', '32006', 591, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (593, '电力系统通信设施', '32007', 592, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (594, '电力枢纽', '32008', 593, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (595, '电力调度控制中心', '32009', 594, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (596, '其他电力基础设施', '32099', 595, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (597, '石油天然气设施', '32H00', 596, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (598, '输油管道设施', '32H01', 597, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (599, '天然气输气管网', '32H02', 598, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (600, '油气田集输处理设施', '32H03', 599, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (601, '石油天然气基础设施', '32H99', 600, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (602, '城市基础设施', '32J00', 601, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (603, '城市道路', '32J01', 602, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (604, '城市桥梁', '32J02', 603, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (605, '城市隧道', '32J03', 604, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (606, '城市轨道交通设施', '32J04', 605, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (607, '城市供水系统', '32J05', 606, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (608, '城市供电系统', '32J06', 607, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (609, '城市供气系统', '32J07', 608, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (610, '城市排水泄洪系统', '32J08', 609, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (611, '城市供热系统', '32J09', 610, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (612, '城市垃圾收运处理系统', '32J10', 611, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (613, '其他石油天然气基础设施', '32J99', 612, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (614, '其他防护目标', '39000', 613, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (615, '应急保障资源', '40000', 614, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (616, '应急机构', '41000', 615, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (617, '领导机构', '41A00', 616, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (618, '国务院', '41A01', 617, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (619, '军队', '41A02', 618, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (620, '省、自治区、直辖市政府', '41A03', 619, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (621, '省级有关部门', '41A04', 620, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (622, '市政府', '41A05', 621, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (623, '市级有关部门', '41A06', 622, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (624, '县政府', '41A07', 623, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (625, '县级有关部门', '41A08', 624, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (626, '其他领导机构', '41A99', 625, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (627, '办事机构', '41B00', 626, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (628, '国务院应急办', '41B01', 627, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (629, '军队应急管理机构', '41B02', 628, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (630, '国务院有关部门应急办', '41B03', 629, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (631, '省应急办', '41B04', 630, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (632, '省级有关部门应急办', '41B05', 631, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (633, '市应急办', '41B06', 632, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (634, '市级有关部门应急办', '41B07', 633, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (635, '县应急办', '41B08', 634, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (636, '县级有关部门应急办', '41B09', 635, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (637, '其他办事机构', '41B99', 636, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (638, '指挥和综合协调机构', '41C00', 637, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (639, '国家防汛抗旱总指挥部', '41C01', 638, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (640, '国家减灾委员会', '41C02', 639, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (641, '国务院抗震救灾指挥部', '41C03', 640, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (642, '国务院地质灾害应急防治总指挥部', '41C04', 641, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (643, '国家林业局扑火指挥部', '41C05', 642, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (644, '国务院安委会', '41C06', 643, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (645, '国家处置铁路行车事故应急救援领导小组', '41C07', 644, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (646, '国家处置民用航空器飞行事故应急指挥部', '41C08', 645, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (647, '中国海上搜救中心', '41C09', 646, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (648, '城市地铁事故灾难应急领导小组', '41CI0', 647, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (649, '大面积电网停电事件应急领导小组', '41C11', 648, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (650, '国家核应急协调委', '41C12', 649, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (651, '全国环境保护部际联席会议', '41C13', 650, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (652, '国家通信保障应急领导小组', '41C14', 651, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (653, '国家突发公共卫生事件应急指挥部', '41C15', 652, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (654, '国家公共事件医疗卫生救援领导小组', '41C16', 653, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (655, '国家突发重大动物疫情应急指挥部', '41C17', 654, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (656, '国家重大食品安全事故应急指挥部', '41C18', 655, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (657, '国家粮食应急工作指挥部', '41C19', 656, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (658, '国家突发金融事件应急领导小组', '41C20', 657, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (659, '国家涉外突发事件应急总指挥部', '41C21', 658, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (660, '国家处置大规模恐怖袭击事件指挥部', '41C22', 659, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (661, '国家处置劫机事件应急领导小组', '41C23', 660, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (662, '新闻发布领导小组', '41C24', 661, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (663, '林业有害生物灾害应急指挥机构', '41C20', 662, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (664, '安全生产应急救援指挥中心', '41C21', 663, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (665, '其他工作机构', '41C99', 664, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (666, '应急人力资源', '42000', 665, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (667, '专家', '42A00', 666, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (668, '自然灾害类专家', '42A01', 667, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (669, '事故灾难类专家', '42A02', 668, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (670, '公共卫生类专家', '42A03', 669, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (671, '社会安全类专家', '42A04', 670, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (672, '综合类专家', '42A05', 671, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (673, '其它专家', '42A99', 672, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (674, '军队', '42B00', 673, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (675, '抗洪抢险专业部队', '42B01', 674, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (676, '国家地震灾害紧急救援队', '42B02', 675, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (677, '国家空中运输队', '42B03', 676, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (678, '专业救援队伍', '42E00', 677, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (679, '专业森林消防队', '42E12', 678, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (680, '林业有害生物灾害应急专业队', '42E13', 679, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (681, '食物中毒事件应急预备队', '42E14', 680, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (682, '路桥抢修队', '42E15', 681, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (683, '通信保障队', '42E16', 682, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (684, '电力抢修队', '42E17', 683, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (685, '供气抢修队', '42E18', 684, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (686, '供水抢修队', '42E19', 685, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (687, '排水污水处理抢险队', '42E20', 686, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (688, '园林养护抢险队', '42E21', 687, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (689, '其它专业救援队伍', '42E99', 688, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (690, '应急物资保障资源', '43000', 689, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (691, '国家战略性储备物资', '43A00', 690, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (692, '国家粮食和食用植物油储备', '43A01', 691, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (693, '国家能源储备', '43A02', 692, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (694, '国家医药储备', '43A03', 693, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (695, '其他国家战略性储备物资', '43A99', 694, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (696, '专用应急物资及储备', '43B00', 695, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (697, '防汛抗旱专用物资', '43B01', 696, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (698, '抗震专用物资', '43B02', 697, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (699, '防灾减灾专用物资', '43B03', 698, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (700, '防疫应急专用物资', '43B04', 699, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (701, '林业有害生物灾害应急防控专用物资', '43B05', 700, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (702, '危险化学品事故救援专用物资', '43B06', 701, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (703, '矿山事故救援专用物资', '43B07', 702, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (704, '油污染处置物资', '43B08', 703, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (705, '其它专项救援物资储备', '43B99', 704, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (706, '基本生活物资保障', '43C00', 705, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (707, '粮食', '43C01', 706, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (708, '除粮食之外的食品', '43C02', 707, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (709, '食用油', '43C03', 708, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (710, '衣被', '43C04', 709, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (711, '饮用水', '43C05', 710, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (712, '救灾帐篷', '43C06', 711, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (713, '其它基本生活物资', '43C99', 712, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (714, '应急通信资源', '44000', 713, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (715, '通信网', '44A00', 714, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (716, '公用固定电话网', '44A01', 715, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (717, '公用移动电话网', '44A02', 716, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (718, '公用数据及IP通信网', '44A03', 717, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (719, '公用传输网', '44A04', 718, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (720, '公用短波、集群无线网', '44A05', 719, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (721, '专用通信网', '44A06', 720, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (722, '其它通信网', '44A99', 721, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (723, '通信保障机构', '44B00', 722, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (724, '基础电信运营企业集团公司', '44B01', 723, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (725, '基础电信运营企业省公司', '44B02', 724, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (726, '基础电信运营企业地市分公司', '44B03', 725, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (727, '省级基础通信运营企业', '44B04', 726, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (728, '市级基础通信运营企业', '44B05', 727, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (729, '县级基础通信运营企业', '44B06', 728, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (730, '中国交通信通保信障机中心机构', '44B07', 729, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (731, '其他通保障机构', '44B99', 730, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (732, '通信设备', '44C00', 731, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (733, 'VSAT系统', '44C01', 732, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (734, '短波通信系统', '44C02', 733, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (735, '卫星通信系统', '44C03', 734, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (736, '车载变换系统', '44C04', 735, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (737, '车载移动基站', '44C05', 736, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (738, '便携微波通信系统', '44C06', 737, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (739, '海事卫星终端', '44C07', 738, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (740, '移动通信卫星终端', '44C08', 739, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (741, '宽带卫星通信终端', '44C09', 740, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (742, '北斗卫星终端', '44CI0', 741, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (743, '其它通信设备', '44C99', 742, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (744, '应急运输与物流资源', '45000', 743, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (745, '运输站场', '45A00', 744, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (746, '机场客货集散区', '45A01', 745, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (747, '港口客货集散区', '45A02', 746, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (748, '火车站客货集散区', '45A03', 747, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (749, '汽车站客货集散区', '45A04', 748, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (750, '其它运输站场', '45A99', 749, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (751, '运输设备', '45B00', 750, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (752, '航空器', '45B01', 751, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (753, '船', '45B02', 752, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (754, '火车', '45B03', 753, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (755, '汽车', '45B04', 754, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (756, '其它运输设备', '45B99', 755, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (757, '运输保障机构', '45C00', 756, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (758, '航空企业', '45C01', 757, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (759, '航运企业', '45C02', 758, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (760, '铁路运输企业', '45C03', 759, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (761, '汽车运输企业', '45C04', 760, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (762, '其它运输保障机构', '45C99', 761, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (763, '医疗卫生资源', '46000', 762, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (764, '医疗机构', '46A00', 763, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (765, '医院', '46A01', 764, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (766, '疗养院', '46A02', 765, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (767, '社区卫生服务中心(站)', '46A03', 766, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (768, '卫生院', '46A04', 767, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (769, '门诊部', '46A05', 768, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (770, '诊所、卫生所(室)、医务室', '46A06', 769, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (771, '妇幼保健院(所、站)', '46A07', 770, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (772, '职业病防治院(所、站)', '46A08', 771, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (773, '病防治院(所、站、中心)', '46A09', 772, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (774, '急救中心(站)', '46A10', 773, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (775, '其他医疗机构', '46A99', 774, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (776, '疾病预防控制中心(疫站)', '46B00', 775, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (777, '省级疾病预防控制中心(防疫站)', '46B01', 776, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (778, '市级疾病预防控制中心(防疫站)', '46B02', 777, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (779, '县级疾病预防控制中心(防疫站)', '46B03', 778, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (780, '其他疾病预防控制机构', '46B99', 779, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (781, '卫生监督所(局)', '46C00', 780, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (782, '省级卫生监督所(局)', '46C01', 781, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (783, '市级卫生监督所(局)', '46C02', 782, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (784, '县级卫生监督所(局)', '46C03', 783, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (785, '其他卫生监督机构', '46C99', 784, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (786, '医学科学研究机构', '46D00', 785, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (787, '医疗设备和药品', '46E00', 786, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (788, '医疗设备', '46E01', 787, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (789, '救护车', '46E02', 788, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (790, '药品', '46E03', 789, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (791, '疫苗类生物制品', '46E04', 790, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (792, '血液及血制品', '46E05', 791, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (793, '其它医疗设备和药品', '46E99', 792, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (794, '其他医疗卫生资源', '46Y00', 793, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (795, '采供血机构', '46Y01', 794, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (796, '应急避难场区', '47000', 795, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (797, '避难场所', '47A00', 796, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (798, '救助管理站', '47A01', 797, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (799, '公园', '47A02', 798, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (800, '广场', '47A03', 799, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (801, '绿地', '47A04', 800, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (802, '其它避难场所', '47A99', 801, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (803, '人防工事', '47B00', 802, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (804, '防空洞', '47B01', 803, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (805, '防空地下室', '47B02', 804, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (806, '防空警报站点', '47B03', 805, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (807, '其它人防工事', '47B99', 806, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (808, '应急财力资源分类', '48000', 807, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (809, '专项应急资金', '48A00', 808, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (810, '国家级', '48A01', 809, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (811, '省级', '48A02', 810, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (812, '市级', '48A03', 811, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (813, '县级', '48A04', 812, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (814, '其它专项应急资金', '48A99', 813, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (815, '募捐资金', '48B00', 814, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (816, '应急保险', '48C00', 815, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (817, '应急知识', '50000', 816, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (818, '法律法规', '51000', 817, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (819, '突发事件应对法', '51A00', 818, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (820, '中华人民共和国突发事件应对法', '51A01', 819, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (821, '突发事件应对有关行政法规', '51A02', 820, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (822, '突发事件应对有关地方性法规', '51A03', 821, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (823, '突发事件应对有关部门规章', '51A04', 822, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (824, '突发事件应对有关地方政府规章', '51A05', 823, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (825, '其他突发事件应对有关法律法规', '51A99', 824, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (826, '自然灾害有关法律', '51B00', 825, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (827, '水旱灾害有关法律法规', '51B01', 826, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (828, '气象灾害有关法律法规', '51B02', 827, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (829, '地震灾害有关法律法规', '51B03', 828, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (830, '地质灾害有关法律法规', '51B04', 829, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (831, '海洋灾害事件有关法律法规', '51B05', 830, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (832, '生物灾害事件有关法律法规', '51B06', 831, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (833, '森林草原火灾有关法律法规', '51B07', 832, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (834, '其他自然灾害有关法律法规', '51B99', 833, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (835, '事故灾难有关法律法规', '51C00', 834, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (836, '工矿商贸事故有关法律法规', '51C01', 835, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (837, '火灾事故有关法律法规', '51C03', 836, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (838, '交通运输事故有关法律法规', '51C04', 837, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (839, '特种有关法律法规', '51C06', 838, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (840, '基础设施和公用设施事故有关法律法规', '51C07', 839, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (841, '环境污染和生态破坏事件有关法律法规', '51C08', 840, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (842, '农业机械事故有关法律法规', '51C09', 841, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (843, '核与辐射事故有关法律法规', '51C10', 842, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (844, '其他事故灾难有关法律法规', '51C99', 843, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (845, '公共卫生有关法律', '51D00', 844, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (846, '传染病疫情有关法律法规', '51D01', 845, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (847, '食品药品安全有关法律法规', '51D02', 846, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (848, '职业危害有关法律法规', '51D03', 847, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (849, '动物疫情有关法律法规', '51D04', 848, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (850, '群体不明原因疾病有关法律法规', '51D05', 849, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (851, '其他公共卫生事件有关法律法规', '51D99', 850, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (852, '社会安全有关法律法规', '51E00', 851, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (853, '群体性事件有关法律法规', '51E01', 852, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (854, '刑事案件有关法律法规', '51E02', 853, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (855, '金融突发事件有关法律法规', '51E03', 854, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (856, '民族和宗教事件有关法律法规', '51E04', 855, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (857, '恐怖袭击事件有关法律法规', '51E05', 856, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (858, '涉外事件有关法律法规', '51E06', 857, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (859, '信息安全事件有关法律法规', '51E07', 858, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (860, '其他社会安全有关法律法规', '51E99', 859, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (861, '技术规范', '52000', 860, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (862, '自然灾害有关技术规范', '52A00', 861, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (863, '水旱灾害有关技术规范', '52A01', 862, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (864, '气象灾害有关技术规范', '52A02', 863, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (865, '地震灾害有关技术规范', '52AG3', 864, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (866, '地质灾害有关技术规范', '52A04', 865, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (867, '海洋灾害事件有关技术规范', '52A05', 866, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (868, '生物灾害事件有关技术规范', '52A06', 867, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (869, '森林草原火灾有关技术规范', '52A07', 868, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (870, '其他有关技术规范', '52A99', 869, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (871, '事故灾难方面技术规范', '52B00', 870, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (872, '工矿商贸事故有关技术规范', '52B01', 871, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (873, '火灾事故有关技术规范', '52B02', 872, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (874, '交通运输事故有关技术规范', '52B03', 873, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (875, '特种设备事故有关技术规范', '52B04', 874, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (876, '基础设施和公用设施事故有关技术规范', '52B05', 875, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (877, '环境污染和生态破坏事件有关技术规范', '52B06', 876, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (878, '农业机械事故有关技术规范', '52B07', 877, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (879, '核与辐射事故有关技术规范', '52B08', 878, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (880, '其他事故灾难有关技术规范', '52B99', 879, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (881, '公共卫生方面技术规范', '52C00', 880, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (882, '传染病疫情有关技术规范', '52C01', 881, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (883, '食品药品安全有关技术规范', '52C02', 882, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (884, '职业危害事件有关技术规范', '52C03', 883, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (885, '动物疫情有关技术规范', '52C04', 884, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (886, '群体不明原因疾病有关技术规范', '52C05', 885, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (887, '动物疫病防治相关技术规范', '52C06', 886, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (888, '其他公共卫生事件有关技术规范', '52C99', 887, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (889, '社会安全方面技术规范', '52D00', 888, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (890, '其他', '59000', 889, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (891, '应急预案', '60000', 890, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (892, '国家级应急预案', '61000', 891, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (893, '总体应急预案', '61A00', 892, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (894, '专项应急预案', '61B00', 893, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (895, '部门应急预案', '61C00', 894, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (896, '其它预案', '61D00', 895, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (897, '省级应急预案', '62000', 896, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (898, '总体应急预案', '62A00', 897, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (899, '专项应急预案', '62B00', 898, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (900, '部门应急预案', '62C00', 899, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (901, '重大活动应急预案', '62D00', 900, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (902, '其它预案', '62Y00', 901, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (903, '市级应急预案', '63000', 902, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (904, '总体应急预案', '63A00', 903, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (905, '专项预案', '63B00', 904, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (906, '部门应急预案', '63C00', 905, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (907, '重大活动应急预案', '63D00', 906, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (908, '其它预案', '63Y00', 907, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (909, '县级应急预案', '64000', 908, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (910, '总体应急预案', '64A00', 909, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (911, '专项应急预案', '64B00', 910, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (912, '部门应急预案', '64C00', 911, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (913, '重大活动应急预案', '64D00', 912, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (914, '其它预案', '64Y00', 913, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (915, '基层应急预案', '65000', 914, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (916, '企业级应急预案', '66000', 915, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (917, '综合应急预案', '66A00', 916, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (918, '专项应急预案', '66B00', 917, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (919, '现场处置预案', '66C00', 918, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (920, '其他企业级应急预案', '66Y00', 919, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (921, '军队应急预案', '67000', 920, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (922, '总部级应急预案', '67A00', 921, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (923, '总部级总体应急预案', '67A01', 922, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (924, '总部级专项应急预案', '67A02', 923, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (925, '总部级部门应急预案', '67A03', 924, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (926, '总部级重大活动应急预案', '67A04', 925, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (927, '总部级其他应急预案', '67A99', 926, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (928, '军区(军兵种)级应急预案', '67B00', 927, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (929, '军区(军兵种)级总体应急预案', '67B01', 928, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (930, '军区(军兵种)级专项应急预案', '67B02', 929, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (931, '军区(军兵种)级部门应急预案', '67B03', 930, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (932, '军区(军兵种)级重大活动应急预案', '67B04', 931, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (933, '军区(军兵种)级其他应急预案', '67B99', 932, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (934, '省军区(集团军)级应急预案', '67C00', 933, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (935, '省军区(集团军)级总体应急预案', '67C01', 934, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (936, '省军区(集团军)级专项应急预案', '67C02', 935, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (937, '省军区(集团军)级部门应急预案', '67C03', 936, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (938, '省军区(集团军)级重大活动应急预案', '67C04', 937, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (939, '省军区(集团军)级其他应急预案', '67C99', 938, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (940, '军分区(师旅)级应急预案', '67D00', 939, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (941, '军分区(师旅)级总体应急预案', '67D01', 940, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (942, '军分区(师旅)级专项应急预案', '67D02', 941, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (943, '军分区(师旅)级部门应急预案', '67D03', 942, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (944, '军分区(师旅)级重大活动应急预案', '67D04', 943, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (945, '军分区(师旅)级其他应急预案', '67D99', 944, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (946, '基层级应急预案', '67E00', 945, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (947, '基层级总体应急预案', '67E01', 946, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (948, '基层级专项应急预案', '67E02', 947, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (949, '基层级部门应急预案', '67E03', 948, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (950, '基层级重大活动应急预案', '67E04', 949, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (951, '基层级其他应急预案', '67E99', 950, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (952, '其他预案', '67Y00', 951, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (953, '其他应急预案', '69000', 952, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (954, '应急平台', '70000', 953, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (955, '国务院应急平台', '71000', 954, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (956, '地方应急平台', '72000', 955, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (957, '省级应急平台', '72A00', 956, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (958, '地市级应急平台', '72B00', 957, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (959, '市县级应急平台', '72C00', 958, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (960, '部门应急平台', '73000', 959, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (961, '国家级部门应急平台', '73A00', 960, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (962, '专项应急指挥系统', '73B00', 961, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (963, '省级部门应急平台', '73C00', 962, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (964, '地市级部门应急平台', '73D00', 963, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (965, '市县级部门应急平台', '73E00', 964, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (966, '其他', '73Y00', 965, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (967, '基层应急平台', '74000', 966, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (968, '企业应急平台', '75000', 967, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (969, '军队应急平台', '76000', 968, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (970, '总部级应急平台', '76A00', 969, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (971, '军区（军兵种）级应急平台', '76C00', 970, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (972, '省军区（集团军）级应急平台', '76D00', 971, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (973, '军分区（师旅）级应急平台', '76E00', 972, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (974, '其他军队应急平台', '76Y00', 973, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (975, '移动应急平台', '77000', 974, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (976, '大型移动平台', '77A00', 975, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (977, '中型移动平台', '77B00', 976, '2018-06-28 16:44:42', '2018-06-28 16:44:42', 'admin', NULL);
INSERT INTO `accidenttype` VALUES (978, '小型移动平台', '77C00', 977, '2018-06-28 16:44:42', '2018-07-27 10:56:28', 'admin', 'admin');

-- ----------------------------
-- Table structure for administrative
-- ----------------------------
DROP TABLE IF EXISTS `administrative`;
CREATE TABLE `administrative`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区编号',
  `code_level` int(20) NULL DEFAULT NULL COMMENT '区等级',
  `latitude` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中心纬度',
  `longitude` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中心经度',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区名称',
  `parent_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属市级编号',
  `parent_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所有父级编号？',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 478 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of administrative
-- ----------------------------
INSERT INTO `administrative` VALUES (1, '440000000000', 1, '', '', '广东省', '', NULL, '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (2, '445100000000', 2, '', '', '潮州市', '440000000000', '440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (3, '445103000000', 3, '', '', '潮安区', '445100000000', '445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (4, '445103100000', 4, '', '', '古巷镇', '445103000000', '445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (5, '445103100001', 5, '', '', '古巷社区居委会', '445103100000', '445103100000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (6, '445103100200', 5, '', '', '枫洋一村委会', '445103100000', '445103100000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (7, '445103100201', 5, '', '', '枫洋二村委会', '445103100000', '445103100000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (8, '445103100202', 5, '', '', '枫洋三村委会', '445103100000', '445103100000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (9, '445103100203', 5, '', '', '枫洋四村委会', '445103100000', '445103100000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (10, '445103100204', 5, '', '', '崎头村委会', '445103100000', '445103100000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (11, '445103100205', 5, '', '', '孚中村委会', '445103100000', '445103100000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (12, '445103100206', 5, '', '', '锡岗村委会', '445103100000', '445103100000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (13, '445103100207', 5, '', '', '福庆村委会', '445103100000', '445103100000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (14, '445103100208', 5, '', '', '永安寨村委会', '445103100000', '445103100000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (15, '445103100209', 5, '', '', '古巷一村委会', '445103100000', '445103100000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (16, '445103100210', 5, '', '', '古巷二村委会', '445103100000', '445103100000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (17, '445103100211', 5, '', '', '古巷三村委会', '445103100000', '445103100000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (18, '445103100212', 5, '', '', '古巷四村委会', '445103100000', '445103100000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (19, '445103100213', 5, '', '', '古巷五村委会', '445103100000', '445103100000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (20, '445103100214', 5, '', '', '横溪村委会', '445103100000', '445103100000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (21, '445103100215', 5, '', '', '东岗村委会', '445103100000', '445103100000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (22, '445103100216', 5, '', '', '水美李村委会', '445103100000', '445103100000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (23, '445103100217', 5, '', '', '网地村委会', '445103100000', '445103100000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (24, '445103101000', 4, '', '', '登塘镇', '445103000000', '445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (25, '445103101001', 5, '', '', '登塘社区居委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (26, '445103101002', 5, '', '', '田东社区居委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (27, '445103101200', 5, '', '', '登塘村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (28, '445103101201', 5, '', '', '笔埔村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (29, '445103101202', 5, '', '', '白云村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (30, '445103101203', 5, '', '', '扬美村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (31, '445103101204', 5, '', '', '林一村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (32, '445103101205', 5, '', '', '林二村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (33, '445103101206', 5, '', '', '溪乾寮村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (34, '445103101207', 5, '', '', '郑岗村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (35, '445103101208', 5, '', '', '下林村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (36, '445103101209', 5, '', '', '凤岗埔村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (37, '445103101210', 5, '', '', '三新乡村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (38, '445103101211', 5, '', '', '东寮村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (39, '445103101212', 5, '', '', '平林村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (40, '445103101213', 5, '', '', '尧田村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (41, '445103101214', 5, '', '', '白水村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (42, '445103101215', 5, '', '', '关竹村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (43, '445103101216', 5, '', '', '枫树员村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (44, '445103101217', 5, '', '', '白茫洲村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (45, '445103101218', 5, '', '', '新西坑村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (46, '445103101219', 5, '', '', '横洋村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (47, '445103101220', 5, '', '', '田东村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (48, '445103101221', 5, '', '', '黄潭村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (49, '445103101222', 5, '', '', '伍全村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (50, '445103101223', 5, '', '', '栖凤村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (51, '445103101224', 5, '', '', '小葫芦村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (52, '445103101225', 5, '', '', '大葫芦村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (53, '445103101226', 5, '', '', '世田村委会', '445103101000', '445103101000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (54, '445103103000', 4, '', '', '凤塘镇', '445103000000', '445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (55, '445103103001', 5, '', '', '凤塘社区居委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (56, '445103103200', 5, '', '', '东门村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (57, '445103103201', 5, '', '', '南门村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (58, '445103103202', 5, '', '', '大埕村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (59, '445103103203', 5, '', '', '浮岗村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (60, '445103103204', 5, '', '', '凤岗村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (61, '445103103205', 5, '', '', '南陇村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (62, '445103103206', 5, '', '', '丘厝村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (63, '445103103207', 5, '', '', '泮洋村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (64, '445103103208', 5, '', '', '冯厝村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (65, '445103103209', 5, '', '', '吉林村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (66, '445103103210', 5, '', '', '双岗村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (67, '445103103211', 5, '', '', '湖美村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (68, '445103103212', 5, '', '', '磷畔村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (69, '445103103213', 5, '', '', '东龙村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (70, '445103103214', 5, '', '', '沟头村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (71, '445103103215', 5, '', '', '英凤村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (72, '445103103216', 5, '', '', '凤水村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (73, '445103103217', 5, '', '', '凤里村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (74, '445103103218', 5, '', '', '玉窖村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (75, '445103103219', 5, '', '', '新乡村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (76, '445103103220', 5, '', '', '义桥村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (77, '445103103221', 5, '', '', '淇园村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (78, '445103103222', 5, '', '', '林兜村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (79, '445103103223', 5, '', '', '书图村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (80, '445103103224', 5, '', '', '洪巷村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (81, '445103103225', 5, '', '', '东和村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (82, '445103103226', 5, '', '', '盛户村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (83, '445103103227', 5, '', '', '西和村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (84, '445103103228', 5, '', '', '新和村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (85, '445103103229', 5, '', '', '和安村委会', '445103103000', '445103103000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (86, '445103104000', 4, '', '', '浮洋镇', '445103000000', '445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (87, '445103104001', 5, '', '', '永大社区居委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (88, '445103104002', 5, '', '', '和平社区居委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (89, '445103104003', 5, '', '', '胜利社区居委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (90, '445103104004', 5, '', '', '中兴社区居委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (91, '445103104200', 5, '', '', '徐陇村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (92, '445103104201', 5, '', '', '陇美村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (93, '445103104202', 5, '', '', '仙庭村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (94, '445103104203', 5, '', '', '东陇村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (95, '445103104204', 5, '', '', '东边村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (96, '445103104205', 5, '', '', '庵后村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (97, '445103104206', 5, '', '', '厦里美村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (98, '445103104207', 5, '', '', '洪巷村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (99, '445103104208', 5, '', '', '斗文村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (100, '445103104209', 5, '', '', '井里村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (101, '445103104210', 5, '', '', '桃李陇村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (102, '445103104211', 5, '', '', '乐桥村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (103, '445103104212', 5, '', '', '深洋村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (104, '445103104213', 5, '', '', '花宫村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (105, '445103104214', 5, '', '', '刘厝村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (106, '445103104215', 5, '', '', '潘吴村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (107, '445103104216', 5, '', '', '颜厝村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (108, '445103104217', 5, '', '', '大吴村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (109, '445103104218', 5, '', '', '新安村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (110, '445103104219', 5, '', '', '下新安村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (111, '445103104220', 5, '', '', '凤仪村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (112, '445103104221', 5, '', '', '潘刘村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (113, '445103104222', 5, '', '', '高义村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (114, '445103104223', 5, '', '', '东巷村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (115, '445103104224', 5, '', '', '林泉村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (116, '445103104225', 5, '', '', '草安村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (117, '445103104226', 5, '', '', '福洞村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (118, '445103104227', 5, '', '', '乌洋村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (119, '445103104228', 5, '', '', '西郊村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (120, '445103104229', 5, '', '', '桥湖村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (121, '445103104230', 5, '', '', '新丰村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (122, '445103104231', 5, '', '', '三胜村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (123, '445103104232', 5, '', '', '韦骆村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (124, '445103104233', 5, '', '', '胜联村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (125, '445103104234', 5, '', '', '木井村委会', '445103104000', '445103104000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (126, '445103105000', 4, '', '', '龙湖镇', '445103000000', '445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (127, '445103105001', 5, '', '', '龙湖社区居委会', '445103105000', '445103105000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (128, '445103105200', 5, '', '', '市头村委会', '445103105000', '445103105000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (129, '445103105201', 5, '', '', '市尾村委会', '445103105000', '445103105000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (130, '445103105202', 5, '', '', '三英村委会', '445103105000', '445103105000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (131, '445103105203', 5, '', '', '下阁村委会', '445103105000', '445103105000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (132, '445103105204', 5, '', '', '阁一村委会', '445103105000', '445103105000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (133, '445103105205', 5, '', '', '阁二村委会', '445103105000', '445103105000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (134, '445103105206', 5, '', '', '塘东村委会', '445103105000', '445103105000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (135, '445103105207', 5, '', '', '东升村委会', '445103105000', '445103105000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (136, '445103105208', 5, '', '', '湖边村委会', '445103105000', '445103105000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (137, '445103105209', 5, '', '', '后郭村委会', '445103105000', '445103105000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (138, '445103105210', 5, '', '', '银湖村委会', '445103105000', '445103105000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (139, '445103105211', 5, '', '', '鹳一村委会', '445103105000', '445103105000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (140, '445103105212', 5, '', '', '鹳二村委会', '445103105000', '445103105000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (141, '445103105213', 5, '', '', '鹳三村委会', '445103105000', '445103105000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (142, '445103105214', 5, '', '', '鹳四村委会', '445103105000', '445103105000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (143, '445103106000', 4, '', '', '金石镇', '445103000000', '445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (144, '445103106001', 5, '', '', '宫前社区居委会', '445103106000', '445103106000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (145, '445103106200', 5, '', '', '远光村委会', '445103106000', '445103106000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (146, '445103106201', 5, '', '', '古楼一村委会', '445103106000', '445103106000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (147, '445103106202', 5, '', '', '古楼二村委会', '445103106000', '445103106000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (148, '445103106203', 5, '', '', '湖美村委会', '445103106000', '445103106000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (149, '445103106204', 5, '', '', '潘厝村委会', '445103106000', '445103106000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (150, '445103106205', 5, '', '', '龙下村委会', '445103106000', '445103106000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (151, '445103106206', 5, '', '', '下陇村委会', '445103106000', '445103106000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (152, '445103106207', 5, '', '', '田头村委会', '445103106000', '445103106000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (153, '445103106208', 5, '', '', '厂头村委会', '445103106000', '445103106000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (154, '445103106209', 5, '', '', '辜厝村委会', '445103106000', '445103106000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (155, '445103106210', 5, '', '', '陈厝巷村委会', '445103106000', '445103106000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (156, '445103106211', 5, '', '', '黄厝巷村委会', '445103106000', '445103106000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (157, '445103106212', 5, '', '', '张厝巷村委会', '445103106000', '445103106000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (158, '445103106213', 5, '', '', '上官路村委会', '445103106000', '445103106000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (159, '445103106214', 5, '', '', '廖厝村委会', '445103106000', '445103106000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (160, '445103106215', 5, '', '', '赖厝村委会', '445103106000', '445103106000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (161, '445103106216', 5, '', '', '翁厝村委会', '445103106000', '445103106000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (162, '445103106217', 5, '', '', '仙都一村委会', '445103106000', '445103106000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (163, '445103106218', 5, '', '', '仙都二村委会', '445103106000', '445103106000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (164, '445103106219', 5, '', '', '仙都三村委会', '445103106000', '445103106000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (165, '445103106220', 5, '', '', '塔下村委会', '445103106000', '445103106000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (166, '445103107000', 4, '', '', '沙溪镇', '445103000000', '445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (167, '445103107001', 5, '', '', '沙溪社区居委会', '445103107000', '445103107000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (168, '445103107200', 5, '', '', '前陇一村委会', '445103107000', '445103107000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (169, '445103107201', 5, '', '', '前陇二村委会', '445103107000', '445103107000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (170, '445103107202', 5, '', '', '上西林村委会', '445103107000', '445103107000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (171, '445103107203', 5, '', '', '下西林村委会', '445103107000', '445103107000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (172, '445103107204', 5, '', '', '内池村委会', '445103107000', '445103107000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (173, '445103107205', 5, '', '', '生聚村委会', '445103107000', '445103107000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (174, '445103107206', 5, '', '', '沙溪一村委会', '445103107000', '445103107000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (175, '445103107207', 5, '', '', '沙溪二村委会', '445103107000', '445103107000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (176, '445103107208', 5, '', '', '高厦一村委会', '445103107000', '445103107000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (177, '445103107209', 5, '', '', '高厦二村委会', '445103107000', '445103107000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (178, '445103107210', 5, '', '', '高楼村委会', '445103107000', '445103107000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (179, '445103107211', 5, '', '', '五嘉陇村委会', '445103107000', '445103107000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (180, '445103107212', 5, '', '', '贾里村委会', '445103107000', '445103107000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (181, '445103107213', 5, '', '', '刘畔村委会', '445103107000', '445103107000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (182, '445103107214', 5, '', '', '仁里村委会', '445103107000', '445103107000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (183, '445103107215', 5, '', '', '程畔村委会', '445103107000', '445103107000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (184, '445103107216', 5, '', '', '玉湖村委会', '445103107000', '445103107000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (185, '445103108000', 4, '', '', '彩塘镇', '445103000000', '445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (186, '445103108001', 5, '', '', '彩塘社区居委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (187, '445103108200', 5, '', '', '彩塘村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (188, '445103108201', 5, '', '', '院前村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (189, '445103108202', 5, '', '', '岭头村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (190, '445103108203', 5, '', '', '水美村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (191, '445103108204', 5, '', '', '新联村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (192, '445103108205', 5, '', '', '东寨村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (193, '445103108206', 5, '', '', '仙乐一村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (194, '445103108207', 5, '', '', '仙安村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (195, '445103108208', 5, '', '', '仙乐二村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (196, '445103108209', 5, '', '', '龙吉美村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (197, '445103108210', 5, '', '', '和平村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (198, '445103108211', 5, '', '', '红旗村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (199, '445103108212', 5, '', '', '宏安一村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (200, '445103108213', 5, '', '', '宏安二村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (201, '445103108214', 5, '', '', '宏安三村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (202, '445103108215', 5, '', '', '宏安四村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (203, '445103108216', 5, '', '', '宏安五村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (204, '445103108217', 5, '', '', '宏安六村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (205, '445103108218', 5, '', '', '金东村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (206, '445103108219', 5, '', '', '南方村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (207, '445103108220', 5, '', '', '金一村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (208, '445103108221', 5, '', '', '金二村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (209, '445103108222', 5, '', '', '金三村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (210, '445103108223', 5, '', '', '金四村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (211, '445103108224', 5, '', '', '东里村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (212, '445103108225', 5, '', '', '华桥村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (213, '445103108226', 5, '', '', '华东村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (214, '445103108227', 5, '', '', '华美一村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (215, '445103108228', 5, '', '', '华美二村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (216, '445103108229', 5, '', '', '骊塘一村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (217, '445103108230', 5, '', '', '骊塘二村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (218, '445103108231', 5, '', '', '骊塘三村委会', '445103108000', '445103108000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (219, '445103109000', 4, '', '', '东凤镇', '445103000000', '445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (220, '445103109001', 5, '', '', '东凤社区居委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (221, '445103109200', 5, '', '', '东凤一村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (222, '445103109201', 5, '', '', '东凤二村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (223, '445103109202', 5, '', '', '东凤三村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (224, '445103109203', 5, '', '', '东凤四村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (225, '445103109204', 5, '', '', '肖洪村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (226, '445103109205', 5, '', '', '横江村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (227, '445103109206', 5, '', '', '大巷村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (228, '445103109207', 5, '', '', '庄西陇村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (229, '445103109208', 5, '', '', '新光村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (230, '445103109209', 5, '', '', '天宁村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (231, '445103109210', 5, '', '', '竹修村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (232, '445103109211', 5, '', '', '大寮村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (233, '445103109212', 5, '', '', '礼阳李村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (234, '445103109213', 5, '', '', '礼阳陇尾村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (235, '445103109214', 5, '', '', '沟美村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (236, '445103109215', 5, '', '', '礼阳郑村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (237, '445103109216', 5, '', '', '诗阳村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (238, '445103109217', 5, '', '', '文路村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (239, '445103109218', 5, '', '', '文路张村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (240, '445103109219', 5, '', '', '博士村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (241, '445103109220', 5, '', '', '昆江三村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (242, '445103109221', 5, '', '', '昆江五村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (243, '445103109222', 5, '', '', '下张村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (244, '445103109223', 5, '', '', '沟边村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (245, '445103109224', 5, '', '', '儒士村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (246, '445103109225', 5, '', '', '黄厝尾村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (247, '445103109226', 5, '', '', '龙甲村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (248, '445103109227', 5, '', '', '堤边村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (249, '445103109228', 5, '', '', '王厝陇村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (250, '445103109229', 5, '', '', '陇仔村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (251, '445103109230', 5, '', '', '内畔村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (252, '445103109231', 5, '', '', '洋东村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (253, '445103109232', 5, '', '', '下园村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (254, '445103109233', 5, '', '', '仙桥村委会', '445103109000', '445103109000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (255, '445103110000', 4, '', '', '庵埠镇', '445103000000', '445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (256, '445103110001', 5, '', '', '中山社区居委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (257, '445103110002', 5, '', '', '中兴社区居委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (258, '445103110003', 5, '', '', '仙乔社区居委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (259, '445103110004', 5, '', '', '复兴社区居委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (260, '445103110005', 5, '', '', '龙桥社区居委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (261, '445103110200', 5, '', '', '霞露村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (262, '445103110201', 5, '', '', '大桥村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (263, '445103110202', 5, '', '', '砚前村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (264, '445103110203', 5, '', '', '茂龙村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (265, '445103110204', 5, '', '', '大干村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (266, '445103110205', 5, '', '', '梅龙村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (267, '445103110206', 5, '', '', '亭厦村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (268, '445103110207', 5, '', '', '美乡村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (269, '445103110208', 5, '', '', '林厝村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (270, '445103110209', 5, '', '', '仙溪村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (271, '445103110210', 5, '', '', '乔林村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (272, '445103110211', 5, '', '', '官路村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (273, '445103110212', 5, '', '', '梅溪村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (274, '445103110213', 5, '', '', '厦吴村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (275, '445103110214', 5, '', '', '外文村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (276, '445103110215', 5, '', '', '凤岐村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (277, '445103110216', 5, '', '', '开濠村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (278, '445103110217', 5, '', '', '文里村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (279, '445103110218', 5, '', '', '刘陇村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (280, '445103110219', 5, '', '', '溜龙村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (281, '445103110220', 5, '', '', '莫陇村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (282, '445103110221', 5, '', '', '薛陇一村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (283, '445103110222', 5, '', '', '薛陇二村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (284, '445103110223', 5, '', '', '潘陇村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (285, '445103110224', 5, '', '', '郭陇一村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (286, '445103110225', 5, '', '', '郭陇二村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (287, '445103110226', 5, '', '', '郭陇三村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (288, '445103110227', 5, '', '', '郭陇四村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (289, '445103110228', 5, '', '', '龙坑村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (290, '445103110229', 5, '', '', '庄陇村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (291, '445103110230', 5, '', '', '宝陇村委会', '445103110000', '445103110000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (292, '445103114000', 4, '', '', '江东镇', '445103000000', '445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (293, '445103114001', 5, '', '', '江东北街社区居委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (294, '445103114200', 5, '', '', '上水头村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (295, '445103114201', 5, '', '', '下水头村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (296, '445103114202', 5, '', '', '柚杭村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (297, '445103114203', 5, '', '', '柚园村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (298, '445103114204', 5, '', '', '元巷村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (299, '445103114205', 5, '', '', '亭头村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (300, '445103114206', 5, '', '', '村头村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (301, '445103114207', 5, '', '', '洲东村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (302, '445103114208', 5, '', '', '洋光村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (303, '445103114209', 5, '', '', '仙坪田村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (304, '445103114210', 5, '', '', '东光村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (305, '445103114211', 5, '', '', '三吴村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (306, '445103114212', 5, '', '', '独树村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (307, '445103114213', 5, '', '', '上庄村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (308, '445103114214', 5, '', '', '中庄村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (309, '445103114215', 5, '', '', '下庄村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (310, '445103114216', 5, '', '', '井美村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (311, '445103114217', 5, '', '', '圆山村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (312, '445103114218', 5, '', '', '龙口村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (313, '445103114219', 5, '', '', '红砂村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (314, '445103114220', 5, '', '', '仙洲村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (315, '445103114221', 5, '', '', '渡头村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (316, '445103114222', 5, '', '', '樟厝洲村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (317, '445103114223', 5, '', '', '谢渡村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (318, '445103114224', 5, '', '', '西前溪村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (319, '445103114225', 5, '', '', '东前溪村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (320, '445103114226', 5, '', '', '蓬洞村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (321, '445103114227', 5, '', '', '佘厝洲村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (322, '445103114228', 5, '', '', '下湖村委会', '445103114000', '445103114000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (323, '445103115000', 4, '', '', '归湖镇', '445103000000', '445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (324, '445103115001', 5, '', '', '归湖社区居委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (325, '445103115002', 5, '', '', '凤南社区居委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (326, '445103115200', 5, '', '', '仙洋村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (327, '445103115201', 5, '', '', '凤东村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (328, '445103115202', 5, '', '', '梨下村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (329, '445103115203', 5, '', '', '田东村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (330, '445103115204', 5, '', '', '石陂村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (331, '445103115205', 5, '', '', '神前村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (332, '445103115206', 5, '', '', '狮峰村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (333, '445103115207', 5, '', '', '塘埔村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (334, '445103115208', 5, '', '', '龙溪村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (335, '445103115209', 5, '', '', '金光村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (336, '445103115210', 5, '', '', '金丰村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (337, '445103115211', 5, '', '', '溪口村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (338, '445103115212', 5, '', '', '潭头村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (339, '445103115213', 5, '', '', '铺头村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (340, '445103115214', 5, '', '', '明潭村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (341, '445103115215', 5, '', '', '砚田村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (342, '445103115216', 5, '', '', '新沟村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (343, '445103115217', 5, '', '', '溪美村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (344, '445103115218', 5, '', '', '克安村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (345, '445103115219', 5, '', '', '西林村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (346, '445103115220', 5, '', '', '绿竹村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (347, '445103115221', 5, '', '', '草塘村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (348, '445103115222', 5, '', '', '金舟村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (349, '445103115223', 5, '', '', '碗窑村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (350, '445103115224', 5, '', '', '山梨村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (351, '445103115225', 5, '', '', '大坪村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (352, '445103115226', 5, '', '', '白藤坑村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (353, '445103115227', 5, '', '', '东山村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (354, '445103115228', 5, '', '', '高原村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (355, '445103115229', 5, '', '', '东明村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (356, '445103115230', 5, '', '', '高升村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (357, '445103115231', 5, '', '', '高峰村委会', '445103115000', '445103115000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (358, '445103116000', 4, '', '', '文祠镇', '445103000000', '445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (359, '445103116001', 5, '', '', '文祠社区居委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (360, '445103116200', 5, '', '', '石坑村委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (361, '445103116201', 5, '', '', '东社村委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (362, '445103116202', 5, '', '', '竹园村委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (363, '445103116203', 5, '', '', '李工坑村委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (364, '445103116204', 5, '', '', '如意溪村委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (365, '445103116205', 5, '', '', '坪坑村委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (366, '445103116206', 5, '', '', '西社村委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (367, '445103116207', 5, '', '', '石柱坑村委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (368, '445103116208', 5, '', '', '小行村委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (369, '445103116209', 5, '', '', '赤内村委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (370, '445103116210', 5, '', '', '下赤村委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (371, '445103116211', 5, '', '', '上赤村委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (372, '445103116212', 5, '', '', '赤水村委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (373, '445103116213', 5, '', '', '坑美村委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (374, '445103116214', 5, '', '', '南社村委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (375, '445103116215', 5, '', '', '河塘村委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (376, '445103116216', 5, '', '', '银潭村委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (377, '445103116217', 5, '', '', '新北村委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (378, '445103116218', 5, '', '', '楠木翁村委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (379, '445103116219', 5, '', '', '楠木村委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (380, '445103116220', 5, '', '', '望岭村委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (381, '445103116221', 5, '', '', '中社村委会', '445103116000', '445103116000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (382, '445103117000', 4, '', '', '凤凰镇', '445103000000', '445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (383, '445103117001', 5, '', '', '凤凰社区居委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (384, '445103117002', 5, '', '', '大山社区居委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (385, '445103117200', 5, '', '', '棋盘村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (386, '445103117201', 5, '', '', '东尝村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (387, '445103117202', 5, '', '', '西春村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (388, '445103117203', 5, '', '', '虎头村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (389, '445103117204', 5, '', '', '康美村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (390, '445103117205', 5, '', '', '欧坑村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (391, '445103117206', 5, '', '', '石古坪村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (392, '445103117207', 5, '', '', '东兴村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (393, '445103117208', 5, '', '', '福北村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (394, '445103117209', 5, '', '', '福南村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (395, '445103117210', 5, '', '', '下埔村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (396, '445103117211', 5, '', '', '南坑村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (397, '445103117212', 5, '', '', '凤光村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (398, '445103117213', 5, '', '', '凤北村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (399, '445103117214', 5, '', '', '二垭村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (400, '445103117215', 5, '', '', '舂堀村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (401, '445103117216', 5, '', '', '凤溪村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (402, '445103117217', 5, '', '', '凤西村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (403, '445103117218', 5, '', '', '乌岽村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (404, '445103117219', 5, '', '', '新东村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (405, '445103117220', 5, '', '', '叫水坑村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (406, '445103117221', 5, '', '', '上春村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (407, '445103117222', 5, '', '', '中段村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (408, '445103117223', 5, '', '', '凤新村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (409, '445103117224', 5, '', '', '三平礤村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (410, '445103117225', 5, '', '', '凤湖村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (411, '445103117226', 5, '', '', '南溪村委会', '445103117000', '445103117000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (412, '445103120000', 4, '', '', '赤凤镇', '445103000000', '445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (413, '445103120001', 5, '', '', '赤凤社区居委会', '445103120000', '445103120000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (414, '445103120200', 5, '', '', '浮石村委会', '445103120000', '445103120000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (415, '445103120201', 5, '', '', '黄山坑村委会', '445103120000', '445103120000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (416, '445103120202', 5, '', '', '杉坪村委会', '445103120000', '445103120000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (417, '445103120203', 5, '', '', '松水村委会', '445103120000', '445103120000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (418, '445103120204', 5, '', '', '小松村委会', '445103120000', '445103120000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (419, '445103120205', 5, '', '', '大庵村委会', '445103120000', '445103120000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (420, '445103120206', 5, '', '', '峙溪村委会', '445103120000', '445103120000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (421, '445103120207', 5, '', '', '溪东村委会', '445103120000', '445103120000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (422, '445103120208', 5, '', '', '安溪村委会', '445103120000', '445103120000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (423, '445103120209', 5, '', '', '塘北村委会', '445103120000', '445103120000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (424, '445103120210', 5, '', '', '水口村委会', '445103120000', '445103120000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (425, '445103120211', 5, '', '', '白叶村委会', '445103120000', '445103120000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (426, '445103120212', 5, '', '', '白莲村委会', '445103120000', '445103120000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (427, '445103120213', 5, '', '', '葵山村委会', '445103120000', '445103120000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (428, '445103120214', 5, '', '', '田湖村委会', '445103120000', '445103120000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (429, '445103120215', 5, '', '', '四望坪村委会', '445103120000', '445103120000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (430, '445103120216', 5, '', '', '韩西村委会', '445103120000', '445103120000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (431, '445103121000', 4, '', '', '枫溪镇', '445103000000', '445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (432, '445103121001', 5, '', '', '怀德社区居委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (433, '445103121002', 5, '', '', '石桥社区居委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (434, '445103121003', 5, '', '', '洲园社区居委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (435, '445103121004', 5, '', '', '长美社区居委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (436, '445103121005', 5, '', '', '福安社区居委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (437, '445103121006', 5, '', '', '南苑居委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (438, '445103121200', 5, '', '', '枫溪一村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (439, '445103121201', 5, '', '', '枫溪二村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (440, '445103121202', 5, '', '', '长美一村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (441, '445103121203', 5, '', '', '长美二村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (442, '445103121204', 5, '', '', '湖厦村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (443, '445103121205', 5, '', '', '藏龙村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (444, '445103121206', 5, '', '', '詹厝村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (445, '445103121207', 5, '', '', '李厝村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (446, '445103121208', 5, '', '', '槐山岗村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (447, '445103121209', 5, '', '', '山边村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (448, '445103121210', 5, '', '', '白塔村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (449, '445103121211', 5, '', '', '池湖村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (450, '445103121212', 5, '', '', '蔡陇村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (451, '445103121213', 5, '', '', '云步村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (452, '445103121214', 5, '', '', '上东埔村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (453, '445103121215', 5, '', '', '堤头村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (454, '445103121216', 5, '', '', '前进村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (455, '445103121217', 5, '', '', '古板头村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (456, '445103121218', 5, '', '', '全福村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (457, '445103121219', 5, '', '', '英塘村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (458, '445103121220', 5, '', '', '洋头村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (459, '445103121221', 5, '', '', '高厦村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (460, '445103121222', 5, '', '', '西边村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (461, '445103121223', 5, '', '', '下东埔村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (462, '445103121224', 5, '', '', '田头村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (463, '445103121225', 5, '', '', '田头何村委会', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (464, '445103121400', 5, '', '', '德州工业园社区', '445103121000', '445103121000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (465, '445103400000', 4, '', '', '万峰林场', '445103000000', '445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (466, '445103400001', 5, '', '', '林场社区居委会', '445103400000', '445103400000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (467, '445103400200', 5, '', '', '铁炉坪村委会', '445103400000', '445103400000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (468, '445103400201', 5, '', '', '望京坪村委会', '445103400000', '445103400000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (469, '445103400202', 5, '', '', '曾厝村委会', '445103400000', '445103400000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (470, '445103400203', 5, '', '', '冬瓜坪村委会', '445103400000', '445103400000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (471, '445103400204', 5, '', '', '径仔村委会', '445103400000', '445103400000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (472, '445103404000', 4, '', '', '大坑苗圃场', '445103000000', '445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (473, '445103404498', 5, '', '', '大坑苗圃场虚拟社区', '445103404000', '445103404000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (474, '445103405000', 4, '', '', '东山湖农场', '445103000000', '445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (475, '445103405598', 5, '', '', '东山湖农场虚拟社区', '445103405000', '445103405000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (476, '445103450000', 4, '', '', '庵埠经济开发试验区', '445103000000', '445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);
INSERT INTO `administrative` VALUES (477, '445103450598', 5, '', '', '庵埠经济开发试验区虚拟社区', '445103450000', '445103450000;445103000000;445100000000;440000000000', '2018-06-04 16:33:51', NULL, '2018-06-04 16:33:51', NULL);

-- ----------------------------
-- Table structure for dev_model_param_relation
-- ----------------------------
DROP TABLE IF EXISTS `dev_model_param_relation`;
CREATE TABLE `dev_model_param_relation`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `dm_id` int(20) NOT NULL COMMENT 'devModel的id',
  `dmp_id` int(20) NOT NULL COMMENT 'devModelParam的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dev_model_param_relation
-- ----------------------------
INSERT INTO `dev_model_param_relation` VALUES (1, 33, 19);
INSERT INTO `dev_model_param_relation` VALUES (2, 39, 19);
INSERT INTO `dev_model_param_relation` VALUES (3, 43, 19);
INSERT INTO `dev_model_param_relation` VALUES (4, 33, 20);
INSERT INTO `dev_model_param_relation` VALUES (6, 33, 21);
INSERT INTO `dev_model_param_relation` VALUES (8, 33, 22);
INSERT INTO `dev_model_param_relation` VALUES (9, 33, 23);
INSERT INTO `dev_model_param_relation` VALUES (10, 33, 24);
INSERT INTO `dev_model_param_relation` VALUES (17, 44, 25);
INSERT INTO `dev_model_param_relation` VALUES (18, 44, 21);
INSERT INTO `dev_model_param_relation` VALUES (19, 44, 20);

-- ----------------------------
-- Table structure for device_log
-- ----------------------------
DROP TABLE IF EXISTS `device_log`;
CREATE TABLE `device_log`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '日志产生时间',
  `deviceDsn` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '设备序列号',
  `deviceName` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '设备序名称',
  `preStatus` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '原始状态',
  `status` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '设备状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of device_log
-- ----------------------------
INSERT INTO `device_log` VALUES (1, '2018-03-02 11:02:16', '15156156', '1111', '1', '11');
INSERT INTO `device_log` VALUES (2, '2018-03-08 09:19:13', 'TC81A01FFF0004DB4F', NULL, '', '00000000');
INSERT INTO `device_log` VALUES (3, '2018-03-13 13:57:21', '0016A21300FFFFFFFF', NULL, '', '00000000');
INSERT INTO `device_log` VALUES (4, '2018-03-13 14:09:04', '0016A21300FFFFFFFF', 'FFFF', '00000001', '00000001');
INSERT INTO `device_log` VALUES (5, '2018-03-16 17:36:17', '0016A21300FFFFFFFF', 'FFFF', '00000001', '00000011');
INSERT INTO `device_log` VALUES (6, '2018-03-16 17:40:40', '0016A21300FFFFFFFF', 'FFFF', '00000011', '00000011');
INSERT INTO `device_log` VALUES (7, '2018-04-26 11:33:23', '0016A21300FFFFFFFF', 'FFFF', '00000001', '00000011');
INSERT INTO `device_log` VALUES (8, '2018-05-11 17:48:56', 'TC81A01FFF0004DB53', NULL, '', '00000000');
INSERT INTO `device_log` VALUES (9, '2018-05-12 10:53:07', 'TC81A01FFF0004DB53', 'DB53', '00000001', '00000001');
INSERT INTO `device_log` VALUES (10, '2018-05-12 10:56:03', 'TC81A01FFF0004DB53', 'DB53', '00000001', '00000001');
INSERT INTO `device_log` VALUES (11, '2018-05-12 10:57:33', 'TC81A01FFF0004DB53', 'DB53', '00000001', '00000001');
INSERT INTO `device_log` VALUES (12, '2018-05-12 11:12:18', 'TC81A01FFF0004DB53', 'DB53', '00000011', '00000001');
INSERT INTO `device_log` VALUES (13, '2018-05-12 11:12:18', 'TC81A01FFF0004DB53', 'DB53', '00000001', '00000001');
INSERT INTO `device_log` VALUES (14, '2018-05-15 13:50:14', 'TC81A01FFF0004DB53', 'DB53', '00000001', '00000001');
INSERT INTO `device_log` VALUES (15, '2018-05-15 15:37:14', 'TC81A01FFF0004DB3E', NULL, '', '00000000');
INSERT INTO `device_log` VALUES (16, '2018-05-18 19:19:28', 'TC81A01FFF0004DB49', NULL, '', '00000000');
INSERT INTO `device_log` VALUES (17, '2018-05-22 10:55:52', 'TC81A01FFF0004DB53', NULL, '', '00000000');
INSERT INTO `device_log` VALUES (18, '2018-05-22 11:30:48', 'TC81A01FFF0004DB53', NULL, '', '00000000');
INSERT INTO `device_log` VALUES (19, '2018-05-22 11:34:40', 'TC81A01FFF0004DB53', 'DB53', '00000001', '00000001');
INSERT INTO `device_log` VALUES (20, '2018-05-22 11:43:28', 'TC81A01FFF0004DB53', 'DB53', '00000001', '00000001');
INSERT INTO `device_log` VALUES (21, '2018-05-22 11:46:18', 'TC81A01FFF0004DB53', 'DB53', '00000001', '00000001');
INSERT INTO `device_log` VALUES (22, '2018-05-22 11:57:27', 'TC81A01FFF0004DB53', 'DB53', '00000001', '00000011');
INSERT INTO `device_log` VALUES (23, '2018-05-22 11:58:27', 'TC81A01FFF0004DB53', 'DB53', '00000011', '00000001');
INSERT INTO `device_log` VALUES (24, '2018-05-22 11:59:17', 'TC81A01FFF0004DB53', 'DB53', '00000001', '00000011');

-- ----------------------------
-- Table structure for device_param_val
-- ----------------------------
DROP TABLE IF EXISTS `device_param_val`;
CREATE TABLE `device_param_val`  (
  `id` int(20) NOT NULL,
  `device_info_id` int(20) NULL DEFAULT NULL,
  `param_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `param_variable` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `val` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `param_form_check` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表单校验字符串',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of device_param_val
-- ----------------------------
INSERT INTO `device_param_val` VALUES (12, 199, '音量', 'Volume', '12', 'oneToHundred');
INSERT INTO `device_param_val` VALUES (13, 199, 'Mask', 'Mask', '192.168.3.222', 'IP');
INSERT INTO `device_param_val` VALUES (14, 199, 'Gate', 'Gate', '192.168.3.222', 'IP');
INSERT INTO `device_param_val` VALUES (15, 199, '本地IP', 'Ip', '192.168.3.66', 'IP');
INSERT INTO `device_param_val` VALUES (16, 199, '服务器IP', 'SerIp', '192.168.3.66', 'IP');
INSERT INTO `device_param_val` VALUES (17, 200, '音量', 'Volume', NULL, 'oneToHundred');
INSERT INTO `device_param_val` VALUES (18, 201, '音量', 'Volume', '1', 'oneToHundred');
INSERT INTO `device_param_val` VALUES (19, 201, 'Mask', 'Mask', '', 'IP');
INSERT INTO `device_param_val` VALUES (20, 201, 'Gate', 'Gate', '', 'IP');
INSERT INTO `device_param_val` VALUES (21, 201, '本地IP', 'Ip', '', 'IP');
INSERT INTO `device_param_val` VALUES (22, 201, '服务器IP', 'SerIp', '', 'IP');
INSERT INTO `device_param_val` VALUES (23, 202, '音量', 'Volume', NULL, 'oneToHundred');
INSERT INTO `device_param_val` VALUES (24, 202, 'Mask', 'Mask', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (25, 202, 'Gate', 'Gate', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (26, 202, '本地IP', 'Ip', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (27, 202, '服务器IP', 'SerIp', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (28, 202, '服务器端口', 'SerPort', NULL, 'Port');
INSERT INTO `device_param_val` VALUES (29, 203, '音量', 'Volume', NULL, 'oneToHundred');
INSERT INTO `device_param_val` VALUES (30, 203, 'Mask', 'Mask', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (31, 203, 'Gate', 'Gate', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (32, 203, '本地IP', 'Ip', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (33, 203, '服务器IP', 'SerIp', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (34, 203, '服务器端口', 'SerPort', NULL, 'Port');
INSERT INTO `device_param_val` VALUES (41, 205, '音量', 'Volume', NULL, 'oneToHundred');
INSERT INTO `device_param_val` VALUES (42, 205, 'Mask', 'Mask', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (43, 205, 'Gate', 'Gate', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (44, 205, '本地IP', 'Ip', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (45, 205, '服务器IP', 'SerIp', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (46, 205, '服务器端口', 'SerPort', NULL, 'Port');
INSERT INTO `device_param_val` VALUES (53, 207, '音量', 'Volume', NULL, 'oneToHundred');
INSERT INTO `device_param_val` VALUES (54, 207, 'Mask', 'Mask', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (55, 207, 'Gate', 'Gate', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (56, 207, '本地IP', 'Ip', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (57, 207, '服务器IP', 'SerIp', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (58, 207, '服务器端口', 'SerPort', NULL, 'Port');
INSERT INTO `device_param_val` VALUES (59, 208, '音量', 'Volume', NULL, 'oneToHundred');
INSERT INTO `device_param_val` VALUES (60, 208, 'Mask', 'Mask', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (61, 208, 'Gate', 'Gate', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (62, 208, '本地IP', 'Ip', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (63, 208, '服务器IP', 'SerIp', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (64, 208, '服务器端口', 'SerPort', NULL, 'Port');
INSERT INTO `device_param_val` VALUES (71, 210, '音量', 'Volume', NULL, 'oneToHundred');
INSERT INTO `device_param_val` VALUES (72, 210, 'Mask', 'Mask', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (73, 210, 'Gate', 'Gate', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (74, 210, '本地IP', 'Ip', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (75, 210, '服务器IP', 'SerIp', NULL, 'IP');
INSERT INTO `device_param_val` VALUES (76, 210, '服务器端口', 'SerPort', NULL, 'Port');

-- ----------------------------
-- Table structure for deviceinfo
-- ----------------------------
DROP TABLE IF EXISTS `deviceinfo`;
CREATE TABLE `deviceinfo`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `devName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  `devDsn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备序列号',
  `devCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备寻址号',
  `devHexcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备地址号',
  `devAddressCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备地址行政编号',
  `devAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备地址名称',
  `status` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '设备状态(8位二进制格式，如:00000001)',
  `lat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纬度',
  `lng` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经度',
  `messageId` int(20) NULL DEFAULT NULL COMMENT '当前应急消息挂载的应急消息的id',
  `deviceModel_id` int(20) NULL DEFAULT NULL COMMENT '设备型号id',
  `deviceId` int(20) NULL DEFAULT NULL,
  `statusScript` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态描述',
  `timefind` datetime(0) NULL DEFAULT NULL COMMENT '发现序列号时间',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `parentPath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 209 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of deviceinfo
-- ----------------------------
INSERT INTO `deviceinfo` VALUES (199, '设备2', '222222222', '000044510310000101', '01', '445103100001', '赣州市/定南县', '00000001', '25.850186', '114.907053', NULL, 33, NULL, NULL, '2018-03-02 09:03:22', '2018-03-01 17:39:51', '2018-05-07 18:26:28', 'admin', 'admin', NULL);
INSERT INTO `deviceinfo` VALUES (200, '设备3', '333333333', '000036072500000002', '02', '510104020009', '赣州市/崇义县', '00000001', '25.868133', '114.919126', NULL, 44, NULL, NULL, '2018-03-02 09:03:22', '2018-03-01 17:39:51', '2018-03-02 09:01:33', 'admin', 'admin', NULL);
INSERT INTO `deviceinfo` VALUES (201, '设备4', '444444444', '000036070000000002', '02', '510104020009', '赣州市', '00000000', '25.842382', '114.9266', NULL, 33, NULL, NULL, '2018-03-02 09:03:22', '2018-03-01 17:39:51', '2018-03-02 09:01:27', 'admin', 'admin', NULL);
INSERT INTO `deviceinfo` VALUES (202, '12312312312312', '123123123', '000044510340020001', '01', '445103400200', '潮安区/万峰林场/铁炉坪村委会', '00000001', '25.782535', '114.981217', NULL, 33, NULL, NULL, '2018-03-02 09:03:22', '2018-03-01 17:39:51', '2018-05-15 14:09:50', 'admin', 'admin', NULL);
INSERT INTO `deviceinfo` VALUES (205, 'FFFF', '0016A21300FFFFFFFF', '000036070200100102', '02', '510104020009', '古巷社区居委会', '00000011', '25.834057', '114.923438', 123123123, 33, NULL, '问题描述2', NULL, '2018-03-13 14:00:51', NULL, 'admin', NULL, NULL);
INSERT INTO `deviceinfo` VALUES (207, 'DB3E', 'TC81A01FFF0004DB3E', '000044510310000103', '03', '445103100001', '古巷社区居委会', '00000000', '23.518999', '116.740077', NULL, 33, NULL, NULL, NULL, '2018-05-15 15:41:03', NULL, 'admin', NULL, '445103100000;445103000000;445100000000;440000000000');
INSERT INTO `deviceinfo` VALUES (208, 'DB49', 'TC81A01FFF0004DB49', '000044510310000104', '04', '445103100001', '古巷社区居委会', '00000001', '23.518204', '116.742664', NULL, 33, NULL, NULL, NULL, '2018-05-18 19:25:37', NULL, 'admin', NULL, '445103100000;445103000000;445100000000;440000000000');

-- ----------------------------
-- Table structure for devicemodel
-- ----------------------------
DROP TABLE IF EXISTS `devicemodel`;
CREATE TABLE `devicemodel`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键\r\n\r\n\r\n\r\n\r\n\r\n',
  `devModel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备型号名\r\n',
  `devModelDescription` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备型号描述',
  `deviceType_id` int(20) NULL DEFAULT NULL COMMENT '设备类型id',
  `devModelCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备型号编码',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of devicemodel
-- ----------------------------
INSERT INTO `devicemodel` VALUES (33, '型号1', '参数：音量，Mask，Gate,本地ip，服务ip，服务端口', 6, NULL, '2018-02-27 15:55:06', 'admin', '2018-06-29 11:15:00', 'admin');
INSERT INTO `devicemodel` VALUES (39, '型号3', '参数：音量', 6, NULL, '2018-02-27 15:55:06', 'admin', '2018-06-29 11:15:00', 'admin');
INSERT INTO `devicemodel` VALUES (43, '型号2', '参数：音量', 6, NULL, '2018-02-27 15:55:06', 'admin', '2018-06-29 11:15:00', 'admin');
INSERT INTO `devicemodel` VALUES (44, '123', '参数：测试,Gate,Mask', 7, NULL, '2018-02-27 15:47:27', 'admin', '2018-07-10 17:04:38', 'admin');

-- ----------------------------
-- Table structure for devicemodelparam
-- ----------------------------
DROP TABLE IF EXISTS `devicemodelparam`;
CREATE TABLE `devicemodelparam`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `param_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `param_variable` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `param_length` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `param_form_check` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '表单校验字符串',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of devicemodelparam
-- ----------------------------
INSERT INTO `devicemodelparam` VALUES (19, '音量', 'Volume', '', 'oneToHundred', '2018-03-01 08:09:10', '2018-03-01 08:09:45', 'admin', 'admin');
INSERT INTO `devicemodelparam` VALUES (20, 'Mask', 'Mask', '', 'IP', '2018-03-01 08:09:14', '2018-03-01 08:09:49', 'admin', 'admin');
INSERT INTO `devicemodelparam` VALUES (21, 'Gate', 'Gate', '', 'IP', '2018-03-01 08:09:14', '2018-03-01 08:09:52', 'admin', 'admin');
INSERT INTO `devicemodelparam` VALUES (22, '本地IP', 'Ip', '', 'IP', '2018-03-01 08:09:14', '2018-03-01 08:09:56', 'admin', 'admin');
INSERT INTO `devicemodelparam` VALUES (23, '服务器IP', 'SerIp', '', 'IP', '2018-03-01 08:09:14', '2018-03-01 08:09:59', 'admin', 'admin');
INSERT INTO `devicemodelparam` VALUES (24, '服务器端口', 'SerPort', NULL, 'Port', '2018-03-01 08:09:14', '2018-03-01 08:08:49', 'admin', 'admin');
INSERT INTO `devicemodelparam` VALUES (25, '测试', '测试参数', NULL, 'number', '2018-06-07 11:33:37', '2018-06-07 11:33:37', NULL, NULL);

-- ----------------------------
-- Table structure for devicetype
-- ----------------------------
DROP TABLE IF EXISTS `devicetype`;
CREATE TABLE `devicetype`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `devType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备类型',
  `devTypeDescription` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备类型描述',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `create_by` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `update_by` varchar(0) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `devTypeCode` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '设备类型code',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of devicetype
-- ----------------------------
INSERT INTO `devicetype` VALUES (6, '接收终端', '接收终端', '2018-01-09 15:46:18', NULL, '2018-01-12 16:49:05', NULL, '0600');
INSERT INTO `devicetype` VALUES (7, '适配设备', '适配设备', '2018-04-10 10:01:17', NULL, '2018-04-10 10:01:20', NULL, '0400');

-- ----------------------------
-- Table structure for displaylanguage
-- ----------------------------
DROP TABLE IF EXISTS `displaylanguage`;
CREATE TABLE `displaylanguage`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `language` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '语言',
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '序号',
  `shortName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '播发语言简写',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of displaylanguage
-- ----------------------------
INSERT INTO `displaylanguage` VALUES (1, '汉语', '1', 'zhong', NULL, NULL, NULL, 'admin');
INSERT INTO `displaylanguage` VALUES (2, '英语', '2', 'eng', NULL, NULL, NULL, 'admin');
INSERT INTO `displaylanguage` VALUES (3, '测试增加1', '3', 'AA', NULL, NULL, NULL, 'admin');
INSERT INTO `displaylanguage` VALUES (4, '测试修改', '4', 'BBAAB', '2018-03-05 14:54:57', '2018-03-05 14:55:06', 'admin', 'admin');

-- ----------------------------
-- Table structure for displaymethod
-- ----------------------------
DROP TABLE IF EXISTS `displaymethod`;
CREATE TABLE `displaymethod`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '播发方式',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '播发方式编码',
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '序号',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of displaymethod
-- ----------------------------
INSERT INTO `displaymethod` VALUES (1, '文本转语音 TTS', '1', '1', NULL, '2018-07-30 13:47:01', NULL, 'admin');
INSERT INTO `displaymethod` VALUES (2, '文本转语音 TTS', '2', '2', NULL, '2018-07-30 13:47:10', NULL, 'admin');
INSERT INTO `displaymethod` VALUES (3, '滚动字幕', '3', '3', NULL, '2018-07-30 14:24:57', NULL, 'admin');
INSERT INTO `displaymethod` VALUES (7, '测试修改', 'AB', '5', '2018-03-05 14:51:58', '2018-07-30 14:17:01', 'admin', 'admin');
INSERT INTO `displaymethod` VALUES (8, '测试增加', 'AANN', '6', '2018-03-05 14:52:40', '2018-07-30 13:46:04', 'admin', 'admin');
INSERT INTO `displaymethod` VALUES (9, '测试增加1', 'AANNmm', '7', '2018-03-05 14:53:07', '2018-07-30 13:46:50', 'admin', 'admin');

-- ----------------------------
-- Table structure for emergencyinfo
-- ----------------------------
DROP TABLE IF EXISTS `emergencyinfo`;
CREATE TABLE `emergencyinfo`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `emergencyName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '信息名称',
  `emergencyCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件编号(随机数)',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `sound` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '音量大小(1-100）',
  `duration` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '持续时间',
  `addressCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选择的覆盖范围的区域编码',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '信息内容',
  `program_id` int(20) NULL DEFAULT NULL COMMENT '输入资源节目id的字符串,每个id用\';\'分割',
  `programDescription` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '输入资源描述',
  `infoSource_id` int(255) NULL DEFAULT NULL COMMENT '输出资源id',
  `accidentLevel_id` int(20) NULL DEFAULT NULL COMMENT 'accidentlevel的id',
  `accidentType_id` int(20) NULL DEFAULT NULL,
  `displayMethod_id` int(20) NULL DEFAULT NULL,
  `displayLanguage_id` int(20) NULL DEFAULT NULL,
  `emergencyLocation_id` int(20) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` int(20) NULL DEFAULT NULL COMMENT '信息状态\r\n(1:待提交\r\n2:待审核\r\n3:未通过审核\r\n4:已审核\r\n5:待发送\r\n6:已发送\r\n7:发送成功\r\n8:等待播发\r\n9:正在播发\r\n10:播发失败\r\n11:播发结束)',
  `unitName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '制作平台名称',
  `EBM_ID` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `areaCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flag` int(10) NULL DEFAULT NULL COMMENT '信息类型(0:预案1:非预案)',
  `addressCodeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 159 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of emergencyinfo
-- ----------------------------
INSERT INTO `emergencyinfo` VALUES (22, '财务室', '58173140132', '2018-02-24 00:00:00', '2018-02-24 17:21:09', '60', '60', '360702001000;360702002000;360702003000;', '1313123123', 2, 'IP:224.10.10.12:5000', 1, 2, 2, 1, 1, NULL, NULL, '2018-06-04 09:05:48', 'admin', '2018-02-24 14:01:33', 4, '章贡区', NULL, '360702000000', 1, NULL);
INSERT INTO `emergencyinfo` VALUES (23, '123123', '58173145327', '2018-02-24 00:00:00', '2018-02-24 17:27:51', '60', '60', '360702000000;', '123123', 2, 'IP:224.10.10.12:5000', 1, 2, 2, 1, 1, NULL, NULL, NULL, 'admin', '2018-02-24 14:53:27', 6, '章贡区', NULL, '360702000000', 1, NULL);
INSERT INTO `emergencyinfo` VALUES (24, '123', '58173160746', '2018-02-24 00:00:00', '2018-02-24 17:27:54', '60', '60', '360702001000;', '123123', 3, 'IP......', 1, 2, 5, 1, 1, NULL, NULL, NULL, 'admin', '2018-02-24 16:07:45', 2, '章贡区', NULL, '360702000000', 1, NULL);
INSERT INTO `emergencyinfo` VALUES (25, '123', '58173160759', '2018-02-24 00:00:00', '2018-02-24 17:27:57', '60', '60', '360702001000;', '123123', 3, 'IP......', 1, 2, 6, 1, 1, NULL, NULL, NULL, 'admin', '2018-02-24 16:07:59', 2, '章贡区', NULL, '360702000000', 1, NULL);
INSERT INTO `emergencyinfo` VALUES (26, 'asdf', '58185093519', '2018-03-08 09:34:54', '2018-03-08 10:34:54', '60', '60', '360700000000;', '1111111111111111111111111111111111111111', NULL, '', 1, 2, 7, 1, 1, NULL, NULL, NULL, 'admin', '2018-03-08 09:35:19', 6, '赣州市', '123456789987654321201803080002', '360700000000', 1, '赣州市;');
INSERT INTO `emergencyinfo` VALUES (27, 'sfsgadfga', '58185102939', '2018-03-08 10:29:03', '2018-03-08 11:29:03', '60', '60', '360721000000;360722100003;', '', NULL, '', 1, 2, 8, 1, 1, NULL, NULL, NULL, 'admin', '2018-03-08 10:29:39', 5, '赣州市', NULL, '360700000000', 1, '赣州市-赣县;赣州市-信丰县-嘉定镇-人民路居委会;');
INSERT INTO `emergencyinfo` VALUES (28, 'sfsgadfga55', '58185103059', '2018-03-08 10:29:03', '2018-03-08 11:29:03', '60', '60', '360700000000;', '', NULL, '', 1, 2, 9, 1, 1, NULL, NULL, NULL, 'admin', '2018-03-08 10:30:59', 6, '赣州市', '123456789987654321201803080003', '360700000000', 1, '赣州市;');
INSERT INTO `emergencyinfo` VALUES (29, '测试111', '58185104947', '2018-03-08 10:49:26', '2018-03-08 11:49:26', '60', '60', '360700000000;', '测试测试测试测试测试测试测试测试测试测试测试', NULL, '', 1, 2, 10, 1, 1, NULL, NULL, NULL, 'admin', '2018-03-08 10:49:47', 6, '赣州市', '123456789987654321201803080004', '360700000000', 1, '赣州市;');
INSERT INTO `emergencyinfo` VALUES (30, 'AAAAA', '58185112159', '2018-03-08 11:21:42', '2018-03-08 12:21:42', '60', '60', '360700000000;', '测试测试测试测试测试测试测试测试测试测试', NULL, '', 1, 2, 11, 1, 1, NULL, NULL, NULL, 'admin', '2018-03-08 11:21:59', 6, '赣州市', '123456789987654321201803080001', '360700000000', 1, '赣州市;');
INSERT INTO `emergencyinfo` VALUES (31, '测试2222', '58185112818', '2018-03-08 11:27:52', '2018-03-08 12:27:52', '60', '60', '360700000000;', '', 1, 'IP通道', 1, 2, 12, 1, 1, NULL, NULL, NULL, 'admin', '2018-03-08 11:28:18', 6, '赣州市', '123456789987654321201803080002', '360700000000', 1, '赣州市;');
INSERT INTO `emergencyinfo` VALUES (32, '测试333', '58185113232', '2018-03-08 11:27:52', '2018-03-08 12:27:52', '60', '60', '360700000000;', '', 1, 'IP通道', 1, 2, 13, 1, 1, NULL, NULL, NULL, 'admin', '2018-03-08 11:32:32', 6, '赣州市', '123456789987654321201803080001', '360700000000', 1, '赣州市;');
INSERT INTO `emergencyinfo` VALUES (33, '测试444', '58185114322', '2018-03-08 11:27:52', '2018-03-08 12:27:52', '60', '60', '360700000000;', '', 1, 'IP通道', 1, 2, 14, 1, 1, NULL, NULL, NULL, 'admin', '2018-03-08 11:43:22', 6, '赣州市', '123456789987654321201803080001', '360700000000', 1, '赣州市;');
INSERT INTO `emergencyinfo` VALUES (34, '测试555', '58185140159', '2018-03-08 14:01:35', '2018-03-08 15:01:35', '60', '60', '360700000000;', '', 1, 'IP通道', 1, 2, 15, 1, 1, NULL, NULL, NULL, 'admin', '2018-03-08 14:01:59', 11, '赣州市', '123456789987654321201803080001', '360700000000', 1, '赣州市;');
INSERT INTO `emergencyinfo` VALUES (36, '测试文字长度', '58186143737', '2018-03-09 14:30:50', '2018-03-09 15:30:50', '60', '60', '360700000000;', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试试测试', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-03-09 14:37:37', 5, '赣州市', NULL, '360700000000', 1, '赣州市;');
INSERT INTO `emergencyinfo` VALUES (37, '测试文字长度', '58186144020', '2018-03-09 14:30:50', '2018-03-09 15:30:50', '60', '60', '360700000000;', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-03-09 14:40:20', 5, '赣州市', NULL, '360700000000', 1, '赣州市;');
INSERT INTO `emergencyinfo` VALUES (38, '6666', '58190115218', '2018-03-13 11:51:59', '2018-03-13 12:51:59', '60', '60', '360700000000;', '应急平台测试文本', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-03-13 11:52:18', 6, '赣州市', '123456789987654321201803130001', '360700000000', 1, '赣州市;');
INSERT INTO `emergencyinfo` VALUES (39, '7777', '58190115737', '2018-03-13 11:51:59', '2018-03-13 12:51:59', '60', '60', '360700000000;', '应急平台测试文本', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-03-13 11:57:37', 6, '赣州市', '123456789987654321201803130001', '360700000000', 1, '赣州市;');
INSERT INTO `emergencyinfo` VALUES (40, '8888', '58190135911', '2018-03-13 13:58:50', '2018-03-13 14:58:50', '60', '60', '360700000000;', '应急广播平台测试文本', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-03-13 13:59:11', 6, '赣州市', '123456789987654321201803130001', '360700000000', 1, '赣州市;');
INSERT INTO `emergencyinfo` VALUES (41, '9999', '58190140143', '2018-03-13 14:01:13', '2018-03-13 15:01:13', '60', '60', '360700000000;', '应急广播平台测试文本', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-03-13 14:01:43', 6, '赣州市', '123456789987654321201803130002', '360700000000', 1, '赣州市;');
INSERT INTO `emergencyinfo` VALUES (42, '8888', '58191102055', '2018-03-14 10:20:44', '2018-03-14 11:20:44', '60', '60', '360700000000;', '', 1, 'IP通道', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-03-14 10:20:55', 6, '赣州市', '123456789987654321201803140001', '360700000000', 1, '赣州市;');
INSERT INTO `emergencyinfo` VALUES (43, '测试增加', '58193163415', '2018-03-16 16:34:10', '2018-03-16 17:34:10', '60', '60', '510100000000;', '', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-03-16 16:34:15', 6, '赣州市', '123456789987654321201803140001', '510100000000', 1, '成都市;');
INSERT INTO `emergencyinfo` VALUES (44, '测试预案', '58196103516', '2018-03-28 10:18:22', '2018-03-28 10:18:26', '60', '60', '510100000000;', '测试', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-03-19 10:35:16', 6, '赣州市', '123456789987654321201803140001', '510100000000', 1, NULL);
INSERT INTO `emergencyinfo` VALUES (45, '测试监控系统1', '58217114554', '2018-04-09 11:45:42', '2018-04-09 12:45:42', '60', '60', '510104000000;', '', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-04-09 11:45:54', 5, '赣州市', NULL, '510100000000', 1, '成都市-锦江区;');
INSERT INTO `emergencyinfo` VALUES (46, '测试合并区域编码', '58218162401', '2018-04-10 16:23:55', '2018-04-10 17:23:55', '60', '60', '510104000000;510106000000;510108002001;510121000000;510132001002;510132001005;', '', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-04-10 16:24:01', 2, '赣州市', NULL, '510100000000', 1, '成都市-锦江区;成都市-金牛区;成都市-成华区-猛追湾街道-东街社区居委会;成都市-金堂县;成都市-新津县-五津街道-复兴社区居委会;成都市-新津县-五津街道-城西社区居委会;');
INSERT INTO `emergencyinfo` VALUES (47, '测试区域编码合并2', '58218162813', '2018-04-10 16:28:11', '2018-04-10 17:28:11', '60', '60', '510106000000;510108002000;510112001000;510114000000;510115000000;510116000000;', '', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-04-10 16:28:13', 2, '赣州市', NULL, '510100000000', 1, '成都市-金牛区;成都市-成华区-猛追湾街道;成都市-龙泉驿区-龙泉街道办事处;成都市-新都区;成都市-温江区;成都市-双流区;');
INSERT INTO `emergencyinfo` VALUES (48, '2018-04-12', '58220142001', '2018-04-12 14:19:48', '2018-04-12 15:19:48', '60', '60', '510100000000;', '', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-04-12 14:20:01', 2, '赣州市', NULL, '510100000000', 1, '成都市;');
INSERT INTO `emergencyinfo` VALUES (49, '20180416', '58224093022', '2018-04-16 09:30:09', '2018-04-16 10:30:09', '60', '60', '510100000000;', '', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-04-16 09:30:22', 2, '赣州市', NULL, '510100000000', 1, '成都市;');
INSERT INTO `emergencyinfo` VALUES (50, '20180416', '58224093030', '2018-04-16 09:30:09', '2018-04-16 10:30:09', '60', '60', '510100000000;', '', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-04-16 09:30:30', 2, '赣州市', NULL, '510100000000', 1, '成都市;');
INSERT INTO `emergencyinfo` VALUES (51, '20180416', '58224093037', '2018-04-16 09:30:09', '2018-04-16 10:30:09', '60', '60', '510100000000;', '', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-04-16 09:30:37', 2, '赣州市', NULL, '510100000000', 1, '成都市;');
INSERT INTO `emergencyinfo` VALUES (52, '20180418', '58226115210', '2018-04-18 11:52:00', '2018-04-18 12:52:00', '60', '60', '510100000000;', '', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-04-18 11:52:10', 2, '赣州市', NULL, '510100000000', 1, '成都市;');
INSERT INTO `emergencyinfo` VALUES (53, '20180419', '58227111830', '2018-04-19 11:18:21', '2018-04-19 12:18:21', '60', '60', '510100000000;', '', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-04-19 11:18:30', 2, '赣州市', NULL, '510100000000', 1, '成都市;');
INSERT INTO `emergencyinfo` VALUES (54, '20180420', '58228102854', '2018-04-20 10:28:40', '2018-04-20 11:28:40', '60', '60', '510100000000;', '', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-04-20 10:28:54', 2, '赣州市', NULL, '510100000000', 1, '成都市;');
INSERT INTO `emergencyinfo` VALUES (76, '预案测试', '58247141233', NULL, NULL, '60', '60', '445103000000;', '1111111111111111111111', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-09 14:12:33', 2, '潮安区', NULL, '445103000000', 0, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (107, '预案测试(2018-05-12 16:03:23)', '58247141233', '2018-05-12 16:03:23', '2018-05-12 17:03:23', '60', '60', '445103000000;', '1111111111111111111111', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-09 14:12:33', 5, '潮安区', NULL, '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (108, '预案测试(2018-05-12 16:03:56)', '58247141233', '2018-05-12 16:03:56', '2018-05-12 17:03:56', '60', '60', '445103000000;', '1111111111111111111111', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-09 14:12:33', 5, '潮安区', NULL, '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (109, '预案测试(2018-05-12 16:04:16)', '58247141233', '2018-05-12 16:04:16', '2018-05-12 17:04:16', '60', '60', '445103000000;', '1111111111111111111111', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-09 14:12:33', 5, '潮安区', NULL, '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (110, '预案测试2', '58250160617', NULL, NULL, '60', '60', '445103000000;', '2222222222', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-12 16:06:17', 2, '潮安区', NULL, '445103000000', 0, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (112, '预案测试(2018-05-12 16:11:30)', '58247141233', '2018-05-12 16:11:30', '2018-05-12 17:11:30', '60', '60', '445103000000;', '1111111111111111111111', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-09 14:12:33', 5, '潮安区', NULL, '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (113, '预案测试3', '58250161203', NULL, NULL, '60', '60', '445103000000;', '2222222222', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-12 16:12:03', 2, '潮安区', NULL, '445103000000', 0, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (114, 'Test1', '58252163417', '2018-05-14 16:34:05', '2018-05-14 16:35:05', '60', '1', '445103000000;', '11111111', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-14 16:34:17', 6, '潮安区', '123456789987654321201805140001', '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (115, 'Test2', '58252164314', '2018-05-14 16:41:40', '2018-05-14 16:42:40', '60', '1', '445103000000;', '111111111', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, '2018-06-05 10:52:41', 'admin', '2018-05-14 16:43:14', 5, '潮安区', '123456789987654321201805140002', '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (116, 'Test3', '58252170628', '2018-05-14 17:06:21', '2018-05-14 17:07:21', '60', '1', '445103000000;', '111111111111111111111', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, '2018-06-05 10:54:08', 'admin', '2018-05-14 17:06:28', 5, '潮安区', '123456789987654321201805140003', '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (117, '123456', '58256145356', '2018-05-18 14:53:48', '2018-05-18 15:53:48', '60', '60', '445103000000;', '11111111111', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, '2018-06-05 10:54:32', 'admin', '2018-05-18 14:53:56', 5, '潮安区', NULL, '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (118, '8989', '58256180947', '2018-05-18 18:09:35', '2018-05-18 19:09:35', '60', '60', '445103000000;', '11111111111111111111111111111111222233333333333333333', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-18 18:09:47', 11, '潮安区', '123456789987654321201805180001', '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (119, '79797', '58256181244', '2018-05-18 18:12:40', '2018-05-18 19:12:40', '60', '60', '445103000000;', '55555555555566666', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-18 18:12:44', 6, '潮安区', '123456789987654321201805180002', '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (120, '8888899', '58256184201', '2018-05-18 18:41:55', '2018-05-18 19:41:55', '60', '60', '445103000000;', '565656656', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-18 18:42:01', 6, '潮安区', '123456789987654321201805180003', '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (121, '564654564', '58256191624', '2018-05-18 19:16:17', '2018-05-18 20:16:17', '60', '60', '445103000000;', '556656656', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-18 19:16:24', 11, '潮安区', '123456789987654321201805180004', '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (122, '预案测试(2018-05-18 19:22:03)', '58247141233', '2018-05-18 19:22:03', '2018-05-18 20:22:03', '60', '60', '445103000000;', '1111111111111111111111', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-09 14:12:33', 5, '潮安区', NULL, '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (123, '88889797', '58256192332', '2018-05-18 19:23:28', '2018-05-18 20:23:28', '60', '60', '445103000000;', '889989', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-18 19:23:32', 11, '潮安区', '123456789987654321201805180009', '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (124, '456456', '58256194103', '2018-05-18 19:40:53', '2018-05-18 20:40:53', '60', '60', '445103000000;', '1545645645646', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-18 19:41:03', 11, '潮安区', '123456789987654321201805180010', '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (125, '456456', '58256194637', '2018-05-18 19:40:53', '2018-05-18 20:40:53', '60', '60', '445103000000;', '7777777777777', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-18 19:46:37', 11, '潮安区', '123456789987654321201805180011', '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (126, '123456', '58256202159', '2018-05-18 20:21:52', '2018-05-18 21:21:52', '60', '60', '445103000000;', '123456789', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-18 20:21:59', 11, '潮安区', '123456789987654321201805180012', '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (127, '7777', '58256204534', '2018-05-18 20:45:26', '2018-05-18 21:45:26', '60', '60', '445103000000;', '123456789987654321', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-18 20:45:34', 11, '潮安区', '123456789987654321201805180013', '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (128, '8888', '58256205316', '2018-05-18 20:53:10', '2018-05-18 21:53:10', '60', '60', '445103000000;', '123456789987654321', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-18 20:53:16', 6, '潮安区', '123456789987654321201805180014', '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (129, '测试TEXT', '58257143809', '2018-05-19 14:37:58', '2018-05-19 15:37:58', '60', '60', '445103000000;', '浔阳江头夜送客，枫叶荻花秋瑟瑟。主人下马客在船，举酒欲饮无管弦。醉不成欢惨将别，别时茫茫江浸月。\r\n忽闻水上琵琶声，主人忘归客不发。寻声暗问弹者谁？琵琶声停欲语迟。移船相近邀相见，添酒回灯重开宴。千呼万唤始出来，犹抱琵琶半遮面。转轴拨弦三两声，未成曲调先有情。弦弦掩抑声声思，似诉平生不得志。低眉信手续续弹，说尽心中无限事。轻拢慢捻抹复挑，初为霓裳后六幺。大弦嘈嘈如急雨，小弦切切如私语。嘈嘈切切错杂弹，大珠小珠落玉盘。间关莺语花底滑，幽咽泉流冰下难。冰泉冷涩弦凝绝，凝绝不通声暂歇。别有幽愁暗恨生，此时无声胜有声。银瓶乍破水浆迸，铁骑突出刀枪鸣。曲终收拨当心画，四弦一声如裂帛。东船西舫悄无言，唯见江心秋月白。\r\n\r\n沉吟放拨插弦中，整顿衣裳起敛容。自言本是京城女，家在虾蟆陵下住。十三学得琵琶成，名属教坊第一部。曲罢曾教善才服，妆成每被秋娘妒。五陵年少争缠头，一曲红绡不知数。钿头银篦击节碎，血色罗裙翻酒污。今年欢笑复明年，秋月春风等闲度。弟走从军阿姨死，暮去朝来颜色故。门前冷落鞍马稀，老大嫁作商人妇。商人重利轻别离，前月浮梁买茶去。去来江口守空船，绕船月明江水寒。夜深忽梦少年事，梦啼妆泪红阑干。\r\n我闻琵琶已叹息，又闻此语重唧唧。同是天涯沦落人，相逢何必曾相识！我从去年辞帝京，谪居卧病浔阳城。浔阳地僻无音乐，终岁不闻丝竹声。住近湓江地低湿，黄芦苦竹绕宅生。其间旦暮闻何物？杜鹃啼血猿哀鸣。春江花朝秋月夜，往往取酒还独倾。岂无山歌与村笛？呕哑嘲哳难为听。今夜闻君琵琶语，如听仙乐耳暂明。莫辞更坐弹一曲，为君翻作《琵琶行》。感我此言良久立，却坐促弦弦转急。凄凄不似向前声，满座重闻皆掩泣。座中泣下谁最多？江州司马青衫湿。', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-19 14:38:09', 2, '潮安区', NULL, '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (130, '测试123', '58260112702', '2018-05-22 11:26:48', '2018-05-22 12:26:48', '60', '60', '445103000000;', '测试123456789', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-22 11:27:02', 11, '潮安区', '123456789987654321201805220001', '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (131, '测试456', '58260113212', '2018-05-22 11:31:59', '2018-05-22 12:31:59', '60', '60', '445103000000;', '测试45789123456', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-22 11:32:12', 11, '潮安区', '123456789987654321201805220002', '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (132, '测试789', '58260113456', '2018-05-22 11:34:47', '2018-05-22 12:34:47', '60', '60', '445103000000;', '测试7894561230', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-22 11:34:56', 11, '潮安区', '123456789987654321201805220003', '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (133, '测试1212', '58260114122', '2018-05-22 11:41:13', '2018-05-22 12:41:13', '60', '60', '445103000000;', '测试121232456789', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-22 11:41:22', 11, '潮安区', '123456789987654321201805220004', '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (134, '应急信息测试', '58261111921', '2018-05-24 11:18:20', '2018-05-24 12:18:20', '60', '60', '445103100000;', '撒大声地撒多撒多', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-23 11:19:22', 5, '潮安区', NULL, '445103000000', 1, '潮安区-古巷镇;');
INSERT INTO `emergencyinfo` VALUES (135, '资源发送测试', '58261140928', '2018-05-23 14:09:16', '2018-05-23 15:09:16', '60', '60', '445103101001;', '', 1, 'IP通道', 1, 2, 1, 1, 1, NULL, NULL, '2018-06-04 09:53:47', 'admin', '2018-05-23 14:09:29', 6, '潮安区', '123456789987654321201805230001', '445103000000', 1, '潮安区-登塘镇-登塘社区居委会;');
INSERT INTO `emergencyinfo` VALUES (136, '测试1111', '58261150301', '2018-05-23 15:02:51', '2018-05-23 16:02:51', '60', '60', '445103000000;', 'gyigvyivhuivugivui', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, '2018-06-04 09:53:48', 'admin', '2018-05-23 15:03:02', 6, '潮安区', '123456789987654321201805230002', '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (137, '再次测试2222', '58261150639', '2018-05-23 15:05:57', '2018-05-23 15:07:57', '60', '2', '445103000000;', '奥术大师大所大所多撒多撒多所', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, '2018-06-04 09:53:50', 'admin', '2018-05-23 15:06:39', 6, '潮安区', '123456789987654321201805230003', '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (138, '新增测试', '58261150854', '2018-05-23 15:08:38', '2018-05-23 16:08:38', '60', '60', '445103000000;', '阿斯顿撒多撒多', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, '2018-06-04 09:53:52', 'admin', '2018-05-23 15:08:54', 5, '潮安区', NULL, '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (139, '不带文本测试', '58262115116', '2018-05-24 11:51:01', '2018-05-24 12:51:01', '60', '60', '445103000000;', '', 1, 'IP通道', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-24 11:51:16', 5, '潮安区', NULL, '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (140, 'sdasdsa ', '58262135936', '2018-05-24 13:59:25', '2018-05-24 14:59:25', '60', '60', '445103000000;', 'wenzi sdasdsa', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-24 13:59:36', 6, '潮安区', '123456789987654321201805240001', '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (141, '什么code', '58262145926', '2018-05-24 14:59:15', '2018-05-24 15:59:15', '60', '60', '445103100000;', 'code？', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-24 14:59:26', 5, '潮安区', NULL, '445103000000', 1, '潮安区-古巷镇;');
INSERT INTO `emergencyinfo` VALUES (142, '什么code', '58262150007', '2018-05-24 14:59:15', '2018-05-24 15:59:15', '60', '60', '445103101000;', 'code？', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-24 15:00:08', 6, '潮安区', '123456789987654321201805250001', '445103000000', 1, '潮安区-登塘镇;');
INSERT INTO `emergencyinfo` VALUES (144, '5月25日测试', '58263104224', '2018-05-25 10:42:03', '2018-05-25 11:42:03', '60', '60', '445103114000;', '2018年5月25日10:42:13测试', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-25 10:42:24', 6, '潮安区', '123456789987654321201805250002', '445103000000', 1, '潮安区-江东镇;');
INSERT INTO `emergencyinfo` VALUES (145, 'vvvvssss', '58268162806', '2018-05-30 16:32:01', '2018-05-30 17:32:01', '60', '60', '445103000000;', 'fscadddee1111', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-30 16:28:06', 6, '潮安区', '123456789987654321201805300001', '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (146, '888888', '58268163146', '2018-05-30 16:35:52', '2018-05-30 17:35:52', '60', '60', '445103000000;', 'fscadddee1111', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-30 16:31:47', 6, '潮安区', '123456789987654321201805300002', '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (147, '预案测试(2018-06-05 10:55:20)', '58247141233', '2018-06-05 10:55:20', '2018-06-05 11:55:20', '60', '60', '445103000000;', '1111111111111111111111', NULL, '', 1, 2, 1, 1, 1, NULL, NULL, NULL, 'admin', '2018-05-09 14:12:33', 5, '潮安区', NULL, '445103000000', 1, '潮安区;');
INSERT INTO `emergencyinfo` VALUES (150, 'boot测试', '58281115749', '2018-06-12 11:57:31', '2018-06-12 12:57:31', '60', '60', '445103100000;', 'springboot测试', NULL, '', 1, 2, 1, 1, 1, NULL, 'admin', '2018-06-12 13:34:42', 'admin', '2018-06-12 11:57:49', 6, '潮安区', '123456789987654321201806120002', '445103000000', 1, '广东省;潮州市;潮安区;');
INSERT INTO `emergencyinfo` VALUES (152, '2018年7月3日18:48:14测试', '58302184831', '2018-07-03 18:48:19', '2018-07-03 19:48:19', '60', '60', '445103104000;', '2018年7月3日18:48:23测试', NULL, '', 1, 2, 1, 1, 1, NULL, 'admin', '2018-07-03 18:48:36', 'admin', '2018-07-03 18:48:31', 6, '安徽省舒城县', '123456789987654321201807030001', '445103000000', 1, '广东省;潮州市;潮安区;');
INSERT INTO `emergencyinfo` VALUES (153, '音频演练', '58324151536', '2018-06-15 21:58:46', '2018-06-15 22:08:46', '60', '10', NULL, '', NULL, 'EBDR_1528773660358_gme.mp3', 1, 2, 1, 2, 1, NULL, NULL, '2018-08-23 11:03:07', '安徽省调度控制平台', '2018-07-13 15:06:26', 2, '安徽省舒城县', '23400000000000101010101201806150001', '341523000000', 1, NULL);
INSERT INTO `emergencyinfo` VALUES (154, 'ces', '58353110710', '2018-08-23 11:06:56', '2018-08-23 12:06:56', '60', '60', '445103000000;', 'ssssssssssssssss', NULL, '', 1, 2, 1, 1, 1, NULL, 'admin', '2018-08-23 11:08:42', 'admin', '2018-08-23 11:07:11', 5, '潮安区', NULL, '445103000000', 1, '广东省;潮州市;潮安区;');
INSERT INTO `emergencyinfo` VALUES (155, 'ffffee', '58365141032', '2018-09-04 14:12:15', '2018-09-04 15:12:15', '60', '60', '445103100000;', '', 1, 'IP通道', 1, 2, 1, 1, 1, NULL, 'admin', '2018-09-04 14:11:41', 'admin', '2018-09-04 14:10:34', 6, '潮安区', '123456789987654321201809040001', '445103000000', 1, '广东省;潮州市;潮安区;');
INSERT INTO `emergencyinfo` VALUES (156, 'lolo', '58365141524', '2018-09-04 14:14:24', '2018-09-04 15:14:24', '60', '60', '445103104000;', '', NULL, '', 1, 2, 1, 1, 1, NULL, 'admin', '2018-09-04 14:15:46', 'admin', '2018-09-04 14:15:26', 6, '潮安区', '123456789987654321201809040003', '445103000000', 1, '广东省;潮州市;潮安区;');
INSERT INTO `emergencyinfo` VALUES (157, '测试2018年9月4日14:19:41', '58365142004', '2018-09-04 14:19:46', '2018-09-04 15:19:46', '60', '60', '445103100000;', '测试2018年9月4日14:20:00', NULL, '', 1, 2, 1, 1, 1, NULL, 'admin', NULL, NULL, '2018-09-04 14:20:06', 2, '潮安区', NULL, '445103000000', 1, '广东省;潮州市;潮安区;');
INSERT INTO `emergencyinfo` VALUES (158, '1111333', '58365180042', '2018-09-04 17:59:52', '2018-09-04 18:59:52', '60', '60', '445103100000;', '', 1, 'IP通道', 1, 2, 1, 1, 1, NULL, 'admin', NULL, NULL, '2018-09-04 18:00:43', 2, '潮安区', NULL, '445103000000', 1, '广东省;潮州市;潮安区;');

-- ----------------------------
-- Table structure for infosource
-- ----------------------------
DROP TABLE IF EXISTS `infosource`;
CREATE TABLE `infosource`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `audio_pid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `info_source_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `info_source_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `port` int(11) NULL DEFAULT NULL,
  `service_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `video_pid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of infosource
-- ----------------------------
INSERT INTO `infosource` VALUES (1, '144', '23434', '麦克风(编码器1)', '224.10.10.101', 6000, '5', '128');
INSERT INTO `infosource` VALUES (2, '144', '23235', '其他（测试）', '224.10.10.101', 6000, '5', '128');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '显示标题',
  `pid` int(20) NULL DEFAULT NULL COMMENT '父节点ID',
  `number` int(20) NULL DEFAULT NULL COMMENT '排序',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '关联地址',
  `menuCaptionHint` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标题相关提示信息',
  `menuCaptionEn` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '英文标题',
  `menuSystem` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '属于哪个系统的menu',
  `menuImage` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '默认图片',
  `perms` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '综合网络管理系统', NULL, 2, NULL, '根菜单，无法修改', 'Integrated network management system', '0', NULL, 'root:integrated');
INSERT INTO `menu` VALUES (2, '系统管理', 1, 23, '', '系统管理：用户管理，角色管理，菜单管理，。。。', 'systemMange', '0', 'icon-man', 'menu:system');
INSERT INTO `menu` VALUES (3, '用户管理', 2, 231, '../userAction/toList', '用户管理，增加，修改，删除', 'userManage', '0', 'icon-man', 'sys:user');
INSERT INTO `menu` VALUES (4, '角色管理', 2, 232, '../roleAction/toList', '角色管理：增加，修改，删除，，，', 'roleManage', '0', 'icon-man', 'sys:role');
INSERT INTO `menu` VALUES (5, '菜单管理', 2, 233, '../menuAction/toList', '菜单管理：增加，修改，删除。。。', 'menuManage', '0', 'icon-man', 'sys:menu');
INSERT INTO `menu` VALUES (6, '应急播发管理系统', NULL, 1, NULL, '根菜单，无法修改', 'Emergency broadcast management system', '1', NULL, 'root:emergency');
INSERT INTO `menu` VALUES (8, '预案管理', 6, 12, '', '应急预案：预案信息。。。', 'emergencyPlanManage', '1', 'icon-man', 'menu:menerPlan');
INSERT INTO `menu` VALUES (9, '设备管理', 1, 21, '', '设备管理：设备信息，，，，', 'deviceManage', '0', 'icon-man', 'menu:device');
INSERT INTO `menu` VALUES (10, '参数管理', 1, 22, '', '参数管理：事件类型，事件等级，。。。。', 'paramManage', '0', 'icon-man', 'menu:param');
INSERT INTO `menu` VALUES (11, '日志管理', 1, 24, '', '日志管理：系统日志，设备日志', 'logManage', '0', 'icon-man', 'menu:log');
INSERT INTO `menu` VALUES (12, '设备信息', 9, 211, '../deviceInfoAction/toList', '设备信息：注册，删除，修改，参数设置', 'deviceInfo', '0', 'icon-man', 'dev:info');
INSERT INTO `menu` VALUES (13, '设备日志', 11, 242, '../deviceLogAction/goDeviceLog', '设备日志：日志信息', 'deviceLog', '0', 'icon-man', 'log:device');
INSERT INTO `menu` VALUES (14, '事件类型', 10, 222, '../accidentTypeAction/toList', '事件类型：增加，修改，删除。', 'accidenttype', '0', 'icon-man', 'param:type');
INSERT INTO `menu` VALUES (15, '事件等级', 10, 223, '../accidentLevelAction/toList', '事件等级：增加，修改，删除', 'accidentLevel', '0', 'icon-man', 'param:level');
INSERT INTO `menu` VALUES (16, '流程管理', 1, 25, '', '流程管理：审核流程，发送流程', 'liuchen', '0', 'icon-man', 'menu:process');
INSERT INTO `menu` VALUES (17, '审核流程', 16, 251, '', '审核流程', 'registerManage', '0', 'icon-man', 'process:review');
INSERT INTO `menu` VALUES (18, '设备型号', 9, 213, '../deviceModelAction/toList', '设备型号：增加，修改，删除', 'deviceModel', '0', 'icon-man', 'dev:model');
INSERT INTO `menu` VALUES (19, '设备类型', 9, 214, '../deviceTypeAction/toList', '设备类型：增加，修改，删除', 'deviceType', '0', 'icon-man', 'dev:type');
INSERT INTO `menu` VALUES (20, '设备总参', 9, 212, '../deviceModelParamAction/toList', '设备参数总表设置', 'deviceParam', '0', 'icon-man', 'dev:param');
INSERT INTO `menu` VALUES (21, '预案信息', 8, 121, '../emergencyInfoAction/toPlan', '应急预案信息', 'emergencyInfoPlan', '1', 'icon-man', 'plan:info');
INSERT INTO `menu` VALUES (22, '统计管理', 6, 13, '', '统计管理：统计图表，统计信息', 'statisticsManage', '1', 'icon-man', 'menu:statistic');
INSERT INTO `menu` VALUES (23, '统计图表', 22, 131, '../statisticsAction/toList', '统计图表', 'statistic chart', '1', 'icon-man', 'count:chart');
INSERT INTO `menu` VALUES (24, '播发管理', 6, 11, '', '应急信息播发管理', 'broadCastManage', '1', 'icon-man', 'menu:broadcast');
INSERT INTO `menu` VALUES (27, '信息审核', 24, 112, '../emergencyInfoAction/toReviewList', '信息审核', 'shenhe', '1', 'icon-man', 'emer:review');
INSERT INTO `menu` VALUES (28, '信息播发', 24, 113, '../emergencyInfoAction/toCastList', '信息播发', '123', '1', 'icon-man', 'emer:cast');
INSERT INTO `menu` VALUES (30, '系统日志', 11, 241, '../sysLogAction/toList', '', 'SystemLog', '0', 'icon-man', 'log:system');
INSERT INTO `menu` VALUES (31, '播发语言', 10, 224, '../displayLanguageAction/toList', '', 'DisplayLanguage', '0', 'icon-man', 'param:language');
INSERT INTO `menu` VALUES (32, '播发方式', 10, 225, '../displayMethodAction/toList', '', 'DisplayMethod', '0', 'icon-man', 'param:method');
INSERT INTO `menu` VALUES (33, '行政区域', 10, 221, '../administrativeAction/toList', '', 'AdministrationArea', '0', 'icon-man', 'param:admin');
INSERT INTO `menu` VALUES (34, '信息管理', 24, 111, '../emergencyInfoAction/toList', '', '1313', '1', 'icon-man', 'emer:info');
INSERT INTO `menu` VALUES (35, '平台管理', 2, 234, '../platformAction/toPlatform', '平台管理：平台信息管理', 'PlatformManage', '0', 'icon-man', 'sys:platform');
INSERT INTO `menu` VALUES (36, '节点管理', 6, 14, '', '系统管理：节点状态、节点管理', 'nodeMange', NULL, 'icon-man', 'menu:node');
INSERT INTO `menu` VALUES (37, '节点列表', 36, 141, '../nodeAction/toList', '节点查看：增加，修改，删除，启用，停用', 'nodeList', NULL, 'icon-man', 'node:info');
INSERT INTO `menu` VALUES (38, '节点消息', 36, 142, '/nodeAction/toNodeNews', '展示接收到的应急消息列表', 'newsManage', NULL, 'icon-man', 'node:news');

-- ----------------------------
-- Table structure for menu_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `menu_role_relation`;
CREATE TABLE `menu_role_relation`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `menu_id` int(20) NULL DEFAULT NULL COMMENT '菜单id',
  `role_id` int(20) NULL DEFAULT NULL COMMENT '角色id',
  `is_view` tinyint(1) NULL DEFAULT NULL COMMENT '查看权',
  `is_modify` tinyint(1) NULL DEFAULT NULL COMMENT '修改权',
  `is_add` tinyint(1) NULL DEFAULT NULL COMMENT '添加权',
  `is_delete` tinyint(1) NULL DEFAULT NULL COMMENT '删除权',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建授权时间',
  `create_by` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `update_by` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of menu_role_relation
-- ----------------------------
INSERT INTO `menu_role_relation` VALUES (1, 34, 14, 0, 1, 1, 1, '2018-08-02 17:57:04', 'admin', '2018-08-03 17:33:39', 'admin');
INSERT INTO `menu_role_relation` VALUES (2, 27, 14, 1, 1, 0, 1, '2018-08-02 17:57:04', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (3, 28, 14, 1, 0, 1, 1, '2018-08-02 17:57:04', 'admin', '2018-08-07 09:21:55', 'admin');
INSERT INTO `menu_role_relation` VALUES (4, 21, 14, 1, 1, 1, 0, '2018-08-02 17:57:04', 'admin', '2018-08-03 17:04:47', 'admin');
INSERT INTO `menu_role_relation` VALUES (5, 23, 14, 1, 0, 1, 1, '2018-08-02 17:57:04', 'admin', '2018-08-07 09:21:55', 'admin');
INSERT INTO `menu_role_relation` VALUES (6, 37, 14, 1, 1, 0, 1, '2018-08-02 17:57:04', 'admin', '2018-08-03 17:04:47', 'admin');
INSERT INTO `menu_role_relation` VALUES (7, 38, 14, 0, 1, 1, 1, '2018-08-02 17:57:04', 'admin', '2018-08-06 17:43:23', 'admin');
INSERT INTO `menu_role_relation` VALUES (14, 3, 1, 1, 1, 1, 1, '2018-08-06 14:24:50', 'admin', '2018-08-07 10:42:26', 'admin');
INSERT INTO `menu_role_relation` VALUES (15, 4, 1, 1, 1, 1, 1, '2018-08-06 14:24:50', 'admin', '2018-08-06 17:32:37', 'admin');
INSERT INTO `menu_role_relation` VALUES (16, 5, 1, 1, 1, 1, 1, '2018-08-06 14:24:50', 'admin', '2018-08-07 10:26:30', 'admin');
INSERT INTO `menu_role_relation` VALUES (17, 35, 1, 1, 1, 1, 1, '2018-08-06 14:24:50', 'admin', '2018-08-06 17:32:37', 'admin');
INSERT INTO `menu_role_relation` VALUES (18, 6, 1, 0, 0, 0, 0, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:29', 'admin');
INSERT INTO `menu_role_relation` VALUES (19, 1, 1, 0, 0, 0, 0, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:29', 'admin');
INSERT INTO `menu_role_relation` VALUES (20, 24, 1, 0, 0, 0, 0, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:29', 'admin');
INSERT INTO `menu_role_relation` VALUES (21, 8, 1, 0, 0, 0, 0, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:29', 'admin');
INSERT INTO `menu_role_relation` VALUES (22, 22, 1, 0, 0, 0, 0, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:29', 'admin');
INSERT INTO `menu_role_relation` VALUES (23, 36, 1, 0, 0, 0, 0, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:29', 'admin');
INSERT INTO `menu_role_relation` VALUES (24, 34, 1, 1, 1, 1, 1, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:58', 'admin');
INSERT INTO `menu_role_relation` VALUES (25, 27, 1, 1, 1, 1, 1, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:58', 'admin');
INSERT INTO `menu_role_relation` VALUES (26, 28, 1, 1, 1, 1, 1, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:58', 'admin');
INSERT INTO `menu_role_relation` VALUES (27, 21, 1, 1, 1, 1, 1, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:37', 'admin');
INSERT INTO `menu_role_relation` VALUES (28, 23, 1, 1, 1, 1, 1, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:37', 'admin');
INSERT INTO `menu_role_relation` VALUES (29, 37, 1, 1, 1, 1, 1, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:37', 'admin');
INSERT INTO `menu_role_relation` VALUES (30, 38, 1, 1, 1, 1, 1, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:37', 'admin');
INSERT INTO `menu_role_relation` VALUES (31, 9, 1, 0, 0, 0, 0, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:29', 'admin');
INSERT INTO `menu_role_relation` VALUES (32, 10, 1, 0, 0, 0, 0, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:29', 'admin');
INSERT INTO `menu_role_relation` VALUES (33, 2, 1, 0, 0, 0, 0, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:29', 'admin');
INSERT INTO `menu_role_relation` VALUES (34, 11, 1, 0, 0, 0, 0, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:29', 'admin');
INSERT INTO `menu_role_relation` VALUES (35, 16, 1, 0, 0, 0, 0, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:29', 'admin');
INSERT INTO `menu_role_relation` VALUES (36, 12, 1, 1, 1, 1, 1, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:37', 'admin');
INSERT INTO `menu_role_relation` VALUES (37, 20, 1, 1, 1, 1, 1, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:37', 'admin');
INSERT INTO `menu_role_relation` VALUES (38, 18, 1, 1, 1, 1, 1, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:37', 'admin');
INSERT INTO `menu_role_relation` VALUES (39, 19, 1, 1, 1, 1, 1, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:37', 'admin');
INSERT INTO `menu_role_relation` VALUES (40, 33, 1, 1, 1, 1, 1, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:37', 'admin');
INSERT INTO `menu_role_relation` VALUES (41, 14, 1, 1, 1, 1, 1, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:37', 'admin');
INSERT INTO `menu_role_relation` VALUES (42, 15, 1, 1, 1, 1, 1, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:37', 'admin');
INSERT INTO `menu_role_relation` VALUES (43, 31, 1, 1, 1, 1, 1, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:37', 'admin');
INSERT INTO `menu_role_relation` VALUES (44, 32, 1, 1, 1, 1, 1, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:37', 'admin');
INSERT INTO `menu_role_relation` VALUES (45, 30, 1, 1, 1, 1, 1, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:37', 'admin');
INSERT INTO `menu_role_relation` VALUES (46, 13, 1, 1, 1, 1, 1, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:37', 'admin');
INSERT INTO `menu_role_relation` VALUES (47, 17, 1, 1, 1, 1, 1, '2018-08-06 17:32:29', 'admin', '2018-08-06 17:32:37', 'admin');
INSERT INTO `menu_role_relation` VALUES (48, 6, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (49, 1, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (50, 24, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (51, 8, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (52, 22, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (53, 36, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (54, 9, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (55, 10, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (56, 2, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (57, 11, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (58, 16, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (59, 12, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (60, 20, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (61, 18, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (62, 19, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (63, 33, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (64, 14, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (65, 15, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (66, 31, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (67, 32, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (68, 3, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (69, 4, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (70, 5, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (71, 35, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (72, 30, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (73, 13, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:10', 'admin');
INSERT INTO `menu_role_relation` VALUES (74, 17, 14, 0, 0, 0, 0, '2018-08-06 17:43:10', 'admin', '2018-08-06 17:43:23', 'admin');

-- ----------------------------
-- Table structure for node
-- ----------------------------
DROP TABLE IF EXISTS `node`;
CREATE TABLE `node`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点地址',
  `link_status` int(20) NULL DEFAULT NULL COMMENT '节点链接状态(0:连接失败,1:连接成功)',
  `node_status` int(20) NULL DEFAULT NULL COMMENT '节点状态(0:停用，1:启用)',
  `src_ebrid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送方EBRID',
  `dest_ebrid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收方EBRID',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `ebd_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点属性',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of node
-- ----------------------------
INSERT INTO `node` VALUES (3, '测试节点1', '192.168.3.60:8888', NULL, 1, '23400000000000101010101', '33415000000000101010101', 'admin', '2018-06-08 17:54:00', 'admin', '2018-06-08 17:59:23', '10234000000000001010101010000000000000001');
INSERT INTO `node` VALUES (4, '测试节点2', 'localhost:8888', NULL, 1, '23400000000000101010101', '23400000000000101010101', 'admin', '2018-06-11 16:06:48', NULL, '2018-06-11 16:06:48', '23400000000000101010101');
INSERT INTO `node` VALUES (5, '测试节点3', 'localhost:8090', NULL, 0, '23400000000000101010101', '23400000000000101010101', 'admin', '2018-06-11 16:12:53', 'admin', '2018-06-13 08:43:57', '23400000000000101010101');

-- ----------------------------
-- Table structure for receive_tar
-- ----------------------------
DROP TABLE IF EXISTS `receive_tar`;
CREATE TABLE `receive_tar`  (
  `id` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '对应tar包名字中的EBDT_后面id',
  `resource_id` varchar(24) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '接收到tar包的时间',
  `status` int(10) NULL DEFAULT NULL COMMENT '状态',
  `audit_user` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `audit_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `type` int(20) NULL DEFAULT NULL,
  `audit_depar` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `audit_desc` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `resource_code` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `ebd_type` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of receive_tar
-- ----------------------------
INSERT INTO `receive_tar` VALUES ('100102510100000000012018052200000007', '', '2018-05-22 13:00:37', 1, '', NULL, 2, '', '', '010251010000000001', '0');
INSERT INTO `receive_tar` VALUES ('10234000000000001010101010000000000002285', NULL, '2018-07-23 10:03:38', 1, NULL, '2018-07-23 10:03:38', 1, NULL, NULL, '23400000000000101010101', 'OMDRequest');
INSERT INTO `receive_tar` VALUES ('10234000000000001010101010000000000002296', NULL, '2018-07-23 10:03:02', 1, NULL, '2018-07-23 10:03:02', 1, NULL, NULL, '23400000000000101010101', 'OMDRequest');
INSERT INTO `receive_tar` VALUES ('10234000000000001010101010000000000002370', NULL, '2018-07-19 13:47:55', 1, NULL, '2018-07-19 13:47:55', 1, NULL, NULL, '23400000000000101010101', 'OMDRequest');
INSERT INTO `receive_tar` VALUES ('10234000000000001010101010000000000002372', NULL, '2018-07-19 17:18:21', 1, NULL, '2018-07-19 17:18:21', 1, NULL, NULL, '23400000000000101010101', 'OMDRequest');
INSERT INTO `receive_tar` VALUES ('10234000000000001010101010000000000002386', NULL, '2018-07-19 17:21:13', 1, NULL, '2018-07-19 17:21:13', 1, NULL, NULL, '23400000000000101010101', 'OMDRequest');
INSERT INTO `receive_tar` VALUES ('10234000000000001010101010000000000002388', NULL, '2018-07-23 10:06:13', 1, NULL, '2018-07-23 10:06:13', 1, NULL, NULL, '23400000000000101010101', 'OMDRequest');
INSERT INTO `receive_tar` VALUES ('10234000000000001010101010000000000002398', NULL, '2018-07-23 10:06:50', 1, NULL, '2018-07-23 10:06:50', 1, NULL, NULL, '23400000000000101010101', 'OMDRequest');
INSERT INTO `receive_tar` VALUES ('10234000000000001010101010000000000002400', NULL, '2018-07-23 10:59:52', 1, NULL, '2018-07-23 10:59:52', 1, NULL, NULL, '23400000000000101010101', 'OMDRequest');
INSERT INTO `receive_tar` VALUES ('10234000000000001010101010000000000002454', NULL, '2018-07-23 09:42:18', 1, NULL, '2018-07-23 09:42:18', 1, NULL, NULL, '23400000000000101010101', 'OMDRequest');
INSERT INTO `receive_tar` VALUES ('10234000000000001010101010000000000002464', NULL, '2018-07-24 11:10:20', 1, NULL, '2018-07-24 11:10:20', 1, NULL, NULL, '23400000000000101010101', 'OMDRequest');
INSERT INTO `receive_tar` VALUES ('10234000000000001010101010000000000002467', NULL, '2018-07-23 09:59:19', 1, NULL, '2018-07-23 09:59:19', 1, NULL, NULL, '23400000000000101010101', 'OMDRequest');
INSERT INTO `receive_tar` VALUES ('10234000000000001010101010000000000002471', NULL, '2018-07-23 10:26:43', 1, NULL, '2018-07-23 10:26:43', 1, NULL, NULL, '23400000000000101010101', 'OMDRequest');
INSERT INTO `receive_tar` VALUES ('10234000000000001010101010000000000002476', NULL, '2018-07-18 17:21:37', 1, NULL, '2018-07-18 17:21:37', 1, NULL, NULL, '23400000000000101010101', 'EBM');
INSERT INTO `receive_tar` VALUES ('10234000000000001010101010000000000002544', NULL, '2018-07-23 10:10:31', 1, NULL, '2018-07-23 10:10:31', 1, NULL, NULL, '23400000000000101010101', 'OMDRequest');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限组名称',
  `descript` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限组描述',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `state` int(20) NULL DEFAULT NULL COMMENT '是否有效',
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色在系统中的等级 0:不能删除和修改（一般指超级管理） ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理', '超级管理拥有系统所有权限', '2018-02-02 10:38:37', 'admin', '2018-04-27 15:40:13', 'admin', NULL, NULL);
INSERT INTO `role` VALUES (2, '综合管理系统管理员', '综合管理系统管理员', '2018-02-07 17:49:36', 'admin', '2018-02-26 17:22:44', 'admin', NULL, NULL);
INSERT INTO `role` VALUES (3, '应急播发系统管理员', '应急播发系统所有权限', '2018-02-24 10:13:57', 'admin', '2018-02-26 17:24:34', 'admin', NULL, NULL);
INSERT INTO `role` VALUES (14, '测试', '测试', '2018-08-02 17:48:52', 'admin', '2018-08-02 17:57:04', 'admin', NULL, NULL);

-- ----------------------------
-- Table structure for send_tar
-- ----------------------------
DROP TABLE IF EXISTS `send_tar`;
CREATE TABLE `send_tar`  (
  `ebdid` varchar(41) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `ebd_type` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `result_code` int(20) NULL DEFAULT NULL,
  `result_desc` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `dest_id` varchar(23) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `send_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ebdid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of send_tar
-- ----------------------------
INSERT INTO `send_tar` VALUES ('10234000000000001010101010000000000002476', 'EBDResponse', NULL, NULL, '23400000000000101010101', '2018-07-18 17:21:37');
INSERT INTO `send_tar` VALUES ('10434152300000001030101012018061600000016', '1', NULL, '23400000000000101010101', '12', '2018-06-16 12:48:53');
INSERT INTO `send_tar` VALUES ('10444510300000001030101012018072600000011', 'EBRASState', NULL, NULL, '23415230000000101010101', '2018-07-26 09:55:08');
INSERT INTO `send_tar` VALUES ('10444510300000001030101012018072600000012', 'EBRPSInfo', NULL, NULL, '23415230000000101010101', '2018-07-26 09:57:27');
INSERT INTO `send_tar` VALUES ('10444510300000001030101012018073000000001', 'EBRASState', NULL, NULL, '23415230000000101010101', '2018-07-30 17:11:14');
INSERT INTO `send_tar` VALUES ('10444510300000001030101012018080700000001', 'EBRPSInfo', NULL, NULL, '23415230000000101010101', '2018-08-07 17:01:34');

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'cron表达式',
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务调用的方法名',
  `is_concurrent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务是否有状态',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务描述',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `bean_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `job_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务状态',
  `job_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务分组',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `spring_bean` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Spring bean',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES (2, '0/10 * * * * ?', 'run1', '1', '', '4028ea815a3d2a8c015a3d2f8d2a0002', 'com.bootdo.common.task.WelcomeJob', '2017-05-19 18:30:56', '0', 'group1', '2017-05-19 18:31:07', NULL, '', 'welcomJob');
INSERT INTO `task` VALUES (8, '0/10 * * * * ?', 'run1', '1', '自动播发信息任务', NULL, 'com.gospell.chitong.rdcenter.broadcast.commonManage.task.SendJob', '2018-07-18 15:18:03', '1', 'group1', '2018-07-18 15:18:03', NULL, NULL, 'sendJob');
INSERT INTO `task` VALUES (9, '0/5 * * * * ?', 'run2', '1', '心跳检测任务', NULL, 'com.gospell.chitong.rdcenter.broadcast.commonManage.task.HeartJob', '2018-07-18 15:18:03', '1', 'group1', '2018-07-18 15:18:03', NULL, NULL, 'heartJob');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `user_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户编号',
  `sex` int(5) NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `area_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户区域编号',
  `area_code_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户区域级别',
  `area_code_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户区域名称',
  `role_id` int(20) NULL DEFAULT NULL COMMENT '角色id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建人',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'd0af8fa1272ef5a152d9e27763eea293', 'e10adc3949ba59abbe56e057f20f883e', 1, '15828411534', '445103000000', '3', '潮安市', 1, '2018-03-01 09:12:04', 'admin', '2018-06-05 14:47:22', 'admin');
INSERT INTO `user` VALUES (5, 'admin2', '32d21517993898397', 'e10adc3949ba59abbe56e057f20f883e', 1, '15828411534', '360702001000', '4', '解放街道办事处', 3, '2018-02-26 17:25:13', 'admin', '2018-06-14 09:03:35', 'admin');
INSERT INTO `user` VALUES (7, 'admin3', '83b21519630261228', 'fcea920f7412b5da7be0cf42b8c93759', 1, '15828411534', '360702000000', '3', '章贡区', 3, '2018-02-26 17:25:20', 'admin', '2018-02-26 15:31:01', 'admin');
INSERT INTO `user` VALUES (9, 'test', '25bb7c42604b0e7aba7bcae50e7762a9', NULL, 1, '13792468501', '郫都区', NULL, NULL, 14, '2018-08-20 08:49:14', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user_log
-- ----------------------------
DROP TABLE IF EXISTS `user_log`;
CREATE TABLE `user_log`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `clientIp` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '客户端ip',
  `urlModule` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '模块',
  `urlFunction` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '功能',
  `des` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '业务数据',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建(访问)时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '请求URL',
  `user_id` int(20) NULL DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户角色名',
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 204 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_log
-- ----------------------------
INSERT INTO `user_log` VALUES (181, '127.0.0.1', NULL, '删除日志', '', '2018-09-04 17:29:42', '2018-09-04 17:29:42', '/sysLogAction/delete', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (182, '127.0.0.1', NULL, '用户退出', '', '2018-09-04 17:35:58', '2018-09-04 17:35:58', '/userAction/logout', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (183, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 17:36:00', '2018-09-04 17:36:00', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (184, '127.0.0.1', NULL, '发送应急信息', '156', '2018-09-04 17:43:06', '2018-09-04 17:43:06', '/backCommunicationAction/sendEmer', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (185, '192.168.3.179', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 17:57:26', '2018-09-04 17:57:26', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (186, '192.168.3.179', NULL, '发送应急信息', '156', '2018-09-04 17:57:49', '2018-09-04 17:57:49', '/backCommunicationAction/sendEmer', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (187, '192.168.3.179', NULL, '应急信息保存', '{\"accidentlevelId\":2,\"accidenttypeId\":1,\"addresscode\":\"445103100000;\",\"addresscodename\":\"广东省;潮州市;潮安区;\",\"areacode\":\"445103000000\",\"content\":\"\",\"displaylanguageId\":1,\"displaymethodId\":1,\"duration\":\"60\",\"emergencycode\":\"58365180042\",\"emergencyname\":\"1111333\",\"endTime\":1536058792000,\"flag\":1,\"id\":158,\"infosourceId\":1,\"programId\":1,\"programdescription\":\"IP通道\",\"sound\":\"60\",\"startTime\":1536055192000,\"status\":2,\"unitname\":\"潮安区\",\"updateBy\":\"admin\"}', '2018-09-04 18:00:43', '2018-09-04 18:00:43', '/emergencyInfoAction/save', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (188, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-05 16:25:55', '2018-09-05 16:25:55', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (189, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-05 16:31:10', '2018-09-05 16:31:10', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (190, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-05 17:01:11', '2018-09-05 17:01:11', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (191, '127.0.0.1', NULL, '用户退出', '', '2018-09-05 17:01:15', '2018-09-05 17:01:15', '/userAction/logout', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (192, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-05 17:01:17', '2018-09-05 17:01:17', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (193, '127.0.0.1', NULL, '用户退出', '', '2018-09-05 17:01:24', '2018-09-05 17:01:24', '/userAction/logout', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (194, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-05 17:01:27', '2018-09-05 17:01:27', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (195, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-05 17:03:53', '2018-09-05 17:03:53', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (196, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-05 17:20:37', '2018-09-05 17:20:37', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (197, '127.0.0.1', NULL, '用户退出', '', '2018-09-05 17:20:45', '2018-09-05 17:20:45', '/userAction/logout', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (198, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-05 17:20:47', '2018-09-05 17:20:47', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (199, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-05 17:21:09', '2018-09-05 17:21:09', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (200, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-05 17:21:09', '2018-09-05 17:21:09', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (201, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-05 17:24:22', '2018-09-05 17:24:22', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (202, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-06 11:34:14', '2018-09-06 11:34:14', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (203, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-07 14:54:03', '2018-09-07 14:54:03', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (49, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-08-31 09:07:00', '2018-08-31 09:07:00', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (50, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-08-31 11:54:27', '2018-08-31 11:54:27', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (51, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-08-31 13:42:28', '2018-08-31 13:42:28', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (52, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-08-31 16:14:50', '2018-08-31 16:14:50', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (53, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-08-31 17:22:28', '2018-08-31 17:22:28', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (54, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-08-31 17:26:32', '2018-08-31 17:26:32', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (55, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 09:01:45', '2018-09-03 09:01:45', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (56, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 09:24:56', '2018-09-03 09:24:56', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (57, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 09:25:35', '2018-09-03 09:25:35', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (58, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 09:26:45', '2018-09-03 09:26:45', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (59, '127.0.0.1', NULL, '用户退出', '', '2018-09-03 09:29:17', '2018-09-03 09:29:17', '/userAction/logout', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (60, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 09:29:20', '2018-09-03 09:29:20', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (61, '127.0.0.1', NULL, '用户退出', '', '2018-09-03 09:31:26', '2018-09-03 09:31:26', '/userAction/logout', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (62, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 09:31:29', '2018-09-03 09:31:29', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (63, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 09:32:22', '2018-09-03 09:32:22', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (64, '127.0.0.1', NULL, '用户退出', '', '2018-09-03 09:53:57', '2018-09-03 09:53:57', '/userAction/logout', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (65, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 09:53:59', '2018-09-03 09:53:59', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (66, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 09:54:14', '2018-09-03 09:54:14', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (67, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 09:54:32', '2018-09-03 09:54:32', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (68, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 09:56:32', '2018-09-03 09:56:32', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (69, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 10:06:08', '2018-09-03 10:06:08', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (70, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 10:06:57', '2018-09-03 10:06:57', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (71, '127.0.0.1', NULL, '用户退出', '', '2018-09-03 10:07:04', '2018-09-03 10:07:04', '/userAction/logout', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (72, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 10:07:06', '2018-09-03 10:07:06', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (73, '127.0.0.1', NULL, '用户退出', '', '2018-09-03 10:07:18', '2018-09-03 10:07:18', '/userAction/logout', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (74, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 10:20:53', '2018-09-03 10:20:53', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (75, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 10:21:09', '2018-09-03 10:21:09', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (76, '127.0.0.1', NULL, '用户退出', '', '2018-09-03 10:21:15', '2018-09-03 10:21:15', '/userAction/logout', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (77, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 10:21:18', '2018-09-03 10:21:18', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (78, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 10:22:47', '2018-09-03 10:22:47', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (79, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 10:26:44', '2018-09-03 10:26:44', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (80, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 10:28:12', '2018-09-03 10:28:12', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (81, '127.0.0.1', NULL, '用户退出', '', '2018-09-03 10:28:20', '2018-09-03 10:28:20', '/userAction/logout', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (82, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 10:28:23', '2018-09-03 10:28:23', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (83, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 10:29:13', '2018-09-03 10:29:13', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (84, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 10:34:05', '2018-09-03 10:34:05', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (85, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 11:00:40', '2018-09-03 11:00:40', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (86, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 11:39:45', '2018-09-03 11:39:45', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (87, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 13:49:12', '2018-09-03 13:49:12', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (88, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 15:01:53', '2018-09-03 15:01:53', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (89, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 16:14:56', '2018-09-03 16:14:56', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (90, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-03 17:42:00', '2018-09-03 17:42:00', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (91, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 09:19:39', '2018-09-04 09:19:39', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (92, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 09:34:04', '2018-09-04 09:34:04', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (93, '127.0.0.1', NULL, '用户退出', '', '2018-09-04 09:34:21', '2018-09-04 09:34:21', '/userAction/logout', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (94, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 09:34:23', '2018-09-04 09:34:23', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (95, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 11:05:15', '2018-09-04 11:05:15', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (96, '192.168.3.179', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:07:32', '2018-09-04 14:07:32', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (97, '192.168.3.179', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:07:53', '2018-09-04 14:07:53', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (98, '192.168.3.179', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:07:58', '2018-09-04 14:07:58', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (99, '192.168.3.179', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:08:23', '2018-09-04 14:08:23', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (100, '192.168.3.179', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:09:18', '2018-09-04 14:09:18', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (101, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:13:56', '2018-09-04 14:13:56', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (102, '192.168.3.179', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:14:42', '2018-09-04 14:14:42', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (103, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:28:53', '2018-09-04 14:28:53', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (104, '127.0.0.1', NULL, '用户退出', '', '2018-09-04 14:30:41', '2018-09-04 14:30:41', '/userAction/logout', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (105, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:30:42', '2018-09-04 14:30:42', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (106, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:30:48', '2018-09-04 14:30:48', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (107, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:30:59', '2018-09-04 14:30:59', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (108, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:32:00', '2018-09-04 14:32:00', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (109, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:33:07', '2018-09-04 14:33:07', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (110, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:33:42', '2018-09-04 14:33:42', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (111, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:35:29', '2018-09-04 14:35:29', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (112, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:35:50', '2018-09-04 14:35:50', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (113, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:36:12', '2018-09-04 14:36:12', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (114, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:36:52', '2018-09-04 14:36:52', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (115, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:37:01', '2018-09-04 14:37:01', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (116, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:37:56', '2018-09-04 14:37:56', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (117, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:38:21', '2018-09-04 14:38:21', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (118, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:38:53', '2018-09-04 14:38:53', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (119, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:39:38', '2018-09-04 14:39:38', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (120, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:40:01', '2018-09-04 14:40:01', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (121, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:40:26', '2018-09-04 14:40:26', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (122, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:52:48', '2018-09-04 14:52:48', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (123, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 14:52:57', '2018-09-04 14:52:57', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (124, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"12345\"}', '2018-09-04 14:54:45', '2018-09-04 14:54:45', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (125, '127.0.0.1', NULL, '用户登录', NULL, '2018-09-04 16:09:16', '2018-09-04 16:09:16', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (126, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 16:14:27', '2018-09-04 16:14:27', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (127, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 16:14:44', '2018-09-04 16:14:44', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (128, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 16:18:46', '2018-09-04 16:18:46', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (129, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 16:18:53', '2018-09-04 16:18:53', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (130, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 16:19:11', '2018-09-04 16:19:11', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (131, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 16:39:30', '2018-09-04 16:39:30', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (132, '127.0.0.1', NULL, '用户退出', '', '2018-09-04 16:39:35', '2018-09-04 16:39:35', '/userAction/logout', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (133, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"1234561\"}', '2018-09-04 16:39:39', '2018-09-04 16:39:39', '/userAction/login', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (134, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"1234561\"}', '2018-09-04 16:40:15', '2018-09-04 16:40:15', '/userAction/login', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (135, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"1234561\"}', '2018-09-04 16:41:02', '2018-09-04 16:41:02', '/userAction/login', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (136, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"12345611\"}', '2018-09-04 16:41:07', '2018-09-04 16:41:07', '/userAction/login', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (137, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"12345611\"}', '2018-09-04 16:42:02', '2018-09-04 16:42:02', '/userAction/login', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (138, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"12345611\"}', '2018-09-04 16:42:23', '2018-09-04 16:42:23', '/userAction/login', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (139, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"\"}', '2018-09-04 16:43:19', '2018-09-04 16:43:19', '/userAction/login', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (140, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"1234561\"}', '2018-09-04 16:44:12', '2018-09-04 16:44:12', '/userAction/login', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (141, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 16:45:40', '2018-09-04 16:45:40', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (142, '127.0.0.1', NULL, '用户退出', '', '2018-09-04 16:45:51', '2018-09-04 16:45:51', '/userAction/logout', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (143, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 16:45:58', '2018-09-04 16:45:58', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (144, '192.168.3.179', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 16:50:51', '2018-09-04 16:50:51', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (145, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 16:51:09', '2018-09-04 16:51:09', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (146, '127.0.0.1', NULL, '用户退出', '', '2018-09-04 16:51:13', '2018-09-04 16:51:13', '/userAction/logout', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (147, '192.168.3.179', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 16:52:03', '2018-09-04 16:52:03', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (148, '192.168.3.179', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 16:52:09', '2018-09-04 16:52:09', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (149, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"1234562\"}', '2018-09-04 16:53:23', '2018-09-04 16:53:23', '/userAction/login', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (150, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"1234566\"}', '2018-09-04 16:54:06', '2018-09-04 16:54:06', '/userAction/login', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (151, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 16:54:13', '2018-09-04 16:54:13', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (152, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"1234566\"}', '2018-09-04 16:54:19', '2018-09-04 16:54:19', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (153, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"1234566\"}', '2018-09-04 16:55:10', '2018-09-04 16:55:10', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (154, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 16:58:48', '2018-09-04 16:58:48', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (155, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 16:58:51', '2018-09-04 16:58:51', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (156, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 16:58:52', '2018-09-04 16:58:52', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (157, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 16:59:18', '2018-09-04 16:59:18', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (158, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 16:59:42', '2018-09-04 16:59:42', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (159, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 16:59:43', '2018-09-04 16:59:43', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (160, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 16:59:44', '2018-09-04 16:59:44', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (161, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 17:00:30', '2018-09-04 17:00:30', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (162, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"1234566\"}', '2018-09-04 17:04:37', '2018-09-04 17:04:37', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (163, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 17:04:39', '2018-09-04 17:04:39', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (164, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 17:05:04', '2018-09-04 17:05:04', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (165, '127.0.0.1', NULL, '用户退出', '', '2018-09-04 17:05:09', '2018-09-04 17:05:09', '/userAction/logout', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (166, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 17:08:26', '2018-09-04 17:08:26', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (167, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 17:08:34', '2018-09-04 17:08:34', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (168, '127.0.0.1', NULL, '用户退出', '', '2018-09-04 17:08:39', '2018-09-04 17:08:39', '/userAction/logout', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (169, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"1234565\"}', '2018-09-04 17:08:42', '2018-09-04 17:08:42', '/userAction/login', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (170, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin0\",\"password\":\"123456\"}', '2018-09-04 17:08:46', '2018-09-04 17:08:46', '/userAction/login', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (171, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 17:10:00', '2018-09-04 17:10:00', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (172, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 17:10:06', '2018-09-04 17:10:06', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (173, '192.168.3.179', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 17:10:42', '2018-09-04 17:10:42', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (174, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 17:23:58', '2018-09-04 17:23:58', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (175, '192.168.3.179', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 17:24:01', '2018-09-04 17:24:01', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (176, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 17:24:03', '2018-09-04 17:24:03', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (177, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 17:24:07', '2018-09-04 17:24:07', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (178, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 17:24:12', '2018-09-04 17:24:12', '/userAction/login', 1, 'admin', '超级管理', '1');
INSERT INTO `user_log` VALUES (179, '127.0.0.1', NULL, '用户退出', '', '2018-09-04 17:24:17', '2018-09-04 17:24:17', '/userAction/logout', -1, '获取用户信息为空', NULL, NULL);
INSERT INTO `user_log` VALUES (180, '127.0.0.1', NULL, '用户登录', '{\"name\":\"admin\",\"password\":\"123456\"}', '2018-09-04 17:24:20', '2018-09-04 17:24:20', '/userAction/login', 1, 'admin', '超级管理', '1');

SET FOREIGN_KEY_CHECKS = 1;
