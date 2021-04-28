package logica_negocio.registro_autenticacion;

import acceso_datos.conexion_bd.ControladorBDConexion;

import java.sql.Connection;

public class ControladorInicio implements IControladorInicio {
    private final ControladorBDConexion controladorBDConexion;

    public ControladorInicio() {
        this.controladorBDConexion = new ControladorBDConexion();
    }

    @Override
    public Connection conectarBD() {
        Connection conexion = this.controladorBDConexion.conectarMySQL();

        if (conexion != null) {
            return conexion;
        } else {
            // TODO: Arrojar exception
            return null;
        }
    }
}
