package acceso_datos.persistencia_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;
import entidades.dto.DTOListaFavoritos;
import entidades.modelo.Receta;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ControladorPBDRecetasCooker implements IControladorPBDRecetasCooker{
    ControladorBDConexion controladorBDConexion;
    Connection conexion;

    public ControladorPBDRecetasCooker() {
        controladorBDConexion = new ControladorBDConexion();
        conexion = controladorBDConexion.conectarMySQL();
    }

    //Lista Favoritos

    @Override
    public boolean crearListaFavoritosConReceta(DTOListaFavoritos listaFavoritos) throws SQLException {

        String insert = "INSERT INTO listafavoritos (idlista, cooker_idusuario, nombre, descripcion) VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(insert);
            preparedStatement.setInt(1, listaFavoritos.getListaFavoritos().getIdListaFavoritos() );
            BigDecimal usuario = new BigDecimal(listaFavoritos.getCooker().getIdUsuario());
            preparedStatement.setBigDecimal(2, usuario);
            preparedStatement.setString(3,listaFavoritos.getListaFavoritos().getNombre());
            preparedStatement.setString(4, listaFavoritos.getListaFavoritos().getDescripicion());

            preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            throw sqle;
        }

        Receta receta = listaFavoritos.getListaFavoritos().getRecetasFavoritas().get(0);

        String insertRecetas = "INSERT INTO recetaxlista (idlista, cooker_idusuario, idreceta) VALUES (?,?,?);";

        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(insertRecetas);
            preparedStatement.setInt(1, listaFavoritos.getListaFavoritos().getIdListaFavoritos());
            BigDecimal usuario = new BigDecimal( listaFavoritos.getCooker().getIdUsuario());
            preparedStatement.setBigDecimal(2, usuario);
            BigDecimal recetaId = new BigDecimal(receta.getIdReceta());
            preparedStatement.setBigDecimal(3, recetaId);

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException sqle) {
            throw sqle;
        }
    }

    @Override
    public boolean crearListaFavoritos(DTOListaFavoritos listaFavoritos) throws SQLException{

        String insert = "INSERT INTO listafavoritos (idlista, cooker_idusuario, nombre, descripcion) VALUES ('" + listaFavoritos.getListaFavoritos().getIdListaFavoritos() + "' , '"
                + listaFavoritos.getCooker().getIdUsuario() + "' , '" + listaFavoritos.getListaFavoritos().getNombre() + "' , '" + listaFavoritos.getListaFavoritos().getDescripicion() + "' );" ;
        try {
            Statement st = conexion.createStatement();
            st.executeUpdate(insert);
            return true;
        } catch (SQLException sqle) {
            throw sqle;
        }
    }

    @Override
    public boolean insertarRecetaListaFavoritos(BigInteger idreceta, int idlista, BigInteger idusuario) throws SQLException{

        String insertRecetas = "INSERT INTO recetaxlista (idlista, cooker_idusuario, idreceta) VALUES (" + idlista + ", " + idusuario + " , " + idreceta + ");";

        try {
            Statement st = conexion.createStatement();
            st.executeUpdate(insertRecetas);

            return true;
        } catch (SQLException sqle) {
            throw sqle;
        }
    }

}
