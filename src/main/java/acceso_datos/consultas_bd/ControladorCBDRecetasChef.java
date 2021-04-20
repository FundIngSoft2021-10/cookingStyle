package acceso_datos.consultas_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;
import entidades.modelo.Ingrediente;

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

    @Override
    public Ingrediente consultaIngrediente (String nombreIngrediente) throws SQLException {
        String consultaIngrediente = "SELECT * FROM ingrediente WHERE nombre = ?";
        int idIngrediente = 0;
        try (PreparedStatement stmt = conexion.prepareStatement(consultaIngrediente)) {
            stmt.setString(1, nombreIngrediente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idIngrediente = rs.getInt("idingrediente");
            }
        } catch (SQLException sqle) {
            throw sqle;
        }
        return new Ingrediente(idIngrediente, nombreIngrediente);
    }
    @Override
    public int consultarIdIngrediente () throws SQLException{
        String consultaIngredientes = "SELECT * FROM ingrediente";
        int idIngrediente = -1;
        try(PreparedStatement stmt = conexion.prepareStatement(consultaIngredientes)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idIngrediente = rs.getInt("idingrediente");
            }
        } catch (SQLException sqle){
            throw sqle;
        }
        return idIngrediente + 1;
    }

}
