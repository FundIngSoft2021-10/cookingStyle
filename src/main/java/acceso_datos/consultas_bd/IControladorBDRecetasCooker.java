package acceso_datos.consultas_bd;

import entidades.dto.DTOListaFavoritos;
import entidades.modelo.*;

import java.sql.SQLException;
import java.util.List;

public interface IControladorBDRecetasCooker {
    public Ingrediente consultaIngrediente (int idIngrediente) throws SQLException;
    public List<LineaIngrediente> consultaLineaIngrediente (int idReceta) throws SQLException;
    public List<PasoReceta> consultaPasosReceta (int idReceta) throws SQLException;
    public List<Categoria> consultaCategorias (int idReceta) throws SQLException;
    public Cooker consultaCooker (int idCooker) throws SQLException;
    public List<Calificacion> consultaCalificaciones (int idReceta) throws SQLException;
    public MotivoReporte consultaMotivoReporte (int idMotivo) throws SQLException;
    public List<Reporte> consultaReportes (int idReceta) throws SQLException;
    public List<Receta> buscarRecetas () throws SQLException;
    public Receta buscarRecetas (int idreceta) throws SQLException;
    public boolean crearListaFavoritosConReceta(DTOListaFavoritos listaFavoritos) throws SQLException;
    public boolean crearListaFavoritos(DTOListaFavoritos listaFavoritos) throws SQLException;
    public boolean insertarRecetaListaFavoritos(int idreceta, int idlista, int idusuario) throws SQLException;

}
