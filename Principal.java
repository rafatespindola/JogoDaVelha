package JogoDaVelha;

//import JogoDaVelha.JogoDaVelha;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws InterruptedException {
        //   X|O|O  00|01|02
        //   X|O|X  10|11|12 linha/coluna
        //   O|O|X  20|21|22

        char continuar = 'S';
        Scanner teclado = new Scanner(System.in);
        JogoDaVelha jogoDaVelha = new JogoDaVelha();

        System.out.println("[1] Player VS Player or [2] Player VS Computer? ");
        int versusQuem = teclado.nextInt();

        if (versusQuem == 1 ) {
            while (continuar == 'S') {

                while (!jogoDaVelha.ganhou() && !jogoDaVelha.deuVelha()) {

                    System.out.println("..:Placar:..");
                    System.out.println("Jogador 01: " + jogoDaVelha.getPlacarP1());
                    System.out.println("Jogador 02: " + jogoDaVelha.getPlacarP2());

                    //desenha na tela o tabuleiro
                    jogoDaVelha.draw();

                    if (jogoDaVelha.vezPlayer1()) {
                        System.out.print("Jogador 1, ");
                    } else {
                        System.out.print("Jogador 2, ");
                    }

                    System.out.println("digite linha e coluna: ");

                    int pos = teclado.nextInt();
                    pos = pos - 11;

                    int linha = pos / 10;
                    int coluna = pos % 10;

                    //se o espaço estiver ocupado, jogue novamente
                    while (!jogoDaVelha.posicaoLivre(linha, coluna)) {
                        System.out.println("Posição já ocupada. Jogue outra posição: ");
                        pos = teclado.nextInt();
                        pos = pos - 11;
                        linha = pos / 10;
                        coluna = pos % 10;
                    }

                    if (jogoDaVelha.vezPlayer1()) {
                        jogoDaVelha.player1Joga(linha, coluna); // põe X na posição
                    } else {
                        jogoDaVelha.player2Joga(linha, coluna); // põe O na posição
                    }
                }

                System.out.println("..:Placar:..");
                System.out.println("Jogador 01: " + jogoDaVelha.getPlacarP1());
                System.out.println("Jogador 02: " + jogoDaVelha.getPlacarP2());

                jogoDaVelha.draw();

                if (jogoDaVelha.deuVelha()) System.out.println("Deu velha!");
                else if (jogoDaVelha.vezPlayer2()) System.out.println("Parabéns player1!");
                else if (jogoDaVelha.vezPlayer1()) System.out.println("Parabéns player2!");


                System.out.println("Continuar? [S/n]");
                continuar = teclado.next().charAt(0);
                while (continuar != 'S' && continuar != 'n') {
                    System.out.println("Continuar? [S/n]");
                    continuar = teclado.next().charAt(0);
                }
                jogoDaVelha.resetGame();
            }
        }else if(versusQuem == 2){

            //inteligencia artificial

            while (continuar == 'S') {

                while (!jogoDaVelha.ganhou() && !jogoDaVelha.deuVelha()) {

                    System.out.println("..:Placar:..");
                    System.out.println("Jogador 01: " + jogoDaVelha.getPlacarP1());
                    System.out.println("Jogador PC: " + jogoDaVelha.getPlacarPc());

                    //desenha na tela o tabuleiro
                    jogoDaVelha.draw();

                    int linha;
                    int coluna;

                    if (jogoDaVelha.vezPlayer1()) {
                        System.out.println("Jogador 1, digite linha e coluna: ");
                        int pos = teclado.nextInt();
                        pos = pos - 11;

                        linha = pos / 10;
                        coluna = pos % 10;
                    } else {
                        System.out.println("Vez do PC. Augarde");
                        jogoDaVelha.estrategia();
                        linha = jogoDaVelha.linhaInteligencia();
                        coluna = jogoDaVelha.colunaInteligencia();
                        Thread.currentThread().sleep(1500);
                    }

                    //se o espaço estiver ocupado, jogue novamente
                    while (!jogoDaVelha.posicaoLivre(linha, coluna)) {
                        System.out.println("Posição " + (linha+1) + ":" + (coluna+1) + " já ocupada. Jogue outra posição: ");
                        int pos = teclado.nextInt();
                        pos = pos - 11;
                        linha = pos / 10;
                        coluna = pos % 10;
                    }

                    if (jogoDaVelha.vezPlayer1()) {
                        jogoDaVelha.player1Joga(linha, coluna); // põe X na posição
                    } else {
                        jogoDaVelha.pcJoga(linha, coluna); // põe O na posição
                    }
                }

                System.out.println("..:Placar:..");
                System.out.println("Jogador 01: " + jogoDaVelha.getPlacarP1());
                System.out.println("Jogador PC: " + jogoDaVelha.getPlacarP2());

                jogoDaVelha.draw();


                if (jogoDaVelha.deuVelha()) System.out.println("Deu velha!");
                else if (jogoDaVelha.vezPlayer2()) System.out.println("Parabéns player1!");
                else System.out.println("PC ganhou como sempre! Ele nunca perde. Bjs");


                System.out.println("Continuar? [S/n]");
                continuar = teclado.next().charAt(0);
                while (continuar != 'S' && continuar != 'n') {
                    System.out.println("Continuar? [S/n]");
                    continuar = teclado.next().charAt(0);
                }
                if (jogoDaVelha.getPlacarP1() == 3){
                    System.out.println("Ja pode pedir música no fantástico!");
                }else if (jogoDaVelha.getPlacarPc() == 3){
                    System.out.println("Ja pode pedir música no fantástico!");
                }
                jogoDaVelha.resetGame();
            }
        }
    }


}
