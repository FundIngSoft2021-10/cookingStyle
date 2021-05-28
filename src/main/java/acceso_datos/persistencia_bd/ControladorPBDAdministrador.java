package acceso_datos.persistencia_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControladorPBDAdministrador implements IControladorPBDAdministrador{
    private ControladorBDConexion controladorBDConexion;
    private Connection conexion;

    public ControladorPBDAdministrador() {
        this.controladorBDConexion = new ControladorBDConexion();
        conexion = this.controladorBDConexion.conectarMySQL();
    }

    @Override
    public void eliminarReporte(int idReporte) throws SQLException{
        String borrar = "DELETE FROM reporte WHERE reporte.idreporte= " + idReporte + ";";

        try {
            PreparedStatement stmt = conexion.prepareStatement(borrar);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }

    }
}
