/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.*;
import com.mycompany.dao.*;

public class Davidcontroller {
    public static void main(String[] args) {
        SucursalDAO sucursalDAO = new SucursalDAO();
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO();

        Sucursal suc1 = new Sucursal(1, "Sucursal Lima", "Av. Arequipa 123");
        Sucursal suc2 = new Sucursal(2, "Sucursal Cusco", "Jr. Comercio 456");
        sucursalDAO.agregar(suc1);
        sucursalDAO.agregar(suc2);

        Colaborador col1 = new Colaborador(1, "Luis Pérez", "Técnico", suc1);
        Colaborador col2 = new Colaborador(2, "Ana Gómez", "Vendedora", suc2);
        colaboradorDAO.agregar(col1);
        colaboradorDAO.agregar(col2);

        System.out.println("Todos los colaboradores:");
        for (Colaborador c : colaboradorDAO.listar()) {
            System.out.println(c);
        }

        System.out.println("\nColaboradores de Sucursal Lima:");
        for (Colaborador c : colaboradorDAO.listarPorSucursal(1)) {
            System.out.println(c);
        }
    }
}
