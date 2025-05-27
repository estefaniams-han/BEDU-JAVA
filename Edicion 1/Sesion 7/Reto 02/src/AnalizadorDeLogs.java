import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AnalizadorDeLogs {
    public static void main(String[] args){
        String rutaLog = "errores.log";
        String rutaRegistroFallos = "registro_fallos.txt"; //Si algo falla, se escribe aquí

        int totalLineas = 0;
        int errores = 0;
        int advertencias = 0;

        //try-with-resources: se asegura de cerrar el archivo al terminar
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaLog))){
            String linea;

            while ((linea = lector.readLine()) != null){
                totalLineas++;

                if (linea.contains("ERROR")){
                    errores++;
                }
                if (linea.contains("WARNING")){
                    advertencias++;
                }
            }

            //Mostrar resumen
            System.out.println("✅ Análisis completado");
            System.out.println("📄 Total de líneas leídas: " + totalLineas);
            System.out.println("❌ Total de errores: " + errores);
            System.out.println("⚠️ Total de advertencias: " + advertencias);

            double porcentajeErrorWarning = ((errores + advertencias) * 100.0) / totalLineas;

        }catch (IOException e){
            System.err.println("⛔ No se pudo procesar el archivo: " + e.getMessage());


            //Si hay error, lo escribimos en otro archivo
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaRegistroFallos))){
                escritor.write("Error al leer el archivo de logs:\n");
                escritor.write(e.getMessage());
            } catch (IOException ex){
                System.err.println("⚠️ No se pudo escribir en registro_fallos.txt: " + ex.getMessage());
            }
        }
    }
}
