import java.util.concurrent.ConcurrentHashMap;

// Esta clase gestiona un mapa seguro de recursos por tema
public class RepositorioRecursos {
    // Mapa concurrente para acceso seguro por múltiples hilos
    private ConcurrentHashMap<String, String> recursos;

    public RepositorioRecursos(){
        recursos = new ConcurrentHashMap<>();
    }

    public void agregarRecurso(String titulo, String enlace){
        recursos.put(titulo, enlace);
    }

    // Muestra todos los recursos almacenados en consola
    public void mostrarRecursos(){
        System.out.println("\n🌐 Repositorio de recursos compartidos:");
        for (String titulo : recursos.keySet()) {
            System.out.println(titulo + " → " + recursos.get(titulo));
        }
    }
}