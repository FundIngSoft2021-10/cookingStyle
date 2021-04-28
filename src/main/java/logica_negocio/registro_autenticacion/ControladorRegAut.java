package logica_negocio.registro_autenticacion;

import acceso_datos.consultas_bd.*;
import acceso_datos.persistencia_bd.*;
import entidades.dto.*;
import entidades.modelo.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

public class ControladorRegAut implements IControladorRegAut {
    private final IControladorSeguridad controlSeguridad;
    private final IControladorCBDRegAut controlConsultaBD;
    private final IControladorPBDRegAut controlPersistenciaBD;
    private Connection conexion;

    public ControladorRegAut(Connection conexion) {
        this.controlSeguridad = new ControladorSeguridad();
        this.controlConsultaBD = new ControladorCBDRegAut(conexion);
        this.controlPersistenciaBD = new ControladorPBDRegAut(conexion);
        this.conexion = conexion;
    }

    /**
     * @inheritDoc
     */
    @Override
    public DTORegistro registrarUsuario(TipoUsuario tipoUsuario, String correo, String password, String nombreUsuario, String nombre) {
        boolean existe;

        // Determinar si el correo no se encuentra registrado ya en la aplicación
        try {
            existe = this.controlConsultaBD.existeCorreoUsuario(correo);
            if (existe) {
                return new DTORegistro(false, "El correo proporcionado ya se encuentra registrado en la App", null);
            }
        } catch (SQLException e) {
            return new DTORegistro(false, "Error en la base de datos; " + e.getMessage(), null);
        }

        // Determinar si el nombre de usuario ya existe
        try {
            existe = this.controlConsultaBD.existeNombreUsuario(nombreUsuario);
            if (existe) {
                return new DTORegistro(false, "El nombre de usuario ya existe", null);
            }
        } catch (SQLException e) {
            return new DTORegistro(false, "Error en la base de datos; " + e.getMessage(), null);
        }

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

        // Generar el usuario
        CredencialesUsuario credenciales = new CredencialesUsuario(correo, salt, hash);
        FactoryUsuario factory = new FactoryUsuario(tipoUsuario, this.conexion);
        Usuario usuario;
        try {
            usuario = factory.crearUsuario(nombreUsuario, nombre);
        } catch (SQLException e) {
            return new DTORegistro(false, "Error en la base de datos; " + e.getMessage(), null);
        }
        usuario.setCredenciales(credenciales);

        // Persistir en la BD
        try {
            this.controlPersistenciaBD.crearUsuario(tipoUsuario, usuario);
        } catch (SQLException e) {
            return new DTORegistro(false, "Error en la base de datos; " + e.getMessage(), null);
        }

        return new DTORegistro(true, "Usuario registrado exitosamente", usuario);
    }

    /**
     * @inheritDoc
     */
    @Override
    public DTOAutenticacion autenticarUsuario(TipoUsuario tipoUsuario, String correo, String password) {
        // Verificar si el correo está registrado en la aplicación
        DTOCredencialesBD credencialesBD;
        try {
            credencialesBD = this.controlConsultaBD.buscarCredencialesUsuario(correo);
        } catch (SQLException e) {
            return new DTOAutenticacion(false, "Error en la base de datos; " + e.getMessage(), null);
        }

        if (!credencialesBD.isExiste()) {
            return new DTOAutenticacion(false, "El correo proporcionado no se encuentra registrado en la App", null);
        }

        // Verificar si la contraseña coincide con el hash guardado en la base de datos
        boolean coinciden;
        try {
            coinciden = this.controlSeguridad.validarPassword(password, credencialesBD.getCredenciales().getSalt(),
                    credencialesBD.getCredenciales().getHash());
        } catch (Exception e) {
            return new DTOAutenticacion(false, "Error en la generacion de hash; " + e.getMessage(), null);
        }

        if (coinciden) {
            try {
                Usuario usuario = this.controlConsultaBD.buscarUsuario(credencialesBD.getIdUsuario());

                return new DTOAutenticacion(true, "Usuario autenticado exitosamente", usuario);
            } catch (Exception e) {
                return new DTOAutenticacion(false, "Error en la base de datos; " + e.getMessage(), null);
            }
        } else {
            return new DTOAutenticacion(false, "Contraseña incorrecta", null);
        }
    }
}
