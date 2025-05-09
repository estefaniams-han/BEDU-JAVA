public class CentralEmergencias {
    public static void main(String[] args){
        Ambulancia ambulancia = new Ambulancia("Ambulancia", new Operador("Juan"));
        Patrulla patrulla = new Patrulla("Patrulla", new Operador("Laura"));
        UnidadBomberos bomberos = new UnidadBomberos("Unidad Bomberos", new Operador("Marco"));

        ambulancia.iniciarOperacion();
        System.out.println();

        patrulla.iniciarOperacion();
        System.out.println();

        bomberos.iniciarOperacion();
    }
}
