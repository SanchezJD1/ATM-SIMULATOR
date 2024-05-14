/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author JESUS SANCHEZ
 */
import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;
    private String contraseña;
    private double saldo;

    public Usuario(String nombre, String contraseña, double saldoInicial) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.saldo = saldoInicial;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean verificarCredenciales(String nombreUsuario, String contraseña) {
        return this.nombre.equals(nombreUsuario) && this.contraseña.equals(contraseña);
    }

    public void depositar(double cantidad) {
        saldo += cantidad;
    }

    public boolean retirar(double cantidad) {
        if (cantidad <= saldo) {
            saldo -= cantidad;
            return true;
        } else {
            System.out.println("Fondos insuficientes");
            return false;
        }
    }

    public void transferir(Usuario destinatario, double cantidad) {
        if (retirar(cantidad)) {
            destinatario.depositar(cantidad);
            System.out.println("Transferencia exitosa.");
        } else {
            System.out.println("Fondos insuficientes para realizar la transferencia.");
        }
    }
}



