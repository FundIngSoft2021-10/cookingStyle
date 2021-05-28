package logica_negocio.perfiles;

import acceso_datos.persistencia_bd.ControladorPBDPerfiles;
import entidades.dto.DTOPerfil;
import entidades.modelo.Usuario;
import java.math.BigInteger;
import java.sql.SQLException;
import entidades.dto.DTOExito;

import java.math.BigInteger;
import java.sql.SQLException;

public class ControladorPerfiles implements IControladorPerfiles{

    private ControladorPBDPerfiles controlPBD;

    public ControladorPerfiles(){
        this.controlPBD = new ControladorPBDPerfiles();
    }

    /**
     * @inheritDoc
     */
    @Override
    public DTOExito eliminarPerfil(BigInteger idusuario){

        try{
            controlPBD.eliminarPerfil(idusuario);
            return new DTOExito(true, "¡Se ha eliminado su cuenta!");
        } catch (SQLException sqlException) {
            return new DTOExito(false, "Error en la base de datos; " + sqlException.getMessage());
        }
    }
    @Override
    public DTOPerfil modificarPerfil(Usuario usuario, String valorAModificar, String modificacion ){
        try{
            this.controlPBD.modificarPerfil(usuario.getIdUsuario(),valorAModificar,modificacion);
        }catch(SQLException e){
            return new DTOPerfil(false, null,"error en la base de datos");
        }
        if(valorAModificar=="correo"){
            usuario.getCredenciales().setCorreo(modificacion);
        }
        if(valorAModificar=="nombreUsuario"){
            usuario.setNombreUsuario(modificacion);
        }
        if(valorAModificar=="nombre"){
            usuario.setNombre(modificacion);
        }
        return new DTOPerfil(true,usuario,"se ha modificado el perfil de manera correcta");
    }
}
