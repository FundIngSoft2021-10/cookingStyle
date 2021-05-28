package logica_negocio.recetas;

import entidades.dto.DTOExito;
import entidades.dto.DTOReceta;
import entidades.modelo.Categoria;
import entidades.modelo.LineaIngrediente;
import entidades.modelo.PasoReceta;
import entidades.modelo.TipoVideo;

import java.math.BigInteger;
import java.util.List;

public interface IControladorRecetasChef {
    public List<Categoria> consultarCategorias();
    public DTOExito subirReceta (String nombre, String descripcion, String link_video, TipoVideo tipovideo, String link_imagen, List<LineaIngrediente> ingredientes, List<Categoria> categorias, List<PasoReceta> pasosReceta);
    public boolean validarUrl (String url);
    public boolean validarTipoVideo(String url, TipoVideo tipovideo);
    public String convertirLink (String url, TipoVideo tipovideo);
    public  boolean validarLinkImagen (String url);

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

    public List<String> categoriasxChef (BigInteger idChef);

    public DTOReceta buscarReceta(BigInteger idreceta);

    public DTOReceta eliminarReceta(BigInteger idreceta);
}
