public abstract class MetodoPago {
    protected double monto;

    public MetodoPago(double monto){
        this.monto = monto;
    }

    //Metodo que cada tipo de pago debe implementar con su logica especifica
    public abstract void procesarPago();

    //Metodo comun que imprime el tipo y el monto del pago
    public void mostrarResumen(){
        System.out.println("📄 Tipo: " + this.getClass().getSimpleName() + " - Monto: $" + monto);
    }
}
