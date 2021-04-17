module cooking {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens presentacion to javafx.fxml;
    exports presentacion;
}