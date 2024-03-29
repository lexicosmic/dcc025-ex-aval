/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.devgabmal.bingo;

import java.util.Scanner;

/**
 *
 * @author devgabmal
 */
public class Bingo {

    private static int determinaTipoJogo(String entrada) {
        if (entrada.equals("1") || entrada.equals("2")) {
            return Integer.parseInt(entrada);
        }
        return -1;
    }

    private static int converteStringEmInteiro(String entrada) {
        try {
            int convertido = Integer.parseInt(entrada);
            if (convertido > 0) {
                return convertido;
            } else {
                System.out.println("ERRO: digite um numero inteiro positivo!");
            }
        } catch (NumberFormatException ex) {
            System.out.println("ERRO: digite um numero inteiro!");
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String entrada = "";
        int tipoJogo = -1;
        int quantidadeCartelas = -1;

        do {
            System.out.println("Digite o modo de jogo:");
            System.out.println("1 - Linha/Coluna");
            System.out.println("2 - Cruz");
            entrada = teclado.nextLine();
            tipoJogo = Bingo.determinaTipoJogo(entrada);
        } while (tipoJogo == -1);

        do {
            System.out.println("Quantas pessoas vao jogar?");
            entrada = teclado.nextLine();
            quantidadeCartelas = Bingo.converteStringEmInteiro(entrada);
        } while (quantidadeCartelas == -1);
        System.out.println();

        Jogo jogo = new Jogo(tipoJogo, quantidadeCartelas, teclado);
        jogo.inicia();
    }
}
