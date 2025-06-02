import java.util.*;
import java.util.stream.*;

//Clase que representa una encuesta de satisfacción
class Encuesta {
    private final String paciente;
    private final String comentario; //puede ser null
    private final int calificacion; //escala 1 a 5

    public Encuesta(String paciente, String comentario, int calificacion){
        this.paciente = paciente;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public Optional<String> getComentario() {
        return Optional.ofNullable(comentario);
    }

    public String getPaciente() {
        return paciente;
    }
}

//Clase que representa una sucursal con encuestas
class Sucursal {
    private final String nombre;
    private final List<Encuesta> encuestas;

    public Sucursal(String nombre, List<Encuesta> encuestas){
        this.nombre = nombre;
        this.encuestas = encuestas;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Encuesta> getEncuestas() {
        return encuestas;
    }
}

public class Main {
    public static void main(String[] args){
        List<Sucursal> sucursales = List.of(
            new Sucursal("Centro", List.of(
                    new Encuesta("Ana", "El tiempo de espera fue largo", 2),
                    new Encuesta("Luis", null, 4),
                    new Encuesta("Carlos", "No me gustó la actitud del personal", 3)
            )),
            new Sucursal("Norte", List.of(
                    new Encuesta("María", "La atención fue buena, pero la limpieza puede mejorar", 3),
                    new Encuesta("Pedro", null, 2),
                    new Encuesta("Lucía", null, 5),
                    new Encuesta("Estefania", "Me atendieron bien, pero tuve que esperar más de 40 minutos", 2)

            ))
        );

        // Procesamos todas las encuestas de todas las sucursales
        sucursales.stream()
                .flatMap(sucursal ->
                        sucursal.getEncuestas().stream()
                                .filter(encuesta -> encuesta.getCalificacion() <= 3)
                                .map(encuesta -> Map.entry(sucursal.getNombre(), encuesta))
                )
                .flatMap(entry ->
                        entry.getValue().getComentario()
                                .map(comentario -> Stream.of(
                                        "Sucursal " + entry.getKey() +
                                                ": Seguimiento a paciente [" + entry.getValue().getPaciente() + // accedemos al nombre
                                                "] con comentario: \"" + comentario + "\""
                                ))
                                .orElseGet(Stream::empty)
                )
                .forEach(System.out::println);
    }
}

//.stream(): convierte una lista en un flujo de datos (stream)
//.filter(): filtra elementos que cumplan una condición booleana
//.map(): transforma cada elemento en otro (1 a 1)
//.flatMap(): aplana estructuras, útil cuando se trabaja con listas dentro de listas u Optional
//.forEach(): aplica una acción a cada elemento del stream (por ejemplo imprimir)

//Method Reference (::) → forma abreviada de lambda cuando ya existe un metodo que hace lo que queremos
//System.out::println equivale a x -> System.out.println(x)