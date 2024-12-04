-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: opensourcedb
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('hanhan','$2a$10$SZtB1LPZvEUYxr3XgozZcueXxPmU6cyGmW5/Xunmninn7mv3P3nce','haon'),('hyunj','$2a$10$jkr/M7KL8IQs5KtKUCZySe.4rC1/enVGlvSI9izXNTVWGJdqXYHme','hyunhyun'),('iamhyun','$2a$10$Ei0kAm4NRGS1GJhomlwdtunp3QTbfaOI2GCyvl6Eq8f5GsdRhtqZ.','hamstar'),('nemo1222','$2a$10$oGZ7nzyuKY2nlRt4Aynjj.MiC/hfs/bjmyjAQivsWUuvQ8epG5PeO','iamnemo'),('sophie12','$2a$10$4lJ22VaWMQ6P5gtCrYckrOCGqjx8WoHw/8LVfHBmrvg8oHPA3znmO','iamsophie'),('sophie1222','$2a$10$5RQi3yLJZFeWQkf85QQLd.fSAtcAspRN.EQLoCP9EYPx5S1fCerxK','iamhj'),('straw12','$2a$10$zxa4MZIoIX9s.kc5ig7La.9XblnUAyYa8qF4byAJsGKSbatw1Yimy','berry'),('yellow02','$2a$10$UHy35ZspVMAaqbf00OZfDOv4P3PJMTNpd/vfTDod6ZaC.9XoowYBm','iammuji');
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

-- Dump completed on 2024-12-04 12:17:28
