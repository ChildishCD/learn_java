/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80029 (8.0.29)
 Source Host           : 127.0.0.1:3306
 Source Schema         : supermarket

 Target Server Type    : MySQL
 Target Server Version : 80029 (8.0.29)
 File Encoding         : 65001

 Date: 13/01/2023 17:30:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sm_goods
-- ----------------------------
DROP TABLE IF EXISTS `sm_goods`;
CREATE TABLE `sm_goods`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `goods_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `goods_type` int NOT NULL COMMENT '商品类型',
  `price` double NOT NULL COMMENT '商品价格',
  `inventory` int NOT NULL COMMENT '库存',
  `state` tinyint NULL DEFAULT 1 COMMENT '1正常2下架3删除',
  `discount` int NULL DEFAULT 10 COMMENT '折扣',
  `is_del` int NULL DEFAULT 0 COMMENT '是否删除  0否 1是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sm_goods
-- ----------------------------
INSERT INTO `sm_goods` VALUES (1, '薯条2333', 9, 10, 990, 3, 10, 1);
INSERT INTO `sm_goods` VALUES (2, '辣条', 1, 222, 133, 1, 1, 0);
INSERT INTO `sm_goods` VALUES (3, '开心果', 10, 32, 3960, 2, 10, 0);
INSERT INTO `sm_goods` VALUES (4, '瓜子', 10, 10, 400, 1, 8, 0);
INSERT INTO `sm_goods` VALUES (6, '车厘子', 2, 30, 20000, 2, 10, 0);
INSERT INTO `sm_goods` VALUES (7, '山东大樱桃', 2, 20, 30000, 1, 10, 0);
INSERT INTO `sm_goods` VALUES (8, '泰国榴莲', 2, 40, 10, 2, 10, 0);
INSERT INTO `sm_goods` VALUES (9, '广东山竹', 2, 53, 340, 1, 10, 0);
INSERT INTO `sm_goods` VALUES (10, '新加坡的波罗蜜', 2, 6, 400, 1, 10, 0);
INSERT INTO `sm_goods` VALUES (11, '巧克力', 1, 12, 88, 1, 9, 0);
INSERT INTO `sm_goods` VALUES (12, '橘子', 2, 2, 99, 1, 10, 0);
INSERT INTO `sm_goods` VALUES (13, '面包', 2, 1, 9, 1, 9, NULL);
INSERT INTO `sm_goods` VALUES (14, '新数据', 1, 12, 22, 1, 10, NULL);

-- ----------------------------
-- Table structure for sm_goods_type
-- ----------------------------
DROP TABLE IF EXISTS `sm_goods_type`;
CREATE TABLE `sm_goods_type`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '商品类型ID',
  `pid` int NULL DEFAULT 0 COMMENT '父id，0表示一级目录',
  `type_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sm_goods_type
-- ----------------------------
INSERT INTO `sm_goods_type` VALUES (1, 0, '测试', '2023-01-12 19:32:30', NULL);
INSERT INTO `sm_goods_type` VALUES (2, 0, '水果', '2023-01-12 18:10:42', NULL);
INSERT INTO `sm_goods_type` VALUES (3, 0, '服装', '2023-01-12 18:10:42', NULL);
INSERT INTO `sm_goods_type` VALUES (4, 0, '海鲜', '2023-01-12 18:10:42', NULL);
INSERT INTO `sm_goods_type` VALUES (5, 0, '酒水', '2023-01-12 18:10:42', NULL);
INSERT INTO `sm_goods_type` VALUES (6, 0, '日用品', '2023-01-12 18:10:42', NULL);
INSERT INTO `sm_goods_type` VALUES (7, 0, '蔬菜', '2023-01-12 18:10:42', NULL);
INSERT INTO `sm_goods_type` VALUES (8, 0, '化妆品', '2023-01-12 18:10:42', NULL);
INSERT INTO `sm_goods_type` VALUES (9, 1, '膨化食品', '2023-01-12 18:10:42', NULL);
INSERT INTO `sm_goods_type` VALUES (10, 1, '坚果', '2023-01-12 18:10:42', NULL);
INSERT INTO `sm_goods_type` VALUES (11, 1, '饼干', '2023-01-12 18:10:42', NULL);
INSERT INTO `sm_goods_type` VALUES (12, 2, '草莓', '2023-01-12 18:10:42', NULL);
INSERT INTO `sm_goods_type` VALUES (13, 2, '车厘子', '2023-01-12 18:10:42', NULL);
INSERT INTO `sm_goods_type` VALUES (15, 3, 'test', '2023-01-12 19:33:10', '2023-01-12 19:33:29');

