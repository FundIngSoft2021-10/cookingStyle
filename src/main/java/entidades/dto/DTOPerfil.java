package entidades.dto;

import entidades.modelo.Usuario;

public class DTOPerfil {
    private boolean estado;
    private Usuario usuario;
    private String mensaje;

    public DTOPerfil() {
    }

    public DTOPerfil(boolean estado, Usuario usuario, String mensaje) {
        this.estado = estado;
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
