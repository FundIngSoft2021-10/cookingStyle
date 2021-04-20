package presentacion.recetas;

import entidades.dto.DTOReceta;
import entidades.dto.DTOSesion;
import entidades.dto.Pantalla;
import entidades.modelo.Cooker;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import logica_negocio.recetas.ControladorRecetasCooker;
import logica_negocio.recetas.IControladorRecetasCooker;
import presentacion.IControladorPantalla;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorCoGUI005 implements IControladorPantalla {
    private DTOSesion sesion;
    private DTOReceta receta;
    private IControladorRecetasCooker controlRecetas;

    @FXML
    public ImageView imgReceta;
    @FXML
    public Text textNombreReceta;
    @FXML
    public Text textNombreChef;
    @FXML
    public ImageView btnFavorito;
    @FXML
    public ImageView btnReportar;
    @FXML
    public Text btnVolver;
    @FXML
    public TextArea textAreaDescripcion;
    @FXML
    public Button btnVerIng;
    @FXML
    public WebView webVideo;
    @FXML
    public ImageView imgFotoUsuario;
    @FXML
    public Text textNombreUsuario;
    @FXML
    public Text textMembresia;
    @FXML
    public ImageView btnServicioCliente;
    @FXML
    public Text textServicioCliente;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
        this.controlRecetas = new ControladorRecetasCooker((Cooker) this.sesion.getUsuario());

        // Cargar el perfil
        this.textNombreUsuario.setText(this.sesion.getUsuario().getNombreUsuario());

        // Cargar la información de la receta
        this.receta = this.controlRecetas.buscarReceta(this.sesion.getIdRecetaCargada());
        this.sesion.setRecetaCargada(this.receta);

        // Colocar la información de la receta en la pantalla
        this.cargarReceta();
    }

    private void cargarReceta() {
        this.textNombreReceta.setText(this.receta.getReceta().getNombre());
        this.textAreaDescripcion.setText(this.receta.getReceta().getDescripcion());

        // Imagen
        Image imagen;
        try {
            imagen = new Image(this.receta.getReceta().getLinkImagen());
        } catch (Exception e) {
            imagen = new Image("https://img.icons8.com/pastel-glyph/2x/file-not-found.png");
        }
        this.imgReceta.setImage(imagen);
        this.imgReceta.setVisible(true);

        // Video
        try {
            this.webVideo.getEngine().load(this.receta.getReceta().getLinkVideo());
        } catch (Exception e) {
        }

        this.cargarChef();
    }

    private void cargarChef() {
        this.textNombreChef.setText(this.receta.getAutor().getNombre());

    }

    public void clickNombreChef(MouseEvent mouseEvent) {
        // TODO: Ir perfil chef
    }

    public void clickFavorito(MouseEvent mouseEvent) {
        // TODO: Agregar a favorito
    }

    public void clickReportar(MouseEvent mouseEvent) {
        // TODO: Reportar
    }

    public void clickVolver(MouseEvent mouseEvent) {
        this.webVideo.getEngine().loadContent("");
        try {
            this.volverPantalla((Event) mouseEvent, this.sesion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickVerIng(ActionEvent actionEvent) {
        try {
            this.cargarPantalla((Event) actionEvent, Pantalla.CO_GUI007_RECETAING, this.sesion, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickPerfil(MouseEvent mouseEvent) {
        // TODO: Perfil
    }

    public void clickMembresia(MouseEvent mouseEvent) {
        // TODO: Membresía
    }

    public void clickServicioCliente(MouseEvent mouseEvent) {
        // TODO: Servicio al cliente
    }
}
