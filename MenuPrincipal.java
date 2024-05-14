/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author JESUS SANCHEZ
 */
import java.util.ArrayList;
import java.util.Scanner;

public class MenuPrincipal {
    private static ArrayList<Usuario> usuarios;

    public static void inicializarUsuarios() {
        usuarios = GestorUsuarios.cargarUsuarios();
    }

    public static void iniciarSesion() {
        Scanner scanner = new Scanner(System.in);
        String nombreUsuario;
        String contraseña;

        System.out.println("Ingrese su nombre de usuario:");
        nombreUsuario = scanner.nextLine();

        System.out.println("Ingrese su contraseña:");
        contraseña = scanner.nextLine();

        Usuario usuario = obtenerUsuarioPorCredenciales(nombreUsuario, contraseña);
        if (usuario != null) {
            System.out.println("Inicio de sesión exitoso. Bienvenido, " + usuario.getNombre() + "!");
            mostrarMenu(usuario);
        } else {
            System.out.println("Credenciales incorrectas. Inténtelo nuevamente.");
            iniciarSesion();
        }
    }

    private static Usuario obtenerUsuarioPorCredenciales(String nombreUsuario, String contraseña) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombreUsuario) && usuario.getContraseña().equals(contraseña)) {
                return usuario;
            }
        }
        return null;
    }

    public static void mostrarMenu(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Bienvenido, " + usuario.getNombre() + "!");
            System.out.println("1. Consultar Saldo");
            System.out.println("2. Depositar Dinero");
            System.out.println("3. Retirar Dinero");
            System.out.println("4. Transferir Dinero");
            System.out.println("5. Salir");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Su saldo actual es: $" + usuario.getSaldo());
                    break;

                case 2:
                    System.out.print("Ingrese la cantidad a depositar: $");
                    double deposito = scanner.nextDouble();
                    usuario.depositar(deposito);
                    System.out.println("Nuevo saldo: $" + usuario.getSaldo());
                    break;

                case 3:
                    System.out.print("Ingrese la cantidad a retirar: $");
                    double retiro = scanner.nextDouble();
                    usuario.retirar(retiro);
                    System.out.println("Nuevo saldo: $" + usuario.getSaldo());
                    break;

                case 4:
                    System.out.print("Ingrese el nombre de usuario destino (Jesus, Holmer o Deimer): ");
                    String nombreDestino = scanner.next();
                    Usuario destinatario = obtenerUsuarioPorNombre(nombreDestino);

                    if (destinatario != null) {
                        System.out.print("Ingrese la cantidad a transferir: $");
                        double transferencia = scanner.nextDouble();
                        usuario.transferir(destinatario, transferencia);
                    } else {
                        System.out.println("Usuario destino no encontrado.");
                    }
                    break;

                case 5:
                    System.out.println("Sesión cerrada. Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 5);

        scanner.close();
        GestorUsuarios.guardarUsuarios(usuarios);
    }

    private static Usuario obtenerUsuarioPorNombre(String nombre) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equalsIgnoreCase(nombre)) {
                return usuario;
            }
        }
        return null;
    }
}
