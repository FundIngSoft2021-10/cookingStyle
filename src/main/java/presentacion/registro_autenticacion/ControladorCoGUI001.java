package presentacion.registro_autenticacion;

import entidades.dto.DTOAutenticacion;
import entidades.dto.DTOSesion;
import entidades.modelo.TipoUsuario;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import logica_negocio.registro_autenticacion.ControladorRegAut;
import logica_negocio.registro_autenticacion.IControladorRegAut;
import presentacion.ControladorPantalla;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorCoGUI001 implements ControladorPantalla {
    private DTOSesion sesion;
    private IControladorRegAut controlRegAut;

    @FXML
    public TextField fieldCorreo;
    @FXML
    public PasswordField fieldPassword;
    @FXML
    public Button btnIniciarSesion;
    @FXML
    public Button btnCancelar;
    @FXML
    public ProgressBar barra;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        barra.setVisible(false);    // TODO: Programar ProgressBar
        btnIniciarSesion.setDisable(false);
    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
        this.controlRegAut = new ControladorRegAut();
    }

    @FXML
    public void clickIniciarSesion(ActionEvent actionEvent) {
        String correo = fieldCorreo.getText();
        String password = fieldPassword.getText();

        if (correo.isEmpty()) {
            this.crearAlerta(Alert.AlertType.ERROR, "Debe ingresar su correo");
            return;
        } else if (password.isEmpty()) {
            this.crearAlerta(Alert.AlertType.ERROR, "Debe ingresar su contraseña");
            return;
        }

        DTOAutenticacion autenticacion = this.controlRegAut.autenticarUsuario(TipoUsuario.COOKER, correo, password);
        if (autenticacion.isEstado()) {
            this.crearAlerta(Alert.AlertType.INFORMATION, autenticacion.getMensaje());

            this.sesion.setTipoUsuario(TipoUsuario.COOKER);
            this.sesion.setUsuario(autenticacion.getUsuario());

            // TODO: Cargar siguiente pantalla
        } else {
            this.crearAlerta(Alert.AlertType.ERROR, autenticacion.getMensaje());
            return;
        }
    }

    @FXML
    public void clickCancelar(ActionEvent actionEvent) {
        try {
            this.volverPantalla((Event) actionEvent, this.sesion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
