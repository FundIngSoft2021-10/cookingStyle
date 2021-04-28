package presentacion.perfiles;

import entidades.dto.DTOReceta;
import entidades.dto.DTOSesion;
import entidades.dto.Pantalla;
import entidades.modelo.Chef;
import entidades.modelo.Cooker;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import logica_negocio.citas.ControladorCitas;
import logica_negocio.recetas.ControladorRecetasChef;
import logica_negocio.recetas.ControladorRecetasCooker;
import logica_negocio.recetas.IControladorRecetasChef;
import logica_negocio.recetas.IControladorRecetasCooker;
import presentacion.IControladorPantalla;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorCoGUI010 implements IControladorPantalla {
    private DTOSesion sesion;
    private IControladorRecetasChef controlChef;
    private IControladorRecetasCooker controlRecetas;
    private int contadorRecetas;
    private List<DTOReceta> recetas;

    @FXML
    public Text textServicioCl;
    @FXML
    public ImageView imgChef;
    @FXML
    public Text textNombreChef;
    @FXML
    public Text tagPuntuacion;
    @FXML
    public Text textPuntuacion;
    @FXML
    public Text textSeguidores;
    @FXML
    public Text textCategoria;
    @FXML
    public Text tagBiografia;
    @FXML
    public Line ln1;
    @FXML
    public Line ln2;
    @FXML
    public Text tagEdad;
    @FXML
    public Text textEdad;
    @FXML
    public Text tagExperiencia;
    @FXML
    public Text textExperiencia;
    @FXML
    public Text tagEstudios;
    @FXML
    public Text textEstudios;
    @FXML
    public Text btnAgenda;
    @FXML
    public Text btnVolver;
    @FXML
    public ImageView imgReceta;
    @FXML
    public ImageView btnAnt;
    @FXML
    public ImageView btnSig;
    @FXML
    public Text TagPuntuaciones;
    @FXML
    public ImageView imgPerfil;
    @FXML
    public Text textNombreUsuario;
    @FXML
    public Text textMembresia;
    @FXML
    public ImageView imgServicioCl;
    @FXML
    public Text textNombreRec;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
        this.controlChef = new ControladorRecetasChef(this.sesion.getRecetaCargada().getAutor(), this.sesion.getConexion());
        this.controlRecetas = new ControladorRecetasCooker((Cooker) this.sesion.getUsuario(), this.sesion.getConexion());
        this.contadorRecetas = 0;
        this.recetas = new ArrayList<>();
        this.ocultar();
        this.cargarChef();
        this.cargarRecetas();
        this.btnAnt.setVisible(false);
        this.textNombreUsuario.setText(this.sesion.getUsuario().getNombreUsuario());

        if(this.recetas.size()==1){
            this.btnSig.setVisible(false);
        }
        if(this.recetas.size()==0){
            this.btnSig.setVisible(false);
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
        this.tagBiografia.setVisible(false);
        this.ln1.setVisible(false);
        this.ln2.setVisible(false);
        this.tagEdad.setVisible(false);
        this.tagEstudios.setVisible(false);
        this.tagExperiencia.setVisible(false);

        //Puntuacion
        this.tagPuntuacion.setVisible(true);
        this.textPuntuacion.setVisible(true);

        //Segidores
        this.textSeguidores.setVisible(false);

    }

    private void cargarChef(){
        //Nombre
        this.textNombreChef.setText(this.sesion.getRecetaCargada().getAutor().getNombre());

        //Imagen
        Image imagen = new Image("https://i2.wp.com/hotbook.com.mx/wp-content/uploads/2020/06/comida-casera-2.jpg?resize=800%2C534&ssl=1");
        this.imgChef.setImage(imagen);

        //Categorias

        List<String> categorias = this.controlChef.categoriasxChef(this.sesion.getRecetaCargada().getAutor().getIdUsuario());
        //CATEGORIAS PARA VER
        //List<String> categorias = this.controlChef.categoriasxChef(BigInteger.valueOf(12));
        if(categorias.size() != 0) {
            String texto = "";
            int conta = 0;
            for (String categoria : categorias) {
                texto += categoria + "\n ";
            }
            this.textCategoria.setText(texto);
        } else {
            this.textCategoria.setText("Sin categorias");
        }

        this.textPuntuacion.setText("0.0");
    }

    private void cargarRecetas(){

        this.recetas.addAll(this.controlRecetas.recetasChef(this.sesion.getRecetaCargada().getAutor().getIdUsuario()));
        //PRUEBA PARA VER
        //this.recetas.addAll(this.controlRecetas.recetasChef(BigInteger.valueOf(12)));
    }

    public void clickAgenda(MouseEvent mouseEvent) {
        this.sesion.setIdUsuarioCargado(this.sesion.getRecetaCargada().getAutor().getIdUsuario());
        try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI011_AGENDACHEF, this.sesion, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickVolver(MouseEvent mouseEvent) {
        try {
            this.volverPantalla((Event) mouseEvent, this.sesion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickReceta(MouseEvent mouseEvent) {
        try {
            this.sesion.setIdRecetaCargada(recetas.get(contadorRecetas).getReceta().getIdReceta());
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI005_VERRECETA, this.sesion, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickAnt(MouseEvent mouseEvent) {
        --this.contadorRecetas;
        this.btnSig.setVisible(true);
        if(this.contadorRecetas == 0){
            this.btnAnt.setVisible(false);
        }
        Image imagen = new Image(recetas.get(contadorRecetas).getReceta().getLinkImagen());
        this.imgReceta.setImage(imagen);
        this.textNombreRec.setText(recetas.get(contadorRecetas).getReceta().getNombre());
    }

    public void clickSig(MouseEvent mouseEvent) {
        this.contadorRecetas++;
        if(this.contadorRecetas == this.recetas.size()-1 ){
            this.btnSig.setVisible(false);
        }
        this.btnAnt.setVisible(true);
        if(this.contadorRecetas-1 != this.recetas.size() ) {
            Image imagen = new Image(recetas.get(contadorRecetas).getReceta().getLinkImagen());
            this.imgReceta.setImage(imagen);
            this.textNombreRec.setText(recetas.get(contadorRecetas).getReceta().getNombre());
        }
    }

    public void clickPerfil(MouseEvent mouseEvent) {
        //TODO: ir a perfil cooker
    }

    public void clickMembresia(MouseEvent mouseEvent) {
        //TODO: ir a pantalla membresia
    }

    public void clickServicioCliente(MouseEvent mouseEvent) {
        try {
            this.irServicioCliente((Event) mouseEvent, this.sesion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
