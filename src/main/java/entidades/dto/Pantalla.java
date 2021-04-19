package entidades.dto;

public enum Pantalla {
    // Pantallas de todos
    TO_GUI001_PRINCIPAL("ToGUI001Principal.fxml"),
    TO_GUI002_INICIO("ToGUI002Inicio.fxml"),

    // Pantallas del usuario Cooker
    CO_GUI001_INICIARSESION("CoGUI001IniciarSesion.fxml"),
    CO_GUI002_REGISTRAR("CoGUI002Registrarse.fxml"),

    // Pantallas del usuario Chef
    CH_GUI001_INICIARSESION("ChGUI001IniciarSesion.fxml"),
    CH_GUI002_REGISTRAR("ChGUI002Registrarse.fxml");

    public String fxml;

    Pantalla(String fxml) {
        this.fxml = fxml;
    }
}
