package acceso_datos.persistencia_bd;

import entidades.modelo.*;

import java.math.BigInteger;
import java.sql.SQLException;

public interface IControladorPBDRegAut {
    public void crearUsuario(TipoUsuario tipoUsuario, Usuario usuario) throws SQLException;

    public void crearCredenciales(BigInteger idUsuario, CredencialesUsuario credenciales) throws SQLException;

}
