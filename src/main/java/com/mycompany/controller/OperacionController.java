/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.dao.OperacionDAO;
import com.mycompany.dao.ProductoDAO;
import com.mycompany.model.Operacion;
import com.mycompany.model.Producto;
import com.mycompany.model.Servicio;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class OperacionController {
    private final OperacionDAO operacionDao;
    private final Scanner scanner;
    private final ProductoDAO productoDao;


    public OperacionController(Connection conexion) {
        this.operacionDao = new OperacionDAO(conexion);
        this.productoDao = new ProductoDAO(conexion); 
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() throws SQLException {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- Gestión de Operaciones ---");
            System.out.println("1. Registrar Operación");
            System.out.println("2. Listar Operaciones");
            System.out.println("3. Actualizar Operación");
            System.out.println("4. Eliminar Operación");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar

            switch (opcion) {
                case 1 -> registrarOperacion();
                case 2 -> listarOperaciones();
                case 3 -> actualizarOperacion();
                case 4 -> eliminarOperacion();
                case 5 -> salir = true;
                default -> System.out.println("⚠️ Opción no válida.");
            }
        }
    }
    
private void registrarOperacion() throws SQLException {
    System.out.print("Tipo de operación (Compra/Alquiler): ");
    String tipo = scanner.nextLine();

    Integer idProd = null;
    Integer idServicio = null;
    double costo = 0.0;

    if (tipo.equalsIgnoreCase("Compra")) {
        System.out.print("ID del producto: ");
        idProd = scanner.nextInt();
        scanner.nextLine();

        Producto producto = productoDao.buscarPorId(idProd);
        if (producto == null) {
            System.out.println("⚠️ Producto no encontrado.");
            return;
        }

        costo = producto.getPrecioCompra(); // ✅ Usar precio de compra
        System.out.println("💲 Precio del producto cargado automáticamente: S/ " + costo);

    } else if (tipo.equalsIgnoreCase("Alquiler")) {
        System.out.print("ID del producto (para alquiler): ");
        idProd = scanner.nextInt();
        scanner.nextLine();

        Producto producto = productoDao.buscarPorId(idProd);
        if (producto == null) {
            System.out.println("⚠️ Producto no encontrado.");
            return;
        }

        costo = producto.getPrecioAlquiler(); // ✅ Usar precio de alquiler
        System.out.println("💲 Precio del alquiler cargado automáticamente: S/ " + costo);

    } else {
        System.out.println("⚠️ Tipo de operación no válido.");
        return;
    }

    System.out.print("RUC del cliente: ");
    String ruc = scanner.nextLine();

    Operacion operacion = new Operacion(tipo, idProd, idServicio, ruc, costo);
    operacionDao.registrar(operacion);
    System.out.println("✅ Operación registrada correctamente.");
}



    private void listarOperaciones() {
        List<Operacion> lista = operacionDao.listar();
        if (lista.isEmpty()) {
            System.out.println("⚠️ No hay operaciones registradas.");
            return;
        }
        System.out.println("=== Lista de Operaciones ===");
        for (Operacion o : lista) {
            System.out.println("ID: " + o.getIdOperacion() +
                    " | Tipo: " + o.getTipoOperacion() +
                    " | Producto: " + o.getIdProducto() +
                    " | Servicio: " + o.getIdServicio() +
                    " | RUC Cliente: " + o.getRucCliente() +
                    " | Fecha: " + o.getFechaOperacion() +
                    " | Costo: S/ " + o.getCostoTotal());
        }
    }
    
    
  private void eliminarOperacion() {
    System.out.print("Ingrese el ID de la operación a eliminar: ");
    int id = scanner.nextInt();
    scanner.nextLine();

    operacionDao.eliminar(id);
}


    private void actualizarOperacion() throws SQLException {
    System.out.print("Ingrese el ID de la operación a actualizar: ");
    int id = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Nuevo tipo de operación (Compra/Alquiler): ");
    String tipo = scanner.nextLine();

    Integer idProd = null;
    Integer idServ = null;
    double costo = 0.0;

    if (tipo.equalsIgnoreCase("Compra")) {
        System.out.print("ID del producto: ");
        idProd = scanner.nextInt();
        scanner.nextLine();

        Producto producto = productoDao.buscarPorId(idProd);
        if (producto == null) {
            System.out.println("⚠️ Producto no encontrado.");
            return;
        }

        costo = producto.getPrecioCompra();

    } else if (tipo.equalsIgnoreCase("Alquiler")) {
        System.out.print("ID del producto (para alquiler): ");
        idProd = scanner.nextInt();
        scanner.nextLine();

        Producto producto = productoDao.buscarPorId(idProd);
        if (producto == null) {
            System.out.println("⚠️ Producto no encontrado.");
            return;
        }

        costo = producto.getPrecioAlquiler();

    } else {
        System.out.println("⚠️ Tipo de operación inválido.");
        return;
    }

    System.out.print("Nuevo RUC del cliente: ");
    String ruc = scanner.nextLine();

    Operacion operacion = new Operacion(id, tipo, idProd, idServ, ruc, costo);
    operacionDao.actualizar(operacion);
}

}

