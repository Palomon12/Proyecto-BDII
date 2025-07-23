/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author axel hizo esta modificacion
 */
public class ContactoCliente {

    private int idContacto;
    private String telefonoCli;
    private String correoCli;
    private Cliente cliente;  // relación con Cliente (por RUC)

    public ContactoCliente(int idContacto, String telefonoCli, String correoCli, Cliente cliente) {
        setIdContacto(idContacto);
        setTelefonoCli(telefonoCli);
        setCorreoCli(correoCli);
        setCliente(cliente);
    }
    public ContactoCliente(String telefonoCli, String correoCli) {
    this.telefonoCli = telefonoCli;
    this.correoCli = correoCli;
}

    // Getters
    public int getIdContacto() {
        return idContacto;
    }

    public String getTelefonoCli() {
        return telefonoCli;
    }

    public String getCorreoCli() {
        return correoCli;
    }

    public Cliente getCliente() {
        return cliente;
    }

    // Setters
    public void setIdContacto(int idContacto) {
        if (idContacto <= 0) {
            throw new IllegalArgumentException("ID debe ser positivo.");
        }
        this.idContacto = idContacto;
    }

    public void setTelefonoCli(String telefonoCli) {
        if (telefonoCli == null || !telefonoCli.matches("\\d{9}")) {
            throw new IllegalArgumentException("Teléfono debe tener 9 dígitos.");
        }
        this.telefonoCli = telefonoCli;
    }

    public void setCorreoCli(String correoCli) {
        if (correoCli == null || !correoCli.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$")) {
            throw new IllegalArgumentException("Correo electrónico inválido.");
        }
        this.correoCli = correoCli.toLowerCase();
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Debe asignarse un cliente.");
        }
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "ContactoCliente{" +
                "idContacto=" + idContacto +
                ", telefonoCli='" + telefonoCli + '\'' +
                ", correoCli='" + correoCli + '\'' +
                ", cliente=" + (cliente != null ? cliente.getNombreCli() : "null") +
                '}';
    }
}