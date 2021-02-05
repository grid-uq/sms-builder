
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
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RECURSO`
--

LOCK TABLES `RECURSO` WRITE;
/*!40000 ALTER TABLE `RECURSO` DISABLE KEYS */;
INSERT INTO `RECURSO` VALUES (1,'administracion',0,'/administracion/index.xhtml'),(2,'seguridad_recurso',0,'/seguridad/recurso/index.xhtml'),(3,'seguridad_rol',0,'/seguridad/rol/index.xhtml'),(4,'seguridad_usuario',0,'/seguridad/usuario/index.xhtml'),(5,'seguridad_usuario_actualizar',0,'/seguridad/usuario/actualizar.xhtml'),(6,'boleta_vender',0,'/boleta/vender.xhtml'),(7,'revision_analizarReferencias',0,'/revision/analizarReferencias.xhtml'),(8,'revision_aplicarCriterios',0,'/revision/aplicarCriterios.xhtml'),(9,'revision_aplicarCriterios2',0,'/revision/aplicarCriterios2.xhtml'),(10,'revision_evaluarReferencia',0,'/revision/evaluarReferencia.xhtml'),(11,'revision_evaluarReferencias',0,'/revision/evaluarReferencias.xhtml'),(12,'revision_gestionarReferenciasRepetidas',0,'/revision/gestionarReferenciasRepetidas.xhtml'),(13,'revision_referenciaAdicionarCitas',0,'/revision/referenciaAdicionarCitas.xhtml'),(14,'revision_registrarTopico',0,'/revision/registrarTopico.xhtml'),(15,'revision_registroAtributoCalidad',0,'/revision/registroAtributoCalidad.xhtml'),(16,'revision_registroInicial',0,'/revision/registroInicial.xhtml'),(17,'revision_registroObjetivo',0,'/revision/registroObjetivo.xhtml'),(18,'revision_registroPregunta',0,'/revision/registroPregunta.xhtml'),(19,'revision_registroReferencias',0,'/revision/registroReferencias.xhtml'),(20,'revision_registroRevision',0,'/revision/registroRevision.xhtml'),(21,'revision_registroTermino',0,'/revision/registroTermino.xhtml'),(22,'revision_resumenReferenciasDestacadas',0,'/revision/resumenReferenciasDestacadas.xhtml'),(23,'revision_resumenReferenciasSeleccionadas',0,'/revision/resumenReferenciasSeleccionadas.xhtml'),(24,'revision_seleccionarRevision',0,'/revision/seleccionarRevision.xhtml'),(25,'revision_tablaResumenEvaluacionReferencias',0,'/revision/tablaResumenEvaluacionReferencias.xhtml'),(26,'revision_tablaResumenEvaluacionReferenciasAtributo',0,'/revision/tablaResumenEvaluacionReferenciasAtributo.xhtml'),(27,'estadisticas_palabrasClave',0,'/estadisticas/palabrasClave.xhtml'),(28,'estadisticas_referenciaPalabrasClave',0,'/estadisticas/referenciaPalabrasClave.xhtml'),(29,'estadisticas_referenciasCalidadYear',0,'/estadisticas/referenciasCalidadYear.xhtml'),(30,'estadisticas_referenciasPregunta',0,'/estadisticas/referenciasPregunta.xhtml'),(31,'estadisticas_referenciasTipo',0,'/estadisticas/referenciasTipo.xhtml'),(32,'estadisticas_referenciasTipoFuente',0,'/estadisticas/referenciasTipoFuente.xhtml'),(33,'estadisticas_referenciasTopico',0,'/estadisticas/referenciasTopico.xhtml'),(34,'estadisticas_referenciasTopicoAtributoCalidad',0,'/estadisticas/referenciasTopicoAtributoCalidad.xhtml'),(35,'estadisticas_referenciasYear',0,'/estadisticas/referenciasYear.xhtml'),(36,'estadisticas_tablaReferenciasPreguntas',0,'/estadisticas/tablaReferenciasPreguntas.xhtml'),(37,'estadisticas_tablaReferenciasTopicos',0,'/estadisticas/tablaReferenciasTopicos.xhtml'),(38,'index',1,'/index.xhtml'),(39,'seguridad_usuario_registro',1,'/seguridad/usuario/registro.xhtml'),(40,'revision_revisores',0,'/revision/revisores/index.xhtml ');
/*!40000 ALTER TABLE `RECURSO` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `ROL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ROL` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NOMBRE` (`NOMBRE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `ROL` WRITE;
/*!40000 ALTER TABLE `ROL` DISABLE KEYS */;
INSERT INTO `ROL` VALUES (1,'Administrador'),(2,'Usuario');
/*!40000 ALTER TABLE `ROL` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `USUARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USUARIO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CLAVE` varchar(50) NOT NULL,
  `ESTADO` varchar(255) DEFAULT NULL,
  `INTENTOS` int(11) DEFAULT NULL,
  `NOMBREUSUARIO` varchar(50) NOT NULL,
  `EMAIL` varchar(150) DEFAULT NULL,
  `NOMBRE` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NOMBREUSUARIO` (`NOMBREUSUARIO`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `USUARIO` WRITE;
/*!40000 ALTER TABLE `USUARIO` DISABLE KEYS */;
INSERT INTO `USUARIO` VALUES (1,'Persona','12345','ACTIVO',0,'root','root@email.com','root','1','7342126');
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
INSERT INTO `ROL_RECURSO` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10),(2,11),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),(2,18),(2,19),(2,20),(2,21),(2,22),(2,23),(2,24),(2,25),(2,26),(2,27),(2,28),(2,29),(2,30),(2,31),(2,32),(2,33),(2,34),(2,35),(2,36),(2,37),(2,40);
/*!40000 ALTER TABLE `ROL_RECURSO` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `REVISION_USUARIO`;
CREATE TABLE `REVISION_USUARIO` (
  `Revision_ID` int(11) NOT NULL,
  `revisores_ID` int(11) NOT NULL,
  PRIMARY KEY (`Revision_ID`,`revisores_ID`),
  KEY `FK_REVISION_USUARIO_revisores_ID` (`revisores_ID`),
  CONSTRAINT `FK_REVISION_USUARIO_Revision_ID` FOREIGN KEY (`Revision_ID`) REFERENCES `REVISION` (`ID`),
  CONSTRAINT `FK_REVISION_USUARIO_revisores_ID` FOREIGN KEY (`revisores_ID`) REFERENCES `USUARIO` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE Revision
ADD COLUMN PROPIETARIO_ID int(11) default 1;

