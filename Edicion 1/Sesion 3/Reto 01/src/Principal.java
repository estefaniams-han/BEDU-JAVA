public class Principal {
    public static void main(String[] args){
        //Crear pasajero y vuelo
        Pasajero pasajero1 = new Pasajero("Estefanía Marmolejo", "P123456");
        Vuelo vuelo1 = new Vuelo("UX123", "París", "14:30");

        //Reservar asiento
        vuelo1.reservarAsiento(pasajero1);

        //Mostrar itinerario
        System.out.println(vuelo1.obtenerItinerario());

        //Cancelar reserva
        vuelo1.cancelarReserva();

        //Mostrar itinerario nuevamente
        System.out.println(vuelo1.obtenerItinerario());

        //Reservar usando nombre y pasaporte
        vuelo1.reservarAsiento("Juan Perez", "P987963");

        //Mostrar itinerario nuevamente
        System.out.println(vuelo1.obtenerItinerario());
    }
}
