package acceso_datos.consultas_bd;

import entidades.modelo.Categoria;
import entidades.modelo.Ingrediente;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface IControladorCBDRecetasChef {
    public boolean existeIdReceta(BigInteger idReceta) throws SQLException;
    public Ingrediente consultaIngrediente (String nombreIngrediente) throws  SQLException;
    public int consultarIdIngrediente () throws SQLException;
    public List<String> categoriasXChef(BigInteger idChef) throws SQLException;
    public List<Categoria> consultarCategorias() throws SQLException;
}
