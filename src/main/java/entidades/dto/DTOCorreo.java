package entidades.dto;

import entidades.modelo.Calendario;
import entidades.modelo.Chef;

public class DTOCorreo {

    private boolean estado;
    private String mensaje;
    private String correo;

    public DTOCorreo() {
    }

    public DTOCorreo(boolean estado, String mensaje, String correo) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.correo = correo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
