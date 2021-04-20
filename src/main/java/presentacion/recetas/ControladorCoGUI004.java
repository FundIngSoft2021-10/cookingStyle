package presentacion.recetas;

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
    private IControladorRecetasCooker controlRecetas;

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
    public ImageView btnSigRecetas;
    @FXML
    public Text textNombreR6;
    @FXML
    public TextField fieldBuscar;
    @FXML
    public ImageView btnBuscar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnAntRecetas.setVisible(false);
        btnSigRecetas.setVisible(false);

        this.crearMiniaturasFX();
        this.desactivarMiniaturasFX();

        this.btnBusquedasRecientes.setVisible(false);       // TODO: Botón búsquedas recientes
    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;

        this.controlRecetas = new ControladorRecetasCooker((Cooker) sesion.getUsuario());
        this.contadorRecetasPantalla = 0;
        this.contadorObservadas = 0;

        this.miniaturas = new ArrayList<>();
        textNombreUsuario.setText(sesion.getUsuario().getNombreUsuario());

        // Cargar la búsqueda realizada
        this.fieldBuscar.setText(this.sesion.getBusqueda());
        this.buscar(this.sesion.getBusqueda());
    }

    private void llenarMiniaturasFX(int i, int j, ImageView imagen, Text nombre) {
        miniaturasFX[i][j] = new FXRecetaMiniatura();
        miniaturasFX[i][j].setImagen(imagen);
        miniaturasFX[i][j].setNombre(nombre);
    }

    private void crearMiniaturasFX() {
        this.miniaturasFX = new FXRecetaMiniatura[2][3];

        this.llenarMiniaturasFX(0, 0, imgR1, textNombreR1);
        this.llenarMiniaturasFX(0, 1, imgR2, textNombreR2);
        this.llenarMiniaturasFX(0, 2, imgR3, textNombreR3);
        this.llenarMiniaturasFX(1, 0, imgR4, textNombreR4);
        this.llenarMiniaturasFX(1, 1, imgR5, textNombreR5);
        this.llenarMiniaturasFX(1, 2, imgR6, textNombreR6);
    }

    private void desactivarMiniaturasFX() {
        for (int i = 0; i < miniaturasFX.length; i++) {
            for (int j = 0; j < miniaturasFX[0].length; j++) {
                miniaturasFX[i][j].getImagen().setImage(null);
                miniaturasFX[i][j].getNombre().setText("");
            }
        }
    }

    private void cargarMiniatura(FXRecetaMiniatura miniaturaFX, DTORecetaMiniatura miniatura) {
        Image imagen;
        try {
            imagen = new Image(miniatura.getLinkImagen());
        } catch (Exception e) {
            imagen = new Image("https://img.icons8.com/pastel-glyph/2x/file-not-found.png");
        }

        miniaturaFX.getImagen().setImage(imagen);
        miniaturaFX.getImagen().setVisible(true);
        miniaturaFX.getNombre().setText(miniatura.getNombreReceta());
    }

    private BigInteger enviarReceta(String nombreReceta) {
        BigInteger idReceta = null;

        for (DTORecetaMiniatura miniatura : this.miniaturas) {
            if (miniatura.getNombreReceta().equals(nombreReceta)) {
                idReceta = miniatura.getIdReceta();
            }
        }
        return idReceta;
    }

    private void buscar(String busqueda) {
        this.contadorObservadas = 0;
        this.contadorRecetasPantalla = 0;
        this.miniaturas = controlRecetas.buscarReceta(busqueda);

        if (this.miniaturas.size() > 6) {
            btnSigRecetas.setVisible(true);
        }

        if (this.miniaturas.size() != 0) {
            for (int i = 0; i < this.miniaturasFX.length; i++) {
                for (int j = 0; j < this.miniaturasFX[0].length; j++) {
                    if (this.miniaturas.size() != contadorRecetasPantalla) {
                        DTORecetaMiniatura miniatura = this.miniaturas.get(this.contadorRecetasPantalla);
                        cargarMiniatura(this.miniaturasFX[i][j], miniatura);

                        this.contadorRecetasPantalla++;
                    }
                }
            }
        } else {
            this.crearAlerta(Alert.AlertType.INFORMATION, "¡No hay coincidencias con la busqueda!");
        }
    }

    @FXML
    public void clickBuscar(MouseEvent mouseEvent) {
        String busqueda = fieldBuscar.getText();

        if (!busqueda.isEmpty())
            this.buscar(busqueda);
    }

    @FXML
    public void clickPerfil(MouseEvent mouseEvent) {
        //TODO: Cargar pantalla PerfilCooker
        /*try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI00X_PERFIL, this.sesion, false);
        } catch (IOException e){
            e.printStackTrace();
        }*/
    }

    @FXML
    public void clickMembresia(MouseEvent mouseEvent) {
        //TODO: Cargar pantalla Membresia
        /*try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI00X_MEMBRESIA, this.sesion, false);
        } catch (IOException e){
            e.printStackTrace();
        }*/
    }

    @FXML
    public void clickServicioCliente(MouseEvent mouseEvent) {
        //TODO: Cargar pantalla Servicio al cliente
        /*try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI00X_SERVICIOCL, this.sesion, false);
        } catch (IOException e){
            e.printStackTrace();
        }*/
    }

    @FXML
    public void clickBusquedasRecientes(MouseEvent mouseEvent) {
        //TODO: Cargar pantalla Busquedas recientes
        /*try {
            this.cargarPantalla((Event) mouseEvent, Pantalla.CO_GUI00X_SERVICIOCL, this.sesion, false);
        } catch (IOException e){
            e.printStackTrace();
        }*/
    }

    @FXML
    public void clickVolver(MouseEvent mouseEvent) {
        try {
            this.volverPantalla((Event) mouseEvent, this.sesion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void irReceta(Event event, BigInteger idReceta) {
        this.sesion.setIdRecetaCargada(idReceta);

        try {
            this.cargarPantalla(event, Pantalla.CO_GUI006_VERRECETA, this.sesion, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clickR1(MouseEvent mouseEvent) {
        String nombreReceta = miniaturasFX[0][0].getNombre().getText();
        this.irReceta((Event) mouseEvent, this.enviarReceta(nombreReceta));
    }

    @FXML
    public void clickR2(MouseEvent mouseEvent) {
        String nombreReceta = miniaturasFX[0][1].getNombre().getText();
        this.irReceta((Event) mouseEvent, this.enviarReceta(nombreReceta));
    }

    @FXML
    public void clickR3(MouseEvent mouseEvent) {
        String nombreReceta = miniaturasFX[0][2].getNombre().getText();
        this.irReceta((Event) mouseEvent, this.enviarReceta(nombreReceta));
    }

    @FXML
    public void clickR4(MouseEvent mouseEvent) {
        String nombreReceta = miniaturasFX[1][0].getNombre().getText();
        this.irReceta((Event) mouseEvent, this.enviarReceta(nombreReceta));
    }

    @FXML
    public void clickR5(MouseEvent mouseEvent) {
        String nombreReceta = miniaturasFX[1][1].getNombre().getText();
        this.irReceta((Event) mouseEvent, this.enviarReceta(nombreReceta));
    }

    @FXML
    public void clickR6(MouseEvent mouseEvent) {
        String nombreReceta = miniaturasFX[1][2].getNombre().getText();
        this.irReceta((Event) mouseEvent, this.enviarReceta(nombreReceta));
    }

    @FXML
    public void clickAntRecetas(MouseEvent mouseEvent) {
        this.desactivarMiniaturasFX();

        if (this.miniaturas.size() - contadorRecetasPantalla < 6) {
            btnSigRecetas.setVisible(true);
        }

        int indice = contadorRecetasPantalla - contadorObservadas - 6;
        int indiceGu = indice;

        if (indice == 0) {
            btnAntRecetas.setVisible(false);
        }

        if (contadorRecetasPantalla == 0) {
            btnAntRecetas.setVisible(false);
        }

        for (int i = 0; i < miniaturasFX.length; i++) {
            for (int j = 0; j < miniaturasFX[0].length; j++) {
                if (this.miniaturas.size() > indice) {
                    DTORecetaMiniatura miniatura = this.miniaturas.get(indice);
                    cargarMiniatura(this.miniaturasFX[i][j], miniatura);

                    indice++;
                }
            }
        }


        contadorRecetasPantalla = contadorRecetasPantalla - contadorObservadas;
        contadorObservadas = indiceGu;

    }

    @FXML
    public void clickSigRecetas(MouseEvent mouseEvent) {
        this.desactivarMiniaturasFX();

        if (this.miniaturas.size() - contadorRecetasPantalla < 6) {
            btnSigRecetas.setVisible(false);
        }

        contadorObservadas = 0;

        btnAntRecetas.setVisible(true);

        for (int i = 0; i < miniaturasFX.length; i++) {
            for (int j = 0; j < miniaturasFX[0].length; j++) {
                if (this.miniaturas.size() > contadorRecetasPantalla) {
                    DTORecetaMiniatura miniatura = this.miniaturas.get(this.contadorRecetasPantalla);
                    cargarMiniatura(this.miniaturasFX[i][j], miniatura);

                    this.contadorRecetasPantalla++;
                    this.contadorObservadas++;
                }
            }
        }

    }
}
