/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devgabmal.bingo;

/**
 *
 * @author devgabmal
 */
public class EspacoNumerico implements Espaco {

    private int numero;
    private boolean marcado;

    public EspacoNumerico(int numero) {
        this.numero = numero;
        this.marcado = false;
    }

    @Override
    public void imprime() {
        String numeroString = numero > 9 ? String.valueOf(numero) : "0" + numero;
        String simboloMarcado = this.marcado ? "X" : " ";
        System.out.print("[" + simboloMarcado + "]" + numeroString);
    }

    @Override
    public boolean isMarcado() {
        return this.marcado;
    }

    @Override
    public void marca() {
        this.marcado = true;
    }

    @Override
    public boolean compara(int numero) {
        return this.numero == numero;
    }

}
