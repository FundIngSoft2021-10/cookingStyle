package logica_negocio.recetas;

import acceso_datos.consultas_bd.ControladorCBDRecetasChef;
import acceso_datos.consultas_bd.ControladorCBDRegAut;
import acceso_datos.consultas_bd.IControladorCBDRecetasChef;
import acceso_datos.consultas_bd.IControladorCBDRegAut;
import acceso_datos.persistencia_bd.ControladorBDRecetasChef;
import acceso_datos.persistencia_bd.IControladorBDRecetasChef;
import entidades.dto.DTOReceta;
import entidades.modelo.*;
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
    private Chef chef;
    private IControladorBDRecetasChef controlPBD;
    private IControladorUtilidad controlUtilidad;
    private IControladorCBDRecetasChef controlCBD;


    public ControladorRecetasChef(Chef chef) {
        this.controlPBD = new ControladorBDRecetasChef();
        this.controlCBD = new ControladorCBDRecetasChef();
        this.controlUtilidad = new ControladorUtilidad();
        this.chef = chef;
    }

    //DTO para mostrar mensaje.
    public DTOReceta subirReceta (String nombre, String descripcion, String link_video, TipoVideo tipovideo, String link_imagen, List<LineaIngrediente> ingredientes, List<Categoria> categorias, List<PasoReceta> pasosReceta) {
        DTOReceta recetaLista = new DTOReceta();
        Receta receta = new Receta();
        boolean existeUrl = true;
        boolean validarTipo = true;
        String link_videoFinal;
        receta.setNombre(nombre);
        receta.setDescripcion(descripcion);
        //Generar un idReceta único
        try {
            BigInteger idReceta = generarIdRecetaUnico();
            receta.setIdReceta(idReceta);
        } catch (SQLException sqle){
            return new DTOReceta(null, null, "Error en la base de datos; " + sqle.getMessage());
        }
        //Comprobar linkvideo:
            //Existencia del URL
        existeUrl = validarUrl(link_video);
        if (!existeUrl){
            return new DTOReceta(null, null, "El url ingresado del video, ¡No existe!");
        } else {
         //Link_video correspondiente
            validarTipo = validarTipoVideo(link_video, tipovideo);
            if (!validarTipo){
                return new DTOReceta(null, null, "El link ingresado no corresponde al tipo de video seleccionado. ");
            } else {
                //Convertir link video
                link_videoFinal = convertirLink(link_video, tipovideo);
                receta.setLinkVideo(link_videoFinal);
            }
        }
        //Comprobar la existencia del link_imagen. (Método que envió Alejo, buscar otra opción)
        //Ingredientes: ¿se recibe una lista de la interfaz? ¿cómo valido estos datos? (Piense:D)
        //Categorias:
        //Pasos:
        //Generar DTO:
            //Instanciar la receta
            //Igualar el chef
            //Mensaje de éxito o fallo correspondiente.

        return recetaLista;
    }

    //Generar un IdReceta único
    private BigInteger generarIdRecetaUnico() throws SQLException {
        BigInteger idReceta;
        boolean existe = true;

        do {
            idReceta = this.controlUtilidad.generarRandomBigInt(new BigInteger("1000000000000"), new BigInteger("10000000000000"));
            existe = controlCBD.existeIdReceta(idReceta);
        } while (existe);

        return idReceta;
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
    /*public  boolean validarImagen (String url){
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
    }*/


}
