package logica_negocio.perfiles;

import entidades.dto.DTOExito;

import java.math.BigInteger;
import java.sql.SQLException;

import entidades.dto.DTOPerfil;
import entidades.modelo.Usuario;

public interface IControladorPerfiles {

    /**
     * Eliminar una cuenta
     * @param idusuario identificador del usuario a eliminar
     * @return DTOExito afirmando la eliminación de la cuenta
     * @throws SQLException
     */
    public DTOExito eliminarPerfil(BigInteger idusuario);

    public DTOPerfil modificarPerfil(Usuario usuario, String valorAModificar, String modificacion );

    public DTOExito verificarPerfil(BigInteger idusuario);
}
