/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.main;

import com.mycompany.util.ConexionMySQL;
import java.sql.Connection;

/**
 *
 * @author Milagros
 */
public class ProyectoBDII {

    public static void main(String[] args) {
        ConexionMySQL c = new ConexionMySQL();
        Connection cn = c.conectar();
        
        System.out.println("Hello mundo como estas ,eh modificado!, voy a tratar de hacero bien");
    }
}
