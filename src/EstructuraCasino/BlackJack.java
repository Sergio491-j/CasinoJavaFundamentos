package EstructuraCasino;
import java.util.*;

public class BlackJack extends CasinoJava {

    private enum results { /* ENUM con todos los tipos de resultados posibles */
        GANADOR,
        PERDEDOR,
        EMPATE
    }

    private static final int OBJECTIVE = 21; /* Constantes de por cuanto se multiplica el prémio si ganamos
    y al número de carta que hay que llegar para terminar la partida */
    private static final int MULTIPLIER = 3;

    private final Random ale = new Random();

    private int crupierCards;
    private int userCards;

    public BlackJack() {
        setMatchCredit();
        crupierCards = 0;
        userCards = 0; //Ponemos el crédito e inicializamos ambos atributos en 0
    }

    public void pickCards() { /* Función que se ejecuta hasta que el jugador o el crupier lleguen a 21 */
        userCards += ale.nextInt(21);
        System.out.println("Tus cartas: " + userCards);
        crupierCards += ale.nextInt(21);
        System.out.println("Cartas del crupier: " + crupierCards);
        if (gameOver()) {
            System.out.println("El juego a terminado!");
            System.out.println(result());
        } //Esta función se ejecuta hasta que game over es true, y ahí ya result() devuelve el resultado
    }

    public boolean gameOver() { /* Devuelve true si el juego se termina */
        return crupierCards >= OBJECTIVE || userCards >= OBJECTIVE;
    }

    private results result() { /* No es la lógica real de un BlackJack, pero espero que sirva para este proyecto. */
        if (userCards == OBJECTIVE && crupierCards == OBJECTIVE) {
            credit += matchCredit;
            return results.EMPATE;
        } else if (userCards > OBJECTIVE && crupierCards < OBJECTIVE) {
            return results.PERDEDOR;
        } else {
            credit += matchCredit * MULTIPLIER;
            return results.GANADOR;
        }
    }
}
