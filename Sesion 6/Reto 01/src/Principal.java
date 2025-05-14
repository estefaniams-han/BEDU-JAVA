import java.util.Scanner;

public class Principal {
    public static void main(String[] args){
        RegistroMuestras registro = new RegistroMuestras();

        // Agregar algunas muestras
        registro.agregarMuestra(new Muestra("M-001", "Homo sapiens", "Dra. López"));
        registro.agregarMuestra(new Muestra("M-002", "Mus musculus", "Dr. Hernández"));
        registro.agregarMuestra(new Muestra("M-003", "Arabidopsis thaliana", "Dra. García"));
        registro.agregarMuestra(new Muestra("M-004", "Homo sapiens", "Dr. Rivera"));

        // Mostrar datos
        registro.mostrarOrdenLlegada();
        registro.mostrarEspeciesUnicas();
        registro.mostrarRelacionIdInvestigador();

        // Búsqueda por ID
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n🔍 Ingrese un ID de muestra para buscar el investigador: ");
        String idBusqueda = scanner.nextLine();

        registro.buscarInvestigadorPorId(idBusqueda);

        scanner.close();
    }
}
