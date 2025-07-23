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
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ProductoController {

    private final ProductoDAO productoDao;
    private final ProveedorDAO proveedorDao;

    public ProductoController(Connection conexion) {
        this.productoDao = new ProductoDAO(conexion);
        this.proveedorDao = new ProveedorDAO(conexion);
    }

    // === PRODUCTO ===
    
    public void iniciar() {
    Scanner scanner = new Scanner(System.in);
    boolean volver = false;

    while (!volver) {
        System.out.println("\n--- MENÚ DE PRODUCTOS ---");
        System.out.println("1. Registrar producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Buscar productos por proveedor");
        System.out.println("4. Volver al menú principal");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        try {
            switch (opcion) {
                case 1 -> {
                      System.out.print("Nombre del producto: ");
                      String nombre = scanner.nextLine();

                      System.out.print("Descripción: ");
                      String descripcion = scanner.nextLine();

                      System.out.print("ID del tipo (número): ");
                      String tipo = scanner.nextLine();

                      System.out.print("ID del proveedor: ");
                      String idProveedor = scanner.nextLine();

                      // Buscar proveedor
                     Proveedor proveedor = proveedorDao.buscarPorId(idProveedor);
                         if (proveedor == null) {
                             System.out.println("❌ Proveedor no encontrado.");
                           return;
                          }

                        System.out.print("Stock: ");
                        int stock = Integer.parseInt(scanner.nextLine());

                        System.out.print("Precio de compra: ");
                        double precioCompra = Double.parseDouble(scanner.nextLine());

                        System.out.print("Precio de alquiler: ");
                        double precioAlquiler = Double.parseDouble(scanner.nextLine());

                        System.out.print("Precio final de venta: ");
                        double precio = Double.parseDouble(scanner.nextLine());

                        // Crear producto usando el constructor completo
                        Producto producto = new Producto(0, nombre, descripcion, tipo, stock,
                                     precioCompra, precioAlquiler, precio, proveedor);

                             registrarProducto(producto);
                                System.out.println("✅ Producto registrado correctamente.");
                }
                case 2 -> {
                    List<Producto> productos = listarProductos();
                    if (productos.isEmpty()) {
                        System.out.println("No hay productos registrados.");
                    } else {
                        System.out.println("\n--- LISTA DE PRODUCTOS ---");
                        for (Producto p : productos) {
                            System.out.println(p);
                        }
                    }
                }
                case 3 -> {
                    System.out.print("Ingrese ID del proveedor: ");
                    String idProv = scanner.nextLine();
                    List<Producto> productosPorProv = buscarProductosPorProveedor(idProv);
                    if (productosPorProv.isEmpty()) {
                        System.out.println("No se encontraron productos para ese proveedor.");
                    } else {
                        System.out.println("\n--- PRODUCTOS DEL PROVEEDOR ---");
                        for (Producto p : productosPorProv) {
                            System.out.println(p);
                        }
                    }
                }
                case 4 -> volver = true;
                default -> System.out.println("⚠️ Opción inválida. Intente otra vez.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}


    public void registrarProducto(Producto producto) throws SQLException {
        productoDao.insertarProducto(producto);
    }

    public List<Producto> listarProductos() throws SQLException {
        return productoDao.listarProductos();
    }

    public List<Producto> buscarProductosPorProveedor(String idProveedor) throws SQLException {
        return productoDao.buscarPorProveedor(idProveedor);
    }

    public Producto buscarProductoPorId(int idProducto) throws SQLException {
        return productoDao.buscarPorId(idProducto);
    }

    public boolean actualizarProducto(Producto producto) throws SQLException {
        return productoDao.actualizarProducto(producto);
    }

    public boolean eliminarProducto(int idProducto) throws SQLException {
        return productoDao.eliminarProducto(idProducto);
    }

    // === PROVEEDOR ===

    public void registrarProveedor(Proveedor proveedor) throws SQLException {
        proveedorDao.insertarProveedor(proveedor);
    }

    public List<Proveedor> listarProveedores() throws SQLException {
        return proveedorDao.listarProveedores();
    }

    public Proveedor buscarProveedorPorId(String idProveedor) throws SQLException {
        return proveedorDao.buscarPorId(idProveedor);
    }

    public boolean actualizarProveedor(Proveedor proveedor) throws SQLException {
        return proveedorDao.actualizarProveedor(proveedor);
    }

    public boolean eliminarProveedor(String idProveedor) throws SQLException {
        return proveedorDao.eliminarProveedor(idProveedor);
    }
}