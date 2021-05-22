package logica_negocio.perfiles;

import entidades.dto.DTOPerfil;
import entidades.modelo.Usuario;

public interface IControladorPerfiles {

    public DTOPerfil modificarPerfil(Usuario usuario, String valorAModificar, String modificacion );
}
