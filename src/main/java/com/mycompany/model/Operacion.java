package com.mycompany.model;

import java.sql.Timestamp;

public class Operacion {
    private int idOperacion;
    private String tipoOperacion; // "Compra" o "Alquiler"
    private Integer idProducto;   // puede ser null
    private Integer idServicio;   // puede ser null
    private String rucCliente;
    private Timestamp fechaOperacion;
    private double costoTotal;

    // Constructor sin ID ni fecha (para insertar)
    public Operacion(String tipoOperacion, Integer idProducto, Integer idServicio, String rucCliente, double costoTotal) {
        this.tipoOperacion = tipoOperacion;
        this.idProducto = idProducto;
        this.idServicio = idServicio;
        this.rucCliente = rucCliente;
        this.costoTotal = costoTotal;
    }

    // Constructor completo (para listar)
    public Operacion(int idOperacion, String tipoOperacion, Integer idProducto, Integer idServicio, String rucCliente, Timestamp fechaOperacion, double costoTotal) {
        this.idOperacion = idOperacion;
        this.tipoOperacion = tipoOperacion;
        this.idProducto = idProducto;
        this.idServicio = idServicio;
        this.rucCliente = rucCliente;
        this.fechaOperacion = fechaOperacion;
        this.costoTotal = costoTotal;
    }
    
    // Constructor para actualizar (sin fecha)
public Operacion(int idOperacion, String tipoOperacion, Integer idProducto, Integer idServicio, String rucCliente, double costoTotal) {
    this.idOperacion = idOperacion;
    this.tipoOperacion = tipoOperacion;
    this.idProducto = idProducto;
    this.idServicio = idServicio;
    this.rucCliente = rucCliente;
    this.costoTotal = costoTotal;
}


    // Getters y Setters
    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public String getRucCliente() {
        return rucCliente;
    }

    public void setRucCliente(String rucCliente) {
        this.rucCliente = rucCliente;
    }

    public Timestamp getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Timestamp fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }
}
