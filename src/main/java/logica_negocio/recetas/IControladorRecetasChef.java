package logica_negocio.recetas;

import entidades.dto.DTOReceta;
import entidades.modelo.Categoria;
import entidades.modelo.LineaIngrediente;
import entidades.modelo.PasoReceta;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface IControladorRecetasChef {
    public void subirReceta (String nombre, String descripcion, String link_video, int tipovideo, String link_imagen, List<LineaIngrediente> ingredientes, List<Categoria> categorias, List<PasoReceta> pasosReceta) throws SQLException;
    public boolean validarUrl (String url);
    public DTOReceta modificarNombre(String nuevoNombre, BigInteger idReceta);
    public DTOReceta modificarDecripcion(String nuevaDesc, BigInteger idReceta);
}
