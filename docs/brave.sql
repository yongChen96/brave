/*
 Navicat Premium Data Transfer

 Source Server         : local5.7
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:13306
 Source Schema         : brave

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 03/08/2021 16:58:17
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门信息表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (25712675904291009, '0', '分页获取用户信息', '/sysUser/page', '192.168.1.113', '', 'POST', '[{\"data\":{\"isLock\":\"\",\"phone\":\"\",\"sex\":\"\",\"userName\":\"\"},\"limit\":1,\"offset\":0,\"page\":{\"current\":1,\"optimizeCountSql\":true,\"orders\":[],\"pages\":0,\"records\":[],\"searchCount\":true,\"size\":10,\"total\":0},\"pageNo\":1,\"pageSize\":10}]', 'Result(code=0, message=操作成功, data=com.baomidou.mybatisplus.extension.plugins.pagination.Page@74a41f1a)', NULL, NULL, NULL, NULL, 'COMPUTER', 'CHROME9', 'Windows 10', NULL, NULL, '2021-08-03 16:40:24', NULL, '2021-08-03 16:40:24');
INSERT INTO `sys_log` VALUES (25712728203067617, '0', '获取用户详细信息', '/sysUser/get', '192.168.1.113', '', 'GET', '[[{\"annotatedType\":{\"annotations\":[],\"declaredAnnotations\":[],\"type\":\"java.lang.Long\"},\"annotations\":[{\"name\":\"\",\"value\":\"\",\"defaultValue\":\"\\n\\t\\t\\n\\t\\t\\n\\n\\t\\t\\t\\t\\n\",\"required\":true}],\"declaringExecutable\":{\"accessible\":false,\"annotatedExceptionTypes\":[],\"annotatedParameterTypes\":[{\"annotations\":[],\"declaredAnnotations\":[],\"type\":\"java.lang.Long\"}],\"annotatedReceiverType\":{\"annotations\":[],\"declaredAnnotations\":[],\"type\":\"com.cloud.brave.controller.SysUserController\"},\"annotatedReturnType\":{\"annotatedActualTypeArguments\":[{\"annotations\":[],\"declaredAnnotations\":[],\"type\":\"com.cloud.brave.dto.UserDetailsDTO\"}],\"annotations\":[],\"declaredAnnotations\":[],\"type\":{\"actualTypeArguments\":[\"com.cloud.brave.dto.UserDetailsDTO\"],\"rawType\":\"Result\",\"typeName\":\"Result<com.cloud.brave.dto.UserDetailsDTO>\"}},\"annotations\":[{\"path\":[],\"headers\":[],\"name\":\"\",\"produces\":[],\"params\":[],\"value\":[\"/get\"],\"consumes\":[]},{\"value\":\"获取用户详细信息\"},{\"code\":200,\"notes\":\"获取用户详细信息\",\"hidden\":false,\"authorizations\":[{\"scopes\":[{\"scope\":\"\",\"description\":\"\"}],\"value\":\"\"}],\"httpMethod\":\"\",\"tags\":[\"\"],\"extensions\":[{\"name\":\"\",\"properties\":[{\"name\":\"\",\"value\":\"\"}]}],\"responseHeaders\":[{\"response\":\"java.lang.Void\",\"name\":\"\",\"responseContainer\":\"\",\"description\":\"\"}],\"response\":\"java.lang.Void\",\"nickname\":\"\",\"produces\":\"\",\"responseReference\":\"\",\"responseContainer\":\"\",\"ignoreJsonView\":false,\"position\":0,\"protocols\":\"\",\"value\":\"获取用户详细信息\",\"consumes\":\"\"}],\"bridge\":false,\"declaringClass\":\"com.cloud.brave.controller.SysUserController\",\"default\":false,\"exceptionTypes\":[],\"genericExceptionTypes\":[],\"genericParameterTypes\":[\"java.lang.Long\"],\"genericReturnType\":{\"$ref\":\"$[0][0].declaringExecutable.annotatedReturnType.type\"},\"modifiers\":1,\"name\":\"getDetailsById\",\"parameterAnnotations\":[[{\"name\":\"\",\"value\":\"\",\"defaultValue\":\"\\n\\t\\t\\n\\t\\t\\n\\n\\t\\t\\t\\t\\n\",\"required\":true}]],\"parameterCount\":1,\"parameterTypes\":[\"java.lang.Long\"],\"returnType\":\"Result\",\"synthetic\":false,\"typeParameters\":[],\"varArgs\":false},\"implicit\":false,\"modifiers\":0,\"name\":\"arg0\",\"namePresent\":false,\"parameterizedType\":\"java.lang.Long\",\"synthetic\":false,\"type\":\"java.lang.Long\",\"varArgs\":false}]]', 'Result(code=0, message=操作成功, data=UserDetailsDTO(sysDept=SysDept(parentId=0, deptName=技术部, idPath=11212766199152673, namePath=技术部, sort=0, leader=brave, phone=13345678912, email=123456789@123.com, deptStatus=0, delState=0), roles=[11140355550347297]))', NULL, NULL, NULL, NULL, 'COMPUTER', 'CHROME9', 'Windows 10', NULL, NULL, '2021-08-03 16:40:37', NULL, '2021-08-03 16:40:37');
INSERT INTO `sys_log` VALUES (25712804740727041, '0', '获取用户信息', '/sysUser/getUserInfo', '192.168.1.113', '', 'GET', '[[{\"annotatedType\":{\"annotations\":[],\"declaredAnnotations\":[],\"type\":\"java.lang.String\"},\"annotations\":[{\"name\":\"\",\"value\":\"username\",\"defaultValue\":\"\\n\\t\\t\\n\\t\\t\\n\\n\\t\\t\\t\\t\\n\",\"required\":true}],\"declaringExecutable\":{\"accessible\":false,\"annotatedExceptionTypes\":[],\"annotatedParameterTypes\":[{\"annotations\":[],\"declaredAnnotations\":[],\"type\":\"java.lang.String\"}],\"annotatedReceiverType\":{\"annotations\":[],\"declaredAnnotations\":[],\"type\":\"com.cloud.brave.controller.SysUserController\"},\"annotatedReturnType\":{\"annotatedActualTypeArguments\":[{\"annotations\":[],\"declaredAnnotations\":[],\"type\":\"com.cloud.brave.dto.UserInfoDTO\"}],\"annotations\":[],\"declaredAnnotations\":[],\"type\":{\"actualTypeArguments\":[\"com.cloud.brave.dto.UserInfoDTO\"],\"rawType\":\"Result\",\"typeName\":\"Result<com.cloud.brave.dto.UserInfoDTO>\"}},\"annotations\":[{\"path\":[],\"headers\":[],\"name\":\"\",\"produces\":[],\"params\":[],\"value\":[\"/getUserInfo\"],\"consumes\":[]},{\"value\":\"获取用户信息\"},{\"code\":200,\"notes\":\"获取用户信息\",\"hidden\":false,\"authorizations\":[{\"scopes\":[{\"scope\":\"\",\"description\":\"\"}],\"value\":\"\"}],\"httpMethod\":\"\",\"tags\":[\"\"],\"extensions\":[{\"name\":\"\",\"properties\":[{\"name\":\"\",\"value\":\"\"}]}],\"responseHeaders\":[{\"response\":\"java.lang.Void\",\"name\":\"\",\"responseContainer\":\"\",\"description\":\"\"}],\"response\":\"java.lang.Void\",\"nickname\":\"\",\"produces\":\"\",\"responseReference\":\"\",\"responseContainer\":\"\",\"ignoreJsonView\":false,\"position\":0,\"protocols\":\"\",\"value\":\"获取用户信息\",\"consumes\":\"\"}],\"bridge\":false,\"declaringClass\":\"com.cloud.brave.controller.SysUserController\",\"default\":false,\"exceptionTypes\":[],\"genericExceptionTypes\":[],\"genericParameterTypes\":[\"java.lang.String\"],\"genericReturnType\":{\"$ref\":\"$[0][0].declaringExecutable.annotatedReturnType.type\"},\"modifiers\":1,\"name\":\"getUserInfo\",\"parameterAnnotations\":[[{\"name\":\"\",\"value\":\"username\",\"defaultValue\":\"\\n\\t\\t\\n\\t\\t\\n\\n\\t\\t\\t\\t\\n\",\"required\":true}]],\"parameterCount\":1,\"parameterTypes\":[\"java.lang.String\"],\"returnType\":\"Result\",\"synthetic\":false,\"typeParameters\":[],\"varArgs\":false},\"implicit\":false,\"modifiers\":0,\"name\":\"arg0\",\"namePresent\":false,\"parameterizedType\":\"java.lang.String\",\"synthetic\":false,\"type\":\"java.lang.String\",\"varArgs\":false}]]', 'Result(code=0, message=操作成功, data=UserInfoDTO(sysUser=SysUser(userName=admin, passWord={bcrypt}$2a$10$dWDGXp1uZ3CSVsORfBaOouqsE4ZvhTREqQppukpxRZAydvIuKoNze, phone=18311540852, email=123456789@emil.com, sex=1, birthday=1995-09-06, deptId=11212766199152673, avatarUrl=http://localhost:9000/test/baiyuekui.jpg, isLock=0, isOnline=1, delState=0), permissions=[sys:operlog:list, sys:user:get, sys:menu:update, sys:menu:status, sys:dept:get, sys:dept:update, sys:menu:del, sys:menu:add, sys:user:add, sys:user:export, sys:role:get, sys:role:update, sys:dept:del, sys:user:import, sys:role:export, sys:menu:get, sys:dept:add, sys:user:update, sys:dept:status, sys:logininfor:list, sys:menu:display, sys:user:resetPwd, sys:role:add, sys:user:del], roles=[11140355550347297]))', NULL, NULL, NULL, NULL, 'UNKNOWN', 'BOT', 'Unknown', NULL, NULL, '2021-08-03 16:40:55', NULL, '2021-08-03 16:40:55');
INSERT INTO `sys_log` VALUES (25712809727754529, '0', '获取当前登录用户信息', '/sysUser/info', '192.168.1.113', '', 'GET', '[]', 'Result(code=0, message=操作成功, data=UserInfoDTO(sysUser=SysUser(userName=admin, passWord={bcrypt}$2a$10$dWDGXp1uZ3CSVsORfBaOouqsE4ZvhTREqQppukpxRZAydvIuKoNze, phone=18311540852, email=123456789@emil.com, sex=1, birthday=1995-09-06, deptId=11212766199152673, avatarUrl=http://localhost:9000/test/baiyuekui.jpg, isLock=0, isOnline=1, delState=0), permissions=[sys:operlog:list, sys:user:get, sys:menu:update, sys:menu:status, sys:dept:get, sys:dept:update, sys:menu:del, sys:menu:add, sys:user:add, sys:user:export, sys:role:get, sys:role:update, sys:dept:del, sys:user:import, sys:role:export, sys:menu:get, sys:dept:add, sys:user:update, sys:dept:status, sys:logininfor:list, sys:menu:display, sys:user:resetPwd, sys:role:add, sys:user:del], roles=[11140355550347297]))', NULL, NULL, NULL, NULL, 'COMPUTER', 'CHROME9', 'Windows 10', NULL, NULL, '2021-08-03 16:40:56', NULL, '2021-08-03 16:40:56');
INSERT INTO `sys_log` VALUES (25712809778086209, '0', '分页获取用户信息', '/sysUser/page', '192.168.1.113', '', 'POST', '[{\"data\":{\"isLock\":\"\",\"phone\":\"\",\"sex\":\"\",\"userName\":\"\"},\"limit\":1,\"offset\":0,\"page\":{\"current\":1,\"optimizeCountSql\":true,\"orders\":[],\"pages\":0,\"records\":[],\"searchCount\":true,\"size\":10,\"total\":0},\"pageNo\":1,\"pageSize\":10}]', 'Result(code=0, message=操作成功, data=com.baomidou.mybatisplus.extension.plugins.pagination.Page@5f097f5)', NULL, NULL, NULL, NULL, 'COMPUTER', 'CHROME9', 'Windows 10', NULL, NULL, '2021-08-03 16:40:56', NULL, '2021-08-03 16:40:56');
INSERT INTO `sys_log` VALUES (25713971768066401, '0', '分页获取用户信息', '/sysUser/page', '192.168.1.113', '', 'POST', '[{\"data\":{\"deptId\":21278957483589697,\"isLock\":\"\",\"phone\":\"\",\"sex\":\"\",\"userName\":\"\"},\"limit\":1,\"offset\":0,\"page\":{\"current\":1,\"optimizeCountSql\":true,\"orders\":[],\"pages\":0,\"records\":[],\"searchCount\":true,\"size\":10,\"total\":0},\"pageNo\":1,\"pageSize\":10}]', 'Result(code=0, message=操作成功, data=com.baomidou.mybatisplus.extension.plugins.pagination.Page@2e3c72b5)', NULL, NULL, NULL, NULL, 'COMPUTER', 'CHROME9', 'Windows 10', NULL, NULL, '2021-08-03 16:45:33', NULL, '2021-08-03 16:45:33');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 25328733552380994 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

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
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 10, '/log', NULL, NULL, 'icon-shujuzhunquexingxiaoyan', '1', 'C', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'online', NULL, NULL, 'online', '1', 'C', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, 'job', NULL, NULL, 'job', '1', 'C', '0', '0', 1, '2021-07-12 08:05:47', 1, '2021-07-12 08:05:47', '0');
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
INSERT INTO `sys_menu` VALUES (1011, '角色删除', 101, 4, '', '/mbs/sysRole/delete', 'sys:role:del', '#', '1', 'F', '0', '0', 1, '2021-07-14 06:09:02', 1, '2021-07-14 06:09:02', '');
INSERT INTO `sys_menu` VALUES (1012, '角色导出', 101, 5, '', NULL, 'sys:role:export', '#', '1', 'F', '0', '0', 1, '2021-07-14 06:09:02', 1, '2021-07-14 06:09:02', '0');
INSERT INTO `sys_menu` VALUES (1081, '操作日志', 108, 0, 'operlog', 'system/operlog/index', 'sys:operlog:list', 'form', '1', 'C', '0', '0', 1, '2021-07-15 10:43:26', 1, '2021-07-15 10:43:30', '0');
INSERT INTO `sys_menu` VALUES (1082, '登录日志', 108, 1, 'logininfor', 'system/logininfor/index', 'sys:logininfor:list', 'logininfor', '1', 'C', '0', '0', 1, '2021-07-15 10:44:29', 1, '2021-07-15 10:44:34', '0');
INSERT INTO `sys_menu` VALUES (21363456221383201, '测试目录', 0, 3, '/ceshi', NULL, NULL, 'ogininfor', '1', 'M', '0', '0', NULL, '2021-07-22 16:38:10', NULL, '2021-07-22 16:38:10', '0');
INSERT INTO `sys_menu` VALUES (21365243133297025, '测试菜单1', 21363456221383201, 0, '/cscd', NULL, NULL, 'ogininfor', '1', 'C', '0', '0', NULL, '2021-07-22 16:45:16', NULL, '2021-07-22 16:45:16', '0');
INSERT INTO `sys_menu` VALUES (21367332555194465, '测试按钮', 21365243133297025, 0, 'csan', NULL, 'ceshi:cscd:an', '', '1', 'F', '0', '0', NULL, '2021-07-22 16:53:34', NULL, '2021-07-22 16:53:34', '1');
INSERT INTO `sys_menu` VALUES (21721626760446913, '测试菜单2', 21363456221383201, 0, '/ceshi', NULL, NULL, '', '1', 'C', '0', '0', NULL, '2021-07-23 16:21:24', NULL, '2021-07-23 16:21:24', '0');
INSERT INTO `sys_menu` VALUES (21729478858246113, 'cccccc', 21365243133297025, 0, '/234', NULL, NULL, '', '0', 'C', '0', '0', NULL, '2021-07-23 16:52:36', NULL, '2021-07-23 16:52:36', '0');
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
INSERT INTO `sys_menu` VALUES (25328733552380993, '部门状态修改', 103, 0, '', '/mbs/\'/sysDept/updateDeptStatus', 'sys:dept:status', '', '1', 'F', '0', '0', NULL, '2021-08-02 15:14:45', NULL, '2021-08-02 15:14:45', '0');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '终端信息表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (21650937504333953, 20262651141554497, 21363456221383201, NULL, '2021-07-23 11:40:30', NULL, '2021-07-23 11:40:30');
INSERT INTO `sys_role_menu` VALUES (21650937512722593, 20262651141554497, 21365243133297025, NULL, '2021-07-23 11:40:30', NULL, '2021-07-23 11:40:30');
INSERT INTO `sys_role_menu` VALUES (21725511025689345, 20261247282839841, 103, NULL, '2021-07-23 16:36:50', NULL, '2021-07-23 16:36:50');
INSERT INTO `sys_role_menu` VALUES (21725511029883681, 20261247282839841, 105, NULL, '2021-07-23 16:36:50', NULL, '2021-07-23 16:36:50');
INSERT INTO `sys_role_menu` VALUES (21725511029883713, 20261247282839841, 107, NULL, '2021-07-23 16:36:50', NULL, '2021-07-23 16:36:50');
INSERT INTO `sys_role_menu` VALUES (25331484692842081, 11140355550347297, 2, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484697036417, 11140355550347297, 109, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484697036449, 11140355550347297, 110, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484701230785, 11140355550347297, 111, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484701230817, 11140355550347297, 112, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484701230849, 11140355550347297, 113, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484701230881, 11140355550347297, 3, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484705425217, 11140355550347297, 114, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484705425249, 11140355550347297, 115, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484705425281, 11140355550347297, 116, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484705425313, 11140355550347297, 21721626760446913, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484709619649, 11140355550347297, 21363456221383201, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484709619681, 11140355550347297, 21365243133297025, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484713814017, 11140355550347297, 21729478858246113, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484713814049, 11140355550347297, 1, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484713814081, 11140355550347297, 100, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484718008417, 11140355550347297, 1001, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484718008449, 11140355550347297, 1002, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484718008481, 11140355550347297, 1003, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484718008513, 11140355550347297, 1004, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484718008545, 11140355550347297, 1005, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484722202881, 11140355550347297, 1006, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484722202913, 11140355550347297, 1007, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484722202945, 11140355550347297, 101, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484722202977, 11140355550347297, 1008, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484726397313, 11140355550347297, 1009, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484726397345, 11140355550347297, 1010, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484726397377, 11140355550347297, 1012, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484726397409, 11140355550347297, 102, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484726397441, 11140355550347297, 25326642280793345, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484730591777, 11140355550347297, 25326782693508481, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484730591809, 11140355550347297, 25326958510343681, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484730591841, 11140355550347297, 25327105277429377, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484730591873, 11140355550347297, 25327252975650561, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484734786209, 11140355550347297, 25327785106999617, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484734786241, 11140355550347297, 103, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484734786273, 11140355550347297, 25328050103126593, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484734786305, 11140355550347297, 25328169082948289, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484734786337, 11140355550347297, 25328334560824129, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484738980673, 11140355550347297, 25328456979975105, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484738980705, 11140355550347297, 25328733552380993, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484738980737, 11140355550347297, 104, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484738980769, 11140355550347297, 105, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484743175105, 11140355550347297, 106, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484743175137, 11140355550347297, 107, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484743175169, 11140355550347297, 108, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484747369505, 11140355550347297, 1081, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');
INSERT INTO `sys_role_menu` VALUES (25331484747369537, 11140355550347297, 1082, NULL, '2021-08-02 15:25:41', NULL, '2021-08-02 15:25:41');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '{bcrypt}$2a$10$dWDGXp1uZ3CSVsORfBaOouqsE4ZvhTREqQppukpxRZAydvIuKoNze', '18311540852', '123456789@emil.com', '1', '1995-09-06', 11212766199152673, 'http://localhost:9000/test/baiyuekui.jpg', '0', '1', 1, '2021-07-13 15:23:02', 1, '2021-07-13 15:23:05', '0');
INSERT INTO `sys_user` VALUES (20253808248488065, 'ic', '{bcrypt}$2a$10$sGA5SMThQpOPEr.LezzIWekmlrawamjOofw2SlL7clsy00QtE6uaW', '13308520258', '', '1', '2021-07-19', 11227993670156321, 'http://localhost:9000/test/baiyuekui.jpg', '0', '0', NULL, '2021-07-19 15:08:49', NULL, '2021-07-19 15:08:49', '0');
INSERT INTO `sys_user` VALUES (20657600966165793, '1', '{bcrypt}$2a$10$Mhz2RXObHJZuhrhK77EvtuLn1IfmgX5cyK2.i3xhr0EDT0RlvKara', '1', '', '0', '2021-07-20', 11212766199152673, 'http://localhost:9000/test/baiyuekui.jpg', '0', '0', NULL, '2021-07-20 17:53:21', NULL, '2021-07-20 17:53:21', '1');
INSERT INTO `sys_user` VALUES (20657987571942977, '测试用户', '{bcrypt}$2a$10$X55t3u.a17qHwvgJZWf7R.Ld9FxpFVOktTwaUOi61eZDwIzxle7i6', '13312312311', '1234568@qq.com', '1', '2021-07-20', 21278957483589697, 'http://localhost:9000/test/baiyuekui.jpg', '0', '0', NULL, '2021-07-20 17:54:53', NULL, '2021-07-20 17:54:53', '0');
INSERT INTO `sys_user` VALUES (21725075573047777, 'test', '{bcrypt}$2a$10$V18nJ2G7EdyApR4nogvGAO70dSeeQ2BsLs9IkH9ZOQRO8si9Pzely', '13312312312', '', '0', NULL, 21279211218010241, 'http://localhost:9000/test/baiyuekui.jpg', '0', '0', NULL, '2021-07-23 16:35:06', NULL, '2021-07-23 16:35:06', '0');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 11140355550347297, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` VALUES (20253808693084321, 20253808248488065, 19098418089559585, NULL, '2021-07-19 15:08:49', NULL, '2021-07-19 15:08:49');
INSERT INTO `sys_user_role` VALUES (20657601456899393, 20657600966165793, 11140355550347297, NULL, '2021-07-20 17:53:21', NULL, '2021-07-20 17:53:21');
INSERT INTO `sys_user_role` VALUES (20657601465288033, 20657600966165793, 19098418089559585, NULL, '2021-07-20 17:53:21', NULL, '2021-07-20 17:53:21');
INSERT INTO `sys_user_role` VALUES (22704860134637729, 21725075573047777, 20261247282839841, NULL, '2021-07-26 09:28:25', NULL, '2021-07-26 09:28:25');
INSERT INTO `sys_user_role` VALUES (22711107302982113, 20657987571942977, 20261247282839841, NULL, '2021-07-26 09:53:15', NULL, '2021-07-26 09:53:15');

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'AT transaction mode undo table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

-- ----------------------------
-- View structure for v_emp
-- ----------------------------
DROP VIEW IF EXISTS `v_emp`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_emp` AS select `c_gxqpt_emp`.`id` AS `id`,`c_gxqpt_emp`.`name_` AS `name_`,`c_gxqpt_emp`.`nickname` AS `nickname`,`c_gxqpt_emp`.`passwordmd5` AS `passwordmd5`,`c_gxqpt_emp`.`passwordsha` AS `passwordsha`,`c_gxqpt_emp`.`sex_` AS `sex_`,`c_gxqpt_emp`.`birthday_` AS `birthday_`,`c_gxqpt_emp`.`nation_` AS `nation_`,`c_gxqpt_emp`.`photo` AS `photo`,`c_gxqpt_emp`.`mainorgid` AS `mainorgid`,`c_gxqpt_emp`.`mainorgname` AS `mainorgname`,`c_gxqpt_emp`.`maindeptid` AS `maindeptid`,`c_gxqpt_emp`.`maindeptname` AS `maindeptname`,`c_gxqpt_emp`.`mainduty` AS `mainduty`,`c_gxqpt_emp`.`maindutyname` AS `maindutyname`,`c_gxqpt_emp`.`mainpost` AS `mainpost`,`c_gxqpt_emp`.`mainpostname` AS `mainpostname`,`c_gxqpt_emp`.`officetel` AS `officetel`,`c_gxqpt_emp`.`mainmobile` AS `mainmobile`,`c_gxqpt_emp`.`submobile` AS `submobile`,`c_gxqpt_emp`.`isorgadmin` AS `isorgadmin`,`c_gxqpt_emp`.`isdelete` AS `isdelete`,`c_gxqpt_emp`.`isenable` AS `isenable`,`c_gxqpt_emp`.`usertype` AS `usertype`,`c_gxqpt_emp`.`status_` AS `status_`,`c_gxqpt_emp`.`descrption` AS `descrption`,`c_gxqpt_emp`.`iscommittee` AS `iscommittee`,`c_gxqpt_emp`.`isdeputy` AS `isdeputy`,`c_gxqpt_emp`.`isheader` AS `isheader`,`c_gxqpt_emp`.`isleader` AS `isleader`,`c_gxqpt_emp`.`userduty` AS `userduty`,`c_gxqpt_emp`.`sortvalue` AS `sortvalue`,`c_gxqpt_emp`.`dutylevel` AS `dutylevel`,`c_gxqpt_emp`.`otherid` AS `otherid`,`c_gxqpt_emp`.`create_user` AS `create_user`,`c_gxqpt_emp`.`create_time` AS `create_time`,`c_gxqpt_emp`.`update_user` AS `update_user`,`c_gxqpt_emp`.`update_time` AS `update_time`,`c_gxqpt_emp`.`logid` AS `logid`,`c_gxqpt_emp`.`gxqpt_emp_id` AS `gxqpt_emp_id`,'gxqpt' AS `system` from `c_gxqpt_emp` union select `c_sdzzww_emp`.`id` AS `id`,`c_sdzzww_emp`.`name_` AS `name_`,`c_sdzzww_emp`.`nickname` AS `nickname`,`c_sdzzww_emp`.`passwordmd5` AS `passwordmd5`,`c_sdzzww_emp`.`passwordsha` AS `passwordsha`,`c_sdzzww_emp`.`sex_` AS `sex_`,`c_sdzzww_emp`.`birthday_` AS `birthday_`,`c_sdzzww_emp`.`nation_` AS `nation_`,`c_sdzzww_emp`.`photo` AS `photo`,`c_sdzzww_emp`.`mainorgid` AS `mainorgid`,`c_sdzzww_emp`.`mainorgname` AS `mainorgname`,`c_sdzzww_emp`.`maindeptid` AS `maindeptid`,`c_sdzzww_emp`.`maindeptname` AS `maindeptname`,`c_sdzzww_emp`.`mainduty` AS `mainduty`,`c_sdzzww_emp`.`maindutyname` AS `maindutyname`,`c_sdzzww_emp`.`mainpost` AS `mainpost`,`c_sdzzww_emp`.`mainpostname` AS `mainpostname`,`c_sdzzww_emp`.`officetel` AS `officetel`,`c_sdzzww_emp`.`mainmobile` AS `mainmobile`,`c_sdzzww_emp`.`submobile` AS `submobile`,`c_sdzzww_emp`.`isorgadmin` AS `isorgadmin`,`c_sdzzww_emp`.`isdelete` AS `isdelete`,`c_sdzzww_emp`.`isenable` AS `isenable`,`c_sdzzww_emp`.`usertype` AS `usertype`,`c_sdzzww_emp`.`status_` AS `status_`,`c_sdzzww_emp`.`descrption` AS `descrption`,`c_sdzzww_emp`.`iscommittee` AS `iscommittee`,`c_sdzzww_emp`.`isdeputy` AS `isdeputy`,`c_sdzzww_emp`.`isheader` AS `isheader`,`c_sdzzww_emp`.`isleader` AS `isleader`,`c_sdzzww_emp`.`userduty` AS `userduty`,`c_sdzzww_emp`.`sortvalue` AS `sortvalue`,`c_sdzzww_emp`.`dutylevel` AS `dutylevel`,`c_sdzzww_emp`.`otherid` AS `otherid`,`c_sdzzww_emp`.`create_user` AS `create_user`,`c_sdzzww_emp`.`create_time` AS `create_time`,`c_sdzzww_emp`.`update_user` AS `update_user`,`c_sdzzww_emp`.`update_time` AS `update_time`,`c_sdzzww_emp`.`logid` AS `logid`,`c_sdzzww_emp`.`gxqpt_emp_id` AS `gxqpt_emp_id`,'sdzzww' AS `system` from `c_sdzzww_emp`;

-- ----------------------------
-- View structure for v_org
-- ----------------------------
DROP VIEW IF EXISTS `v_org`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_org` AS select `c_gxqpt_org`.`id` AS `id`,`c_gxqpt_org`.`parent_id` AS `parent_id`,`c_gxqpt_org`.`org_id` AS `org_id`,`c_gxqpt_org`.`name_` AS `name_`,`c_gxqpt_org`.`short_name` AS `short_name`,`c_gxqpt_org`.`else_name` AS `else_name`,`c_gxqpt_org`.`for_type` AS `for_type`,`c_gxqpt_org`.`for_class` AS `for_class`,`c_gxqpt_org`.`forindustry` AS `forindustry`,`c_gxqpt_org`.`orgadmin` AS `orgadmin`,`c_gxqpt_org`.`zipcode` AS `zipcode`,`c_gxqpt_org`.`telcode` AS `telcode`,`c_gxqpt_org`.`faxcode` AS `faxcode`,`c_gxqpt_org`.`status_` AS `status_`,`c_gxqpt_org`.`sortvalue` AS `sortvalue`,`c_gxqpt_org`.`descrption` AS `descrption`,`c_gxqpt_org`.`forarea` AS `forarea`,`c_gxqpt_org`.`forcity` AS `forcity`,`c_gxqpt_org`.`forcounty` AS `forcounty`,`c_gxqpt_org`.`fortown` AS `fortown`,`c_gxqpt_org`.`forgk` AS `forgk`,`c_gxqpt_org`.`fornodetype` AS `fornodetype`,`c_gxqpt_org`.`orgduty` AS `orgduty`,`c_gxqpt_org`.`isdelete` AS `isdelete`,`c_gxqpt_org`.`isenable` AS `isenable`,`c_gxqpt_org`.`create_user` AS `create_user`,`c_gxqpt_org`.`create_time` AS `create_time`,`c_gxqpt_org`.`update_user` AS `update_user`,`c_gxqpt_org`.`update_time` AS `update_time`,`c_gxqpt_org`.`logid` AS `logid`,`c_gxqpt_org`.`otherid` AS `otherid`,`c_gxqpt_org`.`icon_url` AS `icon_url`,'gxqpt' AS `system` from `c_gxqpt_org` union select `c_sdzzww_org`.`id` AS `id`,`c_sdzzww_org`.`parent_id` AS `parent_id`,`c_sdzzww_org`.`org_id` AS `org_id`,`c_sdzzww_org`.`name_` AS `name_`,`c_sdzzww_org`.`short_name` AS `short_name`,`c_sdzzww_org`.`else_name` AS `else_name`,`c_sdzzww_org`.`for_type` AS `for_type`,`c_sdzzww_org`.`for_class` AS `for_class`,`c_sdzzww_org`.`forindustry` AS `forindustry`,`c_sdzzww_org`.`orgadmin` AS `orgadmin`,`c_sdzzww_org`.`zipcode` AS `zipcode`,`c_sdzzww_org`.`telcode` AS `telcode`,`c_sdzzww_org`.`faxcode` AS `faxcode`,`c_sdzzww_org`.`status_` AS `status_`,`c_sdzzww_org`.`sortvalue` AS `sortvalue`,`c_sdzzww_org`.`descrption` AS `descrption`,`c_sdzzww_org`.`forarea` AS `forarea`,`c_sdzzww_org`.`forcity` AS `forcity`,`c_sdzzww_org`.`forcounty` AS `forcounty`,`c_sdzzww_org`.`fortown` AS `fortown`,`c_sdzzww_org`.`forgk` AS `forgk`,`c_sdzzww_org`.`fornodetype` AS `fornodetype`,`c_sdzzww_org`.`orgduty` AS `orgduty`,`c_sdzzww_org`.`isdelete` AS `isdelete`,`c_sdzzww_org`.`isenable` AS `isenable`,`c_sdzzww_org`.`create_user` AS `create_user`,`c_sdzzww_org`.`create_time` AS `create_time`,`c_sdzzww_org`.`update_user` AS `update_user`,`c_sdzzww_org`.`update_time` AS `update_time`,`c_sdzzww_org`.`logid` AS `logid`,`c_sdzzww_org`.`otherid` AS `otherid`,`c_sdzzww_org`.`icon_url` AS `icon_url`,'sdzzww' AS `system` from `c_sdzzww_org`;

-- ----------------------------
-- View structure for v_tag_cloud
-- ----------------------------
DROP VIEW IF EXISTS `v_tag_cloud`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_tag_cloud` AS select count(0) AS `num`,'API' AS `type` from `b_share_data_api` where ((`b_share_data_api`.`api_status` = 1) and (`b_share_data_api`.`auth_status` = 3)) union all select count(0) AS `num`,'已上架目录' AS `type` from `b_share_data_directory_ware` where ((`b_share_data_directory_ware`.`dir_type` = 2) and (`b_share_data_directory_ware`.`status_` = 4)) union all select count(0) AS `num`,'已上架数据集' AS `type` from `b_share_data_set_ware` where (`b_share_data_set_ware`.`status_` = 3) union all select count(0) AS `num`,'接入单位' AS `type` from `b_share_data_directory_ware` where ((`b_share_data_directory_ware`.`dir_type` = 1) and (`b_share_data_directory_ware`.`status_` = 4)) union all select count(0) AS `num`,'开放目录' AS `type` from `b_share_data_directory_ware` where ((`b_share_data_directory_ware`.`dir_type` = 2) and (`b_share_data_directory_ware`.`status_` = 4) and (`b_share_data_directory_ware`.`open_way` = 1)) union all select count(0) AS `num`,'不开放目录' AS `type` from `b_share_data_directory_ware` where ((`b_share_data_directory_ware`.`dir_type` = 2) and (`b_share_data_directory_ware`.`status_` = 4) and (`b_share_data_directory_ware`.`open_way` = 2)) union all select count(0) AS `num`,'结构化数据' AS `type` from `b_share_data_directory_ware` where ((`b_share_data_directory_ware`.`dir_type` = 2) and (`b_share_data_directory_ware`.`status_` = 4) and (`b_share_data_directory_ware`.`data_type` = 1)) union all select count(0) AS `num`,'非结构化数据' AS `type` from `b_share_data_directory_ware` where ((`b_share_data_directory_ware`.`dir_type` = 2) and (`b_share_data_directory_ware`.`status_` = 4) and (`b_share_data_directory_ware`.`data_type` = 2)) union all select count(0) AS `num`,'API申请量' AS `API申请量` from `b_share_flow_audit` where ((`b_share_flow_audit`.`status_` = 1) and (`b_share_flow_audit`.`proc_code` = 'API_APPLY')) union all select count(0) AS `num`,'API调用次数' AS `API调用次数` from `b_share_data_api_call` where ((`b_share_data_api_call`.`fail_type` = 1) or isnull(`b_share_data_api_call`.`fail_type`));

SET FOREIGN_KEY_CHECKS = 1;
