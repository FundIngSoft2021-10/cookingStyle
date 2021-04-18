package logica_negocio.recetas;

import entidades.modelo.Categoria;
import entidades.modelo.LineaIngrediente;
import entidades.modelo.PasoReceta;
import entidades.modelo.TipoVideo;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControladorRecetasChef implements IControladorRecetasChef {
    //DTO para mostrar mensaje.
    public void subirReceta (String nombre, String descripcion, String link_video, TipoVideo tipovideo, String link_imagen, List<LineaIngrediente> ingredientes, List<Categoria> categorias, List<PasoReceta> pasosReceta) throws SQLException {

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
    public boolean validarTipoVideo (String url, TipoVideo tipovideo){
        List<String> linksyoutube = new ArrayList<>();
        linksyoutube.add("https://www.youtube.com");
        linksyoutube.add("https://youtube.com");
        linksyoutube.add("youtu.be/");
        if (tipovideo.equals(TipoVideo.YOUTUBE)) {
            for (String actual : linksyoutube) {
                if (url.contains(actual)) {
                    return true;
                }
            }
        } else if (tipovideo.equals(TipoVideo.VIMEO)){
            if (url.contains("lol")){
                return true;
            }
        }
        return false;
    }

}
