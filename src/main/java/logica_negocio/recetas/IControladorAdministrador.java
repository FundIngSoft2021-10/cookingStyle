package logica_negocio.recetas;

import entidades.dto.DTOExito;
import entidades.dto.DTOReceta;
import entidades.modelo.Reporte;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface IControladorAdministrador {
    public DTOReceta eliminarVideoInapropiado(BigInteger idreceta);

    /**
     * Revisar Reportes
     * @return el total de reportes en el sistema
     */
    public List<Reporte> revisarReportes ();

    public DTOExito eliminarReporte(int idReporte);
}
