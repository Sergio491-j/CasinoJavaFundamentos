package EstructuraCasino;
import java.util.*;
public class Slot extends CasinoJava {

    private int betMoney;

    private enum SlotChips { /* ENUM de todos los símbolos que pueden tocar en cada tirada de la tragaperras */
        ROJO,
        NEGRO,
        VERDE,
        AZUL,
        AMARILLO,
        GRIS,
        VIOLETA
    }

    private static final int SLOTS_NUMBERS = 3; /* Los números de las ventanas cada vez que incrementamos uno
     debemos de sumarle el número anterior a ese a la cuenta para que sea verdader. Con 3 números comparariamos
     3 ventanas. Con 4, comparariamos 6 ventanas y con 5 (6 + (5 - 1) = 10. En el return de */

    private static final int PRICE = 50;

    private static final Random ale = new Random();

    private final Scanner sc = new Scanner(System.in);



    public Slot() {
        setMatchCredit();
    } /* Inicializamos como el usuario quiere gastarse el dinero y empezamos a jugar */

    public void spin() {;
        boolean exitProgram = false;
        do {
            SlotChips[] arrayNumbers = new SlotChips[SLOTS_NUMBERS];
            SlotChips[] values = SlotChips.values();
            boolean exitComprobation = false;

            String spin = " ";
            System.out.println("Su crédito actual es de: " + credit);
            System.out.println("Pulse a la tecla \"Enter\" para tirar de la tragaperras! Teclee \"Salir\" para salir de la tragaperras");
            spin = sc.nextLine();

            if (spin.isEmpty()) {
                credit -= matchCredit;
                exitComprobation = true;
            } else if (exitMatch(spin) /* Se invocan las funciones desarrolladas utilizando
             parámetros de entrada en al menos una función. */ || credit <= 0) {
                System.out.println("Ha salido correctamente de la tragaperras / No dispone de más saldo!");
                exitProgram = true;
            } else {
                System.out.println("No puedes escribir nada. Solo dale al enter!");
            }
            while (exitComprobation) {

                System.out.println("=======================");

                for (int i = 0; i < arrayNumbers.length; i++) {
                    arrayNumbers[i] = values[ale.nextInt(values.length)];
                    System.out.print(arrayNumbers[i] + " ");
                }
                System.out.println();

                System.out.println("=======================");

                if (comprobation(arrayNumbers)) {
                    System.out.println("Ganaste!");
                    credit += betMoney * PRICE;
                    exitComprobation = false;
                } else {
                    System.out.println("Perdiste!");
                    exitComprobation = false;
                }
            }
        } while (!exitProgram);
    }

    private boolean comprobation(SlotChips[] results) {
        int counter = 0;
        for(int i = 0; i < results.length; i++) {
            for(int j = 0; j < i; j++) {
                if (results[j] == results[i]) {
                    counter++;
                }
            }
        }
        return counter == (SLOTS_NUMBERS * (SLOTS_NUMBERS - 1)) /2; /* Implementamos la fórmula para calcular cuantas
        comprobaciones habrían que hacer por el número de ventanas que tenga la tragaperras */
    }

    private boolean exitMatch(String spin) { /* Se trae como parámetro la variable spin del método con el mismo nombre
    y se valora con un regex si quiere salir de la tragaperras */
        final String regex = "^(?i)salir$";
        return spin.matches(regex);
    }
}