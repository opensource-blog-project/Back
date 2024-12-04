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
-- Table structure for table `hash_tag`
--

DROP TABLE IF EXISTS `hash_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hash_tag` (
  `hashtag_id` bigint NOT NULL AUTO_INCREMENT,
  `hash_tag_type` enum('AGE','CATEGORY','REGION','UTILITY','VISIT_PURPOSE') NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`hashtag_id`),
  UNIQUE KEY `UKlaxxca1qa4eoagsk8dtiaun4e` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hash_tag`
--

LOCK TABLES `hash_tag` WRITE;
/*!40000 ALTER TABLE `hash_tag` DISABLE KEYS */;
INSERT INTO `hash_tag` VALUES (1,'AGE','10대-20대'),(2,'AGE','30대-40대'),(3,'AGE','50대-60대'),(4,'CATEGORY','한식'),(5,'CATEGORY','중식'),(6,'CATEGORY','일식'),(7,'CATEGORY','카페'),(8,'VISIT_PURPOSE','아침'),(9,'VISIT_PURPOSE','점심'),(10,'VISIT_PURPOSE','저녁'),(11,'VISIT_PURPOSE','여행'),(12,'VISIT_PURPOSE','가족외식'),(13,'UTILITY','주차'),(14,'UTILITY','24시간영업'),(15,'REGION','서울'),(16,'REGION','경기'),(17,'REGION','인천');
/*!40000 ALTER TABLE `hash_tag` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-04 12:17:29
