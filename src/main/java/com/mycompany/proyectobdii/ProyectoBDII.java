/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyectobdii;

import com.mycompany.bd.Conexion;
import java.sql.Connection;

/**
 *
 * @author Milagros
 */
public class ProyectoBDII {

    public static void main(String[] args) {
        Conexion c = new Conexion();
        Connection cn = c.conectar();

        System.out.println("Hello World!");
    }
}
