/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.model.Colaborador;
import java.util.ArrayList;
import java.util.List;

public class ColaboradorDAO {
    private List<Colaborador> colaboradores = new ArrayList<>();

    public void agregar(Colaborador c) {
        colaboradores.add(c);
    }

    public List<Colaborador> listar() {
        return colaboradores;
    }

    public List<Colaborador> listarPorSucursal(int idSucursal) {
        List<Colaborador> resultado = new ArrayList<>();
        for (Colaborador c : colaboradores) {
            if (c.getSucursal().getId() == idSucursal) {
                resultado.add(c);
            }
        }
        return resultado;
    }
}
