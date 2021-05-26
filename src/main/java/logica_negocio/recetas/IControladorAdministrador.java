package logica_negocio.recetas;

import entidades.dto.DTOReceta;

import java.math.BigInteger;
import java.sql.SQLException;

public interface IControladorAdministrador {
    public DTOReceta eliminarVideoInapropiado(BigInteger idreceta);
}
