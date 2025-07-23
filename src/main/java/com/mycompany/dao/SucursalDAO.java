/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.model.Sucursal;
import java.util.ArrayList;
import java.util.List;

public class SucursalDAO {
    private List<Sucursal> sucursales = new ArrayList<>();

    public void agregar(Sucursal s) {
        sucursales.add(s);
    }

    public List<Sucursal> listar() {
        return sucursales;
    }

    public Sucursal buscarPorId(int id) {
        for (Sucursal s : sucursales) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
    
    public boolean actualizar(Sucursal sucursalActualizada) {
    for (int i = 0; i < sucursales.size(); i++) {
        if (sucursales.get(i).getId() == sucursalActualizada.getId()) {
            sucursales.set(i, sucursalActualizada);
            return true;
        }
    }
    return false;
}
    
    public boolean eliminar(int id) {
    Sucursal s = buscarPorId(id);
    if (s != null) {
        sucursales.remove(s);
        return true;
    }
    return false;
}

    public List<Sucursal> buscarPorCiudad(String ciudad) {
    List<Sucursal> resultado = new ArrayList<>();
    for (Sucursal s : sucursales) {
        if (s.getCiudad().equalsIgnoreCase(ciudad)) {
            resultado.add(s);
        }
    }
    return resultado;
}

}