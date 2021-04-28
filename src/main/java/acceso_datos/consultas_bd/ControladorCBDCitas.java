package acceso_datos.consultas_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;
import entidades.modelo.Bloque;
import entidades.modelo.Calendario;
import entidades.modelo.Chef;
import entidades.modelo.Dia;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControladorCBDCitas implements IControladorCBDCitas {
    private Connection conexion;

    public ControladorCBDCitas() {
        ControladorBDConexion controladorBDConexion = new ControladorBDConexion();
        this.conexion = controladorBDConexion.conectarMySQL();
    }

    public ControladorCBDCitas(Connection conexion) {
        this.conexion = conexion;
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


}
