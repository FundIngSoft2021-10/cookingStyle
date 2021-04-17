package acceso_datos.persistencia_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;
import entidades.modelo.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;

import java.util.Random;

public class ControladorBDRecetasChef implements IControladorBDRecetasChef {
    ControladorBDConexion controladorBDConexion = new ControladorBDConexion();
    Connection conexion = controladorBDConexion.conectarMySQL();



    @Override
    public boolean subirReceta( Receta rec,  BigInteger idUsuario) throws SQLException {
        //boolean resp=false;
        String insertReceta = "INSERT INTO receta VALUES (?, ?, ? , ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(insertReceta)){


            //cambiar a bigint el id usuario: ya
            //instanciar
            //setbigdecimal
            BigInteger big=randBi(19);//genera el numero aleatorio con 19 cifras
            BigDecimal bigdec = new BigDecimal(big);

            BigDecimal user= new BigDecimal(idUsuario);

            stmt.setBigDecimal(1, bigdec);
            stmt.setBigDecimal(2, user);

            stmt.setString(3, rec.getNombre());
            stmt.setString(4, rec.getDescripcion());
            stmt.setString(5, rec.getLinkVideo());
            stmt.setString(6, rec.getLinkImagen());

            stmt.executeUpdate();
            return true;
        } catch (SQLException sqle){
            throw sqle;
        }

        //return false;
    }
    /*metodo para hallar numero aleatorio*/
    public static BigInteger randBi(int digitosDecimales) {
        Random rand = new Random();
        StringBuilder s = new StringBuilder();
        for( int i = 0; i < digitosDecimales; i++ ) {
            int ir = i == 0 ? rand.nextInt(9) + 1 : rand.nextInt(10);
            s.append((char) ('0' + ir));
        }
        return new BigInteger(s.toString());
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
        //si es 1 es que se modifico correctamente la fila que debia, de resto no deberia haber tantas filas modificadas
        if(valorRet==1){
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public int eliminarReceta (BigInteger idUsuario) throws SQLException {
        int valoresBorra=0;
        String deleteReceta = "DELETE FROM receta WHERE receta.chef_idusuario = "+idUsuario;

        try(PreparedStatement stmt = conexion.prepareStatement(deleteReceta)){
            valoresBorra=stmt.executeUpdate();
        }catch (SQLException sqle){
            throw sqle;
        }

        return valoresBorra;//va a devolver la cantidad de recetas eliminadas
    }



}
