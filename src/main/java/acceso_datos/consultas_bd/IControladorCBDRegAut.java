package acceso_datos.consultas_bd;

import entidades.dto.DTOCredencialesBD;
import entidades.modelo.Usuario;

import java.math.BigInteger;
import java.sql.SQLException;

public interface IControladorCBDRegAut {
    public boolean existeUsuario(BigInteger idUsuario) throws SQLException;
    public boolean existeCorreoUsuario(String correo) throws SQLException;
    public DTOCredencialesBD buscarCredencialesUsuario(String correo) throws SQLException;
    public Usuario buscarUsuario(BigInteger idUsuario) throws SQLException;
}
