package presentacion.recetas;

import entidades.dto.DTOExito;
import entidades.dto.DTOReceta;
import entidades.dto.DTOSesion;
import entidades.dto.Pantalla;
import entidades.modelo.Cooker;
import entidades.modelo.LineaIngrediente;
import entidades.modelo.PasoReceta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logica_negocio.recetas.ControladorRecetasCooker;
import org.w3c.dom.ls.LSInput;
import presentacion.IControladorPantalla;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorCoGUI006 implements IControladorPantalla {
    private DTOSesion sesion;
    private DTOReceta receta;
    private ControladorRecetasCooker controlRecetas;

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
    public VBox vBoxPasosReceta;
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
        this.receta = this.sesion.getRecetaCargada();
        this.controlRecetas = new ControladorRecetasCooker((Cooker) this.sesion.getUsuario());

        // Cargar el perfil
        this.textNombreUsuario.setText(this.sesion.getUsuario().getNombreUsuario());

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
        try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI010_PERFILCHEF, this.sesion, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickFavorito(MouseEvent mouseEvent) {
        DTOExito exito = this.controlRecetas.agregarRecetaListaFavoritos(this.receta.getReceta().getIdReceta());
        this.crearAlerta(Alert.AlertType.INFORMATION, exito.getMensaje());
    }

    public void clickReportar(MouseEvent mouseEvent) {
        // TODO: Reportar
    }

    public void clickVolver(MouseEvent mouseEvent) {
        try {
            this.volverPantalla((Event) mouseEvent, this.sesion);
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
