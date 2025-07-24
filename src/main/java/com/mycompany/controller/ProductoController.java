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

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        boolean volver = false;

        while (!volver) {
            System.out.println("\n--- MENÚ DE PRODUCTOS ---");
            System.out.println("1. Registrar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar productos por proveedor");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            try {
                switch (opcion) {
                    case 1 -> registrarProducto(scanner);
                    case 2 -> listarProductos();
                    case 3 -> buscarProductosPorProveedor(scanner);
                    case 4 -> actualizarProducto(scanner);
                    case 5 -> eliminarProducto(scanner);
                    case 0 -> volver = true;
                    default -> System.out.println("⚠️ Opción inválida. Intente otra vez.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

    private void registrarProducto(Scanner scanner) throws SQLException {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();

        System.out.print("ID del proveedor: ");
        String idProveedor = scanner.nextLine();

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

        Producto producto = new Producto(0, nombre, descripcion, tipo, stock, precioCompra, precioAlquiler, proveedor);
        productoDao.insertarProducto(producto);
        System.out.println("✅ Producto registrado correctamente.");
    }

    private void listarProductos() throws SQLException {
        List<Producto> productos = productoDao.listarProductos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            System.out.println("\n--- LISTA DE PRODUCTOS ---");
            for (Producto p : productos) {
                System.out.println(p);
            }
        }
    }

    private void buscarProductosPorProveedor(Scanner scanner) throws SQLException {
        System.out.print("Ingrese ID del proveedor: ");
        String idProv = scanner.nextLine();
        List<Producto> productosPorProv = productoDao.buscarPorProveedor(idProv);
        if (productosPorProv.isEmpty()) {
            System.out.println("No se encontraron productos para ese proveedor.");
        } else {
            System.out.println("\n--- PRODUCTOS DEL PROVEEDOR ---");
            for (Producto p : productosPorProv) {
                System.out.println(p);
            }
        }
    }

    private void actualizarProducto(Scanner scanner) throws SQLException {
        System.out.print("Ingrese ID del producto a actualizar: ");
        int idProducto = Integer.parseInt(scanner.nextLine());

        Producto producto = productoDao.buscarPorId(idProducto);
        if (producto == null) {
            System.out.println("❌ Producto no encontrado.");
            return;
        }

        System.out.println("Nombre actual: " + producto.getNombre() + ". Nuevo nombre (Enter para mantener):");
        String nuevoNombre = scanner.nextLine();
        if (!nuevoNombre.isBlank()) producto.setNombre(nuevoNombre);

        System.out.println("Descripción actual: " + producto.getDescripcion() + ". Nueva descripción:");
        String nuevaDescripcion = scanner.nextLine();
        if (!nuevaDescripcion.isBlank()) producto.setDescripcion(nuevaDescripcion);

        System.out.println("Tipo actual: " + producto.getTipo() + ". Nuevo tipo:");
        String nuevoTipo = scanner.nextLine();
        if (!nuevoTipo.isBlank()) producto.setTipo(nuevoTipo);

        System.out.println("Stock actual: " + producto.getStock() + ". Nuevo stock:");
        String nuevoStock = scanner.nextLine();
        if (!nuevoStock.isBlank()) producto.setStock(Integer.parseInt(nuevoStock));

        System.out.println("Precio compra actual: " + producto.getPrecioCompra() + ". Nuevo precio:");
        String nuevoPrecioCompra = scanner.nextLine();
        if (!nuevoPrecioCompra.isBlank()) producto.setPrecioCompra(Double.parseDouble(nuevoPrecioCompra));

        System.out.println("Precio alquiler actual: " + producto.getPrecioAlquiler() + ". Nuevo precio:");
        String nuevoPrecioAlquiler = scanner.nextLine();
        if (!nuevoPrecioAlquiler.isBlank()) producto.setPrecioAlquiler(Double.parseDouble(nuevoPrecioAlquiler));

        System.out.println("ID proveedor actual: " + producto.getProveedor().getIdProveedor() + ". Nuevo ID proveedor:");
        String nuevoIdProv = scanner.nextLine();
        if (!nuevoIdProv.isBlank()) {
            Proveedor nuevoProveedor = proveedorDao.buscarPorId(nuevoIdProv);
            if (nuevoProveedor != null) {
                producto.setProveedor(nuevoProveedor);
            } else {
                System.out.println("⚠️ Proveedor no encontrado. Se mantiene el actual.");
            }
        }

        boolean actualizado = productoDao.actualizarProducto(producto);
        if (actualizado) {
            System.out.println("✅ Producto actualizado correctamente.");
        } else {
            System.out.println("❌ No se pudo actualizar el producto.");
        }
    }

    private void eliminarProducto(Scanner scanner) throws SQLException {
        System.out.print("Ingrese ID del producto a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Producto producto = productoDao.buscarPorId(id);
        if (producto == null) {
            System.out.println("❌ Producto no encontrado.");
            return;
        }

        boolean eliminado = productoDao.eliminarProducto(id);
        if (eliminado) {
            System.out.println("✅ Producto eliminado correctamente.");
        } else {
            System.out.println("❌ No se pudo eliminar el producto.");
        }
    }
}
