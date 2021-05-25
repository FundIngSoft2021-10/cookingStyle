package acceso_datos.consultas_bd;

import entidades.modelo.Calendario;
import entidades.modelo.Chef;

import java.math.BigInteger;
import java.sql.SQLException;

public interface IControladorCBDCitas {
    public Calendario buscarCalendarioChef (Chef chef) throws SQLException;
    public String getCorreo (BigInteger idUsuario) throws SQLException;
}
