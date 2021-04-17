package acceso_datos.consultas_bd;

import entidades.dto.DTOListaFavoritos;
import entidades.dto.DTOReceta;
import entidades.modelo.*;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface IControladorBDRecetasCooker {
    public Ingrediente consultaIngrediente (int idIngrediente) throws SQLException;
    public List<LineaIngrediente> consultaLineaIngrediente (BigInteger idReceta) throws SQLException;
    public List<PasoReceta> consultaPasosReceta (BigInteger idReceta) throws SQLException;
    public List<Categoria> consultaCategorias (BigInteger idReceta) throws SQLException;
    public Cooker consultaCooker (BigInteger idCooker) throws SQLException;
    public List<Calificacion> consultaCalificaciones (BigInteger idReceta) throws SQLException;
    public MotivoReporte consultaMotivoReporte (int idMotivo) throws SQLException;
    public List<Reporte> consultaReportes (BigInteger idReceta) throws SQLException;
    public List<DTOReceta> buscarRecetas () throws SQLException;
    public Receta buscarRecetas (BigInteger idreceta) throws SQLException;
    public boolean crearListaFavoritosConReceta(DTOListaFavoritos listaFavoritos) throws SQLException;
    public boolean crearListaFavoritos(DTOListaFavoritos listaFavoritos) throws SQLException;
    public boolean insertarRecetaListaFavoritos(BigInteger idreceta, int idlista, BigInteger idusuario) throws SQLException;

}
