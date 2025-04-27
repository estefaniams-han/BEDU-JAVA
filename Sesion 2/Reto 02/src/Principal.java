import java.util.Scanner;

public class Principal {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        var cajero = new CajeroAutomatico(1000.0);
        int opcion;

        do {
            System.out.println("\nBienvenido al cajero automático 🏦");
            System.out.println("1. Consultar saldo ✅");
            System.out.println("2. Depositar dinero \uD83D\uDCB0");
            System.out.println("3. Retirar dinero \uD83D\uDCB8");
            System.out.println("4. Salir \uD83D\uDC4B");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    cajero.consultarSaldo();
                    break;

                case 2:
                    System.out.print("Ingresa el monto a depositar: ");
                    double deposito = sc.nextDouble();
                    cajero.depositar(deposito);
                    break;

                case 3:
                    System.out.print("Ingresa el monto a retirar: ");
                    double retiro = sc.nextDouble();
                    cajero.retirar(retiro);
                    break;

                case 4:
                    System.out.println("👋 Gracias por usar el cajero automático. ¡Hasta luego!");
                    break;

                default:
                    System.out.println("⚠️ Opción inválida. Intenta nuevamente.");
                    continue;
            }

        } while (opcion != 4);

        sc.close();
    }
}
