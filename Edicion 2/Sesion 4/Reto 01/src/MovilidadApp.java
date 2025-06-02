import java.util.concurrent.*;
import java.util.concurrent.ThreadLocalRandom;

public class MovilidadApp {
    //Simula el cálculo de una ruta (2-3 segundos)
    public static CompletableFuture<String> calcularRuta() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("🚦 Calculando ruta...");
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3001)); // 2-3 segundos
            } catch (InterruptedException e) {
                throw new IllegalStateException("Error al calcular ruta", e);
            }
            return "Centro -> Norte";
        });
    }

    //Simula la estimación de tarifa (1-2 segundos)
    public static CompletableFuture<Double> estimarTarifa() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("💰 Estimando tarifa...");
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2001)); // 1-2 segundos
            } catch (InterruptedException e) {
                throw new IllegalStateException("Error al estimar tarifa", e);
            }
            return 75.50;
        });
    }

    public static void main(String[] args){
        //combinamos ambas tareas asíncronas
        calcularRuta()
                .thenCombine(estimarTarifa(), (ruta, tarifa) ->
                        "✅ 🚗 Ruta calculada: " + ruta + " | Tarifa estimada: $" + tarifa
                )
                .exceptionally(ex -> "❌ Error al procesar la solicitud: " + ex.getMessage())
                .thenAccept(System.out::println);

        // Hacemos que el hilo principal espere un poco
        try {
            Thread.sleep(4000); // suficiente para que ambas tareas se completen
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// supplyAsync() ejecuta una tarea en segundo plano
// thenCombine() espera que ambas tareas terminen y combina sus resultados
// exceptionally() maneja errores sin romper el flujo
// thenAccept() actua cuando todo esta listo y muestra el resultado
