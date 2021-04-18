package logica_negocio.recetas;

import entidades.modelo.Categoria;
import entidades.modelo.LineaIngrediente;
import entidades.modelo.PasoReceta;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class ControladorRecetasChef implements IControladorRecetasChef {
    //DTO para mostrar mensaje.
    public void subirReceta (String nombre, String descripcion, String link_video, int tipovideo, String link_imagen, List<LineaIngrediente> ingredientes, List<Categoria> categorias, List<PasoReceta> pasosReceta) throws SQLException {

    }


    //Validar que la URL de un link exista
    public boolean validarUrl (String url){
        if (url.contains("https://www.")){
            try{
                (new URL(url)).openStream().close();
                return true;
            } catch (IOException e){
            }
        } else {
            url = "https://www." + url;
            try{
                (new URL(url)).openStream().close();
                return true;
            } catch (IOException e){
            }
        }
        return false;
    }

    //Validar que el link del video corresponda al tipo de video escogido.

}
