-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: periodicals
-- ------------------------------------------------------
-- Server version	8.0.13

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
-- Table structure for table `internationalization`
--

DROP TABLE IF EXISTS `internationalization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `internationalization` (
  `idLocal` int(11) NOT NULL AUTO_INCREMENT,
  `nameLocal` varchar(10) NOT NULL,
  PRIMARY KEY (`idLocal`),
  UNIQUE KEY `idLocal` (`idLocal`),
  UNIQUE KEY `nameLocal` (`nameLocal`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `internationalization`
--

LOCK TABLES `internationalization` WRITE;
/*!40000 ALTER TABLE `internationalization` DISABLE KEYS */;
INSERT INTO `internationalization` VALUES (2,'en_EN'),(1,'ru_RU');
/*!40000 ALTER TABLE `internationalization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `payments` (
  `idPayment` int(11) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL DEFAULT '0',
  `payStatus` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idPayment`),
  UNIQUE KEY `idPayment` (`idPayment`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodical_theme`
--

DROP TABLE IF EXISTS `periodical_theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `periodical_theme` (
  `idTheme` int(11) NOT NULL AUTO_INCREMENT,
  `nameTheme` varchar(100) NOT NULL,
  PRIMARY KEY (`idTheme`),
  UNIQUE KEY `idTheme_UNIQUE` (`idTheme`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodical_theme`
--

LOCK TABLES `periodical_theme` WRITE;
/*!40000 ALTER TABLE `periodical_theme` DISABLE KEYS */;
INSERT INTO `periodical_theme` VALUES (1,'Государство и право'),(2,'Бизнес. Предпринимательство. Рынок'),(3,'Экономика и финансы'),(4,'Общественно-политические издания'),(5,'Литературно-художественные издания'),(6,'Образование и педагогика'),(7,'Здравоохранение и медицина'),(8,'Детские и молодежные издания'),(9,'Научные и научно-популярные издания'),(10,'Издания универсального содержания'),(11,'Фантастика. Детектив'),(12,'Рекламные издания'),(13,'Издания для женщин'),(14,'Издания для мужчин'),(15,'Приусадебное и сельское хозяйство'),(16,'Лесное хозяйство и экология'),(17,'Компьютерные издания. Телевидение. Радио. Связь'),(18,'Техника, промышленность, строительство'),(19,'Автомобили, транспорт'),(20,'Культура, искусство'),(21,'Спорт, путешествия, туризм'),(22,'Досуг и телепрограммы'),(23,'Вооруженные силы и правоохранительные органы'),(24,'Социальная защита населения. Издания для инвалидов. Охрана труда. Кадровая служба'),(25,'Религиозные издания');
/*!40000 ALTER TABLE `periodical_theme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodical_type`
--

DROP TABLE IF EXISTS `periodical_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `periodical_type` (
  `idType` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(50) NOT NULL,
  PRIMARY KEY (`idType`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodical_type`
--

LOCK TABLES `periodical_type` WRITE;
/*!40000 ALTER TABLE `periodical_type` DISABLE KEYS */;
INSERT INTO `periodical_type` VALUES (1,'газета'),(2,'журнал');
/*!40000 ALTER TABLE `periodical_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodicals`
--

DROP TABLE IF EXISTS `periodicals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `periodicals` (
  `idPeriodical` int(11) NOT NULL AUTO_INCREMENT,
  `idType` int(11) NOT NULL,
  `idTheme` int(11) NOT NULL,
  `namePeriodical` varchar(50) NOT NULL,
  `periodicityInMonth` int(11) NOT NULL,
  `annotation` varchar(200) DEFAULT NULL,
  `imagePath` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idPeriodical`),
  UNIQUE KEY `idPeriodical` (`idPeriodical`),
  KEY `id_btfk_1_idx` (`idType`),
  KEY `id_btfk_2_idx` (`idTheme`),
  CONSTRAINT `id_btfk_1` FOREIGN KEY (`idType`) REFERENCES `periodical_type` (`idtype`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `id_btfk_2` FOREIGN KEY (`idTheme`) REFERENCES `periodical_theme` (`idtheme`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodicals`
--

LOCK TABLES `periodicals` WRITE;
/*!40000 ALTER TABLE `periodicals` DISABLE KEYS */;
/*!40000 ALTER TABLE `periodicals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscription_types`
--

DROP TABLE IF EXISTS `subscription_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `subscription_types` (
  `idSubscriptionType` int(11) NOT NULL AUTO_INCREMENT,
  `nameSubscriptionType` varchar(50) NOT NULL,
  `monthAmount` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idSubscriptionType`),
  UNIQUE KEY `idSubscriptionType` (`idSubscriptionType`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription_types`
--

LOCK TABLES `subscription_types` WRITE;
/*!40000 ALTER TABLE `subscription_types` DISABLE KEYS */;
INSERT INTO `subscription_types` VALUES (1,'месячная',1),(2,'полугодовая',6),(3,'квартальная',3),(4,'ведомственная месячная',1),(5,'ведомственная полугодовая',6),(6,'ведомственная квартальная',3),(7,'льготная месячная',1),(8,'льготная полугодовая',6),(9,'льготная квартальная',3);
/*!40000 ALTER TABLE `subscription_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscription_variants`
--

DROP TABLE IF EXISTS `subscription_variants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `subscription_variants` (
  `idSubscriptionVariant` int(11) NOT NULL,
  `indexSubscription` varchar(10) NOT NULL,
  `idPeriodical` int(11) NOT NULL,
  `idSubscriptionType` int(11) NOT NULL,
  `cost` double NOT NULL,
  PRIMARY KEY (`idSubscriptionVariant`,`idPeriodical`,`idSubscriptionType`),
  UNIQUE KEY `idSubscriptionVariant` (`idSubscriptionVariant`),
  UNIQUE KEY `indexSubscription` (`indexSubscription`),
  KEY `idSubscriptionType` (`idSubscriptionType`),
  KEY `idPeriodical` (`idPeriodical`),
  CONSTRAINT `subscription_variants_ibfk_1` FOREIGN KEY (`idSubscriptionType`) REFERENCES `subscription_types` (`idsubscriptiontype`),
  CONSTRAINT `subscription_variants_ibfk_2` FOREIGN KEY (`idPeriodical`) REFERENCES `periodicals` (`idperiodical`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription_variants`
--

LOCK TABLES `subscription_variants` WRITE;
/*!40000 ALTER TABLE `subscription_variants` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscription_variants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_addresses`
--

DROP TABLE IF EXISTS `user_addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_addresses` (
  `idAddress` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(100) NOT NULL,
  `idUser` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAddress`),
  UNIQUE KEY `idAddress` (`idAddress`),
  KEY `idUser` (`idUser`),
  CONSTRAINT `user_addresses_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_addresses`
--

LOCK TABLES `user_addresses` WRITE;
/*!40000 ALTER TABLE `user_addresses` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_roles` (
  `idRole` int(11) NOT NULL AUTO_INCREMENT,
  `nameRole` varchar(50) NOT NULL,
  PRIMARY KEY (`idRole`),
  UNIQUE KEY `idRole` (`idRole`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,'администратор'),(2,'подписчик');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_subscriptions`
--

DROP TABLE IF EXISTS `user_subscriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_subscriptions` (
  `idUserSubscription` int(11) NOT NULL AUTO_INCREMENT,
  `idAddress` int(11) NOT NULL,
  `idSubscriptionVariant` int(11) NOT NULL,
  `dateBegin` date NOT NULL,
  `dateEnd` date NOT NULL,
  `idPayment` int(11) NOT NULL,
  PRIMARY KEY (`idUserSubscription`),
  UNIQUE KEY `idUserSubscription` (`idUserSubscription`),
  KEY `idPayment` (`idPayment`),
  KEY `idAddress` (`idAddress`),
  KEY `idSubscriptionVariant` (`idSubscriptionVariant`),
  CONSTRAINT `user_subscriptions_ibfk_1` FOREIGN KEY (`idPayment`) REFERENCES `payments` (`idpayment`),
  CONSTRAINT `user_subscriptions_ibfk_2` FOREIGN KEY (`idAddress`) REFERENCES `user_addresses` (`idaddress`),
  CONSTRAINT `user_subscriptions_ibfk_3` FOREIGN KEY (`idSubscriptionVariant`) REFERENCES `subscription_variants` (`idsubscriptionvariant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_subscriptions`
--

LOCK TABLES `user_subscriptions` WRITE;
/*!40000 ALTER TABLE `user_subscriptions` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_subscriptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password` varchar(200) NOT NULL,
  `idRole` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `idDefaultLocal` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `idUser` (`idUser`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `email` (`email`),
  KEY `idRole` (`idRole`),
  KEY `idDefaultLocal` (`idDefaultLocal`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`idRole`) REFERENCES `user_roles` (`idrole`),
  CONSTRAINT `users_ibfk_2` FOREIGN KEY (`idDefaultLocal`) REFERENCES `internationalization` (`idlocal`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'tvorecz','$2a$10$llw0G6IyibUob8h5XRt9xugk8RFbeWj//0RL5vjeipwHgsVBdsSUS',2,'tvorecz@yandex.ru',2),(2,'dm.zrch','$2a$10$llw0G6IyibUob8h5XRt9xugk8RFbeWj//0RL5vjeipwHgsVBdsSUS',2,'tvorecz@gmail.com',1),(3,'avril','$2a$10$llw0G6IyibUob8h5XRt9xurib1dJVaS3zuUieP9wiPXRmMEwWYnGu',2,'avril@yahoo.com',2),(4,'tata.ta','$2a$10$llw0G6IyibUob8h5XRt9xumehBPNWzGcZH.svv/0Y90EdA2tGTXuC',2,'tata.ta@mail.ru',1),(5,'tamata','$2a$10$llw0G6IyibUob8h5XRt9xumehBPNWzGcZH.svv/0Y90EdA2tGTXuC',2,'gra@tut.by',1),(6,'igor1234','$2a$10$llw0G6IyibUob8h5XRt9xugk8RFbeWj//0RL5vjeipwHgsVBdsSUS',2,'igor@ya.ru',1),(7,'tanya','$2a$10$llw0G6IyibUob8h5XRt9xugk8RFbeWj//0RL5vjeipwHgsVBdsSUS',2,'tanya@ya.by',2),(8,'maria','$2a$10$llw0G6IyibUob8h5XRt9xumllN4V5noGJIF35abIvWwBmfS0taSga',2,'maria@tut.by',2),(9,'yyr','$2a$10$llw0G6IyibUob8h5XRt9xuwW2mkUoMymkeI4f5/eLA4bGiXkTfAJu',2,'yyr@ya.ru',1),(10,'logger','$2a$10$llw0G6IyibUob8h5XRt9xugk8RFbeWj//0RL5vjeipwHgsVBdsSUS',2,'logger@ya.ru',1),(11,'mama','$2a$10$llw0G6IyibUob8h5XRt9xugk8RFbeWj//0RL5vjeipwHgsVBdsSUS',2,'mama@tut.by',1);
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

-- Dump completed on 2018-12-27  1:57:55
