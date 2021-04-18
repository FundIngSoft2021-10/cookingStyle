module cooking {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens presentacion to javafx.fxml;
    opens presentacion.registro_autenticacion to javafx.fxml;
    exports presentacion;
}