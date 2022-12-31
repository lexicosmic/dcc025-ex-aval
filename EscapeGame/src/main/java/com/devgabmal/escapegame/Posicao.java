/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devgabmal.escapegame;

/**
 *
 * @author devgabmal
 */
public class Posicao {

    Entidade entidade;

    public Posicao(char tipoEntidade) {
        this.entidade = new Entidade(tipoEntidade);
    }

    public char getSimbolo(boolean exibirSimbolosEscondidos) {
        return this.entidade.getSimbolo(exibirSimbolosEscondidos);
    }

}
