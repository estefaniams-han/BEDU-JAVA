import java.util.Optional;

public class Factura {
    private double monto;
    private String descripcion;
    private Optional<String> rfc;

    public Factura(double monto, String descripcion, String rfc){
        this.monto = monto;
        this.descripcion = descripcion;
        if (rfc != null){
            this.rfc = Optional.of(rfc);
        } else {
            this.rfc = Optional.empty();
        }
    }

    public String getResumen() {
        String resumen = "\n📄 Factura generada:\n";
        resumen += "Descripción: " + descripcion + "\n";
        resumen += "Monto: $" + monto + "\n";
        resumen += "RFC: " + (rfc.isPresent() ? rfc.get() : "[No proporcionado]");
        return resumen;
    }
}
