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

public class ProductoController {

    private final ProductoDAO productoDao;
    private final ProveedorDAO proveedorDao;

    public ProductoController(Connection conexion) {
        this.productoDao = new ProductoDAO(conexion);
        this.proveedorDao = new ProveedorDAO(conexion);
    }

    // === PRODUCTO ===

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