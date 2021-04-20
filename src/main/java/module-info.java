module cooking {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    opens presentacion to javafx.fxml;
    opens presentacion.registro_autenticacion to javafx.fxml;
    opens presentacion.recetas to javafx.fxml;
    opens presentacion.perfiles to javafx.fxml;
    exports presentacion;
}
