public class CajeroAutomatico {
    double saldo;

    public CajeroAutomatico(double saldoInicial){
        this.saldo = saldoInicial;
    }

    public void consultarSaldo(){
        System.out.println("Saldo actual: $" + saldo);
    }

    public void depositar(double monto){
        saldo += monto;
        System.out.println("Depósito exitoso. Nuevo saldo: $" + saldo);
    }

    public void retirar(double monto){
        if (monto > saldo){
            System.out.println("❌ Fondos insuficientes. Tu saldo es: $" + saldo);
            return;
        }
        saldo -= monto;
        System.out.println("🟩 Retiro exitoso. Nuevo saldo: $" + saldo);
    }
}
