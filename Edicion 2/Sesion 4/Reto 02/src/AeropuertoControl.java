import java.util.concurrent.*;
import java.util.concurrent.ThreadLocalRandom;

public class AeropuertoControl {
    //Verifica disponibilidad de la pista (80% probabilidad de exito)
    public static CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
           simularLatencia();
           boolean disponible = ThreadLocalRandom.current().nextDouble() < 0.80;
           System.out.println("🛣️ Pista disponible: " + disponible);
           return disponible;
        });
    }

    //Verifica condiciones del clima (85% probabilidad de exito)
    public static CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            simularLatencia();
            boolean favorable = ThreadLocalRandom.current().nextDouble() < 0.85;
            System.out.println("🌦️ Clima favorable: " + favorable);
            return favorable;
        });
    }

    // Verifica tráfico aéreo cercano (90% probabilidad de éxito)
    public static CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            simularLatencia();
            boolean despejado = ThreadLocalRandom.current().nextDouble() < 0.90;
            System.out.println("🚦 Tráfico aéreo despejado: " + despejado);
            return despejado;
        });
    }

    // Verifica disponibilidad de personal en tierra (95% probabilidad de éxito)
    public static CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            simularLatencia();
            boolean disponible = ThreadLocalRandom.current().nextDouble() < 0.95;
            System.out.println("👷‍♂️ Personal disponible: " + disponible);
            return disponible;
        });
    }


    // Simula latencia de 2 a 3 segundos
    private static void simularLatencia() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3001));
        } catch (InterruptedException e) {
            throw new RuntimeException("❌ Error de simulación", e);
        }
    }


    public static void main(String[] args) {
        System.out.println("🛫 Verificando condiciones para aterrizaje...\n");

        // Ejecutamos todas las verificaciones en paralelo
        CompletableFuture<Boolean> pista = verificarPista();
        CompletableFuture<Boolean> clima = verificarClima();
        CompletableFuture<Boolean> trafico = verificarTraficoAereo();
        CompletableFuture<Boolean> personal = verificarPersonalTierra();

        // Combinamos todas las tareas
        CompletableFuture.allOf(pista, clima, trafico, personal)
                .thenApply(v -> pista.join() && clima.join() && trafico.join() && personal.join())
                .thenAccept(resultado -> {
                    if (resultado) {
                        System.out.println("\n🛬 Aterrizaje autorizado: todas las condiciones óptimas.");
                    } else {
                        System.out.println("\n🚫 Aterrizaje denegado: condiciones no óptimas.");
                    }
                })
                .exceptionally(ex -> {
                    System.out.println("❌ Error en el sistema: " + ex.getMessage());
                    return null;
                });

        // Esperamos un poco para que la simulación termine antes de que main finalice
        try {
            Thread.sleep(4000); // suficiente para que terminen las tareas
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

// supplyAsync(): ejecuta una tarea en un hilo separado (no bloquea el main)
// ThreadLocalRandom.current().nextDouble() < probabilidad → simula decisiones aleatorias
// allOf(): espera que TODAS las tareas se completen
// join(): obtiene el resultado de cada CompletableFuture (ya terminó en este punto)
// thenApply(): transforma el resultado de allOf (combina booleanos)
// thenAccept(): consume el resultado final (imprime el mensaje)
// exceptionally(): captura errores sin romper el flujo principal
