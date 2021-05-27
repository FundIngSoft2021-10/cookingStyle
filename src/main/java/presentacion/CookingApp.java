package presentacion;

import entidades.dto.Pantalla;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class CookingApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(Pantalla.TO_GUI001_PRINCIPAL.fxml));
        primaryStage.setTitle("Cooking: Cocina a tu Estilo");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {    // Fullscreen mediante F11, en cualquier pantalla
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.F11) {
                    primaryStage.setFullScreen(!primaryStage.isFullScreen());
                }
            }
        });
        primaryStage.getProperties().put("hostServices", this.getHostServices());
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }

}
