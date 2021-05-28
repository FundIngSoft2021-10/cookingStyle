package presentacion.perfiles;

import entidades.dto.DTOPerfil;
import entidades.dto.DTORecetaMiniatura;
import entidades.dto.DTOSesion;
import entidades.modelo.Cooker;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import logica_negocio.perfiles.ControladorPerfiles;
import logica_negocio.perfiles.IControladorPerfiles;
import logica_negocio.recetas.ControladorRecetasCooker;
import logica_negocio.recetas.IControladorRecetasCooker;
import presentacion.IControladorPantalla;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorCoGUI014 implements IControladorPantalla {
    private DTOSesion sesion;
    private IControladorPerfiles controlPerfiles;
    private IControladorRecetasCooker controlCooker;
    private int contadorRecetas;
    private List<DTORecetaMiniatura> recetas;

    @FXML
    public ImageView imgPerfil;
    @FXML
    public Text textNombreUsuario;
    @FXML
    public ImageView btnServicioCliente;
    @FXML
    public Text textServicioCliente;
    @FXML
    public ImageView imgCooker;
    @FXML
    public Text btnVolver;
    @FXML
    public TextField fieldNombre;
    @FXML
    public TextField fieldNombreUsuario;
    @FXML
    public Button btnEditar;
    @FXML
    public ImageView imgReceta;
    @FXML
    public ImageView btnSig;
    @FXML
    public ImageView btnAntes;
    @FXML
    public Button btnEliminarFavoritos;
    @FXML
    public Text textNombReceta;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
        this.controlPerfiles = new ControladorPerfiles();
        this.contadorRecetas = 0;
        this.controlCooker = new ControladorRecetasCooker((Cooker) this.sesion.getUsuario());
        this.recetas = new ArrayList<>();

        //Cargar cooker
        this.cargarCooker();

        //Cargar recetas favoritas
        this.cargarRecetasFavoritas();

        //Configuración flechas
        this.configurarFlechas();
        this.btnEliminarFavoritos.setVisible(false);
    }

    private void cargarCooker() {
        //Nombre
        this.fieldNombre.setText(this.sesion.getUsuario().getNombre());
        //Nombre usuario
        this.fieldNombreUsuario.setText(this.sesion.getUsuario().getNombreUsuario());

        //Imagen
        Image imagen = new Image("https://socialtools.me/wp-content/uploads/2016/04/foto-de-perfil.jpg");
        this.imgCooker.setImage(imagen);

        //Nombre usuario
        this.textNombreUsuario.setText(this.sesion.getUsuario().getNombreUsuario());
    }

    private void cargarRecetasFavoritas() {
        this.recetas.addAll(this.controlCooker.recetasListaFavoritos());
    }

    private void configurarFlechas() {
        this.contadorRecetas = 0;

        this.btnAntes.setVisible(false);
        if (this.recetas.size() == 1) {
            this.btnSig.setVisible(false);
        }
        if (this.recetas.size() == 0) {
            this.btnSig.setVisible(false);
            Image imagen = new Image("https://i.pinimg.com/736x/3a/ab/e0/3aabe0e9a520b9ad90407a82f85adb42.jpg");
            this.imgReceta.setImage(imagen);
        }
        if (this.recetas.size() != 0) {
            Image imagen = new Image(recetas.get(contadorRecetas).getLinkImagen());
            this.imgReceta.setImage(imagen);
            this.textNombReceta.setText(recetas.get(contadorRecetas).getNombreReceta());
        }
    }

    public void clickServicioCliente(MouseEvent mouseEvent) {
    }

    public void clickVolver(MouseEvent mouseEvent) {
        try {
            this.volverPantalla((Event) mouseEvent, this.sesion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickEliminarFavoritos(MouseEvent mouseEvent) {
    }

    public void clickSig(MouseEvent mouseEvent) {
        this.contadorRecetas++;
        if (this.contadorRecetas == this.recetas.size() - 1) {
            this.btnSig.setVisible(false);
        }
        this.btnAntes.setVisible(true);
        if (this.contadorRecetas - 1 != this.recetas.size()) {
            Image imagen = new Image(recetas.get(contadorRecetas).getLinkImagen());
            this.imgReceta.setImage(imagen);
            this.textNombReceta.setText(recetas.get(contadorRecetas).getNombreReceta());
        }
    }

    public void clickAntes(MouseEvent mouseEvent) {
        this.contadorRecetas--;
        this.btnSig.setVisible(true);
        if (this.contadorRecetas == 0) {
            this.btnAntes.setVisible(false);
        }
        Image imagen = new Image(recetas.get(contadorRecetas).getLinkImagen());
        this.imgReceta.setImage(imagen);
        this.textNombReceta.setText(recetas.get(contadorRecetas).getNombreReceta());
    }

    public void clickEditar(MouseEvent mouseEvent) {
        boolean modificado = false;

        if (fieldNombre.getText() != this.sesion.getUsuario().getNombre()) {
            DTOPerfil nuevoPerfil = this.controlPerfiles.modificarPerfil(this.sesion.getUsuario(), "nombre", fieldNombre.getText());
            if (nuevoPerfil.isEstado()) {
                this.sesion.setUsuario(nuevoPerfil.getUsuario());
                modificado = true;
            } else {
                this.crearAlerta(Alert.AlertType.ERROR, nuevoPerfil.getMensaje());
            }
        }

        if (fieldNombre.getText() != this.sesion.getUsuario().getNombreUsuario()) {
            DTOPerfil nuevoPerfil = this.controlPerfiles.modificarPerfil(this.sesion.getUsuario(), "nombreUsuario", fieldNombreUsuario.getText());
            if (nuevoPerfil.isEstado()) {
                this.sesion.setUsuario(nuevoPerfil.getUsuario());
                modificado = true;
            } else {
                this.crearAlerta(Alert.AlertType.ERROR, nuevoPerfil.getMensaje());
            }
        }

        if (modificado) {
            this.crearAlerta(Alert.AlertType.INFORMATION, "Perfil modificado exitosamente");
            this.cargarCooker();
        }
    }
}
