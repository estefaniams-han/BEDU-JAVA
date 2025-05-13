public class PagoTarjeta extends MetodoPago implements Autenticable{
    private double fondosDisponibles;

    public PagoTarjeta(double monto, double fondosDisponibles){
        super(monto);
        this.fondosDisponibles = fondosDisponibles;
    }

    @Override
    public boolean autenticar(){
        return fondosDisponibles >= monto;
    }

    @Override
    public void procesarPago(){
        System.out.println("💳 Procesando pago con tarjeta por $" + monto);
    }

}
