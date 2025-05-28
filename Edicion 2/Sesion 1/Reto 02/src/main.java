import java.util.*;
import java.util.function.Predicate;

//Clase base abstracta para cualquier tipo de material del curso
abstract class MaterialCurso {
    protected String titulo;
    protected String autor;

    public MaterialCurso(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    //Metodo abstracto que las subclases deben implementar
    public abstract void mostrarDetalle();
}

//Subclase Video, con duración
class Video extends MaterialCurso {
    private int duracion; //en minutos

    public Video(String titulo, String autor, int duracion){
        super(titulo, autor);
        this.duracion = duracion;
    }

    public int getDuracion(){
        return duracion;
    }

    @Override
    public void mostrarDetalle(){
        System.out.println("🎥 Video: " + titulo + " - Autor: " + autor + " - Duración: " + duracion + " min");
    }
}

//Subclase Articulo, con cantidad de palabras
class Articulo extends MaterialCurso {
    private int palabras;

    public Articulo(String titulo, String autor, int palabras){
        super(titulo, autor);
        this.palabras = palabras;
    }

    @Override
    public void mostrarDetalle(){
        System.out.println("📄 Artículo: " + titulo + " - Autor: " + autor + " - Palabras: " + palabras);
    }
}

//Subclase Ejercicio, con estado de revision
class Ejercicio extends MaterialCurso {
    private boolean revisado;

    public Ejercicio(String titulo, String autor){
        super(titulo, autor);
        this.revisado = false; //por defecto no está revisado
    }

    public void marcarRevisado() {
        revisado = true;
    }

    public boolean isRevisado() {
        return revisado;
    }

    @Override
    public void mostrarDetalle(){
        System.out.println("📝 Ejercicio: " + titulo + " - Autor: " + autor + " - Revisado: " + revisado);
    }
}

//Clase de utilidades con métodos genéricos
class PlataformaUtils {
    //Muestra los detalles de cualquier tipo de material
    public static void mostrarMateriales(List<? extends MaterialCurso> lista){
        System.out.println("📚 Materiales del curso:");
        for (MaterialCurso material : lista) {
            material.mostrarDetalle();
        }
        System.out.println();
    }

    //Suma la duración total de los videos
    public static void contarDuracionVideos(List<? extends Video> lista){
        int total = 0;
        for (Video video : lista){
            total += video.getDuracion();
        }
        System.out.println("🎥 Duración total de videos: " + total + " minutos\n");
    }

    //Marca los ejercicios como revisados
    public static void marcarEjerciciosRevisados(List<? super Ejercicio> lista){
        for (Object obj : lista){
            if (obj instanceof Ejercicio ejercicio){
                ejercicio.marcarRevisado();
                System.out.println("✅ Ejercicio '" + ejercicio.titulo + "' marcado como revisado.");
            }
        }
    }

    //Filtra por autor usando un Predicate
    public static void filtrarPorAutor(List<? extends MaterialCurso> lista, Predicate<MaterialCurso> criterio) {
        System.out.println("🔍 Filtrando materiales por autor:");
        for (MaterialCurso material : lista) {
            if (criterio.test(material)) {
                material.mostrarDetalle();
            }
        }
    }
}

//Clase principal
public class main {
    public static void main(String [] args){
    //Lista de materiales mezclados
    List<MaterialCurso> materiales = Arrays.asList(
            new Video("Introducción a Java", "Mario", 15),
            new Video("POO en Java", "Carlos", 20),
            new Articulo("Historia de Java", "Ana", 1200),
            new Articulo("Tipos de datos", "Luis", 800),
            new Ejercicio("Variables y tipos", "Luis"),
            new Ejercicio("Condicionales", "Mario")
    );

    //Mostrar materiales
    PlataformaUtils.mostrarMateriales(materiales);

    // Contar duración de los videos (hacemos un filtrado previo por tipo Video)
    List<Video> soloVideos = new ArrayList<>();
    for (MaterialCurso m : materiales) {
        if (m instanceof Video) {
            soloVideos.add((Video) m);
        }
    }
    PlataformaUtils.contarDuracionVideos(soloVideos);

    //Marcar ejercicios como revisados
    PlataformaUtils.marcarEjerciciosRevisados(materiales);

    // Filtro por autor (reto adicional)
    PlataformaUtils.filtrarPorAutor(materiales, m -> m.autor.equals("Mario"));
}
}
