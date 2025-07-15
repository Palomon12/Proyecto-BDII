/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author JHON
 */
import com.mycompany.model.*;
import java.sql.*;
import java.util.*;

public class ProveedorDAO {
    public List<Proveedor> listar() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM Proveedor";

        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Proveedor p = new Proveedor(
                    rs.getString("ID_Proveedor"),
                    rs.getString("Nombre_Proveedor"),
                    rs.getString("Pais_Proveedor"),
                    rs.getString("Sector_Proveedor"),
                    rs.getInt("AñosRelacion")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}

