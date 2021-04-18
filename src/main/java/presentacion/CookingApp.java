package presentacion;

import entidades.dto.Pantalla;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class CookingApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("registro_autenticacion/" + Pantalla.TO_GUI001_PRINCIPAL.fxml));
        primaryStage.setTitle("Cooking App");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }

}
