package presentacion.recetas;

import entidades.dto.DTOSesion;
import entidades.modelo.Cooker;
import javafx.application.HostServices;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logica_negocio.recetas.ControladorRecetasCooker;
import logica_negocio.recetas.IControladorRecetasCooker;
import presentacion.IControladorPantalla;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorCoGUI008 implements IControladorPantalla {
    private DTOSesion sesion;
    private IControladorRecetasCooker controlRecetas;

    @FXML
    public Text textServicioCliente;
    @FXML
    public ImageView imgReceta;
    @FXML
    public Text textNombreReceta;
    @FXML
    public Text textNombreChef;
    @FXML
    public Text btnVolver;
    @FXML
    public ImageView btnCarulla;
    @FXML
    public ImageView btnExito;
    @FXML
    public ImageView btnJumbo;
    @FXML
    public ImageView imgPerfil;
    @FXML
    public Text textNombreUsuario;
    @FXML
    public ImageView btnServicioCliente;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
        this.controlRecetas = new ControladorRecetasCooker((Cooker) sesion.getUsuario());

        this.textNombreUsuario.setText(sesion.getUsuario().getNombreUsuario());
        this.cargarReceta();
    }

    private void cargarReceta() {
        Image imagen;
        try {
            imagen = new Image(this.sesion.getRecetaCargada().getReceta().getLinkImagen());
        } catch (Exception e) {
            imagen = new Image("https://img.icons8.com/pastel-glyph/2x/file-not-found.png");
        }

        this.imgReceta.setImage(imagen);
        this.imgReceta.setVisible(true);
        this.textNombreReceta.setText(this.sesion.getRecetaCargada().getReceta().getNombre());
        this.textNombreChef.setText(this.sesion.getRecetaCargada().getAutor().getNombre());
    }

    @FXML
    public void clickVolver(MouseEvent mouseEvent) {
        try {
            this.volverPantalla((Event) mouseEvent, this.sesion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickExito(MouseEvent mouseEvent) {
        HostServices hostServices = (HostServices) ((Stage)(this.btnVolver.getScene().getWindow())).getProperties().get("hostServices");
        hostServices.showDocument("https://www.exito.com/");
    }

    public void clickJumbo(MouseEvent mouseEvent) {
        HostServices hostServices = (HostServices) ((Stage)(this.btnVolver.getScene().getWindow())).getProperties().get("hostServices");
        hostServices.showDocument("https://www.tiendasjumbo.co/");
    }

    public void clickCarulla(MouseEvent mouseEvent) {
        HostServices hostServices = (HostServices) ((Stage)(this.btnVolver.getScene().getWindow())).getProperties().get("hostServices");
        hostServices.showDocument("https://www.carulla.com/");
    }

    public void clickServicioCliente(MouseEvent mouseEvent) {
        try {
            this.irServicioCliente((Event) mouseEvent, this.sesion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
