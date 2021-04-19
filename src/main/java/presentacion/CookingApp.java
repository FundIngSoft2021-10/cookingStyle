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
        Parent root = FXMLLoader.load(getClass().getResource("GUI001Principal.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1600, 900));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        //launch(args);
        ControladorPBDRecetasChef controladorPBDRecetasChef = new ControladorPBDRecetasChef();
        ControladorRecetasChef controladorRecetasChef = new ControladorRecetasChef();

            System.out.println(controladorRecetasChef.modificarDecripcion("Deliciosa ensalada oriental", BigInteger.valueOf(1021)).getReceta().getDescripcion());

    }

}
