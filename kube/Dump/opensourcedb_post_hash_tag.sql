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
-- Table structure for table `post_hash_tag`
--

DROP TABLE IF EXISTS `post_hash_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_hash_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `hashtag_id` bigint NOT NULL,
  `post_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd7b7hbbyv4limhtu2e9wnkfgp` (`hashtag_id`),
  KEY `FKnpt668kudcsr12uasqrpqxo10` (`post_id`),
  CONSTRAINT `FKd7b7hbbyv4limhtu2e9wnkfgp` FOREIGN KEY (`hashtag_id`) REFERENCES `hash_tag` (`hashtag_id`),
  CONSTRAINT `FKnpt668kudcsr12uasqrpqxo10` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_hash_tag`
--

LOCK TABLES `post_hash_tag` WRITE;
/*!40000 ALTER TABLE `post_hash_tag` DISABLE KEYS */;
INSERT INTO `post_hash_tag` VALUES (6,1,2),(7,2,2),(8,12,2),(9,16,2),(10,1,17),(11,10,17),(12,16,17),(13,1,18),(14,10,18),(15,12,18),(16,15,18),(17,16,18),(18,1,20),(19,2,20),(20,8,20),(21,9,20),(22,10,20),(23,15,20),(24,1,21),(25,7,21),(26,15,21),(27,1,22),(28,8,22),(29,9,22),(30,10,22),(31,15,22),(32,16,22),(33,17,22),(34,1,23),(35,2,23),(36,10,23),(37,15,23),(38,1,29),(39,7,29),(40,10,29),(41,13,29),(42,16,29);
/*!40000 ALTER TABLE `post_hash_tag` ENABLE KEYS */;
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
