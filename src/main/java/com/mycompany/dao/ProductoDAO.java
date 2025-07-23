/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.model.Producto;
import com.mycompany.model.Proveedor;
import java.sql.*;
import java.util.*;

public class ProductoDAO {
    private final Connection conexion;

    public ProductoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarProducto(Producto producto) throws SQLException {
        String sql = """
            INSERT INTO Producto(Nombre_Producto, Descripcion, Tipo, Stock, 
                                 PrecioCompra, PrecioAlquiler, Precio, ID_Proveedor) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setString(3, producto.getTipo());
            ps.setInt(4, producto.getStock());
            ps.setDouble(5, producto.getPrecioCompra());
            ps.setDouble(6, producto.getPrecioAlquiler());
            ps.setDouble(7, producto.getPrecio());
            ps.setString(8, producto.getProveedor().getIdProveedor());
            ps.executeUpdate();
        }
    }

    public List<Producto> listarProductos() throws SQLException {
        List<Producto> lista = new ArrayList<>();
        String sql = """
            SELECT p.*, pr.Nombre_Marca_Pro, pr.Pais_Proveedor, 
                   pr.Linea_Producto_Proveedor, pr.AñosRelacion_Proveedor
            FROM Producto p
            JOIN Proveedor pr ON p.ID_Proveedor = pr.ID_Proveedor
            """;
        try (Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Proveedor pr = new Proveedor(
                    rs.getString("ID_Proveedor"),
                    rs.getString("Nombre_Marca_Pro"),
                    rs.getString("Pais_Proveedor"),
                    rs.getString("Linea_Producto_Proveedor"),
                    rs.getInt("AñosRelacion_Proveedor")
                );
                Producto prod = new Producto(
                    rs.getInt("ID_Producto"),
                    rs.getString("Nombre_Producto"),
                    rs.getString("Descripcion"),
                    rs.getString("Tipo"),
                    rs.getInt("Stock"),
                    rs.getDouble("PrecioCompra"),
                    rs.getDouble("PrecioAlquiler"),
                    rs.getDouble("Precio"),
                    pr
                );
                lista.add(prod);
            }
        }
        return lista;
    }

    public List<Producto> buscarPorProveedor(int idProveedor) throws SQLException {
        List<Producto> lista = new ArrayList<>();
        String sql = """
            SELECT p.*, pr.Nombre_Marca_Pro, pr.Pais_Proveedor, 
                   pr.Linea_Producto_Proveedor, pr.AñosRelacion_Proveedor
            FROM Producto p
            JOIN Proveedor pr ON p.ID_Proveedor = pr.ID_Proveedor
            WHERE pr.ID_Proveedor = ?
            """;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idProveedor);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Proveedor pr = new Proveedor(
                        rs.getString("ID_Proveedor"),
                        rs.getString("Nombre_Marca_Pro"),
                        rs.getString("Pais_Proveedor"),
                        rs.getString("Linea_Producto_Proveedor"),
                        rs.getInt("AñosRelacion_Proveedor")
                    );
                    Producto prod = new Producto(
                        rs.getInt("ID_Producto"),
                        rs.getString("Nombre_Producto"),
                        rs.getString("Descripcion"),
                        rs.getString("Tipo"),
                        rs.getInt("Stock"),
                        rs.getDouble("PrecioCompra"),
                        rs.getDouble("PrecioAlquiler"),
                        rs.getDouble("Precio"),
                        pr
                    );
                    lista.add(prod);
                }
            }
        }
        return lista;
    }
}
