package presentacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CookingApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ChGUI007Perfil.fxml"));
        primaryStage.setTitle("Cooking App");
        primaryStage.initStyle(StageStyle.DECORATED);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setForceIntegerRenderScale(true);
        primaryStage.setMaximized(false);
        primaryStage.show();
       /* try {
            BorderPane root = new BorderPane();
            root.setPadding(new Insets(10));
            Scene scene = new Scene(root,400,400);
            Label l = new Label("SHAPE IMAGE OF MY SISTER");
            l.setFont(Font.font(Font.getFontNames().get(23), FontWeight.EXTRA_BOLD, 14));
            l.setAlignment(Pos.CENTER);
            l.setPrefWidth(Double.MAX_VALUE);
            root.setTop(l);
            ///////////////el código chido empieza acá
            Circle cir2 = new Circle(250,250,120);
            cir2.setStroke(Color.SEAGREEN);
            Image im = new Image("https://juicylinksmag.files.wordpress.com/2016/02/juliet-ibrahim.jpg",false);
            cir2.setFill(new ImagePattern(im));
            cir2.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
            //////////////acá termina el código importante
            root.setCenter(cir2);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }*/
    }
    public static void main(String[] args) {
        launch(args);
    }
}
