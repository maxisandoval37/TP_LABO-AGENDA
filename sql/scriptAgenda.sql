CREATE DATABASE `agenda`;
USE agenda;
CREATE TABLE `personas`
(
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
  `Calle` varchar(20) NOT NULL,
  `Altura` int(8) NOT NULL,
  `Piso` int(4),
  `Departamento` int(4),
  `Localidad` varchar(20),
  `Email` varchar(30),
  `Etiqueta` varchar(20),
  PRIMARY KEY (`idPersona`)
);

