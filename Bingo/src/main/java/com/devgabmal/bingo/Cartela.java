/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devgabmal.bingo;

/**
 *
 * @author devgabmal
 */
public class Cartela {

    private static final int ORDEM = 5;
    private Espaco matriz[][];

    public Cartela() {
        this.matriz = new Espaco[ORDEM][ORDEM];

        for (int i = 0; i < ORDEM; i++) {
            for (int j = 0; j < ORDEM; j++) {
                Espaco espaco = new Espaco();
            }
        }
    }

}
