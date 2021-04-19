package acceso_datos.consultas_bd;

import entidades.modelo.Calendario;
import entidades.modelo.Chef;

import java.sql.SQLException;

public interface IControladorCBDCitas {
    public Calendario buscarCalendarioChef (Chef chef) throws SQLException;
}
