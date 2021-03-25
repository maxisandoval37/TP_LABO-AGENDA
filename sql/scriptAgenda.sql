CREATE DATABASE IF NOT EXISTS `agenda`;
USE agenda;

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
  `Calle` varchar(20),
  `Altura` int(8),
  `Piso` int(4),
  `Departamento` int(4),
  `Localidad` varchar(20),
  `Email` varchar(30),
  `idEtiqueta` int(11),
  `FechaCumple` varchar(20),

  PRIMARY KEY (`idPersona`),
  FOREIGN KEY (idEtiqueta) REFERENCES Etiquetas(idEtiqueta)
);

CREATE USER  IF NOT EXISTS 'montenegro_sandoval'@'localhost' IDENTIFIED BY '1234';
GRANT RELOAD,PROCESS ON *.* TO 'montenegro_sandoval'@'localhost';

GRANT ALL PRIVILEGES ON *.* TO 'montenegro_sandoval'@'localhost' WITH GRANT OPTION;