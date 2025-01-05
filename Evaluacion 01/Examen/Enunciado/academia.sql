-- Tipo de datos compuesto para la información de contacto del profesor
CREATE TYPE Contacto AS (
    telefono VARCHAR(15),
    direccion VARCHAR(255),
    email VARCHAR(100)
);

-- Tipo de datos compuesto para la información del estudiante
CREATE TYPE EstudianteInfo AS (
    nombre VARCHAR(100),
    direccion VARCHAR(255),
    email VARCHAR(100),
    edad INTEGER
);

-- Tipo de datos compuesto para información adicional del curso
CREATE TYPE CursoInfo AS (
    nivel VARCHAR(20),
    duracion_semanas INTEGER,
    horario TEXT
);

-- Tabla de profesores
CREATE TABLE Profesores (
    profesor_id SERIAL PRIMARY KEY,
    nombre VARCHAR(100),
    especialidad VARCHAR(50),
    info_contacto Contacto
);

-- Tabla de cursos
CREATE TABLE Cursos (
    curso_id SERIAL PRIMARY KEY,
    nombre_curso VARCHAR(100),
    descripcion TEXT,
    precio DECIMAL(10, 2),
    profesor_id INT REFERENCES Profesores(profesor_id),
    info_adicional CursoInfo
);

-- Tabla de estudiantes
CREATE TABLE Estudiantes (
    estudiante_id SERIAL PRIMARY KEY,
    info_estudiante EstudianteInfo
);

-- Tabla de inscripciones (relación muchos a muchos entre Curso y Estudiante)
CREATE TABLE Inscripciones (
    inscripcion_id SERIAL PRIMARY KEY,
    curso_id INT REFERENCES Cursos(curso_id),
    estudiante_id INT REFERENCES Estudiantes(estudiante_id),
    fecha_inscripcion DATE DEFAULT CURRENT_DATE
);

-- Tabla de materiales de curso (relación 1 a 1 con Cursos)
CREATE TABLE MaterialCurso (
    material_id SERIAL PRIMARY KEY,
    curso_id INT UNIQUE REFERENCES Cursos(curso_id),
    contenido TEXT,
    tipo_material VARCHAR(50)
);

-- Insertar profesores
INSERT INTO Profesores (nombre, especialidad, info_contacto)
VALUES
  ('Ana Rodríguez', 'Español', ROW('123456789', 'Av. 1, Ciudad A', 'ana@academia.com')),
  ('Carlos López', 'Francés', ROW('987654321', 'Av. 2, Ciudad B', 'carlos@academia.com'));

-- Insertar cursos
INSERT INTO Cursos (nombre_curso, descripcion, precio, profesor_id, info_adicional)
VALUES
  ('Español Básico', 'Curso introductorio al español', 150.00, 1, ROW('Básico', 12, 'Lunes y Miércoles, 10:00-12:00')),
  ('Francés Intermedio', 'Curso para mejorar el francés hablado y escrito', 200.00, 2, ROW('Intermedio', 10, 'Martes y Jueves, 14:00-16:00'));

-- Insertar estudiantes
INSERT INTO Estudiantes (info_estudiante)
VALUES
  (ROW('Juan Pérez', 'Calle A, Ciudad X', 'juan@correo.com', 22)),
  (ROW('Maria Gomez', 'Calle B, Ciudad Y', 'maria@correo.com', 30));

-- Insertar inscripciones (relación muchos a muchos)
INSERT INTO Inscripciones (curso_id, estudiante_id, fecha_inscripcion)
VALUES
  (1, 1, '2023-01-15'),
  (2, 1, '2023-02-10'),
  (1, 2, '2023-01-20');

-- Insertar materiales de curso (relación 1 a 1)
INSERT INTO MaterialCurso (curso_id, contenido, tipo_material)
VALUES
  (1, 'Libro de gramática básica', 'Libro'),
  (2, 'Guía de conversación avanzada', 'Guía');

-- Insertar nuevos profesores
INSERT INTO Profesores (nombre, especialidad, info_contacto)
VALUES
  ('Beatriz Silva', 'Inglés', ROW('111222333', 'Av. 3, Ciudad C', 'beatriz@academia.com')),
  ('David Romero', 'Alemán', ROW('444555666', 'Av. 4, Ciudad D', 'david@academia.com')),
  ('Elena Morales', 'Chino', ROW('777888999', 'Av. 5, Ciudad E', 'elena@academia.com'));

-- Insertar nuevos cursos
INSERT INTO Cursos (nombre_curso, descripcion, precio, profesor_id, info_adicional)
VALUES
  ('Inglés Básico', 'Curso de introducción al idioma inglés', 180.00, 3, ROW('Básico', 10, 'Lunes y Miércoles, 09:00-11:00')),
  ('Alemán Avanzado', 'Curso avanzado para mejorar el dominio del alemán', 220.00, 4, ROW('Avanzado', 8, 'Martes y Jueves, 13:00-15:00')),
  ('Chino Mandarín Intermedio', 'Curso intermedio de chino mandarín', 250.00, 5, ROW('Intermedio', 12, 'Viernes, 10:00-13:00'));

-- Insertar nuevos estudiantes
INSERT INTO Estudiantes (info_estudiante)
VALUES
  (ROW('Carlos Rivera', 'Calle C, Ciudad Z', 'carlos@correo.com', 28)),
  (ROW('Lucía Torres', 'Calle D, Ciudad Y', 'lucia@correo.com', 35)),
  (ROW('Fernando Martínez', 'Calle E, Ciudad X', 'fernando@correo.com', 40)),
  (ROW('Raquel García', 'Calle F, Ciudad W', 'raquel@correo.com', 27));

-- Insertar nuevas inscripciones
INSERT INTO Inscripciones (curso_id, estudiante_id, fecha_inscripcion)
VALUES
  (3, 3, '2023-03-05'), -- Carlos en Inglés Básico
  (4, 4, '2023-03-08'), -- Lucía en Alemán Avanzado
  (5, 5, '2023-04-01'), -- Fernando en Chino Mandarín Intermedio
  (3, 6, '2023-04-02'), -- Raquel en Inglés Básico
  (1, 3, '2023-04-10'), -- Carlos en Español Básico
  (2, 4, '2023-05-15'); -- Lucía en Francés Intermedio

-- Insertar materiales de curso adicionales
INSERT INTO MaterialCurso (curso_id, contenido, tipo_material)
VALUES
  (3, 'Manual de inglés para principiantes', 'Libro'),
  (4, 'Gramática avanzada de alemán', 'Libro'),
  (5, 'Libro de caracteres chinos intermedios', 'Libro');
