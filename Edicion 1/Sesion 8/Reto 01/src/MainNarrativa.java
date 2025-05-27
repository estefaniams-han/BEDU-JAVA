public class MainNarrativa {
    private final TransicionHistoria transicion;
    private final GestorDialogo dialogo;
    private final LogicaDecision decision;

    //Inyección de dependencias: depende solo de interfaces (DIP)
    public MainNarrativa(TransicionHistoria transicion, GestorDialogo dialogo, LogicaDecision decision){
        this.transicion = transicion;
        this.dialogo = dialogo;
        this.decision = decision;
    }

    public void ejecutarEscena(){
        dialogo.mostrarDialogo("Has llegado a un claro oculto, bañado por la luz de la luna.");
        dialogo.mostrarDialogo("Un joven de mirada misteriosa se acerca lentamente…");
        dialogo.mostrarDialogo("Él: «No deberías estar aquí tan tarde... Aunque me alegra que lo estés.»");

        boolean eleccion = decision.tomarDecision();

        if (eleccion) {
            dialogo.mostrarDialogo("Él sonríe suavemente mientras toma tu mano. «Ven, te mostraré mi mundo.»");
        } else {
            dialogo.mostrarDialogo("Él baja la mirada. «Entiendo… Tal vez el destino no estaba de nuestro lado esta vez.»");
        }

        transicion.siguienteEscena();
    }

    public static void main(String[] args){
        //Inyectamos las implementaciones (OCP: si las cambiamos no tocamos MainNarrativa)
        TransicionHistoria transicion = new TransicionSimple();
        GestorDialogo dialogo = new DialogoTexto();
        LogicaDecision decision = new DecisionBinaria();

        MainNarrativa narrativa = new MainNarrativa(transicion, dialogo, decision);
        narrativa.ejecutarEscena();
    }
}
