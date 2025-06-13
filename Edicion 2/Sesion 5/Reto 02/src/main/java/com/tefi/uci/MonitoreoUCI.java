package com.tefi.uci;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

public class MonitoreoUCI {
    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        // Crear flujo para cada paciente (emitirá 10 mediciones cada uno)
        Flux<EventoVital> paciente1 = generarSignosVitales(1);
        Flux<EventoVital> paciente2 = generarSignosVitales(2);
        Flux<EventoVital> paciente3 = generarSignosVitales(3);

        // Fusionar, filtrar, ordenar y mostrar eventos críticos
        Flux.merge(paciente1, paciente2, paciente3)
                .filter(EventoVital::esCritico) // Solo alertas
                .sort((e1, e2) -> Integer.compare(e1.prioridad(), e2.prioridad())) // Priorizar tipo de evento
                .delayElements(Duration.ofSeconds(1)) // Simular backpressure
                .subscribe(System.out::println);


        Thread.sleep(30000);
    }

    /**
     * Genera un flujo de signos vitales aleatorios para un paciente.
     */
    private static Flux<EventoVital> generarSignosVitales(int pacienteId) {
        return Flux.interval(Duration.ofMillis(300))
                .map(tick -> {
                    int fc = 40 + random.nextInt(101);   // 40-140 bpm
                    int pas = 80 + random.nextInt(81);   // 80-160 mmHg
                    int pad = 50 + random.nextInt(51);   // 50-100 mmHg
                    int spo2 = 80 + random.nextInt(21);  // 80-100%
                    return new EventoVital(pacienteId, fc, pas, pad, spo2);
                })
                .take(20); // Emitir solo 20 eventos por paciente
    }

    /**
     * Clase que representa un conjunto de signos vitales de un paciente.
     */
    static class EventoVital {
        private final int pacienteId;
        private final int fc;
        private final int pas;
        private final int pad;
        private final int spo2;

        public EventoVital(int pacienteId, int fc, int pas, int pad, int spo2) {
            this.pacienteId = pacienteId;
            this.fc = fc;
            this.pas = pas;
            this.pad = pad;
            this.spo2 = spo2;
        }

        /**
         * Evalúa si alguno de los signos está fuera de rango.
         */
        public boolean esCritico() {
            return fc < 50 || fc > 120 || spo2 < 90 || pas < 90 || pas > 140 || pad < 60 || pad > 90;
        }

        /**
         * Prioriza la alerta: FC = 1, SpO2 = 2, PA = 3
         */
        public int prioridad() {
            if (fc < 50 || fc > 120) return 1;
            if (spo2 < 90) return 2;
            return 3; // PA fuera de rango
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (fc < 50 || fc > 120) {
                sb.append("⚠️ Paciente ").append(pacienteId).append(" - FC crítica: ").append(fc).append(" bpm\n");
            }
            if (spo2 < 90) {
                sb.append("⚠️ Paciente ").append(pacienteId).append(" - SpO2 baja: ").append(spo2).append("%\n");
            }
            if (pas < 90 || pas > 140 || pad < 60 || pad > 90) {
                sb.append("⚠️ Paciente ").append(pacienteId).append(" - PA fuera de rango: ")
                        .append(pas).append("/").append(pad).append(" mmHg\n");
            }
            return sb.toString().trim();
        }
    }
}