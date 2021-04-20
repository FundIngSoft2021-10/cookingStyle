package logica_negocio.citas;

import entidades.dto.DTOAgendaChef;
import entidades.modelo.Bloque;
import entidades.modelo.Chef;

import java.util.List;

public interface IControladorCitas {
    public DTOAgendaChef crearAgendaChef(Chef chef, List<Bloque> bloques);
    public DTOAgendaChef consultarAgendaChef(Chef chef);
}
