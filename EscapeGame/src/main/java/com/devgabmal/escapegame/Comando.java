/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devgabmal.escapegame;

/**
 *
 * @author devgabmal
 */
public class Comando {

    char direcao;
    int deslocamento;

    public Comando(char direcao, int deslocamento) throws ExcecaoDirecao, ExcecaoDeslocamento {
        direcao = Character.toLowerCase(direcao);
        if (direcao == 'e' || direcao == 'd' || direcao == 'b' || direcao == 'c') {
            if (deslocamento > 0) {
                this.direcao = direcao;
                this.deslocamento = deslocamento;
            } else {
                throw new ExcecaoDeslocamento();
            }
        } else {
            throw new ExcecaoDirecao();
        }
    }

    public char getDirecao() {
        return direcao;
    }

    public int getDeslocamento() {
        return deslocamento;
    }

    @Override
    public String toString() {
        return "(" + this.direcao + ", " + this.deslocamento + ")";
    }

}
