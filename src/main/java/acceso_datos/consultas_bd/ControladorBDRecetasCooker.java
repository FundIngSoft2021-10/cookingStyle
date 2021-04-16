package acceso_datos.consultas_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;
import entidades.dto.DTOListaFavoritos;
import entidades.modelo.*;
import logica_negocio.recetas.IControladorRecetasCooker;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorBDRecetasCooker implements IControladorBDRecetasCooker {
    ControladorBDConexion controladorBDConexion = new ControladorBDConexion();
    Connection conexion = controladorBDConexion.conectarMySQL();

    @Override
    public Ingrediente consultaIngrediente (int idIngrediente) throws SQLException{
        String consultaIngrediente = "SELECT * FROM ingrediente WHERE ingrediente.idingrediente = "+idIngrediente+";";
        String nombreIngrediente = null;
        try (PreparedStatement stmt = conexion.prepareStatement(consultaIngrediente)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                nombreIngrediente = rs.getString("nombre");
            }
        } catch (SQLException sqle) {
            throw sqle;
        }
        return new Ingrediente(idIngrediente, nombreIngrediente);
    }

    @Override
    public List<LineaIngrediente> consultaLineaIngrediente (BigInteger idReceta) throws SQLException{
        List<LineaIngrediente> lineaIngredientes = new ArrayList<>();
        String consultaLineaIngredientes = "SELECT * FROM lineaingrediente WHERE lineaingrediente.idReceta = "+idReceta+";";

        try (PreparedStatement stmt = conexion.prepareStatement(consultaLineaIngredientes)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idIngrediente = rs.getInt("idingrediente");
                float cantidad = rs.getFloat("cantidad");
                String medida = rs.getString("medida");
                Ingrediente ingrediente= consultaIngrediente(idIngrediente);
                LineaIngrediente resultado = null;
                switch (medida) {
                    case "MILIGRAMO":
                        resultado = new LineaIngrediente(ingrediente, cantidad, Medida.MILIGRAMO);
                        lineaIngredientes.add(resultado);
                        break;
                    case "GRAMO":
                        resultado = new LineaIngrediente(ingrediente, cantidad, Medida.GRAMO);
                        lineaIngredientes.add(resultado);
                        break;
                    case "KILOGRAMO":
                        resultado = new LineaIngrediente(ingrediente, cantidad, Medida.KILOGRAMO);
                        lineaIngredientes.add(resultado);
                        break;
                    case "MILILITRO":
                        resultado = new LineaIngrediente(ingrediente, cantidad, Medida.MILILITRO);
                        lineaIngredientes.add(resultado);
                        break;
                    case "LITRO":
                        resultado = new LineaIngrediente(ingrediente, cantidad, Medida.LITRO);
                        lineaIngredientes.add(resultado);
                        break;
                    case "UNIDAD":
                        resultado = new LineaIngrediente(ingrediente, cantidad, Medida.UNIDAD);
                        lineaIngredientes.add(resultado);
                        break;
                }
                lineaIngredientes.add(resultado);
            }

        } catch (SQLException sqle) {
            throw sqle;
        }
        return lineaIngredientes;
    }

    @Override
    public List<PasoReceta> consultaPasosReceta (BigInteger idReceta) throws SQLException{
        List<PasoReceta> pasosReceta = new ArrayList<>();
        String consultaPasos = "SELECT * FROM pasoreceta, receta WHERE pasoreceta.idReceta ="+idReceta+" and receta.idreceta="+idReceta+";";

        try (PreparedStatement stmt = conexion.prepareStatement(consultaPasos)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int numero = rs.getInt("idpasoreceta");
                String texto = rs.getString("texto");
                String linkImagen = rs.getString("linkimagen");
                boolean tieneImagen;
                if (linkImagen == null)
                    tieneImagen = false;
                else
                    tieneImagen = true;
                PasoReceta resultado = new PasoReceta(numero,texto,tieneImagen);
                pasosReceta.add(resultado);
            }

        } catch (SQLException sqle) {
            throw sqle;
        }
        return pasosReceta;
    }

    @Override
    public List<Categoria> consultaCategorias (BigInteger idReceta) throws SQLException{
        List<Categoria> categorias = new ArrayList<>();
        String consultaCategoriasxRecetas = "SELECT * FROM categoriaxreceta WHERE categoriaxreceta.idReceta="+idReceta+";";

        try (PreparedStatement stmt = conexion.prepareStatement(consultaCategoriasxRecetas)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idCategoria = rs.getInt("idcategoria");
                String consultaCategorias = "SELECT * FROM categoria WHERE categoria.idcategoria = "+idCategoria+";";

                String nombre=null, descripcion=null;
                try (PreparedStatement stmt1 = conexion.prepareStatement(consultaCategorias)) {
                    ResultSet rs1 = stmt1.executeQuery();
                    while (rs1.next()) {
                        nombre = rs1.getString("nombre");
                        descripcion = rs1.getString("descripcion");
                    }
                } catch (SQLException sqle) {
                    throw sqle;
                }
                Categoria resultado = new Categoria(idCategoria, nombre, descripcion);
                categorias.add(resultado);
            }

        } catch (SQLException sqle) {
            throw sqle;
        }
        return categorias;
    }

    @Override
    public Cooker consultaCooker (BigInteger idCooker) throws SQLException{
        String nombreUsuario=null, nombre=null;
        Date fecha=null;
        String consultaCookers = "SELECT * FROM usuario WHERE usuario.idusuario = "+idCooker+";";

        try (PreparedStatement stmt = conexion.prepareStatement(consultaCookers)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                nombreUsuario = rs.getString("nombreusuario");
                fecha = rs.getDate("fechacreacion");
                nombre = rs.getString("nombre");
            }
        } catch (SQLException sqle) {
            throw sqle;
        }
        return new Cooker(idCooker, nombreUsuario, fecha, nombre);
    }

    @Override
    public List<Calificacion> consultaCalificaciones (BigInteger idReceta) throws SQLException{
        List<Calificacion> calificaciones = new ArrayList<>();
        String consultaCalificacion = "SELECT * FROM calificacion WHERE calificacion.idReceta="+idReceta+";";

        try (PreparedStatement stmt = conexion.prepareStatement(consultaCalificacion)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                BigInteger idCooker = rs.getBigDecimal("cooker_idusuario").toBigInteger();
                int valor = rs.getInt("valor");
                Cooker cooker = consultaCooker(idCooker);
                Calificacion resultado = new Calificacion(valor,cooker);
                calificaciones.add(resultado);
            }

        } catch (SQLException sqle) {
            throw sqle;
        }
        return calificaciones;
    }

    @Override
    public MotivoReporte consultaMotivoReporte (int idMotivo) throws SQLException{
        String nombre=null, descripcion=null;
        String consultaMotivo = "SELECT * FROM motivo WHERE motivo.idmotivo = "+idMotivo+";";

        try (PreparedStatement stmt = conexion.prepareStatement(consultaMotivo)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                nombre = rs.getString("nombre");
                descripcion = rs.getString("descripcion");
            }
        } catch (SQLException sqle) {
            throw sqle;
        }
        return new MotivoReporte(idMotivo, nombre, descripcion);
    }

    @Override
    public List<Reporte> consultaReportes (BigInteger idReceta) throws SQLException{
        List<Reporte> reportes = new ArrayList<>();
        String consultaReporte = "SELECT * FROM reporte WHERE reporte.idReceta="+idReceta+";";
        try (PreparedStatement stmt = conexion.prepareStatement(consultaReporte)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                BigInteger idCooker = rs.getBigDecimal("cooker_idusuario").toBigInteger();
                int idMotivo = rs.getInt("idmotivo");
                Date fecha = rs.getDate("fecha");
                //------
                boolean resuelto = true;

                Cooker cooker = consultaCooker(idCooker);
                MotivoReporte motivoReporte = consultaMotivoReporte(idMotivo);
                Reporte resultado = new Reporte(cooker, motivoReporte, fecha, resuelto);
                reportes.add(resultado);
            }

        } catch (SQLException sqle) {
            throw sqle;
        }
        return reportes;
    }

    @Override
    public List<Receta> buscarRecetas () throws SQLException{
        List<Receta> recetas = new ArrayList<>();
        String consulta = "SELECT * FROM receta;";
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BigInteger idReceta =  rs.getBigDecimal("idReceta").toBigInteger();
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String linkVideo = rs.getString("linkVideo");
                boolean videoReceta;
                if (linkVideo == null)
                    videoReceta = false;
                else
                    videoReceta = true;

                String linkImagen = rs.getString("linkVideo");
                boolean imagenReceta;
                if (linkVideo == null)
                    imagenReceta = false;
                else
                    imagenReceta = true;

                List<LineaIngrediente> lineaIngredientes = consultaLineaIngrediente(idReceta);

                List<PasoReceta> pasosReceta = consultaPasosReceta(idReceta);

                List<Categoria> categorias = consultaCategorias(idReceta);

                List<Calificacion> calificaciones = consultaCalificaciones(idReceta);

                List<Reporte> reportes = consultaReportes(idReceta);

                Receta resultado = new Receta(idReceta, nombre, descripcion, videoReceta, linkVideo, TipoVideo.VIMEO,
                        imagenReceta, linkImagen, lineaIngredientes, pasosReceta, categorias, calificaciones, reportes);
                recetas.add(resultado);
            }

        } catch (SQLException sqle) {
            throw sqle;
        }
        return recetas;
    }

    @Override
    public Receta buscarRecetas (BigInteger idreceta) throws SQLException{
        Receta receta = new Receta();
        //String consulta = "SELECT * FROM receta WHERE UPPER(nombre) LIKE '%"+nombre+"%';";
        String consulta = "SELECT * FROM receta WHERE receta.idreceta = " + idreceta + ";";
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BigInteger idReceta =  rs.getBigDecimal("idReceta").toBigInteger();
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String linkVideo = rs.getString("linkVideo");
                boolean videoReceta;
                if (linkVideo == null)
                    videoReceta = false;
                else
                    videoReceta = true;

                String linkImagen = rs.getString("linkVideo");
                boolean imagenReceta;
                if (linkVideo == null)
                    imagenReceta = false;
                else
                    imagenReceta = true;

                List<LineaIngrediente> lineaIngredientes = consultaLineaIngrediente(idReceta);

                List<PasoReceta> pasosReceta = consultaPasosReceta(idReceta);

                List<Categoria> categorias = consultaCategorias(idReceta);

                List<Calificacion> calificaciones = consultaCalificaciones(idReceta);

                List<Reporte> reportes = consultaReportes(idReceta);

                receta = new Receta(idReceta, nombre, descripcion, videoReceta, linkVideo, TipoVideo.VIMEO,
                        imagenReceta, linkImagen, lineaIngredientes, pasosReceta, categorias, calificaciones, reportes);

            }

        } catch (SQLException sqle) {
            throw sqle;
        }
        return receta;
    }

    //Lista Favoritos

    @Override
    public boolean crearListaFavoritosConReceta(DTOListaFavoritos listaFavoritos) throws SQLException{

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

        String insertRecetas = "INSERT INTO recetaxlista (listafavoritos_idlista, cooker_idusuario, receta_idreceta) VALUES (?,?,?);";

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

        String insert = "INSERT INTO listafavoritos (idlista, cooker_idususario, nombre, descripcion) VALUES ('" + listaFavoritos.getListaFavoritos().getIdListaFavoritos() + "' , '"
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

        String insertRecetas = "INSERT INTO recetaxlista (listafavoritos_idlista, cooker_idusuario, receta_idreceta) VALUES (?,?,?);";

        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(insertRecetas);
            preparedStatement.setInt(1, idlista);
            BigDecimal usuario = new BigDecimal(idusuario);
            preparedStatement.setBigDecimal(2, usuario);
            BigDecimal receta = new BigDecimal(idreceta);
            preparedStatement.setBigDecimal(3, receta);

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException sqle) {
            throw sqle;
        }
    }
}
