CREATE DATABASE IF NOT EXISTS `agenda`;
USE agenda;

CREATE TABLE `localidades`(
  `idLocalidad` int (11) NOT NULL AUTO_INCREMENT,
  `Pais` varchar(45) NOT NULL,
  `Provincia` varchar(45) NOT NULL,
  `nombreLocalidad` varchar(45) NOT NULL,
  
  PRIMARY KEY(idLocalidad)
);

CREATE TABLE `domicilios`(
  `idDomicilio` int(11) NOT NULL AUTO_INCREMENT,
  `Calle` varchar(20),
  `Altura` int(8),
  `Piso` int(4),
  `Departamento` int(4),
  `idLocalidad` int(11) NOT NULL,

   PRIMARY KEY(idDomicilio),
   FOREIGN KEY (idLocalidad) REFERENCES localidades (idLocalidad)  	
);

CREATE TABLE `etiquetas`(
  idEtiqueta int(11) NOT NULL AUTO_INCREMENT,
  tipoEtiqueta varchar(45) NOT NULL,

  PRIMARY KEY (idEtiqueta)
);

CREATE TABLE `personas`
(
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `idDomicilio` int(11),
  `idEtiqueta` int(11),
  `FechaCumple` varchar(20),

  PRIMARY KEY (`idPersona`),
  FOREIGN KEY (idDomicilio) REFERENCES Domicilios(idDomicilio),
  FOREIGN KEY (idEtiqueta) REFERENCES Etiquetas(idEtiqueta)
);

CREATE USER  IF NOT EXISTS 'montenegro_sandoval'@'localhost' IDENTIFIED BY '1234';
GRANT RELOAD,PROCESS ON *.* TO 'montenegro_sandoval'@'localhost';

GRANT ALL PRIVILEGES ON *.* TO 'montenegro_sandoval'@'localhost' WITH GRANT OPTION;