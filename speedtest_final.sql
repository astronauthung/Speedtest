-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: laptrinhmang
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `speed_data`
--

DROP TABLE IF EXISTS `speed_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `speed_data` (
  `user_id` int DEFAULT NULL,
  `speed_download` varchar(45) DEFAULT NULL,
  `speed_upload` varchar(45) DEFAULT NULL,
  `speed_ping` varchar(45) DEFAULT NULL,
  `speed_ip` varchar(45) DEFAULT NULL,
  `speed_time` datetime DEFAULT NULL,
  `speed_host` varchar(45) DEFAULT NULL,
  `speed_city` varchar(45) DEFAULT NULL,
  `speed_country` varchar(45) DEFAULT NULL,
  KEY `user_id` (`user_id`),
  CONSTRAINT `speed_data_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `speed_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `speed_data`
--

LOCK TABLES `speed_data` WRITE;
/*!40000 ALTER TABLE `speed_data` DISABLE KEYS */;
INSERT INTO `speed_data` VALUES (1,'62.4994615716473','132.4694263962388','8.587','113.174.169.66','2024-01-10 17:15:42','speedtestdng.fpt.vn:8080','Da Nang','Vietnam'),(2,'48.51583901662517','107.1749050677795','12.96','113.174.169.66','2024-01-10 17:17:02','speedtestdng.fpt.vn:8080','Da Nang','Vietnam'),(2,'8.2886052202661','120.55435113196711','10.498','113.174.169.66','2024-01-10 17:17:32','speedtestdng.fpt.vn:8080','Da Nang','Vietnam'),(1,'80.47462472317025','113.51806869338205','9.402','113.174.169.66','2024-01-11 00:21:02','speedtestdng.fpt.vn:8080','Da Nang','Vietnam'),(1,'3.3647277325223257','120.54187199415915','25.548','113.174.169.66','2024-01-11 00:40:23','Speedtestqbh.viettel.vn:8080','Ha Tinh','Vietnam'),(1,'2.6581599236013007','3.1159488765864576','11.888','14.245.243.189','2024-01-11 07:32:15','nt2.mobifone.vn:8080','Nha Trang','Vietnam'),(1,'3.198445210052183','2.839345478307709','11.856','14.245.243.189','2024-01-11 07:32:41','nt2.mobifone.vn:8080','Nha Trang','Vietnam'),(1,'2.6998208534403862','2.6780697712373147','8.702','14.245.243.189','2024-01-11 07:33:06','nt2.mobifone.vn:8080','Nha Trang','Vietnam'),(1,'2.5613745884850956','2.7769977505306676','7.075','14.176.232.239','2024-01-11 07:33:33','dn1.mobifone.vn:8080','Da Nang','Vietnam'),(1,'2.2792849128272805','2.870566108362842','10.876','14.236.18.120','2024-01-11 07:33:57','nt2.mobifone.vn:8080','Nha Trang','Vietnam'),(1,'2.235341207826569','2.7422119395367743','11.027','14.245.243.189','2024-01-11 07:34:21','dn1.mobifone.vn:8080','Da Nang','Vietnam'),(1,'2.9077749698292052','2.904007839775639','9.734','14.245.243.189','2024-01-11 07:34:45','nt2.mobifone.vn:8080','Nha Trang','Vietnam'),(1,'2.9153464160625524','2.829194881300317','12.921','113.160.225.45','2024-01-11 07:35:10','nt2.mobifone.vn:8080','Nha Trang','Vietnam'),(1,'2.545618861790585','3.0102647273384187','22.23','14.176.232.239','2024-01-11 07:35:35','nt2.mobifone.vn:8080','Nha Trang','Vietnam'),(1,'2.638415529191867','2.5818775170379675','24.019','14.245.243.189','2024-01-11 07:36:00','dn1.mobifone.vn:8080','Da Nang','Vietnam'),(1,'2.959496930955007','2.1369373311373034','14.613','14.176.232.239','2024-01-11 07:36:26','nt2.mobifone.vn:8080','Nha Trang','Vietnam'),(1,'2.7059383855239307','2.7882724336333213','14.227','14.176.232.239','2024-01-11 07:36:54','nt2.mobifone.vn:8080','Nha Trang','Vietnam'),(1,'2.5739677552900333','2.62263093986959','12.365','14.245.243.189','2024-01-11 07:37:18','nt2.mobifone.vn:8080','Nha Trang','Vietnam'),(1,'2.5409843748774397','2.173323677670787','15.986','113.160.225.45','2024-01-11 07:37:42','dn1.mobifone.vn:8080','Da Nang','Vietnam'),(1,'2.359392639774756','2.900035034501876','29.248','14.245.243.189','2024-01-11 07:38:09','dn1.mobifone.vn:8080','Da Nang','Vietnam'),(1,'2.953323290052206','2.450766063168732','12.699','14.236.18.120','2024-01-11 07:38:33','nt2.mobifone.vn:8080','Nha Trang','Vietnam'),(1,'2.78497109831179','2.659745971474895','10.023','113.160.225.45','2024-01-11 07:38:58','nt2.mobifone.vn:8080','Nha Trang','Vietnam'),(1,'2.561371544108397','2.6792153439023867','5.942','14.236.18.120','2024-01-11 07:39:21','dn1.mobifone.vn:8080','Da Nang','Vietnam'),(1,'3.065683595029197','2.490113347728439','8.765','14.245.243.189','2024-01-11 07:39:45','nt2.mobifone.vn:8080','Nha Trang','Vietnam'),(1,'1.8022402990789352','4.473414271418707','12.991','14.176.232.239','2024-01-11 07:40:32','dn1.mobifone.vn:8080','Da Nang','Vietnam'),(1,'3.7801011245706877','4.011639495968572','8.013','14.245.243.189','2024-01-11 07:41:57','dn1.mobifone.vn:8080','Da Nang','Vietnam'),(1,'3.1726200061908894','3.1648572681362546','15.261','14.245.243.189','2024-01-11 07:42:20','nt2.mobifone.vn:8080','Nha Trang','Vietnam'),(1,'3.025457252554348','2.3744771723116234','19.164','113.160.225.45','2024-01-11 07:42:45','nt2.mobifone.vn:8080','Nha Trang','Vietnam'),(1,'2.731820666912825','3.1044688013211292','21.9','14.245.243.189','2024-01-11 07:43:11','dn1.mobifone.vn:8080','Da Nang','Vietnam'),(1,'2.7066826302065983','2.841619826170575','16.58','14.176.232.239','2024-01-11 07:43:34','nt2.mobifone.vn:8080','Nha Trang','Vietnam'),(1,'2.30285762610446','2.497877932139228','6.751','113.160.225.45','2024-01-11 07:43:58','nt2.mobifone.vn:8080','Nha Trang','Vietnam'),(1,'2.157916180182257','2.5628285637129244','8.699','14.176.232.239','2024-01-11 07:44:22','nt2.mobifone.vn:8080','Nha Trang','Vietnam'),(1,'2.5612795798855346','2.553719498114108','19.476','14.236.18.120','2024-01-11 07:44:47','nt2.mobifone.vn:8080','Nha Trang','Vietnam'),(1,'2.351807938915674','2.222889899073185','13.878','113.160.225.45','2024-01-11 07:45:11','dn1.mobifone.vn:8080','Da Nang','Vietnam'),(1,'2.68359334873145','2.700451696963272','13.414','14.236.18.120','2024-01-11 07:45:36','dn1.mobifone.vn:8080','Da Nang','Vietnam'),(1,'2.193545819090229','3.834573742984417','9.684','14.176.232.239','2024-01-11 07:46:00','nt2.mobifone.vn:8080','Nha Trang','Vietnam'),(1,'2.62006310654893','1.9435313917345105','31.959','14.236.18.120','2024-01-11 07:46:29','speedtestkv2a.viettel.vn:8080','Da Nang','Vietnam'),(1,'2.504025177145875','3.6307105276289047','27.395','14.245.243.189','2024-01-11 07:46:56','speedtestkv2a.viettel.vn:8080','Da Nang','Vietnam'),(1,'2.7945221144961714','2.9496503583356453','12.277','14.176.232.239','2024-01-11 07:47:21','dn1.mobifone.vn:8080','Da Nang','Vietnam'),(1,'2.974899149885761','2.968107957173373','24.836','14.176.232.239','2024-01-11 07:47:45','speedtestkv2a.viettel.vn:8080','Da Nang','Vietnam'),(1,'1.9260934809414716','2.919338765109303','13.769','14.176.232.239','2024-01-11 07:48:10','vst.powernet.vn:8080','Da Nang','Vietnam'),(1,'2.1685074499675117','2.605775838022284','6.518','113.160.225.45','2024-01-11 07:48:33','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'2.7309061072119754','2.8149882843956884','9.602','113.160.225.45','2024-01-11 07:48:58','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'2.25133910768684','2.834821298969447','5.568','14.176.232.239','2024-01-11 07:49:22','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'3.1444446041260146','4.291783371932628','6.712','14.236.18.120','2024-01-11 07:49:45','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'2.8038771669165623','2.290442528696811','14.117','113.160.225.45','2024-01-11 07:50:09','vst.powernet.vn:8080','Da Nang','Vietnam'),(1,'3.2497267173805082','2.401847468143583','9.509','14.176.232.239','2024-01-11 07:50:41','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'2.5309719869536416','2.9097208649257444','9.221','113.160.225.45','2024-01-11 07:51:07','vst.powernet.vn:8080','Da Nang','Vietnam'),(1,'3.041005113790451','2.6244105496597405','8.026','14.245.243.189','2024-01-11 07:51:31','vst.powernet.vn:8080','Da Nang','Vietnam'),(1,'2.233537472742667','2.893940171614087','7.841','14.236.18.120','2024-01-11 07:51:55','vst.powernet.vn:8080','Da Nang','Vietnam'),(1,'2.5096675596752194','2.716582955725737','10.148','14.245.243.189','2024-01-11 07:52:18','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'2.2007854020175044','3.6837347970142784','7.834','14.176.232.239','2024-01-11 07:52:43','vst.powernet.vn:8080','Da Nang','Vietnam'),(1,'4.186514428253442','3.4682898896401624','9.286','14.176.232.239','2024-01-11 07:53:06','vst.powernet.vn:8080','Da Nang','Vietnam'),(1,'2.293416231439191','2.8598386674586074','10.022','14.236.18.120','2024-01-11 07:53:33','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'2.253328744134045','2.498591313050545','5.96','113.160.225.45','2024-01-11 07:54:04','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'2.6897013593949524','2.367433842390623','7.841','14.236.18.120','2024-01-11 07:54:28','vst.powernet.vn:8080','Da Nang','Vietnam'),(1,'2.582283605183853','2.739975688401781','6.564','14.236.18.120','2024-01-11 07:54:52','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'2.9379514084031437','2.9814803776370438','6.843','113.160.225.45','2024-01-11 07:55:16','vst.powernet.vn:8080','Da Nang','Vietnam'),(1,'2.6999152851485135','1.9604413396880478','6.823','14.176.232.239','2024-01-11 07:55:40','vst.powernet.vn:8080','Da Nang','Vietnam'),(1,'1.4361863852328027','4.447212847250734','7.291','14.245.243.189','2024-01-11 07:56:08','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'3.533425973135281','4.65434319561973','13.729','14.176.232.239','2024-01-11 07:56:39','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'4.46880650845461','4.479986211991923','9.686','14.176.232.239','2024-01-11 07:57:05','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'3.7986295463772946','3.0285193995928816','12.634','14.245.243.189','2024-01-11 07:57:30','speedtestdng.fpt.vn:8080','Da Nang','Vietnam'),(1,'3.3987918963396813','4.826536943888337','8.606','14.245.243.189','2024-01-11 07:57:57','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'3.9107635624203896','4.787131315860708','10.951','14.236.18.120','2024-01-11 07:58:24','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'3.0545959830820295','2.494006638517526','11.898','14.245.243.189','2024-01-11 07:58:48','vst.powernet.vn:8080','Da Nang','Vietnam'),(1,'2.7423936730289116','2.551626658739211','5.338','14.245.243.189','2024-01-11 07:59:21','vst.powernet.vn:8080','Da Nang','Vietnam'),(1,'2.1327757728564483','2.673059668603211','6.167','14.176.232.239','2024-01-11 07:59:46','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'2.7217640802603444','3.1865329031921825','30.712','14.245.243.189','2024-01-11 08:00:15','qnm-speedtest.viettelidc.com.vn:8080','Tam Kỳ','Vietnam'),(1,'2.415843608506272','2.688109520423247','9.651','14.176.232.239','2024-01-11 08:00:44','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'1.7632419647458195','2.563007570815679','5.555','113.160.225.45','2024-01-11 08:01:08','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'2.471790409700956','2.792316423330489','10.18','14.176.232.239','2024-01-11 08:01:32','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'2.4882312110726694','3.0401341630178433','6.383','14.236.18.120','2024-01-11 08:01:55','vst.powernet.vn:8080','Da Nang','Vietnam'),(1,'2.4931555170035855','2.589008330450023','6.935','113.160.225.45','2024-01-11 08:02:21','vst.powernet.vn:8080','Da Nang','Vietnam'),(1,'2.2689388206141428','1.7678635193918926','7.107','113.160.225.45','2024-01-11 08:02:44','vst.powernet.vn:8080','Da Nang','Vietnam'),(1,'3.4468828240920266','4.017480473411796','13.769','14.176.232.239','2024-01-11 08:03:13','vst.powernet.vn:8080','Da Nang','Vietnam'),(1,'2.941780883186958','3.3531428263669554','8.501','113.160.225.45','2024-01-11 08:03:36','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'2.6521416620426486','2.2753145174625065','7.407','14.176.232.239','2024-01-11 08:03:59','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'3.040073665091585','3.5034394218486704','6.639','14.176.232.239','2024-01-11 08:04:24','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'2.476153631794646','2.4481088124461334','9.195','14.176.232.239','2024-01-11 08:04:48','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'2.601266174773868','2.7471656805962934','14.999','14.245.243.189','2024-01-11 08:05:12','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'2.522939861037698','2.627468860578048','5.975','113.160.225.45','2024-01-11 08:05:36','vst.powernet.vn:8080','Da Nang','Vietnam'),(1,'2.0276667258189773','2.7258324119903294','5.259','14.176.232.239','2024-01-11 08:05:59','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'2.518728830419742','2.8537203457723854','6.497','14.176.232.239','2024-01-11 08:06:24','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'3.0850583463700585','2.685730548291665','6.202','14.176.232.239','2024-01-11 08:06:47','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'1.8826736027496256','1.9485447083564997','9.842','14.236.18.120','2024-01-11 08:07:11','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'2.751120598558735','4.416844815486095','9.331','113.160.225.45','2024-01-11 08:07:39','vst.powernet.vn:8080','Da Nang','Vietnam'),(1,'3.6678172316376982','4.692613911699788','11.637','113.160.225.45','2024-01-11 08:08:08','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'2.7740974377006697','3.7207342821191887','6.929','14.176.232.239','2024-01-11 08:08:33','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'3.0166712084274616','4.245654197067474','11.216','14.176.232.239','2024-01-11 08:08:59','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'3.8605841433607417','3.728251700635955','9.562','14.176.232.239','2024-01-11 08:09:22','vst.powernet.vn:8080','Da Nang','Vietnam'),(1,'3.653083094920217','4.548835444101309','10.44','14.176.232.239','2024-01-11 08:09:47','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'2.326836016779983','2.038335824270573','8.641','14.236.18.120','2024-01-11 08:10:12','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'2.5412690251080736','2.449122199236883','8.367','14.176.232.239','2024-01-11 08:10:37','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'2.666007940687665','2.7014364313896873','6.609','14.236.18.120','2024-01-11 08:11:01','vst.powernet.vn:8080','Da Nang','Vietnam'),(1,'1.5666472815877448','3.8696216061081916','7.432','14.176.232.239','2024-01-11 08:11:24','vst.powernet.vn:8080','Da Nang','Vietnam'),(1,'3.806490874392903','4.32435369488764','9.367','113.160.225.45','2024-01-11 08:12:01','vst.powernet.vn:8080','Da Nang','Vietnam'),(1,'3.044537962334797','4.606208232321708','6.752','14.236.18.120','2024-01-11 08:12:24','speedtest5.vtn.com.vn:8080','Da Nang','Vietnam'),(1,'4.374149944822121','4.132564231087563','7.323','113.160.225.45','2024-01-11 08:12:52','speedtestdng.fpt.vn:8080','Da Nang','Vietnam');
/*!40000 ALTER TABLE `speed_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `speed_user`
--

DROP TABLE IF EXISTS `speed_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `speed_user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `user_pass` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `speed_user`
--

LOCK TABLES `speed_user` WRITE;
/*!40000 ALTER TABLE `speed_user` DISABLE KEYS */;
INSERT INTO `speed_user` VALUES (1,'Hung','123123'),(2,'duy','123');
/*!40000 ALTER TABLE `speed_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-13  4:43:07