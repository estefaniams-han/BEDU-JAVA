public class CentralEmergencias {
    public static void main(String [] args){
        //Se crean las unidades con sus operadores especificos
        Ambulancia ambulancia = new Ambulancia("Ambulancia", new Operador("Juan"));
        Patrulla patrulla = new Patrulla("Patrulla", new Operador("Laura"));
        UnidadBomberos bomberos = new UnidadBomberos("UnidadBomberos", new Operador("Marco"));

        //Se inicia la operación de cada unidad
        ambulancia.iniciarOperacion();
        System.out.println(); //Línea vacía para separar la salida

        patrulla.iniciarOperacion();
        System.out.println();

        bomberos.iniciarOperacion();
    }
}
