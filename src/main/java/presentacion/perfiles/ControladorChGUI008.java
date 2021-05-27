package presentacion.perfiles;

import entidades.dto.DTOExito;
import entidades.dto.DTOReceta;
import entidades.dto.DTOSesion;
import entidades.dto.Pantalla;
import entidades.modelo.Chef;
import entidades.modelo.Cooker;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import logica_negocio.perfiles.ControladorPerfiles;
import logica_negocio.perfiles.IControladorPerfiles;
import logica_negocio.recetas.ControladorRecetasChef;
import logica_negocio.recetas.ControladorRecetasCooker;
import logica_negocio.recetas.IControladorRecetasChef;
import logica_negocio.recetas.IControladorRecetasCooker;
import presentacion.IControladorPantalla;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControladorChGUI008 implements IControladorPantalla {

    private DTOSesion sesion;
    private IControladorRecetasChef controlChef;
    private IControladorRecetasCooker controlRecetas;
    private IControladorPerfiles controlPerfiles;
    private int contadorRecetas;
    private List<DTOReceta> recetas;

    @FXML
    public ImageView btnEliminarPerfil;
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
    @FXML
    public Text tagEdad;
    @FXML
    public Text tagExperiencia;
    @FXML
    public Text tagEstudios;
    @FXML
    public Text tagSegidores;
    @FXML
    public Text textNombreRec;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
        this.controlChef = new ControladorRecetasChef((Chef) this.sesion.getUsuario());
        this.controlRecetas = new ControladorRecetasCooker();
        this.controlPerfiles = new ControladorPerfiles();
        this.contadorRecetas = 0;
        this.recetas = new ArrayList<>();
        this.ocultar();
        this.cargarChef();
        this.cargarRecetas();
        this.btnEliminarPerfil.setVisible(true);
        this.btnRecAnt.setVisible(false);
        if(this.recetas.size()==1){
            this.btnRecSig.setVisible(false);
        }
        if(this.recetas.size()==0){
            this.btnRecSig.setVisible(false);
            Image imagen = new Image("https://i.pinimg.com/736x/3a/ab/e0/3aabe0e9a520b9ad90407a82f85adb42.jpg");
            this.imgReceta.setImage(imagen);
        }
        if(this.recetas.size()!=0){
            Image imagen = new Image(recetas.get(contadorRecetas).getReceta().getLinkImagen());
            this.imgReceta.setImage(imagen);
            this.textNombreRec.setText(recetas.get(contadorRecetas).getReceta().getNombre());
        }

    }

    //ELIMINAR DESPUÉS
    private void ocultar(){
        //Biografia
        this.textEdad.setVisible(false);
        this.textEstudios.setVisible(false);
        this.textExperiencia.setVisible(false);
        this.textTagBiografia.setVisible(false);
        this.btnEditarBiog.setVisible(false);
        this.line1.setVisible(false);
        this.line2.setVisible(false);
        this.tagEdad.setVisible(false);
        this.tagEstudios.setVisible(false);
        this.tagExperiencia.setVisible(false);

        //Puntuacion
        this.tagPuntuacion.setVisible(true);
        this.textPuntuacion.setVisible(true);

        //Editar
        this.btnEditarImg.setVisible(false);
        this.btnEditarNomChef.setVisible(false);
        this.btnEditarCat.setVisible(false);

        //Segidores
        this.tagSegidores.setVisible(false);

    }

    private void cargarChef(){
        //Nombre
        this.textNombreChef.setText(this.sesion.getUsuario().getNombreUsuario());

        //Imagen
        Image imagen = new Image("https://www.utreradigital.com/web/wp-content/uploads/2019/11/maria-juez.jpg");
        this.imgChef.setImage(imagen);

        //Categorias

        List<String> categorias = this.controlChef.categoriasxChef(this.sesion.getUsuario().getIdUsuario());
        //CATEGORIAS PARA VER
        //List<String> categorias = this.controlChef.categoriasxChef(BigInteger.valueOf(12));
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

        this.textPuntuacion.setText("0.0");
    }

    private void cargarRecetas(){

        this.recetas.addAll(this.controlRecetas.recetasChef(this.sesion.getUsuario().getIdUsuario()));
        //PRUEBA PARA VER
        //this.recetas.addAll(this.controlRecetas.recetasChef(BigInteger.valueOf(12)));
    }

    @FXML
    public void clickServicioCliente(MouseEvent mouseEvent) {
        try {
            this.irServicioCliente((Event) mouseEvent, this.sesion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clickEditarPerfil(MouseEvent mouseEvent) {
        //TODO: editar perfil del chef
    }

    @FXML
    public void clickCerrar(MouseEvent mouseEvent) {
        try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.TO_GUI002_INICIO, this.sesion, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clickEditarReceta(MouseEvent mouseEvent) {
        try {
            System.out.println(recetas.get(contadorRecetas).getReceta().getNombre());
            this.sesion.setIdRecetaCargada(recetas.get(contadorRecetas).getReceta().getIdReceta());
            this.cargarPantalla((Event) mouseEvent, Pantalla.CH_GUI010_EDITARRECETA, this.sesion, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clickEliminarReceta(MouseEvent mouseEvent) {
        //todo: eliminar receta
    }

    @FXML
    public void clickAniadirReceta(MouseEvent mouseEvent) {
        try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CH_GUI009_SUBIRRECETA, this.sesion, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clickSig(MouseEvent mouseEvent) {
        this.contadorRecetas++;
        if(this.contadorRecetas == this.recetas.size()-1 ){
            this.btnRecSig.setVisible(false);
        }
        this.btnRecAnt.setVisible(true);
        if(this.contadorRecetas-1 != this.recetas.size() ) {
            Image imagen = new Image(recetas.get(contadorRecetas).getReceta().getLinkImagen());
            this.imgReceta.setImage(imagen);
            this.textNombreRec.setText(recetas.get(contadorRecetas).getReceta().getNombre());
        }
    }

    @FXML
    public void clickAnt(MouseEvent mouseEvent) {
        --this.contadorRecetas;
        this.btnRecSig.setVisible(true);
        if(this.contadorRecetas == 0){
            this.btnRecAnt.setVisible(false);
        }
        Image imagen = new Image(recetas.get(contadorRecetas).getReceta().getLinkImagen());
        this.imgReceta.setImage(imagen);
        this.textNombreRec.setText(recetas.get(contadorRecetas).getReceta().getNombre());
    }

    @FXML
    public void clickReceta(MouseEvent mouseEvent) {
        //TODO: Ver receta para chef
    }

    @FXML
    public void clickVerAgenda(MouseEvent mouseEvent) {
        try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CH_GUI011_VERAGENDA, this.sesion, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clickEliminarPerfil(MouseEvent mouseEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar Perfil");
        alert.setHeaderText(null);
        alert.setContentText("¿Seguro quiere eliminar su perfil?");
        ButtonType buttonTypeAceptar = new ButtonType("Aceptar");
        ButtonType buttonTypeCancel = new ButtonType("Cancelar");
        alert.getButtonTypes().setAll(buttonTypeAceptar, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeAceptar){
            DTOExito exito = this.controlPerfiles.eliminarPerfil(this.sesion.getUsuario().getIdUsuario());
            this.crearAlerta(Alert.AlertType.INFORMATION, exito.getMensaje());

            try {
                this.cargarPantalla((Event) mouseEvent, Pantalla.TO_GUI002_INICIO, this.sesion, false);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (result.get() == buttonTypeCancel){
            return;
        }

    }
}
