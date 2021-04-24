package acceso_datos.persistencia_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;
import entidades.modelo.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.Locale;

public class ControladorPBDRegAut implements IControladorPBDRegAut {
    ControladorBDConexion controladorBDConexion;
    Connection conexion;

    public ControladorPBDRegAut() {
        controladorBDConexion = new ControladorBDConexion();
        conexion = controladorBDConexion.conectarMySQL();
    }

    @Override
    public void crearUsuario(TipoUsuario tipoUsuario, Usuario usuario) throws SQLException {
        String insercion = "INSERT INTO usuario (idUsuario, nombreUsuario, fechaCreacion, nombre, idTipoUsuario) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexion.prepareStatement(insercion);
            stmt.setBigDecimal(1, new BigDecimal(usuario.getIdUsuario()));
            stmt.setString(2, usuario.getNombreUsuario());
            stmt.setDate(3, new java.sql.Date(usuario.getFechaCreacion().getTime()));
            stmt.setString(4, usuario.getNombre());
            stmt.setInt(5, tipoUsuario.getTipo());

            stmt.executeUpdate();

            this.crearCredenciales(usuario.getIdUsuario(), usuario.getCredenciales());
            this.crearUsuarioRol(tipoUsuario, usuario.getIdUsuario());

            if (tipoUsuario == TipoUsuario.COOKER)
                this.crearListaDefault(usuario.getIdUsuario(), ((Cooker) usuario).getListasFavoritos().get(0));
        } catch (SQLException sqle) {
            throw sqle;
        }
    }

    @Override
    public void crearCredenciales(BigInteger idUsuario, CredencialesUsuario credenciales) throws SQLException {
        String insercion = "INSERT INTO credenciales (idUsuario, correo, salt, hash) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexion.prepareStatement(insercion);
            stmt.setBigDecimal(1, new BigDecimal(idUsuario));
            stmt.setString(2, credenciales.getCorreo());
            stmt.setString(3, credenciales.getSalt());
            stmt.setString(4, credenciales.getHash());

            stmt.executeUpdate();
        } catch (SQLException sqle) {
            throw sqle;
        }
    }

    private void crearUsuarioRol(TipoUsuario tipoUsuario, BigInteger idUsuario) throws SQLException {
        String insercion = "INSERT INTO " + tipoUsuario.toString().toLowerCase() + " (idUsuario) VALUES (?)";

        try {
            PreparedStatement stmt = conexion.prepareStatement(insercion);
            stmt.setBigDecimal(1, new BigDecimal(idUsuario));

            stmt.executeUpdate();
        } catch (SQLException sqle) {
            throw sqle;
        }
    }

    private void crearListaDefault(BigInteger idUsuario, ListaFavoritos lista) throws SQLException {
        String insercion = "INSERT INTO listafavoritos (idlista, cooker_idusuario, nombre, descripcion) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexion.prepareStatement(insercion);
            stmt.setInt(1, lista.getIdListaFavoritos());
            stmt.setBigDecimal(2, new BigDecimal(idUsuario));
            stmt.setString(3, lista.getNombre());
            stmt.setString(4, lista.getDescripicion());

            stmt.executeUpdate();
        } catch (SQLException sqle) {
            throw  sqle;
        }
    }
}
