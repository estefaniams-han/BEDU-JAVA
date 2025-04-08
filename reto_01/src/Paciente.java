public class Paciente{
    String nombre;
    int edad;
    String numeroExpediente;

    //constructor
    public Paciente(String nombre, int edad, String numeroExpediente){
        this.nombre = nombre;
        this.edad = edad;
        this.numeroExpediente = numeroExpediente;
    }

    public void mostrarInformacion(){
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad + " años");
        System.out.println("Número de expediente: " + numeroExpediente);

    }
}
