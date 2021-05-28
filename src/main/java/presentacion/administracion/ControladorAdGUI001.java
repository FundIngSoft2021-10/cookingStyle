package presentacion.administracion;

import entidades.dto.DTOAutenticacion;
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

public class ControladorAdGUI001 implements IControladorPantalla {

    private DTOSesion sesion;
    private IControladorRegAut controlRegAut;

    @FXML
    public TextField textFieldUsuario;
    @FXML
    public PasswordField textFieldContrasenia;
    @FXML
    public Button btnIniciarSesion;
    @FXML
    public ProgressBar barra;
    @FXML
    public Button btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
        this.controlRegAut = new ControladorRegAut();

        this.barra.setVisible(false);

    }

    public void clickIniciarSesion(ActionEvent actionEvent) {
        String correo = this.textFieldUsuario.getText();
        String password = this.textFieldContrasenia.getText();

        if(correo.isEmpty()){
            this.crearAlerta(Alert.AlertType.ERROR, "Debe ingresar su correo");
            return;
        } else if (password.isEmpty()){
            this.crearAlerta(Alert.AlertType.ERROR, "Debe ingresar su contraseña");
            return;
        }

        DTOAutenticacion autenticacion = this.controlRegAut.autenticarUsuario(TipoUsuario.ADMIN, correo, password);

        if(autenticacion.isEstado()){
            this.crearAlerta(Alert.AlertType.INFORMATION, autenticacion.getMensaje());

            this.sesion.setTipoUsuario(TipoUsuario.ADMIN);
            this.sesion.setUsuario(autenticacion.getUsuario());

            try {
                this.cargarPantalla((Event) actionEvent, Pantalla.AD_GUI002_INICIO, this.sesion, false);
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            this.crearAlerta(Alert.AlertType.ERROR, autenticacion.getMensaje());
            return;
        }
    }

    public void clickCancelar(ActionEvent actionEvent) {
        try {
            this.volverPantalla((Event) actionEvent, this.sesion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
