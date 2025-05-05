import java.util.Objects;

public class Factura {
    private String folio;
    private String cliente;
    private double total;

    public Factura(String folio, String cliente, double total){
        this.folio = folio;
        this.cliente = cliente;
        this.total = total;
    }

    @Override
    public String toString(){
        return "🧾 Factura [folio=" + folio + ", cliente=" + cliente + ", total=$" + total + "]";
    }

    @Override //Indica que estamos sobrescribiendo el metodo equals() de Object
    public boolean equals(Object obj){
        //Si ambos objetos apuntan al mismo lugar en memoria, son iguales (true)
        if (this == obj) return true;
        //Si el objeto a comprar es null o no es exactamente de la misma clase, no son iguales (false)
        if (obj == null || getClass() != obj.getClass()) return false;
        //Aquí se pasaron las validaciones anteriores, se convierte el objeto "obj" al tipo Factura
        Factura factura = (Factura) obj;
        //Compara si el atributo "folio" de ambas facturas es igual
        return Objects.equals(folio, factura.folio);
    }//me costó jajsa 💀

    @Override
    public int hashCode(){
        return Objects.hash(folio);
    }

}
