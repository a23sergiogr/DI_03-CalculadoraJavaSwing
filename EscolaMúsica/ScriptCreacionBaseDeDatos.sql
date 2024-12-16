drop database EscuelaMusica

CREATE DATABASE EscuelaMusica;
GO

USE EscuelaMusica;
GO


CREATE TABLE Persona (
    id_persona INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(50) NOT NULL,
    DNI CHAR(9) NOT NULL UNIQUE,
    apellidoUno NVARCHAR(50) NOT NULL,
    apellidoDos NVARCHAR(50) NULL
);
GO

CREATE TABLE Telefono (
    id_persona INT NOT NULL,
    telefono CHAR(9) NOT NULL,
    PRIMARY KEY (id_persona, telefono),
    FOREIGN KEY (id_persona) REFERENCES Persona(id_persona) ON DELETE CASCADE
);
GO

CREATE TABLE Estudiante (
    id_persona INT PRIMARY KEY,
    fecha_nac DATE NULL,
    direccion NVARCHAR(100) NOT NULL,
    num_matricula INT NOT NULL,
    FOREIGN KEY (id_persona) REFERENCES Persona(id_persona) ON DELETE CASCADE
);
GO

CREATE TABLE Profesor (
    id_persona INT PRIMARY KEY,
    especialidad NVARCHAR(50) NULL,
    anhos_experiencia INT NULL,
    FOREIGN KEY (id_persona) REFERENCES Persona(id_persona) ON DELETE CASCADE
);
GO

CREATE TABLE Clase (
    cod_clase INT IDENTITY(1,1) PRIMARY KEY,
    fecha_clase DATE NOT NULL,
    hora_clase TIME NOT NULL,
    instrumento NVARCHAR(50) NOT NULL
);
GO

CREATE TABLE Asiste (
    id_persona INT NOT NULL,
    cod_clase INT NOT NULL,
    PRIMARY KEY (id_persona, cod_clase),
    FOREIGN KEY (id_persona) REFERENCES Estudiante(id_persona) ON DELETE CASCADE,
    FOREIGN KEY (cod_clase) REFERENCES Clase(cod_clase) ON DELETE CASCADE
);
GO

CREATE TABLE Imparte (
    id_persona INT NOT NULL,
    cod_clase INT NOT NULL,
    PRIMARY KEY (id_persona, cod_clase),
    FOREIGN KEY (id_persona) REFERENCES Profesor(id_persona) ON DELETE CASCADE,
    FOREIGN KEY (cod_clase) REFERENCES Clase(cod_clase) ON DELETE CASCADE
);
GO

CREATE TABLE Evalua (
    id_profesor INT NOT NULL,
    id_estudiante INT NOT NULL,
    tipo NVARCHAR(20) NOT NULL CHECK (tipo IN ('Teórico', 'Práctico')),
    nota INT NULL,
    fecha_evaluacion DATE NOT NULL,
    PRIMARY KEY (id_profesor, id_estudiante, tipo, fecha_evaluacion),
    FOREIGN KEY (id_profesor) REFERENCES Profesor(id_persona) ON DELETE NO ACTION,
    FOREIGN KEY (id_estudiante) REFERENCES Estudiante(id_persona) ON DELETE NO ACTION
);
GO
