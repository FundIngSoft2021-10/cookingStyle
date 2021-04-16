package logica_negocio.registro_autenticacion;

import entidades.dto.DTOAutenticacion;
import entidades.dto.DTORegistro;

import entidades.modelo.CredencialesUsuario;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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

    public DTORegistro registrarUsuario(String correo, String password, String tipoUsuario, String nombreUsuario, String nombre, String apellido) {
        // Tener en cuenta que se pueden tener distintos tipos de usuario
        DTORegistro DTORegistro = null;
        // consultar BD para saber si el correo ya existe, si es asi
        // return mensaje fallo, NO SE CREA USUARIO
        // si el correo no existe, se sigue con las funcionalidades
        byte [] saltBit = new byte[0];
        try {
            saltBit = controlSeguridad.generarSalt();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        String salt = controlSeguridad.toHex(saltBit);
        String hash = "hola";
        try {
            hash = controlSeguridad.generarHash(password,saltBit);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        CredencialesUsuario credencialesUsuario = new CredencialesUsuario(correo,salt,hash);
        // Utilizar this.controlSeguridad y FactoryUsuario
        // }
        return DTORegistro;
    }

}