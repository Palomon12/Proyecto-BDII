CREATE DATABASE FerreyrosG6;
USE FerreyrosG6;
-- tabla cliente

CREATE TABLE Contacto_Cliente(
	ID_Contacto INT primary key auto_increment,
	Telefono_Cli char(9),
    Correo_Cli varchar(30)
);
CREATE TABLE Cliente(
	RUC_Cli varchar(11) primary key,
    Nombre_Cli varchar(20),
    Sector_Cli varchar(30),
    ID_Contacto int,
    foreign key(ID_Contacto) references Contacto_Cliente(ID_Contacto)
);
--
-- tabla proveedor
CREATE TABLE Proveedor(
	ID_Proveedor char(8) primary key,
    Nombre_Marca_Pro varchar(20),
    Pais_Proveedor char(10),
    Linea_Producto_Proveedor varchar(50),
    AñosRelacion_Proveedor int
);
--
-- tabla colaborador
CREATE TABLE Colaborador(
	ID_Colaborador INT PRIMARY KEY,
    Nombre_Col VARCHAR(50),
    Departamento_ID INT,
	Cargo_Col char(30),
    Fecha_Contratacion_Col date,
    ID_Sucursal INT,
    FOREIGN KEY (Departamento_ID) REFERENCES Departamento(ID_Departamento),
    FOREIGN KEY (ID_Sucursal) REFERENCES Sucursal(ID_Sucursal)
);
ALTER TABLE Colaborador ADD COLUMN Sueldo_Col DECIMAL(8,2);

CREATE TABLE Departamento (
    ID_Departamento INT PRIMARY KEY AUTO_INCREMENT,
    Nombre_Departamento VARCHAR(30)
);
CREATE TABLE servicio (
    ID_Servicio INT PRIMARY KEY,
    Nombre_Serv VARCHAR(30),
    Descripcion_Serv VARCHAR(100),
    Tarifa_Base_Serv DECIMAL(6,2),
    Departamento_ID INT,
    ID_Colaborador INT,
    RUC_Cli VARCHAR(11),
    FOREIGN KEY (Departamento_ID) REFERENCES departamento(ID_Departamento),
    FOREIGN KEY (ID_Colaborador) REFERENCES colaborador(ID_Colaborador),
    FOREIGN KEY (RUC_Cli) REFERENCES cliente(RUC_Cli)
);
ALTER TABLE Servicio ADD COLUMN Costo_Total DECIMAL(6,2);
ALTER TABLE Servicio
MODIFY COLUMN Nombre_Serv VARCHAR(100);
ALTER TABLE Servicio
MODIFY COLUMN Tarifa_Base_Serv DECIMAL(10,2);


CREATE TABLE Sucursal (
    ID_Sucursal INT PRIMARY KEY,
    Nombre_Sucursal VARCHAR(30),
    Direccion_Suc VARCHAR(100),
    Ciudad_Suc varchar(30),
    Telefono_Suc CHAR(9)
);
-- Tabla Producto (relacionado con Proveedor y Tipo)
CREATE TABLE Producto (
    ID_Producto INT PRIMARY KEY,
    Nombre_Prod VARCHAR(30),
    Descripcion_Prod VARCHAR(100),
    Tipo_ID INT, 
    ID_Proveedor CHAR(8),
    Stock_Prod INT,
    FOREIGN KEY (Tipo_ID) REFERENCES Tipo_Producto(ID_Tipo),
    FOREIGN KEY (ID_Proveedor) REFERENCES Proveedor(ID_Proveedor)
);
ALTER TABLE Producto
ADD COLUMN Precio_Compra DECIMAL(10,2),
ADD COLUMN Precio_Alquiler DECIMAL(10,2);


CREATE TABLE Tipo_Producto (
    ID_Tipo INT PRIMARY KEY AUTO_INCREMENT,
    Nombre_Tipo VARCHAR(20)
);

CREATE TABLE Producto_Sucursal (
    ID_Producto INT,
    ID_Sucursal INT,
    Stock_Disponible INT,
    PRIMARY KEY (ID_Producto, ID_Sucursal),
    FOREIGN KEY (ID_Producto) REFERENCES Producto(ID_Producto),
    FOREIGN KEY (ID_Sucursal) REFERENCES Sucursal(ID_Sucursal)
);
	
