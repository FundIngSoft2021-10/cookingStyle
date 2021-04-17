package acceso_datos.persistencia_bd;

import entidades.dto.DTOListaFavoritos;
import entidades.modelo.*;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface IControladorBDRecetasChef {
    public boolean subirReceta (Receta rec, BigInteger idUsuario) throws SQLException;
    public boolean modificarReceta (Receta rec, String valorAModificar, String modificacion) throws SQLException;
    public int eliminarReceta (BigInteger idUsuario) throws SQLException;


}
