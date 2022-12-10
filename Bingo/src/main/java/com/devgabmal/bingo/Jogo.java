/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devgabmal.bingo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author devgabmal
 */
public class Jogo {

    private static final int ORDEM = 5;
    private static final int NUM_COLUNA = 15;
    private int tipoJogo;
    private List<Cartela> cartelas;
    private Scanner teclado;
    private boolean numerosSorteados[];

    public Jogo(int tipoJogo, int quantidadeCartelas, Scanner teclado) {
        this.tipoJogo = tipoJogo;
        this.teclado = teclado;
        this.numerosSorteados = new boolean[ORDEM * NUM_COLUNA];
        this.cartelas = new ArrayList<Cartela>();
        for (int i = 0; i < quantidadeCartelas; i++) {
            Cartela cartela = new Cartela();
            this.cartelas.add(cartela);
        }
    }

    public void inicia() {
        boolean fimDeJogo = false;
        String entrada;
        boolean continua = true;
        int cartelaVitoriosa = -1;
        this.imprimeCartelas();
        do {
            System.out.println(this.sorteiaNumero() + "\n");
            this.imprimeCartelas();
            cartelaVitoriosa = this.verificaVitoria();
            System.out.println("=================================");

            if (cartelaVitoriosa > -1) {
                fimDeJogo = true;
            } else {
                do {
                    System.out.print("Deseja continuar? (S/N) ");
                    entrada = teclado.nextLine().toUpperCase();
                    if (entrada.equals("N")) {
                        continua = false;
                        break;
                    }
                } while (!entrada.equals("S"));
                System.out.println();
            }
        } while (!fimDeJogo && continua);
        System.out.println("Fim de jogo!");
        if (cartelaVitoriosa > -1) {
            System.out.println("Vitoria da cartela " + cartelaVitoriosa);
        }

    }

    private void imprimeCartelas() {
        for (Cartela cartela : cartelas) {
            System.out.println("Cartela " + cartela.getNumeroCartela());
            cartela.imprime();
            System.out.println();
        }
    }

    private String sorteiaNumero() {
        int gerado;
        do {
            gerado = (int) (Math.floor(Math.random() * (NUM_COLUNA * ORDEM)) + 1);
        } while (this.numerosSorteados[gerado - 1]);
        this.numerosSorteados[gerado - 1] = true;

        System.out.println("O número sorteado foi: " + gerado);

        String cartelasMarcaram = "";
        for (Cartela cartela : cartelas) {
            if (cartela.marcaNumero(gerado)) {
                cartelasMarcaram += cartela.getNumeroCartela() + ", ";
            }
        }

        if (cartelasMarcaram.isBlank()) {
            return "Ninguém marcou nesta rodada";
        } else {
            return "Jogadores que marcaram: ["
                    + cartelasMarcaram.substring(0, cartelasMarcaram.length() - 2)
                    + "]";
        }
    }

    private int verificaVitoria() {
        for (Cartela cartela : cartelas) {
            if (cartela.verificaVitoria(tipoJogo == 2)) {
                return cartela.getNumeroCartela();
            }
        }
        return -1;
    }

}
