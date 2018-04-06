/*家庭数据表*/
insert into `family`(`id`, `family_unique_code`, `family_name`, `family_address`, `lat`, `lon`, `temperature`, `humidity`, `infrared`, `smoke`, `light_intensity`, `control_mode`, `status`, `register_time`)
values('1','uniqueCode0001','团圆','广州市海珠区','20','20',NULL,NULL,NULL,NULL,NULL,'手动控制','正常','2018-01-31 20:14:23');
insert into `family` (`id`, `family_unique_code`, `family_name`, `family_address`, `lat`, `lon`, `temperature`, `humidity`, `infrared`, `smoke`, `light_intensity`, `control_mode`, `status`, `register_time`)
values('2','uniqueCode0002','美满','广州市萝岗区','30','30',NULL,NULL,NULL,NULL,NULL,'半自动控制','正常','2018-01-31 17:36:43');
insert into `family` (`id`, `family_unique_code`, `family_name`, `family_address`, `lat`, `lon`, `temperature`, `humidity`, `infrared`, `smoke`, `light_intensity`, `control_mode`, `status`, `register_time`)
values('3','uniqueCode0003','幸福','广州市天河区','40','40',NULL,NULL,NULL,NULL,NULL,'全自动控制','正常','2018-01-31 17:38:54');


/*设备数据表*/
insert into `device` (`id`, `family_id`, `name`, `description`, `location`, `info`, `registert_time`, `status`, `control_mode`,`img`)
values('1','1','curtain','智能窗帘','屋子北边','会动的窗帘','2018-02-01 00:14:05','正常','手动','img123');
insert into `device` (`id`, `family_id`, `name`, `description`, `location`, `info`, `registert_time`, `status`, `control_mode`,`img`)
values('2','1','door','智能门禁','门口','有人脸识别的门禁','2018-02-01 00:09:24','正常','手动','img123');
insert into `device` (`id`, `family_id`, `name`, `description`, `location`, `info`, `registert_time`, `status`, `control_mode`,`img`)
values('3','1','kettle','智能水壶','屋子里面','智能控制的水壶','2018-02-01 00:09:39','正常','手动','img123');
insert into `device` (`id`, `family_id`, `name`, `description`, `location`, `info`, `registert_time`, `status`, `control_mode`,`img`)
values('4','1','light','智能灯','屋子里面','比神灯还厉害','2018-02-01 00:10:00','正常','手动','img123');
insert into `device` (`id`, `family_id`, `name`, `description`, `location`, `info`, `registert_time`, `status`, `control_mode`,`img`)
values('5','2','door','智能门禁','门口','有人脸识别的门禁','2018-02-01 00:10:15','正常','手动','img123');
insert into `device` (`id`, `family_id`, `name`, `description`, `location`, `info`, `registert_time`, `status`, `control_mode`,`img`)
values('6','2','kettle','智能水壶','屋子里面','智能控制的水壶','2018-02-01 00:10:24','正常','手动','img123');
insert into `device` (`id`, `family_id`, `name`, `description`, `location`, `info`, `registert_time`, `status`, `control_mode`,`img`)
values('7','3','door','智能门禁','门口','有人脸识别的门禁','2018-02-01 00:10:41','正常','手动','img123');
insert into `device` (`id`, `family_id`, `name`, `description`, `location`, `info`, `registert_time`, `status`, `control_mode`,`img`)
values('8','3','kettle','智能水壶','屋子里面','智能控制的水壶','2018-02-01 00:10:49','正常','手动','img123');
insert into `device` (`id`, `family_id`, `name`, `description`, `location`, `info`, `registert_time`, `status`, `control_mode`,`img`)
values('9','3','light','智能灯','屋子里面','比神灯还厉害','2018-02-01 00:10:59','正常','手动','img123');

