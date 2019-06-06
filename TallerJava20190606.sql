CREATE DATABASE  IF NOT EXISTS `tallerjava` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci */;
USE `tallerjava`;
-- MySQL dump 10.13  Distrib 8.0.14, for Win64 (x86_64)
--
-- Host: localhost    Database: tallerjava
-- ------------------------------------------------------
-- Server version	8.0.14

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `clientes` (
  `idCliente` int(11) NOT NULL AUTO_INCREMENT,
  `NombreCliente` varchar(100) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `DireccionCliente` varchar(100) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `TelefonoCliente` int(9) DEFAULT NULL,
  PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facturas`
--

DROP TABLE IF EXISTS `facturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `facturas` (
  `idFactura` int(11) NOT NULL AUTO_INCREMENT,
  `FechaFactura` date DEFAULT NULL,
  `idClienteFK` int(11) DEFAULT NULL,
  `idReparacionFK` int(11) DEFAULT NULL,
  PRIMARY KEY (`idFactura`),
  KEY `idClienteFK_idx` (`idClienteFK`),
  KEY `idReparacionFK_idx` (`idReparacionFK`),
  CONSTRAINT `idClienteFK` FOREIGN KEY (`idClienteFK`) REFERENCES `clientes` (`idCliente`),
  CONSTRAINT `idReparacionFK` FOREIGN KEY (`idReparacionFK`) REFERENCES `reparaciones` (`idReparacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facturas`
--

LOCK TABLES `facturas` WRITE;
/*!40000 ALTER TABLE `facturas` DISABLE KEYS */;
/*!40000 ALTER TABLE `facturas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incluyen`
--

DROP TABLE IF EXISTS `incluyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `incluyen` (
  `idIncluyen` int(11) NOT NULL AUTO_INCREMENT,
  `idReparacionFK` int(11) DEFAULT NULL,
  `idRecambioFK` int(11) DEFAULT NULL,
  `cantidad` int(4) DEFAULT NULL,
  PRIMARY KEY (`idIncluyen`),
  KEY `idReparacionFK` (`idReparacionFK`),
  KEY `idRecambioFK` (`idRecambioFK`),
  CONSTRAINT `incluyen_ibfk_1` FOREIGN KEY (`idReparacionFK`) REFERENCES `reparaciones` (`idReparacion`),
  CONSTRAINT `incluyen_ibfk_2` FOREIGN KEY (`idRecambioFK`) REFERENCES `recambios` (`idRecambio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incluyen`
--

LOCK TABLES `incluyen` WRITE;
/*!40000 ALTER TABLE `incluyen` DISABLE KEYS */;
/*!40000 ALTER TABLE `incluyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recambios`
--

DROP TABLE IF EXISTS `recambios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `recambios` (
  `idRecambio` int(11) NOT NULL AUTO_INCREMENT,
  `DescripcionRecambio` varchar(45) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `UnidadesRecambio` int(6) DEFAULT NULL,
  `PrecioRecambio` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idRecambio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recambios`
--

LOCK TABLES `recambios` WRITE;
/*!40000 ALTER TABLE `recambios` DISABLE KEYS */;
/*!40000 ALTER TABLE `recambios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reparaciones`
--

DROP TABLE IF EXISTS `reparaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reparaciones` (
  `idReparacion` int(11) NOT NULL AUTO_INCREMENT,
  `Averia` varchar(45) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `FechaEntrada` date DEFAULT NULL,
  `FechaSalida` date DEFAULT NULL,
  `Reparado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idReparacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reparaciones`
--

LOCK TABLES `reparaciones` WRITE;
/*!40000 ALTER TABLE `reparaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `reparaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuarios` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `correoUsuario` varchar(45) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `claveUsuario` varchar(45) COLLATE utf8mb4_spanish2_ci NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'administrador@studium.es','88245df90ff712430e642f3a7e4bfa58'),(2,'usuario@studium.es','88245df90ff712430e642f3a7e4bfa58'),(3,'a','0cc175b9c0f1b6a831c399e269772661');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'tallerjava'
--
/*!50003 DROP PROCEDURE IF EXISTS `borrarTablas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`usuarioTaller`@`localhost` PROCEDURE `borrarTablas`()
BEGIN
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE incluyen;
TRUNCATE TABLE facturas;
TRUNCATE TABLE reparaciones;   
TRUNCATE TABLE clientes;
TRUNCATE TABLE recambios; 
SET FOREIGN_KEY_CHECKS = 1;

ALTER TABLE `tallerjava`.`incluyen` AUTO_INCREMENT = 1 ;
ALTER TABLE `tallerjava`.`facturas` AUTO_INCREMENT = 1 ;
ALTER TABLE `tallerjava`.`reparaciones` AUTO_INCREMENT = 1 ;
ALTER TABLE `tallerjava`.`clientes` AUTO_INCREMENT = 1 ;
ALTER TABLE `tallerjava`.`recambios` AUTO_INCREMENT = 1 ;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-06 10:40:41
