package logica_negocio.recetas;

import entidades.dto.DTOReceta;
import entidades.modelo.Receta;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface IControladorRecetasCooker {

    public boolean crearListaFavoritos(String nombreLista, String descripcion, BigInteger id_receta) throws SQLException;
    public boolean crearListaFavoritos(String nombreLista, String descripcion) throws SQLException ;
    public boolean agregarRecetaListaFavoritos(DTOReceta receta, int idLista) throws SQLException ;
    public boolean agregarRecetaListaFavoritos(DTOReceta receta, List<Integer> idLista) throws SQLException ;
}
