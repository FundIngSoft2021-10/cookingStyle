package acceso_datos.consultas_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;
import entidades.dto.DTOListaFavoritos;
import entidades.dto.DTOReceta;
import entidades.modelo.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorCBDRecetasCooker implements IControladorCBDRecetasCooker {
    ControladorBDConexion controladorBDConexion;
    Connection conexion;

    public ControladorCBDRecetasCooker() {
        controladorBDConexion = new ControladorBDConexion();
        conexion = controladorBDConexion.conectarMySQL();
    }

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
    public List<Integer> consultaIdsIngrediente (String nom_ingrediente) throws SQLException{
        String consultaIngrediente = "SELECT * FROM ingrediente WHERE UPPER(ingrediente.nombre) LIKE '%"+ nom_ingrediente +"%';";
        List<Integer> idsIngredientes = new ArrayList<>();
        int idIngrediente;
        try (PreparedStatement stmt = conexion.prepareStatement(consultaIngrediente)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idIngrediente = rs.getInt("idingrediente");
                idsIngredientes.add(idIngrediente);
            }
        } catch (SQLException sqle) {
            throw sqle;
        }
        return idsIngredientes;
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
    public int consultaIdCategoria (String nom_categoria) throws SQLException{
        int idCategoria = 0;
        String consultaCategoria = "SELECT * FROM categoria WHERE UPPER(categoria.nombre) LIKE '%"+ nom_categoria +"%';";
        try (PreparedStatement stmt = conexion.prepareStatement(consultaCategoria)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idCategoria = rs.getInt("idcategoria");
            }
        } catch (SQLException sqle) {
            throw sqle;
        }
        return idCategoria;
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
    public Chef consultaChefReceta (BigInteger idChef) throws SQLException{
        String nombreUsuario=null, nombre=null;
        Date fecha=null;
        String consultaChefs = "SELECT * FROM usuario WHERE usuario.idusuario = "+idChef+";";

        try (PreparedStatement stmt = conexion.prepareStatement(consultaChefs)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                nombreUsuario = rs.getString("nombreusuario");
                fecha = rs.getDate("fechacreacion");
                nombre = rs.getString("nombre");
            }
        } catch (SQLException sqle) {
            throw sqle;
        }
        return new Chef(idChef, nombreUsuario, fecha, nombre);
    }

    @Override
    public List<BigInteger> consultaIdsChef (String nombre) throws SQLException{
        List<BigInteger> idsChefs = new ArrayList<>();
        BigInteger idChef;
        int tipoUsuario;
        String consulta = "SELECT * FROM usuario WHERE UPPER(nombre) LIKE '%"+nombre+"%' OR UPPER(nombreusuario) LIKE '"+nombre+"';";
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idChef = rs.getBigDecimal("idusuario").toBigInteger();
                tipoUsuario = rs.getInt("idtipousuario");
                if (tipoUsuario == 3){
                    idsChefs.add(idChef);
                }
            }
        } catch (SQLException sqle) {
            throw sqle;
        }
        return idsChefs;
    }

    @Override
    public List<DTOReceta> buscarRecetas () throws SQLException{
        List<DTOReceta> recetas = new ArrayList<>();
        String consulta = "SELECT * FROM receta;";
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BigInteger idReceta =  rs.getBigDecimal("idReceta").toBigInteger();
                BigInteger idChef =  rs.getBigDecimal("chef_idusuario").toBigInteger();
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

                Chef chef = consultaChefReceta(idChef);

                Receta resultado = new Receta(idReceta, nombre, descripcion, videoReceta, linkVideo, TipoVideo.VIMEO,
                        imagenReceta, linkImagen, lineaIngredientes, pasosReceta, categorias, calificaciones, reportes);

                recetas.add(new DTOReceta(resultado, chef));
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

    @Override
    public List<DTOReceta> buscarRecetas (String nombre_receta) throws SQLException{
        List<DTOReceta> recetas = new ArrayList<>();
        String consulta = "SELECT * FROM receta WHERE UPPER(nombre) LIKE '%"+ nombre_receta +"%';";
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BigInteger idReceta =  rs.getBigDecimal("idReceta").toBigInteger();
                BigInteger idChef =  rs.getBigDecimal("chef_idusuario").toBigInteger();
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

                Chef chef = consultaChefReceta(idChef);

                Receta resultado = new Receta(idReceta, nombre, descripcion, videoReceta, linkVideo, TipoVideo.VIMEO,
                        imagenReceta, linkImagen, lineaIngredientes, pasosReceta, categorias, calificaciones, reportes);

                recetas.add(new DTOReceta(resultado, chef));
            }

        } catch (SQLException sqle) {
            throw sqle;
        }
        return recetas;
    }

    @Override
    public List<DTOReceta> buscarRecetasCategoria (int id_categoria) throws SQLException{
        List<DTOReceta> recetas = new ArrayList<>();
        Receta receta;
        String consultaidReceta = "SELECT * FROM categoriaxreceta WHERE categoriaxreceta.idcategoria = " + id_categoria + ";";
        try (PreparedStatement stmt = conexion.prepareStatement(consultaidReceta)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BigInteger idReceta =  rs.getBigDecimal("idreceta").toBigInteger();
                receta = buscarRecetas(idReceta);
                BigInteger idChef = null;
                String consultaChef = "SELECT * FROM receta WHERE receta.idreceta = " + idReceta + ";";
                try (PreparedStatement stmt1 = conexion.prepareStatement(consultaChef)) {
                    ResultSet rs1 = stmt1.executeQuery();
                    while (rs1.next()) {
                        idChef = rs1.getBigDecimal("chef_idusuario").toBigInteger();
                    }
                } catch (SQLException sqle) {
                    throw sqle;
                }
                Chef chef = consultaChefReceta(idChef);
                recetas.add(new DTOReceta(receta, chef));
            }

        } catch (SQLException sqle) {
            throw sqle;
        }
        return recetas;
    }

    @Override
    public List<DTOReceta> buscarRecetasIngrediente (int id_ingrediente) throws SQLException{
        List<DTOReceta> recetas = new ArrayList<>();
        Receta receta;
        String consultaidReceta = "SELECT * FROM lineaingrediente WHERE lineaingrediente.idingrediente = " + id_ingrediente + ";";
        try (PreparedStatement stmt = conexion.prepareStatement(consultaidReceta)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BigInteger idReceta =  rs.getBigDecimal("idreceta").toBigInteger();
                receta = buscarRecetas(idReceta);
                BigInteger idChef = null;
                String consultaChef = "SELECT * FROM receta WHERE receta.idreceta = " + idReceta + ";";
                try (PreparedStatement stmt1 = conexion.prepareStatement(consultaChef)) {
                    ResultSet rs1 = stmt1.executeQuery();
                    while (rs1.next()) {
                        idChef = rs1.getBigDecimal("chef_idusuario").toBigInteger();
                    }
                } catch (SQLException sqle) {
                    throw sqle;
                }
                Chef chef = consultaChefReceta(idChef);
                recetas.add(new DTOReceta(receta, chef));
            }

        } catch (SQLException sqle) {
            throw sqle;
        }
        return recetas;
    }

    @Override
    public List<DTOReceta> buscarRecetasChef (BigInteger idchef) throws SQLException{
        List<DTOReceta> recetas = new ArrayList<>();
        String consulta = "SELECT * FROM receta WHERE receta.chef_idusuario = " + idchef + ";";
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

                Chef chef = consultaChefReceta(idchef);

                Receta resultado = new Receta(idReceta, nombre, descripcion, videoReceta, linkVideo, TipoVideo.VIMEO,
                        imagenReceta, linkImagen, lineaIngredientes, pasosReceta, categorias, calificaciones, reportes);

                recetas.add(new DTOReceta(resultado, chef));
            }

        } catch (SQLException sqle) {
            throw sqle;
        }
        return recetas;
    }


    @Override
    public boolean recetaEnListaRecetas(int idLista, BigInteger idReceta, BigInteger idUsuario) throws SQLException {

        BigInteger idRecetaRec = null;
        BigInteger idUsuarioRec = null;
        int idListaRec = -1;
        String consulta = "SELECT *  FROM recetaxlista WHERE recetaxlista.idreceta = " + idReceta + " and recetaxlista.idlista = " + idLista + " and recetaxlista.cooker_idusuario = " + idUsuario;

        try (PreparedStatement stmt = conexion.prepareStatement(consulta)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idRecetaRec = rs.getBigDecimal("idreceta").toBigInteger();
                idUsuarioRec = rs.getBigDecimal("cooker_idusuario").toBigInteger();
                idListaRec = rs.getInt("idlista");
            }
            if(idListaRec != -1 && idRecetaRec!= null && idUsuarioRec!=null){
                return true;
            } else
            {
                return false;
            }
        } catch (SQLException sqle){
            throw sqle;
        }
    }

    @Override
    public List<Receta> consultaRecetasListaFavoritos(BigInteger idUsuario, int idlista) throws SQLException {
        List<Receta> recetas = new ArrayList<>();
        String consulta = "SELECT * FROM recetaxlista WHERE idlista = " + idlista + " and cooker_idUsuario = " + idUsuario + ";";
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                BigInteger idReceta = rs.getBigDecimal("idreceta").toBigInteger();
                Receta receta = buscarRecetas(idReceta);
                recetas.add(receta);
            }

            return recetas;
        } catch (SQLException sqle){
            throw sqle;
        }
    }

    @Override
    public ListaFavoritos consultaListaFavoritos(int idLista) throws SQLException {

        ListaFavoritos listaFavoritos = new ListaFavoritos();
        String consulta = "SELECT * FROM listafavoritos WHERE listafavoritos.idlista = " + idLista + ";";

        try (PreparedStatement stmt = conexion.prepareStatement(consulta)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){

                BigInteger idUsuario = rs.getBigDecimal("cooker_idusuario").toBigInteger();
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");


                List<Receta> listaRecetas = consultaRecetasListaFavoritos(idUsuario, idLista);
                listaFavoritos = new ListaFavoritos(idLista, nombre, descripcion, listaRecetas);
            }
            return listaFavoritos;
        } catch (SQLException sqle){
            throw sqle;
        }

    }

    @Override
    public Chef consultaRecetaXChef(BigInteger idReceta) throws SQLException {
        Chef chef = new Chef ();

        String consulta = "SELECT * FROM receta WHERE idReceta = " + idReceta + ";";

        try (PreparedStatement stmt = conexion.prepareStatement(consulta)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                BigInteger idChef = rs.getBigDecimal("chef_idusuario").toBigInteger();
                chef = consultaChefReceta(idChef);
            }
            return  chef;

        } catch (SQLException sqle){
            throw sqle;
        }
    }

    @Override
    public Categoria consultaCategoria(int idCategoria) throws SQLException {

        Categoria categoria = new Categoria();
        String nombre = null, descripcion = null;

        String consulta = "SELECT * FROM categoria WHERE categoria.idcategoria = " + idCategoria + ";";

        try (PreparedStatement stmt = conexion.prepareStatement(consulta)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                nombre = rs.getString("nombre");
                descripcion = rs.getString("descripcion");
            }
            categoria.setIdCategoria(idCategoria);
            categoria.setNombre(nombre);
            categoria.setDescripcion(descripcion);

            return categoria;
        } catch (SQLException sqle){
            throw sqle;
        }
    }
}
