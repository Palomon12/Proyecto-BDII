package com.mycompany.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Milagros
 */
public class Cliente {
 
    private String rucCli;
    private String nombreCli;
    private String sectorCli;
    private ContactoCliente contacto; // Relación 1:1

    // --------------------- CONSTRUCTORES ---------------------
    public Cliente() {} // Para JPA/Hibernate

    public Cliente(String rucCli, String nombreCli, String sectorCli, ContactoCliente contacto) {
        setRucCli(rucCli);
        setNombreCli(nombreCli);
        setSectorCli(sectorCli);
        setContacto(contacto);
    }

    // --------------------- GETTERS ---------------------
    public String getRucCli() {
        return rucCli;
    }

    public String getNombreCli() {
        return nombreCli;
    }

    public String getSectorCli() {
        return sectorCli;
    }

    public ContactoCliente getContacto() {
        return contacto;
    }

    // --------------------- SETTERS CON VALIDACIONES ---------------------
    public void setRucCli(String rucCli) {
        if (rucCli == null || !rucCli.matches("\\d{11}")) {
            throw new IllegalArgumentException("RUC debe tener 11 dígitos");
        }
        this.rucCli = rucCli;
    }

    public void setNombreCli(String nombreCli) {
        if (nombreCli == null || nombreCli.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre es obligatorio");
        }
        if (nombreCli.length() > 50) {
            throw new IllegalArgumentException("Nombre no puede exceder 50 caracteres");
        }
        this.nombreCli = nombreCli.trim();
    }

    public void setSectorCli(String sectorCli) {
        if (sectorCli != null && sectorCli.length() > 30) {
            throw new IllegalArgumentException("Sector no puede exceder 30 caracteres");
        }
        this.sectorCli = sectorCli != null ? sectorCli.trim() : null;
    }

    public void setContacto(ContactoCliente contacto) {
        if (contacto == null) {
            throw new IllegalArgumentException("Contacto es obligatorio");
        }
        this.contacto = contacto;
    }

    @Override
    public String toString() {
        return String.format(
            "Cliente[RUC=%s, Nombre=%s, Sector=%s]",
            rucCli, nombreCli, sectorCli
        );
    }
}