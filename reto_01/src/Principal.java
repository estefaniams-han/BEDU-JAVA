/*Desarrollar un programa en Java que registre los datos de un paciente, aplicando
 * los fundamentos de clases, objetos, atributos, métodos y la entrada de datos por
 * consola.
 * */

import java.util.Scanner;

public class Principal {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingresa el nombre del paciente: ");
        String nombre = sc.nextLine();

        System.out.println("Ingresa la edad del paciente: ");
        int edad = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingresa el número de expediente: ");
        String numeroExpediente = sc.nextLine();

        Paciente paciente = new Paciente(nombre, edad, numeroExpediente);

        System.out.println("\n📄 Datos del paciente:");
        paciente.mostrarInformacion();

        sc.close();
    }
}
