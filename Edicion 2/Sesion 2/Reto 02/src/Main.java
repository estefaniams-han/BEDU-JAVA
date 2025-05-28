import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

//Clase que representa un recurso crítico, como una sala de cirugía
class RecursoMedico {
    private final String nombre;
    private final ReentrantLock lock = new ReentrantLock(); //Control de acceso

    public RecursoMedico(String nombre){
        this.nombre = nombre;
    }

    //Metodo que simula el uso del recurso por un profesional
    public void usar(String profesional) {
        System.out.println("⏳ " + profesional + " intentando acceder a " + nombre + "...");
        lock.lock(); //Se bloquea el acceso para otros hilos
        try {
            System.out.println("👩‍⚕️ " + profesional + " ha ingresado a " + nombre);
            Thread.sleep(5000); //  Simulamos que está usando el recurso
            System.out.println("✅ " + profesional + " ha salido de " + nombre);
        } catch (InterruptedException e) {
            System.out.println("❌ " + profesional + " fue interrumpido");
        } finally {
            lock.unlock(); // Se libera el recurso
        }
    }
}

//Clase principal para simular la concurrencia
public class Main {
    public static void main(String[] args){
        System.out.println("🏥 Iniciando acceso a la Sala de cirugía...\n");

        // Recurso compartido
        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugía");

        // Lista de profesionales que intentan usar la sala
        String[] profesionales = {
                "Dra. Sánchez", "Dr. Gómez", "Enfermera Ramírez", "Dr. Lee",
                "Dra. Torres", "Interno Juárez"
        };

        // Creamos tareas tipo Runnable para cada profesional
        ExecutorService executor = Executors.newFixedThreadPool(4); // Simula concurrencia real

        for (String nombre : profesionales) {
            Runnable tarea = () -> salaCirugia.usar(nombre);
            executor.execute(tarea);
        }

        // Cerramos el executor
        executor.shutdown();
    }
}
