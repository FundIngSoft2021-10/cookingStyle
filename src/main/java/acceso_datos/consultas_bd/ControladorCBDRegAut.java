package acceso_datos.consultas_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;
import entidades.dto.DTOCredencialesBD;
import entidades.modelo.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;

public class ControladorCBDRegAut implements IControladorCBDRegAut {
    ControladorBDConexion controladorBDConexion;
    Connection conexion;

    public ControladorCBDRegAut() {
        controladorBDConexion = new ControladorBDConexion();
        conexion = controladorBDConexion.conectarMySQL();
    }

    @Override
    public boolean existeUsuario(BigInteger idUsuario) throws SQLException {
        String consulta = "SELECT COUNT(*) FROM usuario WHERE idUsuario = ?";

        try {
            PreparedStatement stmt = conexion.prepareStatement(consulta);
            stmt.setBigDecimal(1, new BigDecimal(idUsuario));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (SQLException sqle) {
            throw sqle;
        }

        return false;
    }

    @Override
    public boolean existeCorreoUsuario(String correo) throws SQLException {
        String consulta = "SELECT COUNT(*) FROM credenciales WHERE correo = ?";

        try {
            PreparedStatement stmt = conexion.prepareStatement(consulta);
            stmt.setString(1, correo);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (SQLException sqle) {
            throw sqle;
        }

        return false;
    }

    @Override
    public boolean existeNombreUsuario(String nombreUsuario) throws SQLException {
        String consulta = "SELECT COUNT(*) FROM usuario WHERE nombreUsuario = ?";

        try {
            PreparedStatement stmt = conexion.prepareStatement(consulta);
            stmt.setString(1, nombreUsuario);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (SQLException sqle) {
            throw sqle;
        }

        return false;
    }

    @Override
    public DTOCredencialesBD buscarCredencialesUsuario(String correo) throws SQLException {
        String consulta = "SELECT * FROM credenciales WHERE correo = ?";

        try {
            PreparedStatement stmt = conexion.prepareStatement(consulta);
            stmt.setString(1, correo);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                BigInteger idUsuario = rs.getBigDecimal("idUsuario").toBigInteger();
                CredencialesUsuario credenciales = new CredencialesUsuario(rs.getString("correo"),
                        rs.getString("salt"), rs.getString("hash"));
                return new DTOCredencialesBD(true, idUsuario, credenciales);
            }
        } catch (SQLException sqle) {
            throw sqle;
        }

        return new DTOCredencialesBD(false, null, null);
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
                } else if (rs.getInt("idTipoUsuario") == 2) {
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
