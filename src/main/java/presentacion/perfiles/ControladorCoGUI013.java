package presentacion.perfiles;

import entidades.dto.DTOExito;
import entidades.dto.DTORecetaMiniatura;
import entidades.dto.DTOSesion;
import entidades.dto.Pantalla;
import entidades.modelo.Cooker;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class ControladorCoGUI013 implements IControladorPantalla {
    private DTOSesion sesion;
    private IControladorPerfiles controlPerfiles;
    private IControladorRecetasCooker controlCooker;
    private int contadorRecetas;
    private List<DTORecetaMiniatura> recetas;

    @FXML
    public Text textNombReceta;
    @FXML
    public Text textNomUsuario;
    @FXML
    public ImageView imgCooker;
    @FXML
    public Text textNombre;
    @FXML
    public Text btnVolver;
    @FXML
    public ImageView btnCerrarSesion;
    @FXML
    public ImageView btnEliminar;
    @FXML
    public ImageView btnEditarPerfil;
    @FXML
    public ImageView imgReceta;
    @FXML
    public ImageView btnSig;
    @FXML
    public ImageView btnAntes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
        this.controlPerfiles = new ControladorPerfiles();
        this.controlCooker = new ControladorRecetasCooker((Cooker) this.sesion.getUsuario());
        this.contadorRecetas = 0;
        this.recetas = new ArrayList<>();

        //Cargar cooker
        this.cargarCooker();

        //Cargar recetas favoritas
        this.cargarRecetasFavoritas();

        //Configuración flechas

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

    private void cargarCooker() {
        //Nombre
        this.textNombre.setText(this.sesion.getUsuario().getNombre());

        //Imagen
        Image imagen = new Image("https://socialtools.me/wp-content/uploads/2016/04/foto-de-perfil.jpg");
        this.imgCooker.setImage(imagen);

        //Nombre usuario
        this.textNomUsuario.setText(this.sesion.getUsuario().getNombreUsuario());
    }

    private void cargarRecetasFavoritas() {
        this.recetas.addAll(this.controlCooker.recetasListaFavoritos());
    }

    public void clickVolver(MouseEvent mouseEvent) {
        try {
            this.volverPantalla((Event) mouseEvent, this.sesion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickServicioCliente(MouseEvent mouseEvent) {
    }

    public void clickCerraSesion(MouseEvent mouseEvent) {
        try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.TO_GUI002_INICIO, this.sesion, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickEliminarPerfil(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar Perfil");
        alert.setHeaderText(null);
        alert.setContentText("¿Seguro quiere eliminar su perfil?");
        ButtonType buttonTypeAceptar = new ButtonType("Aceptar");
        ButtonType buttonTypeCancel = new ButtonType("Cancelar");
        alert.getButtonTypes().setAll(buttonTypeAceptar, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeAceptar) {
            DTOExito exito = this.controlPerfiles.eliminarPerfil(this.sesion.getUsuario().getIdUsuario());
            this.crearAlerta(Alert.AlertType.INFORMATION, exito.getMensaje());

            try {
                this.cargarPantalla((Event) mouseEvent, Pantalla.TO_GUI002_INICIO, this.sesion, false);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (result.get() == buttonTypeCancel) {
            return;
        }

    }

    public void clickEditarPerfil(MouseEvent mouseEvent) {
        try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI014_EDITARPERFIL, this.sesion, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickReceta(MouseEvent mouseEvent) {
        try {
            this.sesion.setIdRecetaCargada(recetas.get(contadorRecetas).getIdReceta());
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI005_VERRECETA, this.sesion, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        --this.contadorRecetas;
        this.btnSig.setVisible(true);
        if (this.contadorRecetas == 0) {
            this.btnAntes.setVisible(false);
        }
        Image imagen = new Image(recetas.get(contadorRecetas).getLinkImagen());
        this.imgReceta.setImage(imagen);
        this.textNombReceta.setText(recetas.get(contadorRecetas).getNombreReceta());
    }
}
