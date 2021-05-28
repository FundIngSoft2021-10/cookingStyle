package acceso_datos.consultas_bd;

import entidades.dto.DTOReceta;
import entidades.dto.DTORecetaMiniatura;
import entidades.modelo.*;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface IControladorCBDRecetasCooker {

    public Ingrediente consultaIngrediente (int idIngrediente) throws SQLException;
    public List<DTORecetaMiniatura> consultaIdsIngrediente (String nom_ingrediente) throws SQLException;
    public List<LineaIngrediente> consultaLineaIngrediente (BigInteger idReceta) throws SQLException;
    public List<PasoReceta> consultaPasosReceta (BigInteger idReceta) throws SQLException;
    public List<Categoria> consultaCategorias (BigInteger idReceta) throws SQLException;
    public List<DTORecetaMiniatura> consultaIdCategoria (String nom_categoria) throws SQLException;
    public Cooker consultaCooker (BigInteger idCooker) throws SQLException;
    public Chef consultaChefReceta (BigInteger idChef) throws SQLException;
    public List<DTORecetaMiniatura> consultaIdsChef (String nombre) throws SQLException;
    public List<Calificacion> consultaCalificaciones (BigInteger idReceta) throws SQLException;
    public MotivoReporte consultaMotivoReporte (int idMotivo) throws SQLException;
    public List<Reporte> consultaReportes (BigInteger idReceta) throws SQLException;
    public List<DTOReceta> buscarRecetas () throws SQLException;
    public Receta buscarRecetas (BigInteger idreceta) throws SQLException;
    public List<DTORecetaMiniatura> buscarRecetas (String nombre_receta) throws SQLException;
    public List<DTORecetaMiniatura> buscarRecetasCategoria (int id_categoria) throws SQLException;
    public List<DTOReceta> buscarRecetasIngrediente (int id_ingrediente) throws SQLException;
    public List<DTOReceta> buscarRecetasChef (BigInteger idchef) throws SQLException;
    public boolean recetaEnListaRecetas(int idLista, BigInteger idReceta, BigInteger idUsuario) throws SQLException;
    public ListaFavoritos consultaListaFavoritos (int idLista) throws SQLException;
    public List<Receta> consultaRecetasListaFavoritos (BigInteger idUsuario, int idlista) throws SQLException;
    public Chef consultaRecetaXChef (BigInteger idReceta) throws  SQLException;
    public Categoria consultaCategoria(int idCategoria) throws SQLException;
    public List<Categoria> consultarCategorias() throws SQLException;
    public List<Integer> listaCalificacionReceta(BigInteger idReceta) throws SQLException;
    public List<Integer> listaCalificacionChef(BigInteger idChef) throws SQLException;
    public List<Chef> buscarChef(String nombre) throws SQLException;
    public String linkDomicilio (int idDomicilio) throws SQLException;
    public List<Integer> listarReportes() throws SQLException;
    public List<DTORecetaMiniatura> idsListaFavoritos(BigInteger idUsuario) throws SQLException;
    public int calificacionRecetaXCooker(BigInteger idCooker, BigInteger idReceta) throws SQLException;
}
