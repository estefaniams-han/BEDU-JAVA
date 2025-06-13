package com.tefi;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MeridianPrime {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        // Configuramos un manejador global de errores para evitar que la aplicación termine
        Hooks.onErrorDropped(e -> System.err.println("Error no manejado: " + e.getMessage()));

        // Flujo de sensores de tráfico: cada 500 ms emitimos un valor de congestión (0-100)
        Flux<Integer> trafico = Flux.interval(Duration.ofMillis(500))
                // Manejamos la presión negativa para quedarnos solo con el valor más reciente
                .onBackpressureBuffer()
                // Cambiamos la ejecución a un scheduler en paralelo y pedimos de uno en uno
                .publishOn(Schedulers.parallel(), 1)
                // Convertimos el contador de ticks en un porcentaje aleatorio de congestión
                .map(i -> random.nextInt(101))
                // Filtramos únicamente valores que superen el umbral crítico (70%)
                .filter(nivel -> nivel > 70);

        // Nos suscribimos e imprimimos la alerta de congestión crítica
        trafico.subscribe(nivel ->
                System.out.println("🚗 Alerta: Congestión del " + nivel + "% en Avenida Solar")
        );

        // Flujo de contaminación del aire: cada 600 ms emitimos un valor PM2.5 (0-99)
        Flux<Integer> contaminacion = Flux.interval(Duration.ofMillis(600))
                // Aplico la misma estrategia de backpressure para mantener el flujo
                .onBackpressureBuffer()
                .publishOn(Schedulers.parallel(), 1)
                // Generamos un valor aleatorio de partículas PM2.5
                .map(i -> random.nextInt(100))
                // Solo consideramos niveles superiores al límite saludable (50 µg/m3)
                .filter(pm -> pm > 50);

        // Suscripción que imprime la alerta de contaminación alta
        contaminacion.subscribe(pm ->
                System.out.println("\uD83C\uDF2B\uFE0F Alerta de contaminación: PM2.5 = " + pm + " µg/m3")
        );

        // Flujo de accidentes viales: cada 800 ms emitimos una prioridad (Baja, Media, Alta)
        String[] prioridades = {"Baja", "Media", "Alta"};
        Flux<String> accidentes = Flux.interval(Duration.ofMillis(800))
                .onBackpressureBuffer()
                .publishOn(Schedulers.parallel(), 1)
                // Seleccionamos aleatoriamente una prioridad
                .map(i -> prioridades[random.nextInt(prioridades.length)])
                // Solo nos interesa la prioridad Alta para emergencias graves
                .filter(p -> p.equals("Alta"));

        // Suscripción que imprime la emergencia vial de prioridad alta
        accidentes.subscribe(p ->
                System.out.println("\uD83D\uDE91 Emergencia vial: Accidente con prioridad " + p)
        );

        // Flujo de retrasos en trenes maglev: cada 700 ms un valor (0-10 min)
        Flux<Integer> trenes = Flux.interval(Duration.ofMillis(700))
                .onBackpressureBuffer()                // primer nivel de backpressure
                .publishOn(Schedulers.parallel(), 1)    // pedimos de uno en uno
                // Generamos un retraso aleatorio en minutos
                .map(i -> random.nextInt(11))
                // Simulamos que el procesamiento de cada evento tarda 1 segundo
                .delayElements(Duration.ofMillis(1000))
                // Volvemos a aplicar backpressure tras la demora
                .onBackpressureBuffer()
                // Solo interesan retrasos mayores a 5 minutos
                .filter(retraso -> retraso > 5);

        // Suscripción que imprime la alerta de retraso crítico en trenes
        trenes.subscribe(retraso ->
                System.out.println("\uD83D\uDE9D Retraso crítico en tren maglev: " + retraso + " minutos")
        );

        // Flujo de semáforos inteligentes: cada 400 ms un estado (Verde, Amarillo, Rojo)
        String[] estados = {"Verde", "Amarillo", "Rojo"};
        AtomicInteger contadorRojo = new AtomicInteger(0);

        Flux<String> semaforos = Flux.interval(Duration.ofMillis(400))
                .onBackpressureBuffer()
                .publishOn(Schedulers.parallel(), 1)
                .map(i -> estados[random.nextInt(estados.length)])
                // Detectamos tres rojos consecutivos para emitir una alerta específica
                .doOnNext(estado -> {
                    if ("Rojo".equals(estado) && contadorRojo.incrementAndGet() == 3) {
                        System.out.println("\uD83D\uDEA6 Alerta: Semáforo en rojo 3 veces consecutivas en cruce Norte");
                        contadorRojo.set(0);
                    } else if (!"Rojo".equals(estado)) {
                        // Reiniciamos el contador si el estado no es rojo
                        contadorRojo.set(0);
                    }
                });

        // Nos suscribimos sin procesar adicionalmente los estados de semáforo
        semaforos.subscribe();

        // Mantenemos la aplicación en ejecución para observar los eventos generados
        Thread.sleep(30000);  // 30 segundos de observación
    }
}