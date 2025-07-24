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
    try (PreparedStatement ps = conexion.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
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
                rs.getString("Nombre_Prod"),
                rs.getString("Descripcion_Prod"),
                rs.getString("Tipo_ID"),
                rs.getInt("Stock_Prod"),
                rs.getDouble("Precio_Compra"),
                rs.getDouble("Precio_Alquiler"),
                pr
            );
            lista.add(prod);
        }
    }
    return lista;
}

    public List<Producto> buscarPorProveedor(String idProveedor) throws SQLException {
        List<Producto> lista = new ArrayList<>();
        String sql = """
            SELECT p.*, pr.Nombre_Marca_Pro, pr.Pais_Proveedor, 
                   pr.Linea_Producto_Proveedor, pr.AñosRelacion_Proveedor
            FROM Producto p
            JOIN Proveedor pr ON p.ID_Proveedor = pr.ID_Proveedor
            WHERE pr.ID_Proveedor = ?
            """;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, idProveedor);
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
                    rs.getString("Nombre_Prod"),
                    rs.getString("Descripcion_Prod"),
                    rs.getString("Tipo_ID"),
                    rs.getInt("Stock_Prod"),
                    rs.getDouble("Precio_Compra"),
                    rs.getDouble("Precio_Alquiler"),

                        pr
                    );
                    lista.add(prod);
                }
            }
        }
        return lista;
    }
    
    public Producto buscarPorId(int idProducto) throws SQLException {
    String sql = """
        SELECT p.*, pr.Nombre_Marca_Pro, pr.Pais_Proveedor, 
               pr.Linea_Producto_Proveedor, pr.AñosRelacion_Proveedor
        FROM Producto p
        JOIN Proveedor pr ON p.ID_Proveedor = pr.ID_Proveedor
        WHERE p.ID_Producto = ?
        """;
    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setInt(1, idProducto);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                Proveedor pr = new Proveedor(
                    rs.getString("ID_Proveedor"),
                    rs.getString("Nombre_Marca_Pro"),
                    rs.getString("Pais_Proveedor"),
                    rs.getString("Linea_Producto_Proveedor"),
                    rs.getInt("AñosRelacion_Proveedor")
                );
                return new Producto(
                    rs.getInt("ID_Producto"),
                    rs.getString("Nombre_Prod"),
                    rs.getString("Descripcion_Prod"),
                    rs.getString("Tipo_ID"),
                    rs.getInt("Stock_Prod"),
                    rs.getDouble("Precio_Compra"),
                    rs.getDouble("Precio_Alquiler"),
                    pr
                );
            }
        }
    }
    return null;
}
    
    public boolean eliminarProducto(int idProducto) throws SQLException {
    String sql = "DELETE FROM Producto WHERE ID_Producto = ?";
    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setInt(1, idProducto);
        return ps.executeUpdate() > 0;
    }
}
    
    public boolean actualizarProducto(Producto producto) throws SQLException {
    String sql = """
        UPDATE Producto SET Nombre_Producto = ?, Descripcion = ?, Tipo = ?, Stock = ?,
                            PrecioCompra = ?, PrecioAlquiler = ?, Precio = ?, ID_Proveedor = ?
        WHERE ID_Producto = ?
        """;
    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setString(1, producto.getNombre());
        ps.setString(2, producto.getDescripcion());
        ps.setString(3, producto.getTipo());
        ps.setInt(4, producto.getStock());
        ps.setDouble(5, producto.getPrecioCompra());
        ps.setDouble(6, producto.getPrecioAlquiler());

        ps.setString(8, producto.getProveedor().getIdProveedor());
        ps.setInt(9, producto.getIdProducto());
        return ps.executeUpdate() > 0;
    }
}

}