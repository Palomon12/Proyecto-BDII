/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.dao.ClienteDAO;
import com.mycompany.dao.ServicioDAO;
import com.mycompany.model.Cliente;
import com.mycompany.model.ContactoCliente;
import com.mycompany.model.Servicio;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ClienteController {

    private final ClienteDAO clienteDao;
    private final ServicioDAO servicioDao;
    private final Scanner scanner;

    public ClienteController(Connection conexion) {
        this.clienteDao = new ClienteDAO(conexion);
        this.servicioDao = new ServicioDAO(conexion);
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== MENÚ DE CLIENTES ===");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Agregar Servicio a Cliente");
            System.out.println("3. Listar Clientes");
            System.out.println("4. Listar todos los Servicios");
            System.out.println("5. Buscar Cliente por RUC");
            System.out.println("6. Buscar Servicios por Cliente");
            System.out.println("7. Actualizar Cliente");
            System.out.println("8. Eliminar Cliente");
            System.out.println("9. Eliminar Servicio");
            System.out.println("10. Salir al menú principal");
            System.out.println("11. Contar Clientes");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            try {
                switch (opcion) {
                    case 1 -> registrarCliente();
                    case 2 -> agregarServicioACliente();
                    case 3 -> listarClientes();
                    case 4 -> listarTodosLosServicios();
                    case 5 -> buscarClientePorRuc();
                    case 6 -> buscarServiciosPorCliente();
                    case 7 -> actualizarCliente();
                    case 8 -> eliminarCliente();
                    case 9 -> eliminarServicio();
                    case 10 -> salir = true;
                    case 11 -> {
                      int total = contarClientes();
                      System.out.println("📊 Total de clientes registrados: " + total);
                     }
                    default -> System.out.println("Opción no válida");
                }
            } catch (SQLException e) {
                System.err.println("❌ Error de base de datos: " + e.getMessage());
            }
        }
    }

    private void registrarCliente() throws SQLException {
        System.out.println("\n--- REGISTRAR CLIENTE ---");
        System.out.print("RUC (11 dígitos): ");
        String ruc = scanner.nextLine();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Sector: ");
        String sector = scanner.nextLine();

        System.out.print("Teléfono (9 dígitos): ");
        String telefono = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        ContactoCliente contacto = new ContactoCliente(telefono, email);
        Cliente cliente = new Cliente(ruc, nombre, sector, contacto);

        clienteDao.insertarCliente(cliente);
        System.out.println("✅ Cliente registrado exitosamente");
    }

    private void agregarServicioACliente() throws SQLException {
        System.out.println("\n--- AGREGAR SERVICIO AL CLIENTE ---");
        System.out.print("RUC del cliente: ");
        String ruc = scanner.nextLine();

        System.out.print("ID del servicio: ");
        int idServicio = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nombre del servicio: ");
        String nombreServ = scanner.nextLine();

        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        System.out.print("Tarifa base: ");
        double tarifa = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("ID Departamento: ");
        int idDepto = scanner.nextInt();

        System.out.print("ID Colaborador: ");
        int idColab = scanner.nextInt();
        scanner.nextLine();

        Servicio servicio = new Servicio(idServicio, nombreServ, descripcion, tarifa, idDepto, idColab, ruc);
        servicioDao.insertarServicio(servicio);

        System.out.println("✅ Servicio agregado exitosamente");
    }

    private void listarClientes() throws SQLException {
        System.out.println("\n--- LISTA DE CLIENTES ---");
        List<Cliente> clientes = clienteDao.listarClientes();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados");
            return;
        }

        for (Cliente cliente : clientes) {
            System.out.println("RUC: " + cliente.getRucCli());
            System.out.println("Nombre: " + cliente.getNombreCli());
            System.out.println("Sector: " + cliente.getSectorCli());
            System.out.println("Teléfono: " + cliente.getContacto().getTelefonoCli());
            System.out.println("Email: " + cliente.getContacto().getCorreoCli());
            System.out.println("-----------------------");
        }
    }
    
    private void listarTodosLosServicios() throws SQLException {
    System.out.println("\n--- LISTA DE SERVICIOS DISPONIBLES ---");

    List<Servicio> servicios = servicioDao.listarTodos();

    if (servicios.isEmpty()) {
        System.out.println("No hay servicios registrados.");
        return;
    }

    for (Servicio servicio : servicios) {
        System.out.println("ID: " + servicio.getIdServicio());
        System.out.println("Nombre: " + servicio.getNombreServ());
        System.out.println("Descripción: " + servicio.getDescripcionServ());
        System.out.println("Tarifa Base: S/" + servicio.getTarifaBaseServ());
        System.out.println("Departamento ID: " + servicio.getDepartamentoId());
        System.out.println("ID Colaborador: " + servicio.getIdColaborador());
        System.out.println("Cliente RUC: " + servicio.getRucCli());
        System.out.println("-----------------------------");
    }
}


    private void buscarClientePorRuc() throws SQLException {
        System.out.println("\n--- BUSCAR CLIENTE POR RUC ---");
        System.out.print("Ingrese RUC: ");
        String ruc = scanner.nextLine();

        Cliente cliente = clienteDao.obtenerPorRuc(ruc);
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
        } else {
            System.out.println("RUC: " + cliente.getRucCli());
            System.out.println("Nombre: " + cliente.getNombreCli());
            System.out.println("Sector: " + cliente.getSectorCli());
            System.out.println("Teléfono: " + cliente.getContacto().getTelefonoCli());
            System.out.println("Email: " + cliente.getContacto().getCorreoCli());
        }
    }

    private void buscarServiciosPorCliente() throws SQLException {
        System.out.println("\n--- SERVICIOS POR CLIENTE ---");
        System.out.print("Ingrese RUC del cliente: ");
        String ruc = scanner.nextLine();

        List<Servicio> servicios = servicioDao.listarPorCliente(ruc);

        if (servicios.isEmpty()) {
            System.out.println("El cliente no tiene servicios registrados");
            return;
        }

        System.out.println("Servicios encontrados:");
        for (Servicio servicio : servicios) {
            System.out.println("ID: " + servicio.getIdServicio());
            System.out.println("Nombre: " + servicio.getNombreServ());
            System.out.println("Tarifa: S/" + servicio.getTarifaBaseServ());
            System.out.println("-----------------------");
        }
    }

    private void actualizarCliente() throws SQLException {
        System.out.println("\n--- ACTUALIZAR CLIENTE ---");
        System.out.print("Ingrese RUC del cliente a actualizar: ");
        String ruc = scanner.nextLine();

        Cliente cliente = clienteDao.obtenerPorRuc(ruc);
        if (cliente == null) {
            System.out.println("❌ Cliente no encontrado");
            return;
        }

        System.out.print("Nuevo nombre (" + cliente.getNombreCli() + "): ");
        String nombre = scanner.nextLine();

        System.out.print("Nuevo sector (" + cliente.getSectorCli() + "): ");
        String sector = scanner.nextLine();

        System.out.print("Nuevo teléfono (" + cliente.getContacto().getTelefonoCli() + "): ");
        String telefono = scanner.nextLine();

        System.out.print("Nuevo email (" + cliente.getContacto().getCorreoCli() + "): ");
        String email = scanner.nextLine();

        cliente.setNombreCli(nombre);
        cliente.setSectorCli(sector);
        cliente.getContacto().setTelefonoCli(telefono);
        cliente.getContacto().setCorreoCli(email);

        clienteDao.actualizarCliente(cliente);
        System.out.println("✅ Cliente actualizado correctamente");
    }

    private void eliminarCliente() throws SQLException {
        System.out.println("\n--- ELIMINAR CLIENTE ---");
        System.out.print("Ingrese RUC del cliente a eliminar: ");
        String ruc = scanner.nextLine();

        clienteDao.eliminarCliente(ruc);
        System.out.println("✅ Cliente eliminado correctamente");
    }

    private void eliminarServicio() throws SQLException {
        System.out.println("\n--- ELIMINAR SERVICIO ---");
        System.out.print("Ingrese ID del servicio a eliminar: ");
        int idServicio = scanner.nextInt();
        scanner.nextLine();

        servicioDao.eliminarServicio(idServicio);
        System.out.println("✅ Servicio eliminado correctamente");
    }
     // ✅ NUEVO MÉTODO PARA CONTAR CLIENTES
    public int contarClientes() throws SQLException {
        return clienteDao.contarClientes();
    }
}