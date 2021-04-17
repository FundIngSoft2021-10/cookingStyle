package acceso_datos.consultas_bd;

import entidades.dto.DTOListaFavoritos;
import entidades.dto.DTOReceta;
import entidades.modelo.*;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface IControladorCBDRecetasCooker {
    public Ingrediente consultaIngrediente (int idIngrediente) throws SQLException;
    public List<Integer> consultaIngrediente (String nom_ingrediente) throws SQLException;
    public List<LineaIngrediente> consultaLineaIngrediente (BigInteger idReceta) throws SQLException;
    public List<PasoReceta> consultaPasosReceta (BigInteger idReceta) throws SQLException;
    public List<Categoria> consultaCategorias (BigInteger idReceta) throws SQLException;
    public Cooker consultaCooker (BigInteger idCooker) throws SQLException;
    public Chef consultaChefReceta(BigInteger idChef) throws SQLException;
    public List<Calificacion> consultaCalificaciones (BigInteger idReceta) throws SQLException;
    public MotivoReporte consultaMotivoReporte (int idMotivo) throws SQLException;
    public List<Reporte> consultaReportes (BigInteger idReceta) throws SQLException;
    public List<DTOReceta> buscarRecetas () throws SQLException;
    public List<DTOReceta> buscarRecetas (String nombre_receta) throws SQLException;

    public List<DTOReceta> buscarRecetas (int id_ingrediente) throws SQLException;
    public Receta buscarRecetas (BigInteger idreceta) throws SQLException;
}
