import java.util.*;
import java.util.stream.*;

//map() nos da cajas cerradas: [Optional📓, Optional❌]
//flatMap() abre las caja y nos da solo lo útil [📓]


//Clase de representa un pedido de la pizzería
class Pedido{
    private final String cliente;
    private final String tipoEntrega; // "domicilio" o "local"
    private final String telefeno; // puede ser null

    public Pedido(String cliente, String tipoEntrega, String telefono) {
        this.cliente = cliente;
        this.tipoEntrega = tipoEntrega;
        this.telefeno = telefono;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    //Metodo seguro para obtener el teléfono
    public Optional<String> getTelefono() {
        return Optional.ofNullable(telefeno);
    }
}

public class Main {
    public static void main(String[] args) {
        List<Pedido> pedidos = Arrays.asList(
                new Pedido("Ana", "domicilio", "555-1234"),
                new Pedido("Luis", "local", "555-1111"),
                new Pedido("Carla", "domicilio", null),
                new Pedido("José", "domicilio", "555-5678"),
                new Pedido("María", "local", null),
                new Pedido("Elena", "domicilio", "555-9999"),
                new Pedido("Estefania", "domicilio", "311-339-61-12")
        );

        // Stream para procesar pedidos a domicilio con teléfono
        pedidos.stream()
                .filter(p -> p.getTipoEntrega().equalsIgnoreCase("domicilio")) // solo a domicilio
                .map(Pedido::getTelefono)      // devolvemos Optional<String> [Optional.of("555-1234"]
                .flatMap(Optional::stream)     // eliminamos los vacíos (sin null checks manuales)
                .map(telefono -> "📞 Confirmación enviada al número: " + telefono)
                .forEach(System.out::println); // imprimimos todos los mensajes

    }
}


//Java no aplica todos los .filter() primero a toda la lista y luego todos los .map(), como si fueran bloques separados.
//Lo que hace es paso por paso para cada elemento individualmente.