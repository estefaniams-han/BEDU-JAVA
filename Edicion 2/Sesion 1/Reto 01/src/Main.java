import java.util.*;

//Clase abstracta base para representar cuaquier orden de producción
abstract class OrdenProduccion {
    protected String codigo;
    protected int cantidad;

    public OrdenProduccion(String codigo, int cantidad) {
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    //Metodo que cada subclase implementará para mostrar su info
    public abstract void mostrarResumen();
}

//Subclase para ordenes de produccion en masa
class OrdenMasa extends OrdenProduccion {
    public OrdenMasa(String codigo, int cantidad){
        super(codigo, cantidad);
    }

    @Override
    public void mostrarResumen(){
        System.out.println("🔧 OrdenMasa - Código: " + codigo + " - Cantidad: " + cantidad);
    }
}

//Subclase para ordenes personalizadas, incluye cliente
class OrdenPersonalizada extends OrdenProduccion {
    private String cliente;

    public OrdenPersonalizada(String codigo, int cantidad, String cliente){
        super(codigo, cantidad);
        this.cliente = cliente;
    }

    //Este metodo se usa para simular el ajuste con un costo extra
    public void aplicarCostoAdicional(int costo){
        System.out.println("✅ Orden " + codigo + " ajustada con costo adicional de $" + costo);
    }

    @Override
    public void mostrarResumen(){
        System.out.println("🛠️ OrdenPersonalizada - Código: " + codigo + " - Cantidad: " + cantidad + " - Cliente: " + cliente);
    }
}

//Subclase para prototipos, incluye la fase de desarrollo
class OrdenPrototipo extends OrdenProduccion {
    private String faseDesarrollo;

    public OrdenPrototipo(String codigo, int cantidad, String faseDesarrollo) {
        super(codigo, cantidad);
        this.faseDesarrollo = faseDesarrollo;
    }

    @Override
    public void mostrarResumen(){
        System.out.println("🧪 OrdenPrototipo - Código: " + codigo + " - Cantidad: " + cantidad + " - Fase: " + faseDesarrollo);
    }
}


//Clase con métodos genéricos para procesar diferentes tipos de ordenes
class ProcesadorOrdenes {
    //Muestra cualquier tipo de orden (masa, persoanlizada o prototipo)
    public static void mostrarOrdenes(List<? extends OrdenProduccion> lista) {
        System.out.println("📋 Órdenes registradas:");
        for (OrdenProduccion orden : lista){
            orden.mostrarResumen();
        }
        System.out.println();
    }

    //Procesa solo ordenes personalizadas, usando super para permitir una lista más general
    public static void procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costoAdicional) {
        System.out.println("💰 Procesando órdenes personalizadas...");
        for (Object obj : lista) {
            //Aqui usamos instanceof para asegurarnos que estamos tratando con el tipo correcto
            if (obj instanceof OrdenPersonalizada orden) {
                orden.aplicarCostoAdicional(costoAdicional);
            }
        }
        System.out.println();
    }

    //Cuenta cuántas órdenes hay de cada tipo (reto adicional)
    public static void contarOrdenes(List<? extends OrdenProduccion>... listas){
        int masa = 0, personalizada = 0, prototipo = 0;
        for (List<? extends OrdenProduccion> lista : listas) {
            for (OrdenProduccion orden : lista) {
                if (orden instanceof OrdenMasa) masa++;
                else if (orden instanceof OrdenPersonalizada) personalizada++;
                else if (orden instanceof OrdenPrototipo) prototipo++;
            }
        }
        System.out.println("📊 Resumen total de órdenes:");
        System.out.println("🔧 Producción en masa: " + masa);
        System.out.println("🛠️ Personalizadas: " + personalizada);
        System.out.println("🧪 Prototipos: " + prototipo);
    }
}

//Principal
public class Main {
    public static void main(String[] args) {
        //Creamos listas con 2 órdenes de cada tipo
        List<OrdenMasa> ordenesMasa = Arrays.asList(
                new OrdenMasa("A123", 500),
                new OrdenMasa("A124", 750)
        );

        List<OrdenPersonalizada> ordenesPersonalizadas = Arrays.asList(
                new OrdenPersonalizada("P456", 100, "ClienteX"),
                new OrdenPersonalizada("P789", 150, "ClienteY")
        );

        List<OrdenPrototipo> ordenesPrototipo = Arrays.asList(
                new OrdenPrototipo("T789", 10, "Diseño"),
                new OrdenPrototipo("T790", 5, "Pruebas")
        );

        //Mostramos todas las ordenes
        ProcesadorOrdenes.mostrarOrdenes(ordenesMasa);
        ProcesadorOrdenes.mostrarOrdenes(ordenesPersonalizadas);
        ProcesadorOrdenes.mostrarOrdenes(ordenesPrototipo);

        //Aplicamos costo adicional a las personalizadas
        ProcesadorOrdenes.procesarPersonalizadas(new ArrayList<>(ordenesPersonalizadas), 200);

        //Contamos cuántas hay de cada tipo
        ProcesadorOrdenes.contarOrdenes(ordenesMasa, ordenesPersonalizadas, ordenesPrototipo);
    }
}
