package acceso_datos.persistencia_bd;

import entidades.modelo.Bloque;
import entidades.modelo.Chef;

import java.sql.SQLException;
import java.util.List;

public interface IControladorPBDCitas {
    public void crearAgendaChef(Chef chef, List<Bloque> calendario) throws SQLException;
}
