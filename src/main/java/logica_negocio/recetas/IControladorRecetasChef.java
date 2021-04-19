package logica_negocio.recetas;

import entidades.dto.DTOReceta;
import entidades.modelo.Categoria;
import entidades.modelo.LineaIngrediente;
import entidades.modelo.PasoReceta;
import entidades.modelo.TipoVideo;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface IControladorRecetasChef {
    public DTOReceta subirReceta (String nombre, String descripcion, String link_video, TipoVideo tipovideo, String link_imagen, List<LineaIngrediente> ingredientes, List<Categoria> categorias, List<PasoReceta> pasosReceta);
    public boolean validarUrl (String url);
    public boolean validarTipoVideo(String url, TipoVideo tipovideo);

    /**
     * Modifica el nombre de la receta
     * @param nuevoNombre nuevo nombre de la receta
     * @param idReceta la receta a modificar
     * @return DTOReceta con la receta actualizada
     */
    public DTOReceta modificarNombre(String nuevoNombre, BigInteger idReceta);

    /**
     * Modifica el descripcion de la receta
     * @param nuevaDesc nueva descripcion de la receta
     * @param idReceta la receta a modificar
     * @return DTOReceta con la receta actualizada
     */
    public DTOReceta modificarDecripcion(String nuevaDesc, BigInteger idReceta);

    /**
     * Modifica el link de video de la receta
     * @param nuevoLink nuevo link de video de la receta
     * @param idReceta la receta a modificar
     * @return DTOReceta con la receta actualizada
     */
    public DTOReceta modificarLinkVideo(String nuevoLink, BigInteger idReceta);

    /**
     * Modifica el link de imagen de la receta
     * @param nuevoLink nuevo link de imagen de la receta
     * @param idReceta la receta a modificar
     * @return DTOReceta con la receta actualizada
     */
    public DTOReceta modificarLinkImg(String nuevoLink, BigInteger idReceta);
}
