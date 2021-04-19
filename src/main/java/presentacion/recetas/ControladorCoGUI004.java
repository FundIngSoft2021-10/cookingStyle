package presentacion.recetas;

import entidades.dto.DTOReceta;
import entidades.dto.DTORecetaMiniatura;
import entidades.dto.DTOSesion;
import entidades.dto.Pantalla;
import entidades.modelo.Cooker;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import logica_negocio.recetas.ControladorRecetasCooker;
import logica_negocio.recetas.IControladorRecetasCooker;
import presentacion.IControladorPantalla;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorCoGUI004 implements IControladorPantalla {

    private DTOSesion sesion;
    private IControladorRecetasCooker controladorRecCooker;
    private FXRecetaMiniatura[][] miniaturasFX;
    private int contadorRecetasPantalla;
    private List<DTORecetaMiniatura> miniaturas;
    private int contadorObservadas;
    private int pagina;

    @FXML
    public ImageView imgFotoUsuario;
    @FXML
    public ImageView coBotonBuscar;
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
    public Text btnVoler;
    @FXML
    public ImageView imgR1;
    @FXML
    public Text textNombreR1;
    @FXML
    public ImageView imgR2;
    @FXML
    public Text textNombreR2;
    @FXML
    public ImageView imgR4;
    @FXML
    public Text textNombreR4;
    @FXML
    public ImageView imgR3;
    @FXML
    public Text textNombreR3;
    @FXML
    public ImageView imgR5;
    @FXML
    public Text textNombreR5;
    @FXML
    public ImageView imgR6;
    @FXML
    public ImageView btnAntRecetas;
    @FXML
    public ImageView btnSigRetas;
    @FXML
    public Text textNombreR6;
    @FXML
    public TextField fieldBuscar;
    @FXML
    public ImageView btnBuscar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnAntRecetas.setVisible(false);
        btnSigRetas.setVisible(false);

        miniaturasFX = new FXRecetaMiniatura[2][3];
        this.crearMiniaturasFX();
        desactivarMiniaturasFX();

    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
        this.controladorRecCooker = new ControladorRecetasCooker((Cooker) sesion.getUsuario());
        this.contadorRecetasPantalla = 0;
        this.contadorObservadas = 0;
        this.miniaturas = new ArrayList<>();
        textNombreUsuario.setText(sesion.getUsuario().getNombreUsuario());
    }

    private void crearMiniaturasFX() {
        this.llenarMiniaturasFX(0, 0, imgR1, textNombreR1);
        this.llenarMiniaturasFX(0, 1, imgR2, textNombreR2);
        this.llenarMiniaturasFX(0, 2, imgR3, textNombreR3);
        this.llenarMiniaturasFX(1, 0, imgR4, textNombreR4);
        this.llenarMiniaturasFX(1, 1, imgR5, textNombreR5);
        this.llenarMiniaturasFX(1, 2, imgR6, textNombreR6);
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

    private BigInteger enviarReceta(String nombreReceta){

        BigInteger idReceta = null;
        for(DTORecetaMiniatura miniatura : this.miniaturas){
            if(miniatura.getNombreReceta().equals(nombreReceta)){
                idReceta = miniatura.getIdReceta();
            }
        }
        return idReceta; 
    }

    public void clickPerfil(MouseEvent mouseEvent) {
        //TODO: Cargar pantalla PerfilCooker
        /*try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI00X_PERFIL, this.sesion, false);
        } catch (IOException e){
            e.printStackTrace();
        }*/
    }

    public void textMembresia(MouseEvent mouseEvent) {
        //TODO: Cargar pantalla Membresia
        /*try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI00X_MEMBRESIA, this.sesion, false);
        } catch (IOException e){
            e.printStackTrace();
        }*/
    }

    public void clickServicioCliente(MouseEvent mouseEvent) {
        //TODO: Cargar pantalla Membresia
        /*try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI00X_SERVICIOCL, this.sesion, false);
        } catch (IOException e){
            e.printStackTrace();
        }*/
    }

    public void clickBusquedasRecientes(MouseEvent mouseEvent) {
        //TODO: Cargar pantalla Membresia
        /*try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI00X_SERVICIOCL, this.sesion, false);
        } catch (IOException e){
            e.printStackTrace();
        }*/
    }

    public void clickVolver(MouseEvent mouseEvent) {
        try{
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI003_INICIO, this.sesion, false);
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public void clickR1(MouseEvent mouseEvent) {
        String nombreReceta = textNombreR1.getText();
        this.sesion.setIdReceta(enviarReceta(nombreReceta));

        try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI007_RECETAING, this.sesion, false);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void clickR2(MouseEvent mouseEvent) {
        String nombreReceta = String.valueOf(textNombreR2);
        this.sesion.setIdReceta(enviarReceta(nombreReceta));

        try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI006_VERRECETA, this.sesion, false);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void clickR3(MouseEvent mouseEvent) {
        String nombreReceta = String.valueOf(textNombreR3);
        this.sesion.setIdReceta(enviarReceta(nombreReceta));

        try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI006_VERRECETA, this.sesion, false);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void clickR4(MouseEvent mouseEvent) {
        String nombreReceta = String.valueOf(textNombreR4);
        this.sesion.setIdReceta(enviarReceta(nombreReceta));

        try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI006_VERRECETA, this.sesion, false);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void clickR5(MouseEvent mouseEvent) {
        String nombreReceta = String.valueOf(textNombreR5);
        this.sesion.setIdReceta(enviarReceta(nombreReceta));

        try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI006_VERRECETA, this.sesion, false);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void clickR6(MouseEvent mouseEvent) {
        String nombreReceta = String.valueOf(textNombreR6);
        this.sesion.setIdReceta(enviarReceta(nombreReceta));

        try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI006_VERRECETA, this.sesion, false);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void clickAntRecetas(MouseEvent mouseEvent) {

        desactivarMiniaturasFX();
        if(this.miniaturas.size() - contadorRecetasPantalla < 6){
            btnSigRetas.setVisible(true);
        }

        int indice = contadorRecetasPantalla - contadorObservadas - 6;
        int indiceGu = indice;

        if(indice == 0){
            btnAntRecetas.setVisible(false);
        }

        if(contadorRecetasPantalla==0){
            btnAntRecetas.setVisible(false);
        }

        for(int i=0; i<miniaturasFX.length; i++){
            for(int j=0; j<miniaturasFX[0].length; j++){
                if(this.miniaturas.size() > indice){
                    DTORecetaMiniatura infoReceta = this.miniaturas.get(indice);
                    Image image = new Image("https://i.pinimg.com/originals/1f/04/85/1f048500d47614a086ee689bcf024233.jpg");
                    this.miniaturasFX[i][j].getNombre().setText(infoReceta.getNombreReceta());
                    this.miniaturasFX[i][j].getImagen().setImage(image);
                    indice++;
                }
            }
        }


        contadorRecetasPantalla = contadorRecetasPantalla - contadorObservadas;
        contadorObservadas = indiceGu;

    }

    public void clickSigRetas(MouseEvent mouseEvent) {

        desactivarMiniaturasFX();
        if(this.miniaturas.size() - contadorRecetasPantalla < 6){
            btnSigRetas.setVisible(false);
        }

        contadorObservadas = 0;

        btnAntRecetas.setVisible(true);

        for(int i=0; i<miniaturasFX.length; i++){
            for(int j=0; j<miniaturasFX[0].length; j++){
                if(this.miniaturas.size() > contadorRecetasPantalla){
                    DTORecetaMiniatura infoReceta = this.miniaturas.get(this.contadorRecetasPantalla);
                    Image image = new Image("https://i.pinimg.com/originals/1f/04/85/1f048500d47614a086ee689bcf024233.jpg");
                    this.miniaturasFX[i][j].getNombre().setText(infoReceta.getNombreReceta());
                    this.miniaturasFX[i][j].getImagen().setImage(image);
                    this.contadorRecetasPantalla++;
                    this.contadorObservadas++;
                }
            }
        }

    }

    public void clickBuscar(MouseEvent mouseEvent) {

        String busqueda = fieldBuscar.getText();
        this.contadorObservadas = 0;
        this.contadorRecetasPantalla = 0;

        this.miniaturas = controladorRecCooker.buscarReceta(busqueda);

        if(this.miniaturas.size() > 6){
            btnSigRetas.setVisible(true);
        }

        if(this.miniaturas.size() != 0) {

            for (int i = 0; i < this.miniaturasFX.length; i++) {
                for (int j = 0; j < this.miniaturasFX[0].length; j++) {
                    if (this.miniaturas.size() != contadorRecetasPantalla) {
                        DTORecetaMiniatura infoReceta = this.miniaturas.get(this.contadorRecetasPantalla);
                        Image image = new Image("https://i.pinimg.com/originals/1f/04/85/1f048500d47614a086ee689bcf024233.jpg");
                        this.miniaturasFX[i][j].getNombre().setText(infoReceta.getNombreReceta());
                        this.miniaturasFX[i][j].getImagen().setImage(image);
                        this.contadorRecetasPantalla++;
                    }
                }
            }
        } else {
            this.crearAlerta(Alert.AlertType.INFORMATION, "¡No hay coincidencias con la busqueda!");
        }
    }
}
