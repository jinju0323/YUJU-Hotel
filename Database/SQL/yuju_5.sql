-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: yuju
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `breakfasts`
--

DROP TABLE IF EXISTS `breakfasts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `breakfasts` (
  `breakfast_id` int NOT NULL AUTO_INCREMENT COMMENT '조식ID',
  `breakfast_price` int NOT NULL COMMENT '조식가격',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
  `edit_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '변경일자',
  PRIMARY KEY (`breakfast_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='조식';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `breakfasts`
--

LOCK TABLES `breakfasts` WRITE;
/*!40000 ALTER TABLE `breakfasts` DISABLE KEYS */;
/*!40000 ALTER TABLE `breakfasts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inquiries`
--

DROP TABLE IF EXISTS `inquiries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inquiries` (
  `inquiry_id` int NOT NULL AUTO_INCREMENT COMMENT '문의ID',
  `member_id` int DEFAULT NULL COMMENT '회원ID',
  `subject` varchar(255) NOT NULL COMMENT '제목',
  `message` text NOT NULL COMMENT '내용',
  `status` enum('PENDING','ANSWERED') DEFAULT 'PENDING' COMMENT '상태',
  `reply` text COMMENT '답변',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
  `edit_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '변경일자',
  PRIMARY KEY (`inquiry_id`),
  KEY `inquiries_ibfk_1` (`member_id`),
  CONSTRAINT `inquiries_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='문의';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inquiries`
--

LOCK TABLES `inquiries` WRITE;
/*!40000 ALTER TABLE `inquiries` DISABLE KEYS */;
/*!40000 ALTER TABLE `inquiries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inquiry_files`
--

DROP TABLE IF EXISTS `inquiry_files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inquiry_files` (
  `file_id` int NOT NULL AUTO_INCREMENT COMMENT '문의파일ID',
  `inquiry_id` int NOT NULL COMMENT '문의ID',
  `file_name` varchar(255) NOT NULL COMMENT '파일명',
  `file_path` varchar(512) NOT NULL COMMENT '파일경로',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
  `edit_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '변경일자',
  PRIMARY KEY (`file_id`),
  KEY `inquiry_id` (`inquiry_id`),
  CONSTRAINT `inquiry_files_ibfk_1` FOREIGN KEY (`inquiry_id`) REFERENCES `inquiries` (`inquiry_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='문의파일';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inquiry_files`
--

LOCK TABLES `inquiry_files` WRITE;
/*!40000 ALTER TABLE `inquiry_files` DISABLE KEYS */;
/*!40000 ALTER TABLE `inquiry_files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `member_id` int NOT NULL AUTO_INCREMENT COMMENT '회원ID',
  `user_name` varchar(50) NOT NULL COMMENT '이름',
  `user_id` varchar(100) NOT NULL COMMENT '아이디',
  `user_pw` varchar(255) NOT NULL COMMENT '비밀번호',
  `admin` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '관리자 여부',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록 일자',
  `edit_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 일자',
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (1,'박지민 (수정)','yujin.kim@test.com','updatedPass!456','N','2025-02-17 14:17:09','2025-02-17 14:46:57'),(3,'박지민','jimin.park@test.com','testPass!123','N','2025-02-17 14:46:43','2025-02-17 14:46:43');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notices`
--

DROP TABLE IF EXISTS `notices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notices` (
  `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '공지사항ID',
  `member_id` int NOT NULL COMMENT '회원ID',
  `title` varchar(255) NOT NULL COMMENT '제목',
  `content` text NOT NULL COMMENT '내용',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `edit_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`notice_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `notices_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='공지사항';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notices`
--

LOCK TABLES `notices` WRITE;
/*!40000 ALTER TABLE `notices` DISABLE KEYS */;
/*!40000 ALTER TABLE `notices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payments` (
  `payment_id` int NOT NULL AUTO_INCREMENT COMMENT '결제ID',
  `reservation_id` int NOT NULL COMMENT '예약ID',
  `member_id` int NOT NULL COMMENT '회원ID',
  `amount` int NOT NULL COMMENT '결제금액',
  `status` enum('PENDING','COMPLETED','FAILED') DEFAULT 'PENDING' COMMENT '결제상태',
  `payment_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `reg_date` datetime NOT NULL COMMENT '등록일자',
  `edit_date` datetime NOT NULL COMMENT '변경일자',
  PRIMARY KEY (`payment_id`),
  KEY `payments_ibfk_1` (`reservation_id`),
  KEY `payments_ibfk_2` (`member_id`),
  CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`reservation_id`) ON DELETE CASCADE,
  CONSTRAINT `payments_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='결제';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
  `reservation_id` int NOT NULL AUTO_INCREMENT COMMENT '예약ID',
  `member_id` int NOT NULL COMMENT '회원ID',
  `room_id` int NOT NULL COMMENT '객실ID',
  `check_in_date` date NOT NULL COMMENT '체크인',
  `check_out_date` date NOT NULL COMMENT '체크아웃',
  `total_price` int NOT NULL COMMENT '총금액',
  `status` enum('PENDING','CONFIRMED','CANCELLED') DEFAULT 'PENDING' COMMENT '예약상태',
  `reserved_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '예약일자',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
  `edit_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '변경일자',
  PRIMARY KEY (`reservation_id`),
  KEY `reservations_ibfk_1` (`member_id`),
  KEY `reservations_ibfk_2` (`room_id`),
  CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`) ON DELETE CASCADE,
  CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='예약';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_imgs`
--

DROP TABLE IF EXISTS `room_imgs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_imgs` (
  `room_img_id` int NOT NULL AUTO_INCREMENT COMMENT '이미지ID',
  `room_id` int NOT NULL COMMENT '객실ID',
  `img_url` varchar(255) NOT NULL COMMENT '사진경로',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
  `edit_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '변경일자',
  PRIMARY KEY (`room_img_id`),
  KEY `fk_room_imgs_rooms1_idx` (`room_id`),
  CONSTRAINT `fk_room_imgs_rooms1` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='객실 사진';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_imgs`
--

LOCK TABLES `room_imgs` WRITE;
/*!40000 ALTER TABLE `room_imgs` DISABLE KEYS */;
INSERT INTO `room_imgs` VALUES (1,1,'/rooms/stand/stand1.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(2,1,'/rooms/stand/stand2.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(3,1,'/rooms/stand/stand3.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(4,1,'/rooms/stand/stand4.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(5,1,'/rooms/stand/closet1.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(6,1,'/rooms/oceanview.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(7,2,'/rooms/stand/stand1.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(8,2,'/rooms/stand/stand2.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(9,2,'/rooms/stand/stand3.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(10,2,'/rooms/stand/stand4.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(11,2,'/rooms/stand/closet1.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(12,2,'/rooms/oceanview.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(13,3,'/rooms/stand/stand1.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(14,3,'/rooms/stand/stand2.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(15,3,'/rooms/stand/stand3.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(16,3,'/rooms/stand/stand4.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(17,3,'/rooms/stand/closet1.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(18,3,'/rooms/cityview.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(19,4,'/rooms/deluxe/deluxe1.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(20,4,'/rooms/deluxe/deluxe2.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(21,4,'/rooms/deluxe/deluxe3.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(22,4,'/rooms/deluxe/deluxe4.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(23,4,'/rooms/deluxe/closet2.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(24,4,'/rooms/oceanview.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(25,5,'/rooms/deluxe/deluxe1.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(26,5,'/rooms/deluxe/deluxe2.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(27,5,'/rooms/deluxe/deluxe3.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(28,5,'/rooms/deluxe/deluxe4.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(29,5,'/rooms/deluxe/closet2.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(30,5,'/rooms/oceanview.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(31,4,'/rooms/deluxe/deluxe1.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(32,4,'/rooms/deluxe/deluxe2.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(33,4,'/rooms/deluxe/deluxe3.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(34,4,'/rooms/deluxe/deluxe4.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(35,4,'/rooms/deluxe/closet2.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(36,4,'/rooms/cityview.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(37,7,'/rooms/suite/suite1.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(38,7,'/rooms/suite/suite2.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(39,7,'/rooms/suite/suite3.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(40,7,'/rooms/suite/suite4.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(41,7,'/rooms/suite/suite5.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(42,7,'/rooms/suite/closet2.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(43,7,'/rooms/oceanview.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(44,8,'/rooms/suite/suite1.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(45,8,'/rooms/suite/suite2.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(46,8,'/rooms/suite/suite3.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(47,8,'/rooms/suite/suite4.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(48,8,'/rooms/suite/suite5.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(49,8,'/rooms/suite/closet2.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(50,8,'/rooms/oceanview.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(51,9,'/rooms/suite/suite1.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(52,9,'/rooms/suite/suite2.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(53,9,'/rooms/suite/suite3.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(54,9,'/rooms/suite/suite4.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(55,9,'/rooms/suite/suite5.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(56,9,'/rooms/suite/closet2.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38'),(57,9,'/rooms/cityview.jpg','2025-02-15 19:50:38','2025-02-15 19:50:38');
/*!40000 ALTER TABLE `room_imgs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `room_id` int NOT NULL AUTO_INCREMENT COMMENT '객실ID',
  `room_type` enum('Standard','Deluxe','Suite') NOT NULL COMMENT '객실유형',
  `room_category` enum('A','B','C') NOT NULL COMMENT '객실등급',
  `price_per_night` int NOT NULL COMMENT '객실요금',
  `capacity` int NOT NULL COMMENT ' 최대수용인원',
  `description` text COMMENT '설명',
  `is_available` tinyint(1) DEFAULT '1' COMMENT '이용가능여부',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
  `edit_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '변경일자',
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='객실';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,'Standard','A',150000,2,'오션뷰 스탠다드룸',1,'2025-02-15 18:59:59','2025-02-17 15:41:04'),(2,'Standard','B',150000,2,'오션뷰 스탠다드룸',1,'2025-02-15 18:59:59','2025-02-17 15:41:04'),(3,'Standard','C',150000,2,'시티뷰 스탠다드룸',1,'2025-02-15 18:59:59','2025-02-17 15:41:04'),(4,'Deluxe','A',230000,3,'오션뷰 디럭스룸',1,'2025-02-15 18:59:59','2025-02-17 15:41:04'),(5,'Deluxe','B',230000,3,'오션뷰 디럭스룸',1,'2025-02-15 18:59:59','2025-02-17 15:41:04'),(6,'Deluxe','C',230000,3,'시티뷰 디럭스룸',1,'2025-02-15 18:59:59','2025-02-17 15:41:04'),(7,'Suite','A',370000,4,'오션뷰 스위트룸',1,'2025-02-15 18:59:59','2025-02-17 15:41:04'),(8,'Suite','B',370000,4,'오션뷰 스위트룸',1,'2025-02-15 18:59:59','2025-02-17 15:41:04'),(9,'Suite','C',370000,4,'시티뷰 스위트룸',1,'2025-02-15 18:59:59','2025-02-15 18:59:59');
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session`
--

DROP TABLE IF EXISTS `spring_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL COMMENT '세션 고유 식별자',
  `SESSION_ID` char(36) NOT NULL COMMENT '세션 ID',
  `CREATION_TIME` bigint NOT NULL COMMENT '세션 생성 시간',
  `LAST_ACCESS_TIME` bigint NOT NULL COMMENT '마지막 접근 시간',
  `MAX_INACTIVE_INTERVAL` int NOT NULL COMMENT '세션 비활성화 허용시간',
  `EXPIRY_TIME` bigint NOT NULL COMMENT '세션 만료시간',
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL COMMENT '세션과 연결된 사용자명',
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='스프링 세션';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session`
--

LOCK TABLES `spring_session` WRITE;
/*!40000 ALTER TABLE `spring_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `spring_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session_attributes`
--

DROP TABLE IF EXISTS `spring_session_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL COMMENT '세션 고유 식별자',
  `ATTRIBUTE_NAME` varchar(200) NOT NULL COMMENT '세션 속성명(키)',
  `ATTRIBUTE_BYTES` blob NOT NULL COMMENT '직렬화된 세션 데이터',
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='스프링 세션 속성';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session_attributes`
--

LOCK TABLES `spring_session_attributes` WRITE;
/*!40000 ALTER TABLE `spring_session_attributes` DISABLE KEYS */;
/*!40000 ALTER TABLE `spring_session_attributes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-17 15:48:22
