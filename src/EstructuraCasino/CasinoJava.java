package EstructuraCasino;
import java.util.Scanner;

public class CasinoJava {
    private String name;
    static double credit;
    static double matchCredit;
    private String dni;
    private int age;
    private final Scanner sc = new Scanner(System.in);

    public String selectGame() { /* Informción de todos los juegos disponibles y datos del usuario */
        System.out.println("DNI --> " + dni);
        System.out.println("NOMBRE USUARIO --> " + name);
        System.out.println("EDAD --> " + age);
        System.out.println("CREDITO ACTUAL --> " + credit);
        System.out.println();
        System.out.println("¿A que juego jugarás hoy?");
        System.out.println("1.Ruleta");
        System.out.println("2.BlackJack");
        System.out.println("3.Ahorcado");
        System.out.println("4.Sacar/Meter dinero");
        System.out.println("5.Tragaperra!");
        System.out.println("6.Salir del programa");
        return sc.nextLine();
    }

    public void setData() { /* Recopilamos datos del usuario y los validamos a traves de ifs y regex */
        System.out.println("Hola, bienvenido a nuestro casino!");
        System.out.println("Introduzca su nombre de usuario");
        name = sc.nextLine();

        boolean exit = false;
        while (!exit) {
            try {
                System.out.println("Introduzca su edad: ");
                age = sc.nextInt();

                if (age < 18) {
                    System.out.println("No cumple con la mayoría de edad requerida!");
                } else {
                    exit = true;
                }

            } catch (Exception e) {
                System.out.println("Edad introducida de forma incorrecta!");
                sc.nextLine();
            }
        }
        sc.nextLine();
        exit = false;
        final String regex = "^\\d{8}([A-Za-z]|[Ññ])$";
        while (!exit) {
            System.out.println("Introduzca su DNI porfavor");
            dni = sc.nextLine();

            if (!dni.matches(regex)) {
                System.out.println("DNI introducido en formato incorrecto!");
            } else {
                exit = true;
            }
        }
        exit = false;
        final String regex2 = "\\d+";
        while (!exit) {
            System.out.println("¿Con cuanto crédito jugarás?");
            String inCredit = sc.nextLine();

            if (!inCredit.matches(regex2)) {
                System.out.println("Introduzca un valor correcto!");
            } else {
                this.credit = Integer.parseInt(inCredit);
                exit = true;
            }
        }
    }
    protected void setMatchCredit() { /* Es el crédito con el jugaremos la partida. Lo pedimos en
    todos los juegos, es decir. Llamamos todo el rato a este método en todos los construtores  */
        boolean exit = false;
        while (!exit) {
            try {
                System.out.println("Dime cuanto crédito va a meter en esta partida: ");
                matchCredit = sc.nextInt();

                if (matchCredit < 0 || matchCredit > credit) System.out.println("ERROR: Dinero erroneo!");
                else {
                    exit = true;
                }

            } catch (Exception e) {
                System.out.println("ERROR: Dinero erroneo!");
            } finally {
                sc.nextLine();
            }
        }
    }

    protected double getMatchCredit() {
        return matchCredit;
    }

    public boolean otherMatchConfirmation() { /* Es la función con la que comfirmamos si el usuario quiere
    jugar otra partida */
        String regex = "^(?i)no|si$";

        while (true) {
            System.out.println("¿Desea jugar otra vez? (Si/No)");
            String confirmation = sc.nextLine().trim();

            if (confirmation.matches(regex)) {
                return confirmation.equalsIgnoreCase("si");
            } else {
                System.out.println("Solo puedes elegir entre si o no!");
            }
        }
    }
}
