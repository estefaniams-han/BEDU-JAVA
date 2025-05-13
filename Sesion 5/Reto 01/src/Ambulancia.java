public class Ambulancia extends UnidadEmergencia {
    //Composición: la ambulancia "tiene" un GPS, una sirena y un operador
    private SistemaGPS gps;
    private Sirena sirena;
    private Operador operador;

    //Constructor reciben el nombre y el operador como parámetro
    public Ambulancia(String nombre, Operador operador) {
        super(nombre); //Llama al constructor de la clase padre
        this.gps = new SistemaGPS();
        this.sirena = new Sirena();
        this.operador = operador;
    }

    //Implementación específica del metodo abstracto responder
    @Override
    public void responder(){
        System.out.println("🩺 Ambulancia en camino al hospital más cercano.");
    }

    //Metodo que combina todas las acciones necesarias para operar la unidad
    public void iniciarOperacion(){
        activarUnidad(); //Metodo heredado
        gps.localizar();
        sirena.activarSirena();
        operador.reportarse();
        responder();
    }
}
