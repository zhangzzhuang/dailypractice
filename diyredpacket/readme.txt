
/*红包库存表*/
DROP TABLE IF EXISTS `t_red_packet`;
CREATE TABLE `t_red_packet` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `user_id` int(12) NOT NULL,
  `amount` decimal(16,2) NOT NULL,
  `send_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `total` int(12) NOT NULL,
  `unit_amount` decimal(12,0) NOT NULL,
  `stock` int(12) UNSIGNED NOT NULL,
  `version` int(12) NOT NULL DEFAULT '0',
  `note` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*插入一个20万元金额，2万个小红包，每个10元的红包数据 */
insert  into `t_red_packet`(`user_id`,`amount`,`send_date`,`total`,`unit_amount`,`stock`,`version`,`note`) values (1,'200000.00','2019-07-29 10:47:20',20000,'10',20000,0,'20万元金额，2万个小红包，每个10元');
/*中奖记录表 */
DROP TABLE IF EXISTS `t_user_red_packet`;
CREATE TABLE `t_user_red_packet` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `red_packet_id` int(12) NOT NULL,
  `user_id` int(12) NOT NULL,
  `amount` decimal(16,2) NOT NULL,
  `grab_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `note` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;