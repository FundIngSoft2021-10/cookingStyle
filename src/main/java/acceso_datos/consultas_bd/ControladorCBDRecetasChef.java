package acceso_datos.consultas_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControladorCBDRecetasChef implements IControladorCBDRecetasChef {
    private ControladorBDConexion controladorBDConexion;
    private Connection conexion;

    public ControladorCBDRecetasChef() {
        controladorBDConexion = new ControladorBDConexion();
        conexion = controladorBDConexion.conectarMySQL();
    }
    @Override
    public boolean existeIdReceta(BigInteger idReceta) throws SQLException {
        String consulta = "SELECT COUNT(*) FROM receta WHERE idreceta = ?";

        try {
            PreparedStatement stmt = conexion.prepareStatement(consulta);
            stmt.setBigDecimal(1, new BigDecimal(idReceta));

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
}
