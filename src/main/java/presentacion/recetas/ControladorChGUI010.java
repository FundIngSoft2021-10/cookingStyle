package presentacion.recetas;

import entidades.dto.DTOReceta;
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
import logica_negocio.recetas.IControladorRecetasCooker;
import presentacion.IControladorPantalla;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorChGUI010 implements IControladorPantalla {


    private DTOSesion sesion;
    private IControladorRecetasCooker controlRecetas;
    private DTOReceta receta;

    @FXML
    public TextField fieldLinkImg;
    @FXML
    public MenuButton menuPlataformas;
    @FXML
    public TextField fieldNombreRec;
    @FXML
    public TextArea fieldDescripcion;
    @FXML
    public TextField fieldLinkVideo;
    @FXML
    public Button btnModificar;
    @FXML
    public Text btnVolver;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
        this.controlRecetas = new ControladorRecetasCooker();

        this.receta = this.controlRecetas.buscarReceta(this.sesion.getIdRecetaCargada());
        this.sesion.setRecetaCargada(this.receta);

        this.fieldLinkImg.setText(this.sesion.getRecetaCargada().getReceta().getLinkImagen());
        this.fieldLinkVideo.setText(this.sesion.getRecetaCargada().getReceta().getLinkVideo());
        this.fieldDescripcion.setText(this.sesion.getRecetaCargada().getReceta().getDescripcion());
        this.fieldNombreRec.setText(this.sesion.getRecetaCargada().getReceta().getNombre());

    }

    public void clickPlataforma(MouseEvent mouseEvent) {
    }

    public void clickModificar(MouseEvent mouseEvent) {
        String nuevoNombre = this.fieldNombreRec.getText();
        String nuevoLinkImg = this.fieldLinkImg.getText();
        String nuevoLinkVideo = this.fieldLinkVideo.getText();
        String nuevaDescp = this.fieldDescripcion.getText();




    }

    public void clickVolver(MouseEvent mouseEvent) {
    }
}
