DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `following`;
CREATE TABLE `following` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `follower_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `moment`;
CREATE TABLE `moment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `body` VARCHAR(255) CHARACTER SET utf8 NOT NULl,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

INSERT INTO `user` VALUES (1, '周杰伦', null);
INSERT INTO `user` VALUES (2, '蔡依林', null);
INSERT INTO `user` VALUES (3, 'Hebe', null);

INSERT INTO `following` VALUES (NULL, 1, 2);
INSERT INTO `following` VALUES (NULL, 1, 3);
INSERT INTO `following` VALUES (NULL, 2, 1);
INSERT INTO `following` VALUES (NULL, 3, 1);

INSERT INTO `moment` VALUES (NULL, 1, '周杰伦说了句话', NOW(), NOW());
INSERT INTO `moment` VALUES (NULL, 2, '蔡依林坐不住了', NOW(), NOW());