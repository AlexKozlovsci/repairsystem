-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: repairsystem
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `secondname` varchar(45) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `email` varchar(45) NOT NULL,
  `discount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Petr','Petrovich','+275292534131','petr@gmail.com',NULL),(2,'Vladislav','Kroh','+375293342315','kroh@mail.ru',15),(3,'Polina','Eremeeva','+375298513475','eremeevap@gmail.com',20),(4,'Alexander','Demidovich','+375296541286','demidalex@gmail.com',NULL),(5,'Andrey','Chembrovich','+375297425186','chembrovich@mail.ru',NULL),(6,'Karoline','Visockaya','+375295174925','visockaya@gmail.com',NULL),(7,'Alexsey','Gorbachevskiy','+375296438412','gorbach@gmail.com',50),(8,'Artem','Filimonov','+375293335577','filiart@mail.ru',40),(9,'Dmitriy','Krivolap','+375294413573','Krivolap@mail.ru',10),(10,'Veronica','Navros','+375293153852','navros@mail.ru',20),(11,'Evgeniy','Krotov','+375298841263','krotov@gmail.com',10),(12,'Alexander','Galuzo','+375294182352','galuzich@mail.ru',20),(13,'Evgeniy','Smirnov','+375297628519','smirnov@gmail.com',NULL),(14,'Filip','Lipnitskiy','+375296125284','linfil@gmail.com',NULL);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detail`
--

DROP TABLE IF EXISTS `detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `count` int(11) NOT NULL,
  `cost` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail`
--

LOCK TABLES `detail` WRITE;
/*!40000 ALTER TABLE `detail` DISABLE KEYS */;
INSERT INTO `detail` VALUES (1,'Processor Intel core i5-6600',20,172),(2,'Sound Card HP Wireless Audio',4,42),(3,'Sound card A4Tech Bloody G480',2,28),(4,'Cooler for processor Zalman CNPS11X',5,34),(5,'Cooler for processor STC VoTRON ZV-2100PW',12,15),(6,'Processor AMD A4-400',10,50),(7,'Motherboard ASRock X370 Killer SLI',2,150),(8,'Motherboard ASRock Z87 Pro4',2,83),(9,'Optical storage LG GH24NSD0',2,21),(10,'Video card Inno3D GeForce GT 730 1GB DDR3',3,55),(11,'Video card Inno3D GeForce GTX 1050 Ti 4GB GDDR5',4,165),(12,'RAM Kingston HyperX Savage 8GB DDR3 PC3-17000',5,85),(13,'RAM Samsung 8GB DDR4 PC4-17000',3,56),(14,'HDD Hitachi Ultrastar 7K6000 6TB',10,168),(15,'HDD Seagate Constellation ES.3 4TB',8,145),(16,'SSD Micron 1100 512GB',4,132),(17,'Power Supply FSP ATX-600PNR',10,61);
/*!40000 ALTER TABLE `detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `type` enum('order','report') NOT NULL,
  `file` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_document_order_idx` (`order_id`),
  CONSTRAINT `fk_document_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_engineer` int(11) NOT NULL,
  `id_manager` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `problem` text NOT NULL,
  `create_at` date NOT NULL,
  `complete_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_engineer_idx` (`id_engineer`),
  KEY `fk_order_manager_idx` (`id_manager`),
  KEY `fk_order_client_idx` (`id_client`),
  CONSTRAINT `fk_order_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_order_engineer` FOREIGN KEY (`id_engineer`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_order_manager` FOREIGN KEY (`id_manager`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,3,2,1,'Display is broken','2017-04-27',NULL),(3,5,6,2,'Burned processor','2017-04-29',NULL),(4,7,6,3,'Incorrect work of netvork adapter','2017-04-19',NULL),(5,3,2,4,'Broken motherboard','2017-04-21',NULL),(6,3,6,5,'HDD has no memory','2017-04-28',NULL),(7,5,2,6,'Broken web camera','2017-04-25',NULL),(8,7,2,7,'Broken touchpad','2017-05-03',NULL),(9,5,6,8,'Sticking of a power button','2015-05-05',NULL),(10,5,6,9,'No batrery charge','2017-04-16',NULL),(11,7,2,10,'Cooler isn\'t working','2017-04-06',NULL);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_detail` (
  `id_order` int(11) NOT NULL,
  `id_detail` int(11) NOT NULL,
  PRIMARY KEY (`id_order`,`id_detail`),
  KEY `fk_this_detail_idx` (`id_detail`),
  CONSTRAINT `fk_od_detail` FOREIGN KEY (`id_detail`) REFERENCES `detail` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_od_order` FOREIGN KEY (`id_order`) REFERENCES `order` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_pricelist`
--

DROP TABLE IF EXISTS `order_pricelist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_pricelist` (
  `id_order` int(11) NOT NULL,
  `id_pricelist` int(11) NOT NULL,
  PRIMARY KEY (`id_order`,`id_pricelist`),
  KEY `fk_this_pricelist_idx` (`id_pricelist`),
  CONSTRAINT `fk_this_order` FOREIGN KEY (`id_order`) REFERENCES `order` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_this_pricelist` FOREIGN KEY (`id_pricelist`) REFERENCES `pricelist` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_pricelist`
--

LOCK TABLES `order_pricelist` WRITE;
/*!40000 ALTER TABLE `order_pricelist` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_pricelist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pricelist`
--

DROP TABLE IF EXISTS `pricelist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pricelist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_type` varchar(45) NOT NULL,
  `action` text NOT NULL,
  `cost` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pricelist`
--

LOCK TABLES `pricelist` WRITE;
/*!40000 ALTER TABLE `pricelist` DISABLE KEYS */;
INSERT INTO `pricelist` VALUES (1,'Web camera','Change camerf',30),(2,'HDD','Replace condition',10),(3,'Keyboard','Fix button sticking',15),(4,'Processor','Change full processor',150),(5,'Sound card','Fix sound recognition',30),(6,'Power Supply','Fix hole housing',20),(7,'RAM','Change full RAM',50),(8,'Motherboard','Fix few connections',20),(9,'Video card','Change full video card',50),(10,'Cooler','Fixing the wings',27);
/*!40000 ALTER TABLE `pricelist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `secondname` varchar(45) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `email` varchar(45) NOT NULL,
  `role` enum('admin','engineer','manager') NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Vasia','Pupkin','+375293242404','pypkin@gmail.com','admin','admin','21232f297a57a5a743894a0e4a801fc3'),(2,'Sergey','Pashkovski','+375291111111','pashgan@gmail.com','manager','manager','1d0258c2440a8d19e716292b231e3190'),(3,'Aleksey','Kozlovski','+375293416453','kozlovski@gmail.com','engineer','engineer','9d127ff383d595262c67036f50493133'),(4,'Anastasia','Gelda','+375294716538','gelga@gmail.com','admin','Ananas','supergirl'),(5,'Gleb','Lehchilov','+375292348123','lehchilov@gmail.com','engineer','Betrazen','blooddragon'),(6,'Vladislav','Lukianchik','+375294729513','vlaslav@gmail.com','manager','Vlaslav','thebestofthebest'),(7,'Yury','Sych','+375295711243','sych_y@gmail.com','engineer','Nestok','megakrytoi');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-01 18:19:48
