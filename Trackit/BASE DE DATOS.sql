-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: trackit
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alumnos`
--

DROP TABLE IF EXISTS `alumnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alumnos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dispositivo` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `fecha_ingreso` datetime NOT NULL,
  `institucion` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `apellidos` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `nombres` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(400) COLLATE utf8_spanish_ci NOT NULL,
  `conductor` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL,
  `expreso` int(11) DEFAULT NULL,
  `encargado` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL,
  `estado` varchar(1) COLLATE utf8_spanish_ci NOT NULL DEFAULT 'A',
  `buscar` text COLLATE utf8_spanish_ci,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `dispositivo_UNIQUE` (`dispositivo`),
  KEY `id_usuario_idx` (`id_usuario`),
  FULLTEXT KEY `id_search` (`apellidos`,`nombres`,`direccion`),
  CONSTRAINT `id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumnos`
--

LOCK TABLES `alumnos` WRITE;
/*!40000 ALTER TABLE `alumnos` DISABLE KEYS */;
INSERT INTO `alumnos` VALUES (109,'2602F630',103,'2016-11-16 16:01:35','LICEO NAVAL','AYALA TOLEDO','SELENE','HUANCAVILCA ENTRE 12AVA Y 13AVA','DAVID LARA',60,'CLARA CAYAMBE','A','SELENE AYALA TOLEDO HUANCAVILCA ENTRE 12AVA Y 13AVA'),(110,'0644F530',104,'2016-11-17 09:16:15','LICEO NAVAL','TOLEDO','MAYRA','ESTEROS','DAVID LARA',60,'CARLOS AYALA','A','MAYRA TOLEDO ESTEROS'),(111,NULL,105,'2016-11-21 16:04:37','28 DE MAYO','CEVALLOS','ANDREA','LA JOYA CORONA MZ 15 VILLA 14','ANGEL CEVALLOS',23,'SARA ANDRADE','A','ANDREA CEVALLOS LA JOYA CORONA MZ 15 VILLA 14');
/*!40000 ALTER TABLE `alumnos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estados`
--

DROP TABLE IF EXISTS `estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  `estado` char(1) DEFAULT 'A',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estados`
--

LOCK TABLES `estados` WRITE;
/*!40000 ALTER TABLE `estados` DISABLE KEYS */;
INSERT INTO `estados` VALUES (1,'CASA','A'),(2,'EXPRESO','A'),(3,'ESCUELA','A');
/*!40000 ALTER TABLE `estados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historial`
--

DROP TABLE IF EXISTS `historial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historial` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_alumno` int(11) DEFAULT NULL,
  `id_estado` int(11) DEFAULT NULL,
  `fecha` datetime NOT NULL,
  `estado` char(1) DEFAULT 'A',
  PRIMARY KEY (`id`),
  KEY `idAlumnoFK_idx` (`id_alumno`),
  KEY `idEstadoFK_idx` (`id_estado`),
  CONSTRAINT `idAlumnoFK` FOREIGN KEY (`id_alumno`) REFERENCES `alumnos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idEstadoFK` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historial`
--

LOCK TABLES `historial` WRITE;
/*!40000 ALTER TABLE `historial` DISABLE KEYS */;
INSERT INTO `historial` VALUES (176,NULL,2,'2016-11-16 16:07:19','A'),(177,109,2,'2016-11-16 16:08:05','A'),(178,109,2,'2016-11-16 16:08:35','A'),(179,109,2,'2016-11-16 16:13:51','A'),(180,NULL,2,'2016-11-16 19:03:52','A'),(181,109,2,'2016-11-16 19:04:06','A'),(182,NULL,1,'2016-11-16 19:04:42','A'),(183,109,1,'2016-11-16 19:04:46','A'),(184,109,1,'2016-11-16 19:05:59','A'),(185,109,1,'2016-11-16 19:06:01','A'),(186,NULL,1,'2016-11-16 19:06:04','A'),(187,NULL,1,'2016-11-16 19:06:05','A'),(188,109,1,'2016-11-16 19:19:04','A'),(189,109,1,'2016-11-16 19:22:31','A'),(190,109,1,'2016-11-16 19:24:03','A'),(191,NULL,2,'2016-11-16 19:25:21','A'),(192,109,2,'2016-11-16 19:25:35','A'),(193,NULL,1,'2016-11-16 19:29:57','A'),(194,NULL,1,'2016-11-16 19:30:00','A'),(195,NULL,1,'2016-11-16 19:30:12','A'),(196,NULL,1,'2016-11-16 19:30:15','A'),(197,NULL,1,'2016-11-16 19:30:20','A'),(198,109,1,'2016-11-16 19:30:22','A'),(199,109,1,'2016-11-16 19:30:23','A'),(200,NULL,1,'2016-11-16 19:30:45','A'),(201,NULL,1,'2016-11-16 19:30:49','A'),(202,NULL,1,'2016-11-16 19:30:54','A'),(203,109,1,'2016-11-16 19:30:57','A'),(204,109,2,'2016-11-16 19:45:20','A'),(205,109,2,'2016-11-16 21:15:27','A'),(206,109,2,'2016-11-16 21:17:28','A'),(207,109,2,'2016-11-16 21:17:35','A'),(208,109,2,'2016-11-16 21:17:42','A'),(209,109,3,'2016-11-17 09:00:52','A'),(210,109,1,'2016-11-17 09:01:02','A'),(211,109,3,'2016-11-17 09:05:05','A'),(212,109,1,'2016-11-17 09:05:07','A'),(213,109,3,'2016-11-17 09:10:01','A'),(214,109,1,'2016-11-17 09:10:09','A'),(215,109,2,'2016-11-17 09:10:19','A'),(216,NULL,3,'2016-11-17 09:11:48','A'),(217,110,3,'2016-11-17 09:16:29','A'),(218,110,1,'2016-11-17 09:16:31','A'),(219,109,3,'2016-11-17 09:19:44','A'),(220,109,1,'2016-11-17 09:19:45','A'),(221,110,3,'2016-11-17 09:19:47','A'),(222,110,1,'2016-11-17 09:19:48','A'),(223,110,3,'2016-11-17 09:20:08','A'),(224,110,3,'2016-11-17 09:24:11','A'),(225,110,1,'2016-11-17 09:24:13','A'),(226,109,3,'2016-11-17 09:24:17','A'),(227,109,1,'2016-11-17 09:24:19','A'),(228,109,3,'2016-11-17 09:25:29','A'),(229,109,1,'2016-11-17 09:25:30','A'),(230,110,3,'2016-11-17 09:30:45','A'),(231,110,1,'2016-11-17 09:30:47','A'),(232,109,3,'2016-11-17 09:30:53','A'),(233,109,1,'2016-11-17 09:30:54','A'),(234,109,2,'2016-11-17 09:31:03','A'),(235,109,2,'2016-11-17 09:31:10','A'),(236,109,2,'2016-11-17 09:32:29','A'),(237,109,2,'2016-11-17 09:32:58','A'),(239,NULL,3,'2016-11-17 09:34:09','A'),(240,NULL,3,'2016-11-17 09:34:37','A');
/*!40000 ALTER TABLE `historial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) CHARACTER SET utf8 NOT NULL,
  `cedula` varchar(45) CHARACTER SET utf8 NOT NULL,
  `clave` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `apellidos` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `nombres` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `fecha_registro` datetime NOT NULL,
  `estado` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT 'A',
  `buscar` text COLLATE utf8_spanish_ci,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cedula_UNIQUE` (`cedula`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'ADMINISTRADOR','ADMIN','24bc8a6655a0623a7f4e402c12df4b9c','ADMIN','ADMINISTRADOR','2016-08-22 16:51:50','A','ADMIN ADMINISTRADOR ADMIN'),(103,'ESTANDAR','0924832215','25d55ad283aa400af464c76d713c07ad','CAYAMBE','CLARA','2016-11-16 15:38:18','A','0924832215 CLARA CAYAMBE'),(104,'ESTANDAR','0940678956','c61e937b1815da4297ac3befdba836e2','AYALA','CARLOS','2016-11-17 09:13:58','A','0940678956 CARLOS AYALA'),(105,'ESTANDAR','0924096233','24bc8a6655a0623a7f4e402c12df4b9c','VILLACIS','DOLORES','2016-11-21 16:02:33','A','0924096233 DOLORES VILLACIS');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-21 16:15:13
