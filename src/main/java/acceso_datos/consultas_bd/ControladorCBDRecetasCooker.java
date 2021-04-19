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
import java.util.List;

public class ControladorCBDRecetasCooker implements IControladorCBDRecetasCooker {
    private ControladorBDConexion controladorBDConexion;
    private Connection conexion;
    private IControladorCBDRecetas controlRecetas;

    public ControladorCBDRecetasCooker() {
        controlRecetas = new ControladorCBDRecetas();
        controladorBDConexion = new ControladorBDConexion();
        conexion = controladorBDConexion.conectarMySQL();
    }

    @Override
    public Ingrediente consultaIngrediente(int idIngrediente) throws SQLException {
        return this.controlRecetas.consultaIngrediente(idIngrediente);
    }

    @Override
    public List<Integer> consultaIdsIngrediente(String nom_ingrediente) throws SQLException {
        String consultaIngrediente = "SELECT * FROM ingrediente WHERE UPPER(ingrediente.nombre) LIKE '%" + nom_ingrediente + "%';";
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
    public List<LineaIngrediente> consultaLineaIngrediente(BigInteger idReceta) throws SQLException {
        return this.controlRecetas.consultaLineaIngrediente(idReceta);
    }

    @Override
    public List<PasoReceta> consultaPasosReceta(BigInteger idReceta) throws SQLException {
        return this.controlRecetas.consultaPasosReceta(idReceta);
    }

    @Override
    public List<Categoria> consultaCategorias(BigInteger idReceta) throws SQLException {
        return this.controlRecetas.consultaCategorias(idReceta);
    }

    @Override
    public int consultaIdCategoria(String nom_categoria) throws SQLException {
        int idCategoria = 0;
        String consultaCategoria = "SELECT * FROM categoria WHERE UPPER(categoria.nombre) LIKE '%" + nom_categoria + "%';";
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
    public Cooker consultaCooker(BigInteger idCooker) throws SQLException {
        return this.controlRecetas.consultaCooker(idCooker);
    }

    @Override
    public List<Calificacion> consultaCalificaciones(BigInteger idReceta) throws SQLException {
        return this.controlRecetas.consultaCalificaciones(idReceta);
    }

    @Override
    public MotivoReporte consultaMotivoReporte(int idMotivo) throws SQLException {
        return this.controlRecetas.consultaMotivoReporte(idMotivo);
    }

    @Override
    public List<Reporte> consultaReportes(BigInteger idReceta) throws SQLException {
        return this.controlRecetas.consultaReportes(idReceta);
    }

    @Override
    public Chef consultaChefReceta(BigInteger idChef) throws SQLException {
        return this.controlRecetas.consultaChefReceta(idChef);
    }

    @Override
    public List<BigInteger> consultaIdsChef(String nombre) throws SQLException {
        List<BigInteger> idsChefs = new ArrayList<>();
        BigInteger idChef;
        int tipoUsuario;
        String consulta = "SELECT * FROM usuario WHERE UPPER(nombre) LIKE '%" + nombre + "%' OR UPPER(nombreusuario) LIKE '" + nombre + "';";
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idChef = rs.getBigDecimal("idusuario").toBigInteger();
                tipoUsuario = rs.getInt("idtipousuario");
                if (tipoUsuario == 3) {
                    idsChefs.add(idChef);
                }
            }
        } catch (SQLException sqle) {
            throw sqle;
        }
        return idsChefs;
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
        return this.controlRecetas.buscarRecetas(idreceta);
    }

    @Override
    public List<DTOReceta> buscarRecetas(String nombre_receta) throws SQLException {
        List<DTOReceta> recetas = new ArrayList<>();
        String consulta = "SELECT * FROM receta WHERE UPPER(nombre) LIKE '%" + nombre_receta + "%';";
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
        return this.controlRecetas.consultaRecetaXChef(idReceta);
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
}
