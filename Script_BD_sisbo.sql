CREATE DATABASE IF NOT EXISTS `sisbo` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci */;
USE `sisbo`;

CREATE TABLE Club (
    id_club INT(10) AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(300) NOT NULL,
    correo_electronico VARCHAR(200) NOT NULL,
    estadio_propio VARCHAR(300) NOT NULL
);

CREATE TABLE EventoDeportivo (sisbo
    id_evento INT AUTO_INCREMENT PRIMARY KEY,
    oponente VARCHAR(255) NOT NULL,
    estadio VARCHAR(255) NOT NULL,
    fecha DATE NOT NULL,
    hora_ingreso VARCHAR(20) NOT NULL,
    hora_cierre VARCHAR(20) NOT NULL,
    id_club INT,
    FOREIGN KEY (id_club) REFERENCES Club(id_club)
);

CREATE TABLE Localidad (
    id_localidad INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio INT NOT NULL,
    cantidad_puestos_total INT NOT NULL,
    cantidad_puestos_vendidos INT NOT NULL,
    id_evento INT,
    FOREIGN KEY (id_evento) REFERENCES EventoDeportivo(id_evento)
);

CREATE TABLE Seguidor (
    documento_de_identidad VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL,
    contrasena VARCHAR(255) NOT NULL
);

CREATE TABLE MercadoSecundario (
    id_mercado_secundario INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE Boleta (
    id_boleta INT AUTO_INCREMENT PRIMARY KEY,
    precio INT NOT NULL,
    id_evento INT,
    id_localidad INT,
    id_mercado_secundario INT,
    id_seguidor VARCHAR(20),
    FOREIGN KEY (id_evento) REFERENCES EventoDeportivo(id_evento),
    FOREIGN KEY (id_localidad) REFERENCES Localidad(id_localidad),
    FOREIGN KEY (id_mercado_secundario) REFERENCES MercadoSecundario(id_mercado_secundario),
    FOREIGN KEY (id_seguidor) REFERENCES Seguidor(documento_de_identidad)
);

CREATE TABLE ServicioAdicionalClub (
    id_servicio_club INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    precio INT NOT NULL,
    unidades_maximas INT NOT NULL,
    unidades_vendidas INT NOT NULL,
    id_club INT,
    FOREIGN KEY (id_club) REFERENCES Club(id_club)
);

CREATE TABLE ServicioAdicionalSeguidor (
    id_servicio_seguidor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(500) NOT NULL,
    id_seguidor VARCHAR(20),
    FOREIGN KEY (id_seguidor) REFERENCES Seguidor(documento_de_identidad)
);

CREATE TABLE Club_CorreoVinculado (
    id_club INT,
    correo VARCHAR(255),
    PRIMARY KEY (id_club, correo),
    FOREIGN KEY (id_club) REFERENCES Club(id_club)
);
