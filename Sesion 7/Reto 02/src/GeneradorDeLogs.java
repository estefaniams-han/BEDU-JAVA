import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GeneradorDeLogs {
    public static void main(String[] args) {
        //Nombre del archivo donde se guardarán los logs
        String rutaArchivo = "errores.log";
        //Cantidad total de líneas que se van a generar en el archivo
        int totalLineas = 10000;

        //Lista de mensajes que se van a escribir en el archivo (al azar)
        String[] mensajes = {
                "INFO: Inicio del sistema",
                "WARNING: Memoria por encima del 80%",
                "ERROR: No se pudo conectar al servidor",
                "INFO: Proceso completado exitosamente",
                "ERROR: Archivo de configuración no encontrado",
                "INFO: Ejecutando prueba automática 102",
                "WARNING: Tiempo de respuesta alto en prueba 103",
                "INFO: Finalizando sesión",
                "ERROR: Excepción NullPointer en módulo de usuarios",
                "INFO: Backup completado"
        };

        Random random = new Random();

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo))) {

            //Bucle para generar y escribir cada línea del log
            for (int i = 0; i < totalLineas; i++) {
                //Selecciona un mensaje aleatorio del arreglo
                String linea = mensajes[random.nextInt(mensajes.length)];
                //Escribe el mensaje en el archivo
                escritor.write(linea);
                //Agrega un salto de línea para que cada mensaje esté en su propia linea
                escritor.newLine();
            }
            System.out.println("✅ Archivo errores.log generado con " + totalLineas + " líneas.");
        } catch (IOException e) {
            System.err.println("⛔ Error al escribir el archivo: " + e.getMessage());
        }
    }
}
