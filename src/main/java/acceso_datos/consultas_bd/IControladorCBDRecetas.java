package acceso_datos.consultas_bd;

import entidades.modelo.*;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IControladorCBDRecetas {

    public Ingrediente consultaIngrediente (int idIngrediente) throws SQLException;
    public List<LineaIngrediente> consultaLineaIngrediente (BigInteger idReceta) throws SQLException;
    public List<PasoReceta> consultaPasosReceta (BigInteger idReceta) throws SQLException;
    public List<Categoria> consultaCategorias (BigInteger idReceta) throws SQLException;
    public Cooker consultaCooker (BigInteger idCooker) throws SQLException;
    public List<Calificacion> consultaCalificaciones (BigInteger idReceta) throws SQLException;
    public MotivoReporte consultaMotivoReporte (int idMotivo) throws SQLException;
    public List<Reporte> consultaReportes (BigInteger idReceta) throws SQLException;
    public Receta buscarRecetas (BigInteger idreceta) throws SQLException;
    public Chef consultaChefReceta (BigInteger idChef) throws SQLException;
    public Chef consultaRecetaXChef(BigInteger idReceta) throws SQLException;
}
