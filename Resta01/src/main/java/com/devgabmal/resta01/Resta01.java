/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.devgabmal.resta01;

/**
 *
 * @author gabriel
 */
public class Resta01 {

    int[][] tabuleiro;
    private final int dimensao = 7;

    public Resta01() {
        this.tabuleiro = new int[dimensao][dimensao];
        this.populaTabuleiro();
    }

    private void populaTabuleiro() {
        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                if ((i == 2 || i == 3 || i == 4)
                        || (j == 2 || j == 3 || j == 4)) {
                    tabuleiro[i][j] = 1;
                } else {
                    tabuleiro[i][j] = -1;
                }
            }
        }
        tabuleiro[dimensao / 2][dimensao / 2] = 0;
    }

    private String converteValor(int valor) {
        if (valor == -1) {
            return " ";
        } else {
            return String.valueOf(valor);
        }
    }

    private void imprimeTabuleiro() {
        for (int i = -1; i < dimensao; i++) {
            // Borda lateral
            if (i != -1) {
                System.out.print(i + " ");
            }
            for (int j = -1; j < dimensao; j++) {
                // Borda superior
                if (i == -1) {
                    System.out.print(converteValor(j) + " ");
                } else if (j != -1) {
                    System.out.print(
                            converteValor(tabuleiro[i][j]) + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Resta01 jogo = new Resta01();
        jogo.imprimeTabuleiro();
    }
}
