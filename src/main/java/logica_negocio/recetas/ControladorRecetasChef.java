package logica_negocio.recetas;

import acceso_datos.consultas_bd.ControladorCBDRecetas;
import acceso_datos.consultas_bd.ControladorCBDRecetasChef;
import acceso_datos.consultas_bd.IControladorCBDRecetas;
import acceso_datos.consultas_bd.IControladorCBDRecetasChef;
import acceso_datos.persistencia_bd.ControladorPBDRecetasChef;
import acceso_datos.persistencia_bd.IControladorPBDRecetasChef;
import entidades.dto.DTOExito;
import entidades.dto.DTOReceta;
import entidades.modelo.*;
import logica_negocio.utilidad.ControladorUtilidad;
import logica_negocio.utilidad.IControladorUtilidad;

import java.io.IOException;
import java.math.BigInteger;
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

    public ControladorRecetasChef() {
        this.controlPBD = new ControladorPBDRecetasChef();
        this.controlCBD = new ControladorCBDRecetasChef();
        this.controlUtilidad = new ControladorUtilidad();
        this.controlCBDRecetas = new ControladorCBDRecetas();
    }

    public List<Categoria> consultarCategorias() {
        List<Categoria> categorias = new ArrayList<>();

        try {
            categorias = this.controlCBD.consultarCategorias();
        } catch (SQLException throwables) {
        }

        return categorias;
    }

    @Override
    public DTOExito subirReceta(String nombre, String descripcion, String link_video, TipoVideo tipovideo, String link_imagen, List<LineaIngrediente> ingredientes, List<Categoria> categorias, List<PasoReceta> pasosReceta) {
        Receta receta = new Receta();
        boolean existeUrl = true;
        boolean validarTipo = true;
        boolean validarImagen = true;
        String link_videoFinal;
        receta.setNombre(nombre);
        receta.setDescripcion(descripcion);

        //  Generar un idReceta único
        try {
            BigInteger idReceta = generarIdRecetaUnico();
            receta.setIdReceta(idReceta);
        } catch (SQLException sqle) {
            return new DTOExito(false, "Error en la base de datos; " + sqle.getMessage());
        }

        // Comprobar linkvideo:
        // Existencia del URL
        existeUrl = validarUrl(link_video);
        if (!existeUrl) {
            return new DTOExito(false, "El url ingresado del video, ¡No existe!");
        } else {
            //Link_video correspondiente
            validarTipo = validarTipoVideo(link_video, tipovideo);
            if (!validarTipo) {
                return new DTOExito(false, "El link ingresado no corresponde al tipo de video seleccionado");
            } else {
                //Convertir link video
                link_videoFinal = convertirLink(link_video, tipovideo);
                receta.setLinkVideo(link_videoFinal);
            }
        }

        // Comprobar link_imagen:
        // Verificar que el link corresponda al de un formato de imagen
        validarImagen = validarLinkImagen(link_imagen);
        if (!validarImagen) {
            return new DTOExito(false, "El link de la imagen, no corresponde a ningun formato de imagen. (.jpg, .jpge, .png)");
        } else {
            receta.setLinkImagen(link_imagen);
        }

        // Ingredientes:
        for (LineaIngrediente actual : ingredientes) {
            if (actual.getCantidad() <= 0) {
                return new DTOExito(false, "No es posible asignar una cantidad menor a 0 en un ingrediente");
            }
        }
        receta.setLineasIngrediente(ingredientes);

        // Categorias:
        receta.setCategorias(categorias);

        // Pasos:
        for (PasoReceta actual : pasosReceta) {
            //Si existe una imagen, verificar su link.
            if (actual.isTieneImagen()) {
                validarImagen = validarLinkImagen(actual.getLinkImagen());
                if (!validarImagen) {
                    return new DTOExito(false, "El url de la imagen del paso " + actual.getNumero() + ", no corresponde al formato de imagen");
                }
            }
        }
        receta.setPasosReceta(pasosReceta);

        // Agregar ingredientes nuevos
        for (LineaIngrediente actual : ingredientes) {
            try {
                Ingrediente ingrediente = this.controlCBD.consultaIngrediente(actual.getIngrediente().getNombre());
                if (ingrediente.getIdIngrediente() == 0) {
                    actual.getIngrediente().setIdIngrediente(this.controlCBD.consultarIdIngrediente());
                    this.controlPBD.subirIngrediente(actual.getIngrediente());
                } else {
                    actual.getIngrediente().setIdIngrediente(ingrediente.getIdIngrediente());
                }
            } catch (SQLException sqle) {
                return new DTOExito(false, "Error en la base de datos; " + sqle.getMessage());
            }
        }

        // Enviar información para insertarla en la Base de Datos
        try {
            this.controlPBD.subirReceta(receta, this.chef.getIdUsuario());
        } catch (SQLException sqle) {
            return new DTOExito(false, "Error en la base de datos; " + sqle.getMessage());
        }

        // Generar DTO:
        return new DTOExito(true, "La receta fue agregada con exito");
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
    public boolean validarUrl(String url) {
        if (url.contains("https://www.")) {
            try {
                (new URL(url)).openStream().close();
                return true;
            } catch (IOException e) {
            }
        } else {
            url = "https://www." + url;
            try {
                (new URL(url)).openStream().close();
                return true;
            } catch (IOException e) {
            }
        }
        return false;
    }

    @Override
    //Validar que el link del video corresponda al tipo de video escogido.
    public boolean validarTipoVideo(String url, TipoVideo tipovideo) {
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
        } else if (tipovideo == TipoVideo.VIMEO) {
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
    public String convertirLink(String url, TipoVideo tipovideo) {
        String urlVimeo = "player.";
        String urlYoutube = "https://www.youtube.com/embed/";
        if (tipovideo == TipoVideo.YOUTUBE) {
            String[] parts = url.split("=");
            String part1 = parts[0];
            String part2 = parts[1];
            url = urlYoutube + part2;
            return url;
        } else if (tipovideo == TipoVideo.VIMEO) {
            if (url.contains("https://")) {
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
    public boolean validarLinkImagen(String url) {
        if ((url.contains(".png")) || (url.contains(".jpg")) || (url.contains(".jpeg"))) {
            return true;
        }
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public DTOReceta modificarNombre(String nuevoNombre, BigInteger idReceta) {
        DTOReceta receta = new DTOReceta();
        try {
            boolean cambiado = this.controlPBD.modificarNombreReceta(nuevoNombre, idReceta);
            Receta recetaRec = this.controlCBDRecetas.buscarRecetas(idReceta);
            Chef chef = this.controlCBDRecetas.consultaRecetaXChef(idReceta);
            receta.setReceta(recetaRec);
            receta.setAutor(chef);
            receta.setEncontrado(true);
        } catch (SQLException sqlException) {
            receta.setEncontrado(false);
        }

        return receta;
    }

    /**
     * @inheritDoc
     */
    @Override
    public DTOReceta modificarDecripcion(String nuevaDesc, BigInteger idReceta) {
        DTOReceta receta = new DTOReceta();
        try {
            receta.setEncontrado(this.controlPBD.modificarDescrReceta(nuevaDesc, idReceta));
            Receta recetaRec = this.controlCBDRecetas.buscarRecetas(idReceta);
            Chef chef = this.controlCBDRecetas.consultaRecetaXChef(idReceta);
            receta.setReceta(recetaRec);
            receta.setAutor(chef);
        } catch (SQLException sqlException) {
            receta.setEncontrado(false);
        }

        return receta;
    }

    /**
     * @inheritDoc
     */
    @Override
    public DTOReceta modificarLinkVideo(String nuevoLink, BigInteger idReceta) {
        DTOReceta receta = new DTOReceta();
        try {
            receta.setEncontrado(this.controlPBD.modificarLinkVideoReceta(nuevoLink, idReceta));
            Receta recetaRec = this.controlCBDRecetas.buscarRecetas(idReceta);
            Chef chef = this.controlCBDRecetas.consultaRecetaXChef(idReceta);
            receta.setReceta(recetaRec);
            receta.setAutor(chef);
        } catch (SQLException sqlException) {
            receta.setEncontrado(false);
        }

        return receta;
    }

    /**
     * @inheritDoc
     */
    @Override
    public DTOReceta modificarLinkImg(String nuevoLink, BigInteger idReceta) {
        DTOReceta receta = new DTOReceta();
        try {
            receta.setEncontrado(this.controlPBD.modificarLinkImgReceta(nuevoLink, idReceta));
            Receta recetaRec = this.controlCBDRecetas.buscarRecetas(idReceta);
            Chef chef = this.controlCBDRecetas.consultaRecetaXChef(idReceta);
            receta.setReceta(recetaRec);
            receta.setAutor(chef);
        } catch (SQLException sqlException) {
            receta.setEncontrado(false);
        }

        return receta;
    }

    @Override
    public List<String> categoriasxChef(BigInteger idChef) {
        List<String> categorias = new ArrayList<>();
        try {
            categorias.addAll(controlCBD.categoriasXChef(idChef));
        } catch (SQLException sqle) {
            categorias = null;
        }
        return categorias;
    }

    @Override
    public DTOReceta buscarReceta(BigInteger idreceta) {
        DTOReceta receta = new DTOReceta();

        try {
            Receta rec = this.controlCBDRecetas.buscarRecetas(idreceta);
            Chef chef = this.controlCBDRecetas.consultaRecetaXChef(idreceta);
            receta.setReceta(rec);
            receta.setAutor(chef);
        } catch (SQLException sqlException) {
            receta = null;
        }

        return receta;
    }
    @Override
    public DTOReceta eliminarReceta(BigInteger idreceta){
        DTOReceta receta = new DTOReceta();

        try{
            Receta rec = this.controlCBDRecetas.buscarRecetas(idreceta);
            receta.setEncontrado(true);
            receta.setReceta(rec);
            receta.setAutor(this.controlCBDRecetas.consultaRecetaXChef(idreceta));
            if(this.controlPBD.eliminarReceta(idreceta)){
                receta.setMensaje("la receta ha sido eliminada exitosamente");
            }
            else{
                receta.setMensaje("la receta no se pudo eliminar");
            }
        }catch (SQLException sqlException){
            receta.setMensaje("fallo en la base de datos");
            receta.setReceta(null);
            receta.setAutor(null);
            receta.setEncontrado(false);
        }
        return receta;

    }
}
