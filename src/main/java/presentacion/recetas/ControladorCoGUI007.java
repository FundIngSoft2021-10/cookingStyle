package presentacion.recetas;

import entidades.dto.DTOExito;
import entidades.dto.DTORecetaMiniatura;
import entidades.dto.DTOSesion;
import entidades.modelo.Cooker;
import entidades.modelo.LineaIngrediente;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import logica_negocio.recetas.ControladorRecetasCooker;
import logica_negocio.recetas.IControladorRecetasCooker;
import presentacion.IControladorPantalla;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorCoGUI007 implements IControladorPantalla {
    private DTOSesion sesion;
    private IControladorRecetasCooker controlRecetas;

    @FXML
    public ImageView imgFotoReceta;
    @FXML
    public Text textNombreR1;
    @FXML
    public Text btnChef;
    @FXML
    public ImageView imgFotoChef;
    @FXML
    public ImageView btnFavorito;
    @FXML
    public ImageView btnReporte;
    @FXML
    public Text textPuntuaciones;
    @FXML
    public Text btnVolver;
    @FXML
    public Button btnComprar;
    @FXML
    public VBox listViewIngredientes1;
    @FXML
    public VBox listViewIngredientes2;
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
        this.controlRecetas = new ControladorRecetasCooker((Cooker) sesion.getUsuario());

        this.textNombreUsuario.setText(sesion.getUsuario().getNombreUsuario());
        this.listViewIngredientes2.setVisible(false);
        this.cargarIngredientes();
        this.cargarReceta();
    }

    private void cargarReceta() {
        Image imagen;
        try {
            imagen = new Image(this.sesion.getRecetaCargada().getReceta().getLinkImagen());
        } catch (Exception e) {
            imagen = new Image("https://img.icons8.com/pastel-glyph/2x/file-not-found.png");
        }

        this.imgFotoReceta.setImage(imagen);
        this.imgFotoReceta.setVisible(true);
        this.textNombreR1.setText(this.sesion.getRecetaCargada().getReceta().getNombre());
        this.btnChef.setText(this.sesion.getRecetaCargada().getAutor().getNombre());
    }

    private void cargarIngredientes() {
        List<LineaIngrediente> ingredienteLista = this.sesion.getRecetaCargada().getReceta().getLineasIngrediente();
        // List<LineaIngrediente> ingredienteLista = controlRecetas.ingredientesxReceta(this.sesion.getIdRecetaCargada());
        List<String> nombresIng = new ArrayList<>();

        for (LineaIngrediente ingrediente : ingredienteLista) {
            String linea = ingrediente.getIngrediente().getNombre() + ", " + ingrediente.getCantidad() + " " + ingrediente.getMedida().abv();
            nombresIng.add(linea);
        }

        ObservableList<String> nombres = FXCollections.observableArrayList(nombresIng);
        ListView<String> lista = new ListView<>(nombres);
        lista.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        this.listViewIngredientes1.getChildren().addAll(lista);
        lista.setStyle("-fx-background-radius: 50; -fx-background-color: #fcba03");
        lista.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(String item) {
                BooleanProperty observable = new SimpleBooleanProperty();
                observable.addListener((obs, wasSelected, isNowSelected) ->
                        System.out.println("Check box for " + item + " changed from " + wasSelected + " to " + isNowSelected)
                );
                return observable;
            }
        }));
    }

    @FXML
    public void clickChef(MouseEvent mouseEvent) {
        //TODO: Cargar pantalla CoPerfilChef
        /*try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI00X_PERFILCH, this.sesion, false);
        } catch (IOException e){
            e.printStackTrace();
        }*/
    }

    @FXML
    public void clickFavorito(MouseEvent mouseEvent) {
        DTOExito exito = this.controlRecetas.agregarRecetaListaFavoritos(this.sesion.getRecetaCargada().getReceta().getIdReceta());
        this.crearAlerta(Alert.AlertType.INFORMATION, exito.getMensaje());
    }

    @FXML
    public void clickReporte(MouseEvent mouseEvent) {
        //TODO: Cargar pantalla Reporte
        /*try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI00X_REPORTE, this.sesion, false);
        } catch (IOException e){
            e.printStackTrace();
        }*/
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
    public void clickComprar(MouseEvent mouseEvent) {
        //TODO: Cargar pantalla Reporte
        /*try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI00X_REPORTE, this.sesion, false);
        } catch (IOException e){
            e.printStackTrace();
        }*/
    }

    @FXML
    public void clickPerfil(MouseEvent mouseEvent) {
        //TODO: Cargar pantalla PerfilCooker
        /*try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI00X_PERFIL, this.sesion, false);
        } catch (IOException e){
            e.printStackTrace();
        }*/
    }

    @FXML
    public void clickMembresia(MouseEvent mouseEvent) {
        //TODO: Cargar pantalla Membresia
        /*try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI00X_MEMBRESIA, this.sesion, false);
        } catch (IOException e){
            e.printStackTrace();
        }*/
    }

    @FXML
    public void clickServicioCliente(MouseEvent mouseEvent) {
        //TODO: Cargar pantalla ServicioCliente
        /*try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI00X_SERVICIOCL, this.sesion, false);
        } catch (IOException e){
            e.printStackTrace();
        }*/
    }
}
