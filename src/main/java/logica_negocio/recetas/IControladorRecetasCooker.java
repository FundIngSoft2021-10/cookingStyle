package logica_negocio.recetas;

import entidades.dto.DTOListaFavoritos;
import entidades.dto.DTOReceta;
import entidades.modelo.Receta;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface IControladorRecetasCooker {

    public DTOListaFavoritos crearListaFavoritos(String nombreLista, String descripcion, BigInteger id_receta) throws SQLException;
    public DTOListaFavoritos crearListaFavoritos(String nombreLista, String descripcion) throws SQLException ;
    public boolean agregarRecetaListaFavoritos(BigInteger idreceta, int idLista) throws SQLException ;
    public List<DTOListaFavoritos> agregarRecetaListaFavoritos(BigInteger idreceta, List<Integer> idsLista) throws SQLException ;
    public boolean existeRecetaListaFavoritos(int idLista, BigInteger idReceta) throws SQLException;
}
