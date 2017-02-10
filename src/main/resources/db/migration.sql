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
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_following_on_user_id_and_follower_id` (`user_id`,`follower_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `moment`;
CREATE TABLE `moment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `body` VARCHAR(255) CHARACTER SET utf8 NOT NULl,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_moment_on_user_id` (`user_id`)
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
  PRIMARY KEY (`id`),
  KEY `index_comment_on_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
INSERT INTO `user` VALUES (1, '周杰伦', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image1.jpg');
INSERT INTO `user` VALUES (2, '蔡依林', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image2.jpg');
INSERT INTO `user` VALUES (3, 'Hebe', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image3.jpg');
INSERT INTO `user` VALUES (4, '邓紫棋', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image4.jpg');
INSERT INTO `user` VALUES (5, '李易峰', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image5.jpg');
INSERT INTO `user` VALUES (6, '范冰冰', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image6.jpg');
INSERT INTO `user` VALUES (7, '杨幂', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image7.jpg');
INSERT INTO `user` VALUES (8, 'Angelababy', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image8.jpg');
INSERT INTO `user` VALUES (9, '唐嫣', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image9.jpg');
INSERT INTO `user` VALUES (10, '吴亦凡', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image10.jpg');
INSERT INTO `user` VALUES (11, '刘诗诗', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image11.jpg');
INSERT INTO `user` VALUES (12, '周星驰', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image12.jpg');
INSERT INTO `user` VALUES (13, '鹿晗', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image13.jpg');
INSERT INTO `user` VALUES (14, '赵丽颖', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image14.jpg');
INSERT INTO `user` VALUES (15, '张柏芝', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image15.jpg');
INSERT INTO `user` VALUES (16, '波多野结衣', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image16.jpg');
INSERT INTO `user` VALUES (17, '苍井空', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image17.jpg');
INSERT INTO `user` VALUES (18, '柯震东', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image18.jpg');
INSERT INTO `user` VALUES (19, '赵本山', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image19.jpg');
INSERT INTO `user` VALUES (20, '刘德华', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image20.jpg');
INSERT INTO `user` VALUES (21, '谢霆锋', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image21.jpg');
INSERT INTO `user` VALUES (22, '林志颖', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image22.jpg');
INSERT INTO `user` VALUES (23, '谢娜', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image23.jpg');
INSERT INTO `user` VALUES (24, '杨紫', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image24.jpg');
INSERT INTO `user` VALUES (25, '汪峰', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image25.jpg');
INSERT INTO `user` VALUES (26, '章子怡', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image26.jpg');
INSERT INTO `user` VALUES (27, '吴镇宇', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image27.jpg');
INSERT INTO `user` VALUES (28, '郭德纲', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image28.jpg');
INSERT INTO `user` VALUES (29, '黄秋生', 'http://moments-asset.oss-cn-shenzhen.aliyuncs.com/image29.jpg');

INSERT INTO `following` VALUES (NULL, 1, 2);
INSERT INTO `following` VALUES (NULL, 1, 3);
INSERT INTO `following` VALUES (NULL, 2, 1);
INSERT INTO `following` VALUES (NULL, 3, 1);

INSERT INTO `moment` VALUES (NULL, 1, '天气真好~~', NOW(), NOW());
INSERT INTO `moment` VALUES (NULL, 2, '蔡依林：呵呵', NOW(), NOW());

INSERT INTO `comment` VALUES (NULL, 1, '是啊，天气好。', 1, 3, 'Hebe', '', NULL, NULL, NULL, NOW(), NOW());
INSERT INTO `comment` VALUES (NULL, 1, '好。', 1, 1, '周杰伦', '', 3, 'Hebe', '', NOW(), NOW());
INSERT INTO `comment` VALUES (NULL, 1, NULL, 2, 1, '周杰伦', '', NULL, NULL, NULL, NOW(), NOW());
INSERT INTO `comment` VALUES (NULL, 1, NULL, 2, 2, 'Hebe', '', NULL, NULL, NULL, NOW(), NOW());