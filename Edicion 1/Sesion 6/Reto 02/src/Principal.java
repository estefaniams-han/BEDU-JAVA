public class Principal {
    public static void main(String[] args){
        //Instanciar clases de gestión
        GestorTemas gestor = new GestorTemas();
        RepositorioRecursos recursos = new RepositorioRecursos();

        //Agregar temas
        gestor.agregarTema(new Tema("Lectura comprensiva", 2));
        gestor.agregarTema(new Tema("Matemáticas básicas", 1));
        gestor.agregarTema(new Tema("Cuidado del medio ambiente", 3));
        gestor.agregarTema(new Tema("Historia local", 2));

        //Agregar recursos relacioandos
        recursos.agregarRecurso("Lectura comprensiva", "https://recursos.edu/lectura");
        recursos.agregarRecurso("Matemáticas básicas", "https://recursos.edu/mate");
        recursos.agregarRecurso("Cuidado del medio ambiente", "https://recursos.edu/medioambiente");
        recursos.agregarRecurso("Historia local", "https://recursos.edu/historia");

        //Mostrar los datos
        gestor.mostrarTemasPorTitulo();
        gestor.mostrarTemasPorPrioridad();
        recursos.mostrarRecursos();
    }
}
