/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.7.36 : Database - course_system
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`course_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `course_system`;

/*Table structure for table `accounts` */

DROP TABLE IF EXISTS `accounts`;

CREATE TABLE `accounts` (
  `account` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `student_id` int(11) DEFAULT NULL,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`account`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `accounts_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `accounts` */

insert  into `accounts`(`account`,`password`,`student_id`,`type`) values ('admin','123',1,'admin'),('user01','123',1,'student'),('user03','password123',3,'student'),('user05','password123',5,'student'),('user06','password123',6,'student'),('user07','password123',7,'student'),('user08','password123',8,'student'),('user09','password123',9,'student'),('user10','password123',10,'student');

/*Table structure for table `lessons` */

DROP TABLE IF EXISTS `lessons`;

CREATE TABLE `lessons` (
  `lesson_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程编号',
  `lesson_name` varchar(255) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  `major_id` int(11) NOT NULL,
  `lesson_location` varchar(255) DEFAULT NULL,
  `lesson_time` varchar(255) NOT NULL,
  `lesson_choosen_num` int(11) NOT NULL,
  `lesson_max_num` int(11) NOT NULL,
  PRIMARY KEY (`lesson_id`),
  KEY `teacher_id` (`teacher_id`),
  KEY `major_id` (`major_id`),
  CONSTRAINT `lessons_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`teacher_id`),
  CONSTRAINT `lessons_ibfk_2` FOREIGN KEY (`major_id`) REFERENCES `majors` (`major_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

/*Data for the table `lessons` */

insert  into `lessons`(`lesson_id`,`lesson_name`,`teacher_id`,`major_id`,`lesson_location`,`lesson_time`,`lesson_choosen_num`,`lesson_max_num`) values (1,'计算机基础',1,1,'教室101','周一 第一节-第二节',3,30),(3,'有机化学',3,3,'教室301','周二 第一节-第二节',1,20),(4,'动物解剖学',4,4,'实验室401','周二 第三节-第四节',0,15),(5,'高等数学',5,5,'教室501','周三 第一节-第二节',40,40),(6,'英语口语',6,6,'教室601','周三 第三节-第四节',0,35),(7,'世界历史',7,7,'教室701','周四 第一节-第二节',0,30),(8,'地理知识',8,8,'教室801','周四 第三节-第四节',0,25),(9,'哲学导论',9,9,'教室901','周五 第一节-第二节',0,20),(10,'艺术鉴赏',10,10,'教室1001','周五 第三节-第四节',0,15),(13,'数学',10,10,'1001','111',0,50);

/*Table structure for table `majors` */

DROP TABLE IF EXISTS `majors`;

CREATE TABLE `majors` (
  `major_id` int(11) NOT NULL AUTO_INCREMENT,
  `major_name` varchar(255) NOT NULL,
  PRIMARY KEY (`major_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

/*Data for the table `majors` */

insert  into `majors`(`major_id`,`major_name`) values (1,'心里学'),(2,'物理学'),(3,'化学'),(4,'生物学'),(5,'数学'),(6,'英语'),(7,'历史'),(8,'地理'),(9,'哲学'),(10,'艺术史'),(11,'计算机科学'),(12,'民法'),(13,'刑法'),(14,'艺术建筑考古'),(15,'艺术建筑');

/*Table structure for table `students` */

DROP TABLE IF EXISTS `students`;

CREATE TABLE `students` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(255) NOT NULL,
  `student_sex` varchar(255) DEFAULT NULL,
  `major_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  KEY `major_id` (`major_id`),
  CONSTRAINT `students_ibfk_1` FOREIGN KEY (`major_id`) REFERENCES `majors` (`major_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

/*Data for the table `students` */

insert  into `students`(`student_id`,`student_name`,`student_sex`,`major_id`) values (1,'小明','男',1),(3,'小刚','男',3),(5,'小强','男',5),(6,'小芳','女',6),(7,'小杰','男',7),(8,'小霞','女',8),(9,'小虎','男',9),(10,'小琳','女',10),(11,'帆帆帆帆','男',2),(12,'烦烦烦方法','男',1);

/*Table structure for table `students_lessons` */

DROP TABLE IF EXISTS `students_lessons`;

CREATE TABLE `students_lessons` (
  `student_id` int(11) NOT NULL,
  `lesson_id` int(11) NOT NULL,
  KEY `lesson_id` (`lesson_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `students_lessons_ibfk_2` FOREIGN KEY (`lesson_id`) REFERENCES `lessons` (`lesson_id`),
  CONSTRAINT `students_lessons_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `students_lessons` */

insert  into `students_lessons`(`student_id`,`lesson_id`) values (1,1),(1,3);

/*Table structure for table `teachers` */

DROP TABLE IF EXISTS `teachers`;

CREATE TABLE `teachers` (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(255) DEFAULT NULL,
  `teacher_sex` varchar(255) DEFAULT NULL,
  `major_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`),
  KEY `teacher_id` (`teacher_id`),
  KEY `major_id` (`major_id`),
  CONSTRAINT `teachers_ibfk_1` FOREIGN KEY (`major_id`) REFERENCES `majors` (`major_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

/*Data for the table `teachers` */

insert  into `teachers`(`teacher_id`,`teacher_name`,`teacher_sex`,`major_id`) values (1,'张三','男',1),(3,'零零零零','男',1),(4,'赵六','女',4),(5,'孙七','男',5),(6,'周八','女',6),(7,'吴九','男',7),(8,'郑十','女',8),(9,'陈十一','男',9),(10,'林十二','女',10),(16,'斤斤计较','男',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
