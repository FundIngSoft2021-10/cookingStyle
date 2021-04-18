package presentacion.registro_autenticacion;

import entidades.dto.DTOSesion;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import presentacion.IControladorPantalla;
import entidades.dto.Pantalla;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorToGUI001 implements IControladorPantalla {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @Override
    public void inicializar(DTOSesion sesion) {
    }

    @FXML
    public void clickPantalla(MouseEvent mouseEvent) {
        try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.TO_GUI002_INICIO, new DTOSesion());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
