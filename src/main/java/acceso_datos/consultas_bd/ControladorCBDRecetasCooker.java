package acceso_datos.consultas_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;
import entidades.dto.DTOReceta;
import entidades.dto.DTORecetaMiniatura;
import entidades.modelo.*;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControladorCBDRecetasCooker implements IControladorCBDRecetasCooker {
    private IControladorCBDRecetas controlCBDRecetas;
    private ControladorBDConexion controladorBDConexion;
    private Connection conexion;


    public ControladorCBDRecetasCooker() {
        controlCBDRecetas = new ControladorCBDRecetas();
        controladorBDConexion = new ControladorBDConexion();
        conexion = controladorBDConexion.conectarMySQL();
    }

    @Override
    public Ingrediente consultaIngrediente(int idIngrediente) throws SQLException {
        return this.controlCBDRecetas.consultaIngrediente(idIngrediente);
    }

    @Override
    public List<DTORecetaMiniatura> consultaIdsIngrediente(String nom_ingrediente) throws SQLException {
        String consultaIngrediente =
                "SELECT \n" +
                    "receta.idreceta, receta.nombre, receta.linkImagen, \n" +
                    "usuario.idusuario, usuario.nombreusuario, usuario.fechacreacion, \n" +
                    "usuario.nombre, usuario.idtipousuario \n" +
                "FROM \n" +
                    "ingrediente , lineaingrediente , receta, usuario \n" +
                "WHERE \n" +
                    "usuario.idusuario = receta.chef_idusuario AND \n" +
                    "receta.idreceta = lineaingrediente.idreceta AND \n" +
                    "lineaingrediente.idingrediente = ingrediente.idingrediente AND \n" +
                    "UPPER(ingrediente.nombre) LIKE '%" + nom_ingrediente + "%';";
        List<DTORecetaMiniatura> recetasIngredientes = new ArrayList<>();

        try (PreparedStatement stmt = conexion.prepareStatement(consultaIngrediente)) {
            //stmt.setString(1, nom_ingrediente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BigInteger idReceta = rs.getBigDecimal(1).toBigInteger();
                String nombreReceta = rs.getString(2);
                String linkImage = rs.getString(3);

                Chef autor = new Chef(rs.getBigDecimal(4).toBigInteger(), rs.getString(5), rs.getDate(6), rs.getString(7));

                recetasIngredientes.add(new DTORecetaMiniatura(idReceta, nombreReceta, linkImage, autor));
            }
        } catch (SQLException sqle) {
            throw sqle;
        }
        return recetasIngredientes;
    }

    @Override
    public List<LineaIngrediente> consultaLineaIngrediente(BigInteger idReceta) throws SQLException {
        return this.controlCBDRecetas.consultaLineaIngrediente(idReceta);
    }

    @Override
    public List<PasoReceta> consultaPasosReceta(BigInteger idReceta) throws SQLException {
        return this.controlCBDRecetas.consultaPasosReceta(idReceta);
    }

    @Override
    public List<Categoria> consultaCategorias(BigInteger idReceta) throws SQLException {
        return this.controlCBDRecetas.consultaCategorias(idReceta);
    }

    @Override
    public List<DTORecetaMiniatura> consultaIdCategoria(String nom_categoria) throws SQLException {
        List<DTORecetaMiniatura> recetasCategoria = new ArrayList<>();
        String consultaCategoria =
                "SELECT \n" +
                "receta.idreceta, receta.nombre, receta.linkImagen, \n" +
                "usuario.idusuario, usuario.nombreusuario, usuario.fechacreacion, \n"+
                "usuario.nombre, usuario.idtipousuario \n" +
                "FROM \n" +
                "categoria, categoriaxreceta, receta, usuario \n" +
                "WHERE \n" +
                "usuario.idusuario = receta.chef_idusuario AND \n" +
                "receta.idreceta = categoriaxreceta.idreceta AND \n" +
                "categoriaxreceta.idcategoria = categoria.idcategoria AND \n"+
                 "UPPER(categoria.nombre) LIKE '%" + nom_categoria + "%';";
        try (PreparedStatement stmt = conexion.prepareStatement(consultaCategoria)) {
            //stmt.setString(1, nom_categoria);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BigInteger idReceta = rs.getBigDecimal(1).toBigInteger();
                String nombreReceta = rs.getString(2);
                String linkImage = rs.getString(3);

                Chef autor = new Chef(rs.getBigDecimal(4).toBigInteger(), rs.getString(5), rs.getDate(6), rs.getString(7));

                recetasCategoria.add(new DTORecetaMiniatura(idReceta, nombreReceta, linkImage, autor));
            }
        } catch (SQLException sqle) {
            throw sqle;
        }
        return recetasCategoria;
    }

    @Override
    public Cooker consultaCooker(BigInteger idCooker) throws SQLException {
        return this.controlCBDRecetas.consultaCooker(idCooker);
    }

    @Override
    public List<Calificacion> consultaCalificaciones(BigInteger idReceta) throws SQLException {
        return this.controlCBDRecetas.consultaCalificaciones(idReceta);
    }

    @Override
    public MotivoReporte consultaMotivoReporte(int idMotivo) throws SQLException {
        return this.controlCBDRecetas.consultaMotivoReporte(idMotivo);
    }

    @Override
    public List<Reporte> consultaReportes(BigInteger idReceta) throws SQLException {
        return this.controlCBDRecetas.consultaReportes(idReceta);
    }

    @Override
    public Chef consultaChefReceta(BigInteger idChef) throws SQLException {
        return this.controlCBDRecetas.consultaChefReceta(idChef);
    }

    @Override
    public List<DTORecetaMiniatura> consultaIdsChef(String nombre) throws SQLException {
        List<DTORecetaMiniatura> recetasChefs = new ArrayList<>();
        String consulta =
                "SELECT DISTINCT \n" +
                    "receta.idreceta, receta.nombre, receta.linkImagen, \n" +
                    "usuario.idusuario, usuario.nombreusuario, usuario.fechacreacion, \n" +
                    "usuario.nombre, usuario.idtipousuario \n" +
                "FROM \n" +
                    "chef, receta, usuario \n" +
                "WHERE \n" +
                    "usuario.idusuario = receta.chef_idusuario AND \n" +
                    "UPPER(usuario.nombre) LIKE '%"+ nombre + "%';";
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            //stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BigInteger idReceta = rs.getBigDecimal(1).toBigInteger();
                String nombreReceta = rs.getString(2);
                String linkImage = rs.getString(3);

                Chef autor = new Chef(rs.getBigDecimal(4).toBigInteger(), rs.getString(5), rs.getDate(6), rs.getString(7));

                recetasChefs.add(new DTORecetaMiniatura(idReceta, nombreReceta, linkImage, autor));
            }
        } catch (SQLException sqle) {
            throw sqle;
        }
        return recetasChefs;
    }

    @Override
    public List<DTOReceta> buscarRecetas() throws SQLException {
        List<DTOReceta> recetas = new ArrayList<>();
        String consulta = "SELECT * FROM receta;";
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BigInteger idReceta = rs.getBigDecimal("idReceta").toBigInteger();
                BigInteger idChef = rs.getBigDecimal("chef_idusuario").toBigInteger();
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
    public Receta buscarRecetas(BigInteger idreceta) throws SQLException {
        return this.controlCBDRecetas.buscarRecetas(idreceta);
    }

    @Override
    public List<DTORecetaMiniatura> buscarRecetas(String nombre_receta) throws SQLException {
        List<DTORecetaMiniatura> recetas = new ArrayList<>();

        String consulta =
                "SELECT \n" +
                    "receta.idreceta, receta.nombre, receta.linkImagen, \n" +
                    "usuario.idusuario, usuario.nombreusuario, usuario.fechacreacion, \n"+
                    "usuario.nombre, usuario.idtipousuario \n"+
                "FROM \n"+
                    "receta, usuario \n"+
                "WHERE \n"+
                    "receta.chef_idusuario = usuario.idusuario AND \n"+
                    "UPPER(receta.nombre) LIKE '%"+ nombre_receta + "%' ;";

        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BigInteger idReceta = rs.getBigDecimal(1).toBigInteger();
                String nombre = rs.getString(2);
                String linkVideo = rs.getString(3);

                Chef autor = new Chef(rs.getBigDecimal(4).toBigInteger(), rs.getString(5),
                        rs.getDate(6), rs.getString(7));

                recetas.add(new DTORecetaMiniatura(idReceta, nombre, linkVideo, autor));

            }

        } catch (SQLException sqle) {
            throw sqle;
        }
        return recetas;
    }

    @Override
    public List<DTORecetaMiniatura> buscarRecetasCategoria(int id_categoria) throws SQLException {
        List<DTORecetaMiniatura> recetas = new ArrayList<>();
        String consulta =
                "SELECT\n" +
                "    receta.idreceta, receta.nombre, receta.linkImagen,\n" +
                "    usuario.idusuario, usuario.nombreusuario, usuario.fechacreacion, \n" +
                "    usuario.nombre, usuario.idtipousuario\n" +
                "FROM\n" +
                "    categoriaxreceta, receta, usuario\n" +
                "WHERE\n" +
                "    usuario.idusuario = receta.chef_idusuario AND \n" +
                "    receta.idreceta = categoriaxreceta.idreceta AND \n" +
                "    categoriaxreceta.idcategoria = ?";

        try {
            PreparedStatement stmt = conexion.prepareStatement(consulta);
            stmt.setInt(1, id_categoria);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BigInteger idReceta = rs.getBigDecimal(1).toBigInteger();
                String nombreReceta = rs.getString(2);
                String linkImagen = rs.getString(3);

                Chef autor = new Chef(rs.getBigDecimal(4).toBigInteger(), rs.getString(5),
                        rs.getDate(6), rs.getString(7));

                recetas.add(new DTORecetaMiniatura(idReceta, nombreReceta, linkImagen, autor));
            }
        } catch (SQLException sqle) {
            throw sqle;
        }

        return recetas;
    }

    @Override
    public List<DTOReceta> buscarRecetasIngrediente(int id_ingrediente) throws SQLException {
        List<DTOReceta> recetas = new ArrayList<>();
        Receta receta;
        String consultaidReceta = "SELECT * FROM lineaingrediente WHERE lineaingrediente.idingrediente = " + id_ingrediente + ";";
        try (PreparedStatement stmt = conexion.prepareStatement(consultaidReceta)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BigInteger idReceta = rs.getBigDecimal("idreceta").toBigInteger();
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
    public List<DTOReceta> buscarRecetasChef(BigInteger idchef) throws SQLException {
        List<DTOReceta> recetas = new ArrayList<>();
        String consulta = "SELECT * FROM receta WHERE receta.chef_idusuario = " + idchef + ";";
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BigInteger idReceta = rs.getBigDecimal("idReceta").toBigInteger();
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String linkVideo = rs.getString("linkVideo");
                boolean videoReceta;
                if (linkVideo == null)
                    videoReceta = false;
                else
                    videoReceta = true;

                String linkImagen = rs.getString("linkimagen");
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

        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idRecetaRec = rs.getBigDecimal("idreceta").toBigInteger();
                idUsuarioRec = rs.getBigDecimal("cooker_idusuario").toBigInteger();
                idListaRec = rs.getInt("idlista");
            }
            if (idListaRec != -1 && idRecetaRec != null && idUsuarioRec != null) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException sqle) {
            throw sqle;
        }
    }

    @Override
    public List<Receta> consultaRecetasListaFavoritos(BigInteger idUsuario, int idlista) throws SQLException {
        List<Receta> recetas = new ArrayList<>();
        String consulta = "SELECT * FROM recetaxlista WHERE idlista = " + idlista + " and cooker_idUsuario = " + idUsuario + ";";
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BigInteger idReceta = rs.getBigDecimal("idreceta").toBigInteger();
                Receta receta = buscarRecetas(idReceta);
                recetas.add(receta);
            }

            return recetas;
        } catch (SQLException sqle) {
            throw sqle;
        }
    }

    @Override
    public ListaFavoritos consultaListaFavoritos(int idLista) throws SQLException {

        ListaFavoritos listaFavoritos = new ListaFavoritos();
        String consulta = "SELECT * FROM listafavoritos WHERE listafavoritos.idlista = " + idLista + ";";

        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                BigInteger idUsuario = rs.getBigDecimal("cooker_idusuario").toBigInteger();
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");


                List<Receta> listaRecetas = consultaRecetasListaFavoritos(idUsuario, idLista);
                listaFavoritos = new ListaFavoritos(idLista, nombre, descripcion, listaRecetas);
            }
            return listaFavoritos;
        } catch (SQLException sqle) {
            throw sqle;
        }

    }

    @Override
    public Chef consultaRecetaXChef(BigInteger idReceta) throws SQLException {
        return this.controlCBDRecetas.consultaRecetaXChef(idReceta);
    }

    @Override
    public Categoria consultaCategoria(int idCategoria) throws SQLException {
        Categoria categoria = new Categoria();
        String nombre = null, descripcion = null;

        String consulta = "SELECT * FROM categoria WHERE categoria.idcategoria = " + idCategoria + ";";

        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                nombre = rs.getString("nombre");
                descripcion = rs.getString("descripcion");
            }
            categoria.setIdCategoria(idCategoria);
            categoria.setNombre(nombre);
            categoria.setDescripcion(descripcion);

            return categoria;
        } catch (SQLException sqle) {
            throw sqle;
        }
    }

    public List<Categoria> consultarCategorias() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String consulta = "SELECT * FROM categoria";

        try {
            PreparedStatement stmt = conexion.prepareStatement(consulta);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                categorias.add(new Categoria(rs.getInt("idCategoria"),
                        rs.getString("nombre"), rs.getString("descripcion")));
            }
        } catch (SQLException sqle) {
            throw sqle;
        }

        return categorias;
    }

    public List<Chef> buscarChef(String nombre) throws SQLException {
        String nombreUsuario = null;
        BigInteger idChef = new BigInteger(String.valueOf(0));
        Date fecha = null;
        List<Chef> chefs = new ArrayList<>();
        String consulta = "SELECT * FROM usuario WHERE UPPER(nombre) LIKE '%" + nombre + "%' OR " +
                "           WHERE UPPER(nombreusuario) LIKE '%" + nombre + "%';";

        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                chefs.add(new Chef(rs.getBigDecimal("idusuario").toBigInteger(),
                        rs.getString("nombreusuario"), rs.getDate("fechacreacion"),
                        rs.getString("nombre")));
            }
        } catch (SQLException sqle) {
            throw sqle;
        }
        return chefs;
    }

    public String linkDomicilio (int idDomicilio) throws SQLException {
        String linkDom = null;
        String consulta = "SELECT link FROM domicilios WHERE iddomicilio =" + idDomicilio;
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                linkDom = rs.getString("link");
            }
        } catch (SQLException sqle) {
            throw sqle;
        }
        return linkDom;
    }

    @Override
    public List<Integer> listarReportes() throws SQLException {
        List<Integer> ids = new ArrayList<>();
        String consulta = "SELECT reporte.idreporte as id FROM reporte;";

        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ids.add(rs.getInt("id"));
            }
        } catch (SQLException sqle) {
            throw sqle;
        }
        return ids;
    }

    @Override
    public List<DTORecetaMiniatura> idsListaFavoritos(BigInteger idUsuario) throws SQLException{
        List<DTORecetaMiniatura> miniaturas = new ArrayList<>();
        String consulta = "SELECT DISTINCT recetaxlista.idreceta as idReceta, receta.nombre as nombre, receta.linkimagen as link\n" +
                          "FROM recetaxlista, listafavoritos, receta\n" +
                          "WHERE listafavoritos.cooker_idusuario = " + idUsuario +" and listafavoritos.idlista = recetaxlista.idlista and receta.idreceta = recetaxlista.idreceta;";
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BigInteger idReceta = rs.getBigDecimal("idReceta").toBigInteger();
                String nombre = rs.getString("nombre");
                String link = rs.getString("link");

                DTORecetaMiniatura miniatura = new DTORecetaMiniatura(idReceta, nombre, link);
                miniaturas.add(miniatura);
            }
        } catch (SQLException sqle) {
            throw sqle;
        }
        return miniaturas;
    }
}
