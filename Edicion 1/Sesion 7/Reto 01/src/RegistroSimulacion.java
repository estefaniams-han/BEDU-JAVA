import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class RegistroSimulacion {

    private static final Path RUTA_ARCHIVO = Paths.get("config", "parametros.txt");


    public static void main(String[] args){
        try{
            guardarParametros();
            verificarArchivo();
            leerParametros();
        }catch (IOException e){
            System.err.println("⛔ Ocurrió un error: " + e.getMessage());
        }
    }

    //Guardar los parametros de la simulacion en el archivo
    private static void guardarParametros() throws IOException{
        //Crea la carpeta config/ si aun no existe
        if (Files.notExists(RUTA_ARCHIVO.getParent())){
            Files.createDirectories(RUTA_ARCHIVO.getParent());
        }

        String parametros = """
            Flujo másico: 32.5 kg/h
            Volumen del tanque: 150 litros
            Concentración inicial: 0.8 mol/L
            """;


        Files.write(RUTA_ARCHIVO, parametros.getBytes(StandardCharsets.UTF_8));
        System.out.println("✅ Parámetros guardados en: " + RUTA_ARCHIVO.toAbsolutePath());
    }

    //Verificar que el archivo exista
    private static void verificarArchivo(){
        if (Files.exists(RUTA_ARCHIVO)){
            System.out.println("🔍 El archivo existe correctamente.");
        } else {
            System.out.println("⚠️ El archivo NO se encontró.");
        }
    }

    //Lee el contenido del archivo y lo muestra por consola
    private static void leerParametros() throws IOException{
        String contenido = Files.readString(RUTA_ARCHIVO, StandardCharsets.UTF_8);
        System.out.println("📄 Contenido del archivo:");
        System.out.println(contenido);
    }
}
