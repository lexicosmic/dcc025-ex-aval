/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.devgabmal.velha3d;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author devgabmal
 */
public class Velha3D {

    private Scanner teclado;
    private Random aleatorio;
    private boolean modoEntreJogadores;
    private String[] nomes;
    private char[] simbolos;
    private char[][][] cubo;
    private final static int DIMENSAO = 3;

    public Velha3D() {
        this.teclado = new Scanner(System.in);
        this.aleatorio = new Random();
        modoEntreJogadores = false;
        this.nomes = new String[2];
        this.simbolos = new char[2];
        this.cubo = new char[Velha3D.DIMENSAO][Velha3D.DIMENSAO][Velha3D.DIMENSAO];

        for (int i = 0; i < Velha3D.DIMENSAO; i++) {
            for (int j = 0; j < Velha3D.DIMENSAO; j++) {
                for (int k = 0; k < Velha3D.DIMENSAO; k++) {
                    this.cubo[i][j][k] = '-';
                }
            }
        }
    }

    private boolean verificaJogadaVitoriosa(int[] posicao) {
        int camada = posicao[0];
        int linha = posicao[1];
        int coluna = posicao[2];
        int simbolo = this.cubo[camada][linha][coluna];
        boolean formaLinha = true;

        /*
        Linha
        X X X  - - -  - - -
        - - -  - - -  - - -
        - - -  - - -  - - -
         */
        for (int i = 0; i < Velha3D.DIMENSAO; i++) {
            if (i != coluna) {
                char simboloIteracao = this.cubo[camada][linha][i];
                if (simbolo != simboloIteracao) {
                    formaLinha = false;
                }
            }
        }
        if (formaLinha) {
            return true;
        } else {
            formaLinha = true;
        }

        /*
        Coluna
        - X -  - - -  - - -
        - X -  - - -  - - -
        - X -  - - -  - - -
         */
        for (int i = 0; i < Velha3D.DIMENSAO; i++) {
            if (i != linha) {
                char simboloIteracao = this.cubo[camada][i][coluna];
                if (simbolo != simboloIteracao) {
                    formaLinha = false;
                }
            }
        }
        if (formaLinha) {
            return true;
        } else {
            formaLinha = true;
        }

        /*
        Pilar
        - - -  - - -  - - -
        - X -  - X -  - X -
        - - -  - - -  - - -
         */
        for (int i = 0; i < Velha3D.DIMENSAO; i++) {
            if (i != camada) {
                char simboloIteracao = this.cubo[i][linha][coluna];
                if (simbolo != simboloIteracao) {
                    formaLinha = false;
                }
            }
        }
        if (formaLinha) {
            return true;
        } else {
            formaLinha = true;
        }

        /*
        Diagonal principal no plano
        X - -  - - -  - - -
        - X -  - - -  - - -
        - - X  - - -  - - -
         */
        for (int i = 0; i < Velha3D.DIMENSAO; i++) {
            if (!(i == linha && i == coluna)) {
                char simboloIteracao = this.cubo[camada][i][i];
                if (simbolo != simboloIteracao) {
                    formaLinha = false;
                }
            }
        }
        if (formaLinha) {
            return true;
        } else {
            formaLinha = true;
        }

        /*
        Diagonal secundária no plano
        - - X  - - -  - - -
        - X -  - - -  - - -
        X - -  - - -  - - -
         */
        for (int i = 0; i < Velha3D.DIMENSAO; i++) {
            if (!(i == linha && i == coluna)) {
                char simboloIteracao = this.cubo[camada][i][Velha3D.DIMENSAO - i - 1];
                if (simbolo != simboloIteracao) {
                    formaLinha = false;
                }
            }
        }
        if (formaLinha) {
            return true;
        } else {
            formaLinha = true;
        }

        /*
        Diagonal principal na lateral
        - - -  - - -  - - - 020
        - - -  - - -  - - - 121
        X - -  - X -  - - X 222
         */
        for (int i = 0; i < Velha3D.DIMENSAO; i++) {
            if (!(i == camada && i == coluna)) {
                char simboloIteracao = this.cubo[i][linha][i];
                if (simbolo != simboloIteracao) {
                    formaLinha = false;
                }
            }
        }
        if (formaLinha) {
            return true;
        } else {
            formaLinha = true;
        }

        /*
        Diagonal secundária na lateral
        - - -  - - -  - - - 012
        - - X  - X -  X - - 111
        - - -  - - -  - - - 210
         */
        for (int i = 0; i < Velha3D.DIMENSAO; i++) {
            if (!(i == camada && i == coluna)) {
                char simboloIteracao = this.cubo[i][linha][Velha3D.DIMENSAO - i - 1];
                if (simbolo != simboloIteracao) {
                    formaLinha = false;
                }
            }
        }
        if (formaLinha) {
            return true;
        } else {
            formaLinha = true;
        }

        /*
        Diagonal principal no espaço I
        X - -  - - -  - - - 000
        - - -  - X -  - - - 111
        - - -  - - -  - - X 222
         */
        for (int i = 0; i < Velha3D.DIMENSAO; i++) {
            if (!(i == camada && i == linha && i == coluna)) {
                char simboloIteracao = this.cubo[i][i][i];
                if (simbolo != simboloIteracao) {
                    formaLinha = false;
                }
            }
        }
        if (formaLinha) {
            return true;
        } else {
            formaLinha = true;
        }

        /*
        Diagonal principal no espaço II
        - - X  - - -  - - - 002
        - - -  - X -  - - - 111
        - - -  - - -  X - - 220
         */
        for (int i = 0; i < Velha3D.DIMENSAO; i++) {
            if (!(i == camada && i == linha && i == coluna)) {
                char simboloIteracao = this.cubo[i][i][Velha3D.DIMENSAO - i - 1];
                if (simbolo != simboloIteracao) {
                    formaLinha = false;
                }
            }
        }
        if (formaLinha) {
            return true;
        } else {
            formaLinha = true;
        }

        /*
        Diagonal secundária no espaço I
        - - -  - - -  - - X 020
        - - -  - X -  - - - 111
        X - -  - - -  - - - 202
         */
        for (int i = 0; i < Velha3D.DIMENSAO; i++) {
            if (!(i == camada && i == linha && i == coluna)) {
                char simboloIteracao = this.cubo[i][Velha3D.DIMENSAO - i - 1][i];
                if (simbolo != simboloIteracao) {
                    formaLinha = false;
                }
            }
        }
        if (formaLinha) {
            return true;
        } else {
            formaLinha = true;
        }

        /*
        Diagonal secundária no espaço II
        - - -  - - -  X - - 022
        - - -  - X -  - - - 111
        - - X  - - -  - - - 200
         */
        for (int i = 0; i < Velha3D.DIMENSAO; i++) {
            if (!(i == camada && i == linha && i == coluna)) {
                char simboloIteracao = this.cubo[Velha3D.DIMENSAO - i - 1][i][i];
                if (simbolo != simboloIteracao) {
                    formaLinha = false;
                }
            }
        }

        return formaLinha;
    }

