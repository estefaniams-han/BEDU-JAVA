public class Principal {
    public static void main(String[] args){
        //Crear factura con RFC
        Factura facturaConRFC = new Factura(2500.0, "Servicio de consultoría", "ABCC010101XYZ");

        //Crear factura sin RFC
        Factura facturaSinRFC = new Factura(1800.0, "Reparación de equipo", null);

        //Imprimir resúmenes
        System.out.println(facturaConRFC.getResumen());
        System.out.println(facturaSinRFC.getResumen());
    }
}
