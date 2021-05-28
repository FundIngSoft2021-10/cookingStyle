package acceso_datos.persistencia_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;
import entidades.modelo.Admin;
import entidades.modelo.Chef;
import entidades.modelo.Cooker;
import entidades.modelo.Usuario;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    @Override
    public Usuario buscarUsuario(BigInteger idUsuario) throws SQLException {
        String consulta = "SELECT * FROM usuario WHERE idUsuario = ?";

        try {
            PreparedStatement stmt = conexion.prepareStatement(consulta);
            stmt.setBigDecimal(1, new BigDecimal(idUsuario));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                if (rs.getInt("idTipoUsuario") == 1) {
                    usuario = new Admin(idUsuario, rs.getString("nombreUsuario"),
                            rs.getDate("fechaCreacion"), rs.getString("nombre"));
                } else if (rs.getInt("idTipoUsuario") == 2) {
                    usuario = new Cooker(idUsuario, rs.getString("nombreUsuario"),
                            rs.getDate("fechaCreacion"), rs.getString("nombre"));
                } else if (rs.getInt("idTipoUsuario") == 3) {
                    usuario = new Chef(idUsuario, rs.getString("nombreUsuario"),
                            rs.getDate("fechaCreacion"), rs.getString("nombre"));
                }
                return usuario;
            }
        } catch (SQLException sqle) {
            throw sqle;
        }

        return null;
    }

}
