package logica_negocio.recetas;

import entidades.dto.DTOReceta;
import entidades.modelo.Categoria;
import entidades.modelo.LineaIngrediente;
import entidades.modelo.PasoReceta;
import entidades.modelo.TipoVideo;
import javafx.scene.image.Image;
import logica_negocio.utilidad.ControladorUtilidad;
import logica_negocio.utilidad.IControladorUtilidad;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControladorRecetasChef implements IControladorRecetasChef {
    private IControladorUtilidad controlUtilidad;

    public ControladorRecetasChef() {
        this.controlUtilidad = new ControladorUtilidad();
    }

    //DTO para mostrar mensaje.
    public DTOReceta subirReceta (String nombre, String descripcion, String link_video, TipoVideo tipovideo, String link_imagen, List<LineaIngrediente> ingredientes, List<Categoria> categorias, List<PasoReceta> pasosReceta){
        BigInteger idBigInt = this.controlUtilidad.generarRandomBigInt(new BigInteger("1000000000000"), new BigInteger("10000000000000"));
        BigDecimal idReceta = new BigDecimal(idBigInt);

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
        linksyoutube.add("www.youtube.com");
        linksyoutube.add("youtube.com");
        linksyoutube.add("youtu.be/");
        List<String> linksvimeo = new ArrayList<>();
        linksvimeo.add("https://vimeo.com");
        linksvimeo.add("vimeo.com");
        if (tipovideo == TipoVideo.YOUTUBE) {
            for (String actual : linksyoutube) {
                if (url.contains(actual)) {
                    return true;
                }
            }
        } else if (tipovideo == TipoVideo.VIMEO){
            for (String actual : linksvimeo) {
                if (url.contains(actual)) {
                    return true;
                }
            }
        }
        return false;
    }

    //Convertir link a embed o player
    public String convertirLink (String url, TipoVideo tipovideo){
        String urlVimeo = "player.";
        if (tipovideo == TipoVideo.YOUTUBE){
            url.replace("watch?v=","embed/");
            return url;
        } else if (tipovideo == TipoVideo.VIMEO){
            if ( url.contains("https://") ){
                String[] parts = url.split("v");
                String part1 = parts[0]; // https://
                String part2 = parts[1]; // imeo.com/...
                part2 = "v" + part2;
                url = part1 + urlVimeo + part2;
                return url;
            } else {
                url = urlVimeo + url;
                return url;
            }
        }
        return url;
    }

    //NO SIRVE
    public  boolean validarImagen (String url){
        String url1 = "https://fbcdn-dragon-a.akamaihd.net/hphotos-ak-xft1/t39.1997-6/p200x200/851575_126362190881911_254357215_n.png";
        boolean exitoUrl;
        exitoUrl = validarUrl(url);
        if (exitoUrl) {
            Image image = ImageIO.read(url);
            if (image != null) {
                return true;
            }
        }
        return false;
    }


}