CREATE TABLE Operaciones (
    ID_Operacion INT PRIMARY KEY AUTO_INCREMENT,
    Tipo_Operacion ENUM('Compra', 'Alquiler') NOT NULL,
    ID_Producto INT,
    ID_Servicio INT,
    RUC_Cli VARCHAR(11) NOT NULL,
    Fecha_Operacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Costo_Total DECIMAL(10,2) NOT NULL,
    
    FOREIGN KEY (ID_Producto) REFERENCES Producto(ID_Producto),
    FOREIGN KEY (ID_Servicio) REFERENCES Servicio(ID_Servicio),
    FOREIGN KEY (RUC_Cli) REFERENCES Cliente(RUC_Cli)
);

-- 1)TRIGER PARA que los servicios (alquiler o venta) se registren automáticamente en esa tabla vía trigger
DELIMITER $$

CREATE TRIGGER trg_insert_operacion_servicio
AFTER INSERT ON Servicio
FOR EACH ROW
BEGIN
  DECLARE tipo VARCHAR(20);

  -- Detectar tipo de operación por el nombre del servicio
  IF LOWER(NEW.Nombre_Serv) LIKE '%alquiler%' THEN
    SET tipo = 'Alquiler';
  ELSEIF LOWER(NEW.Nombre_Serv) LIKE '%venta%' THEN
    SET tipo = 'Compra';
  ELSE
    SET tipo = 'Otro'; -- No se insertará si no es compra o alquiler
  END IF;

  -- Solo insertamos si es 'Alquiler' o 'Compra'
  IF tipo IN ('Alquiler', 'Compra') THEN
    INSERT INTO Operaciones (Tipo_Operacion, ID_Producto, ID_Servicio, RUC_Cli, Costo_Total)
    VALUES (tipo, NULL, NEW.ID_Servicio, NEW.RUC_Cli, NEW.Tarifa_Base_Serv);
  END IF;
END$$

DELIMITER ;


-- Precarga de datos para la empresa:
INSERT INTO Contacto_Cliente (Telefono_Cli, Correo_Cli) VALUES
('987654321', 'minera1@ferreyros.com'),
('912345678', 'logistica@ferreyros.com'),
('987112233', 'admin@cliente.com'),
('923456789', 'ventas@mineria.com'),
('945678321', 'soporte@ferreyros.com');

INSERT INTO Cliente (RUC_Cli, Nombre_Cli, Sector_Cli, ID_Contacto) VALUES
('12345678901', 'Minera Andina', 'Minería', 1),
('10987654321', 'Logística Perú', 'Logística', 2),
('12312312312', 'Constructora Lima', 'Construcción', 3),
('98765432109', 'Ventas Norte', 'Comercial', 4),
('11122233344', 'Soporte S.A.C.', 'Tecnología', 5);

INSERT INTO Departamento (Nombre_Departamento) VALUES ('Logística');
INSERT INTO Departamento (Nombre_Departamento) VALUES ('Mantenimiento');
INSERT INTO Departamento (Nombre_Departamento) VALUES ('Recursos Humanos');
INSERT INTO Departamento (Nombre_Departamento) VALUES ('Ventas');
INSERT INTO Departamento (Nombre_Departamento) VALUES ('Finanzas');
INSERT INTO Departamento (Nombre_Departamento) VALUES ('Ingeniería');
INSERT INTO Departamento (Nombre_Departamento) VALUES ('Operaciones');
INSERT INTO Departamento (Nombre_Departamento) VALUES ('Proyectos');
INSERT INTO Departamento (Nombre_Departamento) VALUES ('Calidad');
INSERT INTO Departamento (Nombre_Departamento) VALUES ('Seguridad Industrial');

