package logica_negocio.recetas;

import entidades.modelo.Receta;

public interface IControladorRecetasCooker {

    public boolean crearListaFavoritos(String nombreLista, String descripcion, Receta receta);
    public boolean crearListaFavoritos(String nombreLista, String descripcion);
}
