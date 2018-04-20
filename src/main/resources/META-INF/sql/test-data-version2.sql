/*
SQLyog 企业版 - MySQL GUI v8.14
MySQL - 5.7.20-log : Database - smarthome_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`smarthome_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `smarthome_db`;

/*Table structure for table `device` */

DROP TABLE IF EXISTS `device`;

CREATE TABLE `device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `family_id` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `img` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  `registert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(255) NOT NULL DEFAULT '正常',
  `control_mode` varchar(255) NOT NULL DEFAULT '手动',
  PRIMARY KEY (`id`),
  KEY `FK_family_device` (`family_id`),
  CONSTRAINT `FK_family_device` FOREIGN KEY (`family_id`) REFERENCES `family` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `device` */

insert  into `device`(`id`,`family_id`,`name`,`img`,`description`,`location`,`info`,`registert_time`,`status`,`control_mode`) values (1,1,'curtain','img123','智能窗帘','屋子北边','会动的窗帘','2018-02-01 00:14:05','正常','手动'),(2,1,'door','img123','智能门禁','门口','有人脸识别的门禁','2018-02-01 00:09:24','正常','手动'),(3,1,'kettle','img123','智能水壶','屋子里面','智能控制的水壶','2018-02-01 00:09:39','正常','手动'),(4,1,'light','img123','智能灯','屋子里面','比神灯还厉害','2018-02-01 00:10:00','正常','手动'),(6,2,'kettle','img123','智能水壶','屋子里面','智能控制的水壶','2018-02-01 00:10:24','正常','手动'),(7,3,'door','img123','智能门禁','门口','有人脸识别的门禁','2018-02-01 00:10:41','正常','手动'),(8,3,'kettle','img123','智能水壶','屋子里面','智能控制的水壶','2018-02-01 00:10:49','正常','手动'),(9,3,'light','img123','智能灯','屋子里面','比神灯还厉害','2018-02-01 00:10:59','正常','手动');

/*Table structure for table `device_operator` */

DROP TABLE IF EXISTS `device_operator`;

CREATE TABLE `device_operator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_id` int(11) NOT NULL,
  `operator_name` varchar(255) NOT NULL,
  `operator_code` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(255) NOT NULL DEFAULT '正常',
  PRIMARY KEY (`id`),
  KEY `FK_device_deviceOperator` (`device_id`),
  CONSTRAINT `FK_device_deviceOperator` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `device_operator` */

insert  into `device_operator`(`id`,`device_id`,`operator_name`,`operator_code`,`description`,`register_time`,`status`) values (1,1,'智能窗帘开','curtain_open','对智能窗帘的开指令','2018-02-01 14:27:09','正常'),(2,1,'智能窗帘关','curtain_close','对智能窗帘的关指令','2018-02-01 01:15:45','正常'),(3,2,'门禁开','TD11','对智能门禁的开指令','2018-02-01 01:15:52','正常'),(4,2,'门禁关','TD10','对智能门禁的关指令','2018-02-01 01:15:53','正常'),(5,3,'水壶开','TR11','对智能水壶的开指令','2018-02-01 01:15:54','正常'),(6,3,'水壶关','TR10','对智能水壶的关指令','2018-02-01 01:15:55','正常'),(7,4,'电灯开','TL11','对智能电灯的开指令','2018-02-01 01:45:52','正常'),(8,4,'电灯关','TL10','对智能电灯的关指令','2018-02-01 01:35:52','正常'),(9,3,'重煮沸','TR12','对智能水壶的重煮沸指令','2018-02-01 01:15:54','正常'),(10,3,'恒温煮','TR13-XX','对智能水壶的恒温煮指令','2018-02-01 01:15:55','正常');

/*Table structure for table `family` */

DROP TABLE IF EXISTS `family`;

