public class Patrulla extends UnidadEmergencia {
    private SistemaGPS gps;
    private Sirena sirena;
    private Operador operador;


    public Patrulla(String nombre, Operador operador){
        super(nombre);
        this.gps = new SistemaGPS();
        this.sirena = new Sirena();
        this.operador = operador;
    }

    public void iniciarOperacion(){
        activarUnidad();
        gps.localizar();
        sirena.activarSirena();
        operador.reportarse();
        responder();
    }

    @Override
    public void responder() {
        System.out.println("🚓 Patrulla atendiendo situación de seguridad ciudadana.");
    }
}
