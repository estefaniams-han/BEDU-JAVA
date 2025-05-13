import java.util.Random;

public class PagoTransferencia extends MetodoPago implements Autenticable {

    public PagoTransferencia(double monto){
        super(monto);
    }

    @Override
    public boolean autenticar(){
        //Simula una autenticacion aleatoria para representar validacion externa
        return new Random().nextBoolean();
    }

    @Override
    public void procesarPago(){
        System.out.println("🏦 Procesando pago por transferencia bancaria por $" + monto);
    }
}
