package presentacion.recetas;

import entidades.dto.DTORecetaMiniatura;
import entidades.dto.DTOSesion;
import entidades.modelo.Cooker;
import entidades.modelo.LineaIngrediente;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;
import logica_negocio.recetas.ControladorRecetasCooker;
import logica_negocio.recetas.IControladorRecetasCooker;
import presentacion.IControladorPantalla;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorCoGUI007 implements IControladorPantalla {

    private DTOSesion sesion;
    private IControladorRecetasCooker controladorRecCooker;

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
        this.controladorRecCooker = new ControladorRecetasCooker((Cooker) sesion.getUsuario());
        this.textNombreUsuario.setText(sesion.getUsuario().getNombreUsuario());
        System.out.println(this.sesion.getIdReceta());
        this.listViewIngredientes2.setVisible(false);
        cargarIngredientes();
        cargarMiniatura();
    }

    private void cargarMiniatura(){

        DTORecetaMiniatura miniatura = controladorRecCooker.miniaturaRecetas(this.sesion.getIdReceta());

        this.textNombreR1.setText(miniatura.getNombreReceta());
        Image imagen;
        try {
            imagen = new Image(miniatura.getLinkImagen());
        } catch (Exception e) {
            imagen = new Image("https://img.icons8.com/pastel-glyph/2x/file-not-found.png");
        }
        this.imgFotoReceta.setImage(imagen);

        this.btnChef.setText(miniatura.getAutor().getNombre());

    }

    private void cargarIngredientes(){
        List<LineaIngrediente> ingredienteLista = controladorRecCooker.ingredientesxReceta(this.sesion.getIdReceta());
        List<String> nombresIng = new ArrayList<>();

        for(LineaIngrediente ingrediente : ingredienteLista){
            nombresIng.add(ingrediente.getIngrediente().getNombre());
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
                        System.out.println("Check box for "+item+" changed from "+wasSelected+" to "+isNowSelected)
                );
                return observable ;
            }
        }));



    }

    public void clickChef(MouseEvent mouseEvent) {
    }

    public void clickFavorito(MouseEvent mouseEvent) {
    }

    public void clickReporte(MouseEvent mouseEvent) {
    }

    public void clickVolver(MouseEvent mouseEvent) {
    }

    public void clickComprar(MouseEvent mouseEvent) {
    }

    public void clickPerfil(MouseEvent mouseEvent) {
    }

    public void clickMembresia(MouseEvent mouseEvent) {
    }

    public void clickServicioCliente(MouseEvent mouseEvent) {
    }
}
