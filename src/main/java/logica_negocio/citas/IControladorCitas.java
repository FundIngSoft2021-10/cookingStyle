package logica_negocio.citas;

import entidades.dto.DTOAgendaChef;
import entidades.dto.DTOCorreo;
import entidades.modelo.Bloque;
import entidades.modelo.Chef;
import entidades.modelo.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface IControladorCitas {
    public DTOAgendaChef crearAgendaChef(Chef chef, List<Bloque> bloques);
    public DTOAgendaChef consultarAgendaChef(Chef chef);

    /**
     * Correo del usuario
     * @param usuario usuario del correo que se está buscando
     * @return DTOCorreo con la información solicitada
     */
    public DTOCorreo correoUsuario(Usuario usuario);
}
