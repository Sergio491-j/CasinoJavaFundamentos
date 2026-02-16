package EstructuraCasino;
import java.util.InputMismatchException;
import java.util.*;

public class CasinoRoulette extends CasinoJava {
    private int[][] moneyQuanty;
    private int[] hotNumbers;
    private Scanner sc = new Scanner(System.in);
    private Random ale = new Random();
    private int winningNumber;

    public CasinoRoulette() { setMatchCredit(); }

    public void setNumerosMetidos() {
        boolean salir = false;
        int cantidadNumeros = 0;
        System.out.println("¿Cuántos números desea meter en esta tirada?");
        while (!salir) {
            try {
                System.out.println("Introduzca la cantidad de números: ");
                cantidadNumeros = sc.nextInt();

                if (cantidadNumeros < 0 || cantidadNumeros > 36 ) {
                    System.out.println("ERROR no se pueden procesar números negativos ni mayores a los de la tabla!");
                    sc.nextLine();
                } else {
                    final int MONEYANDNUM = 2;/* Ponemos 2 posiciones en las columnas de la matríz, ya que
                    ahí va a ir el número y la cantidad de dinero */
                    salir = true;
                    this.moneyQuanty = new int[cantidadNumeros][MONEYANDNUM];
                }

            } catch (InputMismatchException e) {
                System.out.println("Edad introducida de forma incorrecta!");
                sc.nextLine();
            }
        }
    }

    private boolean repeatedNumbers(int position, int number) {
        for(int i = 0; i < position; i++) {
                if(moneyQuanty[i][0] == number) return true;
        }
        return false;
    } /* Método que revisa que todos los números sean únicos y que el usuario no meta repetidos */

    public void setBet() {
        double quantity = getMatchCredit();
        boolean keepGambling = true;
        for (int i = 0; i < moneyQuanty.length && keepGambling; i++) {
            boolean correctNumber = false;
            try {

                do {
                    System.out.println("Digame el número " + (i + 1));
                    moneyQuanty[i][0] = sc.nextInt();

                    if (repeatedNumbers(i, moneyQuanty[i][0])/* Se invocan las funciones desarrolladas utilizando
                                                             parámetros de entrada en al menos una función. */) { 
                        System.out.println("El número está repetido. Porfavor introduzcalo de nuevo.");
                    } else if (moneyQuanty[i][0] > 36 || moneyQuanty[i][0] < 0) {
                        System.out.println("No se pueden poner números que no están en el tablero!");
                    } else {
                        correctNumber = true;
                    }

                } while(!correctNumber);

                boolean correctMoney = false;

                do {

                    System.out.println("Cantidad restante por meter: " + quantity);

                    System.out.println("Dime la cantidad de dinero que le vas a meter a el número " + i);
                    moneyQuanty[i][1] = sc.nextInt(); /* Aquí es la segunda parte del doble bucle.
                    Revisamos que el usuario meta una cantidad de dinero razonable */

                    if (moneyQuanty[i][1] <= 0) {
                        System.out.println("Cantidad inválida, debe ser mayor que 0.");
                    } else if (moneyQuanty[i][1] > credit || moneyQuanty[i][1] > quantity) {
                        System.out.println("No puedes apostar más del crédito o de la cantidad restante.");
                    } else {
                        quantity -= moneyQuanty[i][1];
                        correctMoney = true;

                        if (quantity <= 0) {
                            System.out.println("Se ha acabado la cantidad disponible.");
                            keepGambling = false;
                        }
                    }


                } while(!correctMoney);


            } catch (InputMismatchException e) {
                System.out.println("Número o cantidad de dinero introducida de forma incorrecta!");
                sc.nextLine();
                i -= 1;
            }
        }
    }

    public void getBet() {
        for(int i = 0; i < moneyQuanty.length; i++) {
            System.out.print((i+1) + ". " + moneyQuanty[i][0] + " --> " + moneyQuanty[i][1] + " ♠");
            System.out.println();
        } /* Obtenemos todas las apuestas echas por el usuario */
    }

    public void setHotNumbers() { /* Metemos una serie de números que pagan más que los normales. Estos números son
    aleatorios en cada tirada y revisamos que no se repitan */
        hotNumbers = new int[ale.nextInt(12) + 1];
        for(int i = 0; i < hotNumbers.length; i++) {
            hotNumbers[i] = ale.nextInt(37);
            for(int j = 0; j < i; j++) {
                if(hotNumbers[i] == hotNumbers[j]) {
                    hotNumbers[i] = ale.nextInt(37);
                }
            }
        }
    }

    public String getHotNumbers() {
        return Arrays.toString(hotNumbers);
    }

    public void tirada() {
        winningNumber = ale.nextInt(37);
        final int PRIZE = 36;
        System.out.println("El número ganador es: " + winningNumber);
        boolean ganar = false;
        boolean detectedNumber = false;
        for(int i = 0; i < moneyQuanty.length; i++) {
            if(moneyQuanty[i][0] == winningNumber) {
                ganar = true; /* Realizamos la tirada y comprobamos que el número esté en los que ha puesto y
                si está en los números que pagan más */
                detectedNumber = true;
                for (int j = 0; j < hotNumbers.length && detectedNumber; j++) {
                    if(moneyQuanty[i][0] == hotNumbers[j]) {
                        int nuevoMultiplicador = ale.nextInt(200);
                        System.out.println("Ganaste tu premio X" + nuevoMultiplicador);
                        credit += nuevoMultiplicador * moneyQuanty[i][1];
                        detectedNumber = false;
                    } else {
                        System.out.println("Ganaste!");
                        credit += moneyQuanty[i][1] * PRIZE;
                        detectedNumber = false;
                    }
                }
            }
        }

        if (!ganar) { /* Si ganar no cambia a true, damos por perdida la partida y restamos al crédito al
        dinero que se había apostado al inicio */
            System.out.println("Perdiste!");
            credit -= getMatchCredit();
        }
        System.out.println("Dinero actual después de la apuesta: " + credit);
    }
}
