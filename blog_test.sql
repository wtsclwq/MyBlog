/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : blog_test

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 10/05/2020 21:03:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(40) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '操作地址的IP',
  `create_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '操作内容',
  `operate_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '操作的访问地址',
  `operate_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作的浏览器',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(40) NOT NULL COMMENT '父级权限的id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
  `permission` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限的唯一标识',
  `create_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modified_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 0, '用户管理', '', '2020-04-25 13:42:55', '2020-04-29 01:08:26');
INSERT INTO `sys_permission` VALUES (2, 3, '添加管理员', 'sys:admin:add', '2020-04-25 13:43:05', '2020-04-29 01:09:42');
INSERT INTO `sys_permission` VALUES (3, 1, '管理员列表', '', '2020-04-25 13:43:13', '2020-04-29 01:10:12');
INSERT INTO `sys_permission` VALUES (4, 3, '删除管理员', 'sys:admin:del', '2020-04-25 13:43:25', '2020-04-29 01:10:07');
INSERT INTO `sys_permission` VALUES (5, 3, '添加管理员', 'sys:admin:add', '2020-04-25 13:43:47', '2020-04-29 01:10:39');
INSERT INTO `sys_permission` VALUES (6, 3, '查询管理员', 'sys:admin:query', '2020-04-25 13:44:05', '2020-04-29 01:13:28');
INSERT INTO `sys_permission` VALUES (7, 3, '更新管理员', 'sys:admin:update', '2020-04-29 01:13:10', '2020-04-29 01:13:10');
INSERT INTO `sys_permission` VALUES (8, 1, '权限管理', '', '2020-04-29 01:14:10', '2020-04-29 01:14:10');
INSERT INTO `sys_permission` VALUES (9, 8, '角色列表', '', '2020-04-29 01:15:03', '2020-04-29 01:15:03');
INSERT INTO `sys_permission` VALUES (10, 9, '添加角色', 'sys:role:add', '2020-04-29 01:15:21', '2020-04-29 01:15:21');
INSERT INTO `sys_permission` VALUES (11, 9, '删除角色', 'sys:role:del', '2020-04-29 01:15:40', '2020-04-29 01:15:40');
INSERT INTO `sys_permission` VALUES (12, 9, '查询角色', 'sys:role:query', '2020-04-29 01:15:51', '2020-04-29 01:15:51');
INSERT INTO `sys_permission` VALUES (13, 9, '更新角色', 'sys:role:update', '2020-04-29 01:16:06', '2020-04-29 01:16:06');
INSERT INTO `sys_permission` VALUES (14, 0, '文章管理', '', '2020-04-29 01:16:41', '2020-04-29 01:16:41');
INSERT INTO `sys_permission` VALUES (15, 14, '文章列表', '', '2020-04-29 01:17:18', '2020-04-29 01:17:18');
INSERT INTO `sys_permission` VALUES (16, 15, '添加文章', 'sys:article:add', '2020-04-29 01:17:40', '2020-04-29 01:20:54');
INSERT INTO `sys_permission` VALUES (17, 15, '删除文章', 'sys:article:del', '2020-04-29 01:20:22', '2020-04-29 01:21:18');
INSERT INTO `sys_permission` VALUES (18, 15, '更新文章', 'sys:article:update', '2020-04-29 01:21:34', '2020-04-29 01:21:34');
INSERT INTO `sys_permission` VALUES (19, 15, '查询文章', 'sys:article:query', '2020-04-29 01:21:53', '2020-04-29 01:21:53');
INSERT INTO `sys_permission` VALUES (20, 14, '分类管理', '', '2020-04-29 01:23:00', '2020-04-29 01:23:00');
INSERT INTO `sys_permission` VALUES (21, 20, '添加分类', 'sys:category:addUp', '2020-04-29 01:23:40', '2020-04-29 01:24:23');
INSERT INTO `sys_permission` VALUES (22, 20, '删除分类', 'sys:category:del', '2020-04-29 01:24:01', '2020-04-29 01:56:45');
INSERT INTO `sys_permission` VALUES (23, 20, '修改分类', 'sys:category:addUp', '2020-04-29 01:24:34', '2020-04-29 01:24:34');
INSERT INTO `sys_permission` VALUES (24, 20, '查询分类', 'sys:category:query', '2020-04-29 01:24:52', '2020-04-29 01:24:58');
INSERT INTO `sys_permission` VALUES (25, 0, '个人信息', '', '2020-04-29 01:26:29', '2020-04-29 01:26:29');
INSERT INTO `sys_permission` VALUES (26, 25, '修改个人信息', '', '2020-04-29 01:26:42', '2020-04-29 01:26:42');
INSERT INTO `sys_permission` VALUES (27, 25, '修改密码', '', '2020-04-29 01:26:56', '2020-04-29 01:26:56');
INSERT INTO `sys_permission` VALUES (28, 8, '查询所有权限', 'sys:permission:query', '2020-04-29 01:48:44', '2020-04-29 01:48:44');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modified_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'admin', '超级管理员', '2020-04-29 01:29:09', '2020-04-29 01:29:09');
INSERT INTO `sys_role` VALUES (2, '游客', '权限较少', '2020-04-29 01:32:03', '2020-04-29 01:32:03');
INSERT INTO `sys_role` VALUES (3, '普通管理员', '只有文章相关权限', '2020-04-29 01:32:42', '2020-04-29 01:32:42');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(40) NOT NULL COMMENT '角色id',
  `permission_id` bigint(40) NOT NULL COMMENT '权限id',
  `create_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modified_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (17, 2, 25, '2020-04-29 01:32:03', '2020-04-29 01:32:03');
INSERT INTO `sys_role_permission` VALUES (18, 2, 26, '2020-04-29 01:32:03', '2020-04-29 01:32:03');
INSERT INTO `sys_role_permission` VALUES (19, 2, 27, '2020-04-29 01:32:03', '2020-04-29 01:32:03');
INSERT INTO `sys_role_permission` VALUES (20, 3, 14, '2020-04-29 01:32:42', '2020-04-29 01:32:42');
INSERT INTO `sys_role_permission` VALUES (21, 3, 15, '2020-04-29 01:32:42', '2020-04-29 01:32:42');
INSERT INTO `sys_role_permission` VALUES (22, 3, 16, '2020-04-29 01:32:42', '2020-04-29 01:32:42');
INSERT INTO `sys_role_permission` VALUES (23, 3, 17, '2020-04-29 01:32:42', '2020-04-29 01:32:42');
INSERT INTO `sys_role_permission` VALUES (24, 3, 18, '2020-04-29 01:32:42', '2020-04-29 01:32:42');
INSERT INTO `sys_role_permission` VALUES (25, 3, 19, '2020-04-29 01:32:42', '2020-04-29 01:32:42');
INSERT INTO `sys_role_permission` VALUES (26, 3, 20, '2020-04-29 01:32:42', '2020-04-29 01:32:42');
INSERT INTO `sys_role_permission` VALUES (27, 3, 21, '2020-04-29 01:32:42', '2020-04-29 01:32:42');
INSERT INTO `sys_role_permission` VALUES (28, 3, 22, '2020-04-29 01:32:42', '2020-04-29 01:32:42');
INSERT INTO `sys_role_permission` VALUES (29, 3, 23, '2020-04-29 01:32:42', '2020-04-29 01:32:42');
INSERT INTO `sys_role_permission` VALUES (30, 3, 24, '2020-04-29 01:32:42', '2020-04-29 01:32:42');
INSERT INTO `sys_role_permission` VALUES (31, 3, 25, '2020-04-29 01:32:42', '2020-04-29 01:32:42');
INSERT INTO `sys_role_permission` VALUES (32, 3, 26, '2020-04-29 01:32:42', '2020-04-29 01:32:42');
INSERT INTO `sys_role_permission` VALUES (33, 3, 27, '2020-04-29 01:32:43', '2020-04-29 01:32:43');
INSERT INTO `sys_role_permission` VALUES (34, 1, 1, '2020-04-29 02:06:49', '2020-04-29 02:06:49');
INSERT INTO `sys_role_permission` VALUES (35, 1, 3, '2020-04-29 02:06:49', '2020-04-29 02:06:49');
INSERT INTO `sys_role_permission` VALUES (36, 1, 2, '2020-04-29 02:06:49', '2020-04-29 02:06:49');
INSERT INTO `sys_role_permission` VALUES (37, 1, 4, '2020-04-29 02:06:49', '2020-04-29 02:06:49');
INSERT INTO `sys_role_permission` VALUES (38, 1, 5, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (39, 1, 6, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (40, 1, 7, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (41, 1, 8, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (42, 1, 9, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (43, 1, 10, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (44, 1, 11, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (45, 1, 12, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (46, 1, 13, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (47, 1, 28, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (48, 1, 14, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (49, 1, 15, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (50, 1, 16, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (51, 1, 17, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (52, 1, 18, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (53, 1, 19, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (54, 1, 20, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (55, 1, 21, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (56, 1, 22, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (57, 1, 23, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (58, 1, 24, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (59, 1, 25, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (60, 1, 26, '2020-04-29 02:06:50', '2020-04-29 02:06:50');
INSERT INTO `sys_role_permission` VALUES (61, 1, 27, '2020-04-29 02:06:50', '2020-04-29 02:06:50');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '加密密码',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `birthday` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '生日',
  `sex` int(1) NULL DEFAULT NULL COMMENT '性别',
  `headimgurl` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像图片地址',
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '状态：1可用 ，0不可用',
  `create_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modified_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统（后台）用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (7, 'admin', '$2a$10$NzgcCUhUaWBdkyffYhVnQecvfWmNpCopBQHCXPE1KeG4XwAEqamwi', 'admin', '17777777777', 'xxx@xx.com', '2020-04-01 00:00:00', 1, '', 1, '2020-04-29 01:36:17', '2020-04-29 01:36:17');
