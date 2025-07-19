/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

public class Proveedor {
    private String idProveedor;
    private String nombreMarca;
    private String pais;
    private String lineaProducto;
    private int aniosRelacion;

    public Proveedor(String idProveedor, String nombreMarca, String pais, String lineaProducto, int aniosRelacion) {
        this.idProveedor = idProveedor;
        this.nombreMarca = nombreMarca;
        this.pais = pais;
        this.lineaProducto = lineaProducto;
        this.aniosRelacion = aniosRelacion;
    }

    public String getIdProveedor() { return idProveedor; }
    public String getNombreMarca() { return nombreMarca; }
    public String getPais() { return pais; }
    public String getLineaProducto() { return lineaProducto; }
    public int getAniosRelacion() { return aniosRelacion; }

    public void setIdProveedor(String idProveedor) { this.idProveedor = idProveedor; }
    public void setNombreMarca(String nombreMarca) { this.nombreMarca = nombreMarca; }
    public void setPais(String pais) { this.pais = pais; }
    public void setLineaProducto(String lineaProducto) { this.lineaProducto = lineaProducto; }
    public void setAniosRelacion(int aniosRelacion) { this.aniosRelacion = aniosRelacion; }
}