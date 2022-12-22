/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 8.0.30 : Database - db_ussd
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_ussd` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `db_ussd`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `accNum` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `accUserId` int unsigned DEFAULT NULL,
  `accBal` float DEFAULT '0',
  PRIMARY KEY (`accNum`),
  KEY `fk_accUserId_accNum` (`accUserId`),
  CONSTRAINT `fk_accUserId_accNum` FOREIGN KEY (`accUserId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `account` */

/*Table structure for table `transaction` */

DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction` (
  `transId` int DEFAULT NULL,
  `transAccNum` int unsigned DEFAULT NULL,
  `transType` char(1) DEFAULT NULL,
  `transDate` date DEFAULT NULL,
  KEY `fk_accNum_transAccNum` (`transAccNum`),
  CONSTRAINT `fk_accNum_transAccNum` FOREIGN KEY (`transAccNum`) REFERENCES `account` (`accNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `transaction` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userId` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `userEmail` varchar(50) DEFAULT NULL,
  `userName` varchar(50) DEFAULT NULL,
  `userSurname` varchar(50) DEFAULT NULL,
  `userPhone` varchar(10) DEFAULT NULL,
  `userPin` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `user` */

insert  into `user`(`userId`,`userEmail`,`userName`,`userSurname`,`userPhone`,`userPin`) values 
(00001,'user@mail.com','Menzi','Mazibuko','0821111111','0821'),
(00002,'user2@mail.com','Themba','Mbatha','0762222222','1100');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
