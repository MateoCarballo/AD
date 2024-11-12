-- CREACIÓN DE LA BASE DE DATOS
DROP DATABASE IF EXISTS Almacen;
CREATE DATABASE Almacen;
USE Almacen;

-- CREACIÓN DE LAS TABLAS
CREATE TABLE productos(
id_producto 				INT AUTO_INCREMENT PRIMARY KEY , -- NO introduzco que sea autoincrement porque las primary keys ya lo son por defecto
nombre_producto				VARCHAR(50) NOT NULL,
precio						DECIMAL (10,2),
stock						INT
);

CREATE TABLE usuarios(
id_usuario					INT AUTO_INCREMENT PRIMARY KEY,
nombre						VARCHAR(50) NOT NULL,
email						VARCHAR(75) NOT NULL,
ano_nacimiento				INT			NOT NULL
);

CREATE TABLE pedidos (
id_pedido					INT AUTO_INCREMENT PRIMARY KEY,
id_usuario					INT,
fecha_pedido				DATE,
FOREIGN KEY (id_usuario)	REFERENCES usuarios(id_usuario)
);

CREATE TABLE pedidos_productos (
id_pedido					INT,
id_producto					INT,
cantidad					INT,
PRIMARY KEY (id_pedido, id_producto),
FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido),
FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

INSERT INTO productos (nombre_producto,precio,stock)
VALUES ('nombre',19.99,100);

