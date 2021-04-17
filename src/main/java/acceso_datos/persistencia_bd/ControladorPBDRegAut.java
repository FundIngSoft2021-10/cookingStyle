package acceso_datos.persistencia_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;

import java.sql.Connection;

public class ControladorPBDRegAut implements IControladorPBDRegAut {
    ControladorBDConexion controladorBDConexion;
    Connection conexion;

    public ControladorPBDRegAut() {
        controladorBDConexion = new ControladorBDConexion();
        conexion = controladorBDConexion.conectarMySQL();
    }

}
