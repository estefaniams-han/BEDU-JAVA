// Clase que representa un tema educativo con título y prioridad
public class Tema implements Comparable<Tema>{
    private String titulo;
    private int prioridad;

    public Tema(String titulo, int prioridad){
        this.titulo = titulo;
        this.prioridad = prioridad;
    }

    public String getTitulo(){
        return titulo;
    }

    public int getPrioridad(){
        return prioridad;
    }

    // Implementación de Comparable para ordenar por título alfabéticamente
    @Override
    public int compareTo(Tema otro){ //-> El compareTo se usa automáticamente cuando se hace algo como Collections.sort(listaDeTemas)
        return this.titulo.compareToIgnoreCase(otro.titulo);
    }

    // Para imprimir bonito el objeto Tema 🌸
    @Override
    public String toString(){
        return titulo + " (Prioiridad: " + prioridad + " )";
    }
}
