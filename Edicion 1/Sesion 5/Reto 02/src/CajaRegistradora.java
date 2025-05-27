public class CajaRegistradora {
    public static void main(String[] args){
        //Arreglo de pagos usando polimorfismo
        MetodoPago[] pagos = {
            new PagoEfectivo(150.0),
            new PagoTarjeta(320.0, 500.0),
            new PagoTransferencia(250.0),
            new PagoTarjeta(100.0, 50.0),             // Fallará por fondos insuficientes
            new PagoTransferencia(80.0)               // Puede fallar o no (aleatorio)
        };

        for (MetodoPago pago: pagos){
            //Validación segura: todos implementan Autenticable
            Autenticable a = (Autenticable) pago;

            if (a.autenticar()) {
                System.out.println("✅ Autenticación exitosa.");
                pago.procesarPago();
                pago.mostrarResumen();
            } else {
                System.out.println("❌ Fallo de autenticación. " +
                        pago.getClass().getSimpleName() + " no válido.");
            }

            System.out.println(); // Separador de salidas
        }
    }
}
