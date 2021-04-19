package entidades.dto;

public enum Pantalla {
    // Pantallas de todos
    TO_GUI001_PRINCIPAL("/presentacion/registro_autenticacion/ToGUI001Principal.fxml"),
    TO_GUI002_INICIO("/presentacion/registro_autenticacion/ToGUI002Inicio.fxml"),
    TO_GUI003_SERVICIOCLIENTE("/presentacion/comunicacion/ToGUI003ServicioAlCliente.fxml"),

    // Pantallas del usuario Cooker
    CO_GUI001_INICIARSESION("/presentacion/registro_autenticacion/CoGUI001IniciarSesion.fxml"),
    CO_GUI002_REGISTRAR("/presentacion/registro_autenticacion/CoGUI002Registrarse.fxml"),
    CO_GUI003_INICIO("/presentacion/recetas/CoGUI003Inicio.fxml"),
    CO_GUI004_RESULTADOSBUSQ("/presentacion/recetas/CoGUI004ResultadosBusqueda.fxml"),
    CO_GUI006_VERRECETA("/presentacion/recetas/CoGUI006VerReceta.fxml"),
    CO_GUI007_RECETAING("/presentacion/recetas/CoGUI007RecetaIngredientes.fxml"),
    CO_GUI008_COMPRARING("/presentacion/recetas/CoGUI008ComprarIngredientes.fxml"),
    CO_GUI009_REDIRIGIRING("/presentacion/recetas/CoGUI009RedirigirCompraIngredientes.fxml"),
    CO_GUI010_PERFILCHEF("/presentacion/perfiles/CoGUI010PerfilChef.fxml"),
    CO_GUI011_AGENDACHEF("/presentacion/perfiles/CoGUI011VerAgendaChef.fxml"),

    // Pantallas del usuario Chef
    CH_GUI001_INICIARSESION("/presentacion/registro_autenticacion/ChGUI001IniciarSesion.fxml"),
    CH_GUI002_REGISTRAR("/presentacion/registro_autenticacion/ChGUI002Registrarse.fxml"),
    CH_GUI008_PERFIL("/presentacion/perfiles/ChGUI008Perfil.fxml"),
    CH_GUI009_SUBIRRECETA("/presentacion/recetas/ChGUI009SubirReceta.fxml"),
    CH_GUI010_EDITARRECETA("/presentacion/recetas/ChGUI009EditarReceta.fxml"),
    CH_GUI011_VERAGENDA("/presentacion/perfiles/ChGUI011VerAgenda.fxml");

    public String fxml;

    Pantalla(String fxml) {
        this.fxml = fxml;
    }
}
