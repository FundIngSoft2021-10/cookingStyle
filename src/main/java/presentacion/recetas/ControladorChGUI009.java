package presentacion.recetas;

import entidades.dto.DTOExito;
import entidades.dto.DTOReceta;
import entidades.dto.Pantalla;
import entidades.modelo.*;
import entidades.dto.DTOSesion;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorChGUI009 implements IControladorPantalla {
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
    public Button btnEnviarReceta;
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

        this.crearLineaIngrediente();
        this.crearPaso();
    }
    
    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
        this.controlRecetas = new ControladorRecetasChef((Chef) this.sesion.getUsuario());

        this.comboCategorias.getItems().setAll(this.controlRecetas.consultarCategorias());

        this.textNombreUsuario.setText(this.sesion.getUsuario().getNombreUsuario());

        this.cantIngredientes = 1;
        this.cantPasos = 1;
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
    public void clickEnviarReceta(ActionEvent actionEvent) {
        String nombreReceta = this.fieldNombreReceta.getText();
        String descripcion = this.textAreaDescripcion.getText();
        String linkImagen = this.fieldLinkImagen.getText();
        String linkVideo = this.fieldLinkVideo.getText();
        TipoVideo tipoVideo = (TipoVideo) this.comboPlataforma.getValue();

        List<LineaIngrediente> lineasIngredientes = new ArrayList<>();
        List<Categoria> categorias = new ArrayList<>();
        List<PasoReceta> pasos = new ArrayList<>();

        if (nombreReceta.isEmpty() || descripcion.isEmpty() || linkImagen.isEmpty()
                || linkVideo.isEmpty() || tipoVideo == null || this.cantIngredientes == 0
                || this.cantPasos == 0) {
            this.crearAlerta(Alert.AlertType.ERROR, "Todos los campos son obligatorios");
            return;
        }

        if (this.comboCategorias.getValue() != null)
            categorias.add((Categoria) this.comboCategorias.getValue());

        for (int i = 0; i < cantIngredientes; i++) {
            AnchorPane linea = (AnchorPane) this.vboxLineaIngrediente.getChildren().get(i);
            ObservableList<Node> datosLinea = linea.getChildren();

            String nombreIngrediente = ((TextField) datosLinea.get(0)).getText();
            if (nombreIngrediente.isEmpty()) {
                this.crearAlerta(Alert.AlertType.ERROR, "Error en el nombre del ingrediente " +
                        String.valueOf(i + 1));
                return;
            }

            float cantidad = 0;
            try {
                cantidad = Float.parseFloat(((TextField) datosLinea.get(1)).getText());
            } catch (Exception e) {
                this.crearAlerta(Alert.AlertType.ERROR, "Error en la cantidad del ingrediente " +
                        String.valueOf(i + 1));
                return;
            }

            if (cantidad < 0) {
                this.crearAlerta(Alert.AlertType.ERROR, "Error en la cantidad del ingrediente " +
                        String.valueOf(i + 1));
                return;
            }

            Medida medida = (Medida) ((ComboBox) datosLinea.get(2)).getValue();
            if (medida == null) {
                this.crearAlerta(Alert.AlertType.ERROR, "Error en la medida del ingrediente " +
                        String.valueOf(i + 1));
                return;
            }

            LineaIngrediente lineaIngrediente = new LineaIngrediente(new Ingrediente(0, nombreIngrediente),
                    cantidad, medida);

            lineasIngredientes.add(lineaIngrediente);
        }

        for (int i = 0; i < cantPasos; i++) {
            AnchorPane paso = (AnchorPane) this.vboxPasos.getChildren().get(i);
            ObservableList<Node> datosPaso = paso.getChildren();

            String texto = ((TextField) datosPaso.get(0)).getText();
            if (texto.isEmpty()) {
                this.crearAlerta(Alert.AlertType.ERROR, "Error en el texto del paso " +
                        String.valueOf(i + 1)
                );
                return;
            }

            String linkImagenPaso = ((TextField) datosPaso.get(1)).getText();
            boolean tieneImagenPaso = !linkImagenPaso.isEmpty();

            PasoReceta pasoReceta = new PasoReceta(i + 1, texto, tieneImagenPaso);
            if (pasoReceta.isTieneImagen())
                pasoReceta.setLinkImagen(linkImagenPaso);

            pasos.add(pasoReceta);
        }

        DTOExito exito = this.controlRecetas.subirReceta(nombreReceta, descripcion, linkVideo, tipoVideo, linkImagen,
                lineasIngredientes, categorias, pasos);
        if (exito.isEstado()) {
            this.crearAlerta(Alert.AlertType.INFORMATION, exito.getMensaje());
            try {
                this.volverPantalla((Event) actionEvent, this.sesion);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.crearAlerta(Alert.AlertType.ERROR, exito.getMensaje());
        }
    }
}
