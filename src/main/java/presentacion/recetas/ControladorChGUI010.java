package presentacion.recetas;

import entidades.dto.DTOReceta;
import entidades.dto.DTOSesion;
import entidades.modelo.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logica_negocio.recetas.ControladorRecetasChef;
import logica_negocio.recetas.IControladorRecetasChef;
import presentacion.IControladorPantalla;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorChGUI010 implements IControladorPantalla {
    private DTOSesion sesion;
    private IControladorRecetasChef controlRecetas;
    private int cantIngredientes = 0;
    private int cantPasos = 0;

    @FXML
    public Text textServicioCliente;
    @FXML
    public TextField fieldLinkImagen;
    @FXML
    public TextField fieldNombreReceta;
    @FXML
    public TextArea textAreaDescripcion;
    @FXML
    public TextField fieldLinkVideo;
    @FXML
    public ComboBox comboCategorias;
    @FXML
    public ComboBox comboPlataforma;
    @FXML
    public VBox vboxLineaIngrediente;
    @FXML
    public ImageView btnMasIngredientes;
    @FXML
    public ImageView btnMenosIngredientes;
    @FXML
    public VBox vboxPasos;
    @FXML
    public Button btnEditarReceta;
    @FXML
    public Text btnVolver;
    @FXML
    public ImageView btnMasPasos;
    @FXML
    public ImageView btnMenosPasos;
    @FXML
    public ImageView imgFotoPerfil;
    @FXML
    public Text textNombreUsuario;
    @FXML
    public ImageView btnServicioCliente;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.textAreaDescripcion.setWrapText(true);

        this.comboPlataforma.getItems().setAll(TipoVideo.values());
    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
        this.controlRecetas = new ControladorRecetasChef((Chef) this.sesion.getUsuario(), this.sesion.getConexion());

        // Cargar el perfil
        this.textNombreUsuario.setText(this.sesion.getUsuario().getNombreUsuario());

        // Cargar el combobox de categorías
        this.comboCategorias.getItems().setAll(this.controlRecetas.consultarCategorias());

        // Cargar la información de la receta
        this.sesion.setRecetaCargada(this.controlRecetas.buscarReceta(this.sesion.getIdRecetaCargada()));
        this.cargarReceta();
    }

    private void crearLineaIngrediente() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/presentacion/recetas/ChGUI009_LineaIngrediente.fxml"));
        AnchorPane lineaIngrediente = null;
        try {
            lineaIngrediente = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (lineaIngrediente != null) {
            ObservableList<Node> nodos = lineaIngrediente.getChildren();
            ComboBox comboMedida = (ComboBox) nodos.get(2);
            comboMedida.getItems().setAll(Medida.values());

            this.vboxLineaIngrediente.getChildren().add(lineaIngrediente);
        }
    }

    private void crearPaso() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/presentacion/recetas/ChGUI009_Paso.fxml"));
        AnchorPane paso = null;

        try {
            paso = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (paso != null) {
            this.vboxPasos.getChildren().add(paso);
        }
    }

    private void eliminarLineaIngrediente(int pos) {
        if (pos >= 0)
            this.vboxLineaIngrediente.getChildren().remove(pos);
    }

    private void eliminarPaso(int pos) {
        if (pos >= 0)
            this.vboxPasos.getChildren().remove(pos);
    }

    private void cargarReceta() {
        this.fieldNombreReceta.setText(this.sesion.getRecetaCargada().getReceta().getNombre());
        this.fieldLinkImagen.setText(this.sesion.getRecetaCargada().getReceta().getLinkImagen());
        this.fieldLinkVideo.setText(this.sesion.getRecetaCargada().getReceta().getLinkVideo());
        this.textAreaDescripcion.setText(this.sesion.getRecetaCargada().getReceta().getDescripcion());

        Categoria categoria = null;
        if (this.sesion.getRecetaCargada().getReceta().getCategorias().size() > 0)
            categoria = this.sesion.getRecetaCargada().getReceta().getCategorias().get(0);
        if (categoria != null)
            this.comboCategorias.setValue(categoria);

        TipoVideo tipoVideo = this.sesion.getRecetaCargada().getReceta().getTipoVideo();
        if (tipoVideo != null)
            this.comboPlataforma.setValue(tipoVideo);

        // Ingredientes
        for (LineaIngrediente lineaIngrediente : this.sesion.getRecetaCargada().getReceta().getLineasIngrediente()) {
            this.crearLineaIngrediente();

            AnchorPane paneLinea = (AnchorPane) this.vboxLineaIngrediente.getChildren().get(this.cantIngredientes);
            ObservableList<Node> datosLinea = paneLinea.getChildren();
            ((TextField) datosLinea.get(0)).setText(lineaIngrediente.getIngrediente().getNombre());
            ((TextField) datosLinea.get(1)).setText(String.valueOf(lineaIngrediente.getCantidad()));
            ((ComboBox) datosLinea.get(2)).setValue(lineaIngrediente.getMedida());

            this.cantIngredientes++;
        }

        // Pasos
        for (PasoReceta paso : this.sesion.getRecetaCargada().getReceta().getPasosReceta()) {
            this.crearPaso();

            AnchorPane panePaso = (AnchorPane) this.vboxPasos.getChildren().get(this.cantPasos);
            ObservableList<Node> datosPaso = panePaso.getChildren();
            ((TextField) datosPaso.get(0)).setText(paso.getTexto());
            if (paso.isTieneImagen())
                ((TextField) datosPaso.get(1)).setText(String.valueOf(paso.getLinkImagen()));

            this.cantPasos++;
        }
    }

    @FXML
    public void clickPerfil(MouseEvent mouseEvent) {
        // TODO: Perfil del chef
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
    public void clickVolver(MouseEvent mouseEvent) {
        try {
            this.volverPantalla((Event) mouseEvent, this.sesion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clickMasIngredientes(MouseEvent mouseEvent) {
        if (this.cantIngredientes == 0)
            this.btnMenosIngredientes.setVisible(true);
        this.cantIngredientes++;

        this.crearLineaIngrediente();
    }

    @FXML
    public void clickMenosIngredientes(MouseEvent mouseEvent) {
        this.cantIngredientes--;
        if (this.cantIngredientes == 0)
            this.btnMenosIngredientes.setVisible(false);

        this.eliminarLineaIngrediente(this.cantIngredientes);
    }

    @FXML
    public void clickMasPasos(MouseEvent mouseEvent) {
        if (this.cantPasos == 0)
            this.btnMenosPasos.setVisible(true);
        this.cantPasos++;

        this.crearPaso();
    }

    @FXML
    public void clickMenosPasos(MouseEvent mouseEvent) {
        this.cantPasos--;
        if (this.cantPasos == 0)
            this.btnMenosPasos.setVisible(false);

        this.eliminarPaso(this.cantPasos);
    }

    @FXML
    public void clickEditarReceta(ActionEvent actionEvent) {
        String nombreReceta = this.fieldNombreReceta.getText();
        String descripcion = this.textAreaDescripcion.getText();
        String linkVideo = this.fieldLinkVideo.getText();
        String linkImagen = this.fieldLinkImagen.getText();
        DTOReceta recetaMod = new DTOReceta();

        if (!this.sesion.getRecetaCargada().getReceta().getNombre().equals(nombreReceta)) {
            recetaMod = this.controlRecetas.modificarNombre(nombreReceta, this.sesion.getRecetaCargada().getReceta().getIdReceta());
        }

        if (!this.sesion.getRecetaCargada().getReceta().getDescripcion().equals(descripcion)) {
            recetaMod = this.controlRecetas.modificarDecripcion(descripcion, this.sesion.getRecetaCargada().getReceta().getIdReceta());
        }

        if (!this.sesion.getRecetaCargada().getReceta().getLinkImagen().equals(linkImagen)) {
            recetaMod = this.controlRecetas.modificarLinkImg(linkImagen, this.sesion.getRecetaCargada().getReceta().getIdReceta());
        }

        if (!this.sesion.getRecetaCargada().getReceta().getLinkVideo().equals(nombreReceta)) {
            recetaMod = this.controlRecetas.modificarLinkVideo(linkVideo, this.sesion.getRecetaCargada().getReceta().getIdReceta());
        }

        if (recetaMod.isEncontrado()) {
            this.crearAlerta(Alert.AlertType.INFORMATION, "La receta ha sido modificada");
            try {
                this.volverPantalla((Event) actionEvent, this.sesion);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.crearAlerta(Alert.AlertType.ERROR, "No se pudo modificar la receta");
        }
    }
}
