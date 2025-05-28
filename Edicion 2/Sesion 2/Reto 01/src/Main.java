import java.util.concurrent.*;

//Runnable -> solo corre, pero no devuelve nada
//Callable<T> -> corre y devuelve algo. En este caso, String

//Sistema de Navegación
class SistemaNavegacion implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(2000); //Simulamos calculos orbitales
        return "\uD83D\uDEF0\uFE0F Navegación: trayectoria corregida con éxito.";
    }
}

//Sistema de Soporte Vital
class SistemaSoporteVital implements Callable<String>{
    @Override
    public String call() throws Exception{
        Thread.sleep(3800); // Simula monitoreo rápido
        return "🧪 Soporte vital: presión y oxígeno dentro de parámetros normales.";
    }
}

//Sistema de Control Termico
class SistemaControlTermico implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(4200); // Toma un poco más verificar temperaturas
        return "🔥 Control térmico: temperatura estable (22°C).";
    }
}

// Sistema de Comunicaciones
class SistemaComunicaciones implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(500); // Enlace rápido
        return "📡 Comunicaciones: enlace con estación terrestre establecido.";
    }
}

//Clase principal
public class Main {
    public static void main(String[] args){
        System.out.println("🚀 Simulación de misión espacial iniciada...");

        //Creamos un pool fijo de 4 hilos (uno por subsistema)
        ExecutorService executor = Executors.newFixedThreadPool(4);

        try {
            //Lanzamos las tareas concurrentes
            Future<String> nav = executor.submit(new SistemaNavegacion());
            Future<String> soporte = executor.submit(new SistemaSoporteVital());
            Future<String> termico = executor.submit(new SistemaControlTermico());
            Future<String> comunicaciones = executor.submit(new SistemaComunicaciones());

            // Obtenemos los resultados (esperamos a que terminen)
            System.out.println(comunicaciones.get());
            System.out.println(soporte.get());
            System.out.println(termico.get());
            System.out.println(nav.get());

            System.out.println("✅ Todos los sistemas reportan estado operativo.");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 🧼 Cerramos el executor
            executor.shutdown();
        }
    }
}
