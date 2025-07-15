/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author JHON
 */
public class Proveedor {
    private String id;
    private String nombre;
    private String pais;
    private String sector;
    private int aniosRelacion;

    public Proveedor() {}

    public Proveedor(String id, String nombre, String pais, String sector, int aniosRelacion) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.sector = sector;
        this.aniosRelacion = aniosRelacion;
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }

    public int getAniosRelacion() { return aniosRelacion; }
    public void setAniosRelacion(int aniosRelacion) { this.aniosRelacion = aniosRelacion; }

    @Override
    public String toString() {
        return nombre;
    }
}
