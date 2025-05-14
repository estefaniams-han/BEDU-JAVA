import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

// Clase que se encarga de registrar y procesar las muestras del laboratorio
public class RegistroMuestras {
    // Lista para mantener el orden de llegada de las muestras
    private ArrayList<Muestra> muestras;
    // Mapa para buscar una muestra rápidamente por su ID
    private HashMap<String, Muestra> mapaMuestras; //

    public RegistroMuestras() {
        muestras = new ArrayList<>();
        mapaMuestras = new HashMap<>();
    }

    // Metodo para agregar una muestra nueva al registro
    public void agregarMuestra(Muestra muestra){
        muestras.add(muestra);
        mapaMuestras.put(muestra.getId(), muestra);
    }

    // Muestra en consola el orden en que llegaron las especies
    public void mostrarOrdenLlegada(){
        System.out.println("📥 Orden de llegada de las muestras:");
        for (Muestra m : muestras){
            System.out.println("- " + m.getEspecie());
        }
    }

    // Extrae las especies únicas usando un HashSet y las muestra
    public void mostrarEspeciesUnicas(){
        HashSet<String> especies = new HashSet<>();
        for (Muestra m : muestras){
            especies.add(m.getEspecie());
        }

        System.out.println("\n🧬 Especies únicas procesadas:");
        for (String especie : especies) {
            System.out.println("- " + especie);
        }
    }

    // Muestra la relación de ID de muestra → nombre del investigador
    public void mostrarRelacionIdInvestigador(){
        System.out.println("\n🧑‍🔬 Relación ID de muestra → Investigador:");
        for (Muestra m : muestras){
            System.out.println(m.getId() + " -> " + m.getInvestigador());
        }
    }

    // Busca un ID específico y muestra el investigador correspondiente
    public void buscarInvestigadorPorId(String id){
        Muestra m = mapaMuestras.get(id);
        if (m != null) {
            System.out.println("Investigador responsable: " + m.getInvestigador());
        } else {
            System.out.println("⚠️ ID de muestra no encontrado.");
        }
    }
}
