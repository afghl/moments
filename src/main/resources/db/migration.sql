DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `users`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;


INSERT INTO `user` VALUES (null, '管理员', null);
INSERT INTO `user` VALUES (null, '邯郸', null);