INSERT INTO `sys_user` VALUES (8, 'guest', '$2a$10$fz/SIrWMuwv2EaLh8Uc2e.s2U.Fdbu4XM9lglVMc6.AElcBCxne3y', 'guest', '18888888888', 'xx@xxx.com', '2020-04-11 00:00:00', 0, '', 1, '2020-04-29 01:36:58', '2020-04-29 01:36:58');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(40) NOT NULL COMMENT '用户id',
  `role_id` bigint(40) NOT NULL COMMENT '角色id',
  `create_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modified_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (13, 7, 1, '2020-04-29 01:36:18', '2020-04-29 01:36:18');
INSERT INTO `sys_user_role` VALUES (14, 8, 2, '2020-04-29 01:36:58', '2020-04-29 01:36:58');

-- ----------------------------
-- Table structure for sys_view
-- ----------------------------
DROP TABLE IF EXISTS `sys_view`;
CREATE TABLE `sys_view`  (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '访问IP',
  `create_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '访问时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_article_category
-- ----------------------------
DROP TABLE IF EXISTS `tbl_article_category`;
CREATE TABLE `tbl_article_category`  (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(40) NOT NULL COMMENT '分类id',
  `article_id` bigint(40) NOT NULL COMMENT '文章id',
  `create_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modified_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_article_category
-- ----------------------------
INSERT INTO `tbl_article_category` VALUES (1, 2, 1, '2020-04-22 13:31:35', '2020-04-22 13:45:34');
INSERT INTO `tbl_article_category` VALUES (2, 2, 8, '2020-04-23 14:26:00', '2020-04-23 14:26:00');
INSERT INTO `tbl_article_category` VALUES (3, 1, 9, '2020-04-23 14:26:13', '2020-04-23 14:26:13');
INSERT INTO `tbl_article_category` VALUES (4, 1, 10, '2020-04-23 14:26:30', '2020-04-23 14:26:30');
INSERT INTO `tbl_article_category` VALUES (5, 6, 11, '2020-04-23 14:26:41', '2020-04-23 14:26:41');
INSERT INTO `tbl_article_category` VALUES (10, 2, 26, '2020-04-26 22:13:47', '2020-04-26 22:13:47');
INSERT INTO `tbl_article_category` VALUES (11, 1, 27, '2020-04-26 22:15:36', '2020-04-26 22:15:36');
INSERT INTO `tbl_article_category` VALUES (12, 1, 28, '2020-04-26 22:16:08', '2020-04-26 22:16:08');
INSERT INTO `tbl_article_category` VALUES (13, 1, 29, '2020-04-27 09:55:51', '2020-04-27 09:55:51');
INSERT INTO `tbl_article_category` VALUES (14, 1, 30, '2020-04-27 10:08:10', '2020-04-27 10:08:10');
INSERT INTO `tbl_article_category` VALUES (15, 2, 31, '2020-04-27 10:10:18', '2020-04-27 10:10:18');
INSERT INTO `tbl_article_category` VALUES (16, 2, 32, '2020-04-27 10:23:25', '2020-04-27 10:23:25');
INSERT INTO `tbl_article_category` VALUES (17, 2, 33, '2020-04-27 10:25:25', '2020-04-27 10:25:25');
INSERT INTO `tbl_article_category` VALUES (18, 2, 34, '2020-04-27 10:53:58', '2020-04-27 10:53:58');
INSERT INTO `tbl_article_category` VALUES (19, 2, 35, '2020-04-27 17:17:08', '2020-04-27 17:17:08');

-- ----------------------------
-- Table structure for tbl_article_comment
-- ----------------------------
DROP TABLE IF EXISTS `tbl_article_comment`;
CREATE TABLE `tbl_article_comment`  (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(40) NOT NULL COMMENT '文章ID',
  `comment_id` bigint(40) NOT NULL COMMENT '对应的留言ID',
  `create_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `is_effective` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否有效，默认为1有效，置0无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_article_content
-- ----------------------------
DROP TABLE IF EXISTS `tbl_article_content`;
CREATE TABLE `tbl_article_content`  (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `article_id` bigint(40) NOT NULL COMMENT '对应文章ID',
  `create_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifield_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_article_content
-- ----------------------------
INSERT INTO `tbl_article_content` VALUES (1, 'test1', 1, '2020-04-22 13:31:35', '2020-04-22 13:31:35');
INSERT INTO `tbl_article_content` VALUES (2, 'test2', 8, '2020-04-23 14:26:00', '2020-04-23 14:26:00');
INSERT INTO `tbl_article_content` VALUES (3, 'test3', 9, '2020-04-23 14:26:13', '2020-04-23 14:26:13');
INSERT INTO `tbl_article_content` VALUES (4, 'test4', 10, '2020-04-23 14:26:30', '2020-04-23 14:26:30');
INSERT INTO `tbl_article_content` VALUES (5, '666666666666666', 11, '2020-04-23 14:26:41', '2020-04-23 14:26:41');
INSERT INTO `tbl_article_content` VALUES (17, '123123123321', 26, '2020-04-26 22:13:47', '2020-04-26 22:13:47');
INSERT INTO `tbl_article_content` VALUES (18, '123123123213', 27, '2020-04-26 22:15:36', '2020-04-26 22:15:36');
INSERT INTO `tbl_article_content` VALUES (19, '33333333333333333333', 28, '2020-04-26 22:16:08', '2020-04-26 22:16:08');
INSERT INTO `tbl_article_content` VALUES (20, '## 3.引入Thymeleaf命名空间\n\n```html\nxmlns:th=\"http://www.thymeleaf.org\"\n```\n\n## 4.请求json文件\n\n直接配置请求路径\n\n```java\n\"/user.json\"  ==> \"user.json\"\n```\n\n## 5.权限树的初始化\n\n先从公共js中获取到所有的权限，通过ztree形成一棵树，再从查询角色信息的请求中获取到用户的权限，通过后者权限与公共js获取到的权限树进行对比，改变相应子树（树叶）的选定情况\n\n\n\n## 6.更新文章时，为什么不需要在js中再获取一次文章id\n\n文章id的属性是hiden的，在初始化页面获取文章信息时就已经赋值了，而且在编辑文章信息时并没有修改，所以可以直接用data.field获取，而其他属性则需要在js中再获取一次并且存到对象中，传给后台Controller', 29, '2020-04-27 09:55:51', '2020-04-27 09:55:51');
INSERT INTO `tbl_article_content` VALUES (21, '989898', 30, '2020-04-27 10:08:10', '2020-04-27 10:08:10');
INSERT INTO `tbl_article_content` VALUES (22, '\r\n\r\n# 三行情书\r\n\r\n\r\n\r\n```java\r\n你说什么\r\n\r\n我都听不懂\r\n\r\n反正我不会松手\r\n\r\n```\r\n\r\n', 31, '2020-04-27 10:10:18', '2020-04-29 17:28:56');
INSERT INTO `tbl_article_content` VALUES (23, '\r\n\r\n#### JsonData\r\n\r\n\r\n\r\n```java\r\npackage cn.sxh.sxh_blog.util;\r\n\r\n/**\r\n * @author sxh\r\n */\r\npublic class JsonData {\r\n    private Integer code;\r\n    private String msg;\r\n\r\n    public JsonData() {\r\n    }\r\n\r\n    public JsonData(Integer code, String msg) {\r\n        this.code = code;\r\n        this.msg = msg;\r\n    }\r\n\r\n    public Integer getCode() {\r\n        return code;\r\n    }\r\n\r\n    public void setCode(Integer code) {\r\n        this.code = code;\r\n    }\r\n\r\n    public String getMsg() {\r\n        return msg;\r\n    }\r\n\r\n    public void setMsg(String msg) {\r\n        this.msg = msg;\r\n    }\r\n}\r\n\r\n```', 32, '2020-04-27 10:23:25', '2020-04-29 17:26:41');
INSERT INTO `tbl_article_content` VALUES (24, '\r\n\r\n# 登科后\r\n\r\n#### 王安石\r\n\r\n\r\n\r\n```\r\n昔日龌龊不足夸，今朝放荡思无涯。\r\n\r\n春风得意马蹄疾，一日看尽长安花。\r\n\r\n```\r\n\r\n', 33, '2020-04-27 10:25:24', '2020-04-29 17:25:23');
INSERT INTO `tbl_article_content` VALUES (25, '\r\n\r\n# Controller\r\n\r\n## 测试Md\r\n\r\n### 测试md\r\n\r\n#### 测试md\r\n\r\n\r\n\r\n\r\n\r\n```java\r\n/**\r\n * @author sxh\r\n */\r\n@Api(tags = \"文章管理\")\r\n@RestController\r\n@RequestMapping(\"/admin/article\")\r\npublic class ArticleController {\r\n\r\n    @Resource\r\n    private ArticleService articleService;\r\n\r\n    @PostMapping\r\n    @ApiOperation(\"添加文章\")\r\n    @PreAuthorize(\"hasAuthority(\'sys:article:add\')\")\r\n    public PublicResultJson addArticle(@RequestBody ArticleAddDto articleAddDto) {\r\n        PublicResultJson resultJson = articleService.add(articleAddDto);\r\n        return resultJson;\r\n    }\r\n\r\n    @DeleteMapping(value = \"/{id}\")\r\n    @ApiOperation(\"根据ID批量或单个删除文章\")\r\n    @PreAuthorize(\"hasAuthority(\'sys:article:del\')\")\r\n    public PublicResultJson deleteArticle(@PathVariable(value = \"id\") Long id) {\r\n        PublicResultJson resultJson = articleService.delete(id);\r\n        return resultJson;\r\n    }\r\n\r\n    @PutMapping\r\n    @ApiOperation(\"更新文章\")\r\n    @PreAuthorize(\"hasAuthority(\'sys:article:update\')\")\r\n    public PublicResultJson updateArticle(@RequestBody ArticleAddDto articleAddDto) {\r\n        PublicResultJson resultJson = articleService.updateByExample(articleAddDto,true);\r\n        return resultJson;\r\n    }\r\n\r\n    @PutMapping(\"/switch\")\r\n    @ApiOperation(\"switch开关更新文章\")\r\n    @PreAuthorize(\"hasAuthority(\'sys:article:update\')\")\r\n    public PublicResultJson updateArticleBySwitch(@RequestBody ArticleAddDto articleAddDto) {\r\n        PublicResultJson resultJson = articleService.updateByExample(articleAddDto,false);\r\n        return resultJson;\r\n    }\r\n\r\n\r\n    @GetMapping\r\n    @ApiOperation(\"文章列表条件模糊查询\")\r\n    @PreAuthorize(\"hasAnyAuthority(\'sys:article:update\',\'sys:article:query\')\")\r\n    public LayuiTableResult selectByExample(ArticleSelectDto article, @RequestParam(defaultValue = \"1\")\r\n            Integer page, @RequestParam(defaultValue = \"10\") Integer pageSize) {\r\n        LayuiTableResult resultJson = articleService.selectByExample(article,page,pageSize);\r\n   //     System.out.println(resultJson);\r\n        return resultJson;\r\n    }\r\n\r\n    @GetMapping(value = \"/{id}\")\r\n    @ApiOperation(value = \"根据id查找文章信息\")\r\n    @PreAuthorize(\"hasAnyAuthority(\'sys:article:update\',\'sys:article:query\')\")\r\n    public PublicResultJson selectByUid(@PathVariable(value = \"id\") Long id) {\r\n        PublicResultJson resultJson = articleService.selectByBid(id);\r\n        System.out.println(resultJson);\r\n        return resultJson;\r\n    }\r\n\r\n\r\n}\r\n```', 34, '2020-04-27 10:53:58', '2020-04-29 17:22:35');
INSERT INTO `tbl_article_content` VALUES (26, '# MySql笔记\r\n\r\n## CURRENT_TIMESTAMP属性\r\n\r\n用来设置时间字段的默认值\r\n\r\n如果加上\r\n\r\n```mysql\r\nON UPDATE CURRENT_TIMESTAMP，\r\n```\r\n\r\n就是根据更新时间设置\r\n\r\n例子：\r\n\r\n```mysql\r\nCREATE TABLE `tbl_article_info` (\r\n  `id` bigint(40) NOT NULL AUTO_INCREMENT COMMENT \'主键\',\r\n  `title` varchar(50) NOT NULL DEFAULT \'\' COMMENT \'文章标题\',\r\n  `summary` varchar(300) NOT NULL DEFAULT \'\' COMMENT \'文章简介，默认100个汉字以内\',\r\n  `is_top` tinyint(1) NOT NULL DEFAULT \'0\' COMMENT \'文章是否置顶，0为否，1为是\',\r\n  `traffic` int(10) NOT NULL DEFAULT \'0\' COMMENT \'文章访问量\',\r\n  `create_by` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT \'创建时间\',\r\n  `modified_by` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT \'修改日期\',\r\n  PRIMARY KEY (`id`)\r\n) ENGINE=InnoDB DEFAULT CHARSET=utf8;\r\n```\r\n\r\n## MyBatis updateByPrimaryKeySelective\r\n\r\nupdateByPrimaryKeySelective会对字段进行判断再更新(如果为Null就忽略更新)，如果你只想更新某一字段，可以用这个方法。\r\n\r\nupdateByPrimaryKey对你注入的字段全部更新\r\n\r\n\r\n\r\n## Mybatis中的example用法\r\n\r\n<https://blog.csdn.net/biandous/article/details/65630783>\r\n\r\n\r\n\r\n\r\n\r\n## MyBatis发生绑定失败错误\r\n\r\nInvalid bound statement (not found)\r\n\r\n网上找到的其他解决方法：<https://blog.csdn.net/yali1990515/article/details/52184553>\r\n\r\n我的错误：\r\n\r\n```yaml\r\n#没有在配置文件中声明xml的路径\r\nmybatis:\r\n  type-aliases-package: cn.sxh.sxh_blog.entity          # 所有实体类所在包路径\r\n  mapper-locations: classpath:mapper/*.xml      # mapper映射文件\r\n```\r\n\r\n', 35, '2020-04-27 17:17:08', '2020-04-29 04:28:24');

-- ----------------------------
-- Table structure for tbl_article_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_article_info`;
CREATE TABLE `tbl_article_info`  (
  `id` bigint(40) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '文章标题',
  `summary` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '文章简介，默认100个汉字以内',
  `picture_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '题图url',
  `is_top` tinyint(1) NOT NULL DEFAULT 0 COMMENT '文章是否置顶，0为否，1为是',
  `traffic` int(10) NOT NULL DEFAULT 0 COMMENT '文章访问量',
  `create_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modified_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_article_info
-- ----------------------------
INSERT INTO `tbl_article_info` VALUES (1, '标题1', '简介1', 'https://images.unsplash.com/photo-1480072723304-5021e468de85?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1952&q=80', 1, 5, '2020-04-22 13:31:35', '2020-04-29 17:29:23');
INSERT INTO `tbl_article_info` VALUES (8, '标题2', '简介2', 'https://images.unsplash.com/photo-1487856374269-301dc48a3c31?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80', 1, 2, '2020-04-23 14:26:00', '2020-04-29 17:29:31');
INSERT INTO `tbl_article_info` VALUES (9, '标题3', '简介3', 'https://images.unsplash.com/photo-1515375380578-a0587184cedd?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1888&q=80', 0, 4, '2020-04-23 14:26:13', '2020-04-29 17:29:42');
INSERT INTO `tbl_article_info` VALUES (10, '标题4', '简介4', 'https://images.unsplash.com/photo-1586191582109-7e0c712e9460?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1949&q=80', 0, 4, '2020-04-23 14:26:30', '2020-04-29 17:29:53');
INSERT INTO `tbl_article_info` VALUES (11, '标题5', '简介5', 'https://images.unsplash.com/photo-1587657565520-6c0c23cf68c7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80', 1, 8, '2020-04-23 14:26:41', '2020-04-29 17:30:05');
INSERT INTO `tbl_article_info` VALUES (26, '标题6', '简介6', 'https://images.unsplash.com/photo-1587847023976-598a80c1ec0e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80', 0, 1, '2020-04-26 22:13:47', '2020-04-29 17:30:15');
INSERT INTO `tbl_article_info` VALUES (27, '标题7', '简介7', 'https://pic2.zhimg.com/80/v2-0d7e0125b2dedd2ada472a2af5d1a7c9_720w.jpg', 0, 1, '2020-04-26 22:15:36', '2020-04-29 17:30:53');
INSERT INTO `tbl_article_info` VALUES (28, '标题8', '简介8', 'https://pic1.zhimg.com/80/v2-https://images.unsplash.com/photo-1551806173-d7147a4e8b59?ixlib=rb-https://images.unsplash.com/photo-1448518340475-e3c680e9b4be?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1951&q=80', 0, 2, '2020-04-26 22:16:08', '2020-04-29 17:30:46');
INSERT INTO `tbl_article_info` VALUES (29, 'md', 'md', 'https://images.unsplash.com/photo-1474945039698-141672b0b403?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80', 0, 5, '2020-04-27 09:55:51', '2020-04-29 17:18:24');
INSERT INTO `tbl_article_info` VALUES (30, '989898', '989898', 'https://images.unsplash.com/photo-1564632516593-e1be25b7ee33?ixlib=rb-https://images.unsplash.com/photo-1496938884040-e0456a8512ca?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1949&q=80', 0, 4, '2020-04-27 10:08:10', '2020-04-29 17:18:20');
INSERT INTO `tbl_article_info` VALUES (31, '三行情书', '三行情书', 'https://images.unsplash.com/photo-1454779132693-e5cd0a216ed3?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1951&q=80', 0, 6, '2020-04-27 10:10:18', '2020-04-29 17:28:05');
INSERT INTO `tbl_article_info` VALUES (32, 'Markdown测试', '测试语法高亮', 'https://images.unsplash.com/photo-1518715179561-57faf0b9fd37?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1952&q=80', 1, 4, '2020-04-27 10:23:25', '2020-04-29 17:27:49');
INSERT INTO `tbl_article_info` VALUES (33, '登科后', '王安石的一首诗', 'https://images.unsplash.com/photo-1537812200084-ceff635d6411?ixlib=rb-https://images.unsplash.com/photo-1587924316340-7a6d9d43d2db?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80', 0, 3, '2020-04-27 10:25:24', '2020-04-29 17:27:31');
INSERT INTO `tbl_article_info` VALUES (34, 'Controlller设计', 'Controlller设计', 'https://images.unsplash.com/photo-1587921667113-507db873557e?ixlib=rb-https://images.unsplash.com/photo-1447014421976-7fec21d26d86?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80', 0, 6, '2020-04-27 10:53:58', '2020-04-29 17:27:20');
INSERT INTO `tbl_article_info` VALUES (35, 'MarkDown', 'MarkDown', 'https://images.unsplash.com/photo-1588079534777-bc7733871c6a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80', 1, 28, '2020-04-27 17:17:08', '2020-04-29 17:39:01');

-- ----------------------------
-- Table structure for tbl_category_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_category_info`;
CREATE TABLE `tbl_category_info`  (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
  `number` tinyint(10) NOT NULL DEFAULT 0 COMMENT '该分类下的文章数量',
  `create_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '分类创建时间',
  `modified_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '分类修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_category_info
-- ----------------------------
INSERT INTO `tbl_category_info` VALUES (1, '分类测试1', 6, '2020-04-22 13:30:19', '2020-04-29 17:31:36');
INSERT INTO `tbl_category_info` VALUES (2, '分类测试2', 8, '2020-04-22 13:30:23', '2020-04-29 17:31:30');
INSERT INTO `tbl_category_info` VALUES (3, '分类测试3', 0, '2020-04-23 17:37:43', '2020-04-29 17:31:44');
INSERT INTO `tbl_category_info` VALUES (4, '分类test4', 0, '2020-04-23 17:37:45', '2020-04-29 17:31:50');
INSERT INTO `tbl_category_info` VALUES (6, 'CategoryTest5', 1, '2020-04-23 17:37:49', '2020-04-29 17:32:05');
INSERT INTO `tbl_category_info` VALUES (10, 'Test7', 0, '2020-04-27 22:26:40', '2020-04-29 17:32:11');
INSERT INTO `tbl_category_info` VALUES (11, 'Test8', 0, '2020-04-27 22:26:52', '2020-04-29 17:32:18');

-- ----------------------------
-- Table structure for tbl_comment
-- ----------------------------
DROP TABLE IF EXISTS `tbl_comment`;
CREATE TABLE `tbl_comment`  (
  `id` bigint(40) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '留言/评论内容',
  `create_by` datetime(0) NOT NULL COMMENT '创建日期',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '邮箱，用于回复消息',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户自己定义的名称',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '留言/评论IP',
  `is_effective` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否有效，默认为1为有效，0为无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '因为message分为两种，一种是留言，一种是评论，这里搞成一张表是因为它们几乎是拥有相同的字段，我觉得没必要分成两张表来进行维护' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
