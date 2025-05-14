import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Collections;
import java.util.Comparator;

// Esta clase gestiona la lista de temas activos
public class GestorTemas {
    // Lista segura para modificar sin problemas en entornos multihilo
    private CopyOnWriteArrayList<Tema> temas;

    public GestorTemas(){
        temas = new CopyOnWriteArrayList<>();
    }

    public void agregarTema(Tema tema){
        temas.add(tema);
    }

    // Ordena y muestra los temas por título (orden natural)
    public void mostrarTemasPorTitulo(){
        Collections.sort(temas); //-> Aquí se usa el compareTo
        System.out.println("📚 Temas ordenados por TÍTULO:");
        for (Tema t : temas) {
            System.out.println("- " + t);
        }
    }

    // Ordena y muestra los temas por prioridad (menor primero)
    public void mostrarTemasPorPrioridad(){
        temas.sort(Comparator.comparingInt(Tema::getPrioridad)); //Comparator por prioridad
        System.out.println("\n🔥 Temas ordenados por PRIORIDAD:");
        for (Tema t : temas) {
            System.out.println("- " + t);
        }
    }
}
