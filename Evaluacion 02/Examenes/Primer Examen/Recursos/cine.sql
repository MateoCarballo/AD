DROP DATABASE IF EXISTS Cine;
CREATE DATABASE Cine;
USE Cine;

-- Tabla Actores con dos atributos adicionales
CREATE TABLE actores (
    id_actor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    nacionalidad VARCHAR(100) NOT NULL
);

-- Tabla Películas con dos atributos adicionales
CREATE TABLE peliculas (
    id_pelicula INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    año_estreno YEAR NOT NULL,
    genero VARCHAR(50) NOT NULL
);

-- Relación muchos a muchos sin atributos (Actores y Películas)
CREATE TABLE actuan (
    actor_id INT,
    pelicula_id INT,
    PRIMARY KEY (actor_id, pelicula_id),
    FOREIGN KEY (actor_id) REFERENCES actores(id_actor),
    FOREIGN KEY (pelicula_id) REFERENCES peliculas(id_pelicula)
);

-- Tabla Salas de cine
CREATE TABLE salas (
    id_sala INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    capacidad INT NOT NULL
);

-- Relación muchos a muchos con atributos (Películas y Salas)
CREATE TABLE proyecciones (
    pelicula_id INT,
    sala_id INT,
    fecha DATE NOT NULL,
    horario TIME NOT NULL,
    PRIMARY KEY (pelicula_id, sala_id, fecha, horario),
    FOREIGN KEY (pelicula_id) REFERENCES peliculas(id_pelicula),
    FOREIGN KEY (sala_id) REFERENCES salas(id_sala)
);

-- Nueva Relación 1 a 1: Películas y Premios
CREATE TABLE premios (
    id_premio INT AUTO_INCREMENT PRIMARY KEY,
    pelicula_id INT UNIQUE,
    nombre_premio VARCHAR(100) NOT NULL,
    año_premio YEAR NOT NULL,
    FOREIGN KEY (pelicula_id) REFERENCES peliculas(id_pelicula)
);

-- Insertar datos en la tabla Actores
INSERT INTO actores (nombre, fecha_nacimiento, nacionalidad) VALUES 
('Leonardo DiCaprio', '1974-11-11', 'Estadounidense'),
('Scarlett Johansson', '1984-11-22', 'Estadounidense'),
('Tom Hanks', '1956-07-09', 'Estadounidense'),
('Penélope Cruz', '1974-04-28', 'Española'),
('Brad Pitt', '1963-12-18', 'Estadounidense'),
('Meryl Streep', '1949-06-22', 'Estadounidense'),
('Robert De Niro', '1943-08-17', 'Estadounidense'),
('Natalie Portman', '1981-06-09', 'Israelí-Estadounidense'),
('Joaquin Phoenix', '1974-10-28', 'Estadounidense'),
('Denzel Washington', '1954-12-28', 'Estadounidense'),
('Anne Hathaway', '1982-11-12', 'Estadounidense'),
('Christian Bale', '1974-01-30', 'Británico'),
('Morgan Freeman', '1937-06-01', 'Estadounidense'),
('Charlize Theron', '1975-08-07', 'Sudafricana'),
('Johnny Depp', '1963-06-09', 'Estadounidense');

-- Insertar datos en la tabla Películas
INSERT INTO peliculas (titulo, año_estreno, genero) VALUES 
('Inception', 2010, 'Ciencia Ficción'),
('Avengers: Endgame', 2019, 'Acción'),
('Forrest Gump', 1994, 'Drama'),
('Black Swan', 2010, 'Thriller'),
('Fight Club', 1999, 'Drama'),
('The Dark Knight', 2008, 'Acción'),
('The Revenant', 2015, 'Aventura'),
('Titanic', 1997, 'Romance'),
('The Godfather', 1972, 'Crimen'),
('Gladiator', 2000, 'Épico'),
('Interstellar', 2014, 'Ciencia Ficción'),
('The Devil Wears Prada', 2006, 'Comedia'),
('Mad Max: Fury Road', 2015, 'Acción'),
('The Departed', 2006, 'Thriller'),
('Joker', 2019, 'Drama');

