CREATE DATABASE /*!32312 IF NOT EXISTS*/`smarthome_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `smarthome_db`;
/*Table structure for table `family` */

DROP TABLE IF EXISTS `family`;

CREATE TABLE `family` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `family_unique_code` varchar(255) NOT NULL UNIQUE,
  `family_name` varchar(255) NOT NULL,
  `family_address` varchar(255) NOT NULL,
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `family` */

/*Table structure for table `device` */

DROP TABLE IF EXISTS `device`;

CREATE TABLE `device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `family_id` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `img`  varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  `registert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(255) NOT NULL DEFAULT '正常',
  `control_mode` varchar(255) NOT NULL DEFAULT '手动',
  PRIMARY KEY (`id`),
  KEY `FK_family_device` (`family_id`),
  CONSTRAINT `FK_family_device` FOREIGN KEY (`family_id`) REFERENCES `family` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `device` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `device_operator` */



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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `log` */


/*Table structure for table `sensor_type` */

DROP TABLE IF EXISTS `sensor_type`;

CREATE TABLE `sensor_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `info` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL DEFAULT '正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sensor_type` */


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sensor` */

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



/*Table structure for table `user` */
/*这里漏了一个字段nickname*/
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255),
  `password` varchar(255) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,/*用户昵称*/
  `sex`   varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) NOT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL DEFAULT '正常',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_family`;
CREATE TABLE `userfamily` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `family_id` int(11) NOT NULL,
  `user_id`  int(11) NOT NULL,
  `family_role` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL DEFAULT '正常',
  PRIMARY KEY (`id`),
  KEY `FK_family_userfamily` (`family_id`),
  CONSTRAINT `FK_family_userfamily` FOREIGN KEY (`family_id`) REFERENCES `family` (`id`),
  KEY `FK_user_userfamily` (`user_id`),
  CONSTRAINT `FK_user_userfamily` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `family_img`;
CREATE TABLE `family_img` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `family_id` int(11) NOT NULL,
  `img_url`  varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL DEFAULT '正常',
  PRIMARY KEY (`id`),
  KEY `FK_family_family_img` (`family_id`),
  CONSTRAINT `FK_family_family_img` FOREIGN KEY (`family_id`) REFERENCES `family` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;