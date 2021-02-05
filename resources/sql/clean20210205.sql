-- MariaDB dump 10.18  Distrib 10.5.8-MariaDB, for osx10.15 (x86_64)
--
-- Host: localhost    Database: sms
-- ------------------------------------------------------
-- Server version	10.5.8-MariaDB

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
-- Table structure for table `ATRIBUTOCALIDAD`
--

DROP TABLE IF EXISTS `ATRIBUTOCALIDAD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ATRIBUTOCALIDAD` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPCION` varchar(255) NOT NULL,
  `REVISION_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ATRIBUTOCALIDAD_REVISION_ID` (`REVISION_ID`),
  CONSTRAINT `FK_ATRIBUTOCALIDAD_REVISION_ID` FOREIGN KEY (`REVISION_ID`) REFERENCES `REVISION` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ATRIBUTOCALIDAD`
--

LOCK TABLES `ATRIBUTOCALIDAD` WRITE;
/*!40000 ALTER TABLE `ATRIBUTOCALIDAD` DISABLE KEYS */;
/*!40000 ALTER TABLE `ATRIBUTOCALIDAD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EVALUACIONCALIDAD`
--

DROP TABLE IF EXISTS `EVALUACIONCALIDAD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EVALUACIONCALIDAD` (
  `EVALUACIONCUALITATIVA` varchar(255) DEFAULT NULL,
  `EVALUACIONCUANTITATIVA` float DEFAULT NULL,
  `ATRIBUTOCALIDAD_ID` int(11) NOT NULL,
  `REFERENCIA_ID` int(11) NOT NULL,
  PRIMARY KEY (`ATRIBUTOCALIDAD_ID`,`REFERENCIA_ID`),
  KEY `FK_EVALUACIONCALIDAD_REFERENCIA_ID` (`REFERENCIA_ID`),
  CONSTRAINT `FK_EVALUACIONCALIDAD_ATRIBUTOCALIDAD_ID` FOREIGN KEY (`ATRIBUTOCALIDAD_ID`) REFERENCES `ATRIBUTOCALIDAD` (`ID`),
  CONSTRAINT `FK_EVALUACIONCALIDAD_REFERENCIA_ID` FOREIGN KEY (`REFERENCIA_ID`) REFERENCES `REFERENCIA` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EVALUACIONCALIDAD`
--

LOCK TABLES `EVALUACIONCALIDAD` WRITE;
/*!40000 ALTER TABLE `EVALUACIONCALIDAD` DISABLE KEYS */;
/*!40000 ALTER TABLE `EVALUACIONCALIDAD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FUENTE`
--

DROP TABLE IF EXISTS `FUENTE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FUENTE` (
  `NOMBRE` varchar(255) NOT NULL,
  `TIPO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`NOMBRE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FUENTE`
--

LOCK TABLES `FUENTE` WRITE;
/*!40000 ALTER TABLE `FUENTE` DISABLE KEYS */;
INSERT INTO `FUENTE` VALUES ('ACM','BASE_DATOS'),('IEEE','BASE_DATOS'),('INCLUSION_DIRECTA','INCLUSION_DIRECTA'),('MANUAL','INCLUSION_DIRECTA'),('SCIENCEDIRECT','BASE_DATOS'),('SCOPUS','BASE_DATOS'),('SNOWBALL_BACKWARD','BOLA_NIEVE'),('SNOWBALL_FORWARD','BOLA_NIEVE'),('SPRINGER','BASE_DATOS'),('WOS','BASE_DATOS');
/*!40000 ALTER TABLE `FUENTE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `METADATO`
--

DROP TABLE IF EXISTS `METADATO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `METADATO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `IDENTIFIER` varchar(255) DEFAULT NULL,
  `VALUE` longtext DEFAULT NULL,
  `REFERENCIA_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_METADATO_REFERENCIA_ID` (`REFERENCIA_ID`),
  CONSTRAINT `FK_METADATO_REFERENCIA_ID` FOREIGN KEY (`REFERENCIA_ID`) REFERENCES `REFERENCIA` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `METADATO`
--

LOCK TABLES `METADATO` WRITE;
/*!40000 ALTER TABLE `METADATO` DISABLE KEYS */;
/*!40000 ALTER TABLE `METADATO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NOTA`
--

DROP TABLE IF EXISTS `NOTA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NOTA` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPCION` longtext DEFAULT NULL,
  `ETAPA` int(11) DEFAULT NULL,
  `REFERENCIA_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_NOTA_REFERENCIA_ID` (`REFERENCIA_ID`),
  CONSTRAINT `FK_NOTA_REFERENCIA_ID` FOREIGN KEY (`REFERENCIA_ID`) REFERENCES `REFERENCIA` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NOTA`
--

LOCK TABLES `NOTA` WRITE;
/*!40000 ALTER TABLE `NOTA` DISABLE KEYS */;
/*!40000 ALTER TABLE `NOTA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OBJETIVO`
--

DROP TABLE IF EXISTS `OBJETIVO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OBJETIVO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODIGO` varchar(255) DEFAULT NULL,
  `DESCRIPCION` varchar(255) DEFAULT NULL,
  `REVISION_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_OBJETIVO_REVISION_ID` (`REVISION_ID`),
  CONSTRAINT `FK_OBJETIVO_REVISION_ID` FOREIGN KEY (`REVISION_ID`) REFERENCES `REVISION` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OBJETIVO`
--

LOCK TABLES `OBJETIVO` WRITE;
/*!40000 ALTER TABLE `OBJETIVO` DISABLE KEYS */;
/*!40000 ALTER TABLE `OBJETIVO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PREGUNTA`
--

DROP TABLE IF EXISTS `PREGUNTA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PREGUNTA` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODIGO` varchar(3) NOT NULL,
  `DESCRIPCION` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PREGUNTA`
--

LOCK TABLES `PREGUNTA` WRITE;
/*!40000 ALTER TABLE `PREGUNTA` DISABLE KEYS */;
/*!40000 ALTER TABLE `PREGUNTA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PREGUNTA_OBJETIVO`
--

DROP TABLE IF EXISTS `PREGUNTA_OBJETIVO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PREGUNTA_OBJETIVO` (
  `objetivos_ID` int(11) NOT NULL,
  `preguntas_ID` int(11) NOT NULL,
  PRIMARY KEY (`objetivos_ID`,`preguntas_ID`),
  KEY `FK_PREGUNTA_OBJETIVO_preguntas_ID` (`preguntas_ID`),
  CONSTRAINT `FK_PREGUNTA_OBJETIVO_objetivos_ID` FOREIGN KEY (`objetivos_ID`) REFERENCES `OBJETIVO` (`ID`),
  CONSTRAINT `FK_PREGUNTA_OBJETIVO_preguntas_ID` FOREIGN KEY (`preguntas_ID`) REFERENCES `PREGUNTA` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PREGUNTA_OBJETIVO`
--

LOCK TABLES `PREGUNTA_OBJETIVO` WRITE;
/*!40000 ALTER TABLE `PREGUNTA_OBJETIVO` DISABLE KEYS */;
/*!40000 ALTER TABLE `PREGUNTA_OBJETIVO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QUERIES`
--

DROP TABLE IF EXISTS `QUERIES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QUERIES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QUERIES`
--

LOCK TABLES `QUERIES` WRITE;
/*!40000 ALTER TABLE `QUERIES` DISABLE KEYS */;
/*!40000 ALTER TABLE `QUERIES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RECURSO`
--

DROP TABLE IF EXISTS `RECURSO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RECURSO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(50) NOT NULL,
  `PUBLICO` tinyint(1) DEFAULT 0,
  `URL` varchar(300) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NOMBRE` (`NOMBRE`),
  UNIQUE KEY `URL` (`URL`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RECURSO`
--

LOCK TABLES `RECURSO` WRITE;
/*!40000 ALTER TABLE `RECURSO` DISABLE KEYS */;
INSERT INTO `RECURSO` VALUES (1,'administracion',0,'/administracion/index.xhtml'),(2,'seguridad_recurso',0,'/seguridad/recurso/index.xhtml'),(3,'seguridad_rol',0,'/seguridad/rol/index.xhtml'),(4,'seguridad_usuario',0,'/seguridad/usuario/index.xhtml'),(5,'seguridad_usuario_actualizar',0,'/seguridad/usuario/actualizar.xhtml'),(6,'boleta_vender',0,'/boleta/vender.xhtml'),(7,'revision_analizarReferencias',0,'/revision/analizarReferencias.xhtml'),(8,'revision_aplicarCriterios',0,'/revision/aplicarCriterios.xhtml'),(9,'revision_aplicarCriterios2',0,'/revision/aplicarCriterios2.xhtml'),(10,'revision_evaluarReferencia',0,'/revision/evaluarReferencia.xhtml'),(11,'revision_evaluarReferencias',0,'/revision/evaluarReferencias.xhtml'),(12,'revision_gestionarReferenciasRepetidas',0,'/revision/gestionarReferenciasRepetidas.xhtml'),(13,'revision_referenciaAdicionarCitas',0,'/revision/referenciaAdicionarCitas.xhtml'),(14,'revision_registrarTopico',0,'/revision/registrarTopico.xhtml'),(15,'revision_registroAtributoCalidad',0,'/revision/registroAtributoCalidad.xhtml'),(16,'revision_registroInicial',0,'/revision/registroInicial.xhtml'),(17,'revision_registroObjetivo',0,'/revision/registroObjetivo.xhtml'),(18,'revision_registroPregunta',0,'/revision/registroPregunta.xhtml'),(19,'revision_registroReferencias',0,'/revision/registroReferencias.xhtml'),(20,'revision_registroRevision',0,'/revision/registroRevision.xhtml'),(21,'revision_registroTermino',0,'/revision/registroTermino.xhtml'),(22,'revision_resumenReferenciasDestacadas',0,'/revision/resumenReferenciasDestacadas.xhtml'),(23,'revision_resumenReferenciasSeleccionadas',0,'/revision/resumenReferenciasSeleccionadas.xhtml'),(24,'revision_seleccionarRevision',0,'/revision/seleccionarRevision.xhtml'),(25,'revision_tablaResumenEvaluacionReferencias',0,'/revision/tablaResumenEvaluacionReferencias.xhtml'),(26,'revision_tablaResumenEvaluacionReferenciasAtributo',0,'/revision/tablaResumenEvaluacionReferenciasAtributo.xhtml'),(27,'revision_revisores',0,'/revision/revisores/index.xhtml'),(28,'estadisticas_palabrasClave',0,'/estadisticas/palabrasClave.xhtml'),(29,'estadisticas_referenciaPalabrasClave',0,'/estadisticas/referenciaPalabrasClave.xhtml'),(30,'estadisticas_referenciasCalidadYear',0,'/estadisticas/referenciasCalidadYear.xhtml'),(31,'estadisticas_referenciasPregunta',0,'/estadisticas/referenciasPregunta.xhtml'),(32,'estadisticas_referenciasTipo',0,'/estadisticas/referenciasTipo.xhtml'),(33,'estadisticas_referenciasTipoFuente',0,'/estadisticas/referenciasTipoFuente.xhtml'),(34,'estadisticas_referenciasTopico',0,'/estadisticas/referenciasTopico.xhtml'),(35,'estadisticas_referenciasTopicoAtributoCalidad',0,'/estadisticas/referenciasTopicoAtributoCalidad.xhtml'),(36,'estadisticas_referenciasYear',0,'/estadisticas/referenciasYear.xhtml'),(37,'estadisticas_tablaReferenciasPreguntas',0,'/estadisticas/tablaReferenciasPreguntas.xhtml'),(38,'estadisticas_tablaReferenciasTopicos',0,'/estadisticas/tablaReferenciasTopicos.xhtml'),(39,'index',1,'/index.xhtml'),(40,'seguridad_usuario_registro',1,'/seguridad/usuario/registro.xhtml');
/*!40000 ALTER TABLE `RECURSO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REFERENCIA`
--

DROP TABLE IF EXISTS `REFERENCIA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REFERENCIA` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CITAS` int(11) DEFAULT NULL,
  `FILTRO` int(11) DEFAULT NULL,
  `NOMBRE` longtext DEFAULT NULL,
  `NOTA` longtext DEFAULT NULL,
  `PONDERACIONCITAS` float DEFAULT NULL,
  `RELEVANCIA` int(11) DEFAULT NULL,
  `RESUMEN` longtext DEFAULT NULL,
  `SCI` float DEFAULT NULL,
  `SPSID` varchar(50) DEFAULT NULL,
  `SRRQI` float DEFAULT NULL,
  `TIPO` varchar(255) DEFAULT NULL,
  `TOTALEVALUACIONCALIDAD` float DEFAULT NULL,
  `YEAR` varchar(4) DEFAULT NULL,
  `REVISION_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_REFERENCIA_REVISION_ID` (`REVISION_ID`),
  CONSTRAINT `FK_REFERENCIA_REVISION_ID` FOREIGN KEY (`REVISION_ID`) REFERENCES `REVISION` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REFERENCIA`
--

LOCK TABLES `REFERENCIA` WRITE;
/*!40000 ALTER TABLE `REFERENCIA` DISABLE KEYS */;
/*!40000 ALTER TABLE `REFERENCIA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REFERENCIA_TOPICO`
--

DROP TABLE IF EXISTS `REFERENCIA_TOPICO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REFERENCIA_TOPICO` (
  `Referencia_ID` int(11) NOT NULL,
  `topicos_ID` int(11) NOT NULL,
  PRIMARY KEY (`Referencia_ID`,`topicos_ID`),
  KEY `FK_REFERENCIA_TOPICO_topicos_ID` (`topicos_ID`),
  CONSTRAINT `FK_REFERENCIA_TOPICO_Referencia_ID` FOREIGN KEY (`Referencia_ID`) REFERENCES `REFERENCIA` (`ID`),
  CONSTRAINT `FK_REFERENCIA_TOPICO_topicos_ID` FOREIGN KEY (`topicos_ID`) REFERENCES `TOPICO` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REFERENCIA_TOPICO`
--

LOCK TABLES `REFERENCIA_TOPICO` WRITE;
/*!40000 ALTER TABLE `REFERENCIA_TOPICO` DISABLE KEYS */;
/*!40000 ALTER TABLE `REFERENCIA_TOPICO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REVISION`
--

DROP TABLE IF EXISTS `REVISION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REVISION` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPCION` varchar(255) DEFAULT NULL,
  `NOMBRE` varchar(255) NOT NULL,
  `PROPIETARIO_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_REVISION_PROPIETARIO_ID` (`PROPIETARIO_ID`),
  CONSTRAINT `FK_REVISION_PROPIETARIO_ID` FOREIGN KEY (`PROPIETARIO_ID`) REFERENCES `USUARIO` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REVISION`
--

LOCK TABLES `REVISION` WRITE;
/*!40000 ALTER TABLE `REVISION` DISABLE KEYS */;
/*!40000 ALTER TABLE `REVISION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REVISION_USUARIO`
--

DROP TABLE IF EXISTS `REVISION_USUARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REVISION_USUARIO` (
  `Revision_ID` int(11) NOT NULL,
  `revisores_ID` int(11) NOT NULL,
  PRIMARY KEY (`Revision_ID`,`revisores_ID`),
  KEY `FK_REVISION_USUARIO_revisores_ID` (`revisores_ID`),
  CONSTRAINT `FK_REVISION_USUARIO_Revision_ID` FOREIGN KEY (`Revision_ID`) REFERENCES `REVISION` (`ID`),
  CONSTRAINT `FK_REVISION_USUARIO_revisores_ID` FOREIGN KEY (`revisores_ID`) REFERENCES `USUARIO` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REVISION_USUARIO`
--

LOCK TABLES `REVISION_USUARIO` WRITE;
/*!40000 ALTER TABLE `REVISION_USUARIO` DISABLE KEYS */;
/*!40000 ALTER TABLE `REVISION_USUARIO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROL`
--

DROP TABLE IF EXISTS `ROL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ROL` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NOMBRE` (`NOMBRE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROL`
--

LOCK TABLES `ROL` WRITE;
/*!40000 ALTER TABLE `ROL` DISABLE KEYS */;
INSERT INTO `ROL` VALUES (1,'Administrador'),(2,'Usuario');
/*!40000 ALTER TABLE `ROL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROL_RECURSO`
--

DROP TABLE IF EXISTS `ROL_RECURSO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ROL_RECURSO` (
  `Rol_ID` int(11) NOT NULL,
  `recursos_ID` int(11) NOT NULL,
  PRIMARY KEY (`Rol_ID`,`recursos_ID`),
  KEY `FK_ROL_RECURSO_recursos_ID` (`recursos_ID`),
  CONSTRAINT `FK_ROL_RECURSO_Rol_ID` FOREIGN KEY (`Rol_ID`) REFERENCES `ROL` (`ID`),
  CONSTRAINT `FK_ROL_RECURSO_recursos_ID` FOREIGN KEY (`recursos_ID`) REFERENCES `RECURSO` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROL_RECURSO`
--

LOCK TABLES `ROL_RECURSO` WRITE;
/*!40000 ALTER TABLE `ROL_RECURSO` DISABLE KEYS */;
INSERT INTO `ROL_RECURSO` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10),(2,11),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),(2,18),(2,19),(2,20),(2,21),(2,22),(2,23),(2,24),(2,25),(2,26),(2,27),(2,28),(2,29),(2,30),(2,31),(2,32),(2,33),(2,34),(2,35),(2,36),(2,37),(2,38);
/*!40000 ALTER TABLE `ROL_RECURSO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TERMINO`
--

DROP TABLE IF EXISTS `TERMINO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TERMINO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPCION` varchar(255) NOT NULL,
  `REVISION_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_TERMINO_REVISION_ID` (`REVISION_ID`),
  CONSTRAINT `FK_TERMINO_REVISION_ID` FOREIGN KEY (`REVISION_ID`) REFERENCES `REVISION` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TERMINO`
--

LOCK TABLES `TERMINO` WRITE;
/*!40000 ALTER TABLE `TERMINO` DISABLE KEYS */;
/*!40000 ALTER TABLE `TERMINO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TOPICO`
--

DROP TABLE IF EXISTS `TOPICO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TOPICO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPCION` varchar(255) DEFAULT NULL,
  `PREGUNTA_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_TOPICO_PREGUNTA_ID` (`PREGUNTA_ID`),
  CONSTRAINT `FK_TOPICO_PREGUNTA_ID` FOREIGN KEY (`PREGUNTA_ID`) REFERENCES `PREGUNTA` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TOPICO`
--

LOCK TABLES `TOPICO` WRITE;
/*!40000 ALTER TABLE `TOPICO` DISABLE KEYS */;
/*!40000 ALTER TABLE `TOPICO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USUARIO`
--

DROP TABLE IF EXISTS `USUARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USUARIO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CLAVE` varchar(50) NOT NULL,
  `EMAIL` varchar(150) DEFAULT NULL,
  `ESTADO` varchar(255) DEFAULT NULL,
  `INTENTOS` int(11) DEFAULT NULL,
  `NOMBRE` varchar(70) DEFAULT NULL,
  `NOMBREUSUARIO` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NOMBREUSUARIO` (`NOMBREUSUARIO`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USUARIO`
--

LOCK TABLES `USUARIO` WRITE;
/*!40000 ALTER TABLE `USUARIO` DISABLE KEYS */;
INSERT INTO `USUARIO` VALUES (1,'12345','root@email.com','ACTIVO',0,'root','root');
/*!40000 ALTER TABLE `USUARIO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USUARIO_ROL`
--

DROP TABLE IF EXISTS `USUARIO_ROL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USUARIO_ROL` (
  `Usuario_ID` int(11) NOT NULL,
  `roles_ID` int(11) NOT NULL,
  PRIMARY KEY (`Usuario_ID`,`roles_ID`),
  KEY `FK_USUARIO_ROL_roles_ID` (`roles_ID`),
  CONSTRAINT `FK_USUARIO_ROL_Usuario_ID` FOREIGN KEY (`Usuario_ID`) REFERENCES `USUARIO` (`ID`),
  CONSTRAINT `FK_USUARIO_ROL_roles_ID` FOREIGN KEY (`roles_ID`) REFERENCES `ROL` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USUARIO_ROL`
--

LOCK TABLES `USUARIO_ROL` WRITE;
/*!40000 ALTER TABLE `USUARIO_ROL` DISABLE KEYS */;
INSERT INTO `USUARIO_ROL` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `USUARIO_ROL` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-05 14:44:42
