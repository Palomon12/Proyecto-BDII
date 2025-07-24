/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;


public class Producto {
    private int idProducto;
    private String nombre;
    private String descripcion;     
    private String tipo;            
    private int stock;              
    private double precioCompra;    
    private double precioAlquiler;  

    private Proveedor proveedor;

    public Producto(int idProducto, String nombre, String descripcion, String tipo,
                    int stock, double precioCompra, double precioAlquiler,
                    Proveedor proveedor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.stock = stock;
        this.precioCompra = precioCompra;
        this.precioAlquiler = precioAlquiler;

        this.proveedor = proveedor;
    }
    public Producto(int idProducto, String nombre, double precio, Proveedor proveedor) {
    this.idProducto = idProducto;
    this.nombre = nombre;

    this.proveedor = proveedor;
}

    public int getIdProducto() { return idProducto; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }          
    public String getTipo() { return tipo; }                        
    public int getStock() { return stock; }                       
    public double getPrecioCompra() { return precioCompra; }        
    public double getPrecioAlquiler() { return precioAlquiler; }    

    public Proveedor getProveedor() { return proveedor; }

    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }    // NUEVO
    public void setTipo(String tipo) { this.tipo = tipo; }                                // NUEVO
    public void setStock(int stock) { this.stock = stock; }                               // NUEVO
    public void setPrecioCompra(double precioCompra) { this.precioCompra = precioCompra; } // NUEVO
    public void setPrecioAlquiler(double precioAlquiler) { this.precioAlquiler = precioAlquiler; } // NUEVO

    public void setProveedor(Proveedor proveedor) { this.proveedor = proveedor; }
    
    public String toString() {
    return "Producto{" +
            "ID=" + idProducto +
            ", Nombre='" + nombre + '\'' +
            ", Descripción='" + descripcion + '\'' +
            ", Tipo='" + tipo + '\'' +
            ", Stock=" + stock +
            ", PrecioCompra=" + precioCompra +
            ", PrecioAlquiler=" + precioAlquiler +
            ", Proveedor=" + (proveedor != null ? proveedor.getIdProveedor() : "N/A") +
            '}';
}
}