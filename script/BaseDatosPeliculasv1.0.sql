-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.6.17 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.3.0.5027
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para oscar
CREATE DATABASE IF NOT EXISTS `oscar` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `oscar`;


-- Volcando estructura para tabla oscar.peliculas
CREATE TABLE IF NOT EXISTS `peliculas` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `titulo` varchar(250) NOT NULL COMMENT 'Titulo de la pelicula',
  `genero` varchar(50) NOT NULL COMMENT 'Genero de la pelicula',
  `duracion` int(10) unsigned DEFAULT NULL COMMENT 'Duracion en minutos',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1 COMMENT='Base de datos de peliculas\r\n';

-- Volcando datos para la tabla oscar.peliculas: ~7 rows (aproximadamente)
/*!40000 ALTER TABLE `peliculas` DISABLE KEYS */;
INSERT INTO `peliculas` (`id`, `titulo`, `genero`, `duracion`) VALUES
	(14, 'superman1', 'accion', 120),
	(15, 'superman2', 'accion', 120),
	(16, 'superman3', 'accion', 120),
	(17, 'superman4', 'accion', 120),
	(18, 'superman5', 'accion', 120),
	(19, 'superman6', 'accion', 120),
	(20, 'superman7', 'accion', 120),
	(21, 'superman8', 'accion', 120),
	(22, 'superman9', 'accion', 120),
	(23, 'superman10', 'accion', 120);
/*!40000 ALTER TABLE `peliculas` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
