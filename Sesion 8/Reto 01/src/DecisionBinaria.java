import java.util.Scanner;

public class DecisionBinaria implements LogicaDecision {
    @Override
    public boolean tomarDecision(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("🌹 Él extiende su mano hacia ti, sus ojos brillan bajo la luna.");
        System.out.println("¿Qué harás?");
        System.out.println("1. Tomar su mano y seguirlo.");
        System.out.println("2. Retroceder con desconfianza.");
        System.out.print("Elige (1 o 2): ");

        String input = scanner.nextLine().trim();
        return input.equals("1"); // true si eliges la opción romántica
    }
}
