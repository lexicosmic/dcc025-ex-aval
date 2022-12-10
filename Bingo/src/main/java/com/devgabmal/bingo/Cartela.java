/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devgabmal.bingo;

import java.util.Arrays;

/**
 *
 * @author devgabmal
 */
public class Cartela {

    private static final int ORDEM = 5;
    private static final int NUM_COLUNA = 15;
    private Espaco matriz[][];
    private boolean numerosGerados[];
    private static int quantidadeCartelas = 0;
    private int numeroCartela;

    public Cartela() {
        this.matriz = new Espaco[ORDEM][ORDEM];
        this.numerosGerados = new boolean[ORDEM * NUM_COLUNA];
        this.numeroCartela = Cartela.quantidadeCartelas + 1;
        Cartela.quantidadeCartelas++;

        // Gera valores aleatórios
        int matrizValores[] = new int[ORDEM * ORDEM];
        int k = 0;
        for (int i = 0; i < ORDEM; i++) {
            for (int j = 0; j < ORDEM; j++) {
                matrizValores[k] = this.geraNovoNumero(
                        (NUM_COLUNA * j) + 1, (NUM_COLUNA * (j + 1)));
                k++;
            }
        }

        // Ordena valores
        Arrays.sort(matrizValores);

        // Instancia espaços
        for (int i = 0; i < ORDEM; i++) {
            for (int j = 0; j < ORDEM; j++) {
                Espaco espaco = null;
                if (i == j && j == ORDEM / 2) {
                    espaco = new EspacoLivre();
                } else {
                    espaco = new EspacoNumerico(matrizValores[i + ORDEM * j]);
                }
                this.matriz[i][j] = espaco;
            }
        }
    }

    public int getNumeroCartela() {
        return numeroCartela;
    }

    private int geraNovoNumero(int min, int max) {
        int gerado;
        do {
            gerado = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
        } while (this.numerosGerados[gerado - 1]);
        this.numerosGerados[gerado - 1] = true;
        return gerado;
    }

    public void imprime() {
        System.out.println("  B      I      N      G      O    ");
        for (int i = 0; i < ORDEM; i++) {
            for (int j = 0; j < ORDEM; j++) {
                this.matriz[i][j].imprime();
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    public boolean marcaNumero(int numero) {
        int coluna = (int) Math.ceil(numero / (double) NUM_COLUNA) - 1;
        for (int i = 0; i < ORDEM; i++) {
            Espaco espaco = this.matriz[i][coluna];
            if (espaco.compara(numero)) {
                espaco.marca();
                return true;
            }
        }
        return false;
    }

    public boolean verificaVitoria(boolean vitoriaCruz) {
        boolean venceu = true;
        if (vitoriaCruz) {
            for (int i = 0; i < ORDEM && venceu; i++) {
                venceu = this.matriz[i][ORDEM / 2].isMarcado();
                venceu = venceu && this.matriz[ORDEM / 2][i].isMarcado();
            }
        } else {
            // Vitória por Linha
            venceu = false;
            for (int i = 0; i < ORDEM && !venceu; i++) {
                venceu = true;
                for (int j = 0; j < ORDEM && venceu; j++) {
                    venceu = this.matriz[i][j].isMarcado();
                }
            }
            // Vitória por coluna
            if (!venceu) {
                for (int i = 0; i < ORDEM && !venceu; i++) {
                    venceu = true;
                    for (int j = 0; j < ORDEM && venceu; j++) {
                        venceu = this.matriz[j][i].isMarcado();
                    }
                }
            }
        }
        return venceu;
    }
}
