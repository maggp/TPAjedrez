-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ajedrez
-- ------------------------------------------------------
-- Server version	5.6.25-log

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
-- Table structure for table `jugadores`
--

DROP TABLE IF EXISTS `jugadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jugadores` (
  `id_jugador` int(11) NOT NULL AUTO_INCREMENT,
  `dni` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  PRIMARY KEY (`id_jugador`),
  UNIQUE KEY `dni_UNIQUE` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugadores`
--

LOCK TABLES `jugadores` WRITE;
/*!40000 ALTER TABLE `jugadores` DISABLE KEYS */;
INSERT INTO `jugadores` VALUES (1,11111111,'Juan','Perez'),(2,22222222,'Gonzalo','Lopez'),(3,33333333,'Pablo','Martinez'),(4,44444444,'Aquiles','Vaeso'),(5,55555555,'Elsa','Thelita');
/*!40000 ALTER TABLE `jugadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partidas`
--

DROP TABLE IF EXISTS `partidas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partidas` (
  `id_partida` int(11) NOT NULL AUTO_INCREMENT,
  `dni_jugador_blancas` int(11) NOT NULL,
  `dni_jugador_negras` int(11) NOT NULL,
  `turno` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id_partida`),
  KEY `FK_jugador_1_idx` (`dni_jugador_blancas`),
  KEY `FK_jugador_2_idx` (`dni_jugador_negras`),
  CONSTRAINT `FK_jugador_1` FOREIGN KEY (`dni_jugador_blancas`) REFERENCES `jugadores` (`dni`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_jugador_2` FOREIGN KEY (`dni_jugador_negras`) REFERENCES `jugadores` (`dni`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partidas`
--

LOCK TABLES `partidas` WRITE;
/*!40000 ALTER TABLE `partidas` DISABLE KEYS */;
INSERT INTO `partidas` VALUES (1,11111111,22222222,'blanco'),(2,33333333,11111111,'negro');
/*!40000 ALTER TABLE `partidas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posiciones`
--

DROP TABLE IF EXISTS `posiciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posiciones` (
  `id_partida` int(11) NOT NULL,
  `ficha` varchar(1) NOT NULL,
  `color` varchar(6) NOT NULL,
  `fila` int(11) NOT NULL DEFAULT '0',
  `columna` varchar(1) NOT NULL DEFAULT 'z',
  PRIMARY KEY (`id_partida`,`fila`,`columna`,`ficha`,`color`),
  CONSTRAINT `FK_partida` FOREIGN KEY (`id_partida`) REFERENCES `partidas` (`id_partida`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posiciones`
--

LOCK TABLES `posiciones` WRITE;
/*!40000 ALTER TABLE `posiciones` DISABLE KEYS */;
INSERT INTO `posiciones` VALUES (1,'A','blanco',0,'z'),(1,'C','blanco',0,'z'),(1,'C','negro',0,'z'),(1,'P','blanco',0,'z'),(1,'P','negro',0,'z'),(1,'T','blanco',0,'z'),(1,'T','blanco',1,'a'),(1,'A','blanco',1,'c'),(1,'D','blanco',1,'d'),(1,'R','blanco',1,'e'),(1,'C','blanco',1,'g'),(1,'P','blanco',2,'a'),(1,'P','blanco',2,'b'),(1,'P','blanco',2,'c'),(1,'P','blanco',2,'d'),(1,'P','blanco',2,'e'),(1,'P','blanco',2,'g'),(1,'P','blanco',2,'h'),(1,'P','negro',7,'a'),(1,'P','negro',7,'c'),(1,'P','negro',7,'d'),(1,'P','negro',7,'e'),(1,'P','negro',7,'f'),(1,'P','negro',7,'g'),(1,'P','negro',7,'h'),(1,'T','negro',8,'a'),(1,'A','negro',8,'c'),(1,'D','negro',8,'d'),(1,'R','negro',8,'e'),(1,'T','negro',8,'f'),(1,'C','negro',8,'g'),(1,'A','negro',8,'h');
/*!40000 ALTER TABLE `posiciones` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-19 12:30:11
