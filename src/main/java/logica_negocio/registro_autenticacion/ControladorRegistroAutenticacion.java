package logica_negocio.registro_autenticacion;

import entidades.dto.*;
import entidades.modelo.*;

import java.security.NoSuchAlgorithmException;

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

    public DTORegistro registrarUsuario(TipoUsuario tipo, String correo, String password, String nombreUsuario, String nombre) {
        // consultar BD para saber si el correo ya existe, si es asi
        // return mensaje fallo, NO SE CREA USUARIO
        // si el correo no existe, se sigue con las funcionalidades

        // Generar salt y encriptar contraseña en hash
        byte[] saltByte;
        try {
            saltByte = this.controlSeguridad.generarSalt();
        } catch (NoSuchAlgorithmException e) {
            return new DTORegistro(false, "Error en la generacion de salt; " + e.getMessage(), null);
        }
        String salt = this.controlSeguridad.toHex(saltByte);
        String hash;
        try {
            hash = this.controlSeguridad.generarHash(password, saltByte);
        } catch (Exception e) {
            return new DTORegistro(false, "Error en la generacion de hash; " + e.getMessage(), null);
        }

        CredencialesUsuario credencialesUsuario = new CredencialesUsuario(correo, salt, hash);
        FactoryUsuario factory = new FactoryUsuario(tipo);
        Usuario usuario = factory.crearUsuario(nombreUsuario, nombre);

        // Persistir el usuario en la base de datos

        return new DTORegistro(true, "Usuario creado exitosamente", usuario);
    }
}
