CREATE DATABASE IF NOT EXISTS `agenda`;
USE agenda;

CREATE TABLE IF NOT EXISTS `signos`(
  idSigno int(11) NOT NULL AUTO_INCREMENT,
  tipoSigno varchar(45) NOT NULL,

  PRIMARY KEY(idSigno)
);

CREATE TABLE IF NOT EXISTS `localidades`(
  `codPostal` int (4) NOT NULL,
  `Pais` varchar(20) NOT NULL,
  `Provincia` varchar(20) NOT NULL,
  `nombreLocalidad` varchar(20) NOT NULL,
  
  PRIMARY KEY(codPostal)
);

CREATE TABLE IF NOT EXISTS `domicilios`(
  `idDomicilio` int(11) NOT NULL AUTO_INCREMENT,
  `Calle` varchar(20),
  `Altura` int(8),
  `Piso` int(4),
  `Departamento` int(4),
  `codPostal` int(4) NOT NULL,

   PRIMARY KEY(idDomicilio),
   FOREIGN KEY (codPostal) REFERENCES localidades (codPostal)  	
);

CREATE TABLE IF NOT EXISTS `etiquetas`(
  idEtiqueta int(11) NOT NULL AUTO_INCREMENT,
  tipoEtiqueta varchar(20) NOT NULL,

  PRIMARY KEY (idEtiqueta)
);

CREATE TABLE IF NOT EXISTS `personas`
(
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `idDomicilio` int(11),
  `idEtiqueta` int(11),
  `FechaCumple` varchar(20),
  `idSigno` int (11),

  PRIMARY KEY (`idPersona`),
  FOREIGN KEY (idDomicilio) REFERENCES Domicilios(idDomicilio),
  FOREIGN KEY (idEtiqueta) REFERENCES Etiquetas(idEtiqueta),
  FOREIGN KEY (idSigno) REFERENCES signos(idSigno)
);

CREATE USER  IF NOT EXISTS 'montenegro_sandoval'@'localhost' IDENTIFIED BY '1234';
GRANT RELOAD,PROCESS ON *.* TO 'montenegro_sandoval'@'localhost';

GRANT ALL PRIVILEGES ON *.* TO 'montenegro_sandoval'@'localhost' WITH GRANT OPTION;