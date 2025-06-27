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

