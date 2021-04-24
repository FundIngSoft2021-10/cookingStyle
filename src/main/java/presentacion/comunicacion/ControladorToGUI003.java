package presentacion.comunicacion;

import entidades.dto.DTOSesion;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import presentacion.IControladorPantalla;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorToGUI003 implements IControladorPantalla {
    private DTOSesion sesion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
    }

    @FXML
    public void clickVolver(MouseEvent mouseEvent) {
        try {
            this.volverPantalla((Event) mouseEvent, this.sesion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clickPerfil(MouseEvent mouseEvent) {
    }
}
