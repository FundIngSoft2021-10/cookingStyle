package logica_negocio.recetas;

import acceso_datos.consultas_bd.ControladorCBDRecetas;
import acceso_datos.consultas_bd.ControladorCBDRecetasChef;
import acceso_datos.consultas_bd.IControladorCBDRecetas;
import acceso_datos.consultas_bd.IControladorCBDRecetasChef;
import acceso_datos.persistencia_bd.ControladorPBDRecetasChef;
import acceso_datos.persistencia_bd.IControladorPBDRecetasChef;
import entidades.dto.DTOReceta;
import entidades.modelo.*;
import javafx.scene.image.Image;
import logica_negocio.utilidad.ControladorUtilidad;
import logica_negocio.utilidad.IControladorUtilidad;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControladorRecetasChef implements IControladorRecetasChef {
    private Chef chef;
    private IControladorPBDRecetasChef controlPBD;
    private IControladorUtilidad controlUtilidad;
    private IControladorCBDRecetasChef controlCBD;
    private IControladorCBDRecetas controlCBDRecetas;


    public ControladorRecetasChef(Chef chef) {
        this.controlPBD = new ControladorPBDRecetasChef();
        this.controlCBD = new ControladorCBDRecetasChef();
        this.controlUtilidad = new ControladorUtilidad();
        this.controlCBDRecetas = new ControladorCBDRecetas();
        this.chef = chef;
    }

    public ControladorRecetasChef(){
        this.controlPBD = new ControladorPBDRecetasChef();
        this.controlCBD = new ControladorCBDRecetasChef();
        this.controlUtilidad = new ControladorUtilidad();
        this.controlCBDRecetas = new ControladorCBDRecetas();
    }

    @Override
    public DTOReceta subirReceta (String nombre, String descripcion, String link_video, TipoVideo tipovideo, String link_imagen, List<LineaIngrediente> ingredientes, List<Categoria> categorias, List<PasoReceta> pasosReceta) {
        Receta receta = new Receta();
        boolean existeUrl = true;
        boolean validarTipo = true;
        boolean validarImagen = true;
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
        //Comprobar link_imagen:
            //Existencia del URL
        existeUrl = validarUrl(link_imagen);
        if (!existeUrl){
            return new DTOReceta(null, null, "El url ingresado de la imagen, ¡No existe!");
        } else {
            //Verificar que el link corresponda al de un formato de imagen
            validarImagen = validarLinkImagen(link_imagen);
            if (!validarImagen){
                return new DTOReceta(null, null, "El link de la imagen, no corresponde a ningun formato de imagen. (.jpg, .jpge, .png)");
            } else{
                receta.setLinkImagen(link_imagen);
            }
        }
        //Ingredientes:
        for (LineaIngrediente actual : ingredientes){
            if (actual.getCantidad() <= 0){
                return new DTOReceta(null, null, "No es posible asignar una cantidad menor a 0 en un ingrediente. ");
            }
        }
        receta.setLineasIngrediente(ingredientes);
        //Categorias:
        receta.setCategorias(categorias);
        //Pasos:
        for (PasoReceta actual: pasosReceta){
            //Si existe una imagen, verificar su link.
            if (actual.isTieneImagen()){
                existeUrl = validarUrl(actual.getLinkImagen());
                if(!existeUrl){
                    return new DTOReceta(null, null, "El url de la imagen del paso " + actual.getNumero() + ", ¡No existe!");
                } else {
                    validarImagen = validarLinkImagen(actual.getLinkImagen());
                    if (!validarImagen){
                        return new DTOReceta(null, null, "El url de la imagen del paso " + actual.getNumero() + ", no corresponde al formato de imagen. ");
                    }
                }
            }
        }
        receta.setPasosReceta(pasosReceta);
        //Agregar ingredientes nuevos
        for (LineaIngrediente actual : ingredientes){
            try {
                Ingrediente ingrediente = this.controlCBD.consultaIngrediente(actual.getIngrediente().getNombre());
                if (ingrediente.getIdIngrediente() == 0){
                    ingrediente.setIdIngrediente(this.controlCBD.consultarIdIngrediente());
                    this.controlPBD.subirIngrediente(actual.getIngrediente());
                }
            } catch (SQLException sqle){
                return new DTOReceta(null, null, "Error en la base de Datos; " + sqle.getMessage());
            }
        }
        //Enviar información para insertarla en la Base de Datos
        try{
            this.controlPBD.subirReceta(receta, this.chef.getIdUsuario());
        } catch (SQLException sqle){
            return new DTOReceta(null, null, "Error en la base de Datos; " + sqle.getMessage());
        }
        //Generar DTO:
        return new DTOReceta(receta,this.chef, "La receta fue agregada con exito. ");
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    //Validar que la URL corresponda a una imagen
    public  boolean validarLinkImagen (String url){
        if ((url.contains(".png")) || (url.contains(".jpg")) || (url.contains(".jpeg"))){
            return true;
        }
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public DTOReceta modificarNombre(String nuevoNombre, BigInteger idReceta){
        DTOReceta receta = new DTOReceta();
        try {
            boolean cambiado = this.controlPBD.modificarNombreReceta(nuevoNombre, idReceta);
            Receta recetaRec = this.controlCBDRecetas.buscarRecetas(idReceta);
            Chef chef = this.controlCBDRecetas.consultaRecetaXChef(idReceta);
            receta.setReceta(recetaRec);
            receta.setAutor(chef);
            receta.setEncotrado(true);
        } catch (SQLException sqlException) {
            receta.setEncotrado(false);
        }

        return receta;
    }

    /**
     * @inheritDoc
     */
    @Override
    public DTOReceta modificarDecripcion(String nuevaDesc, BigInteger idReceta){
        DTOReceta receta = new DTOReceta();
        try {
            receta.setEncotrado(this.controlPBD.modificarDescrReceta(nuevaDesc, idReceta));
            Receta recetaRec = this.controlCBDRecetas.buscarRecetas(idReceta);
            Chef chef = this.controlCBDRecetas.consultaRecetaXChef(idReceta);
            receta.setReceta(recetaRec);
            receta.setAutor(chef);
        } catch (SQLException sqlException) {
            receta.setEncotrado(false);
        }

        return receta;
    }

    /**
     * @inheritDoc
     */
    @Override
    public DTOReceta modificarLinkVideo(String nuevoLink, BigInteger idReceta){
        DTOReceta receta = new DTOReceta();
        try {
            receta.setEncotrado(this.controlPBD.modificarLinkVideoReceta(nuevoLink, idReceta));
            Receta recetaRec = this.controlCBDRecetas.buscarRecetas(idReceta);
            Chef chef = this.controlCBDRecetas.consultaRecetaXChef(idReceta);
            receta.setReceta(recetaRec);
            receta.setAutor(chef);
        } catch (SQLException sqlException) {
            receta.setEncotrado(false);
        }

        return receta;
    }

    /**
     * @inheritDoc
     */
    @Override
    public DTOReceta modificarLinkImg(String nuevoLink, BigInteger idReceta){
        DTOReceta receta = new DTOReceta();
        try {
            receta.setEncotrado(this.controlPBD.modificarLinkImgReceta(nuevoLink, idReceta));
            Receta recetaRec = this.controlCBDRecetas.buscarRecetas(idReceta);
            Chef chef = this.controlCBDRecetas.consultaRecetaXChef(idReceta);
            receta.setReceta(recetaRec);
            receta.setAutor(chef);
        } catch (SQLException sqlException) {
            receta.setEncotrado(false);
        }

        return receta;
    }

}
