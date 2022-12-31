/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devgabmal.escapegame;

/**
 *
 * @author devgabmal
 */
public class Entidade {

    char simbolo;

    public Entidade(char tipoEntidade) {
        if (tipoEntidade == '\u0000') {
            this.simbolo = ' ';
        } else {
            this.simbolo = tipoEntidade;
        }
    }

    public char getSimbolo(boolean exibirSimbolosEscondidos) {
        if ((simbolo == 'B' || simbolo == 'S') && !exibirSimbolosEscondidos) {
            return ' ';
        } else {
            return this.simbolo;
        }
    }

}
