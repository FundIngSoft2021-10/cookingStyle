package presentacion.registro_autenticacion;

import entidades.dto.DTOSesion;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import logica_negocio.registro_autenticacion.ControladorInicio;
import logica_negocio.registro_autenticacion.IControladorInicio;
import presentacion.IControladorPantalla;
import entidades.dto.Pantalla;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorToGUI001 implements IControladorPantalla {
    private IControladorInicio controlInicio;
    private DTOSesion sesion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Crear la conexión BD
        this.controlInicio = new ControladorInicio();
        this.sesion = new DTOSesion(this.controlInicio.conectarBD());
    }

    @Override
    public void inicializar(DTOSesion sesion) {
    }

    @FXML
    public void clickPantalla(MouseEvent mouseEvent) {
        try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.TO_GUI002_INICIO, this.sesion, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
