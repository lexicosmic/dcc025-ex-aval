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
    }

    public static void main(String[] args) {
        Resta01 jogo = new Resta01();
    }
}
