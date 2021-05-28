module cooking {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires org.controlsfx.controls;

    opens entidades.dto to org.junit.jupiter;
    opens entidades.modelo to org.junit.jupiter;
    opens logica_negocio.perfiles to org.junit.jupiter;
    opens logica_negocio.recetas to org.junit.jupiter;
    opens logica_negocio.registro_autenticacion to org.junit.jupiter;
    opens logica_negocio.citas to org.junit.jupiter;
    opens logica_negocio.utilidad to org.junit.jupiter;
    opens presentacion to javafx.fxml;
    opens presentacion.registro_autenticacion to javafx.fxml;
    opens presentacion.recetas to javafx.fxml;
    opens presentacion.perfiles to javafx.fxml;
    opens presentacion.comunicacion to javafx.fxml;
    opens presentacion.administracion to javafx.fxml;
    exports presentacion;
    exports logica_negocio.perfiles;
    exports logica_negocio.recetas;
    exports logica_negocio.registro_autenticacion;
    exports logica_negocio.citas;
    exports logica_negocio.utilidad;
    exports entidades.dto;
    exports entidades.modelo;
}
