/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.main;

import com.mycompany.controller.ClienteController;
import com.mycompany.controller.ProductoController;
import com.mycompany.util.ConexionMySQL;
import com.mycompany.controller.OperacionController;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Milagros
 */
public class ProyectoBDII {

    public static void main(String[] args) throws SQLException {
        ConexionMySQL c = new ConexionMySQL();
        Connection cn = c.conectar();

        if (cn == null) {
            System.err.println("❌ No se pudo establecer conexión con la base de datos.");
            return;
        }

        ClienteController clienteController = new ClienteController(cn);
        ProductoController productoController = new ProductoController(cn);
        OperacionController operacionController = new OperacionController(cn);
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== MENÚ PRINCIPAL - PROYECTO FERREYROS G6 ===");
            System.out.println("1. Gestión de Clientes y Servicios");
            System.out.println("2. Gestión de Productos y Proveedores");
            System.out.println("3. Gestión de Operaciones");
            System.out.println("4. Ver total de clientes registrados");
            System.out.println("5. Salir");

            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> clienteController.iniciar(); // Cliente + Servicio
                case 2 -> productoController.iniciar(); // Producto + búsqueda por proveedor
                case 3 -> operacionController.iniciar();
                case 5 -> {
                    salir = true;
                    System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
                }
                case 4 -> {
           try {
                  int total = clienteController.contarClientes();
                  System.out.println("📊 Total de clientes registrados: " + total);
                 } catch (SQLException e) {
                  System.err.println("❌ Error al contar clientes: " + e.getMessage());
                }
            }
                default -> System.out.println("⚠️ Opción no válida. Intente nuevamente.");
            }
        }

        try {
            cn.close();
        } catch (Exception e) {
            System.err.println("⚠️ Error al cerrar la conexión: " + e.getMessage());
        }
    }
}