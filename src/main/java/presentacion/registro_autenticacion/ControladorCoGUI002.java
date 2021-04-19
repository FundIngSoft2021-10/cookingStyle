package presentacion.registro_autenticacion;

import entidades.dto.DTORegistro;
import entidades.dto.DTOSesion;
import entidades.dto.Pantalla;
import entidades.modelo.TipoUsuario;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import logica_negocio.registro_autenticacion.ControladorRegAut;
import logica_negocio.registro_autenticacion.IControladorRegAut;
import presentacion.IControladorPantalla;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorCoGUI002 implements IControladorPantalla {
    private DTOSesion sesion;
    private IControladorRegAut controlRegAut;

    @FXML
    public ProgressBar barraPassword;
    @FXML
    public ProgressBar barraConfirmarPassword;
    @FXML
    public Button btnRegistrarse;
    @FXML
    public Button btnCancelar;
    @FXML
    public TextField fieldNombres;
    @FXML
    public TextField fieldApellidos;
    @FXML
    public TextField fieldNombreUsuario;
    @FXML
    public TextField fieldCorreo;
    @FXML
    public PasswordField fieldPassword;
    @FXML
    public PasswordField fieldConfirmarPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO: Programar ProgressBar
        this.barraPassword.setVisible(false);
        this.barraConfirmarPassword.setVisible(false);
    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
        this.controlRegAut = new ControladorRegAut();
    }

    @FXML
    public void clickRegistrarse(ActionEvent actionEvent) {
        String nombres = fieldNombres.getText();
        String apellidos = fieldApellidos.getText();
        String nombreUsuario = fieldNombreUsuario.getText();
        String correo = fieldCorreo.getText();
        String password = fieldPassword.getText();
        String confirmarPassword = fieldConfirmarPassword.getText();

        if (nombres.isEmpty() || apellidos.isEmpty() || nombreUsuario.isEmpty() || correo.isEmpty() ||
        password.isEmpty() || confirmarPassword.isEmpty()) {
            this.crearAlerta(Alert.AlertType.ERROR, "Todos los campos son obligatorios");
            return;
        }

        if (!password.equals(confirmarPassword)) {
            this.crearAlerta(Alert.AlertType.ERROR, "Las contraseñas no coinciden");
            return;
        }

        DTORegistro registro = this.controlRegAut.registrarUsuario(TipoUsuario.COOKER, correo, password, nombreUsuario, nombres + " " + apellidos);
        if (registro.isEstado()) {
            this.crearAlerta(Alert.AlertType.INFORMATION, registro.getMensaje());

            this.sesion.setTipoUsuario(TipoUsuario.COOKER);
            this.sesion.setUsuario(registro.getUsuario());

            // TODO: Cargar siguiente pantalla
        } else {
            this.crearAlerta(Alert.AlertType.ERROR, registro.getMensaje());
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
