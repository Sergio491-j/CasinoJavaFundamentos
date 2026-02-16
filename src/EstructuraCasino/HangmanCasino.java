package EstructuraCasino;

import java.util.Random;
import java.util.Scanner;

public class HangmanCasino extends CasinoJava {
    private static final String[] CONSONANTS = {
            "b", "c", "d", "f", "g", "h", "j", "k", "l", "m",
            "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z"
    };
    /* Constantes de vocales y consonantes por las cuales vamos a ir generando la palabra para el juego */
    private static final String[] VOWELS = {"a", "e", "i", "o", "u"};
    private static final int[] GENERATOR = {0, 1}; /* Si sale 0 empezamos por consonantes  y viceversa */

    private String word;
    private StringBuilder censoredWord;
    private final Scanner sc = new Scanner(System.in);

    public HangmanCasino() {
        word = "";
        censoredWord = new StringBuilder();
        setMatchCredit();
        System.out.println(getMatchCredit());
    }

    public void generateWord() {
        Random random = new Random();
        boolean startsWithVowel = false;
        int length = random.nextInt(12) + 4;
        String[] wordArray = new String[length];

        if (GENERATOR[random.nextInt(2)] == 1) {
            startsWithVowel = true;
        }

        for (int i = 0; i < wordArray.length; i += 2) {
            if (startsWithVowel) {
                wordArray[i] = VOWELS[random.nextInt(VOWELS.length)];
                if (i + 1 < wordArray.length) {
                    wordArray[i + 1] = CONSONANTS[random.nextInt(CONSONANTS.length)];
                }
            } else { /* En este método creamos la palabra con la que se va a jugar */
                wordArray[i] = CONSONANTS[random.nextInt(CONSONANTS.length)];
                if (i + 1 < wordArray.length) {
                    wordArray[i + 1] = VOWELS[random.nextInt(VOWELS.length)];
                }
            }
        }

        for (int i = 0; i < wordArray.length; i++) {
            word += wordArray[i];
        } /* Metemos las letras del array en la palabra */
    }

    private String getWord() {
        return word;
    }

    public void censorWord() {
        censoredWord = new StringBuilder(word);
        for (int i = 0; i < word.length(); i++) {
            censoredWord.setCharAt(i, '*');
        }
    } /* Censuramos la palabra para que el usuario tenga que adivinarla */

    private StringBuilder getCensoredWord() {
        return censoredWord;
    }

    public void playGame() {
        System.out.println("Su palabra a adivinar es: " + getCensoredWord() + ". Dispone de 8 intentos");

        int index = 0;
        final int MAX_TRIES = 8;
        boolean gameOver = false;

        String[] attempts = {"primer", "segundo", "tercer", "cuarto", "quinto", "sexto", "séptimo", "octavo"};
        String regex = "^([A-Za-z]|ñ|Ñ)$"; /* Es el juego, he implementado como ya hice en clase un array con todos los
        intentos que hay en el juego */

        while (!gameOver) {
            System.out.println("Dígame una letra y veremos si está en la palabra:");
            String guessedLetter = sc.nextLine();

            if (!guessedLetter.matches(regex)) {
                System.out.println("No puede poner más de una letra!");
                continue;
            }

            guessedLetter = guessedLetter.toUpperCase();
            boolean letterFound = false;

            for (int i = 0; i < getWord().length(); i++) {
                char character = guessedLetter.charAt(0);
                if (character == getWord().charAt(i)) {
                    getCensoredWord().setCharAt(i, character);
                    letterFound = true;
                }
            }

            if (letterFound) {
                System.out.println("La letra introducida estaba en la palabra! Tu progreso actual es: "
                        + getCensoredWord().toString().toUpperCase());
            } else {
                System.out.println("La letra no estaba. Este es tu " + attempts[index] + " intento.");
                index++;
            }

            if (getWord().equalsIgnoreCase(getCensoredWord().toString())) {
                final int GANANCIA = 30;
                System.out.println("¡Ganaste la partida!\n");
                gameOver = true;
                credit += getMatchCredit() * GANANCIA;
            }

            if (index >= MAX_TRIES) {
                System.out.println("¡Perdiste el juego. Lo sentimos!\n");
                gameOver = true;
                credit -= getMatchCredit();
            }
        }
    }
}


