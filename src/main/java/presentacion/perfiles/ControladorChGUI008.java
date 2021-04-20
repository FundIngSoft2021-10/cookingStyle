package presentacion.perfiles;

import entidades.dto.DTOSesion;
import entidades.modelo.Cooker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import logica_negocio.recetas.ControladorRecetasCooker;
import presentacion.IControladorPantalla;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorChGUI008 implements IControladorPantalla {

    private DTOSesion sesion;

    @FXML
    public Text textNombreChef;
    @FXML
    public Text textCategorias;
    @FXML
    public Text textEdad;
    @FXML
    public Text textExperiencia;
    @FXML
    public Text textEstudios;
    @FXML
    public Text btmModificarAgenda;
    @FXML
    public ImageView btnServicioCliente;
    @FXML
    public ImageView btnEditarImg;
    @FXML
    public ImageView btnEditarNomChef;
    @FXML
    public ImageView btnEditarCat;
    @FXML
    public ImageView btnEditarBiog;
    @FXML
    public ImageView btnCerrarSesion;
    @FXML
    public Text btnAgenda;
    @FXML
    public Text btnVolver;
    @FXML
    public Button btnAniadirReceta;
    @FXML
    public ImageView imgReceta;
    @FXML
    public ImageView btnRecSig;
    @FXML
    public ImageView btnRecAnt;
    @FXML
    public Button btnEditar;
    @FXML
    public Button btnEliminar;
    @FXML
    public Line line2;
    @FXML
    public Line line1;
    @FXML
    public Text textTagBiografia;
    @FXML
    public ImageView imgChef;
    @FXML
    public Text textPuntuacion;
    @FXML
    public Text tagPuntuacion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
        this.ocultar();
        this.cargarChef();

    }

    //ELIMINAR DESPUÉS
    private void ocultar(){
        //Biografia
        this.textEdad.setVisible(false);
        this.textEstudios.setVisible(false);
        this.textExperiencia.setVisible(false);
        this.textTagBiografia.setVisible(false);
        this.btnEditarBiog.setVisible(false);

        //Puntuacion
        this.tagPuntuacion.setVisible(false);
        this.textPuntuacion.setVisible(false);

        //Editar
        this.btnEditarImg.setVisible(false);
        this.btnEditarNomChef.setVisible(false);
        this.btnEditarCat.setVisible(false);

    }

    private void cargarChef(){
        this.textNombreChef.setText(this.sesion.getUsuario().getNombreUsuario());
        System.out.println("Nom: " + this.sesion.getUsuario().getNombreUsuario());
        Image imagen = new Image("https://www.utreradigital.com/web/wp-content/uploads/2019/11/maria-juez.jpg");
        this.imgChef.setImage(imagen);

    }

    public void clickServicioCliente(MouseEvent mouseEvent) {
    }

    public void clickEditarPerfil(MouseEvent mouseEvent) {
    }

    public void clickCerrar(MouseEvent mouseEvent) {
    }

    public void clickVolver(MouseEvent mouseEvent) {
    }

    public void clickEditarReceta(MouseEvent mouseEvent) {
    }

    public void clickEliminarReceta(MouseEvent mouseEvent) {
    }
}