    private boolean verificaChaveExiste(String[] lista, String chave) {
        return (lista[0].compareToIgnoreCase(chave) == 0);
    }

    private boolean verificaChaveExiste(char[] lista, char chave) {
        return (lista[0] == chave);
    }

    private void exibeMensagemErroNomeOuSimbolo(boolean ehNome) {
        String mensagemErro = "O ";
        mensagemErro += ehNome ? "nome" : "simbolo";
        mensagemErro += " já foi preenchido!";
        System.out.println(mensagemErro);
    }

    private void interfaceSolicitaChave(String mensagem, String[] lista, int i) {
        String chave;
        boolean continua = true;
        do {
            System.out.print(mensagem);
            chave = teclado.nextLine();

            if ((i > 0 && this.verificaChaveExiste(lista, chave))
                    || (chave.compareToIgnoreCase("Bot") == 0)) {
                this.exibeMensagemErroNomeOuSimbolo(true);
            } else {
                continua = false;
            }
        } while (continua);
        lista[i] = chave;
    }

    private void interfaceSolicitaChave(String mensagem, char[] lista, int i) {
        char chave;
        boolean continua = true;
        do {
            System.out.print(mensagem);
            chave = teclado.nextLine().charAt(0);

            if ((i > 0 && this.verificaChaveExiste(lista, chave))) {
                this.exibeMensagemErroNomeOuSimbolo(false);
            } else {
                continua = false;
            }
        } while (continua);
        lista[i] = chave;
    }

    private void interfaceEscolhaNomeESimbolo(int i) {
        this.interfaceSolicitaChave("Digite o nome do jogador: ",
                this.nomes, i);
        this.interfaceSolicitaChave("Digite o simbolo que deseja utilizar durante o jogo: ",
                this.simbolos, i);
    }

    private void escolherNomeESimbolo() {
        this.interfaceEscolhaNomeESimbolo(0);
        if (this.modoEntreJogadores) {
            this.interfaceEscolhaNomeESimbolo(1);
        } else {
            this.nomes[1] = "Bot";
            if (this.verificaChaveExiste(this.simbolos, 'X')) {
                this.simbolos[1] = 'O';
            } else {
                this.simbolos[1] = 'X';
            }
        }
    }

