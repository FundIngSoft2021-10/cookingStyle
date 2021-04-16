package acceso_datos.consultas_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;
import entidades.modelo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorBDRecetas {
    ControladorBDConexion controladorBDConexion = new ControladorBDConexion();
    Connection conexion = controladorBDConexion.conectarMySQL();

    public Ingrediente consultaIngrediente (int idIngrediente) {
        String consultaIngrediente = "SELECT * FROM ingrediente WHERE ingrediente.idingrediente = "+idIngrediente+";";
        System.out.println(consultaIngrediente);

        String nombreIngrediente = null;
        try (PreparedStatement stmt = conexion.prepareStatement(consultaIngrediente)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                nombreIngrediente = rs.getString("nombre");
            }
        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución consultaIngrediente:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());
        }
        Ingrediente ingrediente = new Ingrediente(idIngrediente, nombreIngrediente);
        return ingrediente;
    }

    public List<LineaIngrediente> consultaLineaIngrediente (int idReceta) {
        List<LineaIngrediente> lineaIngredientes = new ArrayList<>();
        String consultaLineaIngredientes = "SELECT * FROM lineaingrediente WHERE lineaingrediente.receta_idReceta = "+idReceta+";";
        System.out.println(consultaLineaIngredientes);
        try (PreparedStatement stmt = conexion.prepareStatement(consultaLineaIngredientes)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idIngrediente = rs.getInt("ingrediente_idingrediente");
                float cantidad = rs.getFloat("cantidad");
                //Esto toca cambiarlo-----------------------------------------------------
                String medida = rs.getString("medida");
                Ingrediente ingrediente= consultaIngrediente(idIngrediente);
                LineaIngrediente resultado = new LineaIngrediente(ingrediente, cantidad, Medida.KILOGRAMO);
                lineaIngredientes.add(resultado);
            }

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución lineaingredientes:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());
        }
        return lineaIngredientes;
    }

    public List<PasoReceta> consultaPasosReceta (int idReceta) {
        List<PasoReceta> pasosReceta = new ArrayList<>();
        String consultaPasos = "SELECT * FROM pasoreceta, receta WHERE pasoreceta.receta_idReceta ="+idReceta+" and receta.idreceta="+idReceta+";";
        System.out.println(consultaPasos);
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
            System.out.println("Error en la ejecución paso receta:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());
        }
        return pasosReceta;
    }

    public List<Categoria> consultaCategorias (int idReceta) {
        List<Categoria> categorias = new ArrayList<>();
        String consultaCategoriasxRecetas = "SELECT * FROM categoriaxreceta WHERE categoriaxreceta.receta_idReceta="+idReceta+";";
        System.out.println(consultaCategoriasxRecetas);
        try (PreparedStatement stmt = conexion.prepareStatement(consultaCategoriasxRecetas)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idCategoria = rs.getInt("categoria_idcategoria");
                String consultaCategorias = "SELECT * FROM categoria WHERE categoria.idcategoria = "+idCategoria+";";
                System.out.println(consultaCategorias);
                String nombre=null, descripcion=null;
                try (PreparedStatement stmt1 = conexion.prepareStatement(consultaCategorias)) {
                    ResultSet rs1 = stmt1.executeQuery();
                    while (rs1.next()) {
                        nombre = rs1.getString("nombre");
                        descripcion = rs1.getString("descripcion");
                    }
                } catch (SQLException sqle) {
                    System.out.println("Error en la ejecución categorias:"
                            + sqle.getErrorCode() + " " + sqle.getMessage());
                }
                Categoria resultado = new Categoria(idCategoria, nombre, descripcion);
                categorias.add(resultado);
            }

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución categoriaxreceta:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());
        }
        return categorias;
    }

    public Cooker consultaCooker (int idCooker) {
        Cooker cooker = null;
        String nombreUsuario=null, correo=null, nombre=null, apellido=null;
        Date fecha=null;
        String consultaCookers = "SELECT * FROM usuario WHERE usuario.idusuario = "+idCooker+";";
        System.out.println(consultaCookers);
        try (PreparedStatement stmt = conexion.prepareStatement(consultaCookers)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                nombreUsuario = rs.getString("nombreusuario");
                //Toca cambiar estooooooooooooo-----------------------
                correo = "correo@gmail.com";
                fecha = rs.getDate("fechacreacion");
                nombre = rs.getString("nombre");
                //Esto toca quitarlooooooo---------------------
                apellido = "No";
            }
        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución cooker:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());
        }
        cooker = new Cooker(idCooker, nombreUsuario, correo, fecha, nombre, apellido);
        return cooker;
    }

    public List<Calificacion> consultaCalificaciones (int idReceta) {
        List<Calificacion> calificaciones = new ArrayList<>();
        String consultaCalificacion = "SELECT * FROM calificacion WHERE calificacion.receta_idReceta="+idReceta+";";
        System.out.println(consultaCalificacion);
        try (PreparedStatement stmt = conexion.prepareStatement(consultaCalificacion)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idCooker = rs.getInt("cooker_idusuario");
                int valor = rs.getInt("valor");
                Cooker cooker = consultaCooker(idCooker);
                Calificacion resultado = new Calificacion(valor,cooker);
                calificaciones.add(resultado);
            }

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución calificacion:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());
        }
        return calificaciones;
    }

    public MotivoReporte consultaMotivoReporte (int idMotivo) {
        MotivoReporte motivoReporte = null;
        String nombre=null, descripcion=null;

        String consultaMotivo = "SELECT * FROM motivo WHERE motivo.idmotivo = "+idMotivo+";";
        System.out.println(consultaMotivo);
        try (PreparedStatement stmt = conexion.prepareStatement(consultaMotivo)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                nombre = rs.getString("nombre");
                descripcion = rs.getString("descripcion");
            }
        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución cooker:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());
        }
        motivoReporte = new MotivoReporte(idMotivo, nombre, descripcion);
        return motivoReporte;
    }

    public List<Reporte> consultaReportes (int idReceta) {
        List<Reporte> reportes = new ArrayList<>();
        String consultaReporte = "SELECT * FROM reporte WHERE reporte.receta_idReceta="+idReceta+";";
        System.out.println(consultaReporte);
        try (PreparedStatement stmt = conexion.prepareStatement(consultaReporte)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idCooker = rs.getInt("cooker_idusuario");
                int idMotivo = rs.getInt("motivo_idmotivo");
                Date fecha = rs.getDate("fecha");
                boolean resuelto = true;

                Cooker cooker = consultaCooker(idCooker);
                MotivoReporte motivoReporte = consultaMotivoReporte(idMotivo);
                Reporte resultado = new Reporte(cooker, motivoReporte, fecha, resuelto);
                reportes.add(resultado);
            }

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución reporte:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());
        }
        return reportes;
    }

    public List<Receta> buscarRecetas () {
        List<Receta> recetas = new ArrayList<>();
        //String consulta = "SELECT * FROM receta WHERE UPPER(nombre) LIKE '%"+nombre+"%';";
        String consulta = "SELECT * FROM receta;";
        System.out.println(consulta);
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idReceta = rs.getInt("idReceta");
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
            System.out.println("Error en la ejecución receta:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());
        }
        return recetas;
    }
}
