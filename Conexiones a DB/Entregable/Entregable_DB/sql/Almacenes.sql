-- Crear base de datos (si es necesario)
DROP DATABASE IF EXISTS Almacenes;
CREATE DATABASE Almacenes;

-- Cambiar a la base de datos
\c Almacenes;

-- Crear las tablas sin usar SERIAL para el id_producto
CREATE TABLE almacenes (
    id_almacen       SERIAL PRIMARY KEY,  
    nombre_almacen   VARCHAR(50),
    ubicacion        VARCHAR(50)
);

CREATE TABLE categorias (
    id_categoria     SERIAL PRIMARY KEY,  
    nombre_categoria VARCHAR(50)
);

-- Crear tipo para contacto
CREATE TYPE type_contacto AS (
    nombre_contacto  VARCHAR(50),
    nif              VARCHAR(15),
    telefono         VARCHAR(15),
    email            VARCHAR(75)
);

CREATE TABLE proveedores (
    id_proveedor     SERIAL PRIMARY KEY,  
    nombre_proveedor VARCHAR(50),
    contacto         type_contacto
);

CREATE TABLE productos (
    id_producto      INTEGER PRIMARY KEY,  -- No es serial porque este ID viene dado de la otra DB MySQL
    id_proveedor     INTEGER,
    id_categoria     INTEGER,
    FOREIGN KEY (id_proveedor) REFERENCES proveedores(id_proveedor) ON DELETE CASCADE,
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria) ON DELETE CASCADE
);

CREATE TABLE almacenes_productos (
    id_almacen       INTEGER NOT NULL,
    id_producto      INTEGER NOT NULL,
    cantidad         INTEGER NOT NULL,
    PRIMARY KEY (id_almacen, id_producto),
    FOREIGN KEY (id_almacen) REFERENCES almacenes(id_almacen),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

INSERT INTO almacenes (nombre_almacen, ubicacion)
VALUES 
    ('Tech Galicia - Almacén A Coruña', 'Avenida de los Castros, 123, A Coruña'),
    ('Tech Galicia - Almacén Santiago', 'Rúa da República Argentina, 45, Santiago de Compostela'),
    ('Tech Galicia - Almacén Vigo', 'Avenida de Madrid, 22, Vigo'),
    ('Tech Galicia - Almacén Ourense', 'Rúa do Progreso, 67, Ourense');

INSERT INTO categorias (nombre_categoria)
VALUES 
    ('PC de sobremesa'),
    ('Laptop'),
    ('Monitor'),
    ('Disco duro'),
    ('Impresora'),
    ('Accesorio');

INSERT INTO proveedores (nombre_proveedor, contacto)
VALUES
    ('Lenovo', ('Carlos Rodríguez', '23456789A', '555-3456', 'c.rodriguez@lenovo.com')),
    ('HP', ('Ana García', '34567890A', '555-4567', 'ana.garcia@hp.com')),
    ('Seagate', ('Luis Pérez', '45678901A', '555-5678', 'luis.perez@seagate.com')),
    ('Samsung', ('Marta López', '56789012A', '555-6789', 'marta.lopez@samsung.com')),
    ('Canon', ('José Álvarez', '67890123A', '555-7890', 'jose.alvarez@canon.com'));

INSERT INTO productos (id_producto, id_proveedor, id_categoria)
VALUES 
    (1, 1, 1),  -- Lenovo: PC de sobremesa
    (2, 1, 2),  -- Lenovo: Laptop
    (3, 2, 1),  -- HP: PC de sobremesa
    (4, 2, 2),  -- HP: Laptop
    (5, 3, 4),  -- Seagate: Disco duro HDD
    (6, 4, 4),  -- Samsung: Disco duro SSD
    (7, 5, 3),  -- Canon: Impresora
    (8, 4, 3),  -- Samsung: Monitor 24"
    (9, 2, 3),  -- HP: Monitor 27"
    (10, 1, 6); -- Lenovo: Accesorio teclado mecánico

INSERT INTO almacenes_productos (id_almacen, id_producto, cantidad)
VALUES
    (1, 1, 100),  -- Almacén A Coruña: 100 unidades de PC de sobremesa
    (1, 2, 50),   -- Almacén A Coruña: 50 unidades de Laptop Lenovo
    (2, 3, 120),  -- Almacén Santiago: 120 unidades de HP PC de sobremesa
    (2, 4, 60),   -- Almacén Santiago: 60 unidades de HP Laptop
    (3, 5, 200),  -- Almacén Vigo: 200 unidades de Seagate HDD
    (3, 6, 150),  -- Almacén Vigo: 150 unidades de Samsung SSD
    (4, 7, 80),   -- Almacén Ourense: 80 unidades de Canon Impresora
    (4, 8, 110),  -- Almacén Ourense: 110 unidades de Samsung Monitor 24"
    (1, 9, 30);   -- Almacén A Coruña: 30 unidades de Lenovo Teclado mecánico


