package presentacion;

import entidades.dto.DTOSesion;
import entidades.dto.Pantalla;
import javafx.animation.*;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public interface IControladorPantalla extends Initializable {
    /**
     * Inicializa y desempaqueta los datos de la sesión en el controlador
     * @param sesion los datos de la sesión
     */
    public void inicializar(DTOSesion sesion);

    /**
     * Cambia la escena actual cargada, por otra pantalla fxml proporcionada
     * @param event el evento de la acción que proporcionó el llamado a cambio de pantalla
     * @param pantalla el enum {@link Pantalla} correspondiente a la nueva pantalla
     * @param sesion los datos de la sesión
     * @param transicion
     * @throws IOException arroja {@link java.io.IOException} si no se encuentra la pantalla
     */
    public default void cargarPantalla(Event event, Pantalla pantalla, DTOSesion sesion, boolean transicion) throws IOException {
        // Agregar la pantalla al historial de la sesión actual
        sesion.agregarPantalla(pantalla);

        // Cargar el archivo FXML
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(pantalla.fxml));
        Parent nuevaPantallaParent = loader.load();

        // Acceder al controlador de la nueva pantalla, e inicializarlo
        IControladorPantalla IControladorPantalla = loader.getController();
        IControladorPantalla.inicializar(sesion);

        // Obtener la información del Stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (transicion) {
            // Transicionar desde la pantalla actual hacia la nueva pantalla
            this.transicionar(event, nuevaPantallaParent);
        } else {
            stage.getScene().setRoot(nuevaPantallaParent);
        }
    }

    /**
     * Transiciona desde la escena actual a la nueva escena
     * @param event el evento que proporcionó el llamado a cambio de pantalla
     * @param nuevaPantallaParent el root de la nueva pantalla
     */
    private void transicionar(Event event, Parent nuevaPantallaParent) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene escenaActual = ((Node) event.getSource()).getScene();

        // Crear snapshots de ambas pantallas
        WritableImage wi = new WritableImage((int) escenaActual.getWidth(), (int) escenaActual.getHeight());
        Pane root1 = (Pane) escenaActual.getRoot();
        Image img1 = root1.snapshot(new SnapshotParameters(), wi);
        ImageView imgView1 = new ImageView(img1);

        wi = new WritableImage((int) escenaActual.getWidth(), (int) escenaActual.getHeight());
        Parent root2 = nuevaPantallaParent;
        Image img2 = root2.snapshot(new SnapshotParameters(),wi);
        ImageView imgView2= new ImageView(img2);

        // Crear nuevo pane con ambas imágenes
        imgView1.setTranslateX(0);
        imgView2.setTranslateX(escenaActual.getWidth());
        StackPane pane = new StackPane(imgView1,imgView2);
        pane.setPrefSize(escenaActual.getWidth(),escenaActual.getHeight());

        // Reemplazar root1 con el nuevo pane
        ObservableList<Node> children =  root1.getChildren();
        root1.getChildren().setAll(pane);

        // Crear la transición
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(imgView2.translateXProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t->{
            // Remover el pane de transición de root
            root1.getChildren().setAll(children);
            // Mostrar la nueva escena
            stage.setScene(new Scene(nuevaPantallaParent));
        });
        timeline.play();
    }

    /**
     * Cambia a la pantalla anterior guardada en el historial de la sesión
     * @param event el evento que proporcionó el llamado a cambio de pantalla
     * @param sesion los datos de la sesión
     * @throws IOException arroja {@link java.io.IOException} si no se encuentra la pantalla
     */
    public default void volverPantalla(Event event, DTOSesion sesion) throws IOException {
        Pantalla pantallaAnterior = sesion.volverPantalla();
        this.cargarPantalla(event, pantallaAnterior, sesion, false);
    }

    /**
     * Crea una venta de diálogo con la información suministrada
     * @param tipo el tipo de ventana de diálogo. Ver también {@link javafx.scene.control.Alert.AlertType tipos de Alerta}
     * @param mensaje el mensaje a mostrar
     */
    public default void crearAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        if (tipo == Alert.AlertType.INFORMATION) {
            alerta.setTitle("Información");
        } else if (tipo == Alert.AlertType.WARNING) {
            alerta.setTitle("Advertencia");
        } else if (tipo == Alert.AlertType.ERROR) {
            alerta.setTitle("Error");
        }

        alerta.showAndWait();
    }

    /**
     * Crea una venta de diálogo con la información suministrada
     * @param tipo el tipo de ventana de diálogo. Ver también {@link javafx.scene.control.Alert.AlertType tipos de Alerta}
     * @param titulo el título del diálogo
     * @param mensaje el mensaje a mostrar
     */
    public default void crearAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setHeaderText(null);

        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
