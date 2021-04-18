package logica_negocio.citas;

import entidades.dto.DTOAgendaChef;
import entidades.modelo.Chef;
import entidades.modelo.Dia;

import java.util.List;

public interface IControladorCitas {
    public DTOAgendaChef crearAgendaChef(Chef chef, List<Dia> dias, List<Integer> horas);
    public DTOAgendaChef consultarDisponibilidadChef(Chef chef);
}
