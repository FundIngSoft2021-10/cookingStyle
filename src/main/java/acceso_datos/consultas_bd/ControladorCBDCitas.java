package acceso_datos.consultas_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;
import entidades.modelo.Bloque;
import entidades.modelo.Calendario;
import entidades.modelo.Chef;
import entidades.modelo.Dia;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControladorCBDCitas implements IControladorCBDCitas {
    private ControladorBDConexion controladorBDConexion;
    private Connection conexion;

    public ControladorCBDCitas() {
        this.controladorBDConexion = new ControladorBDConexion();
        conexion = this.controladorBDConexion.conectarMySQL();
    }

    @Override
    public Calendario buscarCalendarioChef(Chef chef) throws SQLException {
        Calendario calendario = new Calendario();
        String consulta =
                "SELECT * FROM bloque WHERE Chef_idUsuario = ?";

        try {
            PreparedStatement stmt = conexion.prepareStatement(consulta);
            stmt.setBigDecimal(1, new BigDecimal(chef.getIdUsuario()));

            ResultSet rs = stmt.executeQuery();

            List<Bloque> bloques = new ArrayList<>();
            while (rs.next()) {
                Bloque bloque = new Bloque();

                bloque.setDia(Dia.valueOf(rs.getInt("dia")));
                bloque.setHora(rs.getInt("hora"));
                bloques.add(bloque);
            }
            calendario.setBloques(bloques);

        } catch (SQLException sqle) {
            throw sqle;
        }

        return calendario;
    }

    @Override
    public String getCorreo (BigInteger idUsuario) throws SQLException {
        String correo = null;
        String consulta = "SELECT correo FROM credenciales WHERE idusuario =" + idUsuario;
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                correo = rs.getString("correo");
            }
        } catch (SQLException sqle) {
            throw sqle;
        }
        return correo;
    }

}
