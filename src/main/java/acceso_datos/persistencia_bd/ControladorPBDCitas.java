package acceso_datos.persistencia_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;
import entidades.modelo.Bloque;
import entidades.modelo.Chef;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ControladorPBDCitas implements IControladorPBDCitas{
    private Connection conexion;

    public ControladorPBDCitas() {
        ControladorBDConexion controladorBDConexion = new ControladorBDConexion();
        this.conexion = controladorBDConexion.conectarMySQL();
    }

    public ControladorPBDCitas(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void crearAgendaChef(BigInteger idChef, List<Bloque> bloques) throws SQLException {
        String insercion =
                "INSERT INTO bloque (idBloque, Chef_idUsuario, dia, hora) VALUES (?, ?, ?, ?)";

        int contador = 1;
        for (Bloque bloque: bloques) {
            try {
                PreparedStatement stmt = conexion.prepareStatement(insercion);
                stmt.setInt(1, contador);
                stmt.setBigDecimal(2, new BigDecimal(idChef));
                stmt.setInt(3, bloque.getDia().getValor());
                stmt.setInt(4, bloque.getHora());

                stmt.executeUpdate();
            } catch (SQLException sqle){
                throw sqle;
            }

            contador++;
        }

    }
}