-- Insertar relaciones en la tabla Actuan (Actores y Películas)
INSERT INTO actuan (actor_id, pelicula_id) VALUES 
(1, 1), -- Leonardo DiCaprio en Inception
(2, 2), -- Scarlett Johansson en Avengers: Endgame
(3, 3), -- Tom Hanks en Forrest Gump
(4, 4), -- Penélope Cruz en Black Swan
(5, 4), -- Brad Pitt en Black Swan
(5, 5), -- Brad Pitt en Fight Club
(6, 7), -- Meryl Streep en The Devil Wears Prada
(7, 9), -- Robert De Niro en The Godfather
(8, 4), -- Natalie Portman en Black Swan
(9, 10), -- Joaquin Phoenix en Joker
(10, 5), -- Denzel Washington en Gladiator
(11, 7), -- Anne Hathaway en The Devil Wears Prada
(12, 6), -- Christian Bale en The Dark Knight
(13, 8), -- Morgan Freeman en The Dark Knight
(14, 9), -- Charlize Theron en Mad Max: Fury Road
(15, 3); -- Johnny Depp en Forrest Gump

-- Insertar datos en la tabla Salas de cine
INSERT INTO salas (nombre, capacidad) VALUES 
('Sala IMAX', 200),
('Sala VIP', 50),
('Sala 3D', 150),
('Sala Clásica', 100),
('Sala Premiere', 250),
('Sala Dolby Atmos', 180),
('Sala 4DX', 220),
('Sala Arte', 80),
('Sala Retro', 120),
('Sala Cineclub', 90),
('Sala Open Air', 300),
('Sala Familiar', 130),
('Sala VIP Plus', 60),
('Sala Independiente', 110),
('Sala Experimental', 70);

-- Insertar proyecciones (relación muchos a muchos con atributos) 
INSERT INTO proyecciones (pelicula_id, sala_id, fecha, horario) VALUES 
(1, 1, '2025-02-15', '18:00:00'), -- Inception en Sala IMAX
(2, 2, '2025-02-16', '20:00:00'), -- Avengers: Endgame en Sala VIP
(3, 3, '2025-02-17', '17:30:00'), -- Forrest Gump en Sala 3D
(4, 4, '2025-02-18', '19:45:00'), -- Black Swan en Sala Clásica
(5, 5, '2025-02-19', '21:00:00'), -- Fight Club en Sala Premiere
(6, 6, '2025-02-20', '19:00:00'), -- The Dark Knight en Sala Dolby Atmos
(7, 7, '2025-02-21', '18:30:00'), -- The Revenant en Sala 4DX
(8, 8, '2025-02-22', '17:45:00'), -- Titanic en Sala Arte
(9, 9, '2025-02-23', '20:15:00'), -- The Godfather en Sala Retro
(10, 10, '2025-02-24', '21:30:00'), -- Gladiator en Sala Cineclub
(11, 1, '2025-02-25', '16:00:00'), -- Interstellar en Sala IMAX
(12, 2, '2025-02-26', '18:45:00'), -- The Devil Wears Prada en Sala VIP
(13, 3, '2025-02-27', '19:30:00'), -- Mad Max: Fury Road en Sala 3D
(14, 4, '2025-02-28', '22:00:00'), -- The Departed en Sala Clásica
(15, 5, '2025-02-24', '20:50:00'); -- Joker en Sala Premiere

-- Insertar datos en la tabla Premios
INSERT INTO premios (pelicula_id, nombre_premio, año_premio) VALUES 
(1, 'Oscar a Mejor Película', 2011),
(2, 'Premio Globo de Oro', 2020),
(3, 'Oscar a Mejor Actor', 1995),
(4, 'Premio BAFTA', 2011),
(5, 'Premio SAG', 2000),
(6, 'Oscar a Mejor Actor', 2009),
(7, 'Premio BAFTA', 2016),
(8, 'Oscar a Mejor Película', 1998),
(9, 'Oscar a Mejor Película', 1973),
(10, 'Oscar a Mejor Película', 2001),
(11, 'Oscar a Mejores Efectos Visuales', 2015),
(12, 'Premio Globo de Oro', 2007),
(13, 'Oscar a Mejor Dirección', 2016),
(14, 'Oscar a Mejor Película', 2007),
(15, 'Oscar a Mejor Actor', 2020);
 
