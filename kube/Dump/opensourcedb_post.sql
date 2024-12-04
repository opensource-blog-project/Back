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
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `post_id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `restaurant` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`post_id`),
  KEY `FK72mt33dhhs48hf9gcqrq4fxte` (`user_id`),
  CONSTRAINT `FK72mt33dhhs48hf9gcqrq4fxte` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (2,'진짜 맛있어용','감성타코 판교점','판교 감성타코 추천','hyunj'),(14,'고기가 맛있어요!','홍대장소고기집','소고기 맛집 홍대장','yellow02'),(15,'너무 시끄럽지 않고 규모가 적당히 작아서 소모임 하기 좋아요!\n무엇보다 떡볶이가 진짜 맛있어요 ㅠㅠㅠㅠㅠ','군반장 상현역점','상현역 근처 군반장 추천합니다!!','yellow02'),(17,'마라크림파스타 지이인짜 맛있어요 살짝 맵고 짜긴 한데 또 생각나서 얼마 안 가 또 갔어요... 진짜 맛있고 분위기도 좋아요!','노체부엌 퓨전 펍','행궁동 분위기 좋은 술집&안주 추천','hyunj'),(18,'아는 사람은 다 아는 홍대 유명한 곱창집입니다 내부가 넓지 않아 주말엔 웨이팅이 항상 있어요 ㅠ 근데 진짜 맛있어요!','대승곱창 홍대점','홍대 웨이팅해서 먹는 곱창집 대승곱창','hyunj'),(20,'유명 연예인이 추천해줬다해서 먹어봤는데 진짜 맛있어요!!','황도 바지락칼국수','찐로컬맛집 황도 바칼','hyunj'),(21,'워낙 유명해서 많이들 아실 것 같은 홍대 라헬의 부엌 추천합니다!! 딸기 수플레 진짜진짜 맛있곤 내부도 넓고 너무 예뻐요♡','라헬의 부엌 홍대점','홍대 딸기수플레 찐맛집','straw12'),(22,'체인점이구용 모르시는 분들 많더라구요 미국식 덮밥 파는데 메뉴 하나하나 다 맛있어요 사진은 폴드포크덮밥?인데 기대 안 했는데 진짜 맛있습니다 추천! 그리고 배달 위주로 많이 하더라구요','버텍스 수지구청점','미국식 덮밥 드시고 싶은 분!!','straw12'),(23,'일반 횟집처럼 대자나 중자로 시키는 게 아니라 메뉴별로 6ps, 12ps 시킬 수 있어서 좋았어요! 맛도 좋았어요~ 2층도 있어서 자리도 넓습니당','탐라육해 홍대점','가성비 좋은 술집 겸 횟집!','hanhan'),(25,'뇨끼가 진짜 맛있어요!','운멜로키친 3호점','행궁동 맛집 운멜로키친','nemo1222'),(29,'입구부터 특이하고 칵테일바라서 그런지 분위기가 너무 좋아요! 밤에 가면 특히 좋아요!','성지술례 모란점','분위기 좋은 칵테일집 성지술례 추천','straw12');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
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
