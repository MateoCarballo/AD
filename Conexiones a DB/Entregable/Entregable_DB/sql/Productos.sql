-- CREACIÓN DE LA BASE DE DATOS
DROP DATABASE IF EXISTS Productos;
CREATE DATABASE Productos;
USE Productos;

-- CREACIÓN DE LAS TABLAS
CREATE TABLE productos(
id_producto                 INT AUTO_INCREMENT PRIMARY KEY ,
nombre_producto             VARCHAR(50) NOT NULL,
precio                      DECIMAL (10,2),
stock                       INT
);

CREATE TABLE usuarios(
id_usuario                  INT AUTO_INCREMENT PRIMARY KEY,
nombre                      VARCHAR(50) NOT NULL,
email                       VARCHAR(75) NOT NULL,
ano_nacimiento              INT         NOT NULL
);

CREATE TABLE pedidos (
id_pedido                   INT AUTO_INCREMENT  PRIMARY KEY,
id_usuario                  INT,
fecha_pedido                DATE,
FOREIGN KEY (id_usuario)    REFERENCES usuarios(id_usuario)
);

CREATE TABLE pedidos_productos (
id_pedido                   INT,
id_producto                 INT,
cantidad                    INT,
PRIMARY KEY (id_pedido, id_producto),
FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido),
FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

-- Insertar productos (en MySQL, sin especificar el ID porque se generará automáticamente)
INSERT INTO productos (nombre_producto, precio, stock)
VALUES
    ('PC de sobremesa', 699.99, 50),
    ('Laptop Lenovo', 899.99, 75),
    ('Laptop HP', 799.99, 65),
    ('Seagate Disco Duro HDD', 59.99, 120),
    ('Samsung Disco Duro SSD', 129.99, 95),
    ('Impresora Canon', 119.99, 30),
    ('Monitor Samsung 24"', 199.99, 60),
    ('Monitor HP 27"', 249.99, 50),
    ('Teclado mecánico Lenovo', 89.99, 200),
    ('Mouse inalámbrico Logitech', 29.99, 150);

-- Insertar usuarios
INSERT INTO usuarios (nombre, email, ano_nacimiento)
VALUES
    ('Carlos Rodríguez', 'c.rodriguez@lenovo.com', 1985),
    ('Ana García', 'ana.garcia@hp.com', 1990),
    ('Luis Pérez', 'luis.perez@seagate.com', 1980),
    ('Marta López', 'marta.lopez@samsung.com', 1987);

-- Insertar pedidos
INSERT INTO pedidos (id_usuario, fecha_pedido)
VALUES
    (1, CURDATE()),
    (2, CURDATE()),
    (3, CURDATE()),
    (4, CURDATE());

-- Insertar productos en pedidos_productos
-- Asumiendo que los ID generados por MySQL para los productos comenzarán desde 1 y los usuarios desde 1 también
-- Asignamos productos a pedidos con cantidades arbitrarias
INSERT INTO pedidos_productos (id_pedido, id_producto, cantidad)
VALUES
    (1, 1, 2),  -- Pedido 1: 2 unidades de PC de sobremesa
    (1, 2, 1),  -- Pedido 1: 1 unidad de Laptop Lenovo
    (2, 3, 1),  -- Pedido 2: 1 unidad de Laptop HP
    (2, 4, 3),  -- Pedido 2: 3 unidades de Seagate Disco Duro HDD
    (3, 5, 1),  -- Pedido 3: 1 unidad de Samsung Disco Duro SSD
    (3, 6, 2),  -- Pedido 3: 2 unidades de Impresora Canon
    (4, 7, 1);  -- Pedido 4: 1 unidad de Monitor Samsung 24"
