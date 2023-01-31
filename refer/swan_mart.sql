/*
 Navicat Premium Data Transfer

 Source Server         : LocalMySQL
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : swan_mart

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 30/01/2023 17:56:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for member_orders
-- ----------------------------
DROP TABLE IF EXISTS `member_orders`;
CREATE TABLE `member_orders`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `member_id` int(0) NOT NULL COMMENT '会员ID',
  `price_total` decimal(10, 2) NOT NULL COMMENT '总金额',
  `create_time` datetime(0) NOT NULL COMMENT '下单时间',
  `payment_type` tinyint(0) NOT NULL COMMENT '支付类型（0-现金，1-余额）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10025 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member_orders
-- ----------------------------
INSERT INTO `member_orders` VALUES (10025, 1101, 87.50, '2023-01-01 12:08:49', 0);
INSERT INTO `member_orders` VALUES (10030, 1001, 155.00, '2023-01-30 16:30:27', 1);
INSERT INTO `member_orders` VALUES (10031, 1, 31.00, '2023-01-30 17:15:52', 0);
INSERT INTO `member_orders` VALUES (10032, 1001, 31.00, '2023-01-30 17:16:09', 1);
INSERT INTO `member_orders` VALUES (10033, 1, 62.00, '2023-01-30 17:17:23', 0);
INSERT INTO `member_orders` VALUES (10034, 1, 100.80, '2023-01-30 17:18:30', 0);
INSERT INTO `member_orders` VALUES (10035, 1, 43.60, '2023-01-30 17:32:29', 0);

-- ----------------------------
-- Table structure for member_orders_products
-- ----------------------------
DROP TABLE IF EXISTS `member_orders_products`;
CREATE TABLE `member_orders_products`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '订单详情ID',
  `order_id` int(0) NOT NULL COMMENT '订单ID',
  `product_id` int(0) NOT NULL COMMENT '商品ID',
  `count` int(0) NOT NULL COMMENT '商品数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member_orders_products
-- ----------------------------
INSERT INTO `member_orders_products` VALUES (1, 10025, 2026, 2);
INSERT INTO `member_orders_products` VALUES (2, 10025, 2035, 1);
INSERT INTO `member_orders_products` VALUES (3, 10030, 2026, 5);
INSERT INTO `member_orders_products` VALUES (4, 10033, 2026, 2);
INSERT INTO `member_orders_products` VALUES (5, 10034, 2025, 8);
INSERT INTO `member_orders_products` VALUES (6, 10035, 2025, 1);
INSERT INTO `member_orders_products` VALUES (7, 10035, 2026, 1);

-- ----------------------------
-- Table structure for members
-- ----------------------------
DROP TABLE IF EXISTS `members`;
CREATE TABLE `members`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '会员编号',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '会员名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `contact` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '练习方式',
  `points` float(10, 2) NOT NULL DEFAULT 0.00 COMMENT '积分',
  `balance` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '余额',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1004 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of members
-- ----------------------------
INSERT INTO `members` VALUES (1, '游客', '1', '1', 0.00, 100.00, '1910-01-01 00:00:00', '2023-01-30 17:28:25');
INSERT INTO `members` VALUES (1001, '王先生', '123123', '18181111212', 2.00, 125.00, '2022-12-06 11:17:42', '2023-01-30 16:30:27');
INSERT INTO `members` VALUES (1002, '汝女士', 'abc456789', '13254698752', 10000.00, 0.50, '2022-01-06 11:18:50', '2022-07-15 11:18:54');
INSERT INTO `members` VALUES (1003, '小猪aab', 'pighahaha', 'pighappy@163.com', 200.00, 6.80, '2022-03-02 11:20:27', '2022-10-12 11:20:33');
INSERT INTO `members` VALUES (1004, '英勇的篦子', 'braveMe', '025-5555689', 10.00, 31.40, '2022-11-08 11:23:42', '2022-12-30 11:23:48');

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品名',
  `type_id` int(0) NOT NULL COMMENT '商品所属类型id',
  `inventory` int(0) NOT NULL COMMENT '商品库存数量',
  `price` float NOT NULL COMMENT '单价',
  `state` tinyint(0) NOT NULL COMMENT '商品状态（ 1-正常，2-下架，3-删除）',
  `discount` float(5, 2) UNSIGNED ZEROFILL NOT NULL DEFAULT 00.90 COMMENT '折扣（默认是0.9）',
  `specs` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品规格',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2058 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES (2025, '白菜', 1101, 191, 14, 1, 00.90, '500g');
INSERT INTO `products` VALUES (2026, '萝卜', 1101, 209, 31, 1, 01.00, '500g');
INSERT INTO `products` VALUES (2027, '苹果', 1102, 356, 1503, 1, 00.85, '500g');
INSERT INTO `products` VALUES (2028, '香蕉', 1102, 23, 151.5, 1, 00.75, '500g');
INSERT INTO `products` VALUES (2030, '猪里脊', 1202, 33, 32.1, 1, 00.90, '500g');
INSERT INTO `products` VALUES (2031, '猪头', 1202, 10, 22, 1, 00.85, '500g');
INSERT INTO `products` VALUES (2032, '精选五花', 1202, 12, 2151, 1, 00.75, '500g');
INSERT INTO `products` VALUES (2034, 'A5日本和牛', 1202, 0, 15122, 2, 00.95, '500g');
INSERT INTO `products` VALUES (2035, '羊腰子', 1202, 2, 25.5, 1, 01.00, '500g');
INSERT INTO `products` VALUES (2037, '鸭脖', 1201, 2, 12.5, 1, 01.00, '500g');
INSERT INTO `products` VALUES (2038, '鸡腿', 1201, 25, 12, 1, 01.00, '500g');
INSERT INTO `products` VALUES (2039, '鹅蛋', 1201, 26, 51.99, 1, 01.00, '10g');
INSERT INTO `products` VALUES (2040, '鸡蛋', 1201, 351, 1, 1, 00.95, '10g');
INSERT INTO `products` VALUES (2042, '鱿鱼', 1302, 815, 85, 1, 00.70, '50g');
INSERT INTO `products` VALUES (2043, '鲫鱼', 1301, 2852, 12.65, 1, 00.85, '1kg');
INSERT INTO `products` VALUES (2044, '草鱼', 1301, 185, 15, 1, 00.65, '1kg');
INSERT INTO `products` VALUES (2045, '冷冻蛤蜊肉_冬阴功风味', 1303, 22, 2.22, 1, 00.50, '280g');
INSERT INTO `products` VALUES (2046, '海带', 1103, 255, 1.11, 1, 01.00, '50g');
INSERT INTO `products` VALUES (2048, '金龙鱼菜籽油', 1104, 23, 200, 1, 01.00, '1L');
INSERT INTO `products` VALUES (2049, '福临门泰国香米', 1104, 65, 652, 1, 01.00, '2kg');
INSERT INTO `products` VALUES (2051, '思念韭菜猪肉水饺', 1502, 354, 20, 1, 01.00, '555g');
INSERT INTO `products` VALUES (2052, '甲天下黑芝麻汤圆', 1502, 6554, 53, 1, 01.00, '380g');
INSERT INTO `products` VALUES (2054, '灭火器', 17, 0, 9999.99, 3, 00.20, '1瓶');
INSERT INTO `products` VALUES (2055, '胡椒粉', 1702, 25, 541, 1, 01.00, '20g');
INSERT INTO `products` VALUES (2056, '辣椒油', 1702, 235, 322, 1, 00.95, '100g');
INSERT INTO `products` VALUES (2057, '高压锅', 1701, 6, 888.88, 1, 00.80, '1个');
INSERT INTO `products` VALUES (2058, '蒜', 1101, 500, 5, 1, 00.90, '50g');

-- ----------------------------
-- Table structure for products_types
-- ----------------------------
DROP TABLE IF EXISTS `products_types`;
CREATE TABLE `products_types`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '商品类型ID',
  `pid` int(4) UNSIGNED ZEROFILL NOT NULL COMMENT '父级商品类型ID',
  `isparent` tinyint(1) UNSIGNED ZEROFILL NOT NULL COMMENT '是否为父类型（1为是，0为否）',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品类型名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1702 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products_types
-- ----------------------------
INSERT INTO `products_types` VALUES (11, 0000, 1, '蔬菜');
INSERT INTO `products_types` VALUES (12, 0000, 1, '精肉');
INSERT INTO `products_types` VALUES (13, 0000, 1, '水产');
INSERT INTO `products_types` VALUES (14, 0000, 1, '熟食');
INSERT INTO `products_types` VALUES (15, 0000, 1, '冷藏冷冻');
INSERT INTO `products_types` VALUES (17, 0000, 1, '耗材');
INSERT INTO `products_types` VALUES (1101, 0011, 0, '蔬菜');
INSERT INTO `products_types` VALUES (1102, 0011, 0, '水果');
INSERT INTO `products_types` VALUES (1103, 0011, 0, '干货');
INSERT INTO `products_types` VALUES (1104, 0011, 0, '五谷粮油');
INSERT INTO `products_types` VALUES (1201, 0012, 0, '禽类');
INSERT INTO `products_types` VALUES (1202, 0012, 0, '畜类');
INSERT INTO `products_types` VALUES (1203, 0012, 0, '加工肉类');
INSERT INTO `products_types` VALUES (1301, 0013, 0, '鲜活');
INSERT INTO `products_types` VALUES (1302, 0013, 0, '冰鲜');
INSERT INTO `products_types` VALUES (1303, 0013, 0, '冷冻');
INSERT INTO `products_types` VALUES (1401, 0014, 0, '肉类');
INSERT INTO `products_types` VALUES (1402, 0014, 0, '面点');
INSERT INTO `products_types` VALUES (1501, 0015, 0, '乳制品');
INSERT INTO `products_types` VALUES (1502, 0015, 0, '速冻食品');
INSERT INTO `products_types` VALUES (1503, 0015, 0, '包装蔬果');
INSERT INTO `products_types` VALUES (1504, 0015, 0, '其他');
INSERT INTO `products_types` VALUES (1701, 0017, 0, '厨具');
INSERT INTO `products_types` VALUES (1702, 0017, 0, '调味料');

SET FOREIGN_KEY_CHECKS = 1;
