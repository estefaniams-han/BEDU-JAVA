public class SimuladorFarmacia {
    String nombreMedicamento;
    double precioUnitario;
    int cantidad;

    public SimuladorFarmacia(String nombreMedicamento, double precioUnitario, int cantidad){
        this.nombreMedicamento = nombreMedicamento;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    public void mostrarResumen() {
        var totalSinDescuento = precioUnitario * cantidad;
        boolean aplicaDescuento = totalSinDescuento > 500;
        double descuento = aplicaDescuento ? totalSinDescuento * 0.15 : 0;
        double totalApagar = totalSinDescuento - descuento;

        System.out.println("\n💊Resumen:");
        System.out.println("Medicamento: " + nombreMedicamento);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Precio unitario: $" + precioUnitario);
        System.out.println("Total sin descuento: $" + totalSinDescuento);
        System.out.println("¿Aplica descuento?: " + aplicaDescuento);
        System.out.println("Descuento: $" + descuento);
        System.out.println("Total a pagar: $" + totalApagar);
    }
}
