-- MariaDB dump 10.19  Distrib 10.6.4-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: blockchain
-- ------------------------------------------------------
-- Server version	10.6.4-MariaDB-1:10.6.4+maria~focal

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `nft`
--

DROP TABLE IF EXISTS `nft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nft` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `explanation` varchar(255) DEFAULT NULL,
  `image_url` longtext NOT NULL,
  `title` varchar(255) NOT NULL,
  `token_id` bigint(20) NOT NULL,
  `transaction_hash` varchar(255) NOT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKahuvv187jbcewhkx17kkvj8u0` (`owner_id`),
  CONSTRAINT `FKahuvv187jbcewhkx17kkvj8u0` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nft`
--

LOCK TABLES `nft` WRITE;
/*!40000 ALTER TABLE `nft` DISABLE KEYS */;
INSERT INTO `nft` VALUES (1,'2021-10-07 01:48:33.902600','2021-10-07 01:48:33.902600','0','SSAFY로고','https://myimageuploadserver.s3.amazonaws.com/ssafy-logo.png','싸피로고요',27,'0x39521d99cbda94be95bc7a0d4db182870c1be23a5a7d4e2ac58e1ce5cb8c619f',3),(2,'2021-10-07 01:56:16.971776','2021-10-07 01:56:16.971776','0','제 사인입니다.','https://myimageuploadserver.s3.amazonaws.com/Post20210717112919.png','사인',28,'0xef80459cda0fec22993d1305efc3691be51323a4c3f90302b693929264eaae38',3),(3,'2021-10-07 02:20:59.850205','2021-10-07 02:20:59.850205','0','스펀지밥의 따봉','https://myimageuploadserver.s3.amazonaws.com/dsa.gif','따봉',29,'0x99877f551c0c3b0d05cd62334848d4ec800aad8d0d5e0e591d70e4988c83c7d9',3),(4,'2021-10-07 02:32:38.088205','2021-10-07 11:10:36.942510','0','생일축하해','https://myimageuploadserver.s3.amazonaws.com/KakaoTalk_20210729_161814521.jpg','파티영은',30,'0x6ce928a6fc817168af1591498fa09de256915d7d8ef5f160b98e97dc85684116',3),(5,'2021-10-07 02:47:48.508516','2021-10-07 02:47:48.508516','0','해파리채를 들고 춤추는 스펀지밥','https://myimageuploadserver.s3.amazonaws.com/ddd222.gif','dance',31,'0x38378bd0d881f499c42dc6f07725d2c28e14913eaa3c01b1c3351ce509f72e03',3),(6,'2021-10-07 02:49:55.117225','2021-10-07 11:34:50.622047','1','해가 지는 오후의 창문','https://myimageuploadserver.s3.amazonaws.com/Post20210719011442.png','window',32,'0x09a39a63a194e15c49301b8c9c223b14be3eeb3a4e166b81061f33dbcfed3828',3),(7,'2021-10-07 02:51:29.841267','2021-10-07 12:36:37.677519','1','귀여운 고양이','https://myimageuploadserver.s3.amazonaws.com/catcat.jpg','cat',33,'0x2cb495c6db499151b9143a883bbad221762ab46db6213dc93101680fcb30a642',3),(8,'2021-10-07 03:04:25.022768','2021-10-07 03:04:25.022768','0','웅크려 잠자는 고양이','https://myimageuploadserver.s3.amazonaws.com/sleep.jpg','냥모나이트',34,'0xd222c889983f487225cd420040e3d3c73e93ee537020c21d9643b2354925eddc',1),(9,'2021-10-07 03:06:53.152475','2021-10-07 03:06:53.152475','0','엎드려 있는 고양이','https://myimageuploadserver.s3.amazonaws.com/털뭉치.jpg','털뭉치',35,'0x9116da7e83ea138130f0cac433e53e0f6e3170871eba7044ea8b91fceeb9278d',1),(10,'2021-10-07 03:07:29.334505','2021-10-07 03:07:29.334505','0','뚱뚱한 고양이','https://myimageuploadserver.s3.amazonaws.com/뚱냥이.jpg','뚱냥이',36,'0x169f327d3abb1a989c792811492e308f342026f2099a551ef1ea4fdc460fa1e4',1),(11,'2021-10-07 04:17:42.520649','2021-10-07 04:17:42.520649','0','쌍둥이 빌딩 그림입니다.','https://myimageuploadserver.s3.amazonaws.com/건물.jpg','쌍둥이 빌딩',37,'0xb9c6302976fdd839313bf2334fca6993d133c29f3f04141b6c9c87def3b517d7',2),(12,'2021-10-07 04:18:27.253898','2021-10-07 04:18:27.253898','0','광활한 우주','https://myimageuploadserver.s3.amazonaws.com/cosmos.jpg','cosmos',38,'0x06b90f2030fc181d3d0ef4248146315ab3cfb8d1654b37f98b9a6e9f96f50e56',2),(13,'2021-10-07 04:19:05.296528','2021-10-07 04:19:05.296528','0','밤하늘의 별자리','https://myimageuploadserver.s3.amazonaws.com/별자리.jpg','별자리',39,'0xe4379233a78cccbf83cb677c06ea9e894f942097cda29e98c4acab6d42d9592e',2),(14,'2021-10-07 04:22:09.136524','2021-10-07 04:22:09.136524','0','곰돌이와 쥐','https://myimageuploadserver.s3.amazonaws.com/bear.jpg','animal',40,'0x528960bee9870956be6c749e047595098eadf373f897e9ce23de9495c56d576e',5),(15,'2021-10-07 04:22:55.604901','2021-10-07 04:22:55.604901','0','강아지 그림입니다.','https://myimageuploadserver.s3.amazonaws.com/dog.jpg','Dog',41,'0xb3652009799b42254b54bde7cd851c50f4aa21d524417fce7d600709beca0f20',5),(16,'2021-10-07 04:24:13.343438','2021-10-07 04:24:13.343438','0','귀여운 몬스터','https://myimageuploadserver.s3.amazonaws.com/moster.jpg','monster',42,'0xde4b2d02bfb171b367974e0d9f04e29747c3a1361303c8499a69958f742fe968',5),(17,'2021-10-07 04:26:25.746541','2021-10-07 04:26:25.746541','0','소나무 그림입니다.','https://myimageuploadserver.s3.amazonaws.com/tree.jpg','나무',43,'0x61ebb9be257dfb900506195cdfbc60b179bc2e786ba71e87e3841bf94a05ffaa',6),(18,'2021-10-07 04:27:11.227306','2021-10-07 04:27:11.227306','0','장미 그림입니다.','https://myimageuploadserver.s3.amazonaws.com/rose.jpg','꽃',44,'0xd5244094b9407d0cbf05776a5a57a99163a1663423d6c1539ac13488ef3ccde8',6),(19,'2021-10-07 04:27:36.818272','2021-10-07 04:27:36.818272','0','제주도 바다 그림입니다.','https://myimageuploadserver.s3.amazonaws.com/바다.jpg','바다',45,'0x9242d516ea20d62db1dcf9a92411f345db77e4f4b473ed154d86803b43444fcb',6),(20,'2021-10-07 04:28:10.112630','2021-10-07 04:28:10.112630','0','춤추는 사람들','https://myimageuploadserver.s3.amazonaws.com/people.jpg','people',46,'0x5d4a8afebe5058f8e842d69bf29066eba7946fd5c949f79dc79784d3dc78385a',6);
/*!40000 ALTER TABLE `nft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` decimal(18,13) NOT NULL,
  `status` bit(1) NOT NULL,
  `nft_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1sbjvv179anl5esmvi0s02wot` (`nft_id`),
  KEY `FKdb050tk37qryv15hd932626th` (`user_id`),
  CONSTRAINT `FK1sbjvv179anl5esmvi0s02wot` FOREIGN KEY (`nft_id`) REFERENCES `nft` (`id`),
  CONSTRAINT `FKdb050tk37qryv15hd932626th` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,5.3210000000000,'\0',1,3),(2,1.0000000000000,'',7,3),(3,2.0000000000000,'',5,3),(4,2.3000000000000,'',20,6),(5,0.6000000000000,'',18,6),(6,1.2000000000000,'',15,5),(7,2.0000000000000,'',14,5),(8,10.0000000000000,'',12,2),(9,7.0000000000000,'',11,2);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `lately_time` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2021-10-07 01:37:48.945000','lim8663@gmail.com',NULL,'임영찬','lim123!'),(2,'2021-10-07 01:38:56.083000','dawit0310@gmail.com',NULL,'dawit','9eTrr55CHuGTrn3!'),(3,'2021-10-07 01:40:17.781000','yeongeun@naver.com',NULL,'윤영은','yeongeun0109!'),(4,'2021-10-07 01:42:13.943000','wooki@naver.com',NULL,'우기','ssafy407!'),(5,'2021-10-07 01:45:54.677000','wkjung0329@gmail.com',NULL,'정우기','dnrl407!'),(6,'2021-10-07 01:47:05.379000','noyoon@gmail.com',NULL,'고노윤','noyoon123!'),(7,'2021-10-07 16:00:44.860000','test@naver.com',NULL,'빌런','qlffjs@2'),(8,'2021-10-07 16:02:32.161000','Z@Z.COM',NULL,'mon','@ssafy7');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-07 16:50:42
