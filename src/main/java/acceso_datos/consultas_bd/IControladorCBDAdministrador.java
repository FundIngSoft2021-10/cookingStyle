package acceso_datos.consultas_bd;

import entidades.modelo.Reporte;

import java.sql.SQLException;
import java.util.List;

public interface IControladorCBDAdministrador {
    public List<Reporte> revisarReportes() throws SQLException;
}
