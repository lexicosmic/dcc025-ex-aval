/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devgabmal.escapegame;

import java.util.Random;

/**
 *
 * @author devgabmal
 */
public class Tabuleiro {

    int ordem;
    Posicao matriz[];
    char entidades[];
    Random random;

    public Tabuleiro(int ordem, int dificuldade) {
        this.ordem = ordem;
        this.matriz = new Posicao[ordem * ordem];
        this.entidades = new char[ordem * ordem];
        this.random = new Random();

        // Determina quantidade de bombas
        int quantidadeBombas;
        if (dificuldade == 0) {
            quantidadeBombas = 12;
        } else if (dificuldade == 1) {
            quantidadeBombas = 20;
        } else {
            quantidadeBombas = 30;
        }

        // Preenche lista de códigos de entidades
        this.entidades[this.geraPosicaoValida()] = 'S';
        for (int i = 0; i < quantidadeBombas + 1; i++) {
            entidades[this.geraPosicaoValida()] = 'B';
        }

        // Instancia posições
        for (int i = 0; i < ordem * ordem; i++) {
            this.matriz[i] = new Posicao(entidades[i]);
        }
    }

    public void imprime(boolean fimDeJogo, int posicaoPersonagem[]) {
        for (int i = 0; i < this.ordem; i++) {
            for (int j = 0; j < this.ordem; j++) {
                char simbolo;
                if (i == posicaoPersonagem[0] && j == posicaoPersonagem[1]) {
                    simbolo = 'P';
                } else {
                    int indice = i * ordem + j;
                    simbolo = this.matriz[indice].getSimbolo(fimDeJogo);
                }
                System.out.print("| " + simbolo + " ");
            }
            System.out.println("|");
        }
    }

    private int geraPosicaoValida() {
        int posicaoAleatoria;
        do {
            posicaoAleatoria = this.random.nextInt(1, ordem * ordem);
        } while (this.entidades[posicaoAleatoria] != '\u0000');
        return posicaoAleatoria;
    }

    public char getSimbolo(int linha, int coluna) {
        int indice = linha * ordem + coluna;
        return this.matriz[indice].getSimbolo(true);
    }
}
