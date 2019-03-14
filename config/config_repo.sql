/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.15
Source Server Version : 50722
Source Host           : 192.168.10.15:3306
Source Database       : config_repo

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-03-14 17:59:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for properties
-- ----------------------------
DROP TABLE IF EXISTS `properties`;
CREATE TABLE `properties` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `properties_file_id` bigint(20) DEFAULT NULL,
  `application` varchar(20) DEFAULT NULL,
  `profile` varchar(20) DEFAULT NULL,
  `label` varchar(20) DEFAULT NULL,
  `properties_key` varchar(50) DEFAULT NULL,
  `properties_value` varchar(500) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_user_id` varchar(50) DEFAULT NULL,
  `last_update_date` datetime DEFAULT NULL,
  `last_update_user_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of properties
-- ----------------------------
INSERT INTO `properties` VALUES ('5', '1', 'jmsw-demo', 'dev', 'master', 'eureka.client.serviceUrl.defaultZone', 'http://192.168.10.17/eureka/', '2018-05-25 15:43:38', '', '2018-05-25 15:43:38', '');
INSERT INTO `properties` VALUES ('6', '1', 'jmsw-demo', 'dev', 'master', 'demo-key', 'demo-value-1', '2018-05-25 15:43:38', '', '2018-05-25 15:43:38', '');
INSERT INTO `properties` VALUES ('7', '1', 'jmsw-demo', 'dev', 'master', 'eureka.instance.hostname', '${spring.cloud.client.hostname:localhost}', '2018-05-25 15:43:38', '', '2018-05-25 15:43:38', '');
INSERT INTO `properties` VALUES ('8', '2', 'jmsw-demob', 'dev', 'master', 'eureka.client.serviceUrl.defaultZone', 'http://192.168.10.17/eureka/', '2018-05-25 15:43:56', '', '2018-05-25 15:43:56', '');
INSERT INTO `properties` VALUES ('9', '2', 'jmsw-demob', 'dev', 'master', 'eureka.instance.hostname', '${spring.cloud.client.hostname:localhost}', '2018-05-25 15:43:56', '', '2018-05-25 15:43:56', '');

-- ----------------------------
-- Table structure for properties_file
-- ----------------------------
DROP TABLE IF EXISTS `properties_file`;
CREATE TABLE `properties_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application` varchar(20) DEFAULT NULL,
  `profile` varchar(20) DEFAULT NULL,
  `label` varchar(20) DEFAULT NULL,
  `content` text,
  `create_date` datetime DEFAULT NULL,
  `create_user_id` varchar(50) DEFAULT NULL,
  `last_update_date` datetime DEFAULT NULL,
  `last_update_user_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of properties_file
-- ----------------------------
INSERT INTO `properties_file` VALUES ('1', 'jmsw-demo', 'dev', 'master', 'demo-key=demo-value-1\r\neureka.client.serviceUrl.defaultZone=http://192.168.10.17/eureka/\r\neureka.instance.hostname=${spring.cloud.client.hostname:localhost}', '2018-05-25 15:43:37', 'admin', '2018-05-25 15:43:37', 'admin');
INSERT INTO `properties_file` VALUES ('2', 'jmsw-demob', 'dev', 'master', 'eureka.client.serviceUrl.defaultZone=http://192.168.10.17/eureka/\r\neureka.instance.hostname=${spring.cloud.client.hostname:localhost}', '2018-05-25 15:43:56', 'admin', '2018-05-25 15:43:56', 'admin');
