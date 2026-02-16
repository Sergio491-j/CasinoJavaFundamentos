package EstructuraCasino;
import java.util.Scanner;
//Clase para mover el dinero del programa con métodos para sacar y meter dinero
public class MoneyMovement extends CasinoJava {
    private String retiredMoney;
    private String introducedMoney; //
    private boolean exit = false;
    private final Scanner sc = new Scanner(System.in);

    public MoneyMovement() { /* Menú por el cual preguntamos al
    usuario que operación desea realizar. Es el constructor de la clase */
        while(!exit) {
            System.out.println("¿Que movimiento desea hacer?");
            System.out.println("1.Añadir dinero");
            System.out.println("2.Retirar dinero");
            String operation = sc.nextLine();

            switch (operation) {
                case "1":
                    introduceMoney();
                    break;
                case "2":
                    withDrawMoney();
                    break;
                default:
                    System.out.println("No puedes realizar operaciones que no sean las indicadas!\n");
            }
        }
    }

    private void withDrawMoney() { /* Función para sacar dinero del casino */
        String regex = "\\d+";
        while(!exit) {
            System.out.println("¿Cuánto dinero desea retirar?");
            retiredMoney = sc.nextLine();

            if(retiredMoney.matches(regex)) {
                int IntegerMoney = Integer.parseInt(retiredMoney);
                if(IntegerMoney <= credit) {
                    System.out.println("Dinero retirado con éxito!");
                    credit -= IntegerMoney;
                    exit = !exit;
                } else {
                    System.out.println("No puede retirar más del crédito");
                }
            } else {
                System.out.println("No puedes poner esa cantidad de dinero!");
            }
        }
    }

    private void introduceMoney() { /* Función para meter dinero y aumentar el crédito */
        String regex = "\\d+";
        while(!exit) {
            System.out.println("¿Cuánto dinero desea usted introducir?");
            introducedMoney = sc.nextLine();
            if (introducedMoney.matches(regex)) {
                System.out.println("Dinero introducido con éxito!");
                credit += Integer.parseInt(introducedMoney);
                exit = !exit;
            } else {
                System.out.println("No puedes poner esa cantidad de dinero!");
            }
        }
    }
}
