package presentacion;

import acceso_datos.persistencia_bd.ControladorPBDRecetasChef;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logica_negocio.recetas.ControladorRecetasChef;

import java.math.BigInteger;
import java.sql.SQLException;

public class CookingApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(Pantalla.TO_GUI001_PRINCIPAL.fxml));
        primaryStage.setTitle("Cooking App");
        primaryStage.setScene(new Scene(root));
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }

}
