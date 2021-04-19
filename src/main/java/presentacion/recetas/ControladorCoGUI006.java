package presentacion.recetas;

import entidades.dto.DTOSesion;
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
}
