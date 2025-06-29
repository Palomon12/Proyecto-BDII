/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;
import com.mycompany.model.Servicio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Milagros
 */
public class ServicioDAO {

    private final Connection conexion;

    public ServicioDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // INSERTAR SERVICIO
    public void insertarServicio(Servicio servicio) throws SQLException {
        String sql = "INSERT INTO servicio (ID_Servicio, Nombre_Serv, Descripcion_Serv, "
                   + "Tarifa_Base_Serv, Departamento_ID, ID_Colaborador, RUC_Cli) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, servicio.getIdServicio());
            ps.setString(2, servicio.getNombreServ());
            ps.setString(3, servicio.getDescripcionServ());
            ps.setDouble(4, servicio.getTarifaBaseServ());
            ps.setInt(5, servicio.getDepartamentoId());
            ps.setInt(6, servicio.getIdColaborador());
            ps.setString(7, servicio.getRucCli());
            
            ps.executeUpdate();
        }
    }

    // OBTENER SERVICIO POR ID
    public Servicio obtenerPorId(int idServicio) throws SQLException {
        String sql = "SELECT * FROM servicio WHERE ID_Servicio = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idServicio);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearServicio(rs);
                }
            }
        }
        return null;
    }

    // LISTAR TODOS LOS SERVICIOS
    public List<Servicio> listarTodos() throws SQLException {
        List<Servicio> servicios = new ArrayList<>();
        String sql = "SELECT * FROM servicio";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                servicios.add(mapearServicio(rs));
            }
        }
        return servicios;
    }

    // ACTUALIZAR SERVICIO
    public void actualizarServicio(Servicio servicio) throws SQLException {
        String sql = "UPDATE servicio SET Nombre_Serv = ?, Descripcion_Serv = ?, "
                   + "Tarifa_Base_Serv = ?, Departamento_ID = ?, ID_Colaborador = ?, "
                   + "RUC_Cli = ? WHERE ID_Servicio = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, servicio.getNombreServ());
            ps.setString(2, servicio.getDescripcionServ());
            ps.setDouble(3, servicio.getTarifaBaseServ());
            ps.setInt(4, servicio.getDepartamentoId());
            ps.setInt(5, servicio.getIdColaborador());
            ps.setString(6, servicio.getRucCli());
            ps.setInt(7, servicio.getIdServicio());
            
            ps.executeUpdate();
        }
    }

    // ELIMINAR SERVICIO
    public void eliminarServicio(int idServicio) throws SQLException {
        String sql = "DELETE FROM servicio WHERE ID_Servicio = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idServicio);
            ps.executeUpdate();
        }
    }

    // MAPEAR RESULTADO SQL A OBJETO SERVICIO
    private Servicio mapearServicio(ResultSet rs) throws SQLException {
        return new Servicio(
            rs.getInt("ID_Servicio"),
            rs.getString("Nombre_Serv"),
            rs.getString("Descripcion_Serv"),
            rs.getDouble("Tarifa_Base_Serv"),
            rs.getInt("Departamento_ID"),
            rs.getInt("ID_Colaborador"),
            rs.getString("RUC_Cli")
        );
    }

    // OBTENER SERVICIOS POR CLIENTE (RUC)
    public List<Servicio> listarPorCliente(String rucCli) throws SQLException {
        List<Servicio> servicios = new ArrayList<>();
        String sql = "SELECT * FROM servicio WHERE RUC_Cli = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, rucCli);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    servicios.add(mapearServicio(rs));
                }
            }
        }
        return servicios;
    }
}