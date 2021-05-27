package presentacion.comunicacion;

import entidades.dto.DTOSesion;
import javafx.application.HostServices;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import presentacion.IControladorPantalla;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorToGUI003 implements IControladorPantalla {
    @FXML
    public ImageView btnCorreo;
    @FXML
    public Text btnVolver;
    @FXML
    public Text servicioAlClienteTextoSC;
    @FXML
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

    @FXML
    public void clickCorreo(KeyEvent keyEvent) {
        HostServices hostServices = (HostServices) ((Stage)(this.btnCorreo.getScene().getWindow())).getProperties().get("hostServices");
        hostServices.showDocument("https://mail.google.com/mail/?view=cm&fs=1&to=soporte.cooking@gmail.com&su=Servicio al Cliente Cooking");
    }
}
