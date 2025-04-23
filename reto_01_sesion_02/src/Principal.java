import java.util.Scanner;

public class Principal {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Nombre del medicamento: ");
        String nombreMedicamento = sc.nextLine();

        System.out.print("Cantidad de piezas: ");
        int cantidad = sc.nextInt();

        System.out.print("Precio unitario: ");
        double precioUnitario = sc.nextDouble();


        SimuladorFarmacia simulador = new SimuladorFarmacia(nombreMedicamento, precioUnitario, cantidad);
        simulador.mostrarResumen();

        sc.close();
    }
}
