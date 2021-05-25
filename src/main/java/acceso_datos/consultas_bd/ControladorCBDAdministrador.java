package acceso_datos.consultas_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;
import entidades.modelo.Cooker;
import entidades.modelo.MotivoReporte;
import entidades.modelo.Reporte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControladorCBDAdministrador implements IControladorCBDAdministrador {
    private ControladorBDConexion controladorBDConexion;
    private Connection conexion;

    public ControladorCBDAdministrador() {
        this.controladorBDConexion = new ControladorBDConexion();
        conexion = this.controladorBDConexion.conectarMySQL();
    }

    @Override
    public List<Reporte> revisarReportes() throws SQLException {
        List<Reporte> reportes = new ArrayList<>();
        String consulta = "SELECT usuario.idusuario, usuario.nombreusuario, usuario.fechacreacion, usuario.nombre, motivo.idmotivo, motivo.nombre, motivo.descripcion, reporte.fecha FROM (reporte left outer join usuario on (cooker_idusuario = idusuario)) left outer join motivo on (reporte.idmotivo = motivo.idmotivo);";

        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cooker c = new Cooker(rs.getBigDecimal("usuario.idusuario").toBigInteger(),
                        rs.getString("usuario.nombreusuario"), rs.getDate("usuario.fechacreacion"),
                        rs.getString("usuario.nombre"));
                MotivoReporte mr = new MotivoReporte(rs.getInt("motivo.idmotivo"),
                        rs.getString("motivo.nombre"), rs.getString("motivo.descripcion"));
                Date fecha = rs.getDate("reporte.fechacreacion");
                reportes.add(new Reporte(c, mr, fecha, true)); //No sé si lo de true o qué poner ahí
            }
        } catch (SQLException sqle) {
            throw sqle;
        }
        return reportes;
    }

}
