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
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_number_UNIQUE` (`phone_number`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Petr','Petrovich','+275292534131','petr@gmail.com',0),(2,'Vladislav','Kroh','+375293342315','kroh@mail.ru',15),(3,'Polina','Eremeeva','+375298513475','eremeevap@gmail.com',20),(4,'test','test','+375290000001','test1@mail.ru',0),(5,'test','test','+375290000002','test2@mail.ru',0),(6,'test','test','+375290000004','test3@mail.ru',0),(7,'test','test','+375290000005','test4@mail.ru',0),(8,'Artem','Filimonov','+375293335577','filiart@mail.ru',40),(9,'Dmitriy','Krivolap','+375294413573','Krivolap@mail.ru',10),(10,'Veronica','Navros','+375293153852','navros@mail.ru',20),(11,'Evgeniy','Krotov','+375298841263','krotov@gmail.com',10),(12,'Alexander','Galuzo','+375294182352','galuzich@mail.ru',20),(13,'Evgeniy','Smirnov','+375297628519','smirnov@gmail.com',0),(14,'Filip','Lipnitskiy','+375296125284','linfil@gmail.com',0),(44,'test','test','+375290000000','test5@mail.ru',0),(45,'test','test','+375290000008','test6@mail.ru',0),(46,'test','test','+375290000007','test7@mail.ru',0),(47,'test','test','+375290000006','test8@mail.ru',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
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
  CONSTRAINT `fk_document_order` FOREIGN KEY (`order_id`) REFERENCES `workorder` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
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
  CONSTRAINT `FK9864r0y3hw7tpqldiga07qknb` FOREIGN KEY (`id_detail`) REFERENCES `detail` (`id`),
  CONSTRAINT `FKr5854xfplelloxq9u0pjaq36p` FOREIGN KEY (`id_order`) REFERENCES `workorder` (`id`),
  CONSTRAINT `fk_od_detail` FOREIGN KEY (`id_detail`) REFERENCES `detail` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_od_order` FOREIGN KEY (`id_order`) REFERENCES `workorder` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
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
  CONSTRAINT `FKke9qawwor10hxecsv693j7kfr` FOREIGN KEY (`id_pricelist`) REFERENCES `pricelist` (`id`),
  CONSTRAINT `FKr5bjsbska9ctcqqmt5dxu88p4` FOREIGN KEY (`id_order`) REFERENCES `workorder` (`id`),
  CONSTRAINT `fk_this_order` FOREIGN KEY (`id_order`) REFERENCES `workorder` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
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
  `role` varchar(20) NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Vasia','Pupkin','+375293242404','pypkin@gmail.com','ROLE_ADMIN','admin','21232f297a57a5a743894a0e4a801fc3'),(2,'Sergey','Pashkovski','+375291111111','pashgan@gmail.com','ROLE_MANAGER','manager','1d0258c2440a8d19e716292b231e3190'),(3,'Aleksey','Kozlovski','+375293416453','kozlovski@gmail.com','ROLE_ENGINEER','engineer','9d127ff383d595262c67036f50493133'),(4,'Anastasia','Gelda','+375294716538','gelga@gmail.com','ROLE_ENGINEER','ananas','fc2a36b07f5bf1c68ed52b636b74fe45'),(5,'Gleb','Lehchilov','+375292348123','lehchilov@gmail.com','ROLE_ENGINEER','betrazen','bf88208183d1580d00d210f4c8d7ecfe'),(6,'Vladislav','Lukianchik','+375294729513','vlaslav@gmail.com','ROLE_ENGINEER','vlaslav','89f47cb3e8799391abc70d921385e2b5'),(7,'Yury','Sych','+375295711243','sych_y@gmail.com','ROLE_ENGINEER','nestok','622ea4f8e4866f6d40b0a5daa913f3ac');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workorder`
--

DROP TABLE IF EXISTS `workorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_engineer` int(11) DEFAULT NULL,
  `id_manager` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `description` varchar(50) NOT NULL,
  `problem` text NOT NULL,
  `status` varchar(40) NOT NULL,
  `create_at` varchar(15) NOT NULL,
  `complete_at` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_engineer_idx` (`id_engineer`),
  KEY `fk_order_manager_idx` (`id_manager`),
  KEY `fk_order_client_idx` (`id_client`),
  CONSTRAINT `FKie0fgxq876nnr0eydr4c3n4q4` FOREIGN KEY (`id_engineer`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_order_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_order_engineer` FOREIGN KEY (`id_engineer`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_manager` FOREIGN KEY (`id_manager`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workorder`
--

LOCK TABLES `workorder` WRITE;
/*!40000 ALTER TABLE `workorder` DISABLE KEYS */;
INSERT INTO `workorder` VALUES (1,3,2,1,'Display is broken','many text many text  many text many text many text many text many textmany text many text  many text many text many text many text many textmany text many text  many text many text many text many text many textmany text many text  many text many text many text many text many text','Closed','2017-04-27',NULL),(3,5,6,2,'Burned processor','Burned processor','In work','2017-04-29',NULL),(4,3,2,4,'Problem with cpu','crushed cpu','Closed','2017-05-20',NULL),(5,5,2,5,'test order description','test order problem','Open','2017-05-20',NULL),(6,6,2,6,'test order description','test order problem','Open','2017-05-20',NULL),(7,4,2,7,'test order description','test order problem','Complete','2017-05-20',NULL),(8,7,2,7,'Broken touchpad','Broken touchpad','Complete','2017-04-03','2017-05-01'),(9,5,6,8,'Sticking of a power button','Sticking of a power button','In work','2015-05-05',NULL),(11,7,2,10,'Cooler isn\'t working','Cooler isn\'t working','Open','2017-04-06',NULL);
/*!40000 ALTER TABLE `workorder` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-24 16:26:35
