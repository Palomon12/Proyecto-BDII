/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Milagros
 */
public class Servicio {

    private int idServicio;
    private String nombreServ;
    private String descripcionServ;
    private double tarifaBaseServ;
    private int departamentoId;
    private int idColaborador;
    private String rucCli; // Relación con Cliente

    // --------------------- CONSTRUCTORES ---------------------
    public Servicio() {} // Constructor vacío (para JPA/Hibernate)

    public Servicio(int idServicio, String nombreServ, String descripcionServ, 
                  double tarifaBaseServ, int departamentoId, int idColaborador, 
                  String rucCli) {
        setIdServicio(idServicio);
        setNombreServ(nombreServ);
        setDescripcionServ(descripcionServ);
        setTarifaBaseServ(tarifaBaseServ);
        setDepartamentoId(departamentoId);
        setIdColaborador(idColaborador);
        setRucCli(rucCli);
    }

    // --------------------- GETTERS ---------------------
    public int getIdServicio() {
        return idServicio;
    }

    public String getNombreServ() {
        return nombreServ;
    }

    public String getDescripcionServ() {
        return descripcionServ;
    }

    public double getTarifaBaseServ() {
        return tarifaBaseServ;
    }

    public int getDepartamentoId() {
        return departamentoId;
    }

    public int getIdColaborador() {
        return idColaborador;
    }

    public String getRucCli() {
        return rucCli;
    }

    // --------------------- SETTERS CON VALIDACIONES ---------------------
    public void setIdServicio(int idServicio) {
        if (idServicio <= 0) {
            throw new IllegalArgumentException("El ID del servicio debe ser positivo");
        }
        this.idServicio = idServicio;
    }

    public void setNombreServ(String nombreServ) {
        if (nombreServ == null || nombreServ.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del servicio es obligatorio");
        }
        if (nombreServ.length() > 30) {
            throw new IllegalArgumentException("El nombre no puede exceder 30 caracteres");
        }
        this.nombreServ = nombreServ.trim();
    }

    public void setDescripcionServ(String descripcionServ) {
        if (descripcionServ != null && descripcionServ.length() > 100) {
            throw new IllegalArgumentException("La descripción no puede exceder 100 caracteres");
        }
        this.descripcionServ = descripcionServ != null ? descripcionServ.trim() : null;
    }

    public void setTarifaBaseServ(double tarifaBaseServ) {
        if (tarifaBaseServ < 0) {
            throw new IllegalArgumentException("La tarifa no puede ser negativa");
        }
        if (tarifaBaseServ > 9999.99) {
            throw new IllegalArgumentException("La tarifa no puede exceder 9999.99");
        }
        this.tarifaBaseServ = tarifaBaseServ;
    }

    public void setDepartamentoId(int departamentoId) {
        if (departamentoId <= 0) {
            throw new IllegalArgumentException("El ID de departamento debe ser positivo");
        }
        this.departamentoId = departamentoId;
    }

    public void setIdColaborador(int idColaborador) {
        if (idColaborador <= 0) {
            throw new IllegalArgumentException("El ID de colaborador debe ser positivo");
        }
        this.idColaborador = idColaborador;
    }

    public void setRucCli(String rucCli) {
        if (rucCli == null || !rucCli.matches("\\d{11}")) {
            throw new IllegalArgumentException("El RUC debe tener 11 dígitos");
        }
        this.rucCli = rucCli;
    }

    // --------------------- MÉTODOS ADICIONALES ---------------------
    @Override
    public String toString() {
        return String.format(
            "Servicio [ID=%d, Nombre=%s, Tarifa=%.2f, Cliente=%s]",
            idServicio, nombreServ, tarifaBaseServ, rucCli
        );
    }
}