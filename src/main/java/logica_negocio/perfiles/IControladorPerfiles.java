package logica_negocio.perfiles;

import entidades.dto.DTOExito;

import java.math.BigInteger;
import java.sql.SQLException;

public interface IControladorPerfiles {

    public DTOExito eliminarPerfil(BigInteger idusuario) throws SQLException;
}