-- ----------------------------
-- Table structure for sm_member
-- ----------------------------
DROP TABLE IF EXISTS `sm_member`;
CREATE TABLE `sm_member`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '会员id',
  `member_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会员名称',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `contact` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `integral` bigint NULL DEFAULT 0 COMMENT '积分',
  `balance` bigint NULL DEFAULT 0 COMMENT '余额（分）',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1004 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sm_member
-- ----------------------------
INSERT INTO `sm_member` VALUES (1001, 'zhangsan', '123', '888', 0, 85080, '2023-01-12 21:46:53', NULL);
INSERT INTO `sm_member` VALUES (1002, 'lisi', '444', '444', 0, 900, '2023-01-12 21:50:47', NULL);
INSERT INTO `sm_member` VALUES (1003, 'hanmeimei', 'mei', '666', 0, 0, '2023-01-12 21:50:59', '2023-01-12 21:51:49');

-- ----------------------------
-- Table structure for sm_menu
-- ----------------------------
DROP TABLE IF EXISTS `sm_menu`;
CREATE TABLE `sm_menu`  (
  `mid` int NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名',
  `pid` int NULL DEFAULT NULL COMMENT '父id',
  `show` int NULL DEFAULT 1 COMMENT '是否显示在导航,1显示,2不显示',
  PRIMARY KEY (`mid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据分析菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sm_menu
-- ----------------------------
INSERT INTO `sm_menu` VALUES (10, '商品类型管理', 0, 1);
INSERT INTO `sm_menu` VALUES (11, '商品管理', 0, 1);
INSERT INTO `sm_menu` VALUES (12, '会员管理', 0, 1);
INSERT INTO `sm_menu` VALUES (13, '购买管理', 0, 1);
INSERT INTO `sm_menu` VALUES (14, '订单查询', 0, 1);
INSERT INTO `sm_menu` VALUES (15, '排行统计', 0, 1);
INSERT INTO `sm_menu` VALUES (1001, '添加商品类型信息', 10, 1);
INSERT INTO `sm_menu` VALUES (1002, '修改商品类型信息', 10, 1);
INSERT INTO `sm_menu` VALUES (1003, '查询商品类型信息', 10, 1);
INSERT INTO `sm_menu` VALUES (1004, '删除商品类型信息', 10, 1);
INSERT INTO `sm_menu` VALUES (1101, '添加商品信息', 11, 1);
INSERT INTO `sm_menu` VALUES (1102, '修改商品信息', 11, 1);
INSERT INTO `sm_menu` VALUES (1103, '查询商品信息', 11, 1);
INSERT INTO `sm_menu` VALUES (1104, '删除商品信息', 11, 1);
INSERT INTO `sm_menu` VALUES (1201, '添加会员信息', 12, 1);
INSERT INTO `sm_menu` VALUES (1202, '修改会员信息', 12, 1);
INSERT INTO `sm_menu` VALUES (1203, '查询会员信息', 12, 1);
INSERT INTO `sm_menu` VALUES (1204, '删除会员信息', 12, 1);
INSERT INTO `sm_menu` VALUES (1205, '会员余额充值', 12, 1);

-- ----------------------------
-- Table structure for sm_order
-- ----------------------------
DROP TABLE IF EXISTS `sm_order`;
CREATE TABLE `sm_order`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL DEFAULT 0,
  `rental` bigint NOT NULL DEFAULT 0 COMMENT '总额（分）',
  `create_time` datetime NOT NULL COMMENT '下单时间',
  `pay_type` tinyint NOT NULL DEFAULT 0 COMMENT '0现金1余额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sm_order
-- ----------------------------
INSERT INTO `sm_order` VALUES (6, 1001, 3220, '2023-01-13 01:05:38', 1);
INSERT INTO `sm_order` VALUES (7, 1001, 3200, '2023-01-13 01:08:25', 1);
INSERT INTO `sm_order` VALUES (8, 0, 5900, '2023-01-13 01:33:48', 0);
INSERT INTO `sm_order` VALUES (9, 1001, 2080, '2023-01-13 01:37:41', 1);
INSERT INTO `sm_order` VALUES (10, 1001, 7420, '2023-01-13 14:49:17', 1);
INSERT INTO `sm_order` VALUES (11, 0, 5420, '2023-01-13 14:50:05', 0);

