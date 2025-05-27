public class PagoEfectivo extends MetodoPago implements Autenticable{

    public PagoEfectivo(double monto){
        super(monto);
    }

    @Override
    public boolean autenticar(){
        //Siempre retorna true: no requiere validación adicional
        return true;
    }

    @Override
    public void procesarPago(){
        System.out.println("💵 Procesando pago en efectivo por $" + monto);
    }
}
