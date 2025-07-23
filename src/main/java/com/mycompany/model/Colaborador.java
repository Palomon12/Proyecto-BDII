/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.util.Date;

public class Colaborador {
    private int idColaborador;
    private String nombreCol;
    private int departamentoId;
    private String cargoCol;
    private Date fechaContratacion;
    private int idSucursal;
    private double sueldoCol;

    public Colaborador() {}

    public Colaborador(int idColaborador, String nombreCol, int departamentoId, String cargoCol, Date fechaContratacion, int idSucursal, double sueldoCol) {
        this.idColaborador = idColaborador;
        this.nombreCol = nombreCol;
        this.departamentoId = departamentoId;
        this.cargoCol = cargoCol;
        this.fechaContratacion = fechaContratacion;
        this.idSucursal = idSucursal;
        this.sueldoCol = sueldoCol;
    }

    public int getIdColaborador() { return idColaborador; }
    public String getNombreCol() { return nombreCol; }
    public int getDepartamentoId() { return departamentoId; }
    public String getCargoCol() { return cargoCol; }
    public Date getFechaContratacion() { return fechaContratacion; }
    public int getIdSucursal() { return idSucursal; }
    public double getSueldoCol() { return sueldoCol; }

    public void setIdColaborador(int idColaborador) { this.idColaborador = idColaborador; }
    public void setNombreCol(String nombreCol) { this.nombreCol = nombreCol; }
    public void setDepartamentoId(int departamentoId) { this.departamentoId = departamentoId; }
    public void setCargoCol(String cargoCol) { this.cargoCol = cargoCol; }
    public void setFechaContratacion(Date fechaContratacion) { this.fechaContratacion = fechaContratacion; }
    public void setIdSucursal(int idSucursal) { this.idSucursal = idSucursal; }
    public void setSueldoCol(double sueldoCol) { this.sueldoCol = sueldoCol; }

    @Override
    public String toString() {
        return "Colaborador{" +
                "idColaborador=" + idColaborador +
                ", nombreCol='" + nombreCol + '\'' +
                ", departamentoId=" + departamentoId +
                ", cargoCol='" + cargoCol + '\'' +
                ", fechaContratacion=" + fechaContratacion +
                ", idSucursal=" + idSucursal +
                ", sueldoCol=" + sueldoCol +
                '}';
    }
}