package logica_negocio.recetas;

import entidades.dto.DTOReceta;
import entidades.modelo.Categoria;
import entidades.modelo.LineaIngrediente;
import entidades.modelo.PasoReceta;
import entidades.modelo.TipoVideo;

import java.sql.SQLException;
import java.util.List;

public interface IControladorRecetasChef {
    public DTOReceta subirReceta (String nombre, String descripcion, String link_video, TipoVideo tipovideo, String link_imagen, List<LineaIngrediente> ingredientes, List<Categoria> categorias, List<PasoReceta> pasosReceta);
    public boolean validarUrl (String url);
    public boolean validarTipoVideo(String url, TipoVideo tipovideo);
}
