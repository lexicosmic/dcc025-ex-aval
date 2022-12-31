/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devgabmal.escapegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author devgabmal
 */
public class Jogo {

    int dificuldade;
    Tabuleiro tabuleiro;
    List<Comando> comandos;
    boolean fimDeJogo;
    boolean vitoria;
    Scanner teclado;
    int posicaoPersonagem[];
    final int ORDEM = 10;
    final static int INVALIDO = -1;
    final static int LINHA = 0;
    final static int COLUNA = 1;

    public Jogo(Scanner teclado) {
        this.teclado = teclado;
        this.posicaoPersonagem = new int[]{0, 0};
        this.comandos = new ArrayList<Comando>();
        this.selecionaDificuldade();
    }

    private void selecionaDificuldade() {
        System.out.println("Escolha o nível de dificuldade");
        System.out.println("0 - Fácil");
        System.out.println("1 - Médio");
        System.out.println("2 - Difícil");
        while (true) {
            try {
                int entrada = Integer.parseInt(this.teclado.nextLine());
                if (entrada >= 0 && entrada <= 2) {
                    this.dificuldade = entrada;
                    break;
                } else {
                    System.out.println("ERRO: Digite uma dificuldade válida! [0, 1, 2]");
                }
            } catch (NumberFormatException ex) {
                System.out.println("ERRO: Digite uma dificuldade válida! [Número 0, 1, ou 2]");
            }
        }
    }

    public void inicia() {
        this.fimDeJogo = false;
        this.vitoria = false;
        this.tabuleiro = new Tabuleiro(ORDEM, this.dificuldade);
        this.tabuleiro.imprime(fimDeJogo, posicaoPersonagem);

        do {
            Comando comando;
            int novaPosicao;
            while (true) {
                comando = this.leComando();
                novaPosicao = this.validaComando(comando);
                if (novaPosicao != INVALIDO) {
                    break;
                } else {
                    System.out.println("Este movimento excede os limites do tabuleiro! Tente novamente.");
                }
            }
            comandos.add(comando);

            this.realizaJogada(comando, novaPosicao);

            this.tabuleiro.imprime(fimDeJogo, this.posicaoPersonagem);
        } while (!fimDeJogo);
        if (vitoria) {
            System.out.println("Parabéns!");
        } else {
            System.out.println("Game over");
        }
        System.out.println("Deslocamentos realizados!");
        for (Comando comando : comandos) {
            System.out.println(comando);
        }
    }

    private Comando leComando() {
        Comando comando;
        while (true) {
            String entrada = this.teclado.nextLine();
            try {
                String entradaDividida[] = entrada.substring(1, entrada.length() - 1).split(", ");
                char direcao = entradaDividida[0].charAt(0);
                int deslocamento = Integer.parseInt(entradaDividida[1]);
                comando = new Comando(direcao, deslocamento);
                break;
            } catch (NumberFormatException ex) {
                System.out.println("ERRO: Digite um valor inteiro para o deslocamento!");
            } catch (ExcecaoDirecao ex) {
                System.out.println("ERRO: Digite uma direção válida! [e, d, b, c]");
            } catch (ExcecaoDeslocamento ex) {
                System.out.println("ERRO: Digite um valor válido para o deslocamento!");
            } catch (Exception ex) {
                System.out.println("ERRO: O movimento digitado é inválido!");
            }
        }
        return comando;
    }

    private int validaComando(Comando comando) {
        int novaPosicao = 0;
        switch (comando.getDirecao()) {
            case 'e':
                novaPosicao = this.posicaoPersonagem[COLUNA] - comando.getDeslocamento();
                break;
            case 'd':
                novaPosicao = this.posicaoPersonagem[COLUNA] + comando.getDeslocamento();
                break;
            case 'b':
                novaPosicao = this.posicaoPersonagem[LINHA] + comando.getDeslocamento();
                break;
            case 'c':
                novaPosicao = this.posicaoPersonagem[LINHA] - comando.getDeslocamento();
                break;
            default:
                return INVALIDO;
        }
        if (novaPosicao >= 0 && novaPosicao < ORDEM) {
            return novaPosicao;
        } else {
            return INVALIDO;
        }
    }

    private void realizaJogada(Comando comando, int novaPosicao) {
        switch (comando.getDirecao()) {
            case 'e':
                for (int i = this.posicaoPersonagem[COLUNA] - 1; i >= novaPosicao && !fimDeJogo; i++) {
                    this.interpretaSimbolo(this.tabuleiro.getSimbolo(this.posicaoPersonagem[LINHA], i));
                }
                break;
            case 'd':
                for (int i = this.posicaoPersonagem[COLUNA] + 1; i <= novaPosicao && !fimDeJogo; i++) {
                    this.interpretaSimbolo(this.tabuleiro.getSimbolo(this.posicaoPersonagem[LINHA], i));
                }
                break;
            case 'b':
                for (int i = this.posicaoPersonagem[LINHA] + 1; i <= novaPosicao && !fimDeJogo; i++) {
                    this.interpretaSimbolo(this.tabuleiro.getSimbolo(i, this.posicaoPersonagem[COLUNA]));
                }
                break;
            case 'c':
                for (int i = this.posicaoPersonagem[LINHA] - 1; i >= novaPosicao && !fimDeJogo; i++) {
                    this.interpretaSimbolo(this.tabuleiro.getSimbolo(i, this.posicaoPersonagem[COLUNA]));
                }
                break;
            default: {
                fimDeJogo = true;
                return;
            }
        }

        if (comando.getDirecao() == 'e' || comando.getDirecao() == 'd') {
            posicaoPersonagem[COLUNA] = novaPosicao;
        } else {
            posicaoPersonagem[LINHA] = novaPosicao;
        }
    }

    private void interpretaSimbolo(char simbolo) {
        if (simbolo == 'B') {
            fimDeJogo = true;
        } else if (simbolo == 'S') {
            fimDeJogo = true;
            vitoria = true;
        }
    }

}
