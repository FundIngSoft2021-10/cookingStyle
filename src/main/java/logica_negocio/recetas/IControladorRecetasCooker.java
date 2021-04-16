package logica_negocio.recetas;

import entidades.dto.DTOReceta;
import entidades.modelo.Receta;

import java.sql.SQLException;
import java.util.List;

public interface IControladorRecetasCooker {

    public boolean crearListaFavoritos(String nombreLista, String descripcion, int id_receta) throws SQLException;
    public boolean crearListaFavoritos(String nombreLista, String descripcion) throws SQLException ;
    public boolean agregarRecetaListaFavoritos(DTOReceta receta, int idLista) throws SQLException ;
    public boolean agregarRecetaListaFavoritos(DTOReceta receta, List<Integer> idLista) throws SQLException ;
}
