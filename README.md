# 🚧 Proyecto FerreyrosG6 – Sistema de Gestión + Chat Minero (Java + MySQL + MongoDB)

-----------------INTEGRANTES
| Nombre completo                   | Código UTP |
| --------------------------------- | ---------- |
| Kevin Rafael Palomino Ramirez     | U23241139  |
| Axel Huamani Aguirre              | U23268794  |
| Juan Josue Colqui Quispe          | U23241773  |
| Victor David Nuñez Cabrera        | U23101020  |
| Eidan Jeffri Limaylla Santos      | U23233217  |
| Jhon Franco Escriba Humpiri       | U23215626  |

Este proyecto es un sistema de gestión empresarial desarrollado en **Java (por consola)** con conexión a una base de datos relacional (**MySQL**) 
y un módulo adicional de chat con roles y seguridad utilizando **MongoDB** (NoSQL).

---

## Funcionalidades Principales

- Registro y gestión de **clientes, proveedores, colaboradores, productos y sucursales**
- Relación entre las entidades usando **llaves foráneas**
- Operaciones CRUD completas implementadas en **clases DAO**
- Validación de integridad con **triggers** en MySQL
- Chat minero (MongoDB):
  - Chats segmentados por sector
  - Reporte y calificación de usuarios
  - Rol de usuarios (seguridad en la comunicación)

---

## Tecnologías utilizadas

- Java (NetBeans, JDK 17+)
- MySQL (Workbench o consola)
- MongoDB (Compass + Driver oficial)
- GitHub (repositorio colaborativo)
- Estructura por paquetes (`com.mycompany.dao`, `model`, `controller`, etc.)

---

## Cómo ejecutar

### 1. **Instalar las dependencias**
- Tener Java JDK y NetBeans instalados
- Instalar MySQL Server y MongoDB local
- Clonar el repositorio con:  
  ```bash
  git clone https://github.com/tu_usuario/ferreyrosG6.git
