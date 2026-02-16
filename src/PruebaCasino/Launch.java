package PruebaCasino;

import java.util.Scanner;
import EstructuraCasino.*; //El programa se ha estructurado en más de una clase
/* Importamos todas las clases del paquete donde están todos los juegos y usuario */
public class Launch {
    public static void main(String[] args) { //En el Launch ponemos el main para ejecutar el programa
        Scanner sc = new Scanner(System.in);
        CasinoJava player = new CasinoJava();
        player.setData();
        System.out.println();

        boolean gameOver = false;
        //El programa se ha estructurado en más de una clase
        while (!gameOver) {
            String gameChoice = player.selectGame();
            boolean match = true;

            while (match) {
                switch (gameChoice) {
                    case "1":
                        CasinoRoulette roulette = new CasinoRoulette();
                        roulette.setNumerosMetidos();
                        roulette.setBet(); /* Toda la estructura de la ruleta, mientras que el usuario quiera jugar
                        no se para de ejecutar */
                        roulette.getBet();
                        roulette.setHotNumbers();
                        System.out.println("Números calientes: " + roulette.getHotNumbers());
                        roulette.tirada();
                        System.out.println();
                        if (!player.otherMatchConfirmation()) {
                            match = false;
                        }
                        break;

                    case "2":
                        BlackJack blackJack = new BlackJack();
                        System.out.println("BlackJack!");
                        while (!blackJack.gameOver()) { /* Mientras la condición sea falsa, el usuario
                        y el crupier no dejan de coger cartas */
                            blackJack.pickCards();
                            System.out.println();
                        }
                        if (!player.otherMatchConfirmation()) {
                            match = false;
                        }
                        break;

                    case "3":
                        // El programa se ha estructurado en más de una
                        //función
                        HangmanCasino hangman = new HangmanCasino();
                        hangman.generateWord();
                        hangman.censorWord();
                        hangman.playGame();
                        if (!player.otherMatchConfirmation()) {
                            match = false;
                        }
                        break;

                    case "4":
                        // El programa se ha estructurado en más de una
                        //función
                        new MoneyMovement();
                        match = false; // termina después de mover dinero
                        break;

                    case "5":
                        // El programa se ha estructurado en más de una
                        //función
                        Slot sl = new Slot();
                        sl.spin();
                        match = false;
                        break;

                    case "6":
                        System.out.println("Saliendo del programa...");
                        gameOver = true;
                        match = false;
                        break;

                    default:
                        System.out.println("Opción inválida. Elija entre 1 y 6.");
                        match = false;
                        break;
                }
            }
        }
    }
}

