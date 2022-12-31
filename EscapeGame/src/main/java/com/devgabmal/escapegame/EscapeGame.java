/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.devgabmal.escapegame;

import java.util.Scanner;

/**
 *
 * @author devgabmal
 */
public class EscapeGame {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Boas-vindas ao Escape Game!");
        String entrada;
        do {
            Jogo jogo = new Jogo(teclado);
            jogo.inicia();
            System.out.println("Deseja jogar novamente? (S/N)");
            do {
                entrada = teclado.nextLine().toUpperCase();
            } while (!(entrada.equals("S") || entrada.equals("N")));
        } while (entrada.equals("S"));

    }
}
