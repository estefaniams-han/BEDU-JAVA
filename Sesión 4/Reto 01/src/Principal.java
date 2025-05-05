public class Principal {
    public static void main(String[] args){
        Factura factura1 = new Factura("FAC001", "Juan Pérez", 1450.0);
        Factura factura2 = new Factura("FAC001", "Comercial XYZ", 1450.0);

        System.out.println(factura1.toString());
        System.out.println(factura2.toString());

        System.out.println("¿Las facturas son iguales?: " + factura1.equals(factura2));
    }
}
