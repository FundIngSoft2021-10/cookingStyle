package acceso_datos.consultas_bd;

import java.math.BigInteger;
import java.sql.SQLException;

public interface IControladorCBDRecetasChef {
    public boolean existeIdReceta(BigInteger idReceta) throws SQLException;
    public boolean existeIngrediente (int idIngrediente) throws  SQLException;
}
