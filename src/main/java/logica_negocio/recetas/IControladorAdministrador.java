package logica_negocio.recetas;

import entidades.dto.DTOReceta;
import entidades.modelo.Reporte;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface IControladorAdministrador {
    public DTOReceta eliminarVideoInapropiado(BigInteger idreceta);

    /**
     *
     * @return
     */
    public List<Reporte> revisarReportes ();
}
