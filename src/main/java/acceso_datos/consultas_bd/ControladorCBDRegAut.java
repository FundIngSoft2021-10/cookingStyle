package acceso_datos.consultas_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;

import java.sql.*;

public class ControladorCBDRegAut implements IControladorCBDRegAut {
    ControladorBDConexion controladorBDConexion;
    Connection conexion;

    public ControladorCBDRegAut() {
        controladorBDConexion = new ControladorBDConexion();
        conexion = controladorBDConexion.conectarMySQL();
    }

}
