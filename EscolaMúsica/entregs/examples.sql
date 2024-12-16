INSERT INTO Persona (nombre, DNI, apellidoUno, apellidoDos)
VALUES 
('Juan', '12345678A', 'Pérez', 'Gómez'),
('Ana', '23456789B', 'Martínez', 'López'),
('Carlos', '34567890C', 'García', 'Fernández'),
('Laura', '45678901D', 'Hernández', 'Ruiz');
GO

INSERT INTO Telefono (id_persona, telefono)
VALUES 
(1, '600123456'),
(1, '600654321'),
(2, '601123456'),
(3, '602123456');
GO

INSERT INTO Estudiante (id_persona, fecha_nac, direccion, num_matricula)
VALUES 
(1, '2000-01-15', 'Calle Falsa 123', 1001),
(2, '2001-05-20', 'Avenida Siempreviva 456', 1002);
GO

INSERT INTO Profesor (id_persona, especialidad, anhos_experiencia)
VALUES 
(3, 'Matemáticas', 10),
(4, 'Física', 8);
GO

INSERT INTO Clase (fecha_clase, hora_clase, instrumento)
VALUES 
('2024-11-20', '10:00', 'Guitarra'),
('2024-11-21', '14:00', 'Piano');
GO

INSERT INTO Asiste (id_persona, cod_clase)
VALUES 
(1, 1),
(2, 2);
GO

INSERT INTO Imparte (id_persona, cod_clase)
VALUES 
(3, 1),
(4, 2);
GO

INSERT INTO Evalua (id_profesor, id_estudiante, tipo, nota, fecha_evaluacion)
VALUES 
(3, 1, 'Teórico', 85, '2024-11-20'),
(3, 2, 'Práctico', 90, '2024-11-21'),
(4, 1, 'Práctico', 88, '2024-11-22');
GO

select * from Asiste
select * from Clase
select * from Estudiante
select * from Evalua
select * from Imparte
select * from Profesor
select * from Persona
select * from Telefono



