package com.tefi;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MeridianPrimeDesafio {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        // Manejador global de errores para que la app no termine ante excepciones no gestionadas
        Hooks.onErrorDropped(e -> System.err.println("Error no gestionado detectado: " + e.getMessage()));

        // Flujo de tráfico: genera alertas de congestión crítica
        Flux<String> traficoAlertas = Flux.interval(Duration.ofMillis(500))
                .onBackpressureLatest()
                .publishOn(Schedulers.parallel(), 1)
                .map(i -> random.nextInt(101))
                .filter(nivel -> nivel > 70)
                .map(nivel -> "\uD83D\uDE97 Alerta de congestión: " + nivel + "% en Avenida Solar");

        // Flujo de contaminación: genera alertas de contaminación alta
        Flux<String> contaminacionAlertas = Flux.interval(Duration.ofMillis(600))
                .onBackpressureLatest()
                .publishOn(Schedulers.parallel(), 1)
                .map(i -> random.nextInt(100))
                .filter(pm -> pm > 50)
                .map(pm -> "\uD83C\uDF2B\uFE0F Alerta de contaminación: PM2.5 = " + pm + " µg/m3");

        // Flujo de accidentes: genera alertas de prioridad alta
        String[] prioridades = {"Baja", "Media", "Alta"};
        Flux<String> accidentesAlertas = Flux.interval(Duration.ofMillis(800))
                .onBackpressureLatest()
                .publishOn(Schedulers.parallel(), 1)
                .map(i -> prioridades[random.nextInt(prioridades.length)])
                .filter(p -> p.equals("Alta"))
                .map(p -> "\uD83D\uDE91 Emergencia vial: Accidente con prioridad " + p);

        // Flujo de trenes: genera alertas de retraso crítico
        Flux<String> trenesAlertas = Flux.interval(Duration.ofMillis(700))
                .onBackpressureLatest()
                .publishOn(Schedulers.parallel(), 1)
                .map(i -> random.nextInt(11))
                .delayElements(Duration.ofMillis(1000))
                .onBackpressureLatest()
                .filter(retraso -> retraso > 5)
                .map(retraso -> "\uD83D\uDE9D Retraso crítico en tren maglev: " + retraso + " minutos");

        // Flujo de semáforos: genera alertas tras 3 rojos consecutivos
        String[] estados = {"Verde", "Amarillo", "Rojo"};
        AtomicInteger contadorRojo = new AtomicInteger(0);
        Flux<String> semaforosAlertas = Flux.interval(Duration.ofMillis(400))
                .onBackpressureLatest()
                .publishOn(Schedulers.parallel(), 1)
                .map(i -> estados[random.nextInt(estados.length)])
                .filter(estado -> {
                    if ("Rojo".equals(estado) && contadorRojo.incrementAndGet() == 3) {
                        contadorRojo.set(0);
                        return true;
                    }
                    if (!"Rojo".equals(estado)) {
                        contadorRojo.set(0);
                    }
                    return false;
                })
                .map(e -> "\uD83D\uDEA6 Alerta: Semáforo en rojo 3 veces consecutivas en cruce Norte");

        // Subscripciones individuales para alertas específicas
        traficoAlertas.subscribe(System.out::println);
        contaminacionAlertas.subscribe(System.out::println);
        accidentesAlertas.subscribe(System.out::println);
        trenesAlertas.subscribe(System.out::println);
        semaforosAlertas.subscribe(System.out::println);

        // Merge de todas las alertas críticas
        Flux<String> todasAlertas = Flux.merge(
                traficoAlertas,
                contaminacionAlertas,
                accidentesAlertas,
                trenesAlertas,
                semaforosAlertas
        );

        // Alerta global: si en una ventana de 1 segundo llegan 3 o más alertas, imprimimos alerta global
        todasAlertas
                .bufferTimeout(3, Duration.ofSeconds(1))
                .filter(lista -> lista.size() >= 3)
                .subscribe(lista -> System.out.println("\uD83D\uDEA8 Alerta global: Múltiples eventos críticos detectados en Meridian Prime"));

        // Mantener la aplicación en ejecución para observar resultados
        Thread.sleep(30000);
    }
}


// Flux.merge une varios flujos Flux<String> en un solo flujo
// No espera a que uno termine, simplemente intercala emisiones conforme llegan

/*
* bufferTimeout(n, duracion)
* agrupa eventos en listas (tipo List<String>) con 2 condiciones:
*   se llena la lista con hasta n elementos
*   o bien, se agota el tiempo sin haber llegado a los n elementos
* */

/*
 * filter(lista -> lista.size() >= n)
 * si hay n o más eventos en esa pequeña ventana, significa que ocurrió algo grave
 * es el punto que dispara la alerta global
 * */