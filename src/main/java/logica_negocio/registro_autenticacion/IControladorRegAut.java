package logica_negocio.registro_autenticacion;

import entidades.dto.DTOAutenticacion;
import entidades.dto.DTORegistro;
import entidades.modelo.TipoUsuario;

public interface IControladorRegAut {
    /**
     * Registra un nuevo usuario en la aplicación (base de datos) con los datos dados
     * @param tipo el tipo del usuario
     * @param correo el correo del usuario
     * @param password la contraseña del usuario
     * @param nombreUsuario el nombre de usuario
     * @param nombre el nombre completo del usuario
     * @return el estado de intento de registro
     */
    public DTORegistro registrarUsuario(TipoUsuario tipo, String correo, String password, String nombreUsuario, String nombre);

    /**
     * Autentica un usuario en la aplicación, buscando su información en la base de datos
     * @param correo el correo de intento de autenticación
     * @param password la contraseña de intento de autenticación
     * @return el estado de intento de autenticación
     */
    public DTOAutenticacion autenticarUsuario(String correo, String password);
}
