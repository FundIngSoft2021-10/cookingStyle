package acceso_datos.consultas_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;
import entidades.dto.DTOListaFavoritos;
import entidades.modelo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;
import java.security.SecureRandom;

public class ControladorBDRecetasChef implements IControladorBDRecetasChef {
    ControladorBDConexion controladorBDConexion = new ControladorBDConexion();
    Connection conexion = controladorBDConexion.conectarMySQL();



    @Override
    public boolean subirReceta( String nombre, String descripcion, String linkVideo, String linkImagen, int idUsuario) throws SQLException {
        //boolean resp=false;
        String insertReceta = "INSERT INTO receta VALUES (?, ?, ? , ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(insertReceta)){
            //generar numero aleatorio
            Long l1 = Long.parseUnsignedLong("10000000000000000000");
            Long l2 = Long.parseUnsignedLong("1000000000000000000");
            int  numeroAleatorio = (int) (Math.random()* l1+l2);

            //cambiar a bigint el id usuario
            //instanciar
            //setbigdecimal
            stmt.setInt(1, numeroAleatorio);
            stmt.setInt(2, idUsuario);
            stmt.setString(3, nombre);
            stmt.setString(4, descripcion);
            stmt.setString(5, linkVideo);
            stmt.setString(6, linkImagen);

            stmt.executeUpdate();
            return true;
        } catch (SQLException sqle){
            throw sqle;
        }

        //return false;
    }

    @Override
    public boolean modificarReceta(Receta rec, String valorAModificar, String modificacion) throws SQLException{
        int valorRet=0;
        if(valorAModificar=="nombre"){
            String updateReceta = "UPDATE receta SET nombre="+modificacion+" WHERE receta.nombre = "+valorAModificar;

            try(PreparedStatement stmt = conexion.prepareStatement(updateReceta)){
                stmt.setString(1, modificacion);
                valorRet= stmt.executeUpdate();
            } catch (SQLException sqle){
                throw sqle;
            }

        }
        else if(valorAModificar=="descripcion"){
            String updateReceta = "UPDATE receta SET descripcion="+modificacion+" WHERE receta.nombre = "+valorAModificar;

            try(PreparedStatement stmt = conexion.prepareStatement(updateReceta)){
                stmt.setString(1, modificacion);
                valorRet= stmt.executeUpdate();
            } catch (SQLException sqle){
                throw sqle;
            }
        }
        else if(valorAModificar=="linkvideo"){
            String updateReceta = "UPDATE receta SET linkvideo="+modificacion+" WHERE receta.nombre = "+valorAModificar;

            try(PreparedStatement stmt = conexion.prepareStatement(updateReceta)){
                stmt.setString(1, modificacion);
                valorRet= stmt.executeUpdate();
            } catch (SQLException sqle){
                throw sqle;
            }
        }
        else if(valorAModificar=="linkimagen"){
            String updateReceta = "UPDATE receta SET linkimagen="+modificacion+" WHERE receta.nombre = "+valorAModificar;

            try(PreparedStatement stmt = conexion.prepareStatement(updateReceta)){
                stmt.setString(1, modificacion);
                valorRet= stmt.executeUpdate();
            } catch (SQLException sqle){
                throw sqle;
            }
        }
        //si es 1 es que se modifico correctamente la fila que debia, de resto no deberia haber tantas filas modificas
        if(valorRet==1){
            return true;
        }
        else {
            return false;
        }

    }


}
