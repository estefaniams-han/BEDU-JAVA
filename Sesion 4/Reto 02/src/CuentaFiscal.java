import java.util.Objects;

public class CuentaFiscal {
    private final String rfc;
    private double saldoDisponible;

    public CuentaFiscal(String rfc, double saldoDisponible){
        if (saldoDisponible < 0) {
            throw new IllegalArgumentException("El saldo no puede ser negativo");
        }
        this.rfc = rfc;
        this.saldoDisponible = saldoDisponible;
    }

    public String getRfc(){
        return rfc;
    }

    public double getSaldoDisponible(){
        return saldoDisponible;
    }

    public boolean validarRFC(DeclaracionImpuestos d){
        return Objects.equals(this.rfc, d.rfcContribuyente());
    }
}
