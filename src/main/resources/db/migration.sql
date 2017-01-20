DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `moment_id` int(11) NOT NULL,
  `body` VARCHAR(255) CHARACTER SET utf8 DEFAULT NULl,
  `type` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `user_avatar` varchar(255) CHARACTER SET utf8 NOT NULL,
  `other_id` int(11) DEFAULT NULL,
  `other_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `other_avatar` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

INSERT INTO `user` VALUES (1, '周杰伦', '');
INSERT INTO `user` VALUES (2, '蔡依林', '');
INSERT INTO `user` VALUES (3, 'Hebe', '');

INSERT INTO `following` VALUES (NULL, 1, 2);
INSERT INTO `following` VALUES (NULL, 1, 3);
INSERT INTO `following` VALUES (NULL, 2, 1);
INSERT INTO `following` VALUES (NULL, 3, 1);

INSERT INTO `moment` VALUES (NULL, 1, '周杰伦说了句话', NOW(), NOW());
INSERT INTO `moment` VALUES (NULL, 2, '蔡依林坐不住了', NOW(), NOW());

INSERT INTO `comment` VALUES (NULL, 1, 'Hebe在杰伦的动态里回复了一句', 1, 3, 'Hebe', '', NULL, NULL, NULL, NOW(), NOW());
INSERT INTO `comment` VALUES (NULL, 1, '杰伦回复了Hebe一句！', 1, 1, '周杰伦', '', 3, 'Hebe', '', NOW(), NOW());
INSERT INTO `comment` VALUES (NULL, 1, NULL, 2, 1, '周杰伦', '', NULL, NULL, NULL, NOW(), NOW());
INSERT INTO `comment` VALUES (NULL, 1, NULL, 2, 2, 'Hebe', '', NULL, NULL, NULL, NOW(), NOW());