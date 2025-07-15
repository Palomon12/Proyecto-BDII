/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;
/**
 *
 * @author Milagros
 */
public class Colaborador {
    private int id;
    private String nombre;
    private String puesto;
    private Sucursal sucursal;

    public Colaborador(int id, String nombre, String puesto, Sucursal sucursal) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.sucursal = sucursal;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getPuesto() { return puesto; }
    public Sucursal getSucursal() { return sucursal; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPuesto(String puesto) { this.puesto = puesto; }
    public void setSucursal(Sucursal sucursal) { this.sucursal = sucursal; }

    @Override
    public String toString() {
        return "Colaborador{" + "id=" + id + ", nombre=" + nombre + ", puesto=" + puesto +
                ", sucursal=" + sucursal.getNombre() + '}';
    }
}