INSERT INTO Sucursal VALUES (1, 'Sucursal Lima', 'Av. Javier Prado 1234', 'Lima', '987654321');
INSERT INTO Sucursal VALUES (2, 'Sucursal Arequipa', 'Av. Ejército 543', 'Arequipa', '912345678');
INSERT INTO Sucursal VALUES (3, 'Sucursal Trujillo', 'Av. América Sur 1111', 'Trujillo', '923456789');
INSERT INTO Sucursal VALUES (4, 'Sucursal Piura', 'Av. Grau 321', 'Piura', '934567890');
INSERT INTO Sucursal VALUES (5, 'Sucursal Cusco', 'Av. El Sol 456', 'Cusco', '945678901');
INSERT INTO Sucursal VALUES (6, 'Sucursal Ica', 'Av. San Martín 789', 'Ica', '956789012');
INSERT INTO Sucursal VALUES (7, 'Sucursal Tacna', 'Av. Bolognesi 852', 'Tacna', '967890123');
INSERT INTO Sucursal VALUES (8, 'Sucursal Puno', 'Av. Simón Bolívar 444', 'Puno', '978901234');
INSERT INTO Sucursal VALUES (9, 'Sucursal Huancayo', 'Av. Ferreyros 300', 'Huancayo', '989012345');
INSERT INTO Sucursal VALUES (10, 'Sucursal Chimbote', 'Av. Anchoveta 200', 'Chimbote', '990123456');

INSERT INTO Tipo_Producto (Nombre_Tipo) VALUES ('Excavadora');
INSERT INTO Tipo_Producto (Nombre_Tipo) VALUES ('Cargador Frontal');
INSERT INTO Tipo_Producto (Nombre_Tipo) VALUES ('Camión Minero');
INSERT INTO Tipo_Producto (Nombre_Tipo) VALUES ('Bulldozer');
INSERT INTO Tipo_Producto (Nombre_Tipo) VALUES ('Retroexcavadora');
INSERT INTO Tipo_Producto (Nombre_Tipo) VALUES ('Grúa');
INSERT INTO Tipo_Producto (Nombre_Tipo) VALUES ('Motoniveladora');
INSERT INTO Tipo_Producto (Nombre_Tipo) VALUES ('Tractor de Oruga');
INSERT INTO Tipo_Producto (Nombre_Tipo) VALUES ('Perforadora');
INSERT INTO Tipo_Producto (Nombre_Tipo) VALUES ('Rodillo Compactador');

INSERT INTO Proveedor VALUES ('PRV00001', 'Caterpillar', 'USA', 'Excavadoras, Tractores', 10);
INSERT INTO Proveedor VALUES ('PRV00002', 'Komatsu', 'Japon', 'Cargadores, Bulldozer', 8);
INSERT INTO Proveedor VALUES ('PRV00003', 'VolvoCE', 'Suecia', 'Camiones Mineros', 6);
INSERT INTO Proveedor VALUES ('PRV00004', 'Hitachi', 'Japon', 'Excavadoras eléctricas', 5);
INSERT INTO Proveedor VALUES ('PRV00005', 'JohnDeere', 'USA', 'Retroexcavadoras', 7);
INSERT INTO Proveedor VALUES ('PRV00006', 'Sany', 'China', 'Grúas, Excavadoras', 4);
INSERT INTO Proveedor VALUES ('PRV00007', 'Doosan', 'Corea', 'Cargadores frontales', 6);
INSERT INTO Proveedor VALUES ('PRV00008', 'Liebherr', 'Alemania', 'Perforadoras', 9);
INSERT INTO Proveedor VALUES ('PRV00009', 'Case', 'Italia', 'Compactadores', 5);
INSERT INTO Proveedor VALUES ('PRV00010', 'JCB', 'ReinoUnido', 'Retroexcavadoras', 3);

INSERT INTO Colaborador (ID_Colaborador, Nombre_Col, Departamento_ID, Cargo_Col, Fecha_Contratacion_Col, ID_Sucursal, Sueldo_Col) VALUES
(1, 'Carlos Torres', 1, 'Jefe de Logística', '2020-03-15', 1, 6200.00),
(2, 'Luis Paredes', 2, 'Supervisor de Mantenimiento', '2021-01-10', 2, 5900.00),
(3, 'Diana Ruiz', 3, 'RRHH Senior', '2019-07-25', 3, 5600.00),
(4, 'Pedro Quispe', 4, 'Jefe de Ventas', '2022-04-18', 4, 6000.00),
(5, 'Ana Romero', 5, 'Contadora General', '2020-08-05', 5, 5800.00),
(6, 'Jorge Ccopa', 6, 'Ingeniero de Campo', '2021-09-12', 6, 6200.00),
(7, 'Martha García', 7, 'Operaciones Junior', '2022-11-20', 7, 4800.00),
(8, 'Ernesto Mena', 8, 'Gerente de Proyecto', '2023-01-02', 8, 6500.00),
(9, 'Lucía Vargas', 9, 'Especialista en Calidad', '2020-05-09', 9, 5700.00),
(10, 'Felipe Lozano', 10, 'Supervisor de Seguridad', '2021-06-15', 10, 5500.00);

