/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.dao.ProductoDAO;
import com.mycompany.dao.ProveedorDAO;
import com.mycompany.model.Producto;
import com.mycompany.model.Proveedor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ProveedorController {

    private final ProductoDAO productoDAO;
    private final ProveedorDAO proveedorDAO;
    private final Scanner scanner;

    public ProveedorController(Connection conexion) {
        this.productoDAO = new ProductoDAO(conexion);
        this.proveedorDAO = new ProveedorDAO(conexion);
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== MENÚ PRODUCTOS Y PROVEEDORES ===");
            System.out.println("1. Registrar Proveedor");
            System.out.println("2. Registrar Producto");
            System.out.println("3. Listar Proveedores");
            System.out.println("4. Listar Productos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcion) {
                    case 1 -> registrarProveedor();
                    case 2 -> registrarProducto();
                    case 3 -> listarProveedores();
                    case 4 -> listarProductos();
                    case 5 -> salir = true;
                    default -> System.out.println("Opción no válida");
                }
            } catch (SQLException e) {
                System.err.println("Error de BD: " + e.getMessage());
            }
        }
    }

    private void registrarProveedor() throws SQLException {
        System.out.print("ID Proveedor: ");
        String id = scanner.nextLine();
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("País: ");
        String pais = scanner.nextLine();
        System.out.print("Línea Producto: ");
        String linea = scanner.nextLine();
        System.out.print("Años Relación: ");
        int anios = scanner.nextInt();
        scanner.nextLine();

        Proveedor p = new Proveedor(id, marca, pais, linea, anios);
        proveedorDAO.insertarProveedor(p);
        System.out.println("✅ Proveedor registrado");
    }

    private void registrarProducto() throws SQLException {
        System.out.print("Nombre Producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("ID Proveedor: ");
        String idProv = scanner.nextLine();

        Proveedor p = proveedorDAO.buscarPorId(idProv);
        if (p == null) {
            System.out.println("❌ Proveedor no encontrado");
            return;
        }
        Producto prod = new Producto(0, nombre, precio, p);
        productoDAO.insertarProducto(prod);
        System.out.println("✅ Producto registrado");
    }

    private void listarProveedores() throws SQLException {
        List<Proveedor> lista = proveedorDAO.listarProveedores();
        for (Proveedor p : lista) {
            System.out.println(p.getIdProveedor() + " - " + p.getNombreMarca());
        }
    }

    private void listarProductos() throws SQLException {
        List<Producto> lista = productoDAO.listarProductos();
        for (Producto p : lista) {
            System.out.println(p.getNombre() + " - S/ " + p.getPrecio() + " - " + p.getProveedor().getNombreMarca());
        }
    }

    public static void main(String[] args) {
        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/FerreyrosG6", "root", "")) {
            new ProductoController(conexion).iniciar();
        } catch (SQLException e) {
            System.err.println("Error conexión: " + e.getMessage());
        }
    }
}