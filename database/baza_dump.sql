-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: is1proj
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `obaveze`
--

DROP TABLE IF EXISTS `obaveze`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `obaveze` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `coordX` decimal(10,3) DEFAULT NULL,
  `coordY` decimal(10,3) DEFAULT NULL,
  `vreme` datetime NOT NULL,
  `opis` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obaveze`
--

LOCK TABLES `obaveze` WRITE;
/*!40000 ALTER TABLE `obaveze` DISABLE KEYS */;
INSERT INTO `obaveze` VALUES (1,44.799,20.390,'2019-06-30 12:00:00','zubar'),(2,44.799,20.390,'2019-06-30 13:00:00','Sastanak promenjeno'),(4,44.787,20.457,'2019-07-05 12:00:00','tenis'),(7,44.812,20.454,'2019-07-01 04:50:00','sminka'),(8,44.812,20.454,'2019-07-01 04:50:00','sminka'),(10,44.822,20.461,'2019-07-02 01:15:00','neka obaveza');
/*!40000 ALTER TABLE `obaveze` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pesme`
--

DROP TABLE IF EXISTS `pesme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pesme` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) NOT NULL,
  `url` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_pesme_naziv` (`naziv`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pesme`
--

LOCK TABLES `pesme` WRITE;
/*!40000 ALTER TABLE `pesme` DISABLE KEYS */;
INSERT INTO `pesme` VALUES (1,'Scorpions - No one like you','https://www.youtube.com/watch?v=fRmbCIAz4c8'),(2,'Billy Idol - Rebel Yell','https://www.youtube.com/watch?v=BPwZaQfoIbU'),(3,'Beethoven - Silence','https://www.youtube.com/watch?v=YFD2PPAqNbw'),(4,'Beethoven - Moonlight Sonata','https://www.youtube.com/watch?v=5-MT5zeY6CU'),(5,'default alarm','https://youtu.be/xaXLLF2qm20'),(6,'Mahmut Orhan - Save me','https://www.youtube.com/watch?v=FZ_paWpT9Mo'),(7,'Sofi Tukker - Playa Grande','https://www.youtube.com/watch?v=sxxy-kaeO-o'),(8,'Gigi DAgostino - LAmour Toujours','https://www.youtube.com/watch?v=w15oWDh02K4');
/*!40000 ALTER TABLE `pesme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reprodukovano`
--

DROP TABLE IF EXISTS `reprodukovano`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reprodukovano` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idP` int(11) NOT NULL,
  `vreme` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reprodukovano_idP` (`idP`),
  CONSTRAINT `fk_reprodukovano_idP` FOREIGN KEY (`idP`) REFERENCES `pesme` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reprodukovano`
--

LOCK TABLES `reprodukovano` WRITE;
/*!40000 ALTER TABLE `reprodukovano` DISABLE KEYS */;
INSERT INTO `reprodukovano` VALUES (1,1,'2019-06-29 23:25:46'),(2,4,'2019-06-29 23:25:47'),(3,1,'2019-06-29 23:25:48'),(4,4,'2019-06-29 23:25:48'),(5,1,'2019-06-29 23:25:48'),(6,4,'2019-06-29 23:25:48'),(7,1,'2019-06-29 23:25:48'),(8,4,'2019-06-29 23:25:49'),(9,8,'2019-06-30 20:58:44'),(10,4,'2019-06-30 20:58:45'),(11,8,'2019-07-01 21:33:51');
/*!40000 ALTER TABLE `reprodukovano` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-02  4:32:20
