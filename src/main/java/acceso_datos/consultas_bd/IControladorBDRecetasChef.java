package acceso_datos.consultas_bd;

import entidades.dto.DTOListaFavoritos;
import entidades.modelo.*;

import java.sql.SQLException;
import java.util.List;

public interface IControladorBDRecetasChef {
    public boolean subirReceta (String nombre, String descripcion, String linkVideo, String linkImagen, int idUsuario) throws SQLException;
    public boolean modificarReceta (Receta rec, String valorAModificar, String modificacion) throws SQLException;


}
