package acceso_datos.persistencia_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControladorPBDPerfiles implements IControladorPBDPerfiles{

    ControladorBDConexion controladorBDConexion;
    Connection conexion;

    public ControladorPBDPerfiles() {
        controladorBDConexion = new ControladorBDConexion();
        conexion = controladorBDConexion.conectarMySQL();
    }

    @Override
    public void eliminarPerfil(BigInteger idUsuario) throws SQLException {
        String eliminar = "DELETE FROM usuario WHERE usuario.idusuario= ?";
        try{
            PreparedStatement stmt = conexion.prepareStatement(eliminar);
            stmt.setBigDecimal(1, new BigDecimal(idUsuario));
            stmt.executeUpdate();

        }catch (SQLException sqle) {
            throw  sqle;
        }
    }

    @Override
    public void modificarPerfil(BigInteger idUsuario, String valorAModificar, String modificacion) throws SQLException {
        //el valor a modificar es la columna que se desea cambiar ya sea: nombre, noombreusuario o correo
        String modificar;
        if(valorAModificar=="correo"){
            modificar="UPDATE credenciales SET correo= ? WHERE credenciales.idusuario = ?";
        }
        else{
            modificar="UPDATE usuario SET "+ valorAModificar +"= ? WHERE usuario.idusuario = ?";
        }
        try{
            PreparedStatement stmt = conexion.prepareStatement(modificar);
            stmt.setString(1, modificacion);
            stmt.setBigDecimal(2, new BigDecimal(idUsuario));

            stmt.executeUpdate();
        }catch (SQLException sqle) {
            throw  sqle;
        }

    }

}
