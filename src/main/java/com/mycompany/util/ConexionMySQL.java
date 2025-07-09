package com.mycompany.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    protected Connection conexion;
    private static final String URL = "jdbc:mysql://localhost:3306/ferreyrosg6";
    private static final String USER = "root";
    private static final String PASS = "Leopoldox1.2.3.4.5";
    

    public Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conectada a la bd");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return conexion;
    }

    public void cerrar() {
        if (conexion != null) {
            try { conexion.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}