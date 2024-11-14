/*
Estas dos querys se deben ejecutar una a una o si no no las ejecutará
DROP DATABASE IF EXISTS Almacenes;
CREATE DATABASE Almacenes;
*/

CREATE TABLE almacenes(
	id_almacen				SERIAL PRIMARY KEY,
	nombre_almacen			VARCHAR(50),
	ubicacion				VARCHAR(50)
);

CREATE TABLE categorias(
	id_categoria			SERIAL PRIMARY KEY,
	nombre_categoria		VARCHAR(50)
);

CREATE TYPE type_contacto AS (
    nombre_contacto 		VARCHAR(50),
    nif 					VARCHAR(15),
    telefono 				VARCHAR(15),
    email 					VARCHAR(75)
);

CREATE TABLE proveedores(
	id_proveedor			SERIAL PRIMARY KEY,
	nombre_proveedor		VARCHAR(50),
	contacto				type_contacto
);

CREATE TABLE productos(
id_producto					SERIAL PRIMARY KEY,
id_proveedor				INT,
id_categoria				INT
);

ALTER TABLE productos
ADD CONSTRAINT FK_productos_proveedor FOREIGN KEY(id_proveedor)
REFERENCES proveedores(id_proveedor)
on delete cascade;

ALTER TABLE productos
ADD CONSTRAINT FK_productos_categorias FOREIGN KEY(id_categoria)
REFERENCES categorias(id_categoria)
on delete cascade;

CREATE TABLE almacenes_productos(
id_almacen				INT NOT NULL,
id_producto				INT NOT NULL,
cantidad				INT	NOT NULL,
PRIMARY KEY (id_almacen, id_producto),
FOREIGN KEY (id_almacen) 	REFERENCES almacenes(id_almacen),
FOREIGN KEY (id_producto)	REFERENCES productos(id_producto)	

);

INSERT INTO almacenes (nombre_almacen, ubicacion) 
VALUES ('nombre_prueba1', 'direccion_prueba1');

-- INSERT PARA LOS ALMACENES 
INSERT INTO almacenes (nombre_almacen, ubicacion)
VALUES
    ('Tech Galicia - Almacén A Coruña', 'Avenida de los Castros, 123, A Coruña'),
    ('Tech Galicia - Almacén Santiago', 'Rúa da República Argentina, 45, Santiago de Compostela'),
    ('Tech Galicia - Almacén Vigo', 'Avenida de Madrid, 22, Vigo'),
    ('Tech Galicia - Almacén Ourense', 'Rúa do Progreso, 67, Ourense');

--INSERT PARA LAS CATEGORIAS
INSERT INTO categorias (nombre_categoria)
VALUES 
    ('Computadoras de escritorio'),
    ('Laptops'),
    ('Monitores'),
    ('Discos duros'),
    ('Impresoras'),
    ('Accesorios');

-- INSERT PARA LOS PROVEEDORES
INSERT INTO proveedores (nombre_proveedor, contacto)
VALUES
    ('Lenovo', ('Carlos Rodríguez', '23456789A', '555-3456', 'c.rodriguez@lenovo.com')),
    ('HP', ('Ana García', '34567890A', '555-4567', 'ana.garcia@hp.com')),
    ('Seagate', ('Luis Pérez', '45678901A', '555-5678', 'luis.perez@seagate.com')),
    ('Samsung', ('Marta López', '56789012A', '555-6789', 'marta.lopez@samsung.com')),
    ('Canon', ('José Álvarez', '67890123A', '555-7890', 'jose.alvarez@canon.com'));

-- INSERT PARA LOS PRODUCTOS
-- Productos informáticos relacionados con las categorías previamente insertadas
INSERT INTO productos (id_proveedor, id_categoria)
VALUES
    (1, 1),  -- Lenovo: Computadora de escritorio
    (1, 2),  -- Lenovo: Laptop
    (2, 1),  -- HP: Computadora de escritorio
    (2, 2),  -- HP: Laptop
    (3, 4),  -- Seagate: Disco duro HDD
    (4, 4),  -- Samsung: Disco duro SSD
    (5, 3),  -- Canon: Impresora láser
    (4, 3),  -- Samsung: Monitor 24"
    (2, 3),  -- HP: Monitor 27"
    (1, 5);  -- Lenovo: Accesorio teclado mecánico


-- INSERT PARA LA RELACION ALMACENES PRODUCTO

-- Relación de productos con almacenes y sus cantidades
INSERT INTO almacenes_productos (id_almacen, id_producto, cantidad)
VALUES
    (1, 1, 100),  -- Almacén A Coruña: Lenovo PC de escritorio, 100 unidades
    (1, 2, 50),   -- Almacén A Coruña: Lenovo Laptop, 50 unidades
    (2, 3, 120),  -- Almacén Santiago: HP PC de escritorio, 120 unidades
    (2, 4, 60),   -- Almacén Santiago: HP Laptop, 60 unidades
    (3, 5, 200),  -- Almacén Vigo: Seagate HDD, 200 unidades
    (3, 6, 150),  -- Almacén Vigo: Samsung SSD, 150 unidades
    (4, 7, 80),   -- Almacén Ourense: Canon Impresora, 80 unidades
    (4, 8, 110),  -- Almacén Ourense: Samsung Monitor 24", 110 unidades
    (1, 9, 30);   -- Almacén A Coruña: Lenovo Teclado mecánico, 30 unidades
