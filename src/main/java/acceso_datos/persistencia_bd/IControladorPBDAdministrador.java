package acceso_datos.persistencia_bd;

import java.sql.SQLException;

public interface IControladorPBDAdministrador {
    public void eliminarReporte(int idReporte) throws SQLException;
}
