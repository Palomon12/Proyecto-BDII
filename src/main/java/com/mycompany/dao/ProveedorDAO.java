/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.model.Proveedor;
import java.sql.*;
import java.util.*;

public class ProveedorDAO {
    private final Connection conexion;

    public ProveedorDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarProveedor(Proveedor proveedor) throws SQLException {
        String sql = "INSERT INTO Proveedor VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, proveedor.getIdProveedor());
            ps.setString(2, proveedor.getNombreMarca());
            ps.setString(3, proveedor.getPais());
            ps.setString(4, proveedor.getLineaProducto());
            ps.setInt(5, proveedor.getAniosRelacion());
            ps.executeUpdate();
        }
    }

    public Proveedor buscarPorId(String id) throws SQLException {
        String sql = "SELECT * FROM Proveedor WHERE ID_Proveedor = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Proveedor(
                        rs.getString("Id_Proveedor"),
                        rs.getString("Nombre_Marca_Pro"),
                        rs.getString("Pais_Proveedor"),
                        rs.getString("Linea_Producto_Proveedor"),
                        rs.getInt("AñosRelacion_Proveedor")
                    );
                }
            }
        }
        return null;
    }

    public List<Proveedor> listarProveedores() throws SQLException {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM Proveedor";
        try (Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Proveedor p = new Proveedor(
                    rs.getString("ID_Proveedor"),
                    rs.getString("Nombre_Marca_Pro"),
                    rs.getString("Pais_Proveedor"),
                    rs.getString("Linea_Producto_Proveedor"),
                    rs.getInt("AñosRelacion_Proveedor")
                );
                lista.add(p);
            }
        }
        return lista;
    }
    
    public boolean actualizarProveedor(Proveedor proveedor) throws SQLException {
    String sql = """
        UPDATE Proveedor
        SET Nombre_Marca_Pro = ?, Pais_Proveedor = ?, 
            Linea_Producto_Proveedor = ?, AñosRelacion_Proveedor = ?
        WHERE ID_Proveedor = ?
        """;
    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setString(1, proveedor.getNombreMarca());
        ps.setString(2, proveedor.getPais());
        ps.setString(3, proveedor.getLineaProducto());
        ps.setInt(4, proveedor.getAniosRelacion());
        ps.setString(5, proveedor.getIdProveedor());
        return ps.executeUpdate() > 0;
    }
}
    public boolean eliminarProveedor(String idProveedor) throws SQLException {
    String sql = "DELETE FROM Proveedor WHERE ID_Proveedor = ?";
    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setString(1, idProveedor);
        return ps.executeUpdate() > 0;
    }
}

}