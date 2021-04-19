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

public class ControladorToGUI002 implements IControladorPantalla {
    private DTOSesion sesion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
    }

    @FXML
    public void clickRegistrarseCooker(MouseEvent mouseEvent) {
        try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI002_REGISTRAR, this.sesion, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clickRegistrarseChef(MouseEvent mouseEvent) {
        try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CH_GUI002_REGISTRAR, this.sesion, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clickIniciarSesionCooker(MouseEvent mouseEvent) {
        try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI001_INICIARSESION, this.sesion, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clickIniciarSesionChef(MouseEvent mouseEvent) {
        try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CH_GUI001_INICIARSESION, this.sesion, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clickIniciarAdmin(MouseEvent mouseEvent) {
    }
}
