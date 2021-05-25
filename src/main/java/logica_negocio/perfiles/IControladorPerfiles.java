package logica_negocio.perfiles;

import entidades.dto.DTOExito;

import java.math.BigInteger;
import java.sql.SQLException;

import entidades.dto.DTOPerfil;
import entidades.modelo.Usuario;

public interface IControladorPerfiles {

    public DTOExito eliminarPerfil(BigInteger idusuario) throws SQLException;

    public DTOPerfil modificarPerfil(Usuario usuario, String valorAModificar, String modificacion );
}
