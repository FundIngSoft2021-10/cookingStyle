package acceso_datos.persistencia_bd;

import entidades.dto.DTOListaFavoritos;

import java.math.BigInteger;
import java.sql.SQLException;

public interface IControladorPBDRecetasCooker {
    public boolean crearListaFavoritosConReceta(DTOListaFavoritos listaFavoritos) throws SQLException;
    public boolean crearListaFavoritos(DTOListaFavoritos listaFavoritos) throws SQLException;
    public boolean insertarRecetaListaFavoritos(BigInteger idreceta, int idlista, BigInteger idusuario) throws SQLException;
    public boolean calificarChef (BigInteger idChef, BigInteger idCooker, int calificacion) throws SQLException;
    public boolean calificarReceta(BigInteger idReceta, BigInteger idUsuario, int calificacion) throws SQLException;
    public boolean eliminarCalificacion(BigInteger idReceta, BigInteger idUsuario) throws SQLException;
}
