package logica_negocio.perfiles;

import acceso_datos.persistencia_bd.ControladorPBDPerfiles;

public class ControladorPerfiles implements IControladorPerfiles{

    private ControladorPBDPerfiles controlPBD;

    public ControladorPerfiles(){
        this.controlPBD = new ControladorPBDPerfiles();
    }


}
