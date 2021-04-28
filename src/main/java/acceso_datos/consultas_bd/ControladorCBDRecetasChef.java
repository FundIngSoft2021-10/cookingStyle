package acceso_datos.consultas_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;
import entidades.modelo.Categoria;
import entidades.modelo.Ingrediente;
import entidades.modelo.Receta;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControladorCBDRecetasChef implements IControladorCBDRecetasChef {
    private IControladorCBDRecetas controlCBDRecetas;
    private Connection conexion;

    public ControladorCBDRecetasChef() {
        ControladorBDConexion controladorBDConexion = new ControladorBDConexion();
        this.conexion = controladorBDConexion.conectarMySQL();
        this.controlCBDRecetas = new ControladorCBDRecetas(this.conexion);
    }

    public ControladorCBDRecetasChef(Connection conexion) {
        this.conexion = conexion;
        this.controlCBDRecetas = new ControladorCBDRecetas(this.conexion);
    }

    @Override
    public Receta buscarRecetas(BigInteger idreceta) throws SQLException {
        return this.controlCBDRecetas.buscarRecetas(idreceta);
    }

    @Override
    public boolean existeIdReceta(BigInteger idReceta) throws SQLException {
        String consulta = "SELECT COUNT(*) FROM receta WHERE idreceta = ?";

        try {
            PreparedStatement stmt = conexion.prepareStatement(consulta);
            stmt.setBigDecimal(1, new BigDecimal(idReceta));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (SQLException sqle) {
            throw sqle;
        }

        return false;
    }

    @Override
    public Ingrediente consultaIngrediente(String nombreIngrediente) throws SQLException {
        String consultaIngrediente = "SELECT * FROM ingrediente WHERE nombre = ?";

        int idIngrediente = 0;
        try (PreparedStatement stmt = conexion.prepareStatement(consultaIngrediente)) {
            stmt.setString(1, nombreIngrediente);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                idIngrediente = rs.getInt("idingrediente");
            }
        } catch (SQLException sqle) {
            throw sqle;
        }

        return new Ingrediente(idIngrediente, nombreIngrediente);
    }

    @Override
    public int consultarIdIngrediente() throws SQLException {
        String consultaIngredientes = "SELECT * FROM ingrediente ORDER BY idingrediente DESC";
        int idIngrediente = 0;
        try (PreparedStatement stmt = conexion.prepareStatement(consultaIngredientes)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                idIngrediente = rs.getInt("idingrediente");
            }
        } catch (SQLException sqle) {
            throw sqle;
        }
        return idIngrediente + 1;
    }

    @Override
    public List<String> categoriasXChef(BigInteger idChef) throws SQLException {
        String consulta =
                "SELECT categoria.nombre, receta.chef_idusuario \n" +
                        "FROM receta, categoriaxreceta, categoria \n" +
                        "WHERE categoriaxreceta.idreceta = receta.idreceta AND categoriaxreceta.idcategoria = categoria.idcategoria AND receta.chef_idusuario = ?\n" +
                        "GROUP BY categoria.nombre;";
        List<String> categorias = new ArrayList<>();
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            BigDecimal id = new BigDecimal(idChef);
            stmt.setBigDecimal(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String categoria = rs.getString("categoria.nombre");
                categorias.add(categoria);
            }
        } catch (SQLException sqle) {
            categorias = null;
        }
        return categorias;
    }

    @Override
    public List<Categoria> consultarCategorias() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String consulta = "SELECT * FROM categoria";

        try {
            PreparedStatement stmt = conexion.prepareStatement(consulta);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                categorias.add(new Categoria(rs.getInt("idCategoria"),
                        rs.getString("nombre"), rs.getString("descripcion")));
            }
        } catch (SQLException sqle) {
            throw sqle;
        }

        return categorias;
    }
}