-- ----------------------------
-- Table structure for sm_order_info
-- ----------------------------
DROP TABLE IF EXISTS `sm_order_info`;
CREATE TABLE `sm_order_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL COMMENT '订单id',
  `goods_id` int NOT NULL COMMENT '商品id',
  `goods_num` int NOT NULL COMMENT '购买商品数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sm_order_info
-- ----------------------------
INSERT INTO `sm_order_info` VALUES (8, 8, 9, 5);
INSERT INTO `sm_order_info` VALUES (9, 8, 9, 5);
INSERT INTO `sm_order_info` VALUES (10, 9, 4, 33);
INSERT INTO `sm_order_info` VALUES (11, 9, 4, 33);
INSERT INTO `sm_order_info` VALUES (12, 9, 4, 33);
INSERT INTO `sm_order_info` VALUES (13, 10, 2, 10);
INSERT INTO `sm_order_info` VALUES (14, 10, 2, 10);
INSERT INTO `sm_order_info` VALUES (15, 10, 2, 10);
INSERT INTO `sm_order_info` VALUES (16, 11, 10, 10);
INSERT INTO `sm_order_info` VALUES (17, 11, 2, 10);

-- ----------------------------
-- Table structure for sm_rel_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sm_rel_role_menu`;
CREATE TABLE `sm_rel_role_menu`  (
  `role_id` int NOT NULL,
  `menu_id` int NOT NULL,
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE,
  INDEX `menu_id`(`menu_id` ASC) USING BTREE,
  CONSTRAINT `sm_rel_role_menu_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sm_role` (`rid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sm_rel_role_menu_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `sm_menu` (`mid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sm_rel_role_menu
-- ----------------------------
INSERT INTO `sm_rel_role_menu` VALUES (1, 10);
INSERT INTO `sm_rel_role_menu` VALUES (2, 10);
INSERT INTO `sm_rel_role_menu` VALUES (1, 11);
INSERT INTO `sm_rel_role_menu` VALUES (2, 11);
INSERT INTO `sm_rel_role_menu` VALUES (1, 12);
INSERT INTO `sm_rel_role_menu` VALUES (2, 12);
INSERT INTO `sm_rel_role_menu` VALUES (1, 13);
INSERT INTO `sm_rel_role_menu` VALUES (3, 13);
INSERT INTO `sm_rel_role_menu` VALUES (1, 14);
INSERT INTO `sm_rel_role_menu` VALUES (3, 14);
INSERT INTO `sm_rel_role_menu` VALUES (1, 15);
INSERT INTO `sm_rel_role_menu` VALUES (3, 15);

-- ----------------------------
-- Table structure for sm_role
-- ----------------------------
DROP TABLE IF EXISTS `sm_role`;
CREATE TABLE `sm_role`  (
  `rid` int NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sm_role
-- ----------------------------
INSERT INTO `sm_role` VALUES (1, '超级管理员', '最大权限');
INSERT INTO `sm_role` VALUES (2, '管理员', '添加数据');
INSERT INTO `sm_role` VALUES (3, '收银员', '购买相关操作');

-- ----------------------------
-- Table structure for sm_user
-- ----------------------------
DROP TABLE IF EXISTS `sm_user`;
CREATE TABLE `sm_user`  (
  `uid` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  `reg_time` datetime NULL DEFAULT NULL COMMENT '注册时间',
  `login_time` datetime NULL DEFAULT NULL COMMENT '登录时间',
  `isvalid` int NULL DEFAULT 1 COMMENT '=1有效 =0无效',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sm_user
-- ----------------------------
INSERT INTO `sm_user` VALUES (1, 'root', 'root', 1, '2023-01-04 15:38:19', '2023-01-05 19:06:24', 1);
INSERT INTO `sm_user` VALUES (2, 'admin', 'admin', 2, '2023-01-04 10:03:52', '2023-01-06 09:57:48', 1);
INSERT INTO `sm_user` VALUES (3, 'xiaoming', 'xiaoming', 3, '2023-01-04 15:42:42', '2023-01-07 17:13:59', 1);

SET FOREIGN_KEY_CHECKS = 1;
