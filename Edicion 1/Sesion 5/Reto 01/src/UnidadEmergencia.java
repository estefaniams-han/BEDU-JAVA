public abstract class UnidadEmergencia {
    protected String nombre;

    public UnidadEmergencia(String nombre){
        this.nombre = nombre; //Constructor que inicializa el nombre
    }

    public void activarUnidad(){
        System.out.println("🚨 Activando unidad: " + nombre);
    }

    //Metodo abstracto: cada subclase debe implementar su versión de "responder"
    public abstract void responder();
}
