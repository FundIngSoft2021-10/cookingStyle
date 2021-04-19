package acceso_datos.persistencia_bd;

import entidades.dto.DTOIngrediente;
import entidades.dto.DTOLineaIngrediente;
import entidades.dto.DTOListaFavoritos;
import entidades.modelo.*;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface IControladorPBDRecetasChef {
    //public boolean subirIngrediente (Ingrediente ingrediente)throws SQLException;
    public boolean subirLineaIngrediente (List<LineaIngrediente> lineaIngrediente, BigInteger idReceta) throws SQLException;
    public boolean subirCategoria (List<Categoria> categorias, BigInteger idReceta) throws SQLException;
    public boolean subirPasoreceta (List<PasoReceta> pasoReceta, BigInteger idReceta)throws SQLException;
    //public boolean subirCalificacion (List<Calificacion> calificaciones, BigInteger idReceta) throws SQLException;
    //public boolean subirReporte (List<Reporte> reportes, BigInteger idReceta) throws SQLException;
    public boolean subirReceta (Receta rec, BigInteger idUsuario) throws SQLException;
    public boolean modificarReceta (Receta rec, String valorAModificar, String modificacion) throws SQLException;
    public int eliminarReceta (BigInteger idUsuario) throws SQLException;


}
