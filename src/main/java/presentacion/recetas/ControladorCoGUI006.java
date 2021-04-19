package presentacion.recetas;

import entidades.dto.DTOSesion;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import presentacion.IControladorPantalla;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorCoGUI006 implements IControladorPantalla {
    private DTOSesion sesion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.sesion = sesion;
    }

    public void clickNombreChef(MouseEvent mouseEvent) {
    }

    public void clickFavorito(MouseEvent mouseEvent) {
    }

    public void clickReportar(MouseEvent mouseEvent) {
    }

    public void clickVolver(MouseEvent mouseEvent) {
    }

    public void clickVerIng(ActionEvent actionEvent) {
    }
}
