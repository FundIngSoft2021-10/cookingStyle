module cooking {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    opens presentacion to javafx.fxml;
    exports presentacion;
}