/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : community

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 21/07/2020 16:46:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for problem
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `gmt_create` bigint(255) NULL DEFAULT NULL,
  `gmt_modified` bigint(255) NULL DEFAULT NULL,
  `creator` int(11) NULL DEFAULT NULL,
  `comment_count` int(255) NULL DEFAULT 0,
  `view_count` int(255) NULL DEFAULT 0,
  `like_count` int(255) NULL DEFAULT 0,
  `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem
-- ----------------------------
INSERT INTO `problem` VALUES (10, '基于生态旅游和康养视角下的社区更新规划设计', 'qq', 1595311704201, 1595311704201, 44734532, 0, 0, 0, 'administrator,dd');
INSERT INTO `problem` VALUES (11, 'spring boot学习', '面试必会', 1595318732315, 1595318732315, 44734532, 0, 0, 0, 'springboot,面试,java');
INSERT INTO `problem` VALUES (12, 'mybatis学习', 'mybatis源码解读', 1595320641633, 1595320641633, 44734532, 0, 0, 0, 'mybatis');
INSERT INTO `problem` VALUES (13, 'springmvc学习', 'springmvc源码解读', 1595320780212, 1595320780212, 44734532, 0, 0, 0, 'springmvc');
INSERT INTO `problem` VALUES (14, 'redis学习', 'redis面试必会', 1595320881640, 1595320881640, 44734532, 0, 0, 0, 'redis');
INSERT INTO `problem` VALUES (15, 'java学习', 'java基础面试必备', 1595320931068, 1595320931068, 44734532, 0, 0, 0, 'java,面试');
INSERT INTO `problem` VALUES (16, 'mysql学习', 'mysql面试必会', 1595320980381, 1595320980381, 44734532, 0, 0, 0, 'mysql,面试');
INSERT INTO `problem` VALUES (17, '消息队列', 'rabbitmq面试必会', 1595321041277, 1595321041277, 44734532, 0, 0, 0, 'rabbitmq,消息队列,面试');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `token` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gmt_create` bigint(255) NULL DEFAULT NULL,
  `gmt_modified` bigint(255) NULL DEFAULT NULL,
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account_id_index`(`account_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (12, '44734532', 'HHVic', '5ee3e5a5-f3c3-443f-ae5e-9baa949c18a1', 1595313586545, 1595313586545, 'https://avatars2.githubusercontent.com/u/44734532?v=4');

SET FOREIGN_KEY_CHECKS = 1;
