# springboot-mybatis
创建 SpringBoot 项目集成 Mybatis + PageHelper分页插件 + Druid + WebSocket + Redis + Swagger2

多数据源以及分布式事务正在整合中


sql：

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for syslog
-- ----------------------------
DROP TABLE IF EXISTS `syslog`;
CREATE TABLE `syslog` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `type` varchar(10) NOT NULL,
  `operation` varchar(255) NOT NULL,
  `method` varchar(100) NOT NULL,
  `params` text NOT NULL,
  `ip` varchar(20) NOT NULL,
  `operation_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL,
  `user_name` varchar(130) NOT NULL,
  `age` int(11) NOT NULL,
  `gender` varchar(11) NOT NULL,
  `address` varchar(100) NOT NULL,
  `user_pass` varchar(132) NOT NULL COMMENT '用户秘银，进行md5加盐处理',
  PRIMARY KEY (`id`),
  KEY `uid` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
