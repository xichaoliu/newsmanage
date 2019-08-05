DROP DATABASE IF EXISTS `newsmanage`;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `newsmanage` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `newsmanage`;

/*Table structure for table `sys_role` */

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rolename` varchar(45) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色';

/*Data for the table `sys_role` */

INSERT INTO `newsmanage`.`sys_role` (`id`, `rolename`) VALUES (1, '普通管理员'), (2, '超级管理员');

/*Table structure for table `sys_usr` */

CREATE TABLE `sys_usr` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `usrname` varchar(45) NOT NULL COMMENT '用户名称',
  `password` varchar(45) DEFAULT NULL COMMENT '用户密码',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '用户类型 （1 普通管理员 2 超级管理）',
  `status` tinyint(4) DEFAULT '0' COMMENT '用户状态 （0 正常 1 禁用）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `usrname_UNIQUE` (`usrname`),
  KEY `fk_typeid_idx` (`type`),
  CONSTRAINT `fk_typeid` FOREIGN KEY (`type`) REFERENCES `sys_role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='用户';

/*Data for the table `sys_usr` */

INSERT INTO `newsmanage`.`sys_usr` (`id`, `usrname`, `password`, `type`) values (1, 'admin', 'admin', 1), (2, 'superadmin', '123456', 2);

/*Table structure for table `sys_usr_role` */

CREATE TABLE `sys_usr_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `usr_id` int(11) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  KEY `id_idx` (`role_id`),
  KEY `fk_usrid_idx` (`usr_id`),
  CONSTRAINT `fk_roleid` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usrid` FOREIGN KEY (`usr_id`) REFERENCES `sys_usr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

/*Data for the table `sys_usr_role` */

INSERT INTO `newsmanage`.`sys_usr_role` (`id`, `usr_id`, `role_id`) VALUES (1, 1, 1), (2, 2, 2);

/*Table structure for table `tb_article` */

CREATE TABLE `tb_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `authorid` int(11) NOT NULL COMMENT '作者id',
  `authorname` varchar(45) DEFAULT NULL COMMENT '作者名称',
  `title` varchar(256) DEFAULT NULL COMMENT '文章标题',
  `content` text COMMENT '文章内容',
  `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除  (0 否 1 是)',
  PRIMARY KEY (`id`),
  KEY `fk_userid_idx` (`authorid`),
  CONSTRAINT `fk_userid` FOREIGN KEY (`authorid`) REFERENCES `sys_usr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='文章信息表';

