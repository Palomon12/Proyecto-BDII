/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

public class Producto {
    private int idProducto;
    private String nombre;
    private double precio;
    private Proveedor proveedor;

    public Producto(int idProducto, String nombre, double precio, Proveedor proveedor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.proveedor = proveedor;
    }

    public int getIdProducto() { return idProducto; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public Proveedor getProveedor() { return proveedor; }

    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setProveedor(Proveedor proveedor) { this.proveedor = proveedor; }
}