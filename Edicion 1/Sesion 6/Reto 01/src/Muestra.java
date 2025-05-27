// Clase que representa una muestra individual con su ID, especie e investigador responsable
public class Muestra {
    private String id;
    private String especie;
    private String investigador;

    public Muestra(String id, String especie, String investigador){
        this.id = id;
        this.especie = especie;
        this.investigador = investigador;
    }

    // Getters para acceder a los datos de la muestra
    public String getId() {
        return id;
    }

    public String getEspecie(){
        return especie;
    }

    public String getInvestigador(){
        return investigador;
    }
}
