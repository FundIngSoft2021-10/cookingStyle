package presentacion.administracion;

import entidades.dto.DTOSesion;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import logica_negocio.recetas.ControladorAdministrador;
import logica_negocio.recetas.IControladorAdministrador;
import logica_negocio.registro_autenticacion.ControladorRegAut;
import presentacion.IControladorPantalla;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorAdGUI002 implements IControladorPantalla {

    private DTOSesion sesion;
    private IControladorAdministrador control;

    @FXML
    public Text textReportes;
    @FXML
    public ImageView imgReportes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
        this.control = new ControladorAdministrador();
    }

    public void clickReportes(MouseEvent mouseEvent) {
    }
}