INSERT INTO Servicio (ID_Servicio, Nombre_Serv, Descripcion_Serv, Tarifa_Base_Serv, Departamento_ID, ID_Colaborador, RUC_Cli) VALUES
(1, 'Alquiler Excavadora CAT 320D', 'Alquiler de excavadora hidráulica modelo CAT 320D por semana', 1200.00, 1, 1, '12345678901'), -- Logística-- Minera Andina
(2, 'Venta Cargador Frontal CAT 950GC', 'Venta directa de cargador frontal CAT 950GC', 170000.00, 4, 4, '98765432109'), -- Ventas -- Ventas Norte
(3, 'Alquiler Camión Minero CAT 777G', 'Alquiler por día del camión minero CAT 777G', 3000.00, 1, 1, '12345678901'), -- Logística-- Minera Andina
(4, 'Venta Retroexcavadora CAT 416F2', 'Venta de retroexcavadora versátil CAT 416F2', 140000.00, 4, 4, '12312312312'), -- Ventas -- Constructora Lima
(5, 'Mantenimiento CAT 797F', 'Servicio técnico de mantenimiento para CAT 797F', 2800.00, 2, 2, '12345678901'), -- Mantenimiento-- Minera Andina
(6, 'Alquiler Bulldozer CAT D6K2', 'Alquiler semanal de bulldozer mediano CAT D6K2', 1800.00, 1, 1, '12345678901'), -- Logística - Minera Andina
(7, 'Supervisión Operativa', 'Supervisión de maquinaria y personal en campo minero', 3100.00, 6, 6, '12312312312'), -- Ingeniería-- Constructora Lima
(8, 'Operación de Maquinaria', 'Soporte operativo para maquinaria en obras públicas', 3200.00, 7, 7, '12312312312'), -- Operaciones-- Constructora Lima
(9, 'Venta Motoniveladora CAT 120K', 'Venta directa de motoniveladora CAT 120K', 160000.00, 4, 4, '98765432109'), -- Ventas -- Ventas Norte
(10, 'Alquiler Compactador CAT CS11GC', 'Alquiler por día del compactador CAT CS11GC', 900.00, 1, 1, '10987654321'); -- Logística -- Logística Perú


INSERT INTO Producto (ID_Producto, Nombre_Prod, Descripcion_Prod, Tipo_ID, ID_Proveedor, Stock_Prod, Precio_Compra, Precio_Alquiler) VALUES
-- Excavadoras (Proveedor: PRV00001 - Caterpillar)
(1001, 'CAT 320D', 'Excavadora hidráulica', 1, 'PRV00001', 5, 150000.00, 1200.00),
(1010, 'CAT 349D2', 'Excavadora grande', 1, 'PRV00001', 4, 220000.00, 1900.00),
(1012, 'CAT 374F', 'Excavadora para minería', 1, 'PRV00001', 2, 280000.00, 2100.00),
(1015, 'CAT 301.7', 'Mini excavadora', 1, 'PRV00001', 15, 98000.00, 800.00),
(1016, 'CAT 336F', 'Excavadora mediana', 1, 'PRV00001', 9, 210000.00, 1600.00),
(1017, 'CAT 323D3', 'Excavadora de producción', 1, 'PRV00001', 11, 195000.00, 1500.00),

-- Cargadores Frontales (Proveedor: PRV00007 - Doosan)
(1002, 'CAT 950GC', 'Cargador frontal mediano', 2, 'PRV00007', 8, 170000.00, 1300.00),
(1007, 'CAT 988K', 'Cargador frontal grande', 2, 'PRV00007', 2, 310000.00, 2700.00),
(1014, 'CAT 950L', 'Cargador frontal', 2, 'PRV00007', 7, 185000.00, 1500.00),