CREATE TABLE `family` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `family_unique_code` varchar(255) DEFAULT NULL,
  `family_name` varchar(255) NOT NULL,
  `family_address` varchar(255) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  `lon` varchar(255) DEFAULT NULL,
  `temperature` varchar(255) DEFAULT NULL,
  `humidity` varchar(255) DEFAULT NULL,
  `infrared` varchar(255) DEFAULT NULL,
  `smoke` varchar(255) DEFAULT NULL,
  `light_intensity` varchar(255) DEFAULT NULL,
  `control_mode` varchar(255) NOT NULL DEFAULT '手动',
  `status` varchar(255) NOT NULL DEFAULT '正常',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `family_unique_code` (`family_unique_code`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `family` */

insert  into `family`(`id`,`family_unique_code`,`family_name`,`family_address`,`lat`,`lon`,`temperature`,`humidity`,`infrared`,`smoke`,`light_intensity`,`control_mode`,`status`,`register_time`) values (1,'manager','manager','manager',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'manager','manager','2018-04-16 11:11:50'),(2,NULL,'美满','广州市萝岗区','30','30',NULL,NULL,NULL,NULL,NULL,'半自动控制','正常','2018-04-16 10:35:39'),(3,NULL,'曹欣','1213132',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'testMode','testStatus','2018-04-16 10:35:37'),(4,NULL,'我是傻子',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'手动','删除','2018-04-13 14:08:27'),(5,NULL,'你是傻子123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'手动','删除','2018-04-13 15:10:58'),(6,NULL,'我的家庭',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'手动','删除','2018-04-13 15:47:27'),(7,NULL,'我的家庭啊实打实大',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'手动','删除','2018-04-13 15:50:26'),(8,NULL,'我的家庭1231231',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'手动','删除','2018-04-13 15:50:56'),(9,NULL,'我的家庭123123','北京市 市辖区 东城区',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'手动','正常','2018-04-16 10:35:44'),(10,NULL,'我的家庭',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'手动','正常','2018-04-15 10:15:15');

/*Table structure for table `family_img` */

DROP TABLE IF EXISTS `family_img`;

CREATE TABLE `family_img` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `family_id` int(11) NOT NULL,
  `img_url` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL DEFAULT '正常',
  PRIMARY KEY (`id`),
  KEY `FK_family_family_img` (`family_id`),
  CONSTRAINT `FK_family_family_img` FOREIGN KEY (`family_id`) REFERENCES `family` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `family_img` */

insert  into `family_img`(`id`,`family_id`,`img_url`,`status`) values (1,1,'123','正常'),(2,9,'/upload/family/536e536e-99b6-4a24-a401-49c130646ed0????_20171113140510.jpg','正常'),(3,9,'/upload/family/9851c72d-21f8-44ed-ad68-e0db9646fa5d????_20171113140510.jpg','正常'),(4,9,'/upload/family/8405fcc0-4e1a-4ffb-b774-2e3f558b7bbb????_20171113140510.jpg','正常'),(5,9,'/upload/family/08088adb-88ff-48b4-8c8e-09b21e5df5b6lock.png','正常'),(7,9,'/upload/family/ebd25a67-c5cd-4bf8-b634-e016f04e3a97????_20171113140510.jpg','正常'),(8,9,'/upload/family/8520cce7-ba94-48c0-a8d8-5ae46142d6fb????_20171113140510.jpg','正常'),(9,9,'/upload/family/0d819303-3c4f-45cc-89c2-4aa75c4f108b','正常');

/*Table structure for table `log` */

DROP TABLE IF EXISTS `log`;

CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `family_id` int(11) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `info` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL DEFAULT '正常',
  `generate_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_family_log` (`family_id`),
  CONSTRAINT `FK_family_log` FOREIGN KEY (`family_id`) REFERENCES `family` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `log` */

insert  into `log`(`id`,`family_id`,`type`,`info`,`status`,`generate_time`) values (1,1,'普通日志','开灯操作','正常','2018-01-31 20:55:23'),(2,1,'报警日志','温度过高','正常','2018-01-31 20:50:37'),(3,2,'普通日志','关灯操作','正常','2018-01-31 20:55:23'),(4,2,'报警日志','光强过高','正常','2018-01-31 20:50:37'),(5,3,'普通日志','开热水壶操作','正常','2018-01-31 20:55:23'),(6,3,'报警日志','湿度过高','正常','2018-01-31 20:50:37');

/*Table structure for table `sensor` */

DROP TABLE IF EXISTS `sensor`;

CREATE TABLE `sensor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_id` int(11) NOT NULL,
  `sensor_type_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `info` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL DEFAULT '正常',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_device_sensor` (`device_id`),
  KEY `FK_type_sensor` (`sensor_type_id`),
  CONSTRAINT `FK_device_sensor` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`),
  CONSTRAINT `FK_type_sensor` FOREIGN KEY (`sensor_type_id`) REFERENCES `sensor_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `sensor` */

insert  into `sensor`(`id`,`device_id`,`sensor_type_id`,`name`,`info`,`description`,`status`,`register_time`) values (1,1,1,'窗帘光照传感器','光照传感器','光照传感器','正常','2018-02-01 01:45:40'),(2,3,2,'水壶温度传感器','水壶温度','水壶温度','正常','2018-02-01 01:43:11'),(3,3,3,'水壶容量传感器','水壶容量','水壶容量','正常','2018-02-01 01:43:20'),(4,4,1,'灯光强传感器','光强','光强','正常','2018-02-01 01:43:20');

/*Table structure for table `sensor_data` */

DROP TABLE IF EXISTS `sensor_data`;

CREATE TABLE `sensor_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sensor_id` int(11) NOT NULL,
  `data_name` varchar(255) DEFAULT NULL,
  `data_value` varchar(255) DEFAULT NULL,
  `collection_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `info` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL DEFAULT '正常',
  PRIMARY KEY (`id`),
  KEY `FK_sensor_data` (`sensor_id`),
  CONSTRAINT `FK_sensor_data` FOREIGN KEY (`sensor_id`) REFERENCES `sensor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sensor_data` */

/*Table structure for table `sensor_type` */

DROP TABLE IF EXISTS `sensor_type`;

CREATE TABLE `sensor_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `info` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL DEFAULT '正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sensor_type` */

insert  into `sensor_type`(`id`,`name`,`info`,`description`,`status`) values (1,'光照传感器','光照传感器','光照传感器','正常'),(2,'温度传感器','温度传感器','温度传感器','正常'),(3,'容量传感器','容量传感器','容量传感器','正常');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `nickname` varchar(255) DEFAULT '智能家居',
  `sex` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) NOT NULL,
  `photo` varchar(255) DEFAULT '/upload/user/me.png',
  `birthday` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL DEFAULT '正常',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`nickname`,`sex`,`email`,`phone`,`photo`,`birthday`,`address`,`role`,`code`,`status`,`register_time`) values (1,'caoxin','111111','caoxin',NULL,'123456@qq.com','13535570616','/upload/user/me.png',NULL,NULL,'管理员',NULL,'正常','2018-04-15 10:45:24'),(2,'weisheng','111111',NULL,NULL,'123798@qq.com','13514561284','/upload/user/me.png',NULL,NULL,'用户',NULL,'正常','2018-04-16 10:31:09'),(3,'tianzhao','111111',NULL,NULL,'123654@qq.com','13002090126','/upload/user/me.png',NULL,NULL,'用户',NULL,'正常','2018-04-16 10:30:59'),(8,NULL,'123123','我是大神','男','12312312@qq.com','15622633852','/upload/user/0793ac95-1755-4e9f-9669-d74767d26925timg.jpg','2018-04-07','广东省 中山市 ','用户','9787','正常','2018-04-13 13:37:33'),(9,NULL,'11111111','智能家居',NULL,'','15622633851','/upload/user/me.png',NULL,NULL,'用户',NULL,'正常','2018-04-15 10:51:15');

/*Table structure for table `userfamily` */

DROP TABLE IF EXISTS `userfamily`;

CREATE TABLE `userfamily` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `family_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `family_role` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL DEFAULT '正常',
  `uniquecode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniquecode` (`uniquecode`),
  KEY `FK_family_userfamily` (`family_id`),
  KEY `FK_user_userfamily` (`user_id`),
  CONSTRAINT `FK_family_userfamily` FOREIGN KEY (`family_id`) REFERENCES `family` (`id`),
  CONSTRAINT `FK_user_userfamily` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `userfamily` */

insert  into `userfamily`(`id`,`family_id`,`user_id`,`family_role`,`status`,`uniquecode`) values (1,4,8,'123','正常',NULL),(6,9,8,'管理员','正常','xusfdsdfjfdsfijdsif'),(9,10,9,'管理员','正常',NULL),(10,1,1,'管理员','正常',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