/*设备操作数据*/
insert into `device_operator` (`id`, `device_id`, `operator_name`, `operator_code`, `description`, `register_time`, `status`)
values('1','1','智能窗帘开','curtain_open','对智能窗帘的开指令','2018-02-01 14:27:09','正常');
insert into `device_operator` (`id`, `device_id`, `operator_name`, `operator_code`, `description`, `register_time`, `status`)
values('2','1','智能窗帘关','curtain_close','对智能窗帘的关指令','2018-02-01 01:15:45','正常');
insert into `device_operator` (`id`, `device_id`, `operator_name`, `operator_code`, `description`, `register_time`, `status`)
values('3','2','门禁开','TD11','对智能门禁的开指令','2018-02-01 01:15:52','正常');
insert into `device_operator` (`id`, `device_id`, `operator_name`, `operator_code`, `description`, `register_time`, `status`)
values('4','2','门禁关','TD10','对智能门禁的关指令','2018-02-01 01:15:53','正常');
insert into `device_operator` (`id`, `device_id`, `operator_name`, `operator_code`, `description`, `register_time`, `status`)
values('5','3','水壶开','TR11','对智能水壶的开指令','2018-02-01 01:15:54','正常');
insert into `device_operator` (`id`, `device_id`, `operator_name`, `operator_code`, `description`, `register_time`, `status`)
values('6','3','水壶关','TR10','对智能水壶的关指令','2018-02-01 01:15:55','正常');
insert into `device_operator` (`id`, `device_id`, `operator_name`, `operator_code`, `description`, `register_time`, `status`)
values('7','4','电灯开','TL11','对智能电灯的开指令','2018-02-01 01:45:52','正常');
insert into `device_operator` (`id`, `device_id`, `operator_name`, `operator_code`, `description`, `register_time`, `status`)
values('8','4','电灯关','TL10','对智能电灯的关指令','2018-02-01 01:35:52','正常');
insert into `device_operator` (`id`, `device_id`, `operator_name`, `operator_code`, `description`, `register_time`, `status`)
values('9','3','重煮沸','TR12','对智能水壶的重煮沸指令','2018-02-01 01:15:54','正常');
insert into `device_operator` (`id`, `device_id`, `operator_name`, `operator_code`, `description`, `register_time`, `status`)
values('10','3','恒温煮','TR13-XX','对智能水壶的恒温煮指令','2018-02-01 01:15:55','正常');

/*日志数据*/
insert into `log` (`id`, `family_id`, `type`, `info`, `status`, `generate_time`)
values('1','1','普通日志','开灯操作','正常','2018-01-31 20:55:23');
insert into `log` (`id`, `family_id`, `type`, `info`, `status`, `generate_time`)
values('2','1','报警日志','温度过高','正常','2018-01-31 20:50:37');
insert into `log` (`id`, `family_id`, `type`, `info`, `status`, `generate_time`)
values('3','2','普通日志','关灯操作','正常','2018-01-31 20:55:23');
insert into `log` (`id`, `family_id`, `type`, `info`, `status`, `generate_time`)
values('4','2','报警日志','光强过高','正常','2018-01-31 20:50:37');
insert into `log` (`id`, `family_id`, `type`, `info`, `status`, `generate_time`)
values('5','3','普通日志','开热水壶操作','正常','2018-01-31 20:55:23');
insert into `log` (`id`, `family_id`, `type`, `info`, `status`, `generate_time`)
values('6','3','报警日志','湿度过高','正常','2018-01-31 20:50:37');


/*用户表*/
insert into `user` (`id`, `family_id`, `username`, `nickname`,`password`, `email`, `phone`, `photo`, `role`, `code`, `status`, `register_time`)
values('1','1','caoxin','caoxin','111111','123456@qq.com','13535570616','img/cjk1.jpg','管理员',NULL,'正常','2018-01-31 21:03:17');
insert into `user` (`id`, `family_id`, `username`, `password`, `email`, `phone`, `photo`, `role`, `code`, `status`, `register_time`)
values('2','2','weisheng','111111','123798@qq.com','13514561284','img/cjk2.jpg','管理员',NULL,'正常','2018-02-01 16:58:46');
insert into `user` (`id`, `family_id`, `username`, `password`, `email`, `phone`, `photo`, `role`, `code`, `status`, `register_time`)
values('3','3','tianzhao','111111','123654@qq.com','13002090126','img/cjk3.jpg','管理员',NULL,'正常','2018-01-31 21:10:04');


