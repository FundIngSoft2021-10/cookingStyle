package logica_negocio.recetas;

import entidades.dto.*;
import entidades.modelo.Categoria;
import entidades.modelo.Chef;
import entidades.modelo.LineaIngrediente;

import java.math.BigInteger;
import java.util.List;

public interface IControladorRecetasCooker {
    /**
     * Creación de una lista de recetas favorita específica con una receta inicial
     *
     * @param nombreLista nombre de la lista de favoritos
     * @param descripcion decripcion de la lista
     * @param id_receta   ide de la receta a añiadir a la lista
     * @return la lista creada
     */
    public DTOListaFavoritos crearListaFavoritos(String nombreLista, String descripcion, BigInteger id_receta);

    /**
     * Creación de una lista de recetas favorita específica sin una receta inicial
     *
     * @param nombreLista nombre de la lista de favoritos
     * @param descripcion decripcion de la lista
     * @return la lista creada
     */
    public DTOListaFavoritos crearListaFavoritos(String nombreLista, String descripcion);

    /**
     * Agrega una receta a una lista de favoritos determinada
     *
     * @param idreceta id de la receta a agregar
     * @param idLista  id de la lista a la que se le va a agregar la receta
     * @return DTOExito con la informacion de resultado de la solicitud
     */
    public DTOExito agregarRecetaListaFavoritos(BigInteger idreceta, int idLista);

    /**
     * Dodo una lista de favoritos, agrega la receta a todas las listas
     *
     * @param idreceta id de la receta a agregar
     * @param idsLista lista con los id's de las listas
     * @return DTOExito con la informacion de resultado de la solicitud
     */
    public DTOExito agregarRecetaListaFavoritos(BigInteger idreceta, List<Integer> idsLista);

    /**
     * Buscar Recetas Nombre
     *
     * @param nombre nombre de la receta
     * @return lista DTOReceta con todas las recetas que tienen el nombre recibido en el nombre de la receta
     */
    public List<DTORecetaMiniatura> buscarRecetasNombre(String nombre);

    /**
     * Buscar Recetas Categoria
     *
     * @param categoria categoria que se está buscando
     * @return lista DTOReceta con las recetas que hacen parte de esa categoria
     */
    public List<DTORecetaMiniatura> buscarRecetasCategoria(String categoria);

    /**
     * Buscar Recetas Ingrediente
     *
     * @param nom_ingrediente nombre del ingrediente
     * @return lista DTOReceta con las recetas que tienen ese ingrediente
     */
    public List<DTORecetaMiniatura> buscarRecetasIngrediente(String nom_ingrediente);

    /**
     * Buscar Recetas Chef
     *
     * @param nom_chef nombre del chef
     * @return lista DTOReceta con las recetas que están asociadas con el chef
     */
    public List<DTORecetaMiniatura> buscarRecetasChef(String nom_chef);

    public List<DTORecetaMiniatura> buscarReceta(String busqueda);

    /**
     * TODO: JavaDoc
     */
    public List<DTORecetaMiniatura> buscarMiniaturaRecetaCategoria(int idCategoria);

    /**
     * Obtener la miniatura de una receta
     *
     * @param idReceta receta que se quiere mostrar enla miniatura
     * @return DTORecetaMiniatura con la receta solicitada
     */
    public DTORecetaMiniatura miniaturaRecetas(BigInteger idReceta);

    /**
     * TODO: JavaDoc
     */
    List<Categoria> buscarCategorias();

    /**
     * TODO: JavaDoc
     */
    List<DTORecetasMiniaturaCategoria> buscarMiniaturasRecetasCategoria();

    /**
     * Busca la receta que se quiere mostrar
     *
     * @param idReceta id de la receta que se quiere mostrar
     * @return DTOReceta con la receta y el autor de esta
     */
    public DTOReceta mostrarReceta(BigInteger idReceta);

    public DTOReceta buscarReceta(BigInteger idreceta);
    public List<LineaIngrediente> ingredientesxReceta(BigInteger idReceta);
    public DTOExito agregarRecetaListaFavoritos(BigInteger idreceta);
    public List<DTOReceta> recetasChef(BigInteger idChef);

    /**
     * Calificar chef
     * @param chef chef a calificar
     * @param calificacion entero de calificación
     * @return DToExito con confirmación de la acción
     */
    public DTOExito calificarChef(Chef chef, int calificacion);
}
