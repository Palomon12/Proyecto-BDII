/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.model.Operacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperacionDAO {
    private final Connection conexion;

    public OperacionDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void registrar(Operacion o) {
        String sql = "INSERT INTO Operaciones (Tipo_Operacion, ID_Producto, ID_Servicio, RUC_Cli, Costo_Total) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, o.getTipoOperacion());
            
            if (o.getIdProducto() != null) {
                stmt.setInt(2, o.getIdProducto());
            } else {
                stmt.setNull(2, java.sql.Types.INTEGER);
            }

            if (o.getIdServicio() != null) {
                stmt.setInt(3, o.getIdServicio());
            } else {
                stmt.setNull(3, java.sql.Types.INTEGER);
            }

            stmt.setString(4, o.getRucCliente());
            stmt.setDouble(5, o.getCostoTotal());

            stmt.executeUpdate();
            System.out.println("✅ Operación registrada correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al registrar operación: " + e.getMessage());
        }
    }

    public List<Operacion> listar() {
        List<Operacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM Operaciones";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Operacion o = new Operacion(
                    rs.getInt("ID_Operacion"),
                    rs.getString("Tipo_Operacion"),
                    rs.getObject("ID_Producto", Integer.class),
                    rs.getObject("ID_Servicio", Integer.class),
                    rs.getString("RUC_Cli"),
                    rs.getTimestamp("Fecha_Operacion"),
                    rs.getDouble("Costo_Total")
                );
                lista.add(o);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar operaciones: " + e.getMessage());
        }
        return lista;
    }
        public void eliminar(int idOperacion) {
        String sql = "DELETE FROM Operaciones WHERE ID_Operacion = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idOperacion);
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("✅ Operación eliminada correctamente.");
            } else {
                System.out.println("⚠️ No se encontró una operación con ese ID.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar operación: " + e.getMessage());
        }
    }

    public void actualizar(Operacion o) {
        String sql = "UPDATE Operaciones SET Tipo_Operacion = ?, ID_Producto = ?, ID_Servicio = ?, RUC_Cli = ?, Costo_Total = ? WHERE ID_Operacion = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, o.getTipoOperacion());

            if (o.getIdProducto() != null) {
                stmt.setInt(2, o.getIdProducto());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }

            if (o.getIdServicio() != null) {
                stmt.setInt(3, o.getIdServicio());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }

            stmt.setString(4, o.getRucCliente());
            stmt.setDouble(5, o.getCostoTotal());
            stmt.setInt(6, o.getIdOperacion());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("✅ Operación actualizada correctamente.");
            } else {
                System.out.println("⚠️ No se encontró la operación.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar operación: " + e.getMessage());
        }
    }

}
