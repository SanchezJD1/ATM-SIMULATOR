/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author JESUS SANCHEZ
 */
import java.io.*;
import java.util.ArrayList;

public class GestorUsuarios {
    private static final String NOMBRE_ARCHIVO = "C:\\Users\\JESUS SANCHEZ\\Documents\\NetBeansProjects\\Main\\src\\main\\java\\com\\mycompany\\main\\usuarios.txt";

    public static ArrayList<Usuario> cargarUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                usuarios.add(new Usuario(datos[0], datos[1], Double.parseDouble(datos[2])));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de usuarios no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al cargar usuarios.");
        }

        return usuarios;
    }

    public static void guardarUsuarios(ArrayList<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO))) {
            for (Usuario usuario : usuarios) {
                writer.write(usuario.getNombre() + "," + usuario.getContraseña() + "," + usuario.getSaldo() + "\n");
            }
            System.out.println("Usuarios guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios.");
        }
    }
}

