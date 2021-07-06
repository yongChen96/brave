/*
 Navicat Premium Data Transfer

 Source Server         : local5.7
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:13306
 Source Schema         : nacos

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 06/07/2021 17:16:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (2, 'brave-auth-dev.yml', 'DEFAULT_GROUP', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:13306/brave?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n  redis:\n    database: 0\n    port: 6379\n    password: 123456\n    host: 127.0.0.1\n# Spring Boot Admin 开启和暴露所有端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always', '0be80bf7b32946f5fa338e2f2f5452b1', '2021-06-23 05:57:37', '2021-06-28 09:13:52', 'nacos', '172.17.0.1', '', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (4, 'brave-mbs-rest-dev.yml', 'DEFAULT_GROUP', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:13306/brave?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n  redis:\n    database: 0\n    port: 6379\n    password: 123456\n    host: 127.0.0.1\n# Spring Boot Admin 开启和暴露所有端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always\nmybatis-plus:\n  config-location:\n    - classpath*:/mapper/**/*Mapper.xml', 'fab3d7aaff6b7646613c0bd9d5995b10', '2021-06-23 06:05:22', '2021-06-28 09:05:26', 'nacos', '172.17.0.1', '', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (6, 'brave-gateway-dev.yml', 'DEFAULT_GROUP', 'spring:\n  security:\n    oauth2:\n      resourceserver:\n        jwt:\n          jwk-set-uri: \'http://localhost:8081/rsa/getPublicKey\'\n  redis:\n    database: 0\n    port: 6379\n    password: 123456\n    host: 127.0.0.1\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n          lower-case-service-id: true\n      routes:\n        - id: brave-auth\n          uri: lb://brave-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n        - id: brave-mbs-rest\n          uri: lb://brave-mbs-rest\n          predicates:\n            - Path=/mbs/**\n          filters:\n            - StripPrefix=1\nsecure:\n  ignore:\n    - auth/oauth/token\n# Spring Boot Admin 开启和暴露所有端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always', 'f284e2fd4a448b90a05fe1e8594a7a30', '2021-06-23 06:06:43', '2021-06-30 07:20:29', 'nacos', '172.17.0.1', '', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (15, 'brave-visual-admin-dev.yml', 'DEFAULT_GROUP', 'spring:\n  boot:\n    admin:\n      notify:\n        mail:\n          to: 2271426934@qq.com\n          from: yongchencloud@163.com\n  mail:\n    host: smtp.163.com\n    username: yongchencloud@163.com\n    password: FVXSADFZHPJEWEIV\n    properties:\n      mail:\n        debug: false\n        smtp:\n          auth: true  #安全认证(默认是true)\n          starttls:\n            enable: true\n            required: true\n# 开启和暴露所有端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always', 'f0bc896cce88aecccb0283cc25bfe8c9', '2021-06-28 08:59:50', '2021-06-29 06:49:58', 'nacos', '172.17.0.1', '', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (16, 'brave-flowable-dev.yml', 'DEFAULT_GROUP', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:13306/flowable?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root', 'fc1ee18c611259d2c14deb3329b07b2e', '2021-07-02 02:59:56', '2021-07-02 03:00:29', 'nacos', '172.17.0.1', '', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65', '', '', '', 'yaml', '');

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint(64) UNSIGNED NOT NULL,
  `nid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (0, 1, 'brave-auth', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    url:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    username: root\r\n    password: root\r\n  redis:\r\n    database: 0\r\n    port: 6379\r\n    password: 123456\r\n    host: localhost', '44e71052dafaa50fd2544a11f5fffac2', '2021-06-23 05:56:43', '2021-06-23 05:56:43', NULL, '172.17.0.1', 'I', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (0, 2, 'brave-auth-dev.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    url:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    username: root\r\n    password: root\r\n  redis:\r\n    database: 0\r\n    port: 6379\r\n    password: 123456\r\n    host: localhost', '44e71052dafaa50fd2544a11f5fffac2', '2021-06-23 05:57:37', '2021-06-23 05:57:37', NULL, '172.17.0.1', 'I', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (1, 3, 'brave-auth', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    url:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    username: root\r\n    password: root\r\n  redis:\r\n    database: 0\r\n    port: 6379\r\n    password: 123456\r\n    host: localhost', '44e71052dafaa50fd2544a11f5fffac2', '2021-06-23 05:57:43', '2021-06-23 05:57:43', NULL, '172.17.0.1', 'D', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (2, 4, 'brave-auth-dev.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    url:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    username: root\r\n    password: root\r\n  redis:\r\n    database: 0\r\n    port: 6379\r\n    password: 123456\r\n    host: localhost', '44e71052dafaa50fd2544a11f5fffac2', '2021-06-23 05:58:47', '2021-06-23 05:58:47', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (0, 5, 'brave-mbs-rest-dev.yml', 'DEFAULT_GROUP', '', 'spring:', '8a8c3fec24cde5f873a43f62bc53ef6f', '2021-06-23 06:05:22', '2021-06-23 06:05:22', NULL, '172.17.0.1', 'I', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (4, 6, 'brave-mbs-rest-dev.yml', 'DEFAULT_GROUP', '', 'spring:', '8a8c3fec24cde5f873a43f62bc53ef6f', '2021-06-23 06:05:33', '2021-06-23 06:05:33', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (0, 7, 'brave-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:', '8a8c3fec24cde5f873a43f62bc53ef6f', '2021-06-23 06:06:43', '2021-06-23 06:06:43', NULL, '172.17.0.1', 'I', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (6, 8, 'brave-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:', '8a8c3fec24cde5f873a43f62bc53ef6f', '2021-06-23 06:58:55', '2021-06-23 06:58:56', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (6, 9, 'brave-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: false\n        routes:\n          - id: brave-auth\n            url: lb://brave-auth\n            predicates:\n              - Path=/brave-auth/**\n            filters:\n              - StripPrefix=0\n          - id: brave-mbs-rest\n            url: lb://brave-mbs-rest\n            predicates:\n              - Path=/brave-mbs-rest/*\n            filters:\n              - StripPrefix=1', '3f220c24d468022286d7da03a9113b47', '2021-06-23 07:00:34', '2021-06-23 07:00:34', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (6, 10, 'brave-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n        routes:\n          - id: brave-auth\n            url: lb://brave-auth\n            predicates:\n              - Path=/brave-auth/**\n            filters:\n              - StripPrefix=0\n          - id: brave-mbs-rest\n            url: lb://brave-mbs-rest\n            predicates:\n              - Path=/brave-mbs-rest/*\n            filters:\n              - StripPrefix=1', '51b9e036be8176310853fa4b485dce61', '2021-06-23 07:05:48', '2021-06-23 07:05:49', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (6, 11, 'brave-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n        routes:\n          - id: brave-auth\n            url: lb://brave-auth\n            predicates:\n              - Path=/auth/**\n            filters:\n              - StripPrefix=0\n          - id: brave-mbs-rest\n            url: lb://brave-mbs-rest\n            predicates:\n              - Path=/mbs/**\n            filters:\n              - StripPrefix=1', '3d4fccaadc6930936f9fdd26156c6c17', '2021-06-23 07:07:03', '2021-06-23 07:07:04', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (6, 12, 'brave-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n        routes:\n          - id: brave-auth\n            url: lb://brave-auth\n            predicates:\n              - Path=/auth/**\n            filters:\n              - StripPrefix=1\n          - id: brave-mbs-rest\n            url: lb://brave-mbs-rest\n            predicates:\n              - Path=/mbs/**\n            filters:\n              - StripPrefix=1', '1761d21a52b11093dd2fb8de6c6178fa', '2021-06-23 07:12:48', '2021-06-23 07:12:48', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (6, 13, 'brave-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n        routes:\n          - id: brave-auth\n            url: lb://brave-auth\n            predicates:\n              - Path=/api/auth/**\n            filters:\n              - StripPrefix=1\n          - id: brave-mbs-rest\n            url: lb://brave-mbs-rest\n            predicates:\n              - Path=/mbs/**\n            filters:\n              - StripPrefix=1', '3940d4954cd5ba434ce341e7ae5ba0cd', '2021-06-23 07:14:33', '2021-06-23 07:14:33', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (6, 14, 'brave-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n        routes:\n          - id: brave-auth\n            url: lb://brave-auth\n            predicates:\n              - Path=/auth/**\n            filters:\n              - StripPrefix=1\n          - id: brave-mbs-rest\n            url: lb://brave-mbs-rest\n            predicates:\n              - Path=/mbs/**\n            filters:\n              - StripPrefix=1', '1761d21a52b11093dd2fb8de6c6178fa', '2021-06-23 07:18:02', '2021-06-23 07:18:02', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (6, 15, 'brave-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n      routes:\n        - id: brave-auth\n          url: lb://brave-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n        - id: brave-mbs-rest\n          url: lb://brave-mbs-rest\n          predicates:\n            - Path=/mbs/**\n          filters:\n            - StripPrefix=1', 'd2ad63afc3c6853d8d64f744a787035d', '2021-06-23 07:18:51', '2021-06-23 07:18:51', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (6, 16, 'brave-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n      routes:\n        - id: brave-auth\n          url: lb://brave-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n        - id: brave-mbs-rest\n          url: lb://brave-mbs-rest\n          predicates:\n            - Path=/mbs/**\n          filters:\n            - StripPrefix=1', 'd2ad63afc3c6853d8d64f744a787035d', '2021-06-23 07:28:47', '2021-06-23 07:28:47', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (6, 17, 'brave-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n          lower-case-service-id: true\n      routes:\n        - id: brave-auth\n          uri: lb://brave-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n        - id: brave-mbs-rest\n          uri: lb://brave-mbs-rest\n          predicates:\n            - Path=/mbs/**\n          filters:\n            - StripPrefix=1', '89df5c03f8c9959cbf4b715e8cc8b8a4', '2021-06-23 07:39:11', '2021-06-23 07:39:12', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (6, 18, 'brave-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n          lower-case-service-id: true\n      routes:\n        - id: brave-auth\n          uri: lb://brave-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n        - id: brave-mbs-rest\n          uri: lb://brave-mbs-rest\n          predicates:\n            - Path=/mbs/**\n          filters:\n            - StripPrefix=1\nsecure：\n  ignore：\n    - auth/oauth/token', '141df86bd1e63b606c9d753bb507cf9f', '2021-06-23 07:43:35', '2021-06-23 07:43:36', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (6, 19, 'brave-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  security:\n    oauth2:\n      resourceserver:\n        jwt:\n          jwk-set-uri: \'http://localhost:8081/rsa/getPublicKey\'\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n          lower-case-service-id: true\n      routes:\n        - id: brave-auth\n          uri: lb://brave-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n        - id: brave-mbs-rest\n          uri: lb://brave-mbs-rest\n          predicates:\n            - Path=/mbs/**\n          filters:\n            - StripPrefix=1\nsecure：\n  ignore：\n    - auth/oauth/token', 'cd70165443e523a7a8deb92cc7a2318e', '2021-06-24 01:36:44', '2021-06-24 01:36:44', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (2, 20, 'brave-auth-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:13306/brave?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n  redis:\n    database: 0\n    port: 6379\n    password: 123456\n    host: localhost', 'c43d17b1d45b068e0c75b4f9612dfe6b', '2021-06-24 02:51:33', '2021-06-24 02:51:33', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (4, 21, 'brave-mbs-rest-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:13306/brave?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n  redis:\n    database: 0\n    port: 6379\n    password: 123456\n    host: localhost', 'c43d17b1d45b068e0c75b4f9612dfe6b', '2021-06-24 02:51:44', '2021-06-24 02:51:45', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (2, 22, 'brave-auth-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:13306/brave?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n  redis:\n    database: 0\n    port: 16379\n    password: 123456\n    host: localhost', '3bfa4ed85650870aa868d9509af3d6c7', '2021-06-24 03:21:11', '2021-06-24 03:21:12', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (4, 23, 'brave-mbs-rest-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:13306/brave?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n  redis:\n    database: 0\n    port: 16379\n    password: 123456\n    host: localhost', '3bfa4ed85650870aa868d9509af3d6c7', '2021-06-24 03:21:24', '2021-06-24 03:21:25', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (4, 24, 'brave-mbs-rest-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:13306/brave?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n  redis:\n    database: 0\n    port: 6379\n    password: 123456\n    host: localhost', 'c43d17b1d45b068e0c75b4f9612dfe6b', '2021-06-25 08:56:20', '2021-06-25 08:56:20', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (4, 25, 'brave-mbs-rest-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:13306/brave?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n  redis:\n    database: 0\n    port: 6379\n    password: 123456\n    host: localhost\nmybatis-plus:\n  config-location:\n    - classpath*:/mapper/**/*Mapper.xml', 'b83c9c6917f48dabe15baa0c298217ac', '2021-06-25 08:56:28', '2021-06-25 08:56:29', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (4, 26, 'brave-mbs-rest-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:13306/brave?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n  redis:\n    database: 0\n    port: 16379\n    password: 123456\n    host: localhost\nmybatis-plus:\n  config-location:\n    - classpath*:/mapper/**/*Mapper.xml', 'd6d2d83d4e2552909e543e69ea7df526', '2021-06-25 08:56:39', '2021-06-25 08:56:40', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (2, 27, 'brave-auth-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:13306/brave?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n  redis:\n    database: 0\n    port: 6379\n    password: 123456\n    host: localhost', 'c43d17b1d45b068e0c75b4f9612dfe6b', '2021-06-28 07:40:34', '2021-06-28 07:40:35', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (2, 28, 'brave-auth-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:13306/brave?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n  redis:\n    database: 0\n    port: 6379\n    password: 123456\n    host: localhost\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always', '12bf417fe27ecc9e5e7433fbe7aeddde', '2021-06-28 08:01:20', '2021-06-28 08:01:20', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (4, 29, 'brave-mbs-rest-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:13306/brave?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n  redis:\n    database: 0\n    port: 16379\n    password: 123456\n    host: localhost\nmybatis-plus:\n  config-location:\n    - classpath*:/mapper/**/*Mapper.xml', 'd6d2d83d4e2552909e543e69ea7df526', '2021-06-28 08:01:55', '2021-06-28 08:01:55', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (4, 30, 'brave-mbs-rest-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:13306/brave?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n  redis:\n    database: 0\n    port: 16379\n    password: 123456\n    host: localhost\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always\nmybatis-plus:\n  config-location:\n    - classpath*:/mapper/**/*Mapper.xml', '527dc149231451ca38ce85740aeb8083', '2021-06-28 08:02:36', '2021-06-28 08:02:36', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (2, 31, 'brave-auth-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:13306/brave?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n  redis:\n    database: 0\n    port: 6379\n    password: 123456\n    host: localhost\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always', '12bf417fe27ecc9e5e7433fbe7aeddde', '2021-06-28 08:21:23', '2021-06-28 08:21:23', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (4, 32, 'brave-mbs-rest-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:13306/brave?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n  redis:\n    database: 0\n    port: 6379\n    password: 123456\n    host: localhost\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always\nmybatis-plus:\n  config-location:\n    - classpath*:/mapper/**/*Mapper.xml', '386337805a91401db7f68b488371eb1d', '2021-06-28 08:21:34', '2021-06-28 08:21:35', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (2, 33, 'brave-auth-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:13306/brave?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n  redis:\n    database: 0\n    port: 6379\n    password: 123456\n    host: 127.0.0.1\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always', '51d20c7c5a91f4e5084199644d7cb1f5', '2021-06-28 08:45:13', '2021-06-28 08:45:13', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (6, 34, 'brave-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  security:\n    oauth2:\n      resourceserver:\n        jwt:\n          jwk-set-uri: \'http://localhost:8081/rsa/getPublicKey\'\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n          lower-case-service-id: true\n      routes:\n        - id: brave-auth\n          uri: lb://brave-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n        - id: brave-mbs-rest\n          uri: lb://brave-mbs-rest\n          predicates:\n            - Path=/mbs/**\n          filters:\n            - StripPrefix=1\nsecure:\n  ignore:\n    - auth/oauth/token', '2d076e343c332ce062e98418e0eb5991', '2021-06-28 08:48:16', '2021-06-28 08:48:17', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (0, 35, 'brave-visual-admin-dev.yml', 'DEFAULT_GROUP', '', '# 健康检测规则\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n  endpoint:\r\n    health:\r\n      show-details: always', 'c45fe1ab1fe1134437c97d02f4137ff4', '2021-06-28 08:59:49', '2021-06-28 08:59:50', NULL, '172.17.0.1', 'I', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (15, 36, 'brave-visual-admin-dev.yml', 'DEFAULT_GROUP', '', '# 健康检测规则\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n  endpoint:\r\n    health:\r\n      show-details: always', 'c45fe1ab1fe1134437c97d02f4137ff4', '2021-06-28 09:04:42', '2021-06-28 09:04:43', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (15, 37, 'brave-visual-admin-dev.yml', 'DEFAULT_GROUP', '', '#开启和暴露所有端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always', 'd6d08c1b4afca8683ebae7f64c20cb50', '2021-06-28 09:04:47', '2021-06-28 09:04:47', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (2, 38, 'brave-auth-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:13306/brave?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n  redis:\n    database: 0\n    port: 6379\n    password: 123456\n    host: 127.0.0.1\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always', '1d24e90fe98f61ea81a905453a6049e4', '2021-06-28 09:05:15', '2021-06-28 09:05:16', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (4, 39, 'brave-mbs-rest-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:13306/brave?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n  redis:\n    database: 0\n    port: 6379\n    password: 123456\n    host: 127.0.0.1\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always\nmybatis-plus:\n  config-location:\n    - classpath*:/mapper/**/*Mapper.xml', 'd826ab251f0466d5e6f852f3cee3629a', '2021-06-28 09:05:26', '2021-06-28 09:05:26', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (6, 40, 'brave-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  security:\n    oauth2:\n      resourceserver:\n        jwt:\n          jwk-set-uri: \'http://localhost:8081/rsa/getPublicKey\'\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n          lower-case-service-id: true\n      routes:\n        - id: brave-auth\n          uri: lb://brave-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n        - id: brave-mbs-rest\n          uri: lb://brave-mbs-rest\n          predicates:\n            - Path=/mbs/**\n          filters:\n            - StripPrefix=1\nsecure:\n  ignore:\n    - auth/oauth/token\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always', '918c1b87c79d00d7478909487dc2289c', '2021-06-28 09:05:37', '2021-06-28 09:05:37', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (2, 41, 'brave-auth-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:13306/brave?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n  redis:\n    database: 0\n    port: 6379\n    password: 123456\n    host: 127.0.0.1\n# Spring Boot Admin 开启和暴露所有端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always', '0be80bf7b32946f5fa338e2f2f5452b1', '2021-06-28 09:13:51', '2021-06-28 09:13:52', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (15, 42, 'brave-visual-admin-dev.yml', 'DEFAULT_GROUP', '', '# 开启和暴露所有端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always', '92ed2ca654d407a0354f49c92f903d18', '2021-06-28 09:14:12', '2021-06-28 09:14:13', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (15, 43, 'brave-visual-admin-dev.yml', 'DEFAULT_GROUP', '', '# 开启和暴露所有端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always\nspring:\n  security:\n    user:\n      name: \"admin\"\n      password: \"admin\"', '29a3d88846cf65fe6f21543fea23f3d7', '2021-06-28 09:33:29', '2021-06-28 09:33:30', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (15, 44, 'brave-visual-admin-dev.yml', 'DEFAULT_GROUP', '', '# 开启和暴露所有端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always\nspring:\n  security:\n    user:\n      name: \"admin\"\n      password: \"admin\"', '29a3d88846cf65fe6f21543fea23f3d7', '2021-06-28 09:37:32', '2021-06-28 09:37:32', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (15, 45, 'brave-visual-admin-dev.yml', 'DEFAULT_GROUP', '', '# 开启和暴露所有端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always\nspring:\n  security:\n    user:\n      name: \"admin\"\n      password: \"admin\"', '29a3d88846cf65fe6f21543fea23f3d7', '2021-06-28 09:51:26', '2021-06-28 09:51:26', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (15, 46, 'brave-visual-admin-dev.yml', 'DEFAULT_GROUP', '', '# 开启和暴露所有端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always', '92ed2ca654d407a0354f49c92f903d18', '2021-06-29 06:08:59', '2021-06-29 06:08:59', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (15, 47, 'brave-visual-admin-dev.yml', 'DEFAULT_GROUP', '', '# 开启和暴露所有端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always\nspring:\n  boot:\n    admin:\n      notify:\n        mail:\n          to: 2271426934@qq.com\n          from: yongchencloud@163.com\n  mail:\n    host: smtp.163.com\n    username: yongchencloud@163.com\n    password: cy19950906..\n    properties:\n      mail:\n      debug: false\n      smtp:\n        auth: true  #安全认证(默认是true)\n        port: 456\n        ssl:\n          enable: true  #开启ssl加密 否则项目启动时报530error\n          socketFactory: sf', '350f1110f0270d1dc513b6d39ddba241', '2021-06-29 06:12:43', '2021-06-29 06:12:44', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (15, 48, 'brave-visual-admin-dev.yml', 'DEFAULT_GROUP', '', '# 开启和暴露所有端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always\nspring:\n  boot:\n    admin:\n      notify:\n        mail:\n          to: 2271426934@qq.com\n          from: yongchencloud@163.com\n  mail:\n    host: smtp.163.com\n    username: yongchencloud@163.com\n    password: cy19950906..\n    properties:\n      mail:\n      debug: false\n      smtp:\n        auth: true  #安全认证(默认是true)\n        port: 456\n        ssl:\n          enable: true  #开启ssl加密 否则项目启动时报530error\n          socketFactory: sf', '350f1110f0270d1dc513b6d39ddba241', '2021-06-29 06:30:48', '2021-06-29 06:30:49', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (15, 49, 'brave-visual-admin-dev.yml', 'DEFAULT_GROUP', '', '# 开启和暴露所有端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always\nspring:\n  boot:\n    admin:\n      notify:\n        mail:\n          to: 2271426934@qq.com\n          from: yongchencloud@163.com\n  mail:\n    host: smtp.163.com\n    username: yongchencloud@163.com\n    password: FVXSADFZHPJEWEIV\n    properties:\n      mail:\n      debug: false\n      smtp:\n        auth: true  #安全认证(默认是true)\n        port: 456\n        ssl:\n          enable: true  #开启ssl加密 否则项目启动时报530error\n          socketFactory: sf', '30c10849d59842feacc4429f63c99da0', '2021-06-29 06:34:46', '2021-06-29 06:34:46', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (15, 50, 'brave-visual-admin-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  boot:\n    admin:\n      notify:\n        mail:\n          to: 2271426934@qq.com\n          from: yongchencloud@163.com\n  mail:\n    host: smtp.163.com\n    username: yongchencloud@163.com\n    password: FVXSADFZHPJEWEIV\n    properties:\n      mail:\n      debug: false\n      smtp:\n        auth: true  #安全认证(默认是true)\n        port: 456\n        ssl:\n          enable: true  #开启ssl加密 否则项目启动时报530error\n          socketFactory: sf\n# 开启和暴露所有端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always', '816e6249334b684068642ed332e70c38', '2021-06-29 06:40:14', '2021-06-29 06:40:14', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (15, 51, 'brave-visual-admin-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  boot:\n    admin:\n      notify:\n        mail:\n          to: 2271426934@qq.com\n          from: yongchencloud@163.com\n  mail:\n    host: smtp.163.com\n    username: yongchencloud@163.com\n    password: FVXSADFZHPJEWEIV\n    properties:\n      mail:\n        debug: false\n        smtp:\n          auth: true  #安全认证(默认是true)\n          port: 456\n          ssl:\n            enable: true  #开启ssl加密 否则项目启动时报530error\n            socketFactory: sf\n# 开启和暴露所有端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always', '3cc66d179e8af8be21c7d54541c4e51a', '2021-06-29 06:40:18', '2021-06-29 06:40:18', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (15, 52, 'brave-visual-admin-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  boot:\n    admin:\n      notify:\n        mail:\n          to: 2271426934@qq.com\n          from: yongchencloud@163.com\n  mail:\n    host: smtp.163.com\n    username: yongchencloud@163.com\n    password: FVXSADFZHPJEWEIV\n    properties:\n      mail:\n        debug: false\n        smtp:\n          auth: true  #安全认证(默认是true)\n          port: 456\n          ssl:\n            enable: true  #开启ssl加密 否则项目启动时报530error\n            socketFactory: sf\n# 开启和暴露所有端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always', '3cc66d179e8af8be21c7d54541c4e51a', '2021-06-29 06:44:15', '2021-06-29 06:44:15', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (15, 53, 'brave-visual-admin-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  boot:\n    admin:\n      notify:\n        mail:\n          to: 2271426934@qq.com\n          from: yongchencloud@163.com\n  mail:\n    host: smtp.163.com\n    username: yongchencloud@163.com\n    password: FVXSADFZHPJEWEIV\n    properties:\n      mail:\n        debug: false\n        smtp:\n          auth: true  #安全认证(默认是true)\n          # port: 456\n          ssl:\n            enable: true  #开启ssl加密 否则项目启动时报530error\n            socketFactory: sf\n# 开启和暴露所有端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always', '047087170cfb7f357b5ca978cb66857d', '2021-06-29 06:44:53', '2021-06-29 06:44:53', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (15, 54, 'brave-visual-admin-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  boot:\n    admin:\n      notify:\n        mail:\n          to: 2271426934@qq.com\n          from: yongchencloud@163.com\n  mail:\n    host: smtp.163.com\n    username: yongchencloud@163.com\n    password: FVXSADFZHPJEWEIV\n    properties:\n      mail:\n        debug: false\n        smtp:\n          auth: true  #安全认证(默认是true)\n          # port: 456\n          ssl:\n            enable: true  #开启ssl加密 否则项目启动时报530error\n            socketFactory: sf\n# 开启和暴露所有端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always', '047087170cfb7f357b5ca978cb66857d', '2021-06-29 06:49:52', '2021-06-29 06:49:52', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (15, 55, 'brave-visual-admin-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  boot:\n    admin:\n      notify:\n        mail:\n          to: 2271426934@qq.com\n          from: yongchencloud@163.com\n  mail:\n    host: smtp.163.com\n    username: yongchencloud@163.com\n    password: FVXSADFZHPJEWEIV\n    properties:\n      mail:\n        debug: false\n        smtp:\n          auth: true  #安全认证(默认是true)\n          starttls:\n            enable: true\n            required: true\n# 开启和暴露所有端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always', 'f0bc896cce88aecccb0283cc25bfe8c9', '2021-06-29 06:49:57', '2021-06-29 06:49:58', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (6, 56, 'brave-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  security:\n    oauth2:\n      resourceserver:\n        jwt:\n          jwk-set-uri: \'http://localhost:8081/rsa/getPublicKey\'\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n          lower-case-service-id: true\n      routes:\n        - id: brave-auth\n          uri: lb://brave-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n        - id: brave-mbs-rest\n          uri: lb://brave-mbs-rest\n          predicates:\n            - Path=/mbs/**\n          filters:\n            - StripPrefix=1\nsecure:\n  ignore:\n    - auth/oauth/token\n# Spring Boot Admin 开启和暴露所有端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: always', '419138b58ed9eef63c8583b78235dfe4', '2021-06-30 07:20:29', '2021-06-30 07:20:29', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (0, 57, 'brave-flowable-dev.yml', 'DEFAULT_GROUP', '', 'spring:', '8a8c3fec24cde5f873a43f62bc53ef6f', '2021-07-02 02:59:56', '2021-07-02 02:59:56', NULL, '172.17.0.1', 'I', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (16, 58, 'brave-flowable-dev.yml', 'DEFAULT_GROUP', '', 'spring:', '8a8c3fec24cde5f873a43f62bc53ef6f', '2021-07-02 03:00:12', '2021-07-02 03:00:13', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (16, 59, 'brave-flowable-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:13306/brave?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root', '084ac63b335fcbff6932b6b01ab332d2', '2021-07-02 03:00:25', '2021-07-02 03:00:26', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');
INSERT INTO `his_config_info` VALUES (16, 60, 'brave-flowable-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:13306/flowable?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root', 'fc1ee18c611259d2c14deb3329b07b2e', '2021-07-02 03:00:28', '2021-07-02 03:00:29', 'nacos', '172.17.0.1', 'U', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permissions
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `idx_user_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
INSERT INTO `tenant_info` VALUES (1, '1', 'ecfd1e10-0d45-4fb0-b4a8-74b77c4f7e65', 'brave', 'brave namespaces', 'nacos', 1624351131155, 1624351131155);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
