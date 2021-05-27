package presentacion.comunicacion;

import entidades.dto.DTOSesion;
import entidades.dto.Pantalla;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import logica_negocio.recetas.ControladorRecetasChef;
import logica_negocio.recetas.IControladorRecetasChef;
import presentacion.IControladorPantalla;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorCoGUI012 implements IControladorPantalla  {

    private DTOSesion sesion;
    private IControladorRecetasChef controlChef;

    @FXML
    public Text textServicioCliente;
    @FXML
    public ImageView imgPerfil;
    @FXML
    public Text textNombre;
    @FXML
    public Text textCalificacion;
    @FXML
    public Text textSeguidores;
    @FXML
    public Text textCategorias;
    @FXML
    public Text textBio;
    @FXML
    public Line ln1;
    @FXML
    public Line ln2;
    @FXML
    public Text textEdad;
    @FXML
    public Text textEdadInfo;
    @FXML
    public Text textExp;
    @FXML
    public Text textExpInfo;
    @FXML
    public Text textEstudios;
    @FXML
    public Text textEstInfo;
    @FXML
    public Text btnAgenda;
    @FXML
    public Text btnVover;
    @FXML
    public Button btnContactar;
    @FXML
    public Hyperlink linkCorreoChef;
    @FXML
    public ImageView imgPerfilCooker;
    @FXML
    public Text textNomUsuario;
    @FXML
    public ImageView btnServicioCliente;
    @FXML


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
        this.controlChef = new ControladorRecetasChef(this.sesion.getRecetaCargada().getAutor());
        this.ocultar();
        this.cargarChef();

        this.textNomUsuario.setText(this.sesion.getUsuario().getNombreUsuario());


    }

    private void ocultar(){
        //Seguidores
        this.textSeguidores.setVisible(false);

        //Biografía
        this.ln1.setVisible(false);
        this.textBio.setVisible(false);
        this.ln2.setVisible(false);
        this.textEdad.setVisible(false);
        this.textEdadInfo.setVisible(false);
        this.textExp.setVisible(false);
        this.textExpInfo.setVisible(false);
        this.textEstudios.setVisible(false);
        this.textEstInfo.setVisible(false);
    }

    private void cargarChef(){
        //Nombre Chef
        this.textNombre.setText(this.sesion.getRecetaCargada().getAutor().getNombre());

        //Imagen
        Image imagen = new Image("https://i2.wp.com/hotbook.com.mx/wp-content/uploads/2020/06/comida-casera-2.jpg?resize=800%2C534&ssl=1");
        this.imgPerfil.setImage(imagen);

        //Categorias
        List<String> categorias = this.controlChef.categoriasxChef(this.sesion.getRecetaCargada().getAutor().getIdUsuario());
        //CATEGORIAS PARA VER
        if(categorias.size() != 0) {
            String texto = "";
            int conta = 0;
            for (String categoria : categorias) {
                texto += categoria + "\n ";
            }
            this.textCategorias.setText(texto);
        } else {
            this.textCategorias.setText("Sin categorias");
        }
    }

    private void cargarCorreo(){

    }

    @FXML
    public void clickAgenda(MouseEvent mouseEvent) {
        this.sesion.setIdUsuarioCargado(this.sesion.getRecetaCargada().getAutor().getIdUsuario());
        try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI011_AGENDACHEF, this.sesion, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void clickRedireccion(MouseEvent mouseEvent) {

    }

    @FXML
    public void clickPerfilUsuario(MouseEvent mouseEvent) {
        try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI013_PERFIL, this.sesion, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clickServicioCliente(MouseEvent mouseEvent) {
    }
}
