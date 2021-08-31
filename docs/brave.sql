/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:13306
 Source Schema         : brave

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 31/08/2021 16:44:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `parent_id` bigint(20) NOT NULL COMMENT '父级部门id',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
  `id_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '完整id路劲',
  `name_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '完整名称路径',
  `sort` int(2) NULL DEFAULT NULL COMMENT '排序',
  `leader` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门负责人',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门邮箱',
  `dept_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门使用状态（0：正常，1：禁用）',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (11212766199152673, 0, '技术部', '11212766199152673', '技术部', 0, 'brave', '13345678912', '123456789@123.com', '0', 1, '2021-06-24 16:22:56', NULL, '2021-07-21 15:27:43', '0');
INSERT INTO `sys_dept` VALUES (11227993670156321, 11212766199152673, '研发部', '11212766199152673/11227993670156321', '技术部/研发部', 0, 'siri', '13345678912', '123456789@123.com', '0', 1, '2021-06-24 17:23:27', NULL, '2021-07-21 15:27:56', '0');
INSERT INTO `sys_dept` VALUES (20982935926603809, 0, '财务部', '20982935926603809', '财务部', 1, '小橙子', '13358452568', NULL, '0', NULL, '2021-07-21 15:26:06', NULL, '2021-07-21 15:26:06', '0');
INSERT INTO `sys_dept` VALUES (20983838918312129, 11212766199152673, '测试部', '11212766199152673/20983838918312129', '技术部/测试部', 1, 'breano', '13568952458', NULL, '0', NULL, '2021-07-21 15:29:42', NULL, '2021-07-21 15:29:42', '0');
INSERT INTO `sys_dept` VALUES (21278957483589697, 11227993670156321, '智慧城市BG', '11212766199152673/11227993670156321/21278957483589697', '技术部/研发部/智慧城市BG', 0, '阿飞', '15986254388', 'zihuichengshi@163.com', '0', NULL, '2021-07-22 11:02:23', NULL, '2021-07-22 11:02:23', '0');
INSERT INTO `sys_dept` VALUES (21279211218010241, 11227993670156321, '智慧医疗BG', '11212766199152673/11227993670156321/21279211218010241', '技术部/研发部/智慧医疗BG', 1, '大华', '1598652361', 'zhihuiyiliao@163.com', '0', NULL, '2021-07-22 11:03:24', NULL, '2021-07-22 11:03:24', '0');
INSERT INTO `sys_dept` VALUES (21280542896947489, 20982935926603809, '删除部门', '20982935926603809/21280542896947489', '财务部/删除部门', 0, '阿达', '13356254865', NULL, '0', NULL, '2021-07-22 11:08:41', NULL, '2021-07-22 11:08:41', '1');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `dict_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
  `dict_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典编码',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典键值',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `dict_sort` int(4) NULL DEFAULT NULL COMMENT '字典排序',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, '用户性别', 'SYS_USER_SEX', NULL, NULL, NULL, NULL, 'N', '0', 0, '用户性别列表', 1, '2021-07-13 10:11:07', 1, '2021-07-13 10:11:12', '0');
INSERT INTO `sys_dict` VALUES (2, '菜单状态', 'SYS_SHOW_HIDE', NULL, NULL, NULL, NULL, 'N', '0', 0, '菜单状态列表', 1, '2021-07-13 02:16:02', 1, '2021-07-13 02:16:02', '0');
INSERT INTO `sys_dict` VALUES (3, '系统开关', 'SYS_SWITCH', NULL, NULL, NULL, NULL, 'N', '0', 0, '菜单状态列表', 1, '2021-07-13 02:16:02', 1, '2021-07-13 02:16:02', '0');
INSERT INTO `sys_dict` VALUES (4, '任务状态', 'SYS_JOB_STATUS', NULL, NULL, NULL, NULL, 'N', '0', 0, '菜单状态列表', 1, '2021-07-13 02:16:02', 1, '2021-07-13 02:16:02', '0');
INSERT INTO `sys_dict` VALUES (5, '任务分组', 'SYS_JOB_GROUP', NULL, NULL, NULL, NULL, 'N', '0', 0, '菜单状态列表', 1, '2021-07-13 02:16:02', 1, '2021-07-13 02:16:02', '0');
INSERT INTO `sys_dict` VALUES (6, '系统是否', 'SYS_YES_NO', NULL, NULL, NULL, NULL, 'N', '0', 0, '菜单状态列表', 1, '2021-07-13 02:16:02', 1, '2021-07-13 02:16:02', '0');
INSERT INTO `sys_dict` VALUES (7, '通知类型', 'SYS_NOTICE_TYPE', NULL, NULL, NULL, NULL, 'N', '0', 0, '菜单状态列表', 1, '2021-07-13 02:16:02', 1, '2021-07-13 02:16:02', '0');
INSERT INTO `sys_dict` VALUES (8, '通知状态', 'SYS_NOTICE_STATUS', NULL, NULL, NULL, NULL, 'N', '0', 0, '菜单状态列表', 1, '2021-07-13 02:16:02', 1, '2021-07-13 02:16:02', '0');
INSERT INTO `sys_dict` VALUES (9, '操作类型', 'SYS_OPER_TYPE', NULL, NULL, NULL, NULL, 'N', '0', 0, '菜单状态列表', 1, '2021-07-13 02:16:02', 1, '2021-07-13 02:16:02', '0');
INSERT INTO `sys_dict` VALUES (10, '系统状态', 'SYS_COMMON_STATUS', NULL, NULL, NULL, NULL, 'N', '0', 0, '菜单状态列表', 1, '2021-07-13 02:16:02', 1, '2021-07-13 02:16:02', '0');
INSERT INTO `sys_dict` VALUES (11, '性别', 'SEX', '女', '0', NULL, NULL, 'N', '0', 0, '用户性别', 1, '2021-07-13 02:43:18', 1, '2021-07-13 02:43:18', '0');
INSERT INTO `sys_dict` VALUES (12, '性别', 'SEX', '男', '1', NULL, NULL, 'N', '0', 1, '用户性别', 1, '2021-07-13 02:43:18', 1, '2021-07-13 02:43:18', '0');
INSERT INTO `sys_dict` VALUES (13, '性别', 'SEX', '未知性别', '2', NULL, NULL, 'N', '0', 2, '用户性别', 1, '2021-07-13 02:43:18', 1, '2021-07-13 02:43:18', '0');
INSERT INTO `sys_dict` VALUES (14, '菜单状态', 'MENU_STATUS', '显示', '0', NULL, NULL, 'N', '0', 0, '显示菜单', 1, '2021-07-13 02:43:18', 1, '2021-07-13 02:43:18', '0');
INSERT INTO `sys_dict` VALUES (15, '菜单状态', 'MENU_STATUS', '隐藏', '1', NULL, NULL, 'N', '0', 1, '隐藏菜单', 1, '2021-07-13 02:43:18', 1, '2021-07-13 02:43:18', '0');
INSERT INTO `sys_dict` VALUES (16, '使用状态', 'SYS_NORMAL_DISABLE', '正常', '0', NULL, NULL, 'N', '0', 0, '正常状态', 1, '2021-07-13 02:43:18', 1, '2021-07-13 02:43:18', '0');
INSERT INTO `sys_dict` VALUES (17, '使用状态', 'SYS_NORMAL_DISABLE', '停用', '1', NULL, NULL, 'N', '0', 1, '停用状态', 1, '2021-07-13 02:43:18', 1, '2021-07-13 02:43:18', '0');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志类型（0：正常，1：异常）',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志描述',
  `request_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求uri',
  `request_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求IP地址',
  `request_city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求城市',
  `http_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求类型',
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求参数',
  `result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '返回结果',
  `error_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '异常详情信息',
  `error_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '异常描述',
  `start_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求发起时间',
  `finish_time` datetime NULL DEFAULT NULL COMMENT '请求完成时间',
  `device_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备类型',
  `browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  `operate_system` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人姓名',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `ip_addr` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录IP地址',
  `city_addr` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录城市地址',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录状态（0：成功， 1：失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '提示信息',
  `device_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备类型',
  `browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  `operate_system` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人姓名',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `sort` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `url_perm` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求地址权限标识',
  `btn_perm` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '按钮权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `is_external_link` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否为外链（0：是，1：否）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单类型（M：目录，C：菜单，F：按钮）',
  `is_display` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '显示状态（0：显示，1：隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0：启用，1：禁用）',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31834085978540610 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 0, '/system', NULL, NULL, 'icon-a-24_nor', '1', 'M', '0', '0', 1, '2021-07-12 08:02:02', 1, '2021-07-12 08:02:02', '0');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 1, '/monitor', NULL, NULL, 'icon-yunshezhi', '1', 'M', '0', '0', 1, '2021-07-12 08:02:18', 1, '2021-07-12 08:02:18', '0');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 2, '/tool', NULL, NULL, 'icon-a-ziyuan3', '1', 'M', '0', '0', 1, '2021-07-12 08:02:18', 1, '2021-07-12 08:02:18', '0');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, '/user', '/mbs/sysUser/page', NULL, 'icon-user-group', '1', 'C', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, '/role', '/mbs/sysRole/page', NULL, 'icon-role-group', '1', 'C', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, '/menu', '/mbs/sysMenu/getMenuTree', NULL, 'icon-m-shuxing', '1', 'C', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, '/dept', '/mbs/sysDept/tree', NULL, 'icon-tuoputu', '1', 'C', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, '/post', NULL, NULL, 'post', '1', 'C', '0', '1', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, '/dict', '/mbs/sysDict/page', NULL, 'icon-yuedu', '1', 'C', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, '/config', NULL, NULL, 'edit', '1', 'C', '0', '1', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 9, '/notice', NULL, NULL, 'message', '1', 'C', '0', '1', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 0, 3, '/log', NULL, NULL, 'icon-shujuzhunquexingxiaoyan', '1', 'M', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'online', NULL, NULL, 'online', '1', 'C', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '1');
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, 'job', NULL, NULL, 'job', '1', 'C', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '1');
INSERT INTO `sys_menu` VALUES (111, 'Sentinel控制台', 2, 3, 'http://localhost:8718', NULL, NULL, 'icon-sentinel', '0', 'C', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (112, 'Nacos控制台', 2, 4, 'http://localhost:8848/nacos', NULL, NULL, 'icon-nacos', '0', 'C', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (113, 'Admin控制台', 2, 5, 'http://localhost:9100/login', NULL, NULL, 'icon-icon-spring-boot-admin', '0', 'C', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (114, '表单构建', 3, 1, 'build', NULL, NULL, 'build', '1', 'C', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (115, '代码生成', 3, 2, 'gen', NULL, NULL, 'code', '1', 'C', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (116, '系统接口', 3, 3, 'http://localhost:8080/swagger-ui/index.html', NULL, NULL, 'swagger', '1', 'C', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (1001, '用户查询', 100, 1, '', '/mbs/sysUser/get', 'sys:user:get', '#', '1', 'F', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (1002, '用户新增', 100, 2, '', '/mbs/sysUser/save', 'sys:user:add', '#', '1', 'F', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (1003, '用户修改', 100, 3, '', '/mbs/sysUser/update', 'sys:user:update', '#', '1', 'F', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (1004, '用户删除', 100, 4, '', '/mbs/sysUser/del', 'sys:user:del', '#', '1', 'F', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (1005, '用户导出', 100, 5, '', NULL, 'sys:user:export', '#', '1', 'F', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (1006, '用户导入', 100, 6, '', NULL, 'sys:user:import', '#', '1', 'F', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (1007, '重置密码', 100, 7, '', '/mbs/sysUser/resetPwd', 'sys:user:resetPwd', '#', '1', 'F', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (1008, '角色查询', 101, 1, '', '/mbs/sysRole/get', 'sys:role:get', '#', '1', 'F', '0', '0', 1, '2021-07-14 06:09:02', 1, '2021-07-14 06:09:02', '0');
INSERT INTO `sys_menu` VALUES (1009, '角色新增', 101, 2, '', '/mbs/sysRole/save', 'sys:role:add', '#', '1', 'F', '0', '0', 1, '2021-07-14 06:09:02', 1, '2021-07-14 06:09:02', '0');
INSERT INTO `sys_menu` VALUES (1010, '角色修改', 101, 3, '', '/mbs/sysRole/update', 'sys:role:update', '#', '1', 'F', '0', '0', 1, '2021-07-14 06:09:02', 1, '2021-07-14 06:09:02', '0');
INSERT INTO `sys_menu` VALUES (1011, '角色删除', 101, 4, '', '/mbs/sysRole/delete', 'sys:role:del', '#', '1', 'F', '0', '0', 1, '2021-07-14 06:09:02', 1, '2021-07-14 06:09:02', '0');
INSERT INTO `sys_menu` VALUES (1012, '角色导出', 101, 5, '', NULL, 'sys:role:export', '#', '1', 'F', '0', '0', 1, '2021-07-14 06:09:02', 1, '2021-07-14 06:09:02', '0');
INSERT INTO `sys_menu` VALUES (1081, '操作日志', 108, 0, '/operlog', '/mbs/sysLog/page', NULL, '#', '1', 'C', '0', '0', 1, '2021-07-15 10:43:26', 1, '2021-07-15 10:43:30', '0');
INSERT INTO `sys_menu` VALUES (1082, '登录日志', 108, 1, '/loginlog', '/mbs/sysLoginLog/page', NULL, '#', '1', 'C', '0', '0', 1, '2021-07-15 10:44:29', 1, '2021-07-15 10:44:34', '0');
INSERT INTO `sys_menu` VALUES (21363456221383201, '测试目录', 0, 4, '/ceshi', NULL, NULL, 'ogininfor', '1', 'M', '0', '0', NULL, '2021-07-22 16:38:10', NULL, '2021-07-22 16:38:10', '1');
INSERT INTO `sys_menu` VALUES (21365243133297025, '测试菜单1', 21363456221383201, 0, '/cscd', NULL, NULL, 'ogininfor', '1', 'C', '0', '0', NULL, '2021-07-22 16:45:16', NULL, '2021-07-22 16:45:16', '1');
INSERT INTO `sys_menu` VALUES (21367332555194465, '测试按钮', 21365243133297025, 0, 'csan', NULL, 'ceshi:cscd:an', '', '1', 'F', '0', '0', NULL, '2021-07-22 16:53:34', NULL, '2021-07-22 16:53:34', '1');
INSERT INTO `sys_menu` VALUES (21721626760446913, '测试菜单2', 21363456221383201, 0, '/ceshi', NULL, NULL, '', '1', 'C', '0', '0', NULL, '2021-07-23 16:21:24', NULL, '2021-07-23 16:21:24', '1');
INSERT INTO `sys_menu` VALUES (21729478858246113, 'cccccc', 21365243133297025, 0, '/234', NULL, NULL, '', '0', 'C', '0', '0', NULL, '2021-07-23 16:52:36', NULL, '2021-07-23 16:52:36', '1');
INSERT INTO `sys_menu` VALUES (25326642280793345, '新增资源', 102, 0, '', '/mbs/sysMenu/save', 'sys:menu:add', '', '1', 'F', '0', '0', NULL, '2021-08-02 15:06:27', NULL, '2021-08-02 15:06:27', '0');
INSERT INTO `sys_menu` VALUES (25326782693508481, '修改资源', 102, 0, '', '/mbs/sysMenu/update', 'sys:menu:update', '', '1', 'F', '0', '0', NULL, '2021-08-02 15:07:00', NULL, '2021-08-02 15:07:00', '0');
INSERT INTO `sys_menu` VALUES (25326958510343681, '资源查询', 102, 0, '', '/mbs/sysMenu/get', 'sys:menu:get', '', '1', 'F', '0', '0', NULL, '2021-08-02 15:07:42', NULL, '2021-08-02 15:07:42', '0');
INSERT INTO `sys_menu` VALUES (25327105277429377, '资源删除', 102, 0, '', '/mbs/sysMenu/delete', 'sys:menu:del', '', '1', 'F', '0', '0', NULL, '2021-08-02 15:08:17', NULL, '2021-08-02 15:08:17', '0');
INSERT INTO `sys_menu` VALUES (25327252975650561, '资源状态修改', 102, 0, '', '/mbs/sysMenu/updateStatus', 'sys:menu:status', '', '1', 'F', '0', '0', NULL, '2021-08-02 15:08:52', NULL, '2021-08-02 15:08:52', '0');
INSERT INTO `sys_menu` VALUES (25327785106999617, '显示状态修改', 102, 0, '', '/mbs/sysMenu/updateDisplay', 'sys:menu:display', '', '1', 'F', '0', '0', NULL, '2021-08-02 15:10:59', NULL, '2021-08-02 15:10:59', '0');
INSERT INTO `sys_menu` VALUES (25328050103126593, '新增部门', 103, 0, '', '/mbs/sysDept/save', 'sys:dept:add', '', '1', 'F', '0', '0', NULL, '2021-08-02 15:12:02', NULL, '2021-08-02 15:12:02', '0');
INSERT INTO `sys_menu` VALUES (25328169082948289, '部门修改', 103, 0, '', '/mbs/sysDept/update', 'sys:dept:update', '', '1', 'F', '0', '0', NULL, '2021-08-02 15:12:31', NULL, '2021-08-02 15:12:31', '0');
INSERT INTO `sys_menu` VALUES (25328334560824129, '部门删除', 103, 0, '', '/mbs/sysDept/delete', 'sys:dept:del', '', '1', 'F', '0', '0', NULL, '2021-08-02 15:13:10', NULL, '2021-08-02 15:13:10', '0');
INSERT INTO `sys_menu` VALUES (25328456979975105, '部门查询', 103, 0, '', '/mbs/sysDept/get', 'sys:dept:get', '', '1', 'F', '0', '0', NULL, '2021-08-02 15:13:39', NULL, '2021-08-02 15:13:39', '0');
INSERT INTO `sys_menu` VALUES (25328733552380993, '部门状态修改', 103, 0, '', '/mbs/sysDept/updateDeptStatus', 'sys:dept:status', '', '1', 'F', '0', '0', NULL, '2021-08-02 15:14:45', NULL, '2021-08-02 15:14:45', '0');
INSERT INTO `sys_menu` VALUES (29306517249327233, '操作日志查询', 1081, 0, '', '/mbs/sysLog/get', 'sys:operlog:get', '', '1', 'F', '0', '0', 1, '2021-08-13 14:41:03', NULL, '2021-08-13 14:41:03', '0');
INSERT INTO `sys_menu` VALUES (29306826851877217, '登录日志查询', 1082, 0, '', '/mbs/sysLoginLog/get', 'sys:loginlog:get', '', '1', 'F', '0', '0', 1, '2021-08-13 14:42:17', NULL, '2021-08-13 14:42:17', '0');
INSERT INTO `sys_menu` VALUES (30766371939811713, 'Druid 数据源监控', 2, 0, 'http://localhost:8082/druid', NULL, '', 'icon-druid1', '0', 'C', '0', '0', 1, '2021-08-17 15:21:59', NULL, '2021-08-17 15:21:59', '0');
INSERT INTO `sys_menu` VALUES (30766873402409537, 'Swagger API', 2, 0, 'http://172.18.144.1:8080/doc.html', '', '', 'icon-swagger', '0', 'C', '0', '0', 1, '2021-08-17 15:23:59', NULL, '2021-08-17 15:23:59', '0');
INSERT INTO `sys_menu` VALUES (31793690561216705, 'MINIO 附件管理', 2, 0, 'http://localhost:9000', '', '', 'icon-minio21', '0', 'C', '0', '0', 1, '2021-08-20 11:24:11', NULL, '2021-08-20 11:24:11', '0');
INSERT INTO `sys_menu` VALUES (31834085978540609, 'RabbitMQ管理界面', 2, 0, 'http://localhost:15672', '', '', 'icon-rabbitmq', '0', 'C', '0', '0', 1, '2021-08-20 14:04:42', NULL, '2021-08-20 14:04:42', '0');

-- ----------------------------
-- Table structure for sys_msg
-- ----------------------------
DROP TABLE IF EXISTS `sys_msg`;
CREATE TABLE `sys_msg`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息状态（0：未读，1：已读）',
  `subject` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息主题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息内容',
  `sender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发送人',
  `send_time` datetime NULL DEFAULT NULL COMMENT '发送时间',
  `recipienter` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接收人',
  `recipienter_time` datetime NULL DEFAULT NULL COMMENT '接收时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '消息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_msg