-- Camiones Mineros (Proveedor: PRV00003 - VolvoCE)
(1003, 'CAT 777G', 'Camión minero de gran tonelaje', 3, 'PRV00003', 3, 320000.00, 3000.00),
(1013, 'CAT 730C2', 'Camión articulado', 3, 'PRV00003', 5, 260000.00, 2000.00),

-- Bulldozers (Proveedor: PRV00002 - Komatsu)
(1004, 'CAT D6K2', 'Bulldozer mediano', 4, 'PRV00002', 6, 195000.00, 1800.00),
(1009, 'CAT D11T', 'Tractor oruga gigante', 8, 'PRV00002', 2, 390000.00, 3000.00),

-- Retroexcavadoras (Proveedor: PRV00005 - JohnDeere)
(1005, 'CAT 416F2', 'Retroexcavadora versátil', 5, 'PRV00005', 10, 140000.00, 1100.00),
(1018, 'CAT 426F2', 'Retroexcavadora avanzada', 5, 'PRV00005', 13, 155000.00, 1100.00),

-- Motoniveladoras (Proveedor: PRV00001 - Caterpillar)
(1006, 'CAT 120K', 'Motoniveladora de 14 pies', 7, 'PRV00001', 7, 160000.00, 1400.00),
(1019, 'CAT 140GC', 'Motoniveladora avanzada', 7, 'PRV00001', 6, 175000.00, 1400.00),

-- Compactadores (Proveedor: PRV00009 - Case)
(1011, 'CAT CS11GC', 'Rodillo compactador vibratorio', 10, 'PRV00009', 12, 130000.00, 900.00),

-- Extendedora de asfalto (Proveedor: PRV00004 - Hitachi)
(1020, 'CAT AP555F', 'Extendedora de asfalto', 9, 'PRV00004', 4, 205000.00, 1600.00);

INSERT INTO Producto_Sucursal VALUES (1001, 1, 2);
INSERT INTO Producto_Sucursal VALUES (1001, 2, 3);
INSERT INTO Producto_Sucursal VALUES (1010, 3, 2);
INSERT INTO Producto_Sucursal VALUES (1012, 4, 2);
INSERT INTO Producto_Sucursal VALUES (1015, 5, 7);
INSERT INTO Producto_Sucursal VALUES (1016, 6, 4);
INSERT INTO Producto_Sucursal VALUES (1017, 7, 6);

INSERT INTO Producto_Sucursal VALUES (1002, 1, 4);
INSERT INTO Producto_Sucursal VALUES (1007, 3, 2);
INSERT INTO Producto_Sucursal VALUES (1014, 5, 4);

INSERT INTO Producto_Sucursal VALUES (1003, 6, 2);
INSERT INTO Producto_Sucursal VALUES (1013, 2, 3);

INSERT INTO Producto_Sucursal VALUES (1004, 4, 3);
INSERT INTO Producto_Sucursal VALUES (1009, 7, 2);

INSERT INTO Producto_Sucursal VALUES (1005, 8, 5);
INSERT INTO Producto_Sucursal VALUES (1018, 9, 7);

INSERT INTO Producto_Sucursal VALUES (1006, 10, 3);
INSERT INTO Producto_Sucursal VALUES (1019, 1, 3);

INSERT INTO Producto_Sucursal VALUES (1011, 2, 6);
INSERT INTO Producto_Sucursal VALUES (1020, 3, 2);

DELIMITER //
CREATE TRIGGER insertar_fecha_contratacion
BEFORE INSERT ON Colaborador
FOR EACH ROW
BEGIN
  SET NEW.Fecha_Contratacion_Col = CURDATE();
END;
//
DELIMITER ;
DELIMITER //
CREATE TRIGGER validar_ruc_cliente
BEFORE INSERT ON Cliente
FOR EACH ROW
BEGIN
  IF LENGTH(NEW.RUC_Cli) <> 11 THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'El RUC debe tener exactamente 11 caracteres';
  END IF;
END;
//
DELIMITER ;
DELIMITER //
CREATE TRIGGER validar_costo_servicio
BEFORE INSERT ON Servicio
FOR EACH ROW
BEGIN
  IF NEW.Costo_Total < NEW.Tarifa_Base_Serv THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'El costo total no puede ser menor a la tarifa base del servicio';
  END IF;
END;
//
DELIMITER ;
