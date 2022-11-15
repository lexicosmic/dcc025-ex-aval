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

    private int[][] tabuleiro;
    private static final int dimensao = 7;
    private Scanner teclado;

    public Resta01() {
        this.tabuleiro = new int[dimensao][dimensao];
        this.populaTabuleiro();
        this.teclado = new Scanner(System.in);
    }

    private boolean validaIndice(int indice) {
        return (indice >= 0 && indice < dimensao);
    }

    private boolean verificaCentroCruz(int indice) {
        return (indice == 2 || indice == 3 || indice == 4);
    }

    private boolean validaPosicao(int linha, int coluna) {
        return (this.validaIndice(linha) && this.validaIndice(coluna)
                && (this.verificaCentroCruz(linha) || this.verificaCentroCruz(coluna)));
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

    private void atualizaPosicoes(int linhaPeca, int colunaPeca, int linhaMeio, int colunaMeio, int linhaBranco, int colunaBranco) {
        this.tabuleiro[linhaPeca][colunaPeca] = 0;
        this.tabuleiro[linhaMeio][colunaMeio] = 0;
        this.tabuleiro[linhaBranco][colunaBranco] = 1;
    }

    private boolean realizaMovimento(int[] args, boolean efetivar) {
        int linhaPeca, colunaPeca, linhaBranco, colunaBranco;
        linhaPeca = args[0];
        colunaPeca = args[1];
        linhaBranco = args[2];
        colunaBranco = args[3];

        // Posições são como descritas nos argumentos
        if (this.tabuleiro[linhaPeca][colunaPeca] == 1
                && this.tabuleiro[linhaBranco][colunaBranco] == 0) {

            // Horizontal
            if (linhaPeca == linhaBranco) {
                int distanciaColunas = colunaBranco - colunaPeca;
                if (Math.abs(distanciaColunas) == 2) {
                    int linhaMeio = linhaPeca;
                    int colunaMeio = (colunaPeca + colunaBranco) / 2;
                    if (this.tabuleiro[linhaMeio][colunaMeio] == 1) {
                        if (efetivar) {
                            this.atualizaPosicoes(linhaPeca, colunaPeca, linhaMeio, colunaMeio, linhaBranco, colunaBranco);
                        }
                        return true;
                    }
                }
            } // Vertical
            else if (colunaPeca == colunaBranco) {
                int distanciaLinhas = linhaBranco - linhaPeca;
                if (Math.abs(distanciaLinhas) == 2) {
                    int linhaMeio = (linhaPeca + linhaBranco) / 2;
                    int colunaMeio = colunaPeca;
                    if (this.tabuleiro[linhaMeio][colunaMeio] == 1) {
                        if (efetivar) {
                            this.atualizaPosicoes(linhaPeca, colunaPeca, linhaMeio, colunaMeio, linhaBranco, colunaBranco);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean validaJogada(int[] args, boolean efetivar) {
        if (!(this.validaPosicao(args[0], args[1])
                && this.validaPosicao(args[2], args[3]))) {
            if (efetivar) {
                System.out.println("ERRO: as posições estão fora do tabuleiro!");
            }
            return false;
        }
        if (!this.realizaMovimento(args, efetivar)) {
            if (efetivar) {
                System.out.println("ERRO: o movimento é inválido!");
            }
            return false;
        }
        return true;
    }

    private void realizaJogada(String[] argumentos) {
        try {
            int[] args = new int[4];

            // Converte argumentos em inteiros
            for (int i = 0; i < 4; i++) {
                args[i] = Integer.parseInt(argumentos[i]);
            }

            this.validaJogada(args, true);

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

    private boolean verificaJogadas(int linha, int coluna) {
        boolean haJogadasPossiveis = false;
        if (linha < dimensao - 1) {
            haJogadasPossiveis = haJogadasPossiveis || this.validaJogada(new int[]{linha, coluna, linha + 2, coluna}, false);
        }
        if (linha > 1) {
            haJogadasPossiveis = haJogadasPossiveis || this.validaJogada(new int[]{linha, coluna, linha - 2, coluna}, false);
        }
        if (coluna < dimensao - 2) {
            haJogadasPossiveis = haJogadasPossiveis || this.validaJogada(new int[]{linha, coluna, linha, coluna + 2}, false);
        }
        if (coluna > 1) {
            haJogadasPossiveis = haJogadasPossiveis || this.validaJogada(new int[]{linha, coluna, linha, coluna - 2}, false);
        }

        return haJogadasPossiveis;
    }

    private boolean verificaFimDeJogo() {
        int qtdUns = 0;
        boolean haJogadasPossiveis = false;

        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                if (tabuleiro[i][j] == 1) {
                    qtdUns++;
                    haJogadasPossiveis = haJogadasPossiveis
                            || (this.verificaJogadas(i, j));
                }
            }
        }

        if (qtdUns == 1) {
            System.out.println("Vitória! Parabéns:D");
            return true;
        } else if (!haJogadasPossiveis) {
            System.out.println("Não há mais jogadas possíveis T.T");
            return true;
        }

        return false;
    }

    private void interfaceJogo() {
        String entrada = "";
        boolean continua = true;
        do {
            this.imprimeTabuleiro();
            continua = !this.verificaFimDeJogo();
            if (continua) {
                System.out.print("\nEntrada: ");
                entrada = teclado.nextLine();
                continua = this.interpretaEntrada(entrada);
            }

            System.out.println("=======================\n");
        } while (continua);
    }

    public static void main(String[] args) {
        Resta01 jogo = new Resta01();
        boolean continua = true;
        boolean respondido = false;
        do {
            jogo.interfaceJogo();
            do {
                System.out.print("Deseja jogar novamente? [S/N] ");
                String entrada = jogo.teclado.nextLine();
                if (entrada.compareToIgnoreCase("N") == 0) {
                    continua = false;
                    respondido = true;
                } else if (entrada.compareToIgnoreCase("S") == 0) {
                    continua = true;
                    respondido = true;
                    jogo.populaTabuleiro();
                    System.out.println();
                }
            } while (!respondido);
            respondido = false;
        } while (continua);
        System.out.println("Fim de Jogo!");
    }
}