-- ----------------------------

-- ----------------------------
-- Table structure for sys_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client_details`;
CREATE TABLE `sys_oauth_client_details`  (
  `client_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '终端信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oauth_client_details
-- ----------------------------
INSERT INTO `sys_oauth_client_details` VALUES ('brave', NULL, 'brave', 'all', 'password,client_credentials,refresh_token,authorization_code', NULL, NULL, 3600, 7200, NULL, 'true');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
  `role_sort` int(2) NULL DEFAULT NULL COMMENT '显示顺序',
  `role_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色状态（0：正常，1：停用）',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (11140355550347297, '超级管理员', 'ROOT', 0, '0', NULL, '2021-06-24 11:35:12', NULL, '2021-06-24 11:35:12', '0');
INSERT INTO `sys_role` VALUES (19098418089559585, '管理员', 'ADMIN', 1, '0', NULL, '2021-07-16 10:37:42', NULL, '2021-07-16 10:37:42', '0');
INSERT INTO `sys_role` VALUES (20261247282839841, '普通用户', 'USER', 2, '0', NULL, '2021-07-19 15:38:22', NULL, '2021-07-19 15:38:56', '0');
INSERT INTO `sys_role` VALUES (20262651141554497, '测试角色', 'TEST', 3, '0', NULL, '2021-07-19 15:43:57', NULL, '2021-07-19 16:01:38', '0');
INSERT INTO `sys_role` VALUES (20921515872619233, '角儿', 'ABC', 4, '0', NULL, '2021-07-21 11:22:03', NULL, '2021-07-21 11:22:03', '0');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单id',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单中间表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (21650937504333953, 20262651141554497, 21363456221383201, NULL, '2021-07-23 11:40:30', NULL, '2021-07-23 11:40:30');
INSERT INTO `sys_role_menu` VALUES (21650937512722593, 20262651141554497, 21365243133297025, NULL, '2021-07-23 11:40:30', NULL, '2021-07-23 11:40:30');
INSERT INTO `sys_role_menu` VALUES (21725511025689345, 20261247282839841, 103, NULL, '2021-07-23 16:36:50', NULL, '2021-07-23 16:36:50');
INSERT INTO `sys_role_menu` VALUES (21725511029883681, 20261247282839841, 105, NULL, '2021-07-23 16:36:50', NULL, '2021-07-23 16:36:50');
INSERT INTO `sys_role_menu` VALUES (21725511029883713, 20261247282839841, 107, NULL, '2021-07-23 16:36:50', NULL, '2021-07-23 16:36:50');
INSERT INTO `sys_role_menu` VALUES (29291382996730657, 19098418089559585, 1001, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383005119297, 19098418089559585, 100, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383013507937, 19098418089559585, 1002, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383013507969, 19098418089559585, 1003, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383013508001, 19098418089559585, 1004, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383017702337, 19098418089559585, 1005, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383021896673, 19098418089559585, 1006, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383026091009, 19098418089559585, 1007, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383030285345, 19098418089559585, 1008, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383030285377, 19098418089559585, 101, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383038674017, 19098418089559585, 1009, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383038674049, 19098418089559585, 1010, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383042868385, 19098418089559585, 1012, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383042868417, 19098418089559585, 25326642280793345, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383042868449, 19098418089559585, 102, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383042868481, 19098418089559585, 25326782693508481, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383051257121, 19098418089559585, 25326958510343681, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383051257153, 19098418089559585, 25327105277429377, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383055451489, 19098418089559585, 25327252975650561, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383059645825, 19098418089559585, 25327785106999617, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383059645857, 19098418089559585, 25328050103126593, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383063840193, 19098418089559585, 103, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383063840225, 19098418089559585, 25328169082948289, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383063840257, 19098418089559585, 25328334560824129, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383072228897, 19098418089559585, 25328456979975105, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383072228929, 19098418089559585, 25328733552380993, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383076423265, 19098418089559585, 111, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383076423297, 19098418089559585, 112, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383080617633, 19098418089559585, 113, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383080617665, 19098418089559585, 114, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383080617697, 19098418089559585, 3, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383084812033, 19098418089559585, 115, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383084812065, 19098418089559585, 116, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383084812097, 19098418089559585, 21721626760446913, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383084812129, 19098418089559585, 1, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383084812161, 19098418089559585, 2, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (29291383093200801, 19098418089559585, 21363456221383201, 1, '2021-08-13 13:40:55', NULL, '2021-08-13 13:40:55');
INSERT INTO `sys_role_menu` VALUES (31834411230038945, 11140355550347297, 111, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411238427585, 11140355550347297, 112, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411238427617, 11140355550347297, 113, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411242621953, 11140355550347297, 114, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411246816289, 11140355550347297, 3, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411246816321, 11140355550347297, 115, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411251010657, 11140355550347297, 116, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411251010689, 11140355550347297, 1001, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411255205025, 11140355550347297, 100, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411255205057, 11140355550347297, 1, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411259399393, 11140355550347297, 1002, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411259399425, 11140355550347297, 1003, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411259399457, 11140355550347297, 1004, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411263593793, 11140355550347297, 1005, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411263593825, 11140355550347297, 1006, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411267788161, 11140355550347297, 1007, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411267788193, 11140355550347297, 1008, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411271982529, 11140355550347297, 101, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411271982561, 11140355550347297, 1009, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411276176897, 11140355550347297, 1010, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411276176929, 11140355550347297, 1011, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411276176961, 11140355550347297, 1012, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411280371297, 11140355550347297, 25326642280793345, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411280371329, 11140355550347297, 102, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411284565665, 11140355550347297, 25326782693508481, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411284565697, 11140355550347297, 25326958510343681, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411284565729, 11140355550347297, 25327105277429377, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411288760065, 11140355550347297, 25327252975650561, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411288760097, 11140355550347297, 25327785106999617, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411292954433, 11140355550347297, 25328050103126593, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411292954465, 11140355550347297, 103, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411292954497, 11140355550347297, 25328169082948289, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411297148833, 11140355550347297, 25328334560824129, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411297148865, 11140355550347297, 25328456979975105, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411301343201, 11140355550347297, 25328733552380993, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411301343233, 11140355550347297, 104, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411301343265, 11140355550347297, 105, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411305537601, 11140355550347297, 106, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411305537633, 11140355550347297, 107, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411309731969, 11140355550347297, 29306517249327233, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411309732001, 11140355550347297, 1081, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411309732033, 11140355550347297, 108, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411313926369, 11140355550347297, 29306826851877217, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411313926401, 11140355550347297, 1082, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411318120737, 11140355550347297, 30766371939811713, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411318120769, 11140355550347297, 30766873402409537, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411322315105, 11140355550347297, 31793690561216705, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411322315137, 11140355550347297, 31834085978540609, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');
INSERT INTO `sys_role_menu` VALUES (31834411322315169, 11140355550347297, 2, 1, '2021-08-20 14:06:00', NULL, '2021-08-20 14:06:00');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `pass_word` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '联系方式',
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '性别（0：女，1：男）',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `dept_id` bigint(20) NOT NULL COMMENT '部门id',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像url',
  `is_lock` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '是否锁定（0：否，1：是）',
  `is_online` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否在线（0：否，1：是）',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `key_user_name`(`user_name`) USING BTREE COMMENT '用户名唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$fd16kqOharcPH7d2bCyAf.LEQxxOOMr7VIlebXWJoNLWkukEIF4Mu', '18311540852', '2271426934@qq.com', '1', '1995-09-06', 11212766199152673, 'http://127.0.0.1:9000/brave/dnf4.jpg', '0', '1', 1, '2021-07-13 15:23:02', 1, '2021-07-13 15:23:05', '0');
INSERT INTO `sys_user` VALUES (20253808248488065, 'ic', '$2a$10$sGA5SMThQpOPEr.LezzIWekmlrawamjOofw2SlL7clsy00QtE6uaW', '18311540853', '', '1', '2021-07-19', 11227993670156321, 'http://localhost:9000/test/baiyuekui.jpg', '0', '0', NULL, '2021-07-19 15:08:49', NULL, '2021-07-19 15:08:49', '0');
INSERT INTO `sys_user` VALUES (20657600966165793, '1', '$2a$10$Mhz2RXObHJZuhrhK77EvtuLn1IfmgX5cyK2.i3xhr0EDT0RlvKara', '1', '', '2', '2021-07-20', 11212766199152673, 'http://localhost:9000/test/baiyuekui.jpg', '0', '0', NULL, '2021-07-20 17:53:21', NULL, '2021-07-20 17:53:21', '1');
INSERT INTO `sys_user` VALUES (20657987571942977, '测试用户', '$2a$10$X55t3u.a17qHwvgJZWf7R.Ld9FxpFVOktTwaUOi61eZDwIzxle7i6', '13312312311', '1234568@qq.com', '0', '2021-07-20', 21278957483589697, 'http://localhost:9000/test/baiyuekui.jpg', '0', '0', NULL, '2021-07-20 17:54:53', NULL, '2021-07-20 17:54:53', '0');
INSERT INTO `sys_user` VALUES (21725075573047777, 'test', '$2a$10$V18nJ2G7EdyApR4nogvGAO70dSeeQ2BsLs9IkH9ZOQRO8si9Pzely', '13312312312', '', '2', NULL, 21279211218010241, 'http://localhost:9000/test/baiyuekui.jpg', '0', '0', NULL, '2021-07-23 16:35:06', NULL, '2021-07-23 16:35:06', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色中间表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (20657601456899393, 20657600966165793, 11140355550347297, NULL, '2021-07-20 17:53:21', NULL, '2021-07-20 17:53:21');
INSERT INTO `sys_user_role` VALUES (20657601465288033, 20657600966165793, 19098418089559585, NULL, '2021-07-20 17:53:21', NULL, '2021-07-20 17:53:21');
INSERT INTO `sys_user_role` VALUES (22704860134637729, 21725075573047777, 20261247282839841, NULL, '2021-07-26 09:28:25', NULL, '2021-07-26 09:28:25');
INSERT INTO `sys_user_role` VALUES (22711107302982113, 20657987571942977, 20261247282839841, NULL, '2021-07-26 09:53:15', NULL, '2021-07-26 09:53:15');
INSERT INTO `sys_user_role` VALUES (29291195477787233, 20253808248488065, 19098418089559585, 1, '2021-08-13 13:40:10', NULL, '2021-08-13 13:40:10');
INSERT INTO `sys_user_role` VALUES (35837739601494177, 1, 11140355550347297, 1, '2021-08-31 15:13:48', NULL, '2021-08-31 15:13:48');

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'increment id',
  `branch_id` bigint(20) NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int(11) NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime NOT NULL COMMENT 'create datetime',
  `log_modified` datetime NOT NULL COMMENT 'modify datetime',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'AT transaction mode undo table' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
