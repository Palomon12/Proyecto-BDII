/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.dao.OperacionDAO;
import com.mycompany.model.Operacion;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class OperacionController {
    private final OperacionDAO operacionDao;
    private final Scanner scanner;

    public OperacionController(Connection conexion) {
        this.operacionDao = new OperacionDAO(conexion);
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- Gestión de Operaciones ---");
            System.out.println("1. Registrar Operación");
            System.out.println("2. Listar Operaciones");
            System.out.println("3. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar

            switch (opcion) {
                case 1 -> registrarOperacion();
                case 2 -> listarOperaciones();
                case 3 -> salir = true;
                default -> System.out.println("⚠️ Opción no válida.");
            }
        }
    }

    private void registrarOperacion() {
        System.out.print("Tipo de operación (Compra/Alquiler): ");
        String tipo = scanner.nextLine();

        Integer idProd = null;
        Integer idServ = null;

        if (tipo.equalsIgnoreCase("Compra")) {
            System.out.print("ID del producto: ");
            idProd = scanner.nextInt();
            scanner.nextLine();
        } else if (tipo.equalsIgnoreCase("Alquiler")) {
            System.out.print("ID del servicio: ");
            idServ = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.print("RUC del cliente: ");
        String ruc = scanner.nextLine();

        System.out.print("Costo total: ");
        double costo = scanner.nextDouble();
        scanner.nextLine();

        Operacion o = new Operacion(tipo, idProd, idServ, ruc, costo);
        operacionDao.registrar(o);
    }

    private void listarOperaciones() {
        List<Operacion> lista = operacionDao.listar();
        if (lista.isEmpty()) {
            System.out.println("⚠️ No hay operaciones registradas.");
            return;
        }
        System.out.println("=== Lista de Operaciones ===");
        for (Operacion o : lista) {
            System.out.println("ID: " + o.getIdOperacion() +
                    " | Tipo: " + o.getTipoOperacion() +
                    " | Producto: " + o.getIdProducto() +
                    " | Servicio: " + o.getIdServicio() +
                    " | RUC Cliente: " + o.getRucCliente() +
                    " | Fecha: " + o.getFechaOperacion() +
                    " | Costo: S/ " + o.getCostoTotal());
        }
    }
}