/*传感器数据 1.光照传感器 2.温度传感器 3.容量传感器*/
insert into `sensor` (`id`, `device_id`, `sensor_type_id`, `name`, `info`, `description`, `status`, `register_time`)
values('1','1','1','窗帘光照传感器','光照传感器','光照传感器','正常','2018-02-01 01:45:40');
insert into `sensor` (`id`, `device_id`, `sensor_type_id`, `name`, `info`, `description`, `status`, `register_time`)
values('2','3','2','水壶温度传感器','水壶温度','水壶温度','正常','2018-02-01 01:43:11');
insert into `sensor` (`id`, `device_id`, `sensor_type_id`, `name`, `info`, `description`, `status`, `register_time`)
values('3','3','3','水壶容量传感器','水壶容量','水壶容量','正常','2018-02-01 01:43:20');
insert into `sensor` (`id`, `device_id`, `sensor_type_id`, `name`, `info`, `description`, `status`, `register_time`)
values('4','4','1','灯光强传感器','光强','光强','正常','2018-02-01 01:43:20');

/*传感器类型数据*/
insert into `sensor_type` (`id`, `name`, `info`, `description`, `status`)
values('1','光照传感器','光照传感器','光照传感器','正常');
insert into `sensor_type` (`id`, `name`, `info`, `description`, `status`)
values('2','温度传感器','温度传感器','温度传感器','正常');
insert into `sensor_type` (`id`, `name`, `info`, `description`, `status`)
values('3','容量传感器','容量传感器','容量传感器','正常');


/*传感器数据的数据*/
insert into `sensor_data` (`id`, `sensor_id`, `data_name`, `data_value`, `collection_time`, `info`, `status`)
values('1','1','光照','500','2018-02-01 02:08:26',NULL,'正常');
insert into `sensor_data` (`id`, `sensor_id`, `data_name`, `data_value`, `collection_time`, `info`, `status`)
values('2','1','光照','300','2018-02-01 02:05:28',NULL,'正常');
insert into `sensor_data` (`id`, `sensor_id`, `data_name`, `data_value`, `collection_time`, `info`, `status`)
values('3','1','光照','400','2018-02-01 02:05:40',NULL,'正常');
insert into `sensor_data` (`id`, `sensor_id`, `data_name`, `data_value`, `collection_time`, `info`, `status`)
values('4','2','温度','30','2018-02-01 02:08:26',NULL,'正常');
insert into `sensor_data` (`id`, `sensor_id`, `data_name`, `data_value`, `collection_time`, `info`, `status`)
values('5','2','温度','10','2018-02-01 02:05:28',NULL,'正常');
insert into `sensor_data` (`id`, `sensor_id`, `data_name`, `data_value`, `collection_time`, `info`, `status`)
values('6','2','温度','5','2018-02-01 02:05:40',NULL,'正常');
insert into `sensor_data` (`id`, `sensor_id`, `data_name`, `data_value`, `collection_time`, `info`, `status`)
values('7','3','容量','12','2018-02-01 02:08:26',NULL,'正常');
insert into `sensor_data` (`id`, `sensor_id`, `data_name`, `data_value`, `collection_time`, `info`, `status`)
values('8','3','容量','19','2018-02-01 02:05:28',NULL,'正常');
insert into `sensor_data` (`id`, `sensor_id`, `data_name`, `data_value`, `collection_time`, `info`, `status`)
values('9','3','容量','8','2018-02-01 02:05:40',NULL,'正常');
insert into `sensor_data` (`id`, `sensor_id`, `data_name`, `data_value`, `collection_time`, `info`, `status`)
values('10','1','光照','351','2018-02-01 02:08:26',NULL,'正常');
insert into `sensor_data` (`id`, `sensor_id`, `data_name`, `data_value`, `collection_time`, `info`, `status`)
values('11','1','光照','369','2018-02-01 02:05:28',NULL,'正常');
insert into `sensor_data` (`id`, `sensor_id`, `data_name`, `data_value`, `collection_time`, `info`, `status`)
values('12','1','光照','354','2018-02-01 02:05:40',NULL,'正常');

