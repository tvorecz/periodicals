CREATE DATABASE  IF NOT EXISTS `periodicals` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `periodicals`;
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
  `payStatus` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idPayment`),
  UNIQUE KEY `idPayment` (`idPayment`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES (7,11.86,0),(8,1.9,0),(9,8.81,0),(10,2.4,0),(11,1.9,0),(12,1.2,0),(13,3.6,0),(14,6.51,0),(15,141.4,0),(16,4.3,0),(17,1.2,0),(18,7.77,0),(19,2.3,0),(20,13.8,0);
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
  `annotation` varchar(1000) DEFAULT NULL,
  `imagePath` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idPeriodical`),
  UNIQUE KEY `idPeriodical` (`idPeriodical`),
  KEY `id_btfk_1_idx` (`idType`),
  KEY `id_btfk_2_idx` (`idTheme`),
  CONSTRAINT `id_btfk_1` FOREIGN KEY (`idType`) REFERENCES `periodical_type` (`idtype`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `id_btfk_2` FOREIGN KEY (`idTheme`) REFERENCES `periodical_theme` (`idtheme`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodicals`
--

LOCK TABLES `periodicals` WRITE;
/*!40000 ALTER TABLE `periodicals` DISABLE KEYS */;
INSERT INTO `periodicals` VALUES (14,2,8,'Девчонки',2,'Первая любовь и крепкая дружба, красота и мода, звезды и увлечения, истории и школьная жизнь, гадания и тесты, полезные советы и ответы на волнующие вопросы — все это и многое другое в журнале «Девчонки»!','/images/devchonki3.jpg'),(15,2,14,'Ну, за рыбалку!',1,'Настоящий мужской журнал. Рыбалка и зимой и летом. Советы любителей и профессионалов.','/images/fish1.jpg'),(16,2,13,'Семья',1,'Журнал о самом главном «Семья» – это тепло домашнего очага. Это детский смех, ласковые мамины объятия и надежное плечо отца. Это бабушки и дедушки, чья доброта и мудрость не имеют границ.','/images/fam21.jpg'),(17,1,13,'Доживём до ста',2,'«Доживём до ста» – ежемесячная газета для тех, кто мечтает о долгой жизни и крепком здоровье.','/images/do_sta2_1.jpg'),(18,2,22,'Три в одном',2,'Новый журнал «Три в одном» — ежемесячное издание для тех, кто хочет приятно провести время и узнать что-то новое. В каждом номере журнала – отборный юмор, множество сканвордов, лучшие кулинарные рецепты.','/images/tri_v_odnom2_1.jpg'),(19,1,13,'Экономим вместе',2,'Газета «Экономим вместе» – копилка простых и нужных советов на все случаи жизни. Незаменимое издание всем тем, кто бережет свои деньги и время. Оно будет интересна тем, кто хочет научиться тонкостям и хитростям домоводства, цветоводства, кулинарии, приусадебного хозяйства.  С нашей помощью вы узнаете, как создавать интересные вещи из ненужных предметов, готовить быстрые и вкусные блюда, наводить чистоту в доме за считанные минуты.  А еще в каждом номере газеты – эффективные рецепты народной медицины, секреты красоты и долголетия, правила здорового питания.  Все это и многое другое – только на страницах ежемесячной газеты «Экономим вместе».','/images/econom2_1.jpg'),(20,1,22,'Ложка',2,'Газета «Ложка» – это подборка простых кулинарных рецептов на все случаи!  Из газеты вы узнаете, как недорого и вкусно накормить семью, чем порадовать детей и встретить праздник, как приготовить полезные блюда и сэкономить на продуктах. А также получите возможность перенять чужой кулинарный опыт и поделиться своим!','/images/lojka02-01_c_NEW.jpg'),(21,2,8,'Школа модниц',1,'«Школа модниц» — это ежемесячный развлекательный журнал для юных красавиц и модниц. Вместе с нами девочки смогут пофантазировать и придумать стильный наряд, сотворить блестящий макияж, нарисовать сногсшибательный маникюр, а также сделать прикольные аксессуары и открытки.','/images/shkola2.jpg'),(22,2,8,'Раскраски народные сказки',2,'Наше издательство представляет еще одну новинку для юных художников – журнал «Раскраски народные сказки». В каждом номере добрая, умная, поучительная русская народная сказка с картинками, которые ваш малыш сможет раскрасить по своему усмотрению.','/images/skazki2.jpg'),(23,1,13,'Мудрые советы',2,'Век живи – век учись! Домашнее хозяйство и ремонт, рецепты и дача, здоровье и красота, мужской подход и женская смекалка – делимся опытом в газете «Мудрые советы»! Издание основано на вопросах и письмах читателей, а также советах экспертов.','/images/mc3_1.jpg'),(24,2,22,'Вулкан сканвордов',1,'Представляем новинку в мире сканвордно-кроссвордных изданий – ежемесячный развлекательный журнал «Вулкан сканвордов». На 52-х страницах издания собраны сканворды большие и маленькие, судоку, цифровые сканворды, цикловорды, чайнворды, анаграммы, а также другие головоломки. С «Вулканом сканвордов» вам не придется скучать!','/images/vul2_1.jpg'),(25,1,8,'Переходный возраст',4,'\"Переходный возраст\" — еженедельная газета для подростков, специализированное государственное издание. ','/images/E8TCDV4luVU.jpg'),(26,2,14,'Esquire',1,'Ежемесячный мужской журнал, основанный в 1933 году в США. Основные темы: культура и искусство, мода и стиль, бизнес и политика, технологии и автомобили, еда и здоровье, персонажи и интервью','/images/esquire.jpg'),(27,2,14,'Men\'s Health',1,'Его читают в 35 странах, а российское издание уже больше 20 лет обращается к активным и успешным мужчинам, которым важно физическое, профессиональное, ментальное и эмоциональное здоровье. Журнал рассказывает о том, как сделать жизнь современного мужчины лучше, охватывая темы от здоровья и питания до моды и технологий.','/images/health.jpg'),(28,2,20,'Искусство кино',1,'«Искусство кино» — старейший в Европе журнал о кинематографе, издается«Искусство кино» — старейший в Европе журнал о кинематографе, издается с января 1931 года.  В каждом номере публикуется около 50 больших текстов: обзоры крупнейших фестивалей, интервью с главными кинодеятелями мира, анализ кинематографических трендов прошлого, настоящего и будущего, сценарии, кинопроза и многое другое. «Искусство кино» — отечественное киноиздание, уважаемое во всем мире, и один из немногих «толстых журналов», который не только не прекратил существование, но и продолжает активно развиваться. В частности, не так давно начала свою деятельность Синематека ИК - цикл кинопоказов и сопровождающих лекций, призванных вернуть любителей авторского кинематографа к просмотру в кинозале и зрительским дискуссиям.','/images/кино.jpeg'),(33,2,22,'Семь дней ТВ-программа',4,'«Семь дней ТВ-программа» – развлекательный иллюстрированный журнал для семейного чтения, содержащий аннотированную телепрограмму.','/images/7days.jpg'),(34,1,17,'Всякая всячина',22,'4234','/images/PaymentDAO.png'),(36,2,22,'TAXI',1,'TAXI универсальный журнал удобного формата для современных мужчин и женщин. Полезные статьи и профессиональные мастер классы, интересные интервью и свежие новости – все это можно прочитать на страницах нашего журнала.','/images/taxi.jpg');
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
  `idSubscriptionVariant` int(11) NOT NULL AUTO_INCREMENT,
  `indexSubscription` varchar(10) NOT NULL,
  `idPeriodical` int(11) NOT NULL,
  `idSubscriptionType` int(11) NOT NULL,
  `costForIssue` double NOT NULL,
  PRIMARY KEY (`idSubscriptionVariant`),
  UNIQUE KEY `idSubscriptionVariant` (`idSubscriptionVariant`),
  KEY `id_btfk_1_idx` (`idSubscriptionType`),
  KEY `2fk` (`idPeriodical`),
  CONSTRAINT `1fk` FOREIGN KEY (`idSubscriptionType`) REFERENCES `subscription_types` (`idsubscriptiontype`),
  CONSTRAINT `2fk` FOREIGN KEY (`idPeriodical`) REFERENCES `periodicals` (`idperiodical`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription_variants`
--

LOCK TABLES `subscription_variants` WRITE;
/*!40000 ALTER TABLE `subscription_variants` DISABLE KEYS */;
INSERT INTO `subscription_variants` VALUES (7,'00857',14,1,0.95),(8,'00857',14,2,0.95),(9,'00857',14,3,0.9),(10,'01379',15,1,0.96),(11,'01379',15,2,0.96),(12,'01379',15,3,0.9),(13,'013792',15,4,1.51),(14,'013792',15,5,1.51),(15,'013792',15,6,1.4),(16,'12312',16,1,1.4),(17,'12312',16,2,1.4),(18,'12312',16,3,1.4),(19,'12312',16,8,0.9),(20,'089012',17,1,0.6),(21,'089012',17,2,0.55),(22,'02321',18,1,0.9),(23,'02321',18,2,0.9),(24,'02321',18,3,0.9),(25,'09981',19,1,0.45),(26,'09981',19,2,0.45),(27,'09981',19,3,0.45),(28,'09982',19,9,0.4),(29,'09234',20,1,0.3),(30,'09234',20,2,0.3),(31,'09234',20,3,0.3),(32,'02371',21,1,0.95),(33,'02373',21,2,0.85),(34,'02372',21,3,0.9),(35,'89001',22,1,1.15),(36,'89003',22,2,1.05),(37,'89002',22,3,1.15),(38,'13909',23,1,0.7),(39,'13909',23,2,0.7),(40,'13909',23,3,0.7),(41,'23091',24,1,1.11),(42,'23091',24,2,1.11),(43,'23091',24,3,1.11),(44,' 626',25,1,1.3),(45,' 626',25,2,1.3),(46,' 626',25,3,1.3),(47,'131132',26,1,5.6),(48,'131132',26,2,5.6),(49,'131132',26,3,5.6),(50,'323210',27,1,6.9),(51,'323210',27,2,6.9),(52,'323210',27,3,6.9),(53,'123312',28,1,4.4),(54,'123312',28,2,4.4),(55,'123312',28,3,4.4),(56,'0843',33,1,1.3),(57,'0843',33,2,1.3),(58,'0843',33,3,1.3),(59,'2311',36,2,2.9);
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_addresses`
--

LOCK TABLES `user_addresses` WRITE;
/*!40000 ALTER TABLE `user_addresses` DISABLE KEYS */;
INSERT INTO `user_addresses` VALUES (1,'220125, Республика Беларусь, Минск, Острошицкая 21 - 18',1),(2,'220125, РБ, Минск, пр. Независимости 181/3',1),(3,'220123, РБ, Минск, Горовца 12 - 10б',7),(4,'220125, РБ, Минск, пр. Независимости 181/3',15),(5,'27110, Россия, Москва, Никитский бул. 90б',13),(6,'21312, Москва, Кремль, Кремлевская 1',19),(7,'121321, Беларусь, Минск, ул. Я. Коласа 17',15),(8,'131230, Беларусь, Минск, Городецкая 13',13),(9,'131230, Беларусь, Минск, Городецкая 13',13),(10,'131230, Беларусь, Минск, Городецкая 13',13),(11,'123123, РФ, Москва, Тверская 11',19),(12,'2323, Россия, Москва, Кремлевская 11в',19),(13,'23213, РБ, Минск, Городецкая, 17',15),(14,'2313, Беларусь, Витебск, Орловская 10',25);
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
  KEY `user_subscriptions_ibfk_3_idx` (`idSubscriptionVariant`),
  CONSTRAINT `user_subscriptions_ibfk_1` FOREIGN KEY (`idPayment`) REFERENCES `payments` (`idpayment`),
  CONSTRAINT `user_subscriptions_ibfk_2` FOREIGN KEY (`idAddress`) REFERENCES `user_addresses` (`idaddress`),
  CONSTRAINT `user_subscriptions_ibfk_3` FOREIGN KEY (`idSubscriptionVariant`) REFERENCES `subscription_variants` (`idsubscriptionvariant`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_subscriptions`
--

LOCK TABLES `user_subscriptions` WRITE;
/*!40000 ALTER TABLE `user_subscriptions` DISABLE KEYS */;
INSERT INTO `user_subscriptions` VALUES (1,3,7,'2019-02-01','2019-03-01',7),(2,3,15,'2019-04-01','2019-07-01',7),(3,3,11,'2019-07-01','2020-01-01',7),(4,3,7,'2019-02-01','2019-03-01',8),(5,4,7,'2019-02-01','2019-03-01',9),(6,4,9,'2019-04-01','2019-07-01',9),(7,4,13,'2019-02-01','2019-03-01',9),(8,5,29,'2019-02-01','2019-03-01',10),(9,5,22,'2019-02-01','2019-03-01',10),(10,6,7,'2019-02-01','2019-03-01',11),(11,5,20,'2019-02-01','2019-03-01',12),(12,7,25,'2019-02-01','2019-03-01',13),(13,7,12,'2019-04-01','2019-07-01',13),(14,5,41,'2019-02-01','2019-03-01',14),(15,5,24,'2019-04-01','2019-07-01',14),(16,11,38,'2019-02-01','2019-03-01',15),(17,11,38,'2019-02-01','2019-03-01',15),(18,11,38,'2019-02-01','2019-03-01',15),(19,11,38,'2019-02-01','2019-03-01',15),(20,11,38,'2019-02-01','2019-03-01',15),(21,11,38,'2019-02-01','2019-03-01',15),(22,11,38,'2019-02-01','2019-03-01',15),(23,11,38,'2019-02-01','2019-03-01',15),(24,11,38,'2019-02-01','2019-03-01',15),(25,11,38,'2019-02-01','2019-03-01',15),(26,11,38,'2019-02-01','2019-03-01',15),(27,11,38,'2019-02-01','2019-03-01',15),(28,11,38,'2019-02-01','2019-03-01',15),(29,11,38,'2019-02-01','2019-03-01',15),(30,11,38,'2019-02-01','2019-03-01',15),(31,11,38,'2019-02-01','2019-03-01',15),(32,11,38,'2019-02-01','2019-03-01',15),(33,11,38,'2019-02-01','2019-03-01',15),(34,11,38,'2019-02-01','2019-03-01',15),(35,11,38,'2019-02-01','2019-03-01',15),(36,11,38,'2019-02-01','2019-03-01',15),(37,11,38,'2019-02-01','2019-03-01',15),(38,11,38,'2019-02-01','2019-03-01',15),(39,11,38,'2019-02-01','2019-03-01',15),(40,11,38,'2019-02-01','2019-03-01',15),(41,11,38,'2019-02-01','2019-03-01',15),(42,11,38,'2019-02-01','2019-03-01',15),(43,11,38,'2019-02-01','2019-03-01',15),(44,11,38,'2019-02-01','2019-03-01',15),(45,11,38,'2019-02-01','2019-03-01',15),(46,11,38,'2019-02-01','2019-03-01',15),(47,11,38,'2019-02-01','2019-03-01',15),(48,11,38,'2019-02-01','2019-03-01',15),(49,11,38,'2019-02-01','2019-03-01',15),(50,11,38,'2019-02-01','2019-03-01',15),(51,11,38,'2019-02-01','2019-03-01',15),(52,11,38,'2019-02-01','2019-03-01',15),(53,11,38,'2019-02-01','2019-03-01',15),(54,11,38,'2019-02-01','2019-03-01',15),(55,11,38,'2019-02-01','2019-03-01',15),(56,11,38,'2019-02-01','2019-03-01',15),(57,11,38,'2019-02-01','2019-03-01',15),(58,11,38,'2019-02-01','2019-03-01',15),(59,11,38,'2019-02-01','2019-03-01',15),(60,11,38,'2019-02-01','2019-03-01',15),(61,11,38,'2019-02-01','2019-03-01',15),(62,11,38,'2019-02-01','2019-03-01',15),(63,11,38,'2019-02-01','2019-03-01',15),(64,11,38,'2019-02-01','2019-03-01',15),(65,11,38,'2019-02-01','2019-03-01',15),(66,11,38,'2019-02-01','2019-03-01',15),(67,11,38,'2019-02-01','2019-03-01',15),(68,11,38,'2019-02-01','2019-03-01',15),(69,11,38,'2019-02-01','2019-03-01',15),(70,11,38,'2019-02-01','2019-03-01',15),(71,11,38,'2019-02-01','2019-03-01',15),(72,11,38,'2019-02-01','2019-03-01',15),(73,11,38,'2019-02-01','2019-03-01',15),(74,11,38,'2019-02-01','2019-03-01',15),(75,11,38,'2019-02-01','2019-03-01',15),(76,11,38,'2019-02-01','2019-03-01',15),(77,11,38,'2019-02-01','2019-03-01',15),(78,11,38,'2019-02-01','2019-03-01',15),(79,11,38,'2019-02-01','2019-03-01',15),(80,11,38,'2019-02-01','2019-03-01',15),(81,11,38,'2019-02-01','2019-03-01',15),(82,11,38,'2019-02-01','2019-03-01',15),(83,11,38,'2019-02-01','2019-03-01',15),(84,11,38,'2019-02-01','2019-03-01',15),(85,11,38,'2019-02-01','2019-03-01',15),(86,11,38,'2019-02-01','2019-03-01',15),(87,11,38,'2019-02-01','2019-03-01',15),(88,11,38,'2019-02-01','2019-03-01',15),(89,11,38,'2019-02-01','2019-03-01',15),(90,11,38,'2019-02-01','2019-03-01',15),(91,11,38,'2019-02-01','2019-03-01',15),(92,11,38,'2019-02-01','2019-03-01',15),(93,11,38,'2019-02-01','2019-03-01',15),(94,11,38,'2019-02-01','2019-03-01',15),(95,11,38,'2019-02-01','2019-03-01',15),(96,11,38,'2019-02-01','2019-03-01',15),(97,11,38,'2019-02-01','2019-03-01',15),(98,11,38,'2019-02-01','2019-03-01',15),(99,11,38,'2019-02-01','2019-03-01',15),(100,11,38,'2019-02-01','2019-03-01',15),(101,11,38,'2019-02-01','2019-03-01',15),(102,11,38,'2019-02-01','2019-03-01',15),(103,11,38,'2019-02-01','2019-03-01',15),(104,11,38,'2019-02-01','2019-03-01',15),(105,11,38,'2019-02-01','2019-03-01',15),(106,11,38,'2019-02-01','2019-03-01',15),(107,11,38,'2019-02-01','2019-03-01',15),(108,11,38,'2019-02-01','2019-03-01',15),(109,11,38,'2019-02-01','2019-03-01',15),(110,11,38,'2019-02-01','2019-03-01',15),(111,11,38,'2019-02-01','2019-03-01',15),(112,11,38,'2019-02-01','2019-03-01',15),(113,11,38,'2019-02-01','2019-03-01',15),(114,11,38,'2019-02-01','2019-03-01',15),(115,11,38,'2019-02-01','2019-03-01',15),(116,11,38,'2019-02-01','2019-03-01',15),(117,8,7,'2019-02-01','2019-03-01',16),(118,8,20,'2019-02-01','2019-03-01',16),(119,8,20,'2019-02-01','2019-03-01',16),(120,5,20,'2019-02-01','2019-03-01',17),(121,12,41,'2019-02-01','2019-03-01',18),(122,12,42,'2019-07-01','2020-01-01',18),(123,13,35,'2019-02-01','2019-03-01',19),(124,14,50,'2019-02-01','2019-03-01',20),(125,14,50,'2019-02-01','2019-03-01',20);
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'tvorecz','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',2,'tvorecz@yandex.ru',1),(2,'dm.zrch','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',2,'tvorecz@gmail.com',1),(3,'avril','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',2,'avril@yahoo.com',2),(4,'tata.ta','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',2,'tata.ta@mail.ru',1),(5,'tamata','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',2,'gra@tut.by',1),(6,'igor1234','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',2,'igor@ya.ru',1),(7,'tanya','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',2,'tanya@ya.by',1),(8,'maria','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',2,'maria@tut.by',2),(9,'yyr','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',2,'yyr@ya.ru',1),(10,'logger','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',2,'logger@ya.ru',1),(11,'mama','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',2,'mama@tut.by',1),(12,'qwerty','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',2,'qw@tut.by',2),(13,'yuu','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',2,'yuu@ya.ru',2),(14,'mimimi','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',2,'mi@ya.ru',1),(15,'admin','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',1,'tvorecz@tut.by',1),(16,'miui.mi','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',2,'miumi@tut.by',2),(17,'mirta','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',2,'mirta@tut,by',1),(18,'terem','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',2,'terem@tut.by',2),(19,'putin','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',2,'putin@ya.ru',1),(20,'tamama','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',2,'ta@tut.by',2),(21,'haha','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',2,'haha@tut.by',1),(22,'kuku','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',2,'kuku@tut.by',2),(23,'agugu','$2a$10$llw0G6IyibUob8h5XRt9xu/4/3uQfUO/tJjZDHpux6tRy7P8GAFw2',2,'agugu@gmail.com',2),(24,'mymymy','$2a$10$llw0G6IyibUob8h5XRt9xuZ9U5Sq3xQ0Noggaa6izHE6tD7k7qQNu',2,'my@tut.by',1),(25,'hohoho','$2a$10$llw0G6IyibUob8h5XRt9xuZ9U5Sq3xQ0Noggaa6izHE6tD7k7qQNu',2,'ho@tut.by',1),(26,'dmitry','$2a$10$llw0G6IyibUob8h5XRt9xuaRSdCnq4acE78/E7VR97PXXfWHM6rSO',2,'dmitry@tut.by',1),(27,'jozhik','$2a$10$llw0G6IyibUob8h5XRt9xu35lBPN/9Gj3kXfiOhtvoOpQsNTuLG5m',2,'jozhik@tut.by',1);
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

-- Dump completed on 2019-01-13 23:49:36
