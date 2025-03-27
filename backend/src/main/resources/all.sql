/*
SQLyog Ultimate v10.00 Beta1
MySQL - 8.0.31 : Database - demo_db
*******************************************************************
*/

/*!40101 SET SQL_MODE=''*/;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`demo_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `demo_db`;


DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
                          `id` BIGINT NOT NULL AUTO_INCREMENT,
                          `teacher_id` BIGINT DEFAULT NULL,
                          `name` VARCHAR(255) DEFAULT NULL,
                          `rest_num` BIGINT DEFAULT NULL,
                          `total` BIGINT DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

/*Data for the table `course` */

INSERT  INTO `course`(`id`,`teacher_id`,`name`,`rest_num`,`total`) VALUES (1,1,'JavaEE企业级应用',24,48),(2,2,'C语言程序基础与设计',24,48),(3,3,'C语言数据与结构',24,48),(4,4,'python爬虫',24,48),(5,5,'道德与法治',24,48),(6,6,'Vue',24,48),(7,1,'NodeJs',24,24),(8,6,'JavaScript',24,24);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
                           `id` BIGINT NOT NULL AUTO_INCREMENT,
                           `course_id` BIGINT DEFAULT NULL,
                           `name` VARCHAR(255) DEFAULT NULL,
                           `sex` VARCHAR(255) DEFAULT NULL,
                           `age` INT DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

/*Data for the table `student` */

INSERT  INTO `student`(`id`,`course_id`,`name`,`sex`,`age`) VALUES (1,1,'Aimer','女',25),(2,2,'Griffen Oskar','男',35),(3,3,'Masetti','男',27),(4,4,'Sophie Rose','女',19),(5,5,'Savoir Adore','女',24),(6,6,'Joysaaaa','女',20),(7,7,'Taylor Swift','女',36);

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
                           `id` BIGINT NOT NULL AUTO_INCREMENT,
                           `name` VARCHAR(255) DEFAULT NULL,
                           `sex` VARCHAR(255) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

/*Data for the table `teacher` */

INSERT  INTO `teacher`(`id`,`name`,`sex`) VALUES (1,'邱儒','男'),(2,'金慧峰','女'),(3,'章理登','男'),(4,'符洪姿','女'),(5,'许久莹','女'),(6,'陈锡锻','男');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
                        `id` BIGINT NOT NULL AUTO_INCREMENT,
                        `username` VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL,
                        `password` VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT '123456',
                        `sex` VARCHAR(16) CHARACTER SET utf8mb4 DEFAULT NULL,
                        `age` BIGINT DEFAULT NULL,
                        `role` VARCHAR(16) DEFAULT NULL,
                        `online` INT DEFAULT '0' COMMENT '1:登录，0：未登录',
                        `create_at` DATETIME DEFAULT NULL,
                        `update_at` DATETIME DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

INSERT  INTO `user`(`id`,`username`,`password`,`sex`,`age`,`role`,`online`,`create_at`,`update_at`) VALUES (0,'admin','123456','男',19,'admin',1,'2023-08-15 12:09:08',NULL),(1,'ljr','123456','女',19,'admin',1,'2023-08-15 12:10:48',NULL),(2,'lzn','123456','男',19,'admin',0,'2023-08-15 12:12:17',NULL),(3,'ssw','123456','男',19,'admin',0,'2023-08-15 12:12:30',NULL),(4,'lgb','123456','男',19,'admin',0,'2023-08-15 12:13:06',NULL),(5,'cjy','123456','女',19,'admin',0,'2023-08-15 12:13:22',NULL),(6,'cyh','123456','女',19,'admin',0,'2023-08-15 12:13:46',NULL),(7,'lnn','123456','女',19,'admin',0,'2023-08-15 12:14:01',NULL),(8,'jjq','123456','女',19,'admin',0,'2023-08-15 12:14:18',NULL),(9,'myy','123456','女',19,'user',0,'2023-08-21 15:09:28','2023-08-21 15:09:28'),(10,'wch','123456','男',20,'admin',0,'2023-09-18 10:22:02','2023-09-18 10:22:02');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;