package acceso_datos.persistencia_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;
import entidades.modelo.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;

import java.util.List;
import java.util.Random;

public class ControladorPBDRecetasChef implements IControladorPBDRecetasChef {
    ControladorBDConexion controladorBDConexion = new ControladorBDConexion();
    Connection conexion = controladorBDConexion.conectarMySQL();
/*
    @Override
    public boolean subirIngrediente (Ingrediente ingrediente)throws SQLException{
        String insertIngrediente = "INSERT INTO ingrediente VALUES (?, ?)";

        try (PreparedStatement smtm =conexion.prepareStatement(insertIngrediente)){

            smtm.setInt(1, ingrediente.getIdIngrediente());
            smtm.setString(2, ingrediente.getNombre());

            smtm.executeUpdate();
            return true;

        }catch (SQLException sqle){
            throw sqle;
        }

    }*/

    @Override
    public boolean subirLineaIngrediente(List<LineaIngrediente> lineaIngrediente, BigInteger idReceta) throws SQLException {
        String insertLineaIngre = "INSERT INTO lineaingrediente (idreceta, idingrediente, cantidad, medida) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(insertLineaIngre)){
            for(int i=0; i < lineaIngrediente.size(); i++){
                BigDecimal bigdec= new BigDecimal(idReceta);
                stmt.setBigDecimal(1, bigdec);
                stmt.setInt(2, lineaIngrediente.get(i).getIngrediente().getIdIngrediente());
                stmt.setFloat(3, lineaIngrediente.get(i).getCantidad());
                stmt.setString(4, lineaIngrediente.get(i).getMedida().toString());

                stmt.executeUpdate();
            }
            return true;

        }catch (SQLException sqle){
            throw sqle;
        }

    }

    @Override
    public boolean subirPasoreceta(List<PasoReceta> pasoReceta, BigInteger idReceta) throws SQLException{
        String insertPasoReceta = "INSERT INTO pasoreceta (idpasoreceta, linkimagen, texto, idreceta) VALUES (?, ?, ?, ?)";

        try(PreparedStatement stmt = conexion.prepareStatement(insertPasoReceta)){
            for(int i=0; i< pasoReceta.size(); i++){
                stmt.setInt(1, pasoReceta.get(i).getNumero());
                BigDecimal bigdec= new BigDecimal(idReceta);
                stmt.setString(2, pasoReceta.get(i).getLinkImagen());
                stmt.setString(3, pasoReceta.get(i).getTexto());
                stmt.setBigDecimal(4, bigdec);

                stmt.executeUpdate();


            }
            return true;
        }catch (SQLException sqle){
            throw sqle;
        }
    }

    @Override
    public boolean subirCategoria(List<Categoria> categorias, BigInteger idReceta) throws SQLException {
        String insertCategoria = "INSERT INTO categoriaxreceta VALUES (?, ?)";//no se hace el insert en categoria si no en categoriaxreceta

        try(PreparedStatement stmt = conexion.prepareStatement(insertCategoria)){
            for(int i=0; i<categorias.size();i++){
                BigDecimal bigdec= new BigDecimal(idReceta);
                stmt.setBigDecimal(1, bigdec);
                stmt.setInt(2, categorias.get(i).getIdCategoria());

                stmt.executeUpdate();

            }
            return true;
        }catch (SQLException sqle){
            throw sqle;
        }
    }

    /*
    @Override
    public boolean subirCalificacion(List<Calificacion> calificaciones, BigInteger idReceta) throws SQLException {
        String insertCalificacion = "INSERT INTO calificacion VALUES (?, ?, ?)";

        try(PreparedStatement stmt=conexion.prepareStatement(insertCalificacion)){
            for(int i=0; i<calificaciones.size(); i++){
                BigDecimal bigdec= new BigDecimal(idReceta);
                BigDecimal idUser= new BigDecimal(calificaciones.get(i).getUsuario().getIdUsuario());
                stmt.setBigDecimal(1, idUser);
                stmt.setBigDecimal(2, bigdec);

                stmt.setInt(3, (int) calificaciones.get(i).getValor());

                stmt.executeUpdate();
            }

            return true;
        }catch (SQLException sqle){
            throw sqle;
        }
    }

    @Override
    public boolean subirReporte(List<Reporte> reportes, BigInteger idReceta) throws SQLException {
        String insertReceta = "INSERT INTO reporte VALUES (?, ?, ? , ?, ?)";

        try(PreparedStatement stmt=conexion.prepareStatement(insertReceta)){
            for(int i=0; i<reportes.size(); i++){
                stmt.setInt(1, reportes.get(i).getMotivo().getIdMotivo());
                BigDecimal bigdec= new BigDecimal(idReceta);
                stmt.setBigDecimal(2, bigdec);
                BigDecimal idUser= new BigDecimal(reportes.get(i).getUsuario().getIdUsuario());
                stmt.setBigDecimal(3, idUser);
                stmt.setInt(4, reportes.get(i).getMotivo().getIdMotivo());
                stmt.setDate(5, (Date) reportes.get(i).getFecha());
            }
            return true;
        }catch (SQLException sqle){
            throw sqle;
        }
    }
    */
    @Override
    public boolean subirReceta( Receta rec,  BigInteger idUsuario) throws SQLException {
        //boolean resp=false;
        String insertReceta = "INSERT INTO receta (idreceta, nombre, descripcion, linkVideo, linkimagen, chef_idusuario) VALUES (?, ?, ? , ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(insertReceta)){


            //cambiar a bigint el id usuario: ya
            //instanciar
            //setbigdecimal
            // BigInteger big=randBi(19);//genera el numero aleatorio con 19 cifras
            BigDecimal bigdec = new BigDecimal(rec.getIdReceta());

            BigDecimal user = new BigDecimal(idUsuario);

            stmt.setBigDecimal(1, bigdec);//id receta
            stmt.setString(2, rec.getNombre());
            stmt.setString(3, rec.getDescripcion());
            stmt.setString(4, rec.getLinkVideo());
            stmt.setString(5, rec.getLinkImagen());
            stmt.setBigDecimal(6, user);

            subirLineaIngrediente(rec.getLineasIngrediente(), rec.getIdReceta());//se manda a metodo de subir receta

            subirPasoreceta(rec.getPasosReceta(), rec.getIdReceta());// se manda a metodo de subir pasoreceta

            subirCategoria(rec.getCategorias(), rec.getIdReceta());

            //subirCalificacion(rec.getCalificaciones(), big);

            //subirCalificacion(rec.getCalificaciones(), big);

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
    public boolean modificarNombreReceta(String nuevoNombre, BigInteger idReceta) throws SQLException{
        String updateReceta = "UPDATE receta SET nombre= ? WHERE receta.idreceta = ?";

        try(PreparedStatement stmt = conexion.prepareStatement(updateReceta)){

            stmt.setString(1, nuevoNombre);
            BigDecimal idRec = new BigDecimal(idReceta);
            stmt.setBigDecimal(2, idRec);

            stmt.executeUpdate();

            return true;
        } catch (SQLException sqle){
            throw sqle;
        }
    }

    @Override
    public boolean modificarDescrReceta(String nuevaDecrip, BigInteger idReceta) throws SQLException{
        String updateReceta = "UPDATE receta SET descripcion= ? WHERE receta.idreceta = ?";

        try(PreparedStatement stmt = conexion.prepareStatement(updateReceta)){

            stmt.setString(1, nuevaDecrip);
            BigDecimal idRec = new BigDecimal(idReceta);
            stmt.setBigDecimal(2, idRec);

            stmt.executeUpdate();

            return true;
        } catch (SQLException sqle){
            throw sqle;
        }
    }

    @Override
    public boolean modificarReceta(Receta rec, String valorAModificar, String modificacion) throws SQLException{

        int valorRet=-1;
        if(valorAModificar=="linkvideo"){
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
