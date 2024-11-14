-- CREACIÓN DE LA BASE DE DATOS
DROP DATABASE IF EXISTS Productos;
CREATE DATABASE Productos;
USE Productos;

-- CREACIÓN DE LAS TABLAS
CREATE TABLE productos(
id_producto 				INT AUTO_INCREMENT PRIMARY KEY ,
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
id_pedido					INT AUTO_INCREMENT  PRIMARY KEY,
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
VALUES 	('Prod1',19.99,100),
		('Prod2',15.55,101),
        ('Prod3',19.99,100),
		('Prod4',15.55,101),
        ('Prod5',19.99,100),
        ('Prod6',19.99,100),
        ('Prod7',19.99,100),
        ('Prod8',19.99,100),
        ('Prod9',19.99,100),
        ('Prod10',19.99,100);

INSERT INTO usuarios (nombre,email,ano_nacimiento)
VALUES 	('nombre1','email1',2000),
		('nombre2','email2',2001),
        ('nombre3','email3',2002),
        ('nombre4','email4',2003);

INSERT INTO pedidos (id_usuario,fecha_pedido)
VALUES 	(1,CURDATE()),
		(2,CURDATE()),
        (3,CURDATE()),
        (4,CURDATE()),
        (1,CURDATE()),
        (1,CURDATE()),
		(2,CURDATE()),
        (3,CURDATE()),
        (4,CURDATE()),
        (1,CURDATE()),
        (1,CURDATE()),
		(2,CURDATE()),
        (3,CURDATE()),
        (4,CURDATE()),
        (1,CURDATE());

INSERT INTO pedidos_productos (id_pedido, id_producto, cantidad)
VALUES 	(1,1,12),
		(1,4,2),
        (1,7,1),
        (2,1,1),
        (2,8,25),
        (3,1,12),
		(3,4,2),
        (3,7,1),
        (3,9,1),
        (3,8,25),
        (4,1,12),
		(4,4,2),
        (4,7,1),
        (4,9,1),
        (4,8,25);


SELECT id_usuario, COUNT(*) AS pedidos
FROM pedidos
GROUP BY id_usuario;


