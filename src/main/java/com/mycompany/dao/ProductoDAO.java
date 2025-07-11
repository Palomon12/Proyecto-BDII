/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author JHON
 */
import modelo.Producto;
import modelo.Proveedor;
import java.sql.*;
import java.util.*;

public class ProductoDAO {
    public void insertar(Producto producto) {
        String sql = "INSERT INTO Producto(Nombre_Producto, Precio, ID_Proveedor) VALUES (?, ?, ?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setString(3, producto.getProveedor().getId());
            ps.executeUpdate();

        } catch (SQLException e) {
        }
    }

    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT p.ID_Producto, p.Nombre_Producto, p.Precio, " +
                     "pr.ID_Proveedor, pr.Nombre_Proveedor, pr.Pais_Proveedor, pr.Sector_Proveedor, pr.AñosRelacion " +
                     "FROM Producto p JOIN Proveedor pr ON p.ID_Proveedor = pr.ID_Proveedor";

        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Proveedor pr = new Proveedor(
                    rs.getString("ID_Proveedor"),
                    rs.getString("Nombre_Proveedor"),
                    rs.getString("Pais_Proveedor"),
                    rs.getString("Sector_Proveedor"),
                    rs.getInt("AñosRelacion")
                );

                Producto prod = new Producto(
                    rs.getInt("ID_Producto"),
                    rs.getString("Nombre_Producto"),
                    rs.getDouble("Precio"),
                    pr
                );

                lista.add(prod);
            }

        } catch (SQLException e) {
        }

        return lista;
    }
}