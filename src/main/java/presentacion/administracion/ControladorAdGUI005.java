package presentacion.administracion;

import entidades.dto.DTOReceta;
import entidades.dto.DTOSesion;
import entidades.modelo.Cooker;
import entidades.modelo.PasoReceta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logica_negocio.recetas.ControladorRecetasCooker;
import presentacion.IControladorPantalla;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorAdGUI005 implements IControladorPantalla {


    private DTOSesion sesion;
    private DTOReceta receta;
    private ControladorRecetasCooker controlRecetas;

    @FXML
    public VBox vBoxPasosReceta;
    @FXML
    public Text textServicioCliente;
    @FXML
    public ImageView imgReceta;
    @FXML
    public Text textNombreReceta;
    @FXML
    public Text textNombreChef;
    @FXML
    public ImageView fotoChefIngredientes;
    @FXML
    public ImageView btnFavorito;
    @FXML
    public ImageView btnReportar;
    @FXML
    public Text btnVolver;
    @FXML
    public ScrollPane scrollPanePasosReceta;
    @FXML
    public ImageView imgFotoUsuario;
    @FXML
    public Text textNombreUsuario;
    @FXML
    public Text textMembresia;
    @FXML
    public ImageView btnServicioCliente;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
        this.receta = this.sesion.getRecetaCargada();
        this.controlRecetas = new ControladorRecetasCooker();

        // Cargar el perfil
        this.textNombreUsuario.setText(this.sesion.getUsuario().getNombreUsuario());

        //Fav y Reporte y membresia
        this.btnFavorito.setVisible(false);
        this.btnReportar.setVisible(false);
        this.textMembresia.setVisible(false);

        // Colocar la información de la receta en la pantalla
        this.cargarReceta();
    }

    private void cargarReceta() {
        this.textNombreReceta.setText(this.receta.getReceta().getNombre());

        // Imagen
        Image imagen;
        try {
            imagen = new Image(this.receta.getReceta().getLinkImagen());
        } catch (Exception e) {
            imagen = new Image("https://img.icons8.com/pastel-glyph/2x/file-not-found.png");
        }
        this.imgReceta.setImage(imagen);
        this.imgReceta.setVisible(true);

        this.cargarChef();
        this.cargarPasos();
    }

    private void cargarChef() {
        this.textNombreChef.setText(this.receta.getAutor().getNombre());
    }

    private void cargarPasos() {
        List<PasoReceta> pasos = this.sesion.getRecetaCargada().getReceta().getPasosReceta();
        List<String> stringPasos = new ArrayList<>();
        int contadorPaso = 1;

        for(PasoReceta paso : pasos){
            String linea = " " + contadorPaso + ". " + paso.getTexto() + "  ";
            stringPasos.add(linea);
            contadorPaso++;
        }

        ObservableList<String> listaPasos = FXCollections.observableArrayList(stringPasos);
        ListView<String> lista = new ListView<>(listaPasos);

        this.vBoxPasosReceta.getChildren().addAll(lista);

    }

    public void clickNombreChef(MouseEvent mouseEvent) {
    }

    public void clickFavorito(MouseEvent mouseEvent) {
    }

    public void clickReportar(MouseEvent mouseEvent) {
    }

    public void clickVolver(MouseEvent mouseEvent) {
        try {
            this.volverPantalla((Event) mouseEvent, this.sesion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickPerfil(MouseEvent mouseEvent) {
    }

    public void clickMembresia(MouseEvent mouseEvent) {
    }

    public void clickServicioCliente(MouseEvent mouseEvent) {
    }
}
