package logica_negocio.perfiles;

import acceso_datos.persistencia_bd.ControladorPBDPerfiles;
import entidades.dto.DTOExito;

import java.math.BigInteger;
import java.sql.SQLException;

public class ControladorPerfiles implements IControladorPerfiles{

    private ControladorPBDPerfiles controlPBD;

    public ControladorPerfiles(){
        this.controlPBD = new ControladorPBDPerfiles();
    }

    @Override
    public DTOExito eliminarPerfil(BigInteger idusuario) throws SQLException {

        try{
            controlPBD.eliminarPerfil(idusuario);
            DTOExito mensaje = new DTOExito(true, "¡Se ha eliminado su cuenta!");
            return mensaje;
        } catch (SQLException sqlException) {
            throw sqlException;
        }
    }
}
