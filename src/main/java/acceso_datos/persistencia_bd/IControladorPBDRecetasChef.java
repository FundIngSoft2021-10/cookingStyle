package acceso_datos.persistencia_bd;

import entidades.dto.DTOIngrediente;
import entidades.dto.DTOLineaIngrediente;
import entidades.dto.DTOListaFavoritos;
import entidades.modelo.*;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface IControladorPBDRecetasChef {
    public boolean subirReceta (Receta rec, BigInteger idUsuario) throws SQLException;
    public boolean subirIngrediente (Ingrediente ingrediente)throws SQLException;
    public boolean subirLineaIngrediente (List<LineaIngrediente> lineaIngrediente, BigInteger idReceta) throws SQLException;
    public boolean subirCategoria (List<Categoria> categorias, BigInteger idReceta) throws SQLException;
    public boolean subirPasoreceta (List<PasoReceta> pasoReceta, BigInteger idReceta)throws SQLException;
    //public boolean subirCalificacion (List<Calificacion> calificaciones, BigInteger idReceta) throws SQLException;
    //public boolean subirReporte (List<Reporte> reportes, BigInteger idReceta) throws SQLException;
    public int eliminarRecetas (BigInteger idUsuario) throws SQLException;
    public boolean modificarNombreReceta(String nuevoNombre, BigInteger idReceta) throws SQLException;
    public boolean modificarDescrReceta(String nuevaDecrip, BigInteger idReceta) throws SQLException;
    public boolean modificarLinkVideoReceta(String nuevoLink, BigInteger idReceta) throws SQLException;
    public boolean modificarLinkImgReceta(String nuevoLink, BigInteger idReceta) throws SQLException;
    public boolean eliminarReceta (BigInteger idReceta) throws SQLException;

}
