package acceso_datos.persistencia_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;
import entidades.modelo.Bloque;
import entidades.modelo.Chef;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ControladorPBDCitas implements IControladorPBDCitas{
    ControladorBDConexion controladorBDConexion;
    Connection conexion;

    public ControladorPBDCitas() {
        controladorBDConexion = new Controlador BDConexion();
        conexion = controladorBDConexion.conectarMySQL();
    }

    @Override
    public void crearAgendaChef(Chef chef, List<Bloque> calendario) throws SQLException {
        String insercion = "INSERT INTO bloque (idBloque,Chef_idUsuario,dia,hora) VALUES (?,?,?,?)";
        int contador =1;
        for(Bloque bloque: calendario){
            try{
                PreparedStatement stmt = conexion.prepareStatement(insercion);
                stmt.setBigDecimal(1,new BigDecimal(contador));
                stmt.setBigDecimal(2,new BigDecimal(chef.getIdUsuario()));
                stmt.setInt(3,bloque.getDia().getValor());
                stmt.setInt(4,bloque.getHora());

                stmt.executeUpdate();

            } catch (SQLException sqle){
                throw sqle;
            }
            contador++;
        }

    }
}
