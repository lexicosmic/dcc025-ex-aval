/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devgabmal.bingo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author devgabmal
 */
public class Jogo {

    private int tipoJogo;
    private Cartela cartelas;

    public Jogo(int tipoJogo, int quantidadeCartelas) {
        this.tipoJogo = tipoJogo;

        List<Cartela> listaCartelas = new ArrayList<Cartela>();
        for (int i = 0; i < quantidadeCartelas; i++) {
            Cartela cartela = new Cartela();
        }
        this.cartelas = cartelas;
    }

}
