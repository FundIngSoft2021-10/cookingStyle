package presentacion.recetas;

import entidades.dto.DTORecetasMiniaturaCategoria;
import entidades.dto.DTOSesion;
import entidades.modelo.Categoria;
import entidades.modelo.Cooker;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import logica_negocio.recetas.ControladorRecetasCooker;
import logica_negocio.recetas.IControladorRecetasCooker;
import presentacion.IControladorPantalla;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorCoGUI003 implements IControladorPantalla {
    private DTOSesion sesion;
    private IControladorRecetasCooker controlRecetas;
    List<Categoria> categorias;
    List<DTORecetasMiniaturaCategoria> miniaturasCategoria;

    private FXRecetaMiniatura[][] miniaturasFX;

    @FXML
    public TextField fieldBuscar;
    @FXML
    public ImageView imgFotoUsuario;
    @FXML
    public ImageView btnBuscar;
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
    public ListView listviewCategorias;
    @FXML
    public Text btnVerMasCategorias;
    @FXML
    public ImageView imgC1R1;
    @FXML
    public Text textNombreC1R1;
    @FXML
    public Text textC1;
    @FXML
    public ImageView imgC1R2;
    @FXML
    public Text textNombreC1R2;
    @FXML
    public ImageView imgC2R1;
    @FXML
    public Text textNombreC2R1;
    @FXML
    public Text textC2;
    @FXML
    public ImageView imgC1R3;
    @FXML
    public Text textNombreC1R3;
    @FXML
    public AnchorPane paneReceta5;
    @FXML
    public ImageView imgC2R2;
    @FXML
    public Text textNombreC2R2;
    @FXML
    public ImageView imgC2R3;
    @FXML
    public Text textNombreC2R3;
    @FXML
    public ImageView btnAntC1;
    @FXML
    public ImageView btnSigC1;
    @FXML
    public ImageView btnAntC2;
    @FXML
    public ImageView btnSigC2;
    @FXML
    public ImageView btnAntCategorias;
    @FXML
    public ImageView btnSigCategorias;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Empaquetar el grid de categorias - recetas dentro de una matriz para fácil acceso
        this.crearMiniaturasFX();

        // Desactivar todas las recetas
        this.desactivarRecetasFX();

    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
        this.controlRecetas = new ControladorRecetasCooker((Cooker) this.sesion.getUsuario());

        // Obtener la información de la pantalla
        this.categorias = this.controlRecetas.buscarCategorias();
        this.miniaturasCategoria = this.controlRecetas.buscarMiniaturasRecetasCategoria();

        // Cargar las categorías en la pantalla
        this.cargarCategorias();


    }

    private void crearMiniaturasFX() {
        miniaturasFX = new FXRecetaMiniatura[2][3];
        this.llenarMiniaturasFX(0, 0, imgC1R1, textNombreC1R1);
        this.llenarMiniaturasFX(0, 1, imgC1R2, textNombreC1R2);
        this.llenarMiniaturasFX(0, 2, imgC1R3, textNombreC1R3);
        this.llenarMiniaturasFX(1, 0, imgC2R1, textNombreC2R1);
        this.llenarMiniaturasFX(1, 1, imgC2R2, textNombreC2R2);
        this.llenarMiniaturasFX(1, 2, imgC2R3, textNombreC2R3);
    }

    private void llenarMiniaturasFX(int i, int j, ImageView imagen, Text nombre) {
        miniaturasFX[i][j] = new FXRecetaMiniatura();
        miniaturasFX[i][j].setImagen(imagen);
        miniaturasFX[i][j].setNombre(nombre);
    }

    private void desactivarMiniaturasFX() {
        for (int i = 0; i < miniaturasFX.length; i++) {
            for (int j = 0; j < miniaturasFX[0].length; j++) {
                miniaturasFX[i][j].getImagen().setImage(null);
                miniaturasFX[i][j].getNombre().setText("");
            }
        }
    }

    private void desactivarBotonesNavegacion() {
        btnAntC1.setVisible(false);
        btnSigC1.setVisible(false);
        btnAntC2.setVisible(false);
        btnSigC2.setVisible(false);
        btnAntCategorias.setVisible(false);
        btnSigCategorias.setVisible(false);
    }

    private void desactivarRecetasFX() {
        this.textC1.setText("");
        this.textC2.setText("");
        this.desactivarBotonesNavegacion();
        this.desactivarMiniaturasFX();
    }

    private void cargarCategorias() {
        // TODO: Cargar categorías en la pantalla
    }

    public void clickPerfil(MouseEvent mouseEvent) {
    }

    public void clickBuscar(MouseEvent mouseEvent) {
    }

    public void clickMembresia(MouseEvent mouseEvent) {
    }

    public void clickServicioCliente(MouseEvent mouseEvent) {
    }

    public void clickBusquedasRecientes(MouseEvent mouseEvent) {
    }

    public void clickVerMasCategorias(MouseEvent mouseEvent) {
    }

    public void clickC1R1(MouseEvent mouseEvent) {
    }

    public void clickC1R2(MouseEvent mouseEvent) {
    }

    public void clickC2R1(MouseEvent mouseEvent) {
    }

    public void clickC1R3(MouseEvent mouseEvent) {
    }

    public void clickC2R2(MouseEvent mouseEvent) {
    }

    public void clickC2R3(MouseEvent mouseEvent) {
    }

    public void clickAntC1(MouseEvent mouseEvent) {
    }

    public void clickAntCategorias(MouseEvent mouseEvent) {
    }

    public void clickAntC2(MouseEvent mouseEvent) {
    }

    public void clickSigC1(MouseEvent mouseEvent) {
    }

    public void clickSigCategorias(MouseEvent mouseEvent) {
    }

    public void clickSigC2(MouseEvent mouseEvent) {
    }
}

