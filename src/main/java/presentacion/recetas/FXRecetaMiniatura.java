package presentacion.recetas;

import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class FXRecetaMiniatura {
    public ImageView imagen;
    public Text nombre;

    public FXRecetaMiniatura(ImageView imagen, Text nombre) {
        this.imagen = imagen;
        this.nombre = nombre;
    }

    public FXRecetaMiniatura() {
    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }

    public Text getNombre() {
        return nombre;
    }

    public void setNombre(Text nombre) {
        this.nombre = nombre;
    }
}
