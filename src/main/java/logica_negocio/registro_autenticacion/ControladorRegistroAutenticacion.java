package logica_negocio.registro_autenticacion;

import entidades.dto.DTOAutenticacion;

public class ControladorRegistroAutenticacion {
    public IControladorSeguridad controlSeguridad;

    public ControladorRegistroAutenticacion() {
        controlSeguridad = new ControladorSeguridad();
    }

    public DTOAutenticacion autenticarUsuario(String correo, String password) {
        DTOAutenticacion autenticacion = null;
        // Llamar a BD para verificar el correo
        // Consulta que devuelva idUsuario y credenciales (DTO)

        // Seguridad -> password y salt, y que devuelva el hash de este intento

        // Comparar hash intento, con hash de BD

        // Si sí coinciden, WOO! -> True, Mensaje de éxito, Usuario
        // Si no coinciden, BOO! -> False, Mensajes de por qué falló, NO SE MANDA USUARIO
        return autenticacion;
    }

    // public DTORegistro? registrarUsuario(?) {
        // Tener en cuenta que se pueden tener distintos tipos de usuario
        // Utilizar this.controlSeguridad y FactoryUsuario
    // }
}

