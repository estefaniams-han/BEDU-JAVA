/*Desarrollar un programa de Java que permita registrar la información básica
* de entradas para eventos, aplicando los fundamentos de clases, objetos, atributos,
* métodos y el uso de constructores
* */

public class Principal {
    public static void main(String[] args) {
        Entrada entrada1 = new Entrada("Obra de Teatro", 450.0);
        Entrada entrada2 = new Entrada("Concierto de Rock", 799.99);

        entrada1.mostrarInformacion();
        entrada2.mostrarInformacion();

        //Usando record
        Entrada.Entrada_Record entradaRecord = new Entrada.Entrada_Record("Exposicion de Arte", 55.22);
        entradaRecord.mostrarInformacionRecord();
    }
}
