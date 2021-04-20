package presentacion.recetas;

import entidades.dto.*;
import entidades.modelo.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
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
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorCoGUI003 implements IControladorPantalla {
    private DTOSesion sesion;
    private IControladorRecetasCooker controlRecetas;
    private List<Categoria> categorias;
    private List<DTORecetasMiniaturaCategoria> miniaturasCategorias;

    private int cantFilas;      // Cantidad de filas (categorías) cargadas
    private int cantCols;       // Cantidad de columnas (recetas por categoría) cargadas
    private int posicionRecetas[];      // Posición de las recetas por cada categoría (0) - (1) - (...)
    private int posicionCategorias;     // Posición de las categorías (1,2) - (3,4) - (...)
    private Text[] nombresCategorias;   // Nombres de cada fila de categorías
    private FXRecetaMiniatura[][] miniaturasFX;
    private DTORecetaMiniatura[][] miniaturas;

    @FXML
    public TextField fieldBuscar;
    @FXML
    public ImageView imgFotoUsuario;
    @FXML
    public ImageView btnBuscar;
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
    public ListView listviewCategorias;
    @FXML
    public Text btnVerMasCategorias;
    @FXML
    public ImageView imgC1R1;
    @FXML
    public Text textNombreC1R1;
    @FXML
    public Text textC1;
    @FXML
    public ImageView imgC1R2;
    @FXML
    public Text textNombreC1R2;
    @FXML
    public ImageView imgC2R1;
    @FXML
    public Text textNombreC2R1;
    @FXML
    public Text textC2;
    @FXML
    public ImageView imgC1R3;
    @FXML
    public Text textNombreC1R3;
    @FXML
    public AnchorPane paneReceta5;
    @FXML
    public ImageView imgC2R2;
    @FXML
    public Text textNombreC2R2;
    @FXML
    public ImageView imgC2R3;
    @FXML
    public Text textNombreC2R3;
    @FXML
    public ImageView btnAntC1;
    @FXML
    public ImageView btnSigC1;
    @FXML
    public ImageView btnAntC2;
    @FXML
    public ImageView btnSigC2;
    @FXML
    public ImageView btnAntCategorias;
    @FXML
    public ImageView btnSigCategorias;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Empaquetar el grid de categorias - recetas dentro de una matriz para fácil acceso
        this.crearMiniaturas();

        // Desactivar todas las recetas
        this.desactivarRecetasFX();

        this.btnBusquedasRecientes.setVisible(false);      // TODO: Botón búsquedas recientes
    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
        this.controlRecetas = new ControladorRecetasCooker((Cooker) this.sesion.getUsuario());

        // Cargar el perfil
        this.textNombreUsuario.setText(this.sesion.getUsuario().getNombreUsuario());

        // Obtener la información de la pantalla
        this.categorias = this.controlRecetas.buscarCategorias();
        this.miniaturasCategorias = this.controlRecetas.buscarMiniaturasRecetasCategoria();

        // Cargar las categorías en la pantalla
        this.cargarCategorias();

        // Cargar las miniaturas iniciales
        this.cargarTodasMiniaturas();
        this.determinarBotonesCategorias();
    }

    private void crearMiniaturas() {
        this.cantFilas = 2;
        this.cantCols = 3;

        this.posicionRecetas = new int[cantFilas];
        for (int i = 0; i < this.cantFilas; i++)
            this.posicionRecetas[i] = 0;
        this.posicionCategorias = 0;

        nombresCategorias = new Text[2];
        nombresCategorias[0] = textC1;
        nombresCategorias[1] = textC2;

        miniaturas = new DTORecetaMiniatura[this.cantFilas][this.cantCols];
        miniaturasFX = new FXRecetaMiniatura[this.cantFilas][this.cantCols];
        this.llenarMiniaturasFX(0, 0, imgC1R1, textNombreC1R1);
        this.llenarMiniaturasFX(0, 1, imgC1R2, textNombreC1R2);
        this.llenarMiniaturasFX(0, 2, imgC1R3, textNombreC1R3);
        this.llenarMiniaturasFX(1, 0, imgC2R1, textNombreC2R1);
        this.llenarMiniaturasFX(1, 1, imgC2R2, textNombreC2R2);
        this.llenarMiniaturasFX(1, 2, imgC2R3, textNombreC2R3);
    }

    private void llenarMiniaturasFX(int i, int j, ImageView imagen, Text nombre) {
        miniaturasFX[i][j] = new FXRecetaMiniatura();
        miniaturasFX[i][j].setImagen(imagen);
        miniaturasFX[i][j].setNombre(nombre);
    }

    private void desactivarMiniaturasCategoria(int filaCategoria) {
        nombresCategorias[filaCategoria].setText("");
        for (int j = 0; j < this.cantCols; j++) {
            miniaturasFX[filaCategoria][j].getImagen().setImage(null);
            miniaturasFX[filaCategoria][j].getImagen().setVisible(false);
            miniaturasFX[filaCategoria][j].getNombre().setText("");
            miniaturas[filaCategoria][j] = null;
        }
    }

    private void desactivarMiniaturas() {
        for (int i = 0; i < this.cantFilas; i++) {
            this.desactivarMiniaturasCategoria(i);
        }
    }

    private void desactivarBotonesNavegacion() {
        btnAntC1.setVisible(false);
        btnSigC1.setVisible(false);
        btnAntC2.setVisible(false);
        btnSigC2.setVisible(false);
        btnAntCategorias.setVisible(false);
        btnSigCategorias.setVisible(false);
    }

    private void desactivarRecetasFX() {
        this.desactivarBotonesNavegacion();
        this.desactivarMiniaturas();
    }

    private void cargarMiniatura(int i, int j, DTORecetaMiniatura miniatura) {
        miniaturas[i][j] = miniatura;

        Image imagen;
        try {
            System.out.println(miniatura.getLinkImagen());
            imagen = new Image(miniatura.getLinkImagen());
        } catch (Exception e) {
            imagen = new Image("https://img.icons8.com/pastel-glyph/2x/file-not-found.png");
        }

        this.miniaturasFX[i][j].getImagen().setImage(imagen);
        this.miniaturasFX[i][j].getImagen().setVisible(true);
        this.miniaturasFX[i][j].getNombre().setText(miniatura.getNombreReceta());
    }

    private void cargarMiniaturasCategoria(int filaCategoria) {
        this.desactivarMiniaturasCategoria(filaCategoria);

        // TODO: Botones de tamaño variable, no fijos
        if (filaCategoria == 0) {
            btnAntC1.setVisible(false);
            btnSigC1.setVisible(false);
        } else if (filaCategoria == 1) {
            btnAntC2.setVisible(false);
            btnSigC2.setVisible(false);
        }

        // Determinar si la categoría - miniatura existe
        if (this.posicionCategorias * this.cantFilas + filaCategoria < this.miniaturasCategorias.size()) {
            DTORecetasMiniaturaCategoria miniaturaCategoria =
                    this.miniaturasCategorias.get(this.posicionCategorias * this.cantFilas + filaCategoria);
            this.nombresCategorias[filaCategoria].setText(miniaturaCategoria.getCategoria().getNombre());

            for (int j = this.posicionRecetas[filaCategoria]; j < this.cantCols; j++) {
                if (j < miniaturaCategoria.getRecetasCategoria().size()) {
                    this.cargarMiniatura(filaCategoria, j, miniaturaCategoria.getRecetasCategoria().get(j));
                }
            }

            // Determinar los botones de navegación de la categoría
            // TODO: Botones de tamaño variable, no fijos
            if (filaCategoria == 0) {
                if (this.posicionRecetas[filaCategoria] * this.cantCols > 0) {
                    this.btnAntC1.setVisible(true);
                }

                if (miniaturaCategoria.getRecetasCategoria().size()
                        - this.posicionRecetas[filaCategoria] * this.cantCols > this.cantCols) {
                    this.btnSigC1.setVisible(true);
                }
            } else if (filaCategoria == 1) {
                if (this.posicionRecetas[filaCategoria] * this.cantCols > 0) {
                    this.btnAntC2.setVisible(true);
                }

                if (miniaturaCategoria.getRecetasCategoria().size()
                        - this.posicionRecetas[filaCategoria] * this.cantCols > this.cantCols) {
                    this.btnSigC2.setVisible(true);
                }
            }
        }
    }

    private void cargarTodasMiniaturas() {
        for (int i = 0; i < this.cantFilas; i++) {
            this.cargarMiniaturasCategoria(i);
        }
    }

    private void determinarBotonesCategorias() {
        if (this.posicionCategorias > 0) {
            this.btnAntCategorias.setVisible(true);
        } else {
            this.btnAntCategorias.setVisible(false);
        }

        if (this.miniaturasCategorias.size() - this.posicionCategorias * this.cantFilas > this.cantFilas) {
            this.btnSigCategorias.setVisible(true);
        } else {
            this.btnSigCategorias.setVisible(false);
        }
    }

    private void cargarCategorias() {
        // TODO: Cargar categorías en la pantalla
    }

    public void clickPerfil(MouseEvent mouseEvent) {
    }

    @FXML
    public void clickBuscar(MouseEvent mouseEvent) {
        String busqueda = fieldBuscar.getText();

        if (!busqueda.isEmpty()) {
            this.sesion.setBusqueda(busqueda);

            try {
                this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI004_RESULTADOSBUSQ, this.sesion, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void clickMembresia(MouseEvent mouseEvent) {
    }

    public void clickServicioCliente(MouseEvent mouseEvent) {
    }

    public void clickBusquedasRecientes(MouseEvent mouseEvent) {
    }

    public void clickVerMasCategorias(MouseEvent mouseEvent) {
    }

    public void irReceta(Event event, DTORecetaMiniatura miniatura) {
        this.sesion.setIdRecetaCargada(miniatura.getIdReceta());
        try {
            this.cargarPantalla((Event) event, Pantalla.CO_GUI006_VERRECETA, this.sesion, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clickC1R1(MouseEvent mouseEvent) {
        if (this.miniaturas[0][0] != null)
            this.irReceta((Event) mouseEvent, this.miniaturas[0][0]);
    }

    @FXML
    public void clickC1R2(MouseEvent mouseEvent) {
        if (this.miniaturas[0][1] != null)
            this.irReceta((Event) mouseEvent, this.miniaturas[0][1]);
    }

    @FXML
    public void clickC2R1(MouseEvent mouseEvent) {
        if (this.miniaturas[0][2] != null)
            this.irReceta((Event) mouseEvent, this.miniaturas[0][2]);
    }

    @FXML
    public void clickC1R3(MouseEvent mouseEvent) {
        if (this.miniaturas[1][0] != null)
            this.irReceta((Event) mouseEvent, this.miniaturas[1][0]);
    }

    @FXML
    public void clickC2R2(MouseEvent mouseEvent) {
        if (this.miniaturas[1][1] != null)
            this.irReceta((Event) mouseEvent, this.miniaturas[1][1]);
    }

    @FXML
    public void clickC2R3(MouseEvent mouseEvent) {
        if (this.miniaturas[1][2] != null)
            this.irReceta((Event) mouseEvent, this.miniaturas[1][2]);
    }

    @FXML
    public void clickAntC1(MouseEvent mouseEvent) {
        this.posicionRecetas[0] -= 1;
        this.cargarMiniaturasCategoria(0);
    }

    @FXML
    public void clickAntC2(MouseEvent mouseEvent) {
        this.posicionRecetas[1] -= 1;
        this.cargarMiniaturasCategoria(1);
    }

    @FXML
    public void clickSigC1(MouseEvent mouseEvent) {
        this.posicionRecetas[0] += 1;
        this.cargarMiniaturasCategoria(0);
    }

    @FXML
    public void clickSigC2(MouseEvent mouseEvent) {
        this.posicionRecetas[1] += 1;
        this.cargarMiniaturasCategoria(1);
    }

    @FXML
    public void clickAntCategorias(MouseEvent mouseEvent) {
        this.posicionCategorias -= 1;
        this.determinarBotonesCategorias();
        this.cargarTodasMiniaturas();
    }

    @FXML
    public void clickSigCategorias(MouseEvent mouseEvent) {
        this.posicionCategorias += 1;
        this.determinarBotonesCategorias();
        this.cargarTodasMiniaturas();
    }
}

