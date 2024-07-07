-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.3.7-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para sisbo
CREATE DATABASE IF NOT EXISTS `sisbo` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci */;
USE `sisbo`;

-- Volcando estructura para tabla sisbo.boleta
CREATE TABLE IF NOT EXISTS `boleta` (
  `id_boleta` int(11) NOT NULL AUTO_INCREMENT,
  `precio` int(11) NOT NULL,
  `mercado_secundario` tinyint(4) DEFAULT 0,
  `id_localidad` int(11) NOT NULL,
  `id_seguidor` varchar(20) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`id_boleta`),
  KEY `id_localidad` (`id_localidad`),
  KEY `id_seguidor` (`id_seguidor`),
  CONSTRAINT `boleta_ibfk_2` FOREIGN KEY (`id_localidad`) REFERENCES `localidad` (`id_localidad`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `boleta_ibfk_4` FOREIGN KEY (`id_seguidor`) REFERENCES `seguidor` (`documento_de_identidad`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sisbo.boleta: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `boleta` DISABLE KEYS */;
/*!40000 ALTER TABLE `boleta` ENABLE KEYS */;

-- Volcando estructura para tabla sisbo.club
CREATE TABLE IF NOT EXISTS `club` (
  `id_club` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(300) COLLATE utf8_spanish2_ci NOT NULL,
  `correo_electronico` varchar(200) COLLATE utf8_spanish2_ci NOT NULL,
  `contrasena` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `estadio_propio` varchar(300) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id_club`),
  UNIQUE KEY `nombre` (`nombre`),
  UNIQUE KEY `correo_electronico` (`correo_electronico`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sisbo.club: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
INSERT INTO `club` (`id_club`, `nombre`, `correo_electronico`, `contrasena`, `estadio_propio`) VALUES
	(6, 'Atletico Bucaramanga', 'AtleticoBga@gmail.com', 'bucaramanga123', 'Americo Montanini'),
	(7, 'Millonarios FC', 'millonarios@gmail.com', 'millonarios123', 'El campin'),
	(8, 'Atletico Nacional', 'nacional@gmail.com', 'nacional123', 'Atanasio Girardot'),
	(9, 'Junior de Barranquilla', 'junior@gmail.com', 'junior123', 'Metropolitano Roberto Meléndez');
/*!40000 ALTER TABLE `club` ENABLE KEYS */;

-- Volcando estructura para tabla sisbo.club_correovinculado
CREATE TABLE IF NOT EXISTS `club_correovinculado` (
  `id_club` int(11) NOT NULL,
  `correo` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id_club`,`correo`),
  CONSTRAINT `club_correovinculado_ibfk_1` FOREIGN KEY (`id_club`) REFERENCES `club` (`id_club`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sisbo.club_correovinculado: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `club_correovinculado` DISABLE KEYS */;
/*!40000 ALTER TABLE `club_correovinculado` ENABLE KEYS */;

-- Volcando estructura para tabla sisbo.eventodeportivo
CREATE TABLE IF NOT EXISTS `eventodeportivo` (
  `id_evento` int(11) NOT NULL AUTO_INCREMENT,
  `oponente` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `estadio` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `fecha` date NOT NULL,
  `hora_ingreso` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `hora_cierre` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `id_club` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_evento`),
  KEY `id_club` (`id_club`),
  CONSTRAINT `eventodeportivo_ibfk_1` FOREIGN KEY (`id_club`) REFERENCES `club` (`id_club`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sisbo.eventodeportivo: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `eventodeportivo` DISABLE KEYS */;
INSERT INTO `eventodeportivo` (`id_evento`, `oponente`, `estadio`, `fecha`, `hora_ingreso`, `hora_cierre`, `id_club`) VALUES
	(13, 'Atletico Bucaramanga', 'Metropolitano Roberto Meléndez', '2024-07-13', '15:00', '18:00', 9),
	(14, 'Millonarios FC', 'El campin', '2024-07-27', '18:00', '21:00', 9),
	(15, 'Atletico Nacional', 'El campin', '2024-07-13', '19:00', '22:00', 7),
	(16, 'Deportivo Pereira', 'Hernán Ramírez Villegas', '2024-08-10', '13:00', '16:00', 7),
	(17, 'Junior de Barranquilla', 'Americo Montanini', '2024-07-27', '17:00', '20:00', 6),
	(18, 'Atletico Nacional', 'El campin', '2024-08-10', '15:00', '18:00', 6);
/*!40000 ALTER TABLE `eventodeportivo` ENABLE KEYS */;

-- Volcando estructura para tabla sisbo.localidad
CREATE TABLE IF NOT EXISTS `localidad` (
  `id_localidad` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `precio` int(11) NOT NULL,
  `cantidad_puestos_total` int(11) NOT NULL,
  `cantidad_puestos_vendidos` int(11) NOT NULL,
  `id_evento` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_localidad`),
  KEY `id_evento` (`id_evento`),
  CONSTRAINT `localidad_ibfk_1` FOREIGN KEY (`id_evento`) REFERENCES `eventodeportivo` (`id_evento`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sisbo.localidad: ~8 rows (aproximadamente)
/*!40000 ALTER TABLE `localidad` DISABLE KEYS */;
INSERT INTO `localidad` (`id_localidad`, `nombre`, `precio`, `cantidad_puestos_total`, `cantidad_puestos_vendidos`, `id_evento`) VALUES
	(12, 'Occidental', 200000, 5000, 0, 13),
	(13, 'Sur', 350000, 2000, 0, 13),
	(14, 'Oriental', 380000, 5000, 0, 13),
	(15, 'Norte', 350000, 2000, 0, 13),
	(16, 'Norte', 400000, 2000, 0, 14),
	(17, 'Oriental', 450000, 5000, 0, 14),
	(18, 'Occidental', 400000, 6000, 0, 14),
	(19, 'Sur', 350000, 2000, 0, 14),
	(20, 'Occidental', 450000, 6000, 0, 15),
	(21, 'Norte', 400000, 2000, 0, 15),
	(22, 'Oriental', 350000, 5000, 0, 15),
	(23, 'Sur', 350000, 2000, 0, 15),
	(24, 'Norte', 250000, 1000, 0, 16),
	(25, 'Occidental', 300000, 3000, 0, 16),
	(26, 'Oriental', 320000, 2800, 0, 16),
	(27, 'Sur', 250000, 1000, 0, 16),
	(28, 'Occidental', 250000, 10000, 0, 17),
	(29, 'Sur', 350000, 3800, 0, 17),
	(30, 'Norte', 300000, 4000, 0, 17),
	(31, 'Oriental', 250000, 9000, 0, 17),
	(32, 'Norte', 400000, 5000, 0, 18),
	(33, 'Sur', 400000, 5000, 0, 18),
	(34, 'Occidental', 300000, 8000, 0, 18),
	(35, 'Oriental', 320000, 9000, 0, 18);
/*!40000 ALTER TABLE `localidad` ENABLE KEYS */;

-- Volcando estructura para tabla sisbo.seguidor
CREATE TABLE IF NOT EXISTS `seguidor` (
  `documento_de_identidad` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `correo_electronico` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `contrasena` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`documento_de_identidad`),
  UNIQUE KEY `documento_de_identidad` (`documento_de_identidad`),
  UNIQUE KEY `correo_electronico` (`correo_electronico`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sisbo.seguidor: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `seguidor` DISABLE KEYS */;
INSERT INTO `seguidor` (`documento_de_identidad`, `nombre`, `correo_electronico`, `contrasena`) VALUES
	('1111111111', 'Juan Sepulveda', 'juan@gmail.com', 'juan123'),
	('9999999999', 'Carlos Garcia', 'carlos@gmail.com', 'carlos123');
/*!40000 ALTER TABLE `seguidor` ENABLE KEYS */;

-- Volcando estructura para tabla sisbo.servicioadicionalclub
CREATE TABLE IF NOT EXISTS `servicioadicionalclub` (
  `id_servicio_club` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `descripcion` varchar(500) COLLATE utf8_spanish2_ci NOT NULL,
  `precio` int(11) NOT NULL,
  `unidades_totales` int(11) NOT NULL,
  `unidades_vendidas` int(11) NOT NULL,
  `id_club` int(11) NOT NULL,
  PRIMARY KEY (`id_servicio_club`),
  KEY `id_club` (`id_club`),
  CONSTRAINT `servicioadicionalclub_ibfk_1` FOREIGN KEY (`id_club`) REFERENCES `club` (`id_club`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sisbo.servicioadicionalclub: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `servicioadicionalclub` DISABLE KEYS */;
/*!40000 ALTER TABLE `servicioadicionalclub` ENABLE KEYS */;

-- Volcando estructura para tabla sisbo.servicioadicionalseguidor
CREATE TABLE IF NOT EXISTS `servicioadicionalseguidor` (
  `id_servicio_seguidor` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `descripcion` varchar(500) COLLATE utf8_spanish2_ci NOT NULL,
  `id_seguidor` varchar(20) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`id_servicio_seguidor`),
  KEY `id_seguidor` (`id_seguidor`),
  CONSTRAINT `servicioadicionalseguidor_ibfk_1` FOREIGN KEY (`id_seguidor`) REFERENCES `seguidor` (`documento_de_identidad`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sisbo.servicioadicionalseguidor: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `servicioadicionalseguidor` DISABLE KEYS */;
/*!40000 ALTER TABLE `servicioadicionalseguidor` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
