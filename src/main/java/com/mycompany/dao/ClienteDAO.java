/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.model.Cliente;
import com.mycompany.model.ContactoCliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Milagros
 */
public class ClienteDAO {

    private Connection conexion;

    public ClienteDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // INSERTAR CLIENTE (con transacción para Contacto_Cliente)
    public void insertarCliente(Cliente cliente) throws SQLException {
        String sqlContacto = "INSERT INTO Contacto_Cliente (Telefono_Cli, Correo_Cli) VALUES (?, ?)";
        String sqlCliente = "INSERT INTO Cliente (RUC_Cli, Nombre_Cli, Sector_Cli, ID_Contacto) VALUES (?, ?, ?, ?)";

        try (PreparedStatement psContacto = conexion.prepareStatement(sqlContacto, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psCliente = conexion.prepareStatement(sqlCliente)) {

            conexion.setAutoCommit(false);  // Iniciar transacción

            // 1. Insertar contacto
            psContacto.setString(1, cliente.getContacto().getTelefonoCli());
            psContacto.setString(2, cliente.getContacto().getCorreoCli());
            psContacto.executeUpdate();

            // Obtener ID del contacto insertado
            int idContacto;
            try (ResultSet rs = psContacto.getGeneratedKeys()) {
                if (rs.next()) {
                    idContacto = rs.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID del contacto");
                }
            }

            // 2. Insertar cliente
            psCliente.setString(1, cliente.getRucCli());
            psCliente.setString(2, cliente.getNombreCli());
            psCliente.setString(3, cliente.getSectorCli());
            psCliente.setInt(4, idContacto);
            psCliente.executeUpdate();

            conexion.commit();  // Confirmar transacción

        } catch (SQLException e) {
            conexion.rollback();  // Revertir en caso de error
            throw e;
        } finally {
            conexion.setAutoCommit(true);
        }
    }

    // OBTENER CLIENTE POR RUC
    public Cliente obtenerPorRuc(String ruc) throws SQLException {
        String sql = "SELECT c.*, cc.Telefono_Cli, cc.Correo_Cli FROM Cliente c "
                   + "JOIN Contacto_Cliente cc ON c.ID_Contacto = cc.ID_Contacto "
                   + "WHERE c.RUC_Cli = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, ruc);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ContactoCliente contacto = new ContactoCliente(
                        rs.getString("Telefono_Cli"),
                        rs.getString("Correo_Cli")
                    );
                    
                    return new Cliente(
                        rs.getString("RUC_Cli"),
                        rs.getString("Nombre_Cli"),
                        rs.getString("Sector_Cli"),
                        contacto
                    );
                }
            }
        }
        return null;
    }

    // LISTAR TODOS LOS CLIENTES
    public List<Cliente> listarClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT c.*, cc.Telefono_Cli, cc.Correo_Cli FROM Cliente c "
                   + "JOIN Contacto_Cliente cc ON c.ID_Contacto = cc.ID_Contacto";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                ContactoCliente contacto = new ContactoCliente(
                    rs.getString("Telefono_Cli"),
                    rs.getString("Correo_Cli")
                );
                
                clientes.add(new Cliente(
                    rs.getString("RUC_Cli"),
                    rs.getString("Nombre_Cli"),
                    rs.getString("Sector_Cli"),
                    contacto
                ));
            }
        }
        return clientes;
    }

    // ACTUALIZAR CLIENTE
    public void actualizarCliente(Cliente cliente) throws SQLException {
        String sqlCliente = "UPDATE Cliente SET Nombre_Cli = ?, Sector_Cli = ? WHERE RUC_Cli = ?";
        String sqlContacto = "UPDATE Contacto_Cliente SET Telefono_Cli = ?, Correo_Cli = ? "
                           + "WHERE ID_Contacto = (SELECT ID_Contacto FROM Cliente WHERE RUC_Cli = ?)";
        
        try (PreparedStatement psCliente = conexion.prepareStatement(sqlCliente);
             PreparedStatement psContacto = conexion.prepareStatement(sqlContacto)) {
            
            conexion.setAutoCommit(false);
            
            // 1. Actualizar cliente
            psCliente.setString(1, cliente.getNombreCli());
            psCliente.setString(2, cliente.getSectorCli());
            psCliente.setString(3, cliente.getRucCli());
            psCliente.executeUpdate();
            
            // 2. Actualizar contacto
            psContacto.setString(1, cliente.getContacto().getTelefonoCli());
            psContacto.setString(2, cliente.getContacto().getCorreoCli());
            psContacto.setString(3, cliente.getRucCli());
            psContacto.executeUpdate();
            
            conexion.commit();
            
        } catch (SQLException e) {
            conexion.rollback();
            throw e;
        } finally {
            conexion.setAutoCommit(true);
        }
    }

    // ELIMINAR CLIENTE (elimina también su contacto)
    public void eliminarCliente(String ruc) throws SQLException {
        String sqlDeleteCliente = "DELETE FROM Cliente WHERE RUC_Cli = ?";
        String sqlDeleteContacto = "DELETE FROM Contacto_Cliente WHERE ID_Contacto = "
                                 + "(SELECT ID_Contacto FROM Cliente WHERE RUC_Cli = ?)";
        
        try (PreparedStatement psCliente = conexion.prepareStatement(sqlDeleteCliente);
             PreparedStatement psContacto = conexion.prepareStatement(sqlDeleteContacto)) {
            
            conexion.setAutoCommit(false);
            
            // Eliminar cliente primero (por la FK)
            psCliente.setString(1, ruc);
            psCliente.executeUpdate();
            
            // Eliminar contacto asociado
            psContacto.setString(1, ruc);
            psContacto.executeUpdate();
            
            conexion.commit();
            
        } catch (SQLException e) {
            conexion.rollback();
            throw e;
        } finally {
            conexion.setAutoCommit(true);
        }
    }
}