package com.tefi.uci;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MonitoreoUCIDesafio {
    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        // Generar un flujo de signos vitales simulados para cada paciente
        Flux<EventoVital> paciente1 = generarSignosVitales(1);
        Flux<EventoVital> paciente2 = generarSignosVitales(2);
        Flux<EventoVital> paciente3 = generarSignosVitales(3);

        // Contador para saber en qué segundo se encuentra el monitoreo
        AtomicInteger segundoActual = new AtomicInteger(1);

        // Fusión y procesamiento de los tres flujos de pacientes
        Flux.merge(paciente1, paciente2, paciente3)
                .filter(EventoVital::esCritico) // Filtrar solo los eventos fuera de rango
                .bufferTimeout(10, Duration.ofSeconds(1)) // Agrupar eventos por ventana de 1 segundo
                .flatMap(batch -> {
                    // Obtener el número de segundo actual
                    int segundo = segundoActual.getAndIncrement();

                    // Ordenar los eventos del lote según su prioridad (FC > SpO2 > PA)
                    List<EventoVital> ordenados = batch.stream()
                            .sorted(Comparator.comparingInt(EventoVital::prioridad))
                            .toList();

                    // Emitir un encabezado por segundo seguido de los eventos ordenados como texto
                    return Flux.just("=== 🕐 Segundo " + segundo + " ===")
                            .concatWith(Flux.fromIterable(ordenados).map(EventoVital::toString));
                })
                .subscribe(System.out::println); // Imprimir cada alerta y encabezado en consola

        // Mantener la aplicación activa durante 15 segundos
        Thread.sleep(15000);
    }

    /**
     * Genera un flujo reactivo que emite eventos vitales aleatorios para un paciente.
     * Se emite un nuevo evento cada 300 ms, hasta alcanzar 20 eventos.
     */
    private static Flux<EventoVital> generarSignosVitales(int pacienteId) {
        return Flux.interval(Duration.ofMillis(300))
                .map(tick -> {
                    // Generación de signos vitales aleatorios
                    int fc = 40 + random.nextInt(101);   // Frecuencia cardíaca: 40–140 bpm
                    int pas = 80 + random.nextInt(81);   // Presión sistólica: 80–160 mmHg
                    int pad = 50 + random.nextInt(51);   // Presión diastólica: 50–100 mmHg
                    int spo2 = 80 + random.nextInt(21);  // Saturación de oxígeno: 80–100%
                    return new EventoVital(pacienteId, fc, pas, pad, spo2);
                })
                .take(50); // Emitir solo 50 eventos por paciente
    }

    /**
     * Representa un conjunto de signos vitales capturados de un paciente.
     */
    static class EventoVital {
        private final int pacienteId;
        private final int fc;    // Frecuencia cardíaca
        private final int pas;   // Presión arterial sistólica
        private final int pad;   // Presión arterial diastólica
        private final int spo2;  // Saturación de oxígeno

        public EventoVital(int pacienteId, int fc, int pas, int pad, int spo2) {
            this.pacienteId = pacienteId;
            this.fc = fc;
            this.pas = pas;
            this.pad = pad;
            this.spo2 = spo2;
        }

        /**
         * Determina si el evento contiene algún valor fuera de rango normal.
         */
        public boolean esCritico() {
            return fc < 50 || fc > 120 || spo2 < 90 || pas < 90 || pas > 140 || pad < 60 || pad > 90;
        }

        /**
         * Asigna una prioridad al evento:
         * 1 = Frecuencia cardíaca crítica
         * 2 = Saturación de oxígeno baja
         * 3 = Presión arterial fuera de rango
         */
        public int prioridad() {
            if (fc < 50 || fc > 120) return 1;
            if (spo2 < 90) return 2;
            return 3;
        }

        /**
         * Genera la representación textual de las alertas del evento.
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (fc < 50 || fc > 120)
                sb.append("⚠️ Paciente ").append(pacienteId).append(" - FC crítica: ").append(fc).append(" bpm\n");
            if (spo2 < 90)
                sb.append("⚠️ Paciente ").append(pacienteId).append(" - SpO2 baja: ").append(spo2).append("%\n");
            if (pas < 90 || pas > 140 || pad < 60 || pad > 90)
                sb.append("⚠️ Paciente ").append(pacienteId).append(" - PA fuera de rango: ")
                        .append(pas).append("/").append(pad).append(" mmHg\n");
            return sb.toString().trim(); // Elimina salto final si lo hay
        }
    }
}
