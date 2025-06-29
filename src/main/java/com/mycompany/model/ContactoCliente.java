/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author EIDAN
 */
public class ContactoCliente {

    private String telefonoCli;
    private String correoCli;

    public ContactoCliente(String telefonoCli, String correoCli) {
        setTelefonoCli(telefonoCli);
        setCorreoCli(correoCli);
    }

    // Getters
    public String getTelefonoCli() {
        return telefonoCli;
    }

    public String getCorreoCli() {
        return correoCli;
    }

    // Setters con validaciones
    public void setTelefonoCli(String telefonoCli) {
        if (telefonoCli == null || !telefonoCli.matches("\\d{9}")) {
            throw new IllegalArgumentException("Teléfono debe tener 9 dígitos");
        }
        this.telefonoCli = telefonoCli;
    }

    public void setCorreoCli(String correoCli) {
        if (correoCli == null || !correoCli.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$")) {
            throw new IllegalArgumentException("Correo electrónico inválido");
        }
        this.correoCli = correoCli.toLowerCase();
    }
}