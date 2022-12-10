/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devgabmal.bingo;

/**
 *
 * @author devgabmal
 */
public class EspacoLivre implements Espaco {

    private char simbolo;

    public EspacoLivre() {
        this.simbolo = ' ';
    }

    @Override
    public void imprime() {
        System.out.print("  " + this.simbolo + "  ");
    }

    @Override
    public boolean isMarcado() {
        return true;
    }

    @Override
    public void marca() {

    }

    @Override
    public boolean compara(int numero) {
        return false;
    }

}
