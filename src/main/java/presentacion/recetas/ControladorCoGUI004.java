package presentacion.recetas;

import entidades.dto.DTOSesion;
import entidades.modelo.Cooker;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import logica_negocio.recetas.ControladorRecetasCooker;
import logica_negocio.recetas.IControladorRecetasCooker;
import presentacion.IControladorPantalla;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorCoGUI004 implements IControladorPantalla {

    private DTOSesion sesion;
    private IControladorRecetasCooker controladorRecCooker;

    @FXML
    public ImageView imgFotoUsuario;
    @FXML
    public ImageView coBotonBuscar;
    @FXML
    public Text textNombreUsuario;
    @FXML
    public Text textMembresia;
    @FXML
    public ImageView btnServicioCliente;
    @FXML
    public Text textServicioCliente;
    @FXML
    public Text btnBusquedasRecientes;
    @FXML
    public Text btnVoler;
    @FXML
    public ImageView imgR1;
    @FXML
    public Text textNombreR1;
    @FXML
    public ImageView imgR2;
    @FXML
    public Text textNombreR2;
    @FXML
    public ImageView imgR4;
    @FXML
    public Text textNombreR4;
    @FXML
    public ImageView imgR3;
    @FXML
    public Text textNombreR3;
    @FXML
    public ImageView imgR5;
    @FXML
    public Text textNombreR5;
    @FXML
    public ImageView imgR6;
    @FXML
    public ImageView btnAntRecetas;
    @FXML
    public ImageView btnSigRetas;
    @FXML
    public Text textNombreR6;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
        this.controladorRecCooker = new ControladorRecetasCooker((Cooker) sesion.getUsuario());


    }

    public void clickPerfil(MouseEvent mouseEvent) {
    }

    public void textMembresia(MouseEvent mouseEvent) {
    }

    public void clickServicioCliente(MouseEvent mouseEvent) {
    }

    public void clickBusquedasRecientes(MouseEvent mouseEvent) {
    }

    public void clickVolver(MouseEvent mouseEvent) {
    }

    public void clickR1(MouseEvent mouseEvent) {
    }

    public void clickR2(MouseEvent mouseEvent) {
    }

    public void clickR4(MouseEvent mouseEvent) {
    }

    public void clickR3(MouseEvent mouseEvent) {
    }

    public void clickR5(MouseEvent mouseEvent) {
    }

    public void clickR6(MouseEvent mouseEvent) {
    }

    public void clickAntRecetas(MouseEvent mouseEvent) {
    }

    public void clickSigRetas(MouseEvent mouseEvent) {
    }
}
