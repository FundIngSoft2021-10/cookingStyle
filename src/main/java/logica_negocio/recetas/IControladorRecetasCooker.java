package logica_negocio.recetas;

import entidades.dto.DTOReceta;
import entidades.modelo.Receta;

public interface IControladorRecetasCooker {

    public boolean crearListaFavoritos(String nombreLista, String descripcion, int id_receta);
    public boolean crearListaFavoritos(String nombreLista, String descripcion);
    public boolean agregarRecetaListaFavoritos(DTOReceta receta, int idLista);
}
