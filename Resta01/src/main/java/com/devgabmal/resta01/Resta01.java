/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.devgabmal.resta01;

import java.util.Scanner;

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

    private boolean validaIndice(int indice) {
        return (indice == 2 || indice == 3 || indice == 4);
    }

    private boolean validaPosicao(int linha, int coluna) {
        return (this.validaIndice(linha) || this.validaIndice(coluna));
    }

    private void populaTabuleiro() {
        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                if (this.validaPosicao(i, j)) {
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

    private boolean validaMovimento(int[] args) {
        int linhaPeca, colunaPeca, linhaBranco, colunaBranco;
        linhaPeca = args[0];
        colunaPeca = args[1];
        linhaBranco = args[2];
        colunaBranco = args[3];

        // Posições são como descritas nos argumentos
        if ((this.tabuleiro[linhaPeca][colunaPeca] == 1)
                && this.tabuleiro[linhaBranco][colunaBranco] == 0) {

            // Horizontal
            if (linhaPeca == linhaBranco) {
                int distanciaColunas = colunaBranco - colunaPeca;
                if (Math.abs(distanciaColunas) == 2) {
                    return true;
                }
            } // Vertical
            else if (colunaPeca == colunaBranco) {
                int distanciaLinhas = linhaBranco - linhaPeca;
                if (Math.abs(distanciaLinhas) == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    private void realizaJogada(String[] argumentos) {
        try {
            int[] args = new int[4];

            // Converte argumentos em inteiros
            for (int i = 0; i < 4; i++) {
                args[i] = Integer.parseInt(argumentos[i]);
            }

            // Validações
            if (!(this.validaPosicao(args[0], args[1])
                    && this.validaPosicao(args[2], args[3]))) {
                System.out.println("ERRO: as posições estão fora do tabuleiro!");
                return;
            }
            if (!this.validaMovimento(args)) {
                System.out.println("ERRO: o movimento é inválido!");
                return;
            }

            System.out.println("realizou");
        } catch (NumberFormatException ex) {
            System.out.println("ERRO: insira argumentos numéricos!");
        }
    }

    private boolean interpretaEntrada(String entrada) {
        if (entrada.startsWith("(") && entrada.endsWith(")")) {
            entrada = entrada.substring(1, entrada.length() - 1);
            String[] argumentos = entrada.split(", ");
            if (argumentos.length == 4) {
                this.realizaJogada(argumentos);
            }
        } else if (entrada.compareToIgnoreCase("reiniciar") == 0) {
            this.populaTabuleiro();
        } else if (entrada.compareToIgnoreCase("sair") == 0) {
            return false;
        }
        return true;
    }

    private void interfaceJogo() {
        String entrada = "";
        Scanner teclado = new Scanner(System.in);
        do {
            this.imprimeTabuleiro();
            System.out.print("\nEntrada: ");
            entrada = teclado.nextLine();
            this.interpretaEntrada(entrada);

            System.out.println();
        } while (entrada.compareToIgnoreCase("sair") != 0);
    }

    public static void main(String[] args) {
        Resta01 jogo = new Resta01();
        jogo.interfaceJogo();
    }
}
