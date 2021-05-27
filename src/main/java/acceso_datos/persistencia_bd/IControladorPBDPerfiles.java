package acceso_datos.persistencia_bd;

import java.math.BigInteger;
import java.sql.SQLException;

public interface IControladorPBDPerfiles {

    public void eliminarPerfil(BigInteger idUsuario) throws SQLException;

    public void modificarPerfil(BigInteger idUsuario, String valorAModificar, String modificacion) throws SQLException;
}
