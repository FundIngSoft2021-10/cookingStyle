package acceso_datos.consultas_bd;

import entidades.modelo.Ingrediente;

import java.math.BigInteger;
import java.sql.SQLException;

public interface IControladorCBDRecetasChef {
    public boolean existeIdReceta(BigInteger idReceta) throws SQLException;
    public Ingrediente consultaIngrediente (String nombreIngrediente) throws  SQLException;
    public int consultarIdIngrediente () throws SQLException;
}