    private void escolherModoJogo() {
        boolean continua = true;
        do {
            System.out.print("Digite o modo de jogo(JxJ - para dois jogadores,"
                    + " ou JxB - para jogar contra o bot): ");
            String entrada = teclado.nextLine();
            continua = false;
            if (entrada.compareToIgnoreCase("JxJ") == 0) {
                this.modoEntreJogadores = true;
            } else if (!(entrada.compareToIgnoreCase("JxB") == 0)) {
                continua = true;
            }
        } while (continua);
    }

    private boolean validaIndice(int indice) {
        return (indice >= 0 && indice < Velha3D.DIMENSAO);
    }

    private boolean verificaPosicaoPreenchida(int[] posicao) {
        return this.cubo[posicao[0]][posicao[1]][posicao[2]] != '-';
    }

    private int[] converteJogada(String[] argumentos) {
        boolean erroConversao = false;
        boolean erroPosicao = false;
        int[] posicao = new int[Velha3D.DIMENSAO];

        for (int i = 0; i < Velha3D.DIMENSAO; i++) {
            try {
                int argumento = Integer.parseInt(argumentos[i]);
                if (this.validaIndice(argumento)) {
                    posicao[i] = argumento;
                } else {
                    erroPosicao = true;
                    break;
                }
            } catch (NumberFormatException ex) {
                erroConversao = true;
                break;
            }
        }

        if (erroConversao) {
            System.out.println("ERRO: Os argumentos devem ser números de 0 a 2!");
        } else if (erroPosicao) {
            System.out.println("ERRO: A posição informada está fora das dimensões do tabuleiro!");
        } else if (this.verificaPosicaoPreenchida(posicao)) {
            System.out.println("ERRO: A posição informada já foi preenchida!");
        } else {
            return posicao;
        }
        return null;
    }

    private void realizaJogada(int[] posicao, int turno) {
        this.cubo[posicao[0]][posicao[1]][posicao[2]] = this.simbolos[turno];
    }

    private int[] interpretaJogada(String jogada) {
        if (jogada.startsWith("(") && jogada.endsWith(")")) {
            jogada = jogada.substring(1, jogada.length() - 1);
            String[] comandos = jogada.split(", ");
            if (comandos.length == Velha3D.DIMENSAO) {
                return this.converteJogada(comandos);
            }
        }
        System.out.println("ERRO: A jogada não está no formato esperado!");
        return null;
    }

    private String solicitaJogada() {
        System.out.print("Digite a posicao que deseja jogar no formato"
                + " (linha, coluna, camada): ");
        return teclado.nextLine();
    }

    private int[] obtemJogadaDoBot() {
        int[] jogada = new int[3];
        do {
            for (int i = 0; i < Velha3D.DIMENSAO; i++) {
                jogada[i] = aleatorio.nextInt(Velha3D.DIMENSAO);
            }
        } while (this.verificaPosicaoPreenchida(jogada));
        return jogada;
    }

    private void imprimeTabuleiros() {
        for (int j = 0; j < Velha3D.DIMENSAO; j++) {
            for (int i = 0; i < Velha3D.DIMENSAO; i++) {
                for (int k = 0; k < Velha3D.DIMENSAO; k++) {
                    System.out.print(this.cubo[i][j][k] + " ");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void interfaceJogo() {
        boolean vitoria = false;
        int posicoesMaximas = (int) Math.pow(Velha3D.DIMENSAO, 3);
        int[] posicao = null;
        int turno = 0;

        System.out.println();
        this.imprimeTabuleiros();
        do {
            System.out.println("====> Turno de " + this.nomes[turno]);
            do {
                if (turno == 1 && !this.modoEntreJogadores) {
                    posicao = this.obtemJogadaDoBot();
                } else {
                    String jogada = this.solicitaJogada();
                    posicao = this.interpretaJogada(jogada);
                }
                if (posicao != null) {
                    this.realizaJogada(posicao, turno);
                    vitoria = this.verificaJogadaVitoriosa(posicao);
                    posicoesMaximas--;
                }
            } while (posicao == null);
            this.imprimeTabuleiros();
            turno = turno == 0 ? 1 : 0;
        } while (!vitoria && posicoesMaximas > 0);
        if (vitoria) {
            turno = turno == 0 ? 1 : 0;
            System.out.println("Parabens, " + this.nomes[turno] + "|");
        } else {
            System.out.println("Eita, deu velha!");
        }
    }

    private void iniciar() {
        this.escolherModoJogo();
        this.escolherNomeESimbolo();
        this.interfaceJogo();
    }

    public static void main(String[] args) {
        Velha3D jogo = new Velha3D();
        jogo.iniciar();
    }
}
