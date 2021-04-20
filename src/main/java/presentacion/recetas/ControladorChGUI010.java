package presentacion.recetas;

import entidades.dto.DTOSesion;
import entidades.modelo.Cooker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import logica_negocio.recetas.ControladorRecetasCooker;
import presentacion.IControladorPantalla;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorChGUI010 implements IControladorPantalla {

    public MenuButton menuPlataformas;
    public TextField fieldNombreRec;
    public TextArea fieldDescripcion;
    public TextField fieldLinkVideo;
    public Button btnModificar;
    public Text btnVolver;
    private DTOSesion sesion;
    @FXML
    public TextField fieldLinkImg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;

        this.fieldLinkImg.setText("Prueba");

    }

    public void clickPlataforma(MouseEvent mouseEvent) {
    }

    public void clickModificar(MouseEvent mouseEvent) {
    }

    public void clickVolver(MouseEvent mouseEvent) {
    }
}
