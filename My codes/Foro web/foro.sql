CREATE DATABASE  IF NOT EXISTS `foro` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `foro`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: foro
-- ------------------------------------------------------
-- Server version	5.7.15-log

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
-- Table structure for table `respuestas`
--

DROP TABLE IF EXISTS `respuestas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `respuestas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idTema` int(11) DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `respuesta` varchar(500) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respuestas`
--

LOCK TABLES `respuestas` WRITE;
/*!40000 ALTER TABLE `respuestas` DISABLE KEYS */;
INSERT INTO `respuestas` VALUES (1,3,3,'dsfsf','2017-10-20 18:55:46'),(2,3,3,'ghfgh','2017-10-20 18:55:51'),(3,3,3,'fdgfdg','2017-10-20 18:55:57'),(4,1,3,'fhtht','2017-10-20 18:58:24');
/*!40000 ALTER TABLE `respuestas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tema_con`
--

DROP TABLE IF EXISTS `tema_con`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tema_con` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Tema` varchar(200) NOT NULL,
  `consulta` varchar(1000) NOT NULL,
  `fecha` date DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `nVisitas` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tema_con`
--

LOCK TABLES `tema_con` WRITE;
/*!40000 ALTER TABLE `tema_con` DISABLE KEYS */;
INSERT INTO `tema_con` VALUES (1,'Un telefono movil que diagnostica el cancer','Cientificos de la Escuela Medica de Harvard y del Instituto Tecnolegico de Massachusetts (MIT), en Estados Unidos, han desarrollado un dispositivo portetil que, acoplado a un telefono mevil inteligente, detecta las proteenas producidas por las celulas canceregenas, proporcionando un metodo rapido para diagnosticar y monitorizar la evolucion de un posible tumor en enfermos de cancer. ','1995-01-29',2,332),(2,'Cual es el origen de la tecla Escape?	','La mayoria de los programas o aplicaciones informaticas permiten interrumpir una accion o cancelarla por medio de la tecla Esc, mas conocida como Escape. Coincidiendo con el boom de la informatica de consumo en los años sesenta, Robert William Bemer, un ingeniero de IBM, invento esta tecla que, al presionarla, detiene, por ejemplo, la carga de un programa. ','1995-01-29',2,328),(3,'Que es lo mas buscado en Wikipedia?','Segin el ultimo ranking oficial de Wikipedia, los cinco contenidos globalmente más vistos en la enciclopedia colaborativa online son Amero (Redirección a Unión monetaria de America del Norte), Dia de Muertos, Halloween, Wikipedia y Calentamiento global.','2017-09-20',2,10),(4,'Cual es la capacidad de la humanidad para almacenar y transmitir informacion? ','En 2007, la humanidad fue capaz de almacenar 295 trillones de bytes óptimamente comprimidos, comunicar casi dos cuatrillones de megabytes, y llevar a cabo 6,4 “','2017-09-20',2,4);
/*!40000 ALTER TABLE `tema_con` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `USR` varchar(20) NOT NULL,
  `PSW` varchar(50) DEFAULT NULL,
  `priv` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'tony','7c4a8d09ca3762af61e59520943dc26494f8941b','0'),(2,'lalo','7c4a8d09ca3762af61e59520943dc26494f8941b','1'),(3,'dioney','7c4a8d09ca3762af61e59520943dc26494f8941b','1'),(4,'gus','7c4a8d09ca3762af61e59520943dc26494f8941b','1');
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

-- Dump completed on 2017-11-13 11:49:17
