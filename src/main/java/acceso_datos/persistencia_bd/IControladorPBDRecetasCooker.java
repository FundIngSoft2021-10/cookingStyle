package acceso_datos.persistencia_bd;

import entidades.dto.DTOListaFavoritos;

import java.math.BigInteger;
import java.sql.SQLException;

public interface IControladorPBDRecetasCooker {
    public boolean crearListaFavoritosConReceta(DTOListaFavoritos listaFavoritos) throws SQLException;
    public boolean crearListaFavoritos(DTOListaFavoritos listaFavoritos) throws SQLException;
    public boolean insertarRecetaListaFavoritos(BigInteger idreceta, int idlista, BigInteger idusuario) throws SQLException;
}
