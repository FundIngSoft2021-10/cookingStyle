package presentacion.administracion;

import entidades.dto.DTOExito;
import entidades.dto.DTOReceta;
import entidades.dto.DTOSesion;
import entidades.dto.Pantalla;
import entidades.modelo.Receta;
import entidades.modelo.Reporte;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import logica_negocio.recetas.ControladorAdministrador;
import logica_negocio.recetas.ControladorRecetasCooker;
import logica_negocio.recetas.IControladorAdministrador;
import logica_negocio.recetas.IControladorRecetasCooker;
import presentacion.IControladorPantalla;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControladorAdGUI003 implements IControladorPantalla {

    private DTOSesion sesion;
    private IControladorAdministrador controlAdmin;
    private IControladorRecetasCooker controlRecetas;
    private DTOReceta receta;
    private List<Reporte> reportes;
    private int contaReportes;
    private int totalReportes;

    @FXML
    public TextArea textDetalleReporte;
    @FXML
    public Button btnAceptar;
    @FXML
    public Button btnRechazar;
    @FXML
    public Text btnVolver;
    @FXML
    public ImageView imgReceta;
    @FXML
    public Text textNomReceta;
    @FXML
    public Text textNomChef;
    @FXML
    public ImageView btnFavoritos;
    @FXML
    public ImageView btnReportar;
    @FXML
    public Button btnVeReceta;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
        this.contaReportes = 0;
        this.reportes = new ArrayList<>();
        this.controlAdmin = new ControladorAdministrador();
        this.controlRecetas = new ControladorRecetasCooker(); //Posible error

        //Lista de reportes
        this.cargarReportes();

        //Cargar el primer reporte
        this.cargarReporte();

        //Cargar receta
        this.cargarReceta();

        this.totalReportes = this.reportes.size();

    }

    private void cargarReportes(){
        this.reportes.addAll(this.controlAdmin.revisarReportes());
    }

    private void cargarReporte(){
        Reporte reporte = this.reportes.get(this.contaReportes);
        this.receta = this.controlRecetas.buscarReceta(reporte.getIdReceta());
        String detalle = "Usuario: " + reporte.getUsuario().getNombre() + '\n' +
                         "Fecha reporte: " + reporte.getFecha() + '\n' +
                         "Receta reportada: " + receta.getReceta().getNombre() + '\n' +
                         "Chef: " + receta.getAutor().getNombre() + '\n';
        this.textDetalleReporte.setText(detalle);
    }

    private void cargarReceta(){

        //Autor de la receta
        this.textNomChef.setText(this.receta.getAutor().getNombre());

        //Nombre receta
        this.textNomReceta.setText(this.receta.getReceta().getNombre());

        //Imagen receta
        Image imagen = new Image(this.receta.getReceta().getLinkImagen());
        this.imgReceta.setImage(imagen);

        //Ocultar
        this.btnFavoritos.setVisible(false);
        this.btnReportar.setVisible(false);

    }

    public void clickAceptar(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar Receta");
        alert.setHeaderText(null);
        alert.setContentText("¿Seguro quiere eliminar la receta?");
        ButtonType buttonTypeAceptar = new ButtonType("Aceptar");
        ButtonType buttonTypeCancel = new ButtonType("Cancelar");
        alert.getButtonTypes().setAll(buttonTypeAceptar, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeAceptar){
            DTOReceta recetaEliminada = this.controlAdmin.eliminarVideoInapropiado(this.receta.getReceta().getIdReceta());
            if(recetaEliminada != null){
                this.crearAlerta(Alert.AlertType.INFORMATION, "Receta eliminada con éxito");
                this.controlAdmin.eliminarReporte(this.reportes.get(this.contaReportes).getIdReporte());
                this.contaReportes++;
                if (this.contaReportes >= this.totalReportes){
                    try {
                        this.cargarPantalla((Event) mouseEvent, Pantalla.AD_GUI002_INICIO, this.sesion, false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    this.cargarReporte();
                    this.cargarReceta();
                }
            } else{
                this.crearAlerta(Alert.AlertType.ERROR, recetaEliminada.getMensaje());
            }

        } else if (result.get() == buttonTypeCancel){
            return;
        }
    }

    public void clickRechazar(MouseEvent mouseEvent) {

        this.controlAdmin.eliminarReporte(this.reportes.get(this.contaReportes).getIdReporte());
        this.contaReportes++;
        if (this.contaReportes >= this.totalReportes){
            try {
                this.cargarPantalla((Event) mouseEvent, Pantalla.AD_GUI002_INICIO, this.sesion, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.cargarReporte();
            this.cargarReceta();
        }
    }

    public void clickVolver(MouseEvent mouseEvent) {
        try {
            this.volverPantalla((Event) mouseEvent, this.sesion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickVerReceta(MouseEvent mouseEvent) {
        try {
            this.sesion.setIdRecetaCargada(this.receta.getReceta().getIdReceta());
            this.cargarPantalla((Event) mouseEvent, Pantalla.AD_GUI004_VERRECETA, this.sesion, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